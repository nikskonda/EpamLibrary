package by.epam.java.training.web.command;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static CommandFactory instance = new CommandFactory();
    private static Lock lock = new ReentrantLock();


    private CommandManager commandManager = new CommandManager();


    private CommandFactory() {
    }

    public static Command getCommand(String command){
        return getInstance().commandManager.getCommand(command);
    }

    private static CommandFactory getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new CommandFactory();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("Command unlock error", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }
}
