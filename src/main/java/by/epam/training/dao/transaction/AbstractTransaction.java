package by.epam.training.dao.transaction;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.exception.TransactionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An abstract class that implements the basic methods of transactions.
 *
 * @author  Nikita Shkonda
 */
public abstract class AbstractTransaction extends AbstractDAO {

    private static final Logger logger = Logger.getLogger(AbstractTransaction.class);


    /**
     * The method starts the transaction.
     *
     * @param con - connection to database.
     *
     * @throws TransactionException  if there was an error in the beginning of
     * the transaction
     *
     */
    protected void startTransaction(Connection con) throws TransactionException {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex){
            throw new TransactionException("Start transaction failed.", ex);
        }
    }

    /**
     * The method closes the transaction.
     *
     * @param con - connection to database.
     *
     * @throws TransactionException  In the event of a transaction closing error
     *
     */
    protected void closeTransaction(Connection con) throws TransactionException{
        try {
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex){
            throw new TransactionException("Close transaction failed.", ex);
        }

    }

    /**
     * The method cancels the transaction.
     *
     * @param con - connection to database.
     *
     * @throws TransactionException  If an error occurred canceling a transaction
     *
     */
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
