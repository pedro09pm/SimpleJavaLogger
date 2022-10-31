// Simple Java Logger

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime; 
import java.time.LocalDate;


public class Logger {


    // Configuration

    final private static boolean deleteOldLogs = true;

    final private static boolean logWarning = true;
    final private static boolean logInfo = true;
    final private static boolean logDebug = true;

    final private static String logPath = "logs/";
    final private static String defaultLogFile = "DefaultLog.txt";

    private static String currentLog = defaultLogFile;


    public static enum LogType {

        // LogType(isShown, includesTime, padding)

        // For formatting reasons if isTagShown == false -> padding = 0

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


    private static void createLog(String logName) {



    }

    private static void deleteLog(String logName) {



    }


    private static void writeLogHeader() {

        

    }

    public static void log(String log, LogType type) {

        // Format log.

        if (type == LogType.HEADER) {

            // Write log without modifications.

        }

        if (type.isLogged && type != LogType.HEADER) {

            if (type.isTagShown) {

                log = " ".repeat(type.padding + 1) + "[" + type + "] : " + log;

            } else {

                log = " ".repeat(10 + type.padding + 1) + " : " + log;

            }

            if (type.includesTime) {

                log = "[" + getCurrentTime() + "]" + log;

            } else {

                log = " ".repeat(21) + log;

            }

        }

        // Write log to file.

        System.out.println(log);

    }


    public static void main(String[] args) {

        log("El wiwi", LogType.HEADER);
        log("El wiwi", LogType.WARNING);
        log("El wiwi", LogType.INFO);
        log("El wiwi", LogType.DEBUG);
        log("El wiwi", LogType.REQUIRED);

    }

}