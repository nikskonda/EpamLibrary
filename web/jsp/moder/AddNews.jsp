<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" charset="utf-8">

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

<section class="s-content">
    <div class="content">
        <div class="row">
            <form action="/moderator" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_news" >
                <div class="col-lg-6">

                    <input type="text" name="news_title" placeholder="Enter title here..." style="width: 100%">
                    <textarea name="news_text" style="width: 100%">Enter text here...</textarea>
                </div>
                <div class="col-lg-6">
                    <input type="hidden" name="news_lang" value="ru" >
                    <input type="text" name="news_title_ru" placeholder="Enter title here..." style="width: 100%">
                    <textarea name="news_text_ru" style="width: 100%">Enter text here...</textarea>
                </div>
                <input type="file" name="news_photo_url">
                <input type="file" name="news_thumbs_url">
                <%--<input type="file" name="file" multiple="false" >--%>
                <input type="submit" value="Add news">
            </form>
        </div>
    </div>
</section>


</body>
</html>
