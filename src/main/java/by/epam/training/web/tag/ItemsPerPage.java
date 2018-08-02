package by.epam.training.web.tag;

import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * The class implements the ItemsPerPage tag.
 *
 * @author  Nikita Shkonda
 */
public class ItemsPerPage extends SimpleTagSupport {
    private  static final Logger logger = Logger.getLogger(ItemsPerPage.class);

    private String url;
    private String command;
    private String items;
    private int currentCount;

    private int startCount = FieldNameConstant.INIT_START_COUNT;
    private int stepCount = FieldNameConstant.INIT_STEP_COUNT;
    private int maxCount = FieldNameConstant.INIT_MAX_COUNT;

    /**
     * The method sets the value of url.
     *
     * @param  url - url of the request.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The method sets the value of command.
     *
     * @param  command - command of the request.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setCommand(String command) {
        this.command = (command);
    }

    /**
     * The method sets the value of items.
     *
     * @param  items - items of the request.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setItems(String items) {
        this.items = items;
    }

    /**
     * The method sets current count items on page.
     *
     * @param  currentCount - count items on page.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setCurrentCount(String currentCount) {
        this.currentCount = getInt(currentCount);
    }

    /**
     * The method implements the action of the tag.
     *
     * @author  Nikita Shkonda
     *
     */
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

    private Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("Error converting number to string", ex);
        }
        return result;
    }
}
