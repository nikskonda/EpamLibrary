package by.epam.training.web.command;

import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CommandFactory used for getting {@link Command}s to process requests.
 *
 * @author Nikita Shkonda
 */
public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    /**
     * CommandFactory instance.
     */
    private static CommandFactory instance = new CommandFactory();
    private static Lock lock = new ReentrantLock();

    /**
     * CommandManager instance.
     */
    private CommandManager commandManager = new CommandManager();


    private CommandFactory() {
    }

    /**
     * Return {@link Command} by its corresponding command name.
     *
     * @param command command name.
     * @return {@link Command}
     */
    public static Command getCommand(String command){
        return getInstance().commandManager.getCommand(command);
    }

    /**
     * Return CommandFactory instance.
     *
     * @return CommandFactory instance.
     */
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
