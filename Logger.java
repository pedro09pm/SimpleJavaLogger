// Simple Java Logger by Pedro Marín Sanchis, lisenced under MIT.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;


public class Logger {

    // List of paths with an active logger.

    ArrayList<String> activeLogPaths = new ArrayList<>();

    // Log

    private static StringBuffer logBuffer = new StringBuffer();

    // LogType

    private static boolean loggerInitialized = false;

    private static byte[] paddingArray;
    private static byte biggestLogTypeLength = 0;
    
    private static boolean[] isLoggedArray = {true, true, true, true, true,}; // Must be declared in the same order as enum LogType [HEADER, WARNING...].

    // Configuration.

    private static boolean deleteOldLogFiles = true;

    private static String logFilePath = "Logs/";
    private static String currentLog = "DefaultLog.txt";

    private static String logHeaderText = "Simple Java Logger\n\n" + "-".repeat(50); // Default Header Text.


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


    public static void initialize() {

        if (!loggerInitialized) {

            // Generate padding array for text formatting.

            generatePaddingArray();

            // Delete old log files, if option is set so.

            if (deleteOldLogFiles) {

                //deleteOldLogs();

            }

            // Set current log name.

            currentLog = "Log[" + getCurrentTime() + "].txt";

            // Log header text

            log(logHeaderText, LogType.HEADER);

            // Set initialized to true.

            loggerInitialized = true;

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

    public static void setHeaderText(String header) {

        logHeaderText = header;

    }

    public static void changeLogTypeLogging(LogType type, boolean isLogged) {

        isLoggedArray[type.ordinal()] = isLogged;

    }

    public static void changeLogPath(String path) {

        if (!loggerInitialized) {logFilePath = path;}

    }

    public static void deleteOldLogsAtInit(boolean choice) {

        deleteOldLogFiles = choice;

    }

    private static void deleteOldLogs() {

        File[] oldLogs = new File(logFilePath).listFiles();

        for (int i = 0; i < oldLogs.length; i++) {
            oldLogs[i].delete();
        }

    }

    private static String getCurrentTime() {

        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

    }

    private static void createLogFile(File file) {

        try {

            FileWriter writer = new FileWriter(file);
            writer.write(logBuffer.toString());
            writer.close();
            
        } catch (IOException e) {
            log("Error while trying to create log " + currentLog + ".", LogType.REQUIRED);
            e.printStackTrace();
        }

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

    public static void log(String log, LogType type) {

        // Format log.

        if (!isLoggedArray[type.ordinal()]) {return;}

        log = formatLog(log, type);

        // Write log to file.

        logBuffer.append(log);

    }

    public static void writeToFile() {

        createLogFile(new File(logFilePath+currentLog));

    }

    @Override
    protected void finalize() throws Throwable {
        writeToFile();
        super.finalize();
    }
}