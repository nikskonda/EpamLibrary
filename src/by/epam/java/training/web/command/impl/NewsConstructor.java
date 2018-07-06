package by.epam.java.training.web.command.impl;

import by.epam.java.training.model.news.NewsConstr;
import by.epam.java.training.model.news.NewsLang;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages.*;

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
//            forward(request, response, START_PAGE.getPage());
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

    private static final String PHOTO_URL = "new_photo_url";

    private static final String USER = "user";
    private static final String NEW_LANG = "news_lang";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser)session.getAttribute(USER);

            if (user==null){
                redirect(response, SIGN_IN);
                return;
            }

            NewsConstr defNews = new NewsConstr();
            defNews.setText(request.getParameter(TEXT));
            defNews.setPhotoUrl(request.getParameter(PHOTO_URL));
            defNews.setTitle(request.getParameter(TITLE));

            defNews.setUserId(user.getId());
            NewsLang newsRu = new NewsLang();
            newsRu.setText(request.getParameter(TEXT_RU));
            newsRu.setTitle(request.getParameter(TITLE_RU));
            newsRu.setLang(request.getParameter(NEW_LANG));
            NewsService service = ServiceFactory.getNewsService();

            service.addNews(defNews, newsRu);

            forward(request, response, START_PAGE);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
