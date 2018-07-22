package by.epam.java.training.web.command.impl.book;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.READING_ROOM;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class ReadBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ReadBook.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookService service = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);
            Integer currentPage = getCurrentPage(request);
            Integer bookId = Integer.parseInt(request.getParameter(BOOK_ID));
            String path = request.getServletContext().getRealPath(EMPTY_STRING);

            request.setAttribute(BOOK_ID, bookId);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(BOOK_TEXT, service.getTextOfBook(bookId, locale, path, currentPage));

            forward(request, response, READING_ROOM);

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
