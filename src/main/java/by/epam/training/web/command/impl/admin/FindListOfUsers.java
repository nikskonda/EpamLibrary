package by.epam.training.web.command.impl.admin;

import by.epam.training.model.PageAttribute;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserSearchService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import static by.epam.training.web.command.util.FieldNameConstant.*;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Search for users.
 *
 * @author  Nikita Shkonda
 */
public class FindListOfUsers extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(FindListOfUsers.class);

    /**
     * Looks for users.
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
            rememberLastAction(request);
            UserSearchService service = ServiceFactory.getUserSearchService();
            String search = request.getParameter(SEARCH);
            Integer countUsers = getCount(request, COUNT_USERS_ON_PAGE, INIT_COUNT_USERS);
            Integer currentPage = getCurrentPage(request);
            HttpSession session = request.getSession(true);

            PageAttribute pageData = new PageAttribute();
            pageData.setCountOnPage(countUsers);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale((String)session.getAttribute(LOCALE));

            session.setAttribute(COUNT_USERS_ON_PAGE, countUsers);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountUserSearchResults(search, countUsers));
            request.setAttribute(SEARCH, search);
            request.setAttribute(USER_LIST, service.findUsersPerPage(search, pageData));

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
