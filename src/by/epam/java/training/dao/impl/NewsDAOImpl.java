package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsConstr;
import by.epam.java.training.model.news.NewsCover;
import by.epam.java.training.model.news.NewsLang;
import org.apache.log4j.Logger;

import java.sql.*;
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
        ConnectionPool conPool = DAOFactory.getConnectionPool();
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
                news.setSmallPhotoUrl(rs.getString(NEWS_SMALL_PHOTO_URL));
                news.setUserFirstName(rs.getString(USER_FIRST_NAME));
                news.setUserLastName(rs.getString(USER_LAST_NAME));
                news.setPublishDate(rs.getTime(NEWS_PUBLISH_DATE));
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
    public News getNews(Integer newsId, String locale) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        News news = new News();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_NEWS_BY_ID);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(NEWS_ID, newsId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                news.setId(rs.getInt(NEWS_ID));
                news.setTitle(rs.getString(NEWS_TITLE));
                news.setPhotoUrl(rs.getString(NEWS_PHOTO_URL));
                news.setUserFirstName(rs.getString(USER_FIRST_NAME));
                news.setUserLastName(rs.getString(USER_LAST_NAME));
                news.setPublishDate(rs.getTime(NEWS_PUBLISH_DATE));
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

    @Override
    public Integer calcTotalPages(String locale, Integer countNewsOnOnePage) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_NEWS);
            cstmt.setInt(COUNT_NEWS_ON_PAGE, countNewsOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public Integer addNews(NewsConstr defNews) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        Integer newsId = null;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(ADD_NEWS);
            cstmt.setString(NEWS_TITLE, defNews.getTitle());
            cstmt.setString(NEWS_TEXT, defNews.getText());
            cstmt.setString(NEWS_PHOTO_URL, defNews.getPhotoUrl());
            cstmt.setString(NEWS_SMALL_PHOTO_URL, defNews.getPhotoUrl());
            cstmt.setInt(USER_ID, defNews.getUserId());
            cstmt.registerOutParameter(NEWS_ID, Types.SMALLINT);
            cstmt.executeQuery();
            newsId = cstmt.getInt(NEWS_ID);

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return newsId;
    }

    @Override
    public void addNewsByLang(NewsLang news) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(ADD_NEWS_LANG);
            cstmt.setInt(NEWS_ID, news.getId());
            cstmt.setString(NEWS_TITLE, news.getTitle());
            cstmt.setString(NEWS_TEXT, news.getText());
            cstmt.setString(LOCALE, news.getLang());
            cstmt.executeQuery();

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
    }
}
