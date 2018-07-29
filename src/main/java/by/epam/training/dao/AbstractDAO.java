package by.epam.training.dao;

import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * An abstract class that implements the basic methods for accessing the database.
 *
 * @author  Nikita Shkonda
 */
public abstract class AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractDAO.class);

    /**
     * Returns a connection to the database.
     *
     * @return connection.
     *
     * @throws ConnectionPoolException  if there was an error in getting connection.
     *
     * @see ConnectionPool
     *
     */
    protected Connection retrieveConnection() throws ConnectionPoolException{
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        Connection connection = connectionPool.retrieve();
        return connection;
    }

    /**
     * Close ResultSet.
     *
     * @param resultSet - ResultSet.
     *
     * @throws DAOException  if there was an error in the closing of
     * the ResultSet.
     *
     */
    protected void closeResultSet(ResultSet resultSet) throws DAOException {
        try{
            if (resultSet!=null){
                resultSet.close();
            }
        }
        catch (SQLException ex){
            throw new DAOException("I can not close ResultSet",ex);
        }
    }

    /**
     * Close Statement.
     *
     * @param statement - Statement.
     *
     * @throws DAOException  if there was an error in the closing of
     * the Statement.
     *
     */
    protected void closeStatement(Statement statement) throws DAOException {
        try{
            if (statement!=null){
                statement.close();
            }
        }
        catch (SQLException ex){
            throw new DAOException("I can not close CallableStatement",ex);
        }
    }

    /**
     * Putback connection.
     *
     * @param connection - Сonnection.
     *
     * @throws DAOException  if there was connection return error.
     *
     * @see ConnectionPool
     */
    protected void putbackConnection(Connection connection) throws DAOException {
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        try{
            connectionPool.putback(connection);
        } catch (NullPointerException ex){
            throw new DAOException("Connection was not received", ex);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        }
    }

    /**
     * Close ResultSet, Statement and Connection.
     *
     * @param resultSet - ResultSet.
     * @param statement - Statement.
     * @param connection - Сonnection.
     *
     * @throws DAOException  if there was an error in the closing of
     * the result set, statement or connection.
     *
     */
    protected void closeAll(ResultSet resultSet, Statement statement, Connection connection) throws DAOException {
        closeResultSet(resultSet);
        closeStatementAndConnection(statement, connection);
    }

    /**
     * Close Statement and Connection.
     *
     * @param statement - Statement.
     * @param connection - Сonnection.
     *
     * @throws DAOException  if there was an error in the closing of
     * the statement or connection.
     *
     */
    protected void closeStatementAndConnection(Statement statement, Connection connection) throws DAOException {
        closeStatement(statement);
        putbackConnection(connection);
    }
}
