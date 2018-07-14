package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.USER_LIST;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class FindUserList extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(FindUserList.class);

    private static final int INIT_COUNT_USERS = 8;
    private static final int INIT_NUMBER_OF_PAGE = 1;

    private Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("", ex);
        }
        return result;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            AdministratorService service = ServiceFactory.getAdministratorService();
            rememberLastAction(request);
            String search = request.getParameter(SEARCH);
            HttpSession session = request.getSession(true);
            Integer countUsers = null;
            Integer newCountUsers = getInt(request.getParameter(COUNT_USERS_ON_PAGE));
            if (newCountUsers == null){
                countUsers = (Integer)(session.getAttribute(COUNT_USERS_ON_PAGE));
                if (countUsers==null){
                    countUsers = INIT_COUNT_USERS;
                }
            } else {
                countUsers = newCountUsers;
            }

            Integer currentPage = getInt(request.getParameter(NUMBER_OF_PAGE));
            if (currentPage==null){
                currentPage = INIT_NUMBER_OF_PAGE;
            }

            LordOfPages pageData = new LordOfPages();
            pageData.setCountOnPage(countUsers);
            pageData.setNumberOfPage(currentPage);

            session.setAttribute(COUNT_USERS_ON_PAGE, countUsers);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcTotalPagesWithUsersSearch(search, countUsers));
            request.setAttribute(SEARCH, search);
            request.setAttribute(USERS, service.FindUsersByPages(search, pageData));

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
