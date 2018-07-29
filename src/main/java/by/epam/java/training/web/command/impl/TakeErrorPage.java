package by.epam.java.training.web.command.impl;

import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.ERROR;

public class TakeErrorPage extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeErrorPage.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{

            forward(request, response, ERROR);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }
}
