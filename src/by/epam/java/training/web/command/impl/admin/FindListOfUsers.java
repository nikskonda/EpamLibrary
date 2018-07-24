package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserSearchService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.util.FieldNameConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstants.USER_LIST;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class FindListOfUsers extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(FindListOfUsers.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            UserSearchService service = ServiceFactory.getUserSearchService();
            rememberLastAction(request);
            String search = request.getParameter(SEARCH);
            Integer countUsers = getCount(request, COUNT_USERS_ON_PAGE, INIT_COUNT_USERS);
            Integer currentPage = getCurrentPage(request);
            HttpSession session = request.getSession(true);

            PageAttributes pageData = new PageAttributes();
            pageData.setCountOnPage(countUsers);
            pageData.setNumberOfPage(currentPage);

            session.setAttribute(COUNT_USERS_ON_PAGE, countUsers);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountUserSearchResult(search, countUsers));
            request.setAttribute(SEARCH, search);
            request.setAttribute(FieldNameConstants.USER_LIST, service.findUsersPerPages(search, pageData));

            forward(request, response, USER_LIST);
        } catch (DAOException ex){
            logger.warn("Problem with database", ex);
            request.setAttribute(ERROR_DATABASE, true);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }
    }

}
