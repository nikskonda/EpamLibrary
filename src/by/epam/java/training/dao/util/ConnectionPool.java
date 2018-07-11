package by.epam.java.training.dao.util;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final int INIT_COUNT_CONNECTIONS = 7;

    private static final String DB_RESOURCE_BUNDLE = "resource.db";
    private static final String SQL_LOGIN = "db.login";
    private static final String SQL_PASSWORD = "db.password";
    private static final String URL = "db.url";
    private static final String DRIVER = "db.driver";

    private Queue<Connection> freeConnections = new LinkedList<>();
    private List<Connection> usedConnections = new LinkedList<>();


    public ConnectionPool() {
        for (int i = 0; i< INIT_COUNT_CONNECTIONS; i++){
            Connection con = getConnection();
            if (con != null){
                freeConnections.offer(con);
            }
        }
    }

    private Connection getConnection() {
        ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
        Connection connection = null;
        try {
            Class.forName(rb.getString(DRIVER));
            connection = DriverManager.getConnection(rb.getString(URL),
                    rb.getString(SQL_LOGIN), rb.getString(SQL_PASSWORD));
        } catch (ClassNotFoundException ex){
            logger.warn("Driver Class not found", ex);
        }
        catch (SQLException ex) {
            logger.warn("Error get Connection", ex);
        }
        return connection;
    }

    public synchronized Connection retrieve() throws ConnectionPoolException{
        Connection newConn = null;
        if (freeConnections.size() == 0){
            newConn = getConnection();
        } else {
            newConn = freeConnections.poll();
        }
        usedConnections.add(newConn);
        if (newConn == null){
            throw new ConnectionPoolException();
        }
        return newConn;
    }

    public synchronized void putback(Connection connection) throws NullPointerException{
        if (connection!=null){
            if (usedConnections.remove(connection)){
                freeConnections.offer(connection);
            } else {
                throw new NullPointerException();
            }
        }
    }


    public int getFreeConnectionsCount() {
        return freeConnections.size();
    }

}
