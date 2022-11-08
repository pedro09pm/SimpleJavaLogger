public class LoggerTester {

    public static void main(String[] args) {

        Logger.log("El wiwi", Logger.LogType.HEADER);
        Logger.log("El wiwi", Logger.LogType.WARNING);
        Logger.log("El wiwi", Logger.LogType.INFO);
        Logger.log("El wiwi", Logger.LogType.DEBUG);
        Logger.log("El wiwi", Logger.LogType.REQUIRED);

    }
    
}
