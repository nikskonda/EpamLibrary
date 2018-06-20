package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.book.Book;
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

import static by.epam.java.training.web.command.Pages.CATALOG;

public class BookCatalog extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(BookCatalog.class);

    private static final String BOOKS = "books";
    private static final String LOCAL = "local";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            BookService service = ServiceFactory.getInstance().getBookService();

            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCAL);

            List<Book> books = service.getBooks(locale);

            request.setAttribute(BOOKS, books);
            forward(request, response, CATALOG.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
