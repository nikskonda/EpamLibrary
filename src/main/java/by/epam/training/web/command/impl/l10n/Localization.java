package by.epam.training.web.command.impl.l10n;

import by.epam.training.web.command.AbstractCommand;

import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to change a localization.
 *
 * @author  Nikita Shkonda
 */
public class Localization extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(Localization.class);

    /**
     * Changing localization.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(true);

            session.setAttribute(FieldNameConstant.LOCALE, request.getParameter(FieldNameConstant.COMMAND));

            executeLastAction(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
