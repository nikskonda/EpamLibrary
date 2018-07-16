package by.epam.java.training.web.filter;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.Page;

import static by.epam.java.training.web.command.util.FieldNames.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            AdministratorService service = ServiceFactory.getAdministratorService();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(USER);

            if (user == null) {
                redirect(response, Page.SIGN_IN);
                return;
            }
            if (!service.isAdministrator(user.getLogin())) {
                request.setAttribute(INSUFFICIENT_RIGHTS, true);
                executeLastAction(request, response);
                return;
            } else {
                filterChain.doFilter(request, response);
            }

        } catch (DAOException ex){

        }
    }

}