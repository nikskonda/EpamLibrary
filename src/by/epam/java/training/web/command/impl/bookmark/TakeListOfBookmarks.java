package by.epam.java.training.web.command.impl.bookmark;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstants.LIST_OF_BOOKMARK;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class TakeListOfBookmarks extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfBookmarks.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);
            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            PageAttributes pa = new PageAttributes();
            pa.setLocale((String) session.getAttribute(LOCALE));
            pa.setCountOnPage(getCount(request, COUNT_BOOKMARKS_ON_PAGE, INIT_COUNT_BOOKMARKS));
            pa.setNumberOfPage(getCurrentPage(request));

            session.setAttribute(COUNT_BOOKMARKS_ON_PAGE, pa.getCountOnPage());
            request.setAttribute(NUMBER_OF_PAGE, pa.getNumberOfPage());
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountBookmarks(user.getId(),
                    locale, pa.getCountOnPage()));
            request.setAttribute(BOOKS, service.getBooksWithBookmark(user.getId(), pa));

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
