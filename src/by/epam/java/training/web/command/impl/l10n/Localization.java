package by.epam.java.training.web.command.impl.l10n;

import by.epam.java.training.web.command.AbstractCommand;
import static by.epam.java.training.web.command.util.FieldNames.*;

import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.CommandName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Localization extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(Localization.class);

    private static final String LOCAL = "local";
    private static final String COMMAND = "command";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            HttpSession session = request.getSession(true);
            session.setAttribute(LOCAL, request.getParameter(COMMAND));

            executeLastAction(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }

    }
}
