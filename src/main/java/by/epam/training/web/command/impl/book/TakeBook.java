package by.epam.training.web.command.impl.book;

import by.epam.training.model.book.Book;
import by.epam.training.servise.BookService;
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
 * Used to get a information about book.
 *
 * @author  Nikita Shkonda
 */
public class TakeBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeBook.class);

    /**
     * Getting information about book.
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
            BookService service = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);
            Integer bookId = getInt(request.getParameter(FieldNameConstant.BOOK_ID));

            Book book = service.getBook(bookId, locale);

            request.setAttribute(FieldNameConstant.BOOK_DATA, book);
            request.setAttribute(FieldNameConstant.BOOK_DESCRIPTION, book.getDescription());

            forward(request, response, PageConstant.BOOK);

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
