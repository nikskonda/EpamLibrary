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

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.catalog.title" var="title" />
<fmt:message bundle="${loc}" key="local.button.backToBook.name" var="backToBook" />
<fmt:message bundle="${loc}" key="local.button.setBookmark.name" var="setBookmark" />
<fmt:message bundle="${loc}" key="local.bookmark.message.success" var="success" />
<fmt:message bundle="${loc}" key="local.bookmark.message.error" var="error" />
<fmt:message bundle="${loc}" key="local.book.message.end" var="end" />
<fmt:message bundle="${loc}" key="local.book.message.thanks" var="thanks" />
<fmt:message bundle="${loc}" key="local.book.message.gookLuck" var="gookLuck" />
<fmt:message bundle="${loc}" key="local.page.title.readingRoom" var="rr" />

<html>
<head>
    <title>${rr}</title>

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
    <body style="background-color: black">

    <jsp:include page="../Header.jsp"/>

    <%--<button type="button" onclick="lightOff()">Off</button>--%>
    <%--<button type="button" onclick="lightOn()">On</button>--%>

    <section id="readingRoom" class="s-content">
        <div class="container">
            <div class="book_sets">
                <c:if test="${sessionScope.user != null}">
                    <form method="post" action="/book">
                        <input type="hidden" name="command" value="set_bookmark">
                        <input type="hidden" name="book_id" value="<c:out value="${requestScope.book_id}"/>">
                        <input type="hidden" name="numberOfPage" value="<c:out value="${requestScope.numberOfPage}"/>">
                        <button type="submit">${setBookmark}</button>
                    </form>
                    <c:if test="${requestScope.set_bookmark_result != null}">
                        <c:choose>
                            <c:when test="${requestScope.set_bookmark_result}">
                                ${success}
                            </c:when>
                            <c:otherwise>
                                ${error}
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:if>

                <form action="/book">
                    <input type="hidden" name="command" value="take_book">
                    <input type="hidden" name="book_id" value="<c:out value="${requestScope.book_id}"/>">
                    <input type="submit" value="${backToBook}">
                </form>
            </div>


            <div class="row">
                <c:choose>
                    <c:when test="${requestScope.text.size()>0}">
                        <c:forEach var="paragraph" items="${requestScope.text}">
                            <p><c:out value="${paragraph}"/></p>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>${end}</p>
                        <p>${thanks}</p>
                        <p>${gookLuck}</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <pag:pagination url="book" command="read_book&book_id=${requestScope.book_id}&" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
            <%--<div class="row">--%>
                <%--<div class="col-full">--%>
                    <%--<nav class="pgn">--%>
                        <%--<ul>--%>
                            <%--<c:if test="${requestScope.numberOfPage != 1}">--%>
                                <%--<li><a class="pgn__num" href="/book?command=read_book&book_id=${requestScope.book_id}&numberOfPage=1">First: 1</a></li>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${requestScope.numberOfPage > 1}">--%>
                                <%--<li><a class="pgn__prev" href="/book?command=read_book&book_id=${requestScope.book_id}&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>--%>
                            <%--</c:if>--%>
                            <%--<li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>--%>
                            <%--<c:if test="${requestScope.text.size() > 0}">--%>
                                <%--<li><a class="pgn__next" href="/book?command=read_book&book_id=${requestScope.book_id}&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${requestScope.numberOfPage<requestScope.totalPages}">--%>
                                <%--<li><a class="pgn__num" href="/news?command=read_book&book_id=${requestScope.book_id}&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>--%>
                            <%--</c:if>--%>
                        <%--</ul>--%>
                    <%--</nav>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>



    </section> <!-- s-content -->
    <jsp:include page="../Footer.jsp"/>
    <script src="../../js/main.js"></script>
    </body>
</html>
