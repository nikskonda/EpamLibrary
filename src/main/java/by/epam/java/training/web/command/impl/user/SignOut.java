package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class SignOut extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignOut.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession();

            session.removeAttribute(USER);

            executeLastAction(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}
