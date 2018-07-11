package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.ADMINISTRATION_USER;
import static by.epam.java.training.web.command.Page.ERROR;

public class OpenErrorPage extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(OpenErrorPage.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            forward(request, response, ERROR);
        } catch (IOException ex){
            logger.warn("Error in page path");
        }
    }
}
