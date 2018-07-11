package by.epam.java.training.web.command.impl.book;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.Page.READING_ROOM;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class ReadBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ReadBook.class);

    private static final String TEXT = "text";
    private static final String BOOK_ID = "book_id";
    private static final String LOCALE = "local";

    private static final String CURRENT_PAGE = "currentPage";

    private static final int INIT_NUMBER_OF_PAGE = 1;

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
            rememberLastAction(request);
            BookService service = ServiceFactory.getBookService();

            HttpSession session = request.getSession(true);
            if (session.getAttribute(LOCALE)==null){
                session.setAttribute(LOCALE, "en");
            }
            String locale = (String)session.getAttribute(LOCALE);

            Integer currentPage = getInt(request.getParameter(CURRENT_PAGE));
            if (currentPage==null){
                currentPage = INIT_NUMBER_OF_PAGE;
            }
            Integer bookId = Integer.parseInt(request.getParameter(BOOK_ID));

            String path = request.getServletContext().getRealPath("WEB-INF/classes/");
            request.setAttribute(BOOK_ID, bookId);
            request.setAttribute(CURRENT_PAGE, currentPage);
            request.setAttribute(TEXT, service.getTextOfBook(bookId, locale, path, currentPage));
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
