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
    <body style="background-color: black">

    <jsp:include page="../Header.jsp"/>

    <section class="s-content">
        <div class="container">
            <form method="post" action="/book">
                <input type="hidden" name="command" value="set_bookmark">
                <input type="hidden" name="book_id" value="<c:out value="${requestScope.book_id}"/>">
                <input type="hidden" name="numberOfPage" value="<c:out value="${requestScope.numberOfPage}"/>">
                <button type="submit">Bookmark on current page</button>
            </form>
            <c:if test="${requestScope.set_bookmark_result != null}">
                <c:choose>
                    <c:when test="${requestScope.set_bookmark_result}">
                        Sucsess!
                    </c:when>
                    <c:otherwise>
                        Ou =( error
                    </c:otherwise>
                </c:choose>
            </c:if>

            <form method="post" action="/book">
                <input type="hidden" name="command" value="open_book">
                <input type="hidden" name="book_id" value="<c:out value="${book_id}"/>">
                <button type="submit">Back to book</button>
            </form>
            <div class="row">
                <c:choose>
                    <c:when test="${requestScope.text.size()>0}">
                        <c:forEach var="paragraph" items="${requestScope.text}">
                            <p><c:out value="${paragraph}"/></p>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>Happy end!</p>
                        <p>Thank you for using our service!</p>
                        <p>Good Luck</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="row">
                <div class="col-full">
                    <nav class="pgn">
                        <ul>
                            <c:if test="${requestScope.numberOfPage>1}">
                                <li><a class="pgn__prev" href="/book?command=read_book&book_id=${requestScope.book_id}&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
                            </c:if>
                            <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
                            <c:if test="${requestScope.text.size() > 0}">
                                <li><a class="pgn__next" href="/book?command=read_book&book_id=${requestScope.book_id}&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
                            </c:if>

                        </ul>
                    </nav>
                </div>
            </div>
        </div>



    </section> <!-- s-content -->

    <jsp:include page="../Footer.jsp"/>
    </body>
</html>
