package by.epam.java.training.web.filter;

import by.epam.java.training.web.command.Page;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainFilter extends AbstractFilter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        redirect(httpResponse, "/news?command=open_news_list");
        return;
    }
}
