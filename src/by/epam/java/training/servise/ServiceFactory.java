package by.epam.java.training.servise;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.servise.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceFactory {

    private static final Logger logger = Logger.getLogger(DAOFactory.class);

    private static ServiceFactory instance = new ServiceFactory();
    private static Lock lock = new ReentrantLock();


    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {}

    public UserService getUserService() {
        return userService;
    }


    public static ServiceFactory getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new ServiceFactory();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("=(", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }

}
