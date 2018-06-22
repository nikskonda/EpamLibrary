package by.epam.java.training.dao;

import by.epam.java.training.dao.impl.BookDAOImpl;
import by.epam.java.training.dao.impl.NewsDAOImpl;
import by.epam.java.training.dao.impl.UserDAOImpl;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DAOFactory {
    private static final Logger logger = Logger.getLogger(DAOFactory.class);

    private static DAOFactory instance = new DAOFactory();
    private static Lock lock = new ReentrantLock();

    private final ConnectionPool connectionPool = new ConnectionPool();

    private final UserDAO userDAO = new UserDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();
    private final NewsDAO newsDAO = new NewsDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return this.userDAO;
    }
    public BookDAO getBookDAO(){
        return this.bookDAO;
    }
    public NewsDAO getNewsDAO(){
        return this.newsDAO;
    }

    public ConnectionPool getConnectionPool(){
        return this.connectionPool;
    }

    public static DAOFactory getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new DAOFactory();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("=(", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }

}
