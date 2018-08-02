<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/Pagination.tld" prefix="pag" %>
<%@ taglib uri="/WEB-INF/tld/ItemsPerPage.tld" prefix="ipp" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
<fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
<fmt:message bundle="${loc}" key="local.button.read.name" var="read" />
<fmt:message bundle="${loc}" key="local.news.error.notFound" var="newsNotFound" />


<html>
  <head>
    <title>Index</title>


    <!-- mobile specific metas
    ================================================== -->
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


  </head>


  <body id="top">


  <jsp:include page="../Header.jsp"/>

  <section class="s-content">

    <div class="row masonry-wrap">
      <div class="masonry">

        <div class="grid-sizer"></div>

        <c:choose>
          <c:when test="${requestScope.news.size() > 0}">
            <c:forEach var="news" items="${requestScope.news}">
              <article class="masonry__brick entry format-standard" data-aos="fade-up">

                  <div class="entry__thumb">
                      <img src="..<c:out value="${news.thumbsUrl}"/>" alt="">
                  </div>

                  <div class="entry__text">
                    <div class="entry__header">

                      <div class="entry__date">
                        <fmt:formatDate type="both" pattern="HH:mm dd-MMM-yy" value="${news.publishDate}"/>
                      </div>
                      <div class="entry__date">
                        <p><c:out value="${news.getUserFirstName()}"/> <c:out value="${news.getUserLastName()}"/></p>
                      </div>
                      <h1 class="entry__title"><c:out value="${news.getTitle()}"/></h1>
                    </div>

                    <a class="btn btn--stroke full-width" href="/news?command=take_news&news_id=<c:out value="${news.getId()}"/>">${read}</a>

                  </div>
              </article> <!-- end article -->

            </c:forEach>
          </c:when>
          <c:otherwise>
            <p>${newsNotFound}</p>
          </c:otherwise>
        </c:choose>


      </div> <!-- end masonry -->
    </div> <!-- end masonry-wrap -->
    <pag:pagination url="news" command="take_list_of_news" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
    <ipp:itemPerPage url="news" command="take_list_of_news" items="countNews" currentCount="${sessionScope.countNews}"/>



  </section> <!-- s-content -->

  <jsp:include page="../Footer.jsp"/>

  <!-- Java Script
  ================================================== -->
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/plugins.js"></script>
  <script src="js/main.js"></script>
  </body>
</html>