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
<%@ taglib uri="/WEB-INF/tld/Pagination.tld" prefix="pag" %>
<%@ taglib uri="/WEB-INF/tld/ItemsPerPage.tld" prefix="ipp" %>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />

    <fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
    <fmt:message bundle="${loc}" key="local.lable.firstName.value" var="firstName" />
    <fmt:message bundle="${loc}" key="local.lable.lastName.value" var="lastName" />
    <fmt:message bundle="${loc}" key="local.lable.email.value" var="email" />
    <fmt:message bundle="${loc}" key="local.lable.role.value" var="role" />
    <fmt:message bundle="${loc}" key="local.user.error.notFound" var="usersNotFound" />
    <fmt:message bundle="${loc}" key="local.button.profile.name" var="profile" />
    <fmt:message bundle="${loc}" key="local.button.admin.name" var="admin" />
    <fmt:message bundle="${loc}" key="local.button.find.name" var="find" />
    <fmt:message bundle="${loc}" key="local.search.error.length" var="searchError" />
    <fmt:message bundle="${loc}" key="local.page.title.userList" var="users" />
    <fmt:message bundle="${loc}" key="local.placeholder.search" var="search" />
<html>
<head>
    <title>${users}</title>

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

        <div class="row narrow">
            <form method="post" action="/admin" onsubmit="return isValidSearchForm()">
                <input type="hidden" name="command" value="find_list_of_users">
                <div class="book_sets">
                    <input type="text" name="search" value="<c:out value="${requestScope.search}"/>" placeholder="${search}" required style="width: 500px;">
                    <button type="submit">${find}</button>
                </div>
                <p class="error-input" id="searchError"></p>
            </form>
        </div>

        <div class="row add-bottom">

            <div class="col-twelve">

                <h3>Users</h3>


                <div class="table-responsive">

                    <table>
                        <thead>
                        <tr>
                            <th>${login}</th>
                            <th>${firstName}</th>
                            <th>${lastName}</th>
                            <th>${email}</th>
                            <th>${role}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${requestScope.user_list.size()>0}">
                                <c:forEach var="user" items="${requestScope.user_list}">
                                    <tr>
                                        <td><c:out value="${user.login}"/></td>
                                        <td><c:out value="${user.firstName}"/></td>
                                        <td><c:out value="${user.lastName}"/></td>
                                        <td><c:out value="${user.email}"/></td>
                                        <td><c:out value="${user.getRole().getName()}"/></td>
                                        <c:choose>
                                            <c:when test="${user.id == sessionScope.user.id}">
                                                <td><a style="color: limegreen" href="/profile?command=go_to_profile_form">${profile}</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a style="color: red" href="/admin?command=take_user&user_id=<c:out value="${user.id}"/>">${admin}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>${usersNotFound}</p>
                            </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>

                </div>

            </div>

        </div> <!-- end row -->

        <c:choose>
            <c:when test="${not empty requestScope.search}">
                <pag:pagination url="admin" command="find_list_of_users&search=${requestScope.search}&" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
                <ipp:itemPerPage url="admin" command="find_list_of_users&search=${requestScope.search}&" items="countUsers" currentCount="${sessionScope.countUsers}"/>
            </c:when>
            <c:otherwise>
                <pag:pagination url="admin" command="take_list_of_users" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
                <ipp:itemPerPage url="admin" command="take_list_of_users" items="countUsers" currentCount="${sessionScope.countUsers}"/>
            </c:otherwise>
        </c:choose>
    </div>

</section>

<jsp:include page="../Footer.jsp"/>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "searchLengthError":"${searchError}"
        }
    ;
</script>
<script src="../../js/validator.js"></script>
</body>
</html>
