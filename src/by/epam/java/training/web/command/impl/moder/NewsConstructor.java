package by.epam.java.training.web.command.impl.moder;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.CommandManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;
import static by.epam.java.training.web.command.Page.*;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class NewsConstructor extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(NewsConstructor.class);

//    private final Random random = new Random();
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        try{
//
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//
//            factory.setSizeThreshold(1024*1024);
//            File tempDir = (File)request.getServletContext().getAttribute("javax.servlet.context.tempdir");
//            factory.setRepository(tempDir);
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            upload.setSizeMax(1024 * 1024 * 10);
//
//            List items = upload.parseRequest(request);
//
//            Iterator iter = items.iterator();
//
//            while (iter.hasNext()) {
//                FileItem item = (FileItem) iter.next();
//
//                if (item.isFormField()) {
//                    //если принимаемая часть данных является полем формы
//                    System.out.println(item.getFieldName()+"="+item.getString());
//                } else {
//                    //в противном случае рассматриваем как файл
//                    processUploadedFile(item, request);
//                }
//            }
//
//            forward(request, response, NEWS_LIST.getPage());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//    }
//
//    private void processUploadedFile(FileItem item, HttpServletRequest request) throws Exception {
//        File uploadetFile = null;
//        do{
//            String path = request.getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());
//            uploadetFile = new File(path);
//        }while(uploadetFile.exists());
//        uploadetFile.createNewFile();
//        item.write(uploadetFile);
//    }

    private static final String TEXT = "news_text";
    private static final String TITLE = "news_title";

    private static final String TEXT_RU = "news_text_ru";
    private static final String TITLE_RU = "news_title_ru";

    private static final String PHOTO_URL = "news_photo_url";
    private static final String THUMBS_URL = "news_thumbs_url";

    private static final String USER = "user";
    private static final String NEWS_LANG = "news_lang";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            if (user==null){
                redirect(response, SIGN_IN);
                return;
            }

            ModeratorService service = ServiceFactory.getModeratorService();
            News defNews = new News();
            defNews.setText(request.getParameter(TEXT));
            defNews.setPhotoUrl(request.getParameter(PHOTO_URL));
            defNews.setThumbsUrl(request.getParameter(THUMBS_URL));
            defNews.setTitle(request.getParameter(TITLE));
            defNews.setUserId(user.getId());

            News translatedNews = new News();
            translatedNews.setText(request.getParameter(TEXT_RU));
            translatedNews.setTitle(request.getParameter(TITLE_RU));
            String lang = (request.getParameter(NEWS_LANG));

            service.addNews(defNews, translatedNews, lang);

            CommandFactory.getCommand(SHOW_NEWS_LIST).execute(request, response);
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
