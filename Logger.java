// Simple Java Logger

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime; 
import java.time.LocalDate;


public class Logger {

    private static byte[] paddingArray;
    private static byte biggestLogTypeLength = 0;

    // Configuration

    final private static boolean deleteOldLogFiles = true;

    final private static boolean logWarning = true;
    final private static boolean logInfo = true;
    final private static boolean logDebug = true;

    final private static String logFilePath = "logs/";

    private static String logHeaderText = "Simple Java Logger\n\n";


    public static enum LogType {

        // LogType(isLogged, isShown, includesTime)

        HEADER(true, false, false),
        WARNING(logWarning, true, true),
        INFO(logInfo, true, true),
        DEBUG(logDebug, true, true),
        REQUIRED(true, true, true);

        private final boolean isLogged;
        private final boolean isTagShown;
        private final boolean includesTime;

        private LogType (boolean isLogged, boolean isTagShown, boolean includesTime) {

            this.isLogged = isLogged;
            this.isTagShown = isTagShown;
            this.includesTime = includesTime;

        }

    }


    public static void initialize() {

        // Generate padding array for text formatting.

        generatePaddingArray();

        // Delete old log files, if option is set so.

        if (deleteOldLogFiles) {

            

        }

        // Check if default log file exists. If not, create one.

        // Create new log file.

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


    private static String getCurrentTime() {

        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

    }


    private static void createLogFile(String logName) {



    }
    

    private static void deleteLogFile(String logName) {



    }


    private static String formatLog(String log, LogType type) {

        int spacing = 1; // Spacing between date and LogType

        if (type == LogType.HEADER) {

            return log; // Return log without modifications.

        } else if (type.isLogged) {

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


    public static void log(String log, LogType type) {

        // Format log.

        log = formatLog(log, type);

        // Write log to file.

        System.out.println(log);

    }

}