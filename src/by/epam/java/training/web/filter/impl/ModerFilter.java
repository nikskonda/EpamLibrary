package by.epam.java.training.web.filter.impl;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.CommandName;
import by.epam.java.training.web.filter.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNames.INSUFFICIENT_RIGHTS;
import static by.epam.java.training.web.command.util.FieldNames.USER;

public class ModerFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ModeratorService service = ServiceFactory.getModeratorService();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(USER);
            System.out.println((String)session.getAttribute(LAST_ACTION));
            if (user == null) {
                rememberLastAction(request);
                redirect(response, "/openSignIn");
                return;
            }
            if (!service.isModerator(user.getLogin())) {
                request.setAttribute(INSUFFICIENT_RIGHTS, true);
                forward(request, response, CommandName.TAKE_LIST_OF_NEWS);
                return;
            } else {
                filterChain.doFilter(request, response);
            }
            
        } catch (DAOException ex){

        }
    }

}
