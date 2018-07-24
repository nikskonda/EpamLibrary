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

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.catalog.title" var="title" />
<fmt:message bundle="${loc}" key="local.message.catalog.header" var="headerP" />


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
                                <a href="/book?command=open_book&book_id=${book.id}">
                                    <div class="col-lg-3 offset-lg-1">
                                        <img class="entry__thumb" src="../<c:out value="${book.getCoverUrl()}"/>" alt="" style="height: 300px">
                                    </div>
                                </a>
                                <div class="col-lg-7 offset-lg-1">
                                    <a href="/book?command=open_book&book_id=${book.id}">
                                        <div >
                                            <h3><c:out value="${book.getName()}"/></h3>
                                            <p><c:out value="${book.getPublishYear()}"/></p>
                                            <p><c:out value="${book.getAuthors()}"/></p>
                                        </div>
                                    </a>
                                    <div class="row">
                                        <div style="float: right">
                                            <form method="post" action="/book">
                                                <input type="hidden" name="command" value="open_bookmark">
                                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                                <button type="submit">Open Bookmark</button>
                                            </form>
                                        </div>
                                        <div>
                                            <form method="post" action="/book">
                                                <input type="hidden" name="command" value="delete_bookmark">
                                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                                <input type="hidden" name="numberOfPage" value="${requestScope.numberOfPage}">
                                                <button type="submit">Delete Bookmark</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p>закладок нет, сорян</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="row">
                <div class="col-full">
                    <nav class="pgn">
                        <ul>
                            <c:if test="${requestScope.numberOfPage!=1}">
                                <li><a class="pgn__num" href="/news?command=take_list_of_bookmarks&numberOfPage=1">First: 1</a></li>
                            </c:if>
                            <c:if test="${requestScope.numberOfPage>1}">
                                <li><a class="pgn__prev" href="/news?command=take_list_of_bookmarks&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
                            </c:if>
                            <%--<c:if test="${requestScope.numberOfPage>1}">--%>
                            <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
                            <%--</c:if>--%>
                            <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                <li><a class="pgn__next" href="/news?command=take_list_of_bookmarks&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
                            </c:if>
                            <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                                <li><a class="pgn__num" href="/news?command=take_list_of_bookmarks&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
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
                                    <c:when test="${i == sessionScope.countBookmarks}">
                                        <li><a class="pgn__num current" href="/news?command=take_list_of_bookmarks&countBookmarks=${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="pgn__num" href="/news?command=take_list_of_bookmarks&countBookmarks=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </section> <!-- s-content -->
    <jsp:include page="../Footer.jsp"/>

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/main.js"></script>
    </body>
</html>
