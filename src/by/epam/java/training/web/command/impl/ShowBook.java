package by.epam.java.training.web.command.impl;

import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages2.BOOK;

public class ShowBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ShowBook.class);

    private static final String BOOK_DATA = "book";
    private static final String BOOK_ID = "book_id";
    private static final String LOCALE = "local";


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
            BookService service = ServiceFactory.getInstance().getBookService();

            HttpSession session = request.getSession(true);
            if (session.getAttribute(LOCALE)==null){
                session.setAttribute(LOCALE, "en");
            }
            String locale = (String)session.getAttribute(LOCALE);

            Integer bookId = Integer.parseInt(request.getParameter(BOOK_ID));

            request.setAttribute(BOOK_DATA, service.getBook(bookId, locale));
            forward(request, response, BOOK.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
