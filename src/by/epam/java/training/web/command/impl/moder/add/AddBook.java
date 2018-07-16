package by.epam.java.training.web.command.impl.moder.add;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
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
import java.io.File;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.OPEN_ADD_BOOK;
import static by.epam.java.training.web.command.CommandName.SHOW_BOOK_CATALOG;
import static by.epam.java.training.web.command.Page.SIGN_IN;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class AddBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(AddBook.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            ModeratorService service = ServiceFactory.getModeratorService();
            String[] genres = request.getParameterValues(GENRES);
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
            for (String genreId : genres){
                defBook.addGenre(
                        new Genre(Integer.parseInt(genreId)));
            }

            Book translatedBook = new Book();
            translatedBook.setName(request.getParameter(BOOK_NAME_RU));
            translatedBook.setDescription(request.getParameter(BOOK_DESCRIPTION_RU));
            translatedBook.setPdfFileUrl(request.getParameter(BOOK_PDF_URL_RU));
            translatedBook.setTextFileUrl(request.getParameter(BOOK_TEXT_URL_RU));
            String lang = request.getParameter(LANG);

            if (!service.addBook(defBook, translatedBook, lang)){
                delete(request.getServletContext().getRealPath(defBook.getTextFileUrl()));
                delete(request.getServletContext().getRealPath(defBook.getPdfFileUrl()));
                delete(request.getServletContext().getRealPath(defBook.getCoverUrl()));
                delete(request.getServletContext().getRealPath(translatedBook.getPdfFileUrl()));
                delete(request.getServletContext().getRealPath(translatedBook.getTextFileUrl()));
                request.setAttribute(BOOK, defBook);
                request.setAttribute(BOOK_RU, translatedBook);
                CommandFactory.getCommand(OPEN_ADD_BOOK).execute(request, response);
                return;
            }

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

    private static void delete(String nameFile) {
        File file = new File(nameFile);
        if (file.exists()) {
            file.delete();
        }
    }

}
