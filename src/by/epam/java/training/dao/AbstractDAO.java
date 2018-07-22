package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.impl.BookDAOImpl;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractDAO.class);

    public Connection retrieveConnection() throws ConnectionPoolException{
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        Connection connection = connectionPool.retrieve();
        return connection;
    }

    public void closeResultSet(ResultSet resultSet){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close ResultSet",ex);
        }
    }

    public void closeStatement(Statement statement){
        try{
            if (statement!=null){
                statement.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close CallableStatement",ex);
        }
    }

    public void putbackConnection(Connection connection){
        ConnectionPool connectionPool = DAOFactory.getConnectionPool();
        try{
            connectionPool.putback(connection);
        } catch (NullPointerException ex){
            logger.warn("Connection was not received", ex);
        }
    }

    public void closeAll(ResultSet resultSet, Statement statement, Connection connection){
        closeResultSet(resultSet);
        closeStatement(statement);
        putbackConnection(connection);
    }

    public void closeStatementAndConnection(Statement statement, Connection connection){
        closeStatement(statement);
        putbackConnection(connection);
    }
}
