package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.validation.Validator;

public class IdValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof Integer)){
            return false;
        }

        Integer id = (Integer) obj;

        if (id == null){
            return false;
        }
        if (id<=0){
            return false;
        }

        return true;
    }
}
