package by.epam.training.servise.validation.util;

import by.epam.training.servise.validation.Validator;

/**
 * The class for checking string for validity.
 *
 * @author  Nikita Shkonda
 *
 */
public class StringValidator implements Validator {


    /**
     * The method returns <tt>true<tt/> if the string is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof String)){
            return false;
        }

        String str = (String) obj;

        if (str.isEmpty()){
            return false;
        }

        return true;
    }
}
