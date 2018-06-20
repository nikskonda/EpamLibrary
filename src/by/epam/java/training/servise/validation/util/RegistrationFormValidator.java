package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.RegistrationForm;
import by.epam.java.training.servise.validation.Validator;

public class RegistrationFormValidator implements Validator {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 20;

    private static final int FIRST_NAME_MIN_LENGTH = 3;
    private static final int FIRST_NAME_MAX_LENGTH = 20;

    private static final int LAST_NAME_MIN_LENGTH = 3;
    private static final int LAST_NAME_MAX_LENGTH = 20;


    private static final String REGEXP_NAME = "^[a-zA-Z][A-Za-z\\s]+[A-Za-z]$";
    private static final String REGEXP_EMAIL = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";

    @Override
    public boolean isValid(Object obj) {

        if (RegistrationForm.class != obj.getClass()){
            return false;
        }

        RegistrationForm regForm = (RegistrationForm) obj;

        if (!new LoginValidator().isValid(regForm.getLogin())){
            return false;
        }

        if (regForm.getPassword().length() < PASSWORD_MIN_LENGTH ||
                regForm.getPassword().length()>PASSWORD_MAX_LENGTH){
            return false;
        }

        if (!regForm.getPassword().equals(regForm.getConfirmPassword())){
            return false;
        }

        if (regForm.getFirstName().length() < FIRST_NAME_MIN_LENGTH ||
                regForm.getFirstName().length()>FIRST_NAME_MAX_LENGTH){
            return false;
        }
        if (!regForm.getFirstName().matches(REGEXP_NAME)){
            return false;
        }

        if (regForm.getLastName().length() < LAST_NAME_MIN_LENGTH ||
                regForm.getLastName().length()>LAST_NAME_MAX_LENGTH){
            return false;
        }
        if (!regForm.getLastName().matches(REGEXP_NAME)){
            return false;
        }

        if (!regForm.getEmail().matches(REGEXP_EMAIL)){
            return false;
        }

        return true;
    }
}
