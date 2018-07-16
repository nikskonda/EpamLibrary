package by.epam.java.training.web.command.impl.moder;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.*;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class DeleteBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteBook.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            ModeratorService service = ServiceFactory.getModeratorService();
            Integer bookId = Integer.parseInt(request.getParameter(BOOK_ID));

            if (!service.delBook(bookId)){
                CommandFactory.getCommand(OPEN_EDITING_BOOK).execute(request, response);
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

}
