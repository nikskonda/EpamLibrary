package by.epam.training.web.filter.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class for getting paths for different types of files.
 *
 * @author Nikita Shkonda
 *
 */
public class PathManager {

    private static final Logger logger = Logger.getLogger(PathManager.class);

    private static PathManager instance = new PathManager();
    private static Lock lock = new ReentrantLock();

    private final Map<String, String> paths;

    private PathManager() {
        this.paths = new HashMap<>();
        this.paths.put(PathConstant.NEWS_THUMBS_PARAMETER, PathConstant.NEWS_THUMBS_PATH);
        this.paths.put(PathConstant.NEWS_PHOTO_PARAMETER, PathConstant.NEWS_PHOTO_PATH);

        this.paths.put(PathConstant.BOOK_TEXT_PARAMETER, PathConstant.BOOK_TEXT_PATH);
        this.paths.put(PathConstant.BOOK_RU_TEXT_PARAMETER, PathConstant.BOOK_RU_TEXT_PATH);
        this.paths.put(PathConstant.BOOK_PDF_PARAMETER, PathConstant.BOOK_PDF_PATH);
        this.paths.put(PathConstant.BOOK_RU_PDF_PARAMETER, PathConstant.BOOK_RU_PDF_PATH);
        this.paths.put(PathConstant.BOOK_COVER_PARAMETER, PathConstant.BOOK_COVER_PATH);
    }

    /**
     * The method returns path for file.
     *
     * @param pathFor  type of file.
     *
     */
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
            logger.warn("Failed get instance.", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }
}
