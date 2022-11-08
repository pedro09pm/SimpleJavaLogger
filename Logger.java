// Simple Java Logger

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime; 
import java.time.LocalDate;


public class Logger {


    // Configuration

    final private static boolean deleteOldLogFiles = true;

    final private static boolean logWarning = true;
    final private static boolean logInfo = true;
    final private static boolean logDebug = true;

    final private static String logFilePath = "logs/";
    final private static String defaultLogFile = "DefaultLog.txt";

    final private static String logHeaderText = "";

    private static String currentLogFile = defaultLogFile; // Our 

    public static enum LogType {

        // LogType(isShown, includesTime, padding)

        // For formatting reasons if isTagShown == false padding is irrelevant.

        HEADER(true, false, false, 0),
        WARNING(logWarning, true, true, 1),
        INFO(logInfo, true, true, 4),
        DEBUG(logDebug, true, true, 3),
        REQUIRED(true, true, true, 0); // [Biggest type, length is 8]

        private final boolean isLogged;
        private final boolean isTagShown;
        private final boolean includesTime;
        private final int padding;

        private LogType (boolean isLogged, boolean isTagShown, boolean includesTime, int padding) {

            this.isLogged = isLogged;
            this.isTagShown = isTagShown;
            this.includesTime = includesTime;
            this.padding = padding;

        }

    }


    private static String getCurrentTime() {

        return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

    }


    public static void initialize() {

        // Check if default log file exists.

        // Create new log file.

    }


    private static void createLogFile(String logName) {



    }

    private static void deleteLogFile(String logName) {



    }


    private static String formatLog(String log, LogType type) {


        if (type == LogType.HEADER) {

            return log; // Return log without modifications.

        } else if (type.isLogged) {

            if (type.isTagShown) {

                log = " ".repeat(type.padding + 1) + "[" + type + "] : " + log;

            } else {

                log = " ".repeat(11) + " : " + log;

            }

            if (type.includesTime) {

                log = "[" + getCurrentTime() + "]" + log;

            } else {

                log = " ".repeat(21) + log;

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