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

        <div class="row narrow">
            <form method="post" action="/admin">
                <input type="hidden" name="command" value="find_users">
                <input type="text" name="search" value="<c:out value="${requestScope.search}"/>">
                <button type="submit">Find me plz</button>
            </form>
        </div>

        <div class="row add-bottom">

            <div class="col-twelve">

                <h3>Users</h3>


                <div class="table-responsive">

                    <table>
                        <thead>
                        <tr>
                            <th>Login</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
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
                                                <td><a style="color: limegreen" href="/profile?command=open_profile">Profile</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a style="color: red" href="/admin?command=open_user&user_id=<c:out value="${user.id}"/>">Administration</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>users нет, сорян</p>
                            </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>

                </div>

            </div>

        </div> <!-- end row -->

        <c:choose>
            <c:when test="${not empty requestScope.search}">
                <div class="row">
                    <div class="col-full">
                        <nav class="pgn">
                            <ul>
                                <c:if test="${requestScope.numberOfPage!=1}">
                                    <li><a class="pgn__num" href="/admin?command=find_users&search=${requestScope.search}&numberOfPage=1">First: 1</a></li>
                                </c:if>
                                <c:if test="${requestScope.numberOfPage>1}">
                                    <li><a class="pgn__prev" href="/admin?command=find_users&search=${requestScope.search}&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
                                </c:if>
                                <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
                                <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                    <li><a class="pgn__next" href="/admin?command=find_users&search=${requestScope.search}&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
                                </c:if>
                                <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                    <li><a class="pgn__num" href="/admin?command=find_users&search=${requestScope.search}&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="col-full">
                        <nav class="pgn">
                            <ul>
                                <c:forEach var="i" begin="8" end="32" step="4">
                                    <c:choose>
                                        <c:when test="${i == sessionScope.countUsers}">
                                            <li><a class="pgn__num current" href="/admin?command=find_users&search=${requestScope.search}&countUsers=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="pgn__num" href="/admin?command=find_users&search=${requestScope.search}&countUsers=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-full">
                        <nav class="pgn">
                            <ul>
                                <c:if test="${requestScope.numberOfPage!=1}">
                                    <li><a class="pgn__num" href="/admin?command=show_user_list&numberOfPage=1">First: 1</a></li>
                                </c:if>
                                <c:if test="${requestScope.numberOfPage>1}">
                                    <li><a class="pgn__prev" href="/admin?command=show_user_list&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
                                </c:if>
                                <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
                                <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                    <li><a class="pgn__next" href="/admin?command=show_user_list&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
                                </c:if>
                                <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                    <li><a class="pgn__num" href="/admin?command=show_user_list&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="col-full">
                        <nav class="pgn">
                            <ul>
                                <c:forEach var="i" begin="8" end="32" step="4">
                                    <c:choose>
                                        <c:when test="${i == sessionScope.countUsers}">
                                            <li><a class="pgn__num current" href="/admin?command=show_user_list&countUsers=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="pgn__num" href="/admin?command=show_user_list&countUsers=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</section>

<jsp:include page="../Footer.jsp"/>
</body>
</html>
