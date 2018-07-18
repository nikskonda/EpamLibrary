package by.epam.java.training.web.command.impl.bookmark;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.READ_BOOK;
import static by.epam.java.training.web.command.Page.LIST_OF_BOOKMARK;
import static by.epam.java.training.web.command.Page.SIGN_IN;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class TakeListOfBookmarks extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfBookmarks.class);

    private static final int INIT_COUNT_BOOKMARKS = 8;
    private static final int INIT_NUMBER_OF_PAGE = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);

            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            String lang = (String) session.getAttribute(LOCALE);

            Integer countBookmarks = getCount(request, COUNT_BOOKMARKS_ON_PAGE, INIT_COUNT_BOOKMARKS);
            Integer currentPage = getCurrentPage(request, NUMBER_OF_PAGE, INIT_NUMBER_OF_PAGE);

            session.setAttribute(COUNT_BOOKMARKS_ON_PAGE, countBookmarks);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, 10);

            request.setAttribute(BOOKS, service.getListOfBooksWithBookmark(user.getId(), lang, countBookmarks, currentPage));

            forward(request, response, LIST_OF_BOOKMARK);
        } catch (DAOException ex){
            logger.warn("Problem with database", ex);
            request.setAttribute(ERROR_DATABASE, true);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }

    }
}
