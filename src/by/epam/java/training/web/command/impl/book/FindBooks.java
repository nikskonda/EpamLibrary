package by.epam.java.training.web.command.impl.book;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.servise.BookSearchService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.BOOK_CATALOG;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class FindBooks extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(FindBooks.class);

    private static final int INIT_COUNT_BOOKS = 6;
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
            BookSearchService service = ServiceFactory.getBookSearchService();
            String search = request.getParameter(SEARCH);

            HttpSession session = request.getSession(true);
            if (session.getAttribute(LOCALE)==null){
                session.setAttribute(LOCALE, "en");
            }
            String locale = (String)session.getAttribute(LOCALE);

            Integer countBooks = null;

            Integer newCountBooks = getInt(request.getParameter(COUNT_BOOKS_ON_PAGE));
            if (newCountBooks == null){
                countBooks = (Integer)(session.getAttribute(COUNT_BOOKS_ON_PAGE));
                if (countBooks==null){
                    countBooks = INIT_COUNT_BOOKS;
                }
            } else {
                countBooks = newCountBooks;
            }

            Integer currentPage = getInt(request.getParameter(NUMBER_OF_PAGE));
            if (currentPage==null){
                currentPage = INIT_NUMBER_OF_PAGE;
            }

            session.setAttribute(COUNT_BOOKS_ON_PAGE, newCountBooks);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcTotalPages(locale, search, countBooks));
            request.setAttribute(SEARCH, search);

            LordOfPages pageData = new LordOfPages();
            pageData.setCountOnPage(countBooks);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            request.setAttribute(BOOKS, service.getBooksByPage(search, pageData));

            forward(request, response, BOOK_CATALOG);

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
