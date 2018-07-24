package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static by.epam.java.training.dao.util.SQLRequest.*;

public class NewsDAOImpl extends AbstractDAO implements NewsDAO {

    private static final Logger logger = Logger.getLogger(NewsDAOImpl.class);

    @Override
    public List<NewsPreview> getNewsByPage(PageAttributes pageData) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<NewsPreview> newsList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_LIST_OF_NEWS);
            cstmt.setString(LOCALE, pageData.getLocale());
            cstmt.setInt(COUNT_NEWS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            rs = cstmt.executeQuery();

            while (rs.next()) {
                newsList.add(buildNewsCover(rs));
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        }  finally {
            closeAll(rs, cstmt, con);
        }
        return newsList;
    }

    @Override
    public News getNews(Integer newsId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        News news = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_NEWS);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(NEWS_ID, newsId);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                news = buildNews(rs);
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return news;
    }

    private NewsPreview buildNewsCover(ResultSet rs) throws SQLException{
        NewsPreview news = new NewsPreview();

        news.setId(rs.getInt(NEWS_ID));
        news.setTitle(rs.getString(NEWS_TITLE));
        news.setThumbsUrl(rs.getString(NEWS_THUMBS_URL));
        news.setUserFirstName(rs.getString(USER_FIRST_NAME));
        news.setUserLastName(rs.getString(USER_LAST_NAME));
        news.setPublishDate(rs.getTimestamp(NEWS_PUBLISH_DATE));

        return news;
    }

    private News buildNews(ResultSet rs) throws SQLException{
        News news = new News(buildNewsCover(rs));

        news.setPhotoUrl(rs.getString(NEWS_PHOTO_URL));
        news.setText(rs.getString(NEWS_TEXT));

        return news;
    }

    @Override
    public Integer calcTotalPagesWithBooks(String locale, Integer countNewsOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_NEWS);
            cstmt.setInt(COUNT_NEWS_ON_PAGE, countNewsOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return result;
    }
}
