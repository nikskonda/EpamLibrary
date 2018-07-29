package by.epam.training.servise.validation.util;

import by.epam.training.model.news.News;
import by.epam.training.servise.validation.Validator;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;

/**
 * The class for checking translated news for validity.
 *
 * @author  Nikita Shkonda
 *
 */
public class TranslatedNewsValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the translated news is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof News)){
            return false;
        }

        News news = (News) obj;

        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getTitle())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getText())){
            return false;
        }

        return true;
    }
}
