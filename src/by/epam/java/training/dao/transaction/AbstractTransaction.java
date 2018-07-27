package by.epam.java.training.dao.transaction;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractTransaction extends AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractTransaction.class);

    protected void startTransaction(Connection con) throws TransactionException {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex){
            throw new TransactionException("Start transaction failed.", ex);
        }
    }

    protected void closeTransaction(Connection con) throws TransactionException{
        try {
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex){
            throw new TransactionException("Close transaction failed.", ex);
        }

    }

    protected void rollback(Connection con) throws TransactionException {
        try
        {
            con.rollback ();
            con.setAutoCommit (true);
        }
        catch (SQLException ex) {
            throw new TransactionException("Rolling back failed.", ex);
        }
    }
}
