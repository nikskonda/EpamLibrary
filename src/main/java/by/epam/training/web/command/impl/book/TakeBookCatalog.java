package by.epam.training.web.command.impl.book;

import by.epam.training.model.PageAttribute;
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
 * Used to get a list of books.
 *
 * @author  Nikita Shkonda
 */
public class TakeBookCatalog extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeBookCatalog.class);

    /**
     * Getting all books.
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
            Integer countBooks = getCount(request, FieldNameConstant.COUNT_BOOKS_ON_PAGE, FieldNameConstant.INIT_COUNT_BOOKS);
            Integer currentPage = getCurrentPage(request);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);

            PageAttribute pageData = new PageAttribute();
            pageData.setCountOnPage(countBooks);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            session.setAttribute(FieldNameConstant.COUNT_BOOKS_ON_PAGE, countBooks);
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, currentPage);
            request.setAttribute(FieldNameConstant.TOTAL_PAGES, service.calcPagesCountBooks(locale, countBooks));
            request.setAttribute(FieldNameConstant.BOOKS, service.getBooksPerPage(pageData));

            forward(request, response, PageConstant.BOOK_CATALOG);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
