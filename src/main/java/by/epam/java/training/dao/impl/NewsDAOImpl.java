package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static by.epam.java.training.dao.util.SQLRequestConstant.*;

/**
 * Implement an interface that defines different
 * activities with news in database.
 *
 * @author  Nikita Shkonda
 */
public class NewsDAOImpl extends AbstractDAO implements NewsDAO {

    private static final Logger logger = Logger.getLogger(NewsDAOImpl.class);

    /**
     * Return list of news previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of news previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see NewsPreview
     * @see PageAttribute
     *
     */
    @Override
    public List<NewsPreview> getNewsPerPage(PageAttribute pageAttribute) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<NewsPreview> newsList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_LIST_OF_NEWS);
            cstmt.setString(LOCALE, pageAttribute.getLocale());
            cstmt.setInt(COUNT_NEWS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            rs = cstmt.executeQuery();

            while (rs.next()) {
                newsList.add(buildNewsCover(rs));
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return newsList;
    }

    /**
     * Return information about news by id and language
     *
     * @param newsId - Id of the news.
     * @param locale - Language.
     *
     * @return information about news.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
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
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return news;
    }

    /**
     * Return total count of page with news.
     *
     * @param locale - Language.
     * @param countNewsOnOnePage - Count news on one page.
     *
     * @return Total count of page with news.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountNews(String locale, Integer countNewsOnOnePage) throws DAOException {
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
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return result;
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
}
