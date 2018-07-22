package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.validation.Validator;

public class NaturalNumberValidator implements Validator {

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
