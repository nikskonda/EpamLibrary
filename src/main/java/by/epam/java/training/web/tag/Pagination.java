package by.epam.java.training.web.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class Pagination extends SimpleTagSupport {
    private  static final Logger logger = Logger.getLogger(Pagination.class);

    private String url;
    private String command;
    private int currentPage;
    private int totalPages;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCommand(String command) {
        this.command = (command);
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = getInt(currentPage);
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = getInt(totalPages);
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

        if (currentPage != 1){
            out.print("<li><a class=\"pgn__num\" href=\"/"+url+"?command="+command+"&numberOfPage=1\">First: 1</a></li>");
        }
        if (currentPage > 1){
            out.print("<li><a class=\"pgn__prev\" href=\"/"+url+"?command="+command+"&numberOfPage="+(currentPage-1)+"\">Prev</a></li>");
        }
        out.print("<li><a class=\"pgn__num current\">"+currentPage+"</a></li>");
        if (currentPage < totalPages){
            out.print("<li><a class=\"pgn__next\" href=\"/"+url+"?command="+command+"&numberOfPage="+(currentPage+1)+"\">Next</a></li>");
            out.print("<li><a class=\"pgn__num\" href=\"/"+url+"?command="+command+"&numberOfPage="+totalPages+"\">Last: "+totalPages+"</a></li>");
        }


        out.print("</ul>");
        out.print("</nav>");
        out.print("</div>");
        out.print("</div>");
    }
}
