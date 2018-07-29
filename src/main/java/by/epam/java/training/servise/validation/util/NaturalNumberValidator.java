package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

/**
 * The class for checking natural number for validity.
 *
 * @author  Nikita Shkonda
 */
public class NaturalNumberValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the natural number is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Integer)){
            return false;
        }

        Integer id = (Integer) obj;

        if (id<=0){
            return false;
        }

        return true;
    }
}
