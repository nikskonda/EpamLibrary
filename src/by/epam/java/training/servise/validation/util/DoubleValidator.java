package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

public class DoubleValidator implements Validator {

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
