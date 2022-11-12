public class LoggerTester {
    public static void main(String[] args) {

        Logger.initialize();

        Logger.log("Test", Logger.LogType.HEADER);
        Logger.log("Test", Logger.LogType.WARNING);
        Logger.log("Test", Logger.LogType.INFO);
        Logger.log("Test", Logger.LogType.DEBUG);
        Logger.log("Test", Logger.LogType.REQUIRED);

    }
    
}
