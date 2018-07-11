package by.epam.java.training.web.filter.user;

import by.epam.java.training.web.filter.user.util.PathManager;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileUpload implements Filter {

    private static final Logger logger = Logger.getLogger(FileUpload.class);

    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final String RUSSIAN_CHARSET = "UTF-8";
    private static final String TEMP_DIR = "javax.servlet.context.tempdir";

    private FilterConfig filterConfig = null;
    private final Random random = new Random();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try{

            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setSizeThreshold(1024*1024);
            File tempDir = (File)request.getServletContext().getAttribute(TEMP_DIR);
            factory.setRepository(tempDir);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(1024 * 1024 * 10);

            Map<String, String[]> parameterMap = new HashMap<String, String[]>();

            Iterator iter = upload.parseRequest(httpRequest).iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы
                    processFormField(item.getFieldName(), item.getString(), parameterMap);

                } else {
                    //в противном случае рассматриваем как файл
                    String fileName = processUploadedFile(item, request);
                    processFormField(item.getFieldName(), fileName, parameterMap);
                }
            }

            httpRequest = wrapRequest(httpRequest, parameterMap);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        filterChain.doFilter(httpRequest, response);
    }



    private void processFormField(String name, String value, Map<String, String[]> parameterMap) throws UnsupportedEncodingException {
        //переводим из стандартной кодировки в utf-8
        value = new String(value.getBytes(DEFAULT_CHARSET), RUSSIAN_CHARSET);
        String[] values = parameterMap.get(name);
        if (values == null) {
            // Not in parameter map yet, so add as new value.
            parameterMap.put(name, new String[] { value });
        } else {
            // Multiple field values, so add new value to existing array.
            int length = values.length;
            String[] newValues = new String[length + 1];
            System.arraycopy(values, 0, newValues, 0, length);
            newValues[length] = value;
            parameterMap.put(name, newValues);
        }
    }

    private String processUploadedFile(FileItem item, ServletRequest request) throws Exception {
        File uploadetFile = null;
        String fileName;
        do{
            fileName = PathManager.getPath(item.getFieldName())+nameGenerate(item.getName());
            String path = request.getServletContext().getRealPath( fileName);
            uploadetFile = new File(path);
        }while(uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
        return fileName;
    }

    private static final String DOT = ".";

    private String nameGenerate(String oldName){
        Integer newName = random.nextInt();
        String type = oldName.substring(oldName.lastIndexOf(DOT));
        return newName+type;
    }

    private static HttpServletRequest wrapRequest(
            HttpServletRequest request, final Map<String, String[]> parameterMap)
    {
        return new HttpServletRequestWrapper(request) {
            public Map<String, String[]> getParameterMap() {
                return parameterMap;
            }
            public String[] getParameterValues(String name) {
                return parameterMap.get(name);
            }
            public String getParameter(String name) {
                String[] params = getParameterValues(name);
                return params != null && params.length > 0 ? params[0] : null;
            }
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameterMap.keySet());
            }
        };
    }
}