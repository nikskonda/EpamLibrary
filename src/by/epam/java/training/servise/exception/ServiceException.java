package by.epam.java.training.servise.exception;

public class ServiceException extends Exception{

    public ServiceException() {
    }

    public ServiceException(String s) {
        super(s);
    }
    
    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }


}
