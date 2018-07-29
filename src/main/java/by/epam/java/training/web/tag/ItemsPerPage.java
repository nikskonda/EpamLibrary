package by.epam.java.training.web.tag;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class ItemsPerPage extends SimpleTagSupport {
    private  static final Logger logger = Logger.getLogger(ItemsPerPage.class);

    private String url;
    private String command;
    private String items;
    private int currentCount;

    private int startCount = INIT_START_COUNT;
    private int stepCount =INIT_STEP_COUNT;
    private int maxCount = INIT_MAX_COUNT;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setCommand(String command) {
        this.command = (command);
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setCurrentCount(String currentCount) {
        this.currentCount = getInt(currentCount);
    }


    protected Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("Error converting number to string", ex);
        }
        return result;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();

        out.print("<div class=\"row\">");
        out.print(" <div class=\"col-full\">");
        out.print("<nav class=\"pgn\">");
        out.print("<ul>");

        for (int i = startCount; i<maxCount; i+=stepCount){
            if (currentCount == i){
                out.print("<li><a class=\"pgn__num current\" href=\"/"+url+"?command="+command+"&"+items+"="+i+"\">"+i+"</a></li>");
            } else {
                out.print("<li><a class=\"pgn__num\" href=\"/"+url+"?command="+command+"&"+items+"="+i+"\">"+i+"</a></li>");
            }
        }

        out.print("</ul>");
        out.print("</nav>");
        out.print("</div>");
        out.print("</div>");
    }
}
