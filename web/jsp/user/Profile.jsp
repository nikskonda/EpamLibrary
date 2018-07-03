<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />

    <fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
    <fmt:message bundle="${loc}" key="local.lable.password.value" var="password" />
    <fmt:message bundle="${loc}" key="local.lable.newPassword.value" var="newPassword" />
    <fmt:message bundle="${loc}" key="local.lable.confirmPassword.value" var="confirmPassword" />
    <fmt:message bundle="${loc}" key="local.lable.firstName.value" var="firstName" />
    <fmt:message bundle="${loc}" key="local.lable.lastName.value" var="lastName" />
    <fmt:message bundle="${loc}" key="local.lable.email.value" var="email" />

    <fmt:message bundle="${loc}" key="local.profile.oldPassword.value" var="ioldPassword" />
    <fmt:message bundle="${loc}" key="local.profile.newPassword.value" var="inewPassword" />
    <fmt:message bundle="${loc}" key="local.profile.confirmPassword.value" var="iconfirmPassword" />
    <fmt:message bundle="${loc}" key="local.profile.firstName.value" var="ifirstName" />
    <fmt:message bundle="${loc}" key="local.profile.lastName.value" var="ilastName" />
    <fmt:message bundle="${loc}" key="local.profile.email.value" var="iemail" />

    <fmt:message bundle="${loc}" key="local.message.error.password.not_match" var="passwordDoesNotMatch" />
    <fmt:message bundle="${loc}" key="local.message.error.password.length" var="pwLen" />
    <fmt:message bundle="${loc}" key="local.message.error.confirm_password.content" var="comfPwCont" />
    <fmt:message bundle="${loc}" key="local.message.error.first_name.content" var="fnCont" />
    <fmt:message bundle="${loc}" key="local.message.error.first_name.length" var="fnLen" />
    <fmt:message bundle="${loc}" key="local.message.error.last_name.content" var="lnCont" />
    <fmt:message bundle="${loc}" key="local.message.error.last_name.length" var="lnLen" />
    <fmt:message bundle="${loc}" key="local.message.error.email.content" var="emailCont" />
<html>
<head>
    <title>Profile</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

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
    <div class="container">
        <div class="row">
            <form method="post" action="/profile" onsubmit="return isValidProfileForm()">
                <input type="hidden" name="command" value="update_profile"/>
                <div>
                    <label for="login">${login}</label>
                    <input type="text" class="full-width" name="login" id="login"  readonly="true"
                           value="<c:out value="${requestScope.profile.login}"/>"/>
                </div>
                <div>
                    <label for="oldPassword">${password}</label>
                    <input type="password" class="full-width" name="oldPassword" id="oldPassword"  placeholder="${ioldPassword}"/>
                    <p class="error-input" id="oldPasswordError">
                        <c:if test="${error_match == true}">
                            ${passwordDoesNotMatch}
                        </c:if>
                    </p>
                </div>
                <div>
                    <label for="newPassword">${newPassword}</label>
                    <input type="password" class="full-width" name="newPassword" id="newPassword"  placeholder="${inewPassword}"/>
                    <p class="error-input" id="newPasswordError"></p>
                </div>
                <div>
                    <label for="confirmPassword">${confirmPassword}</label>
                    <input type="password" class="full-width" name="confirmPassword" id="confirmPassword"  placeholder="${iconfirmPassword}"/>
                    <p class="error-input" id="confirmPasswordError"></p>
                </div>
                <div>
                    <label for="firstName">${firstName}</label>
                    <input type="text" class="full-width" name="firstName" id="firstName"  placeholder="${ifirstName}"
                           value="<c:out value="${requestScope.profile.firstName}"/>"/>
                    <p class="error-input" id="firstNameError"></p>
                </div>
                <div>
                    <label for="lastName">${lastName}</label>
                    <input type="text" class="full-width" name="lastName" id="lastName"  placeholder="${ilastName}"
                           value="<c:out value="${requestScope.profile.lastName}"/>"/>
                    <p class="error-input" id="lastNameError"></p>
                </div>
                <div>
                    <label for="email">${email}</label>
                    <input type="text" class="full-width" name="email" id="email"  placeholder="${iemail}"
                           value="<c:out value="${requestScope.profile.email}"/>"/>
                    <p class="error-input" id="emailError"></p>
                </div>

                <input class="btn--primary full-width" type="submit" value="Apply">

            </form>
        </div>
    </div>

</section>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "passwordLengthError":"${pwLen}",
            "confirmPasswordError":"${comfPwCont}",
            "firstNameContentError":"${fnCont}",
            "firstNameLengthError":"${fnLen}",
            "lastNameContentError":"${lnCont}",
            "lastNameLengthError":"${lnLen}",
            "emailError":"${emailCont}"
        }
    ;
</script>
<script src="js/validator.js"></script>

</body>
</html>
