package by.epam.java.training.dao.exception;

public class ConnectionPoolException extends DAOException{

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }
}
