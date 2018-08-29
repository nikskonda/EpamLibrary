function isValidSignUpForm() {
    var result = true;

    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");
    var confirmPasswordField = document.getElementById("confirmPassword");
    var firstNameField = document.getElementById("firstName");
    var lastNameField = document.getElementById("lastName");
    var emailField = document.getElementById("email");

    var loginError = document.getElementById("loginError");
    var passwordError = document.getElementById("passwordError");
    var confirmPasswordError = document.getElementById("confirmPasswordError");
    var firstNameError = document.getElementById("firstNameError");
    var lastNameError = document.getElementById("lastNameError");
    var emailError = document.getElementById("emailError");


    var loginMinLength = 3, loginMaxLength = 20;
    var passwordMinLength = 6, passwordMaxLength = 20;
    var firstNameMinLength = 3, firstNameMaxLength = 20;
    var lastNameMinLength = 3, lastNameMaxLength = 20;

    var REGEXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]$";
    var REGEXP_NAME = "^[a-zA-ZА-Яа-я][A-Za-zА-Яа-я\\s]+[A-Za-zА-Яа-я]$";
    var REGEXP_EMAIL = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";
    var borderColorRed = "#F54D4D";

    if(loginField.value.length >= loginMinLength && loginField.value.length <= loginMaxLength){
        if (loginField.value.search(REGEXP_LOGIN) === -1){
            result=false;
            loginField.borderColor = borderColorRed;
            loginError.innerText = validationErrorMessages.loginContentError;
        } else{
            clear(loginError);
        }
    } else {
        result = false;
        loginField.borderColor = borderColorRed;
        loginError.innerText = validationErrorMessages.loginLengthError;
    }

    if(passwordField.value.length < passwordMinLength || passwordField.value.length > passwordMaxLength) {
        result = false;
        passwordField.borderColor = borderColorRed;
        passwordError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(passwordError);
    }

    if (passwordField.value !== confirmPasswordField.value){
        result = false;
        confirmPasswordField.borderColor = borderColorRed;
        confirmPasswordError.innerText = validationErrorMessages.confirmPasswordError;
    }else{
        clear(confirmPasswordError);
    }

    if(firstNameField.value.length >= firstNameMinLength && firstNameField.value.length <= firstNameMaxLength){
        if (firstNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            firstNameField.borderColor = borderColorRed;
            firstNameError.innerText = validationErrorMessages.firstNameContentError;
        }else{
            clear(firstNameError);
        }
    } else {
        result = false;
        firstNameField.borderColor = borderColorRed;
        firstNameError.innerText = validationErrorMessages.firstNameLengthError;
    }

    if(lastNameField.value.length >= lastNameMinLength && lastNameField.value.length <= lastNameMaxLength){
        if (lastNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            lastNameField.borderColor = borderColorRed;
            lastNameError.innerText = validationErrorMessages.lastNameContentError;
        }else{
            clear(lastNameError);
        }
    } else {
        result = false;
        lastNameField.borderColor = borderColorRed;
        lastNameError.innerText = validationErrorMessages.lastNameLengthError;
    }

    if (emailField.value.search(REGEXP_EMAIL) === -1){
        result = false;
        emailField.borderColor = borderColorRed;
        emailError.innerText = validationErrorMessages.emailError;
    }else{
        clear(emailError);
    }

    return result;
}

function isValidSignInForm() {
    var result = true;

    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");

    var loginError = document.getElementById("loginError");
    var passwordError = document.getElementById("passwordError");

    var loginMinLength = 3, loginMaxLength = 20;
    var passwordMinLength = 6, passwordMaxLength = 20;

    var REGEXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]$";
    var borderColorRed = "#F54D4D";

    if(loginField.value.length >= loginMinLength && loginField.value.length <= loginMaxLength){
        if (loginField.value.search(REGEXP_LOGIN) === -1){
            result=false;
            loginField.borderColor = borderColorRed;
            loginError.innerText = validationErrorMessages.loginContentError;
        }else{
            clear(loginError);
        }
    } else {
        result = false;
        loginField.borderColor = borderColorRed;
        loginError.innerText = validationErrorMessages.loginLengthError;
    }

    if(passwordField.value.length < passwordMinLength || passwordField.value.length > passwordMaxLength) {
        result = false;
        passwordField.borderColor = borderColorRed;
        passwordError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(passwordError);
    }

    return result;
}

function isValidProfileForm() {
    var result = true;

    var oldPasswordField = document.getElementById("oldPassword");
    var newPasswordField = document.getElementById("newPassword");
    var confirmPasswordField = document.getElementById("confirmPassword");
    var firstNameField = document.getElementById("firstName");
    var lastNameField = document.getElementById("lastName");
    var emailField = document.getElementById("email");

    var oldPasswordError = document.getElementById("oldPasswordError");
    var newPasswordError = document.getElementById("newPasswordError");
    var confirmPasswordError = document.getElementById("confirmPasswordError");
    var firstNameError = document.getElementById("firstNameError");
    var lastNameError = document.getElementById("lastNameError");
    var emailError = document.getElementById("emailError");


    var passwordMinLength = 6, passwordMaxLength = 20;
    var firstNameMinLength = 3, firstNameMaxLength = 20;
    var lastNameMinLength = 3, lastNameMaxLength = 20;

    var REGEXP_NAME = "^[a-zA-ZА-Яа-я][A-Za-zА-Яа-я\\s]+[A-Za-zА-Яа-я]$";
    var REGEXP_EMAIL = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";
    var borderColorRed = "#F54D4D";

    if(oldPasswordField.value.length < passwordMinLength || oldPasswordField.value.length > passwordMaxLength) {
        result = false;
        oldPasswordField.borderColor = borderColorRed;
        oldPasswordError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(oldPasswordError);
    }

    if (!(isEmpty(newPasswordField.value) && isEmpty(confirmPasswordField.value))) {

        if (newPasswordField.value.length < passwordMinLength || newPasswordField.value.length > passwordMaxLength) {
            result = false;
            newPasswordField.borderColor = borderColorRed;
            newPasswordError.innerText = validationErrorMessages.passwordLengthError;
        }else{
            clear(newPasswordError);
        }

        if (newPasswordField.value !== confirmPasswordField.value) {
            result = false;
            confirmPasswordField.borderColor = borderColorRed;
            confirmPasswordError.innerText = validationErrorMessages.confirmPasswordError;
        }else{
            clear(confirmPasswordError);
        }
    }

    if(firstNameField.value.length >= firstNameMinLength && firstNameField.value.length <= firstNameMaxLength){
        if (firstNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            firstNameField.borderColor = borderColorRed;
            firstNameError.innerText = validationErrorMessages.firstNameContentError;
        }else{
            clear(firstNameError);
        }
    } else {
        result = false;
        firstNameField.borderColor = borderColorRed;
        firstNameError.innerText = validationErrorMessages.firstNameLengthError;
    }

    if(lastNameField.value.length >= lastNameMinLength && lastNameField.value.length <= lastNameMaxLength){
        if (lastNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            lastNameField.borderColor = borderColorRed;
            lastNameError.innerText = validationErrorMessages.lastNameContentError;
        }else{
            clear(lastNameError);
        }
    } else {
        result = false;
        lastNameField.borderColor = borderColorRed;
        lastNameError.innerText = validationErrorMessages.lastNameLengthError;
    }

    if (emailField.value.search(REGEXP_EMAIL) === -1){
        result = false;
        emailField.borderColor = borderColorRed;
        emailError.innerText = validationErrorMessages.emailError;
    }else{
        clear(emailError);
    }

    return result;
}

function isValidNewsForm() {
    var result = true;

    var title = document.getElementById("news_title");
    var text = document.getElementById("news_text");
    var titleRu = document.getElementById("news_title_ru");
    var textRu = document.getElementById("news_text_ru");
    var photo = document.getElementById("news_photo_url");
    var thumbs = document.getElementById("news_thumbs_url");
    var password = document.getElementById("password");

    var titleError = document.getElementById("titleError");
    var titleRuError = document.getElementById("titleRuError");
    var textError = document.getElementById("textError");
    var textRuError = document.getElementById("textRuError");
    var photoError = document.getElementById("photoError");
    var thumbsError = document.getElementById("thumbsError");
    var pwError = document.getElementById("pwError");

    var passwordMinLength = 6, passwordMaxLength = 20;
    var titleMinLength = 6, titleMaxLength = 100;
    var textMinLength = 10;

    var DEFAULT_TEXT = "Enter text here ...";
    var DEFAULT_TEXT2 = "Введите английский текст здесь ...";
    var DEFAULT_TEXT_RU = "Введите текст здесь ...";
    var DEFAULT_TEXT_RU2 = "Enter russian text here ...";
    var REGEXP_JPG = "^.+\\.jpg$";
    var borderColorRed = "#F54D4D";


    if(password.value.length < passwordMinLength || password.value.length > passwordMaxLength) {
        result = false;
        pwError.borderColor = borderColorRed;
        pwError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(pwError);
    }

    if(title.value.length < titleMinLength || title.value.length > titleMaxLength) {
        result = false;
        title.borderColor = borderColorRed;
        titleError.innerText = validationErrorMessages.titleLengthError;
    }else{
        clear(titleError);
    }

    if(titleRu.value.length < titleMinLength || titleRu.value.length > titleMaxLength) {
        result = false;
        titleRu.borderColor = borderColorRed;
        titleRuError.innerText = validationErrorMessages.titleLengthError;
    }else{
        clear(titleRuError);
    }

    if(text.value.length < textMinLength || text.value.indexOf(DEFAULT_TEXT) !== -1 || text.value.indexOf(DEFAULT_TEXT2) !== -1) {
        result = false;
        text.borderColor = borderColorRed;
        textError.innerText = validationErrorMessages.textLengthError;
    }else{
        clear(textError);
    }

    if(textRu.value.length < textMinLength || textRu.value.indexOf(DEFAULT_TEXT_RU) !== -1 || textRu.value.indexOf(DEFAULT_TEXT_RU2) !== -1) {
        result = false;
        textRu.borderColor = borderColorRed;
        textRuError.innerText = validationErrorMessages.textLengthError;
    }else{
        clear(textRuError);
    }

    if(photo.files.length === 0 || photo.files[0].name.search(REGEXP_JPG) === -1) {
        result = false;
        photo.borderColor = borderColorRed;
        photoError.innerText = validationErrorMessages.photoError;
    }else{
        clear(photoError);
    }

    if(thumbs.files.length === 0 || thumbs.files[0].name.search(REGEXP_JPG) === -1) {
        result = false;
        thumbs.borderColor = borderColorRed;
        thumbsError.innerText = validationErrorMessages.thumbsError;
    }else{
        clear(thumbsError);
    }

    return result;
}

function isValidBookForm() {
    var result = true;

    var name = document.getElementById("name");
    var description = document.getElementById("description");
    var authors = document.getElementById("authors");
    var textUrl = document.getElementById("textUrl");
    var pdfUrl = document.getElementById("pdfUrl");

    var nameRu = document.getElementById("nameRU");
    var descriptionRu = document.getElementById("descriptionRU");
    var authorsRu = document.getElementById("authorsRU");
    var textUrlRu = document.getElementById("textUrlRU");
    var pdfUrlRu = document.getElementById("pdfUrlRU");

    var year = document.getElementById("year");
    var price = document.getElementById("price");
    var pages = document.getElementById("pages");
    var publishingHouse = document.getElementById("publishingHouse");
    var coverUrl = document.getElementById("coverUrl");

    var password = document.getElementById("password");

    var nameError = document.getElementById("nameError");
    var descriptionError = document.getElementById("descriptionError");
    var authorsError = document.getElementById("authorsError");
    var textError = document.getElementById("textError");
    var pdfError = document.getElementById("pdfError");

    var nameRuError = document.getElementById("nameRuError");
    var descriptionRuError = document.getElementById("descriptionRuError");
    var authorsRuError = document.getElementById("authorsRuError");
    var textRuError = document.getElementById("textRuError");
    var pdfRuError = document.getElementById("pdfRuError");

    var yearError = document.getElementById("yearError");
    var priceError = document.getElementById("priceError");
    var pagesError = document.getElementById("pagesError");
    var publishingHouseError = document.getElementById("publishingHouseError");
    var coverError = document.getElementById("coverError");
    var genresError = document.getElementById("genresError");
    var pwError = document.getElementById("pwError");


    var nameMinLength = 6, nameMaxLength = 100;
    var authorsMinLength = 3, authorsMaxLength = 100;
    var descMinLength = 10;
    var phMinLength = 3, phMaxLength = 50;
    var passwordMinLength = 6, passwordMaxLength = 20;

    var DEFAULT_DESC = "Enter english description here ...";
    var DEFAULT_DESC2 = "Введите описание на английском здесь ...";
    var DEFAULT_DESC_RU = "Введите описание на русском здесь ...";
    var DEFAULT_DESC_RU2 = "Enter russian description here ...";
    var REGEXP_TXT = "^.+\\.txt$";
    var REGEXP_PDF = "^.+\\.pdf$";
    var REGEXP_JPG = "^.+\\.jpg$";
    var borderColorRed = "#F54D4D";


    if(password.value.length < passwordMinLength || password.value.length > passwordMaxLength) {
        result = false;
        pwError.borderColor = borderColorRed;
        pwError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(pwError);
    }

    if(name.value.length < nameMinLength || name.value.length > nameMaxLength) {
        result = false;
        name.borderColor = borderColorRed;
        nameError.innerText = validationErrorMessages.nameLengthError;
    }else{
        clear(nameError);
    }

    if(nameRu.value.length < nameMinLength || nameRu.value.length > nameMaxLength) {
        result = false;
        nameRu.borderColor = borderColorRed;
        nameRuError.innerText = validationErrorMessages.nameLengthError;
    }else{
        clear(nameRuError);
    }

    if(description.value.length < descMinLength || description.value.indexOf(DEFAULT_DESC) !== -1 || description.value.indexOf(DEFAULT_DESC2) !== -1) {
        result = false;
        description.borderColor = borderColorRed;
        descriptionError.innerText = validationErrorMessages.descriptionContentError;
    }else{
        clear(descriptionError);
    }
    if(descriptionRu.value.length < descMinLength || descriptionRu.value.indexOf(DEFAULT_DESC_RU) !== -1 || descriptionRu.value.indexOf(DEFAULT_DESC_RU2) !== -1) {
        result = false;
        descriptionRu.borderColor = borderColorRed;
        descriptionRuError.innerText = validationErrorMessages.descriptionContentError;
    }else{
        clear(descriptionRuError);
    }

    if(authors.value.length < authorsMinLength || authors.value.length > authorsMaxLength) {
        result = false;
        authors.borderColor = borderColorRed;
        authorsError.innerText = validationErrorMessages.authorsLengthError;
    }else{
        clear(authorsError);
    }

    if(authorsRu.value.length < authorsMinLength || authorsRu.value.length > authorsMaxLength) {
        result = false;
        authorsRu.borderColor = borderColorRed;
        authorsRuError.innerText = validationErrorMessages.authorsLengthError;
    }else{
        clear(authorsRuError);
    }

    if(textUrl.files.length === 0 || textUrl.files[0].name.search(REGEXP_TXT) === -1) {
        result = false;
        textUrl.borderColor = borderColorRed;
        textError.innerText = validationErrorMessages.textUrlError;
    }else{
        clear(textError);
    }

    if(textUrlRu.files.length === 0 || textUrlRu.files[0].name.search(REGEXP_TXT) === -1) {
        result = false;
        textUrlRu.borderColor = borderColorRed;
        textRuError.innerText = validationErrorMessages.textUrlError;
    }else{
        clear(textRuError);
    }

    if(pdfUrl.files.length === 0 || pdfUrl.files[0].name.search(REGEXP_PDF) === -1) {
        result = false;
        pdfUrl.borderColor = borderColorRed;
        pdfError.innerText = validationErrorMessages.pdfUrlError;
    }else{
        clear(pdfError);
    }

    if(pdfUrlRu.files.length === 0 || pdfUrlRu.files[0].name.search(REGEXP_PDF) === -1) {
        result = false;
        pdfUrlRu.borderColor = borderColorRed;
        pdfRuError.innerText = validationErrorMessages.pdfUrlError;
    }else{
        clear(pdfRuError);
    }

    var date = new Date();
    if(year.value < 0 || year.value > date.getFullYear()) {
        result = false;
        year.borderColor = borderColorRed;
        yearError.innerText = validationErrorMessages.yearError;
    }else{
        clear(yearError);
    }

    if(price.value < 0) {
        result = false;
        price.borderColor = borderColorRed;
        priceError.innerText = validationErrorMessages.priceError;
    }else{
        clear(priceError);
    }
    if(pages.value < 0) {
        result = false;
        pages.borderColor = borderColorRed;
        pagesError.innerText = validationErrorMessages.pagesError;
    }else{
        clear(pagesError);
    }
    if(publishingHouse.value.length < phMinLength || publishingHouse.value.length > phMaxLength) {
        result = false;
        publishingHouse.borderColor = borderColorRed;
        publishingHouseError.innerText = validationErrorMessages.phLengthError;
    }else{
        clear(publishingHouseError);
    }

    if(coverUrl.files.length === 0 || coverUrl.files[0].name.search(REGEXP_JPG) === -1) {
        result = false;
        coverError.borderColor = borderColorRed;
        coverError.innerText = validationErrorMessages.coverError;
    }else{
        clear(coverError);
    }

    return result;
}

function isValidPasswordForm() {
    var result = true;

    var password = document.getElementById("passwordField");

    var pwError = document.getElementById("passwordError");

    var passwordMinLength = 6, passwordMaxLength = 20;

    var borderColorRed = "#F54D4D";


    if(password.value.length < passwordMinLength || password.value.length > passwordMaxLength) {
        result = false;
        pwError.borderColor = borderColorRed;
        pwError.innerText = validationErrorMessages.passwordLengthError;
    }else{
        clear(pwError);
    }

    return result;
}

function isValidSearchForm() {
    var result = true;

    var search = document.getElementById("search");

    var searchError = document.getElementById("searchError");

    var MinLength = 3, MaxLength = 20;
    var borderColorRed = "#F54D4D";

    if(search.value.length < MinLength || search.value.length > MaxLength) {
        result = false;
        searchError.borderColor = borderColorRed;
        searchError.innerText = validationErrorMessages.searchLengthError;
    }else{
        clear(searchError);
    }

    return result;
}

function clear(obj) {
    var EMPTY_STRING = "";
    obj.innerText = EMPTY_STRING;
}

function isEmpty(str) {
    if (str != null && typeof str !== "undefined") {
        str = str.trim();
    }
    if (!str) {
        return true;
    }
    return false;
}