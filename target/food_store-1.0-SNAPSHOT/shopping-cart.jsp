<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
                Shopping Cart
            </h2>
        </section>


        <!-- Main menu -->
        <section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
            <div class="container">
                <table class="table w-100 shopping-cart-table">
                    <thead>
                        <tr>
                            <th scope="col">Food name</th>
                            <th scope="col">Food image</th>
                            <th scope="col">Amount</th>                            
                            <th scope="col">Price</th>
                            <th scope="col">
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart}" var="value">
                            <tr>
                                <td>${value.getFoodName()}</th>
                                <td><img src="images/${value.getFoodImage()}" style="max-width: 120px; border-radius: 10px" alt="food image" /></td>
                                <td>${value.getAmount()}</td>
                                <td>${value.getTotalPrice()}</td>
                                <td>
                                    <a class="h3" href="./delete-shopping-cart?id=${value.getFoodId()}"><ion-icon name="close-circle-outline"></ion-icon></a>
                                </td>                                    
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="text-right">
                    <div>
                        <b>Total: $${total_pay}</b>
                    </div>
                    <a href="./checkout">Checkout</a>
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
