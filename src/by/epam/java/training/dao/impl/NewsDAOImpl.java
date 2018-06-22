package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class NewsDAOImpl extends AbstractDAO implements NewsDAO {

    private static final Logger logger = Logger.getLogger(NewsDAOImpl.class);

    @Override
    public List<NewsCover> getNewsByPage(String locale, Integer countOnPage, Integer numberOfPage) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        List<NewsCover> newsList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_ALL_NEWS);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(COUNT_NEWS_ON_PAGE, countOnPage);
            cstmt.setInt(NUMBER_OF_PAGE, numberOfPage);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                NewsCover news = new NewsCover();
                news.setId(rs.getInt(NEWS_ID));
                news.setTitle(rs.getString(NEWS_TITLE));
                news.setPhotoUrl(rs.getString(NEWS_PHOTO_URL));
                news.setUserFirstName(rs.getString(USER_FIRST_NAME));
                news.setUserLastName(rs.getString(USER_LAST_NAME));
                news.setPublishDate(rs.getDate(NEWS_PUBLISH_DATE));

                newsList.add(news);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return newsList;
    }

    @Override
    public News getNews(Integer id, String locale) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        News news = new News();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_ALL_NEWS);
            cstmt.setString(LOCALE, locale);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                news.setId(rs.getInt(NEWS_ID));
                news.setTitle(rs.getString(NEWS_TITLE));
                news.setPhotoUrl(rs.getString(NEWS_PHOTO_URL));
                news.setUserFirstName(rs.getString(USER_FIRST_NAME));
                news.setUserLastName(rs.getString(USER_LAST_NAME));
                news.setPublishDate(rs.getDate(NEWS_PUBLISH_DATE));
                news.setText(rs.getString(NEWS_TEXT));
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return news;
    }


}
