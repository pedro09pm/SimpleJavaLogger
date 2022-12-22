public class LoggerTester {
    public static void main(String[] args) {

        Logger.initialize();
        Logger.setHeaderText("HEADER TEXT");

        Logger.log("1", Logger.LogType.HEADER);
        Logger.log("2", Logger.LogType.WARNING);
        Logger.log("3", Logger.LogType.INFO);
        Logger.log("4", Logger.LogType.DEBUG);
        Logger.log("5", Logger.LogType.REQUIRED);

        Logger.writeToFile();

        Logger.changeLogTypeLogging(Logger.LogType.DEBUG, false);

        Logger.log("6", Logger.LogType.HEADER);
        Logger.log("7", Logger.LogType.WARNING);
        Logger.log("8", Logger.LogType.INFO);
        Logger.log("9", Logger.LogType.DEBUG);
        Logger.log("10", Logger.LogType.REQUIRED);

        Logger.writeToFile();

    }
    
}
