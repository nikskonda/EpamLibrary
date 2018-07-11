package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

public class StringValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof String)){
            return false;
        }

        String str = (String) obj;

        if (str == null){
            return false;
        }

        if (str.isEmpty()){
            return false;
        }

        return true;
    }
}
