package by.epam.training.web.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * The class implements the pagination tag.
 *
 * @author  Nikita Shkonda
 */
public class Pagination extends SimpleTagSupport {
    private  static final Logger logger = Logger.getLogger(Pagination.class);

    private String url;
    private String command;
    private int currentPage;
    private int totalPages;

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
     * The method sets current page.
     *
     * @param  currentPage - current page.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setCurrentPage(String currentPage) {
        this.currentPage = getInt(currentPage);
    }

    /**
     * The method sets total pages.
     *
     * @param  totalPages - total pages.
     *
     * @author  Nikita Shkonda
     *
     */
    public void setTotalPages(String totalPages) {
        this.totalPages = getInt(totalPages);
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

        if (currentPage != 1){
            out.print("<li><a class=\"pgn__num\" href=\"/"+url+"?command="+command+"&numberOfPage=1\">1</a></li>");
        }
        if (currentPage > 1){
            out.print("<li><a class=\"pgn__prev\" href=\"/"+url+"?command="+command+"&numberOfPage="+(currentPage-1)+"\">Prev</a></li>");
        }
        out.print("<li><a class=\"pgn__num current\">"+currentPage+"</a></li>");
        if (currentPage < totalPages){
            out.print("<li><a class=\"pgn__next\" href=\"/"+url+"?command="+command+"&numberOfPage="+(currentPage+1)+"\">Next</a></li>");
            out.print("<li><a class=\"pgn__num\" href=\"/"+url+"?command="+command+"&numberOfPage="+totalPages+"\">"+totalPages+"</a></li>");
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
