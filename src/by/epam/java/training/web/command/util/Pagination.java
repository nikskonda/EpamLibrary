package by.epam.java.training.web.command.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class Pagination {
    private  static final Logger logger = Logger.getLogger(Pagination.class);

    protected Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("Error converting number to string", ex);
        }
        return result;
    }

    protected Integer getCount(HttpServletRequest request, String countOnOnePage, Integer initCount){
        HttpSession session = request.getSession(true);
        Integer count = null;
        Integer newCount = getInt(request.getParameter(countOnOnePage));

        if (newCount == null){
            count = (Integer)(session.getAttribute(countOnOnePage));
            if (count == null){
                count = initCount;
            }
        } else {
            count = newCount;
        }

        return count;
    }

    protected Integer getCurrentPage(HttpServletRequest request){
        Integer currentPage = getInt(request.getParameter(NUMBER_OF_PAGE));

        if (currentPage==null){
            currentPage = INIT_NUMBER_OF_PAGE;
        }

        return currentPage;
    }

}
