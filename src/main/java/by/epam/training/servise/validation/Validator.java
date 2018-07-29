package by.epam.training.servise.validation;

/**
 * The interface for checking objects for validity.
 *
 * @author  Nikita Shkonda
 */
public interface Validator {

    /**
     * The method returns <tt>true<tt/> if the object is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    boolean isValid(Object obj);

}
