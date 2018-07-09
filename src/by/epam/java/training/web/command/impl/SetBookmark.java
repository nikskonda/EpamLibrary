package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.Commandos;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages.SIGN_IN;
import static by.epam.java.training.web.command.Pages2.READING_ROOM;

public class SetBookmark extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SetBookmark.class);

    private static final String USER = "user";
    private static final String BOOK_ID = "book_id";
    private static final String LOCALE = "local";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String SET_BOOKMARK_RESULT = "set_bookmark_result";


    private Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("", ex);
        }
        return result;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            BookmarkService service = ServiceFactory.getBookmarkService();
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser)session.getAttribute(USER);
            if (activeUser==null){
                redirect(response, SIGN_IN);
            }
            Bookmark bookmark = new Bookmark();
            bookmark.setBookId(getInt(request.getParameter(BOOK_ID)));
            bookmark.setUserId(activeUser.getId());
            bookmark.setPageNumber(getInt(request.getParameter(CURRENT_PAGE)));
            bookmark.setLocale((String)session.getAttribute(LOCALE));

            request.setAttribute(SET_BOOKMARK_RESULT, service.setBookmark(bookmark));

            CommandFactory.getCommand(Commandos.READ_BOOK).execute(request, response);
        } catch (Exception ex) {

        }

    }
}
