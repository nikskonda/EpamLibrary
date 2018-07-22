package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class ProfileFormValidator implements Validator {

    private static final int PASSWORD_LENGTH = 32;

    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof ProfileForm)){
            return false;
        }

        ProfileForm profileForm = (ProfileForm) obj;

        if (!ValidatorManager.isValid(ValidatorType.SIGN_UP_FORM_VALIDATOR, profileForm)){
            return false;
        }
        if (profileForm.getPassword().length() != PASSWORD_LENGTH){
            return false;
        }

        return true;
    }
}
