package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.impl.BookDAOImpl;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.zip.DataFormatException;

public abstract class AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractDAO.class);

    public Connection retrieveConnection() throws ConnectionPoolException{
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        Connection connection = connectionPool.retrieve();
        return connection;
    }

    public void closeResultSet(ResultSet resultSet) throws DAOException {
        try{
            if (resultSet!=null){
                resultSet.close();
            }
        }
        catch (SQLException ex){
            throw new DAOException("I can not close ResultSet",ex);
        }
    }

    public void closeStatement(Statement statement) throws DAOException {
        try{
            if (statement!=null){
                statement.close();
            }
        }
        catch (SQLException ex){
            throw new DAOException("I can not close CallableStatement",ex);
        }
    }

    public void putbackConnection(Connection connection) throws DAOException {
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        try{
            connectionPool.putback(connection);
        } catch (NullPointerException ex){
            throw new DAOException("Connection was not received", ex);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        }
    }

    public void closeAll(ResultSet resultSet, Statement statement, Connection connection) throws DAOException {
        closeResultSet(resultSet);
        closeStatementAndConnection(statement, connection);
    }

    public void closeStatementAndConnection(Statement statement, Connection connection) throws DAOException {
        closeStatement(statement);
        putbackConnection(connection);
    }
}
