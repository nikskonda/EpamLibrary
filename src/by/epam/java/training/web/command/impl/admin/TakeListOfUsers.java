package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.USER_LIST;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class TakeListOfUsers extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfUsers.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            AdministratorService service = ServiceFactory.getAdministratorService();
            rememberLastAction(request);
            HttpSession session = request.getSession(true);
            Integer countUsers = getCount(request, COUNT_USERS_ON_PAGE, INIT_COUNT_USERS);
            Integer currentPage = getCurrentPage(request);
            String locale = (String) session.getAttribute(LOCALE);

            PageAttribute pageAttribute = new PageAttribute();
            pageAttribute.setCountOnPage(countUsers);
            pageAttribute.setNumberOfPage(currentPage);
            pageAttribute.setLocale(locale);


            session.setAttribute(COUNT_USERS_ON_PAGE, countUsers);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountUsers(countUsers));
            request.setAttribute(FieldNameConstant.USER_LIST, service.getUsersPerPage(pageAttribute));

            forward(request, response, USER_LIST);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
