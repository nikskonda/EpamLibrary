package by.epam.java.training.web.command.impl.book;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.*;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class TakeBookCatalog extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeBookCatalog.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            BookService service = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            Integer countBooks = getCount(request, COUNT_BOOKS_ON_PAGE, INIT_COUNT_BOOKS);
            Integer currentPage = getCurrentPage(request);
            String locale = (String)session.getAttribute(LOCALE);

            PageAttribute pageData = new PageAttribute();
            pageData.setCountOnPage(countBooks);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            session.setAttribute(COUNT_BOOKS_ON_PAGE, countBooks);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountBooks(locale, countBooks));
            request.setAttribute(BOOKS, service.getBooksPerPage(pageData));

            forward(request, response, BOOK_CATALOG);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
