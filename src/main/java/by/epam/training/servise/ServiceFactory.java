package by.epam.training.servise;

import by.epam.training.servise.impl.*;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceFactory {

    private static final Logger logger = Logger.getLogger(ServiceFactory.class);

    /**
     * ServiceFactory instance.
     */
    private static ServiceFactory instance = new ServiceFactory();
    /**
     * {@link Lock} field.
     */
    private static Lock lock = new ReentrantLock();

    /**
     * {@link AdministratorService} field.
     */
    private final AdministratorService administratorService = new AdministratorServiceImpl();

    /**
     * {@link UserSearchService} field.
     */
    private final UserSearchService userSearchService = new UserSearchServiceImpl();

    /**
     * {@link ModeratorService} field.
     */
    private final ModeratorService moderatorService = new ModeratorServiceImpl();

    /**
     * {@link UserService} field.
     */
    private final UserService userService = new UserServiceImpl();

    /**
     * {@link BookService} field.
     */
    private final BookService bookService = new BookServiceImpl();

    /**
     * {@link BookmarkService} field.
     */
    private final BookmarkService bookmarkService = new BookmarkServiceImpl();

    /**
     * {@link BookSearchService} field.
     */
    private final BookSearchService bookSearchService = new BookSearchServiceImpl();

    /**
     * {@link NewsService} field.
     */
    private final NewsService newsService = new NewsServiceImpl();


    private ServiceFactory() {}

    /**
     * Return {@link AdministratorService} implementation.
     *
     * @return {@link AdministratorService} implementation.
     */
    public static AdministratorService getAdministratorService(){
        return getInstance().administratorService;
    }

    /**
     * Return {@link UserSearchService} implementation.
     *
     * @return {@link UserSearchService} implementation.
     */
    public static UserSearchService getUserSearchService(){
        return getInstance().userSearchService;
    }

    /**
     * Return {@link ModeratorService} implementation.
     *
     * @return {@link ModeratorService} implementation.
     */
    public static ModeratorService getModeratorService(){
        return getInstance().moderatorService;
    }

    /**
     * Return {@link UserService} implementation.
     *
     * @return {@link UserService} implementation.
     */
    public static UserService getUserService() {
        return getInstance().userService;
    }

    /**
     * Return {@link BookService} implementation.
     *
     * @return {@link BookService} implementation.
     */
    public static BookService getBookService() {
        return getInstance().bookService;
    }

    /**
     * Return {@link BookSearchService} implementation.
     *
     * @return {@link BookSearchService} implementation.
     */
    public static BookSearchService getBookSearchService() {
        return getInstance().bookSearchService;
    }

    /**
     * Return {@link BookmarkService} implementation.
     *
     * @return {@link BookmarkService} implementation.
     */
    public static BookmarkService getBookmarkService() {
        return getInstance().bookmarkService;
    }

    /**
     * Return {@link NewsService} implementation.
     *
     * @return {@link NewsService} implementation.
     */
    public static NewsService getNewsService(){
        return getInstance().newsService;
    }

    /**
     * Return ServiceFactory instance.
     *
     * @return ServiceFactory instance.
     */
    private static ServiceFactory getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new ServiceFactory();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("Service unlock error", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }

}
