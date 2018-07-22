package by.epam.java.training.dao.transaction;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractTransaction extends AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractTransaction.class);


    protected void rollback(Connection con){
        try
        {
            con.rollback ();
            con.setAutoCommit (true);
        }
        catch (SQLException sqlEx) {
            logger.warn("Rolling back failed.", sqlEx);
        }
    }
}
