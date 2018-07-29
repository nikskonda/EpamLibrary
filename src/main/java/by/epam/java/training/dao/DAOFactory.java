package by.epam.java.training.dao;

import by.epam.java.training.dao.impl.*;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DAOFactory used for getting {@link DAOFactory}s to process requests.
 *
 * @author Nikita Shkonda
 */
public class DAOFactory {
    private static final Logger logger = Logger.getLogger(DAOFactory.class);

    /**
     * DaoFactory instance.
     */
    private static DAOFactory instance = new DAOFactory();
    /**
     * {@link Lock} field.
     */
    private static Lock lock = new ReentrantLock();

    /**
     * {@link ConnectionPool} field.
     */
    private static final ConnectionPool connectionPool = new ConnectionPool();

    /**
     * {@link AdministratorDAO} field.
     */
    private final AdministratorDAO administratorDAO = new AdministratorDAOImpl();

    /**
     * {@link UserSearchDAO} field.
     */
    private final UserSearchDAO userSearchDAO = new UserSearchDAOImpl();

    /**
     * {@link ModeratorDAO} field.
     */
    private final ModeratorDAO moderatorDAO = new ModeratorDAOImpl();

    /**
     * {@link UserDAO} field.
     */
    private final UserDAO userDAO = new UserDAOImpl();

    /**
     * {@link BookDAO} field.
     */
    private final BookDAO bookDAO = new BookDAOImpl();

    /**
     * {@link BookSearchDAO} field.
     */
    private final BookSearchDAO bookSearchDAO = new BookSearchDAOImpl();

    /**
     * {@link BookmarkDAO} field.
     */
    private final BookmarkDAO bookmarkDAO = new BookmarkDAOImpl();

    /**
     * {@link NewsDAO} field.
     */
    private final NewsDAO newsDAO = new NewsDAOImpl();


    private DAOFactory() {}

    /**
     * Return {@link AdministratorDAO} implementation.
     *
     * @return {@link AdministratorDAO} implementation.
     */
    public static AdministratorDAO getAdministratorDAO(){ return getInstance().administratorDAO;}

    /**
     * Return {@link UserSearchDAO} implementation.
     *
     * @return {@link UserSearchDAO} implementation.
     */
    public static UserSearchDAO getUserSearchDAO(){ return getInstance().userSearchDAO;}

    /**
     * Return {@link ModeratorDAO} implementation.
     *
     * @return {@link ModeratorDAO} implementation.
     */
    public static ModeratorDAO getModeratorDAO() {return getInstance().moderatorDAO;}

    /**
     * Return {@link UserDAO} implementation.
     *
     * @return {@link UserDAO} implementation.
     */
    public static UserDAO getUserDAO() {
        return getInstance().userDAO;
    }

    /**
     * Return {@link BookDAO} implementation.
     *
     * @return {@link BookDAO} implementation.
     */
    public static BookDAO getBookDAO(){
        return getInstance().bookDAO;
    }

    /**
     * Return {@link BookSearchDAO} implementation.
     *
     * @return {@link BookSearchDAO} implementation.
     */
    public static BookSearchDAO getBookSearchDAO(){
        return getInstance().bookSearchDAO;
    }

    /**
     * Return {@link BookmarkDAO} implementation.
     *
     * @return {@link BookmarkDAO} implementation.
     */
    public static BookmarkDAO getBookmarkDAO(){
        return getInstance().bookmarkDAO;
    }

    /**
     * Return {@link NewsDAO} implementation.
     *
     * @return {@link NewsDAO} implementation.
     */
    public static NewsDAO getNewsDAO(){
        return getInstance().newsDAO;
    }

    /**
     * Return {@link ConnectionPool} implementation.
     *
     * @return {@link ConnectionPool} implementation.
     */
    public static ConnectionPool getConnectionPool(){
        return getInstance().connectionPool;
    }

    /**
     * Return DaoFactory instance.
     *
     * @return DaoFactory instance.
     */
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
