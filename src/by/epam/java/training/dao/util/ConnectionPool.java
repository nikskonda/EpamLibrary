package by.epam.java.training.dao.util;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final int INIT_COUNT_CONNECTIONS = 7;

    private static final String SQL_LOGIN = "root";
    private static final String SQL_PASSWORD = "password";
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private Queue<Connection> freeConnections = new LinkedList<>();
    private List<Connection> usedConnections = new LinkedList<>();


    public ConnectionPool() {
        for (int i = 0; i< INIT_COUNT_CONNECTIONS; i++){
            freeConnections.offer(getConnection());
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, SQL_LOGIN, SQL_PASSWORD);
        } catch (ClassNotFoundException ex){
            logger.warn("Driver Class not found", ex);
        }
        catch (SQLException ex) {
            logger.warn("Error get Connection", ex);
        }
        return connection;
    }

    public synchronized Connection retrieve() throws SQLException{
        Connection newConn = null;
        if (freeConnections.size() == 0){
            newConn = getConnection();
        } else {
            newConn = freeConnections.poll();
        }
        usedConnections.add(newConn);
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
