package by.epam.training.web.command.impl.bookmark;

import by.epam.training.model.book.Bookmark;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.servise.BookService;
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
 * Used to open the bookmark.
 *
 * @author  Nikita Shkonda
 */
public class GoToBookmark extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToBookmark.class);

    /**
     * Opened the bookmark.
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
            BookService bookService = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String path = request.getServletContext().getRealPath(FieldNameConstant.EMPTY_STRING);
            ActiveUser activeUser = (ActiveUser)session.getAttribute(FieldNameConstant.USER);

            Bookmark bookmark = new Bookmark();
            bookmark.setBookId(getInt(request.getParameter(FieldNameConstant.BOOK_ID)));
            bookmark.setUserId(activeUser.getId());
            bookmark.setLocale((String)session.getAttribute(FieldNameConstant.LOCALE));

            Integer page = service.getBookmark(bookmark);

            request.setAttribute(FieldNameConstant.BOOK_ID, bookmark.getBookId());
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, page);
            request.setAttribute(FieldNameConstant.TOTAL_PAGES,bookService.calcPagesCountText(bookmark.getBookId(), bookmark.getLocale(), path));
            request.setAttribute(FieldNameConstant.BOOK_TEXT, bookService.getTextOfBook(bookmark.getBookId(),
                    bookmark.getLocale(), path, page));

            forward(request, response, PageConstant.READING_ROOM);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
