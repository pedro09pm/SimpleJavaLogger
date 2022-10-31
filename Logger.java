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

        WARNING,
        INFO,
        DEBUG,
        REQUIRED, // Required logs do not show their tag.

    }


    private static String getCurrentTime(boolean includeDate) {

        if (includeDate) {
        
            return LocalDate.now().toString() + "_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");
        
        } else {

            return LocalTime.now().toString().substring(0, 8).replaceAll(":", "-");

        }

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

    public static void log(String log, LogType type, boolean includeTime, boolean append) {



    }


    public static void main(String[] args) {

        System.out.println(getCurrentTime(true));
        System.out.println(getCurrentTime(false));

    }

}