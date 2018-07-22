package by.epam.java.training.dao.transaction;

import by.epam.java.training.dao.*;
import by.epam.java.training.dao.impl.*;
import by.epam.java.training.dao.transaction.impl.ModeratorTransactionImpl;
import by.epam.java.training.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionFactory {
    private static final Logger logger = Logger.getLogger(DAOFactory.class);

    private static TransactionFactory instance = new TransactionFactory();
    private static Lock lock = new ReentrantLock();


    private final ModeratorTransaction moderatorTransaction = new ModeratorTransactionImpl();


    private TransactionFactory() {}


    public static ModeratorTransaction getModeratorTransaction(){
        return getInstance().moderatorTransaction;
    }


    private static TransactionFactory getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new TransactionFactory();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("=(", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }
}
