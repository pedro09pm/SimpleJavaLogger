// Simple Java Logger by Pedro Marín Sanchis, lisenced under MIT.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;


public class Logger {

    // Log

    private StringBuffer logBuffer = new StringBuffer();

    // LogType

    private static byte[] paddingArray;
    private static byte biggestLogTypeLength = 0;
    
    private boolean[] isLoggedArray = {true, true, true, true, true,}; // Must be declared in the same order as enum LogType [HEADER, WARNING...].

    // Configuration.

    private String logFilePath = "Logs";
    private String currentLog = "DefaultLog.txt";

    private String logHeaderText = "Simple Java Logger\n\n" + "-".repeat(50); // Default Header Text.


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

    public Logger(String logFilePath, String logHeaderText, boolean deleteOldLogFiles) {

        this.logFilePath = logFilePath + "/"; //TODO: FIX THIS, NOT MULTIPLATFORM.
        
        if (logHeaderText != null) {
            this.logHeaderText = logHeaderText;
        } else {
            System.out.println("Null!");
        }

        if (deleteOldLogFiles) {deleteOldLogs();};

        generatePaddingArray();
        currentLog = "Log[" + getCurrentTime() + "].log";
        log(this.logHeaderText, LogType.HEADER);

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

    public void changeLogTypeLogging(LogType type, boolean isLogged) {

        isLoggedArray[type.ordinal()] = isLogged;

    }

    public void changeLogPath(String logFilePath) {

        this.logFilePath = logFilePath;

    }

    private void deleteOldLogs() {

        File[] oldLogs = new File(logFilePath).listFiles();

        for (int i = 0; i < oldLogs.length; i++) {
            if (oldLogs[i].getName().endsWith(".log")) {
                oldLogs[i].delete();
            }
        }

    }

    private static String getCurrentTime() {

        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

    }

    private static String formatLog(String log, LogType type) {

        int spacing = 1; // Spacing between date and LogType.

        if (!type.isTagShown && !type.includesTime) {

            return log; // Return log without modifications.

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

        log = formatLog(log, type);

        // Write log to file.

        logBuffer.append(log);

    }

    public void writeToFile() {

        File file = new File(logFilePath+currentLog);
        try {
    
            FileWriter writer = new FileWriter(file);
            writer.write(logBuffer.toString());
            writer.close();
            
        } catch (IOException e) {
            log("Error while trying to create log " + currentLog + ".", LogType.REQUIRED);
            e.printStackTrace();
        }

    }

    @Override
    protected void finalize() throws Throwable {
        writeToFile();
    }
}