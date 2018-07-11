<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Index</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
    <fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
    <fmt:message bundle="${loc}" key="local.button.catalog.name" var="catalog" />

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
          <c:when test="${requestScope.news.size()-1 > 0}">
            <c:forEach var="news" items="${requestScope.news}">
              <article class="masonry__brick entry format-standard" data-aos="fade-up">

                  <div class="entry__thumb">
                      <img src="<c:out value="${news.thumbsUrl}"/>" alt="">
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

                    <a class="btn btn--stroke full-width" href="/news?command=open_news&news_id=<c:out value="${news.getId()}"/>">Read</a>

                  </div>
              </article> <!-- end article -->

            </c:forEach>
          </c:when>
          <c:otherwise>
            <p>Ничего нового )</p>
          </c:otherwise>
        </c:choose>


      </div> <!-- end masonry -->
    </div> <!-- end masonry-wrap -->

    <div class="row">
      <div class="col-full">
        <nav class="pgn">
          <ul>
            <c:if test="${requestScope.numberOfPage!=1}">
              <li><a class="pgn__num" href="/news?command=open_news_list&numberOfPage=1">First: 1</a></li>
            </c:if>
            <c:if test="${requestScope.numberOfPage>1}">
              <li><a class="pgn__prev" href="/news?command=open_news_list&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
            </c:if>
            <%--<c:if test="${requestScope.numberOfPage>1}">--%>
              <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
            <%--</c:if>--%>
            <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
              <li><a class="pgn__next" href="/news?command=open_news_list&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
            </c:if>
            <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
              <li><a class="pgn__num" href="/news?command=open_news_list&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
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
                <c:when test="${i == sessionScope.countNews}">
                  <li><a class="pgn__num current" href="/news?command=open_news_list&countNews=${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                  <li><a class="pgn__num" href="/news?command=open_news_list&countNews=${i}">${i}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </ul>
        </nav>
      </div>
    </div>

  </section> <!-- s-content -->


  <!-- s-extra
  ================================================== -->
  <section class="s-extra">

    <div class="row top">

      <div class="col-eight md-six tab-full popular">
        <h3>Popular Posts</h3>

        <div class="block-1-2 block-m-full popular__posts">
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/wheel-150.jpg" alt="">
            </a>
            <h5><a href="#0">Visiting Theme Parks Improves Your Health.</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-19">Dec 19, 2017</time></span>
            </section>
          </article>
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/shutterbug-150.jpg" alt="">
            </a>
            <h5><a href="#0">Key Benefits Of Family Photography.</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-18">Dec 18, 2017</time></span>
            </section>
          </article>
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/cookies-150.jpg" alt="">
            </a>
            <h5><a href="#0">Absolutely No Sugar Oatmeal Cookies.</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-16">Dec 16, 2017</time></span>
            </section>
          </article>
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/beetle-150.jpg" alt="">
            </a>
            <h5><a href="#0">Throwback To The Good Old Days.</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-16">Dec 16, 2017</time></span>
            </section>
          </article>
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/tulips-150.jpg" alt="">
            </a>
            <h5><a href="#0">10 Interesting Facts About Caffeine.</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-14">Dec 14, 2017</time></span>
            </section>
          </article>
          <article class="col-block popular__post">
            <a href="#0" class="popular__thumb">
              <img src="images/thumbs/small/salad-150.jpg" alt="">
            </a>
            <h5><a href="#0">Healthy Mediterranean Salad Recipes</a></h5>
            <section class="popular__meta">
              <span class="popular__author"><span>By</span> <a href="#0"> John Doe</a></span>
              <span class="popular__date"><span>on</span> <time datetime="2017-12-12">Dec 12, 2017</time></span>
            </section>
          </article>
        </div> <!-- end popular_posts -->
      </div> <!-- end popular -->

      <div class="col-four md-six tab-full about">
        <h3>About Philosophy</h3>

        <p>
          Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Pellentesque in ipsum id orci porta dapibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula. Quisque velit nisi, pretium ut lacinia in, elementum id enim. Donec sollicitudin molestie malesuada.
        </p>

        <ul class="about__social">
          <li>
            <a href="#0"><i class="fa fa-facebook" aria-hidden="true"></i></a>
          </li>
          <li>
            <a href="#0"><i class="fa fa-twitter" aria-hidden="true"></i></a>
          </li>
          <li>
            <a href="#0"><i class="fa fa-instagram" aria-hidden="true"></i></a>
          </li>
          <li>
            <a href="#0"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
          </li>
        </ul> <!-- end header__social -->
      </div> <!-- end about -->

    </div> <!-- end row -->

    <div class="row bottom tags-wrap">
      <div class="col-full tags">
        <h3>Tags</h3>

        <div class="tagcloud">
          <a href="#0">Salad</a>
          <a href="#0">Recipe</a>
          <a href="#0">Places</a>
          <a href="#0">Tips</a>
          <a href="#0">Friends</a>
          <a href="#0">Travel</a>
          <a href="#0">Exercise</a>
          <a href="#0">Reading</a>
          <a href="#0">Running</a>
          <a href="#0">Self-Help</a>
          <a href="#0">Vacation</a>
        </div> <!-- end tagcloud -->
      </div> <!-- end tags -->
    </div> <!-- end tags-wrap -->

  </section> <!-- end s-extra -->


  <!-- s-footer
  ================================================== -->
  <footer class="s-footer">

    <div class="s-footer__main">
      <div class="row">

        <div class="col-two md-four mob-full s-footer__sitelinks">

          <h4>Quick Links</h4>

          <ul class="s-footer__linklist">
            <li><a href="#0">Home</a></li>
            <li><a href="#0">Blog</a></li>
            <li><a href="#0">Styles</a></li>
            <li><a href="#0">About</a></li>
            <li><a href="#0">Contact</a></li>
            <li><a href="#0">Privacy Policy</a></li>

          </ul>

        </div> <!-- end s-footer__sitelinks -->

        <div class="col-two md-four mob-full s-footer__archives">

          <h4>Archives</h4>

          <ul class="s-footer__linklist">
            <li><a href="#0">January 2018</a></li>
            <li><a href="#0">December 2017</a></li>
            <li><a href="#0">November 2017</a></li>
            <li><a href="#0">October 2017</a></li>
            <li><a href="#0">September 2017</a></li>
            <li><a href="#0">August 2017</a></li>
          </ul>

        </div> <!-- end s-footer__archives -->

        <div class="col-two md-four mob-full s-footer__social">

          <h4>Social</h4>

          <ul class="s-footer__linklist">
            <li><a href="#0">Facebook</a></li>
            <li><a href="#0">Instagram</a></li>
            <li><a href="#0">Twitter</a></li>
            <li><a href="#0">Pinterest</a></li>
            <li><a href="#0">Google+</a></li>
            <li><a href="#0">LinkedIn</a></li>
          </ul>

        </div> <!-- end s-footer__social -->

        <div class="col-five md-full end s-footer__subscribe">

          <h4>Our Newsletter</h4>

          <p>Sit vel delectus amet officiis repudiandae est voluptatem. Tempora maxime provident nisi et fuga et enim exercitationem ipsam. Culpa consequatur occaecati.</p>

          <div class="subscribe-form">
            <form id="mc-form" class="group" novalidate="true">

              <input type="email" value="" name="EMAIL" class="email" id="mc-email" placeholder="Email Address" required="">

              <input type="submit" name="subscribe" value="Send">

              <label for="mc-email" class="subscribe-message"></label>

            </form>
          </div>

        </div> <!-- end s-footer__subscribe -->

      </div>
    </div> <!-- end s-footer__main -->

    <div class="s-footer__bottom">
      <div class="row">
        <div class="col-full">
          <div class="s-footer__copyright">
            <span>© Copyright Philosophy 2018</span>
            <span>Site Template by <a href="https://colorlib.com/">Colorlib</a></span>
          </div>

          <div class="go-top">
            <a class="smoothscroll" title="Back to Top" href="#top"></a>
          </div>
        </div>
      </div>
    </div> <!-- end s-footer__bottom -->

  </footer> <!-- end s-footer -->


  <!-- preloader
  ================================================== -->
  <div id="preloader">
    <div id="loader">
      <div class="line-scale">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
  </div>


  <!-- Java Script
  ================================================== -->
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/plugins.js"></script>
  <script src="js/main.js"></script>
  </body>
</html>