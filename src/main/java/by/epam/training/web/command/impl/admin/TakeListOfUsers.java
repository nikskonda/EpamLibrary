package by.epam.training.web.command.impl.admin;

import by.epam.training.model.PageAttribute;
import by.epam.training.servise.AdministratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Used to get a list of users.
 *
 * @author  Nikita Shkonda
 */
public class TakeListOfUsers extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfUsers.class);

    /**
     * Getting all users.
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
            AdministratorService service = ServiceFactory.getAdministratorService();
            rememberLastAction(request);
            HttpSession session = request.getSession(true);
            Integer countUsers = getCount(request, FieldNameConstant.COUNT_USERS_ON_PAGE, FieldNameConstant.INIT_COUNT_USERS);
            Integer currentPage = getCurrentPage(request);
            String locale = (String) session.getAttribute(FieldNameConstant.LOCALE);

            PageAttribute pageAttribute = new PageAttribute();
            pageAttribute.setCountOnPage(countUsers);
            pageAttribute.setNumberOfPage(currentPage);
            pageAttribute.setLocale(locale);


            session.setAttribute(FieldNameConstant.COUNT_USERS_ON_PAGE, countUsers);
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, currentPage);
            request.setAttribute(FieldNameConstant.TOTAL_PAGES, service.calcPagesCountUsers(countUsers));
            request.setAttribute(FieldNameConstant.USER_LIST, service.getUsersPerPage(pageAttribute));

            forward(request, response, PageConstant.USER_LIST);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
