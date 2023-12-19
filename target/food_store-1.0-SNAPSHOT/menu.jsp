<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/themify/themify-icons.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">        
        <link rel="stylesheet" type="text/css" href="css/custom.css">

        <!--===============================================================================================-->
    </head>
    <body class="animsition">

        <%@include file="header.jsp" %>


        <!-- Title Page -->
        <section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(images/bg-title-page-01.jpg);">
            <h2 class="tit6 t-center">
                Pato Menu
            </h2>
        </section>


        <!-- Main menu -->
        <section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
            <div class="container">
                <div class="row">
                      <div class="col-md-10 col-lg-6 p-l-35 p-l-15-lg m-l-r-auto">
                        <div class="wrap-item-mainmenu p-b-22">
                            <h3 class="tit-mainmenu tit10 p-b-25">
                                STARTERS
                            </h3>

                            <c:forEach items="${starters}" var="value">
                                <div class="item-mainmenu m-b-36">
                                    <div class="flex-w flex-b m-b-3">
                                        <a href="./detail?id=${value.getFoodId()}" class="name-item-mainmenu txt21">
                                            ${value.getFoodName()}
                                        </a>

                                        <div class="line-item-mainmenu bg3-pattern"></div>

                                        <div class="price-item-mainmenu txt22">
                                            ${value.getFoodPrice()}$
                                        </div>
                                    </div>

                                    <span class="info-item-mainmenu txt23">
                                        ${value.getFoodDescription()}
                                    </span>
                                </div>
                            </c:forEach>

                            <div class="wrap-item-mainmenu p-b-22">
                                <h3 class="tit-mainmenu tit10 p-b-25">
                                    DRINKS
                                </h3>

                                <c:forEach items="${drinks}" var="value">
                                    <div class="item-mainmenu m-b-36">
                                        <div class="flex-w flex-b m-b-3">
                                            <a href="./detail?id=${value.getFoodId()}" class="name-item-mainmenu txt21">
                                                ${value.getFoodName()}
                                            </a>

                                            <div class="line-item-mainmenu bg3-pattern"></div>

                                            <div class="price-item-mainmenu txt22">
                                                ${value.getFoodPrice()}$
                                            </div>
                                        </div>

                                        <span class="info-item-mainmenu txt23">
                                            ${value.getFoodDescription()}
                                        </span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-10 col-lg-6 p-l-35 p-l-15-lg m-l-r-auto">
                        <div class="wrap-item-mainmenu p-b-22">
                            <h3 class="tit-mainmenu tit10 p-b-25">
                                Main
                            </h3>

                            <c:forEach items="${mainDish}" var="value">
                                <div class="item-mainmenu m-b-36">
                                    <div class="flex-w flex-b m-b-3">
                                        <a href="./detail?id=${value.getFoodId()}" class="name-item-mainmenu txt21">
                                            ${value.getFoodName()}
                                        </a>

                                        <div class="line-item-mainmenu bg3-pattern"></div>

                                        <div class="price-item-mainmenu txt22">
                                            ${value.getFoodPrice()}$
                                        </div>
                                    </div>

                                    <span class="info-item-mainmenu txt23">
                                        ${value.getFoodDescription()}
                                    </span>
                                </div>
                            </c:forEach>

                            <div class="wrap-item-mainmenu p-b-22">
                                <h3 class="tit-mainmenu tit10 p-b-25">
                                    Dessert
                                </h3>

                                <c:forEach items="${dessert}" var="value">
                                    <div class="item-mainmenu m-b-36">
                                        <div class="flex-w flex-b m-b-3">
                                            <a href="./detail?id=${value.getFoodId()}" class="name-item-mainmenu txt21">
                                                ${value.getFoodName()}
                                            </a>

                                            <div class="line-item-mainmenu bg3-pattern"></div>

                                            <div class="price-item-mainmenu txt22">
                                                ${value.getFoodPrice()}$
                                            </div>
                                        </div>

                                        <span class="info-item-mainmenu txt23">
                                            ${value.getFoodDescription()}
                                        </span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
        </section>


        <!-- Lunch -->
        <section class="section-lunch bgwhite">
            <div class="header-lunch parallax0 parallax100" style="background-image: url(images/header-menu-01.jpg);">
                <div class="bg1-overlay t-center p-t-170 p-b-165">
                    <h2 class="tit4 t-center">
                        Lunch
                    </h2>
                </div>
            </div>

            <div class="container">
                <div class="row p-t-108 p-b-70">
                    <div class="col-md-8 col-lg-12 m-l-r-auto food-grid">
                        <!-- Block3 -->
                        <c:forEach items="${lunch}" var="value">
                            <div class="blo3 flex-w flex-col-l-sm m-b-30">
                                <div class="pic-blo3 size20 bo-rad-10 hov-img-zoom m-r-28">
                                    <a href="./detail?id=${value.getFoodId()}"><img src="images/${value.getFoodImage()}" alt="IMG-MENU"></a>
                                </div>

                                <div class="text-blo3 size21 flex-col-l-m">
                                    <a href="./detail?id=${value.getFoodId()}" class="txt21 m-b-3">
                                        ${value.getFoodName()}
                                    </a>

                                    <span class="txt23">
                                        ${value.getFoodDescription()}
                                    </span>

                                    <span class="txt22 m-t-20">
                                        $${value.getFoodPrice()}
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="d-flex align-items-center justify-content-center w-100">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="1" end="${totalPage}">
                                    <li class="page-item <c:if test="${currentPage eq i}">active</c:if>">
                                        <a class="page-link" href="?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${currentPage < totalPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section>


        <!-- Dinner -->
        <section class="section-dinner bgwhite">
            <div class="header-dinner parallax0 parallax100" style="background-image: url(images/header-menu-02.jpg);">
                <div class="bg1-overlay t-center p-t-170 p-b-165">
                    <h2 class="tit4 t-center">
                        Dinner
                    </h2>
                </div>
            </div>

            <div class="container">
                <div class="row p-t-108 p-b-70">
                    <div class="col-md-8 col-lg-12 m-l-r-auto food-grid">
                        <!-- Block3 -->
                        <c:forEach items="${dinner}" var="value">
                            <div class="blo3 flex-w flex-col-l-sm m-b-30">
                                <div class="pic-blo3 size20 bo-rad-10 hov-img-zoom m-r-28">
                                    <a href="./detail?id=${value.getFoodId()}"><img src="images/${value.getFoodImage()}" alt="IMG-MENU"></a>
                                </div>

                                <div class="text-blo3 size21 flex-col-l-m">
                                    <a href="./detail?id=${value.getFoodId()}" class="txt21 m-b-3">
                                        ${value.getFoodName()}
                                    </a>

                                    <span class="txt23">
                                        ${value.getFoodDescription()}
                                    </span>

                                    <span class="txt22 m-t-20">
                                        $${value.getFoodPrice()}
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>


        <!-- Sign up -->
        <div class="section-signup bg1-pattern p-t-85 p-b-85">
            <form class="flex-c-m flex-w flex-col-c-m-lg p-l-5 p-r-5">
                <span class="txt5 m-10">
                    Specials Sign up
                </span>

                <div class="wrap-input-signup size17 bo2 bo-rad-10 bgwhite pos-relative txt10 m-10">
                    <input class="bo-rad-10 sizefull txt10 p-l-20" type="text" name="email-address" placeholder="Email Adrress">
                    <i class="fa fa-envelope ab-r-m m-r-18" aria-hidden="true"></i>
                </div>

                <!-- Button3 -->
                <button type="submit" class="btn3 flex-c-m size18 txt11 trans-0-4 m-10">
                    Sign-up
                </button>
            </form>
        </div>


        <%@include file="footer.jsp" %>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/daterangepicker/moment.min.js"></script>
        <script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/slick/slick.min.js"></script>
        <script type="text/javascript" src="js/slick-custom.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/parallax100/parallax100.js"></script>
        <script type="text/javascript">
            $('.parallax100').parallax100();
        </script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>

    </body>
</html>
