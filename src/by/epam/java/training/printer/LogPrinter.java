package by.epam.java.training.printer;

import org.apache.log4j.Logger;

public class LogPrinter {
    private LogPrinter(){}

    public static void printLogWarn(String msg, Logger logger){
        logger.warn(msg);
    }

    public static void printLogError(String msg, Logger logger){
        logger.error(msg);
    }

    public static void printLogDebug(String msg, Logger logger){
        logger.debug(msg);
    }

    public static void printLogFatal(String msg, Logger logger){
        logger.fatal(msg);
    }

    public static void printLogTrace(String msg, Logger logger){
        logger.trace(msg);
    }

    public static void printLogInfo(String msg, Logger logger){
        logger.info(msg);
    }

}
