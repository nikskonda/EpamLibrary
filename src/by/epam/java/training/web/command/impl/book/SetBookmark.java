package by.epam.java.training.web.command.impl.book;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.CommandName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.SIGN_IN;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class SetBookmark extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SetBookmark.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser)session.getAttribute(USER);
            if (activeUser==null){
                redirect(response, SIGN_IN);
                return;
            }
            Bookmark bookmark = new Bookmark();
            bookmark.setBookId(getInt(request.getParameter(BOOK_ID)));
            bookmark.setUserId(activeUser.getId());
            bookmark.setPageNumber(getInt(request.getParameter(NUMBER_OF_PAGE)));
            bookmark.setLocale((String)session.getAttribute(LOCALE));

            request.setAttribute(SET_BOOKMARK_RESULT, service.setBookmark(bookmark));

            CommandFactory.getCommand(CommandName.READ_BOOK).execute(request, response);
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
