// Simple Java Logger by Pedro Marín Sanchis, lisenced under MIT.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime; 
import java.time.LocalDate;


public class Logger {

    private static boolean loggerInitialized = false;

    private static byte[] paddingArray;
    private static byte biggestLogTypeLength = 0;

    private static boolean[] isLoggedArray = {true, true, true, true, true,}; // Must be declared in the same order as enum LogType [HEADER, WARNING...].

    // Configuration.

    private static boolean deleteOldLogFiles = true;

    private static String logFilePath = "logs/";
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

                deleteOldLogs();

            }

            // Create new log file.

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

        // Find all logs to be deleted and use deleteLogFile() method.

    }

    private static String getCurrentTime() {

        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

    }


    private static void createLogFile(String logName) {



    }
    

    private static void deleteLogFile(String logName) {



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

        return log;

    }


    public static boolean log(String log, LogType type) { // Returns true if write to log is successful.

        // Format log.

        if (!isLoggedArray[type.ordinal()]) {return false;}

        log = formatLog(log, type);

        // Write log to file.

        System.out.println(log);

        return true;

    }

}