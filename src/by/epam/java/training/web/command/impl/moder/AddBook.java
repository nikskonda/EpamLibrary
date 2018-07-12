package by.epam.java.training.web.command.impl.moder;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.PublishingHouse;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.SHOW_BOOK_CATALOG;
import static by.epam.java.training.web.command.Page.SIGN_IN;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class AddBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(AddBook.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            if (user==null){
                redirect(response, SIGN_IN);
                return;
            }
            Book defBook = new Book();
            defBook.setName(request.getParameter(BOOK_NAME));
            defBook.setDescription(request.getParameter(BOOK_DESCRIPTION));
            defBook.setPdfFileUrl(request.getParameter(BOOK_PDF_URL));
            defBook.setTextFileUrl(request.getParameter(BOOK_TEXT_URL));
            defBook.setPublishYear(Integer.parseInt(request.getParameter(BOOK_YEAR)));
            defBook.setPrice(Double.parseDouble(request.getParameter(BOOK_PRICE)));
            defBook.setPages(Integer.parseInt(request.getParameter(BOOK_PAGES)));
            defBook.setCoverUrl(request.getParameter(BOOK_COVER_URL));
            defBook.setPublishingHouse(
                    new PublishingHouse(request.getParameter(BOOK_PUBLISHING_HOUSE)));
            Book translatedBook = new Book();
            translatedBook.setName(request.getParameter(BOOK_NAME_RU));
            translatedBook.setDescription(request.getParameter(BOOK_DESCRIPTION_RU));
            translatedBook.setPdfFileUrl(request.getParameter(BOOK_PDF_URL_RU));
            translatedBook.setTextFileUrl(request.getParameter(BOOK_TEXT_URL_RU));
            String lang = request.getParameter(LANG);

            ModeratorService service = ServiceFactory.getModeratorService();
            service.addBook(defBook, translatedBook, lang);

            CommandFactory.getCommand(SHOW_BOOK_CATALOG).execute(request, response);
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
