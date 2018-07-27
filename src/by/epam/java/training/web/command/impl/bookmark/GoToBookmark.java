package by.epam.java.training.web.command.impl.bookmark;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import static by.epam.java.training.web.command.util.PageConstant.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class GoToBookmark extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToBookmark.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookmarkService service = ServiceFactory.getBookmarkService();
            BookService bookService = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String path = request.getServletContext().getRealPath(EMPTY_STRING);
            ActiveUser activeUser = (ActiveUser)session.getAttribute(USER);

            Bookmark bookmark = new Bookmark();
            bookmark.setBookId(getInt(request.getParameter(BOOK_ID)));
            bookmark.setUserId(activeUser.getId());
            bookmark.setLocale((String)session.getAttribute(LOCALE));

            Integer page = service.getBookmark(bookmark);

            request.setAttribute(BOOK_ID, bookmark.getBookId());
            request.setAttribute(NUMBER_OF_PAGE, page);
            request.setAttribute(TOTAL_PAGES,bookService.calcPagesCountText(bookmark.getBookId(), bookmark.getLocale(), path));
            request.setAttribute(BOOK_TEXT, bookService.getTextOfBook(bookmark.getBookId(),
                    bookmark.getLocale(), path, page));

            forward(request, response, READING_ROOM);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
