package by.epam.training.web.command.impl.moder.add;

import by.epam.training.model.book.Book;
import by.epam.training.model.book.constituents.Genre;
import by.epam.training.model.book.constituents.PublishingHouse;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.ModeratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.util.EncriptionMD5;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static by.epam.training.web.command.CommandConstants.GO_TO_ADD_BOOK_FORM;
import static by.epam.training.web.command.CommandConstants.TAKE_BOOK_CATALOG;

/**
 * Used to add a book.
 *
 * @author  Nikita Shkonda
 */
public class AddBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(AddBook.class);

    /**
     * Adding a book.
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
            ModeratorService service = ServiceFactory.getModeratorService();
            UserService userService = ServiceFactory.getUserService();
            HttpSession session = request.getSession(true);

            String[] genres = request.getParameterValues(FieldNameConstant.GENRES);
            Book defBook = new Book();
            defBook.setName(request.getParameter(FieldNameConstant.BOOK_NAME));
            defBook.setDescription(request.getParameter(FieldNameConstant.BOOK_DESCRIPTION));
            defBook.setPdfFileUrl(request.getParameter(FieldNameConstant.BOOK_PDF_URL));
            defBook.setTextFileUrl(request.getParameter(FieldNameConstant.BOOK_TEXT_URL));
            defBook.setPublishYear(Integer.parseInt(request.getParameter(FieldNameConstant.BOOK_YEAR)));
            defBook.setPrice(Double.parseDouble(request.getParameter(FieldNameConstant.BOOK_PRICE)));
            defBook.setPages(Integer.parseInt(request.getParameter(FieldNameConstant.BOOK_PAGES)));
            defBook.setCoverUrl(request.getParameter(FieldNameConstant.BOOK_COVER_URL));
            defBook.setAuthors(request.getParameter(FieldNameConstant.BOOK_AUTHORS));
            defBook.setPublishingHouse(
                    new PublishingHouse(request.getParameter(FieldNameConstant.BOOK_PUBLISHING_HOUSE)));
            for (String genreId : genres){
                defBook.addGenre(
                        new Genre(Integer.parseInt(genreId)));
            }

            Book tBook = new Book();
            tBook.setName(request.getParameter(FieldNameConstant.BOOK_NAME_RU));
            tBook.setDescription(request.getParameter(FieldNameConstant.BOOK_DESCRIPTION_RU));
            tBook.setPdfFileUrl(request.getParameter(FieldNameConstant.BOOK_PDF_URL_RU));
            tBook.setTextFileUrl(request.getParameter(FieldNameConstant.BOOK_TEXT_URL_RU));
            tBook.setAuthors(request.getParameter(FieldNameConstant.BOOK_AUTHORS_RU));
            String lang = request.getParameter(FieldNameConstant.LANG);

            String password = EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD));
            ActiveUser user = (ActiveUser)session.getAttribute(FieldNameConstant.USER);

            if (!userService.isUserExist(new SignInForm(user.getLogin(), password))){
                clearData(request, defBook, tBook);
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                CommandFactory.getCommand(GO_TO_ADD_BOOK_FORM).execute(request, response);
                return;
            }

            if (!service.addBook(defBook, tBook, lang)){
                clearData(request, defBook, tBook);
                CommandFactory.getCommand(GO_TO_ADD_BOOK_FORM).execute(request, response);
                return;
            }

            CommandFactory.getCommand(TAKE_BOOK_CATALOG).execute(request, response);

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

    private void delete(String nameFile) {
        File file = new File(nameFile);
        if (file.exists()) {
            file.delete();
        }
    }

    private void clearData(HttpServletRequest request, Book defBook, Book tBook){
        delete(request.getServletContext().getRealPath(defBook.getTextFileUrl()));
        delete(request.getServletContext().getRealPath(defBook.getPdfFileUrl()));
        delete(request.getServletContext().getRealPath(defBook.getCoverUrl()));
        delete(request.getServletContext().getRealPath(tBook.getPdfFileUrl()));
        delete(request.getServletContext().getRealPath(tBook.getTextFileUrl()));
        request.setAttribute(FieldNameConstant.BOOK, defBook);
        request.setAttribute(FieldNameConstant.BOOK_RU, tBook);
    }


}
