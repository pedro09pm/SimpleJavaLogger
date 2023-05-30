// Simple Java Logger by Pedro Marín Sanchis, lisenced under MIT.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime;
import java.time.LocalDate;


public class Logger {
    // Configuration.
    private int storedEntries = 0;
    private int writeToFileInterval = 100;
    private String logFilePath = "Logs" + File.separatorChar;
    private String currentLog = "DefaultLog";
    private String logHeaderText = "SIMPLE JAVA LOGGER\n\n" + "-".repeat(50); // Default Header Text.

    // Log.
    private StringBuffer logBuffer = new StringBuffer();
    private File logFile = null;

    // LogType.
    private static byte[] paddingArray;
    private static byte biggestLogTypeLength = 0;
    private boolean[] isLoggedArray = {true, true, true, true, true,}; // Must be declared in the same order as enum LogType [HEADER, WARNING...].

    public static enum LogType {
        // LogType(isShown, includesTime).
        HEADER(false, false),
        WARNING(true, true),
        INFO(true, true),
        DEBUG(true, true),
        REQUIRED(true, true);

        private final boolean isTagShown;
        private final boolean includesTime;

        private LogType (boolean isTagShown, boolean includesTime) {
            this.isTagShown = isTagShown;
            this.includesTime = includesTime;
        }
    }

    public Logger(String logFilePath, String logHeaderText) {
        if (logHeaderText != null) {
            this.logFilePath = logFilePath + File.separatorChar;
        }
        
        if (logHeaderText != null) {
            this.logHeaderText = logHeaderText;
        }

        generateIsLoggedArray();
        generatePaddingArray();
        currentLog = "Log["+getCurrentTime()+"]"; 
        logFile = findAvailableLog();
        log(this.logHeaderText, LogType.HEADER);
        writeToFile();
    }

    public void setWriteToFileInterval(int writeToFileInterval) {
        this.writeToFileInterval = writeToFileInterval;
    }

    private File findAvailableLog() {
        int count = 1;
        while (true) {
            File file = new File(logFilePath+currentLog+"["+count+"].log");
            if (!file.exists()) {
                return file;
            }
            count++;
        }
    }

    private static void generatePaddingArray() {

        paddingArray = new byte[LogType.values().length];

        LogType biggestLogType = LogType.values()[0];

        for (int i = 0; i < LogType.values().length; i++) { // Find biggest logType by length

            if (LogType.values()[i].toString().length() > biggestLogType.toString().length()) {

                biggestLogType = LogType.values()[i];

            }

        }

        biggestLogTypeLength = (byte) biggestLogType.toString().length();

        for (int i = 0; i < LogType.values().length; i++) { // Create paddingArray

            paddingArray[i] = (byte) (biggestLogTypeLength - LogType.values()[i].toString().length());

        }

    }

    private void generateIsLoggedArray() { // Establish isLoggedArray with the length of LogType enum and set all values to TRUE as default.
        isLoggedArray = new boolean[LogType.values().length];
        for (int i = 0; i < isLoggedArray.length; i++) {
            isLoggedArray[i] = true;
        }
    }

    public void changeLogTypeLogging(LogType type, boolean isLogged) {
        isLoggedArray[type.ordinal()] = isLogged;
    }

    public void changeLogPath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public static void deleteOldLogs(String path) {
        File[] oldLogs = new File(path+File.separatorChar).listFiles();
        for (int i = 0; i < oldLogs.length; i++) {
            if (oldLogs[i].getName().endsWith(".log")) {
                oldLogs[i].delete();
            }
        }
    }

    private static String getCurrentTime() {
        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");
    }

    private static String getFormattedLog(String log, LogType type) {
        int spacing = 1; // Spacing between date and LogType.

        if (!type.isTagShown && !type.includesTime) {
            return log + "\n"; // Return log without modifications (Except newline, of course).
        } else {
            if (type.isTagShown) {
                log = " ".repeat(paddingArray[type.ordinal()] + spacing) + "[" + type + "] : " + log;
            } else {
                log = " ".repeat(biggestLogTypeLength + spacing*2) + " : " + log;
            }

            if (type.includesTime) {
                log = "[" + getCurrentTime() + "]" + log;
            } else {
                log = " ".repeat(getCurrentTime().length() + spacing*2) + log;
            }
        }

        return log + "\n";
    }

    public void log(String log, LogType type) {
        // Format log.
        if (!isLoggedArray[type.ordinal()]) {return;} // Do not log if isLoggedArray says so.

        logBuffer.append(getFormattedLog(log, type));
        storedEntries++;
        if (storedEntries>=writeToFileInterval) {
            writeToFile();
        }

    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(logFile);
            writer.write(logBuffer.toString());
            writer.close();  
            storedEntries = 0;
        } catch (IOException e) {
            log("Error while trying to create log " + currentLog + ".", LogType.REQUIRED);
            e.printStackTrace();
        }
    }

    public void close() {
        log("\nEND OF LOG.", LogType.HEADER);
        writeToFile();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
    }
}