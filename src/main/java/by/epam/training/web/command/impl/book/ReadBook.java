package by.epam.training.web.command.impl.book;

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
 * Used to get the text of a book.
 *
 * @author  Nikita Shkonda
 */
public class ReadBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ReadBook.class);

    /**
     * Getting the text of a book.
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
            Integer currentPage = getCurrentPage(request);
            Integer bookId = Integer.parseInt(request.getParameter(FieldNameConstant.BOOK_ID));
            String path = request.getServletContext().getRealPath(FieldNameConstant.EMPTY_STRING);

            request.setAttribute(FieldNameConstant.BOOK_ID, bookId);
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, currentPage);
            request.setAttribute(FieldNameConstant.TOTAL_PAGES, service.calcPagesCountText(bookId,locale, path));
            request.setAttribute(FieldNameConstant.BOOK_TEXT, service.getTextOfBook(bookId, locale, path, currentPage));

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
