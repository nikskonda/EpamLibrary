package by.epam.training.web.command.impl.moder.edit;

import by.epam.training.model.book.Book;
import by.epam.training.servise.BookService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.PageConstant;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to go to the book constructor.
 *
 * @author  Nikita Shkonda
 */
public class GoToEditBookForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToEditBookForm.class);

    /**
     * Moves to editing a book.
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
            BookService service = ServiceFactory.getBookService();
            Integer bookId = Integer.parseInt(request.getParameter(FieldNameConstant.BOOK_ID));
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);

            Book defBook = service.getBook(bookId, FieldNameConstant.ENGLISH);
            Book ruBook = service.getBook(bookId, FieldNameConstant.RUSSIAN);

            request.setAttribute(FieldNameConstant.BOOK, defBook);
            request.setAttribute(FieldNameConstant.BOOK_RU, ruBook);
            request.setAttribute(FieldNameConstant.GENRES, service.getGenres(locale));

            forward(request, response, PageConstant.BOOK_EDIT);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

}
