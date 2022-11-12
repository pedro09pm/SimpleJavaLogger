public class LoggerTester {
    public static void main(String[] args) {

        Logger.initialize();
        Logger.setHeaderText("HEADER TEXT");

        Logger.log("Test", Logger.LogType.HEADER);
        Logger.log("Test", Logger.LogType.WARNING);
        Logger.log("Test", Logger.LogType.INFO);
        Logger.log("Test", Logger.LogType.DEBUG);
        Logger.log("Test", Logger.LogType.REQUIRED);

        Logger.changeLogTypeLogging(Logger.LogType.DEBUG, false);

        Logger.log("Test", Logger.LogType.HEADER);
        Logger.log("Test", Logger.LogType.WARNING);
        Logger.log("Test", Logger.LogType.INFO);
        Logger.log("Test", Logger.LogType.DEBUG);
        Logger.log("Test", Logger.LogType.REQUIRED);

    }
    
}
