package by.epam.training.web.command.impl.moder.add;

import by.epam.training.servise.BookService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.PageConstant;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to go to the book constructor.
 *
 * @author  Nikita Shkonda
 */
public class GoToAddBookForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToAddBookForm.class);

    /**
     * Moves to adding a book
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
            BookService service = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);

            request.setAttribute(FieldNameConstant.GENRES, service.getGenres(locale));

            forward(request, response, PageConstant.ADD_BOOK);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

}
