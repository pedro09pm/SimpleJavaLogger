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
    final private static boolean logDebug = false;

    final private static String logPath = "logs/";
    final private static String defaultLogFile = "DefaultLog.txt";


    public static enum LogType {

        // LogType(isShown, includesTime, padding)

        WARNING(true, true, 0),
        INFO(true, true, 0),
        DEBUG(true, true, 0),
        REQUIRED(false, false, 0); // Required log's tags and time are not shown.

        private final boolean isTagShown;
        private final boolean includesTime;
        private final int padding;

        private LogType (boolean isShown, boolean includesTime, int padding) {
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



    }


    public static void main(String[] args) {

        System.out.println(getCurrentTime());

    }

}