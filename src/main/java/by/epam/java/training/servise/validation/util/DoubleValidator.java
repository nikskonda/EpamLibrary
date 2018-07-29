package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

/**
 * The class for checking double for validity.
 *
 * @author  Nikita Shkonda
 */
public class DoubleValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the double is valid.
     *
     * @author  Nikita Shkonda
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Double)){
            return false;
        }

        Double number = (Double) obj;

        if (number == null){
            return false;
        }
        if (number <= 0){
            return false;
        }

        return true;
    }
}
