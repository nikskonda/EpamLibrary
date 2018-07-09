package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.PublishingHouse;
import by.epam.java.training.model.news.NewsConstr;
import by.epam.java.training.model.news.NewsLang;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static by.epam.java.training.web.command.Pages.SIGN_IN;
import static by.epam.java.training.web.command.Pages.START_PAGE;

public class BookConstructor extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(BookConstructor.class);


    private static final String NAME = "name";
    private static final String DESCROPTION = "description";
    private static final String TEXT_URL = "textUrl";
    private static final String PDF_URL = "pdfUrl";
    private static final String YEAR = "year";
    private static final String PRICE = "price";
    private static final String PAGES = "pages";
    private static final String PUBLISHIND_HOUSE = "publishingHouse";
    private static final String COVER_URL = "coverUrl";
    private static final String NAME_RU = "nameRU";
    private static final String DESCROPTION_RU = "descriptionRU";
    private static final String TEXT_URL_RU = "textUrlRU";
    private static final String PDF_URL_RU = "pdfUrlRU";

    private static final String LANG = "lang";

    private static final String USER = "user";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            if (user==null){
                redirect(response, SIGN_IN);
                return;
            }

            Book defBook = new Book();
            defBook.setName(request.getParameter(NAME));
            defBook.setDescription(request.getParameter(DESCROPTION));
            defBook.setPdfFileUrl(request.getParameter(PDF_URL));
            defBook.setTextFileUrl(request.getParameter(TEXT_URL));
            defBook.setPublishYear(Integer.parseInt(request.getParameter(YEAR)));
            defBook.setPrice(Double.parseDouble(request.getParameter(PRICE)));
            defBook.setPages(Integer.parseInt(request.getParameter(PAGES)));
            defBook.setCoverUrl(request.getParameter(COVER_URL));
            defBook.setPublishingHouse(
                    new PublishingHouse(request.getParameter(PUBLISHIND_HOUSE)));
            Book ruBook = new Book();
            ruBook.setName(request.getParameter(NAME_RU));
            ruBook.setDescription(request.getParameter(DESCROPTION_RU));
            ruBook.setPdfFileUrl(request.getParameter(PDF_URL_RU));
            ruBook.setTextFileUrl(request.getParameter(TEXT_URL_RU));
            String lang = request.getParameter(LANG);



            forward(request, response, START_PAGE);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
