package by.epam.java.training.web.command.impl.moder.add;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class GoToAddBookForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToAddBookForm.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            BookService service = ServiceFactory.getBookService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);

            request.setAttribute(GENRES, service.getGenres(locale));

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
