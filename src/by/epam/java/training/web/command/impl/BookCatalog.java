package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.java.training.web.command.Pages.*;

public class BookCatalog extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(BookCatalog.class);

    private static final String BOOKS = "books";
    private static final String LOCALE = "local";

    private static final String COUNT_BOOKS_ON_PAGE = "countBooks";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String TOTAL_PAGES = "totalPages";

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
            BookService service = ServiceFactory.getBookService();

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

            Integer currentPage = getInt(request.getParameter(CURRENT_PAGE));
            if (currentPage==null){
                currentPage = INIT_NUMBER_OF_PAGE;
            }

            session.setAttribute(COUNT_BOOKS_ON_PAGE, newCountBooks);
            request.setAttribute(CURRENT_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcTotalPages(locale, countBooks));
            request.setAttribute(BOOKS, service.getBooksByPage(locale, countBooks, currentPage));

            forward(request, response, BOOK_CATALOG);

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
