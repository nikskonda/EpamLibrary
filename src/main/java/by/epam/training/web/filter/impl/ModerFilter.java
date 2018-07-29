package by.epam.training.web.filter.impl;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.servise.ModeratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.CommandConstants;
import by.epam.training.web.filter.AbstractFilter;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The filter is designed to verify the moderator's user role.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public class ModerFilter extends AbstractFilter {
    private static final Logger logger = Logger.getLogger(ModerFilter.class);

    /**
     * The method checks the user role for the moderator.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain {@link FilterChain}
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ModeratorService service = ServiceFactory.getModeratorService();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(FieldNameConstant.USER);
            System.out.println((String)session.getAttribute(LAST_ACTION));
            if (user == null) {
                rememberLastAction(request);
                redirect(response, "/openSignIn");
                return;
            }
            if (!service.isModerator(user.getLogin())) {
                request.setAttribute(FieldNameConstant.INSUFFICIENT_RIGHTS, true);
                forward(request, response, CommandConstants.TAKE_LIST_OF_NEWS);
                return;
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ServiceException ex){
            throw new ServletException(ex);
        } catch (IOException ex) {
            throw new IOException("Error in pages path", ex);
        }
    }

}
