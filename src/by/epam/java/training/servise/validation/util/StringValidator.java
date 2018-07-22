package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

public class StringValidator implements Validator {

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
