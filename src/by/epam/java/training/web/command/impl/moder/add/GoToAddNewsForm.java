package by.epam.java.training.web.command.impl.moder.add;

import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.util.PageConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class GoToAddNewsForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToAddNewsForm.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{

            forward(request, response, PageConstants.ADD_NEWS);

        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }

    }

}