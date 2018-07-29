package by.epam.training.web.servlet;

import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to Processes requests and redirect them to Command.
 *
 * @author Nikita Shkonda
 *
 * @see CommandFactory
 * @see Command
 */
public class FrontController extends HttpServlet {

    private static final String COMMAND = "command";

    /**
     * Process http GET-requests.<br>
     * Redirect to {@link FrontController#process(HttpServletRequest, HttpServletResponse)}.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Process http POST-requests.<br>
     * Redirect to {@link FrontController#process(HttpServletRequest, HttpServletResponse)}.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
    }

    /**
     * Process http requests.
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String command = request.getParameter(COMMAND);
        CommandFactory.getCommand(command).execute(request, response);
    }


}
