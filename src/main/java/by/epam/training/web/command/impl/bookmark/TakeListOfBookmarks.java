package by.epam.training.web.command.impl.bookmark;

import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.servise.BookmarkService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to get a list of bookmarks.
 *
 * @author  Nikita Shkonda
 */
public class TakeListOfBookmarks extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfBookmarks.class);

    /**
     * Getting all bookmarks.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);
            ActiveUser user = (ActiveUser)session.getAttribute(FieldNameConstant.USER);
            PageAttribute pa = new PageAttribute();
            pa.setLocale((String) session.getAttribute(FieldNameConstant.LOCALE));
            pa.setCountOnPage(getCount(request, FieldNameConstant.COUNT_BOOKMARKS_ON_PAGE, FieldNameConstant.INIT_COUNT_BOOKMARKS));
            pa.setNumberOfPage(getCurrentPage(request));

            session.setAttribute(FieldNameConstant.COUNT_BOOKMARKS_ON_PAGE, pa.getCountOnPage());
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, pa.getNumberOfPage());
            request.setAttribute(FieldNameConstant.TOTAL_PAGES, service.calcPagesCountBookmarks(user.getId(),
                    locale, pa.getCountOnPage()));
            request.setAttribute(FieldNameConstant.BOOKS, service.getBooksWithBookmark(user.getId(), pa));

            forward(request, response, PageConstant.LIST_OF_BOOKMARK);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
