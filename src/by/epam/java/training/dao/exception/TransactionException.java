package by.epam.java.training.dao.exception;

public class TransactionException extends DAOException {

    public TransactionException() {
    }

    public TransactionException(String s) {
        super(s);
    }

    public TransactionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TransactionException(Throwable throwable) {
        super(throwable);
    }
}