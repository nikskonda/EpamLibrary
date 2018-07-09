package by.epam.java.training.dao;

import by.epam.java.training.dao.impl.*;
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
    private final BookmarkDAO bookmarkDAO = new BookmarkDAOImpl();
    private final BookSearchDAO bookSearchDAO = new BookSearchDAOImpl();
    private final NewsDAO newsDAO = new NewsDAOImpl();

    private DAOFactory() {}

    public static UserDAO getUserDAO() {
        return getInstance().userDAO;
    }
    public static BookDAO getBookDAO(){
        return getInstance().bookDAO;
    }
    public static BookmarkDAO getBookmarkDAO(){
        return getInstance().bookmarkDAO;
    }
    public static NewsDAO getNewsDAO(){
        return getInstance().newsDAO;
    }
    public static BookSearchDAO getBookSearchDAO(){
        return getInstance().bookSearchDAO;
    }

    public static ConnectionPool getConnectionPool(){
        return getInstance().connectionPool;
    }

    private static DAOFactory getInstance() {
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
