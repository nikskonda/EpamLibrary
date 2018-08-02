<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/Pagination.tld" prefix="pag" %>
<%@ taglib uri="/WEB-INF/tld/ItemsPerPage.tld" prefix="ipp" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.button.goToBookmark.name" var="goToBookmark" />
<fmt:message bundle="${loc}" key="local.button.delBookmark.name" var="delBookmark" />
<fmt:message bundle="${loc}" key="local.news.error.notFound" var="newsNotFound" />

<html>
<head>
    <title>Catalog</title>

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
                <c:choose>
                    <c:when test="${requestScope.books.size()>0}">
                        <c:forEach var="book" items="${requestScope.books}">
                            <div class="row">
                                <a href="/book?command=take_book&book_id=${book.id}">
                                    <div class="col-lg-3 offset-lg-1">
                                        <img class="entry__thumb" src="../<c:out value="${book.getCoverUrl()}"/>" alt="" style="height: 300px">
                                    </div>
                                </a>
                                <div class="col-lg-7 offset-lg-1">
                                    <a href="/book?command=take_book&book_id=${book.id}">
                                        <div >
                                            <h3><c:out value="${book.getName()}"/></h3>
                                            <p><c:out value="${book.getPublishYear()}"/></p>
                                            <p><c:out value="${book.getAuthors()}"/></p>
                                        </div>
                                    </a>
                                    <div class="row">
                                        <div style="float: right">
                                            <form method="post" action="/book">
                                                <input type="hidden" name="command" value="go_to_bookmark">
                                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                                <button type="submit">${goToBookmark}</button>
                                            </form>
                                        </div>
                                        <div>
                                            <form method="post" action="/book">
                                                <input type="hidden" name="command" value="delete_bookmark">
                                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                                <input type="hidden" name="numberOfPage" value="${requestScope.numberOfPage}">
                                                <button type="submit">${delBookmark}</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p>${newsNotFound}</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <pag:pagination url="book" command="take_list_of_bookmarks" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
            <ipp:itemPerPage url="book" command="take_list_of_bookmarks" items="countBookmarks" currentCount="${sessionScope.countBookmarks}"/>

    </section> <!-- s-content -->
    <jsp:include page="../Footer.jsp"/>

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/main.js"></script>
    </body>
</html>
