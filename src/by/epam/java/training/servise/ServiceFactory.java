package by.epam.java.training.servise;

import by.epam.java.training.servise.impl.*;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceFactory {

    private static final Logger logger = Logger.getLogger(ServiceFactory.class);


    private static ServiceFactory instance = new ServiceFactory();
    private static Lock lock = new ReentrantLock();

    private final AdministratorService administratorService = new AdministratorServiceImpl();
    private final ModeratorService moderatorService = new ModeratorServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private final BookService bookService = new BookServiceImpl();
    private final BookmarkService bookmarkService = new BookmarkServiceImpl();
    private final BookSearchService bookSearchService = new BookSearchServiceImpl();

    private final NewsService newsService = new NewsServiceImpl();


    private ServiceFactory() {}

    public static AdministratorService getAdministratorService(){
        return getInstance().administratorService;
    }
    public static ModeratorService getModeratorService(){
        return getInstance().moderatorService;
    }
    public static UserService getUserService() {
        return getInstance().userService;
    }

    public static BookService getBookService() {
        return getInstance().bookService;
    }
    public static BookSearchService getBookSearchService() {
        return getInstance().bookSearchService;
    }
    public static BookmarkService getBookmarkService() {
        return getInstance().bookmarkService;
    }

    public static NewsService getNewsService(){
        return getInstance().newsService;
    }


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
