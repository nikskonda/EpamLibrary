package by.epam.training.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to process a request received from {@link by.epam.training.web.servlet.FrontController}
 *
 * @author Nikita Shkonda
 */
public interface Command {

    /**
     * Executes the request.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
