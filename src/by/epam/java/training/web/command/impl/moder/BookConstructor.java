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

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.CommandName.OPEN_BOOK_CATALOG;
import static by.epam.java.training.web.command.Page.BOOK_CATALOG;
import static by.epam.java.training.web.command.Page.SIGN_IN;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class BookConstructor extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(BookConstructor.class);


    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TEXT_URL = "textUrl";
    private static final String PDF_URL = "pdfUrl";
    private static final String YEAR = "year";
    private static final String PRICE = "price";
    private static final String PAGES = "pages";
    private static final String PUBLISHING_HOUSE = "publishingHouse";
    private static final String COVER_URL = "coverUrl";
    private static final String NAME_RU = "nameRU";
    private static final String DESCRIPTION_RU = "descriptionRU";
    private static final String TEXT_URL_RU = "textUrlRU";
    private static final String PDF_URL_RU = "pdfUrlRU";

    private static final String LANG = "lang";

    private static final String USER = "user";


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
            defBook.setName(request.getParameter(NAME));
            defBook.setDescription(request.getParameter(DESCRIPTION));
            defBook.setPdfFileUrl(request.getParameter(PDF_URL));
            defBook.setTextFileUrl(request.getParameter(TEXT_URL));
            defBook.setPublishYear(Integer.parseInt(request.getParameter(YEAR)));
            defBook.setPrice(Double.parseDouble(request.getParameter(PRICE)));
            defBook.setPages(Integer.parseInt(request.getParameter(PAGES)));
            defBook.setCoverUrl(request.getParameter(COVER_URL));
            defBook.setPublishingHouse(
                    new PublishingHouse(request.getParameter(PUBLISHING_HOUSE)));
            Book translatedBook = new Book();
            translatedBook.setName(request.getParameter(NAME_RU));
            translatedBook.setDescription(request.getParameter(DESCRIPTION_RU));
            translatedBook.setPdfFileUrl(request.getParameter(PDF_URL_RU));
            translatedBook.setTextFileUrl(request.getParameter(TEXT_URL_RU));
            String lang = request.getParameter(LANG);

            ModeratorService service = ServiceFactory.getModeratorService();
            service.addBook(defBook, translatedBook, lang);

            CommandFactory.getCommand(OPEN_BOOK_CATALOG).execute(request, response);
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
