package by.epam.java.training.web.filter.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static by.epam.java.training.web.filter.util.Path.*;

public class PathManager {

    private static final Logger logger = Logger.getLogger(PathManager.class);

    private static PathManager instance = new PathManager();
    private static Lock lock = new ReentrantLock();

    private final Map<String, String> paths;

    public PathManager() {
        this.paths = new HashMap<>();
        this.paths.put(NEWS_THUMBS_PARAMETER, NEWS_THUMBS_PATH);
        this.paths.put(NEWS_PHOTO_PARAMETER, NEWS_PHOTO_PATH);

        this.paths.put(BOOK_TEXT_PARAMETER, BOOK_TEXT_PATH);
        this.paths.put(BOOK_RU_TEXT_PARAMETER, BOOK_RU_TEXT_PATH);
        this.paths.put(BOOK_PDF_PARAMETER, BOOK_PDF_PATH);
        this.paths.put(BOOK_RU_PDF_PARAMETER, BOOK_RU_PDF_PATH);
        this.paths.put(BOOK_COVER_PARAMETER, BOOK_COVER_PATH);
    }

    public static String getPath(String pathFor){
        return getInstance().paths.get(pathFor);
    }

    private static PathManager getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new PathManager();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("=(", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }
}
