package by.epam.java.training.dao;

import by.epam.java.training.dao.impl.BookDAOImpl;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractDAO.class);

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

    public void closeCallableStatement(CallableStatement callableStatement){
        try{
            if (callableStatement!=null){
                callableStatement.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close CallableStatement",ex);
        }
    }

    public void putbackConnection(Connection connection, ConnectionPool connectionPool){
        try{
            connectionPool.putback(connection);
        } catch (NullPointerException ex){
            logger.warn("Connection was not received", ex);
        }
    }
}
