package by.epam.training.web.command.impl;

import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to display the error page.
 *
 * @author  Nikita Shkonda
 */
public class GoToErrorPage extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToErrorPage.class);

    /**
     * Displays the error page.
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

            forward(request, response, PageConstant.ERROR);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }
}
