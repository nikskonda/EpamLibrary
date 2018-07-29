package by.epam.training.servise.validation.util;

import by.epam.training.model.user.form.ProfileForm;
import by.epam.training.servise.validation.Validator;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;

/**
 * The class for checking profile form for validity.
 *
 * @author  Nikita Shkonda
 */
public class ProfileFormValidator implements Validator {

    private static final int PASSWORD_LENGTH = 32;

    /**
     * The method returns <tt>true<tt/> if the profile form is valid.
     *
     * @author  Nikita Shkonda
     *
     */
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
