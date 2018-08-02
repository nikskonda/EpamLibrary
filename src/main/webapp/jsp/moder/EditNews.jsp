<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />

<fmt:message bundle="${loc}" key="local.news.lable.title" var="title" />
<fmt:message bundle="${loc}" key="local.news.lable.text" var="text" />
<fmt:message bundle="${loc}" key="local.news.lable.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.news.lable.thumbs" var="thumbs" />

<fmt:message bundle="${loc}" key="local.news.placeholder.title" var="ititle" />
<fmt:message bundle="${loc}" key="local.news.placeholder.text" var="itext" />

<fmt:message bundle="${loc}" key="local.news.button.addNews" var="addNews" />
<fmt:message bundle="${loc}" key="local.news.button.editNews" var="editNews" />
<fmt:message bundle="${loc}" key="local.news.button.deleteNews" var="deleteNews" />

<fmt:message bundle="${loc}" key="local.news.message.en" var="headerEN" />
<fmt:message bundle="${loc}" key="local.news.message.ru" var="headerRU" />

<fmt:message bundle="${loc}" key="local.news.error.title.length" var="titleLengthError" />
<fmt:message bundle="${loc}" key="local.news.error.text.lenght" var="textLengthError" />
<fmt:message bundle="${loc}" key="local.news.error.photo.content" var="photoError" />
<fmt:message bundle="${loc}" key="local.news.error.thumbs.content" var="thumbsError" />
<fmt:message bundle="${loc}" key="local.message.error.login.not_found" var="notFound" />
<fmt:message bundle="${loc}" key="local.message.error.password.length" var="pwLen" />
<fmt:message bundle="${loc}" key="local.profile.oldPassword.value" var="ioldPassword" />
<fmt:message bundle="${loc}" key="local.message.error.edit" var="editError" />
<html>
<head>
    <title>Error</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" charset="utf-8">

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/base.css">
    <link rel="stylesheet" href="../../css/vendor.css">
    <link rel="stylesheet" href="../../css/main.css">

    <!-- script
    ================================================== -->
    <script src="../../js/modernizr.js"></script>
    <script src="../../js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="icon" href="../../favicon.ico" type="image/x-icon">
</head>
<body>
<jsp:include page="../Header.jsp"/>
<section class="s-content">
    <div class="content">
        <div class="row">

            <p style="color: red">
                <c:if test="${error_edit == true}">
                    ${editError}
                </c:if>
            </p>

            <form action="/moderator" method="POST" enctype="multipart/form-data" onsubmit="return isValidNewsForm()">
                <input type="hidden" name="command" value="edit_news">
                <input type="hidden" name="news_id" value="<c:out value="${requestScope.news.getId()}"/>" >

                <div>
                    <div class="col-lg-6">
                        <div>
                            <h3>${headerEN}</h3>
                        </div>

                        <div>
                            <label for="news_title">${title}</label>
                            <input type="text" id="news_title" name="news_title" placeholder="${ititle}"  value="<c:out value="${requestScope.news.getTitle()}"/>" class="full-width" required>
                            <p class="error-input" id="titleError"></p>
                        </div>

                        <div>
                            <label for="news_text">${text}</label>
                            <textarea class="add_book_descr full-width" placeholder="${itext}" id="news_text" name="news_text" required><c:out value="${requestScope.news.getText()}"/></textarea>
                            <p class="error-input" id="textError"></p>
                        </div>

                    </div>

                    <div class="col-lg-6">
                        <input type="hidden" name="news_lang" value="ru" >

                        <div>
                            <h3>${headerRU}</h3>
                        </div>

                        <div>
                            <label for="news_title_ru">${title}</label>
                            <input type="text" id="news_title_ru" name="news_title_ru" placeholder="${ititle}"  value="<c:out value="${requestScope.newsRU.getTitle()}"/>" class="full-width" required>
                            <p class="error-input" id="titleRuError"></p>
                        </div>

                        <div>
                            <label for="news_text_ru">${text}</label>
                            <textarea class="add_book_descr full-width" placeholder="${itext}" id="news_text_ru" name="news_text_ru" required><c:out value="${requestScope.newsRU.getText()}"/></textarea>
                            <p class="error-input" id="textRuError"></p>
                        </div>
                    </div>
                </div>

                <div style="width: 50%; margin: 0 auto;">
                    <div>
                        <label for="news_photo_url">${photo}</label>
                        <input type="file" id="news_photo_url" name="news_photo_url" class="full-width" multiple="false" content="1234"  required>
                        <p class="error-input" id="photoError"></p>
                    </div>

                    <div>
                        <label for="news_thumbs_url">${thumbs}</label>
                        <input type="file" id="news_thumbs_url" name="news_thumbs_url" class="full-width" multiple="false" required>
                        <p class="error-input" id="thumbsError"></p>
                    </div>

                    <div>
                        <label for="password">${password}</label>
                        <input type="password" id="password" name="password" class="full-width" placeholder="${ioldPassword}" required>
                        <p class="error-input" id="pwError">
                            <c:if test="${error_exist == true}">
                                ${notFound}
                            </c:if>
                        </p>
                    </div>

                    <input class="add_book_button full-width" type="submit" value="${editNews}">
                </div>

            </form>
        </div>
        <div class="row">
            <form action="/moderator" method="POST" enctype="multipart/form-data" onsubmit="return isValidPasswordForm()">
                <input type="hidden" name="command" value="delete_news">
                <input type="hidden" name="news_id" value="<c:out value="${requestScope.news.getId()}"/>" >

                <div>
                    <label for="passwordField">${password}</label>
                    <input type="password" id="passwordField" name="password" class="full-width" placeholder="${ioldPassword}" required>
                    <p class="error-input" id="passwordError">
                        <c:if test="${error_exist == true}">
                            ${notFound}
                        </c:if>
                    </p>
                </div>

                <input class="add_book_button full-width" type="submit" value="${deleteNews}">
            </form>
        </div>
    </div>
</section>


<jsp:include page="../Footer.jsp"/>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "titleLengthError":"${titleLengthError}",
            "textLengthError":"${textLengthError}",
            "photoError":"${photoError}",
            "thumbsError":"${thumbsError}",
            "passwordLengthError":"${pwLen}"
        }
    ;
</script>
<script src="../../js/validator.js"></script>

</body>
</html>
