package by.epam.training.web.command.impl.bookmark;

import by.epam.training.model.book.Bookmark;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.servise.BookmarkService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.command.CommandConstants;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to set the bookmark.
 *
 * @author  Nikita Shkonda
 */
public class SetBookmark extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SetBookmark.class);

    /**
     * Setted the bookmark.
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
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser)session.getAttribute(FieldNameConstant.USER);

            Bookmark bookmark = new Bookmark();
            bookmark.setBookId(getInt(request.getParameter(FieldNameConstant.BOOK_ID)));
            bookmark.setUserId(activeUser.getId());
            bookmark.setPageNumber(getInt(request.getParameter(FieldNameConstant.NUMBER_OF_PAGE)));
            bookmark.setLocale((String)session.getAttribute(FieldNameConstant.LOCALE));

            request.setAttribute(FieldNameConstant.SET_BOOKMARK_RESULT, service.setBookmark(bookmark));

            CommandFactory.getCommand(CommandConstants.READ_BOOK).execute(request, response);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
