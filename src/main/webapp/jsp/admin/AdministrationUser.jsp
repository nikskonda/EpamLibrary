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
    <fmt:message bundle="${loc}" key="local.lable.date.value" var="date" />

    <fmt:message bundle="${loc}" key="local.header.changeRole" var="changeRole" />
    <fmt:message bundle="${loc}" key="local.header.deleteUser" var="deleteUser" />

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
    <fmt:message bundle="${loc}" key="local.message.error.login.not_found" var="notFound" />
<fmt:message bundle="${loc}" key="local.message.error.password.length" var="pwLen" />
<fmt:message bundle="${loc}" key="local.page.title.administrationUser" var="user" />
<html>
<head>
    <title>${user}</title>

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

        <div class="row" >
            <div style="width: 50%; margin: 0 auto;">

                <div>
                    <label for="login">${login}</label>
                    <input type="text" class="full-width" name="login" id="login"  readonly="true"
                           value="<c:out value="${requestScope.profile.login}"/>"/>
                </div>
                <div>
                    <label for="firstName">${firstName}</label>
                    <input type="text" class="full-width" name="firstName" id="firstName" readonly="true"
                           value="<c:out value="${requestScope.profile.firstName}"/>"/>
                </div>
                <div>
                    <label for="lastName">${lastName}</label>
                    <input type="text" class="full-width" name="lastName" id="lastName" readonly="true"
                           value="<c:out value="${requestScope.profile.lastName}"/>"/>
                </div>
                <div>
                    <label for="email">${email}</label>
                    <input type="text" class="full-width" name="email" id="email" readonly="true"
                           value="<c:out value="${requestScope.profile.email}"/>"/>

                </div>
                <div>
                    <label for="date">${date}</label>
                    <input type="date" class="full-width" name="date" id="date" readonly="true"
                           value="<c:out value="${requestScope.profile.registrationDate}"/>"/>

                </div>


            </div>

        </div>
        <div class="row">
            <div class="col-lg-6">
                <h1>${changeRole}</h1>
                <form method="post" action="/admin" >
                    <input type="hidden" name="command" value="change_user_role"/>
                    <input type="hidden" name="user_id" value="<c:out value="${requestScope.profile.id}"/>"/>
                    <c:forEach var="role" items="${requestScope.roles}">
                        <p><input type="radio" name="role" value="<c:out value="${role.name}"/>"
                        <c:if test="${role.name == requestScope.profile.role.name}">
                            <c:out value="checked"/>
                        </c:if> > ${role.name} </p>
                    </c:forEach>
                    <label for="password">${password}</label>
                    <input type="password" name="password" id="password" class="full-width" placeholder="${ioldPassword}" required>
                    <p class="error-input">
                        <c:if test="${requestScope.error_exist == true}">
                            ${notFound}
                        </c:if>
                    </p>
                    <input type="submit" value="Apply" >
                </form>
            </div>
                 <div class="col-lg-6">
                     <h1>${deleteUser}</h1>
                        <form method="post" action="/admin" onsubmit="return isValidPasswordForm()">
                            <input type="hidden" name="command" value="delete_user"/>
                            <input type="hidden" name="user_id" value="<c:out value="${requestScope.profile.id}"/>"/>
                            <label for="passwordField">${password}</label>
                            <input type="password" id="passwordField" name="password" class="full-width" placeholder="${ioldPassword}" required>
                            <p class="error-input" id="passwordError">
                                <c:if test="${error_exist == true}">
                                    ${notFound}
                                </c:if>
                            </p>
                            <input type="submit" value="Delete">
                            </form>
                        </div>
        </div>


    </div>


</section>
<jsp:include page="../Footer.jsp"/>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "passwordLengthError":"${pwLen}"
        }
    ;
</script>
<script src="../../js/validator.js"></script>
</body>
</html>
