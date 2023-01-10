public class LoggerTester {
    public static void main(String[] args) {

        Logger logger = new Logger("Logs", null, true);

        logger.log("1", Logger.LogType.HEADER);
        logger.log("2", Logger.LogType.WARNING);
        logger.log("3", Logger.LogType.INFO);
        logger.log("4", Logger.LogType.DEBUG);
        logger.log("5", Logger.LogType.REQUIRED);

        logger.writeToFile();

        /*logger.changeLogTypeLogging(Logger.LogType.DEBUG, false);

        logger.log("6", Logger.LogType.HEADER);
        logger.log("7", Logger.LogType.WARNING);
        logger.log("8", Logger.LogType.INFO);
        logger.log("9", Logger.LogType.DEBUG);
        logger.log("10", Logger.LogType.REQUIRED);

        logger = null;
        System.gc();*/

    }
    
}
