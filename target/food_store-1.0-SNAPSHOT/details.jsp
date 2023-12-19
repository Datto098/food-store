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
                Food Detail
            </h2>
        </section>


        <!-- Main menu -->

        <section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
            <div class="container">
                <c:forEach items="${food}" var="food">
                    <form action="./shopping-cart" method="POST" id="form-add-to-cart">
                        <input type="text" name="food-id" id="food-id" value="${food.getFoodId()}" hidden/>
                        <input type="text" name="food-name" id="food-name" value="${food.getFoodName()}" hidden/>
                        <input type="text" name="food-price" id="food-price" value="${food.getFoodPrice()}" hidden/>
                        <input type="text" name="food-image" id="food-image" value="${food.getFoodImage()}" hidden/>
                        <input type="text" name="amount" id="amount" value="0" hidden/>
                    </form>


                    <div class="row">
                        <div class="col-lg-6">
                            <div class="box-img mb-3">
                                <img src="images/${food.getFoodImage()}" alt="food image"/>
                            </div>
                            <div class="slide-image">

                                <div class="slide-container">
                                    <div class="slide-wrapper">

                                        <c:forEach items="${food_image}" var="image">
                                            <div class="slide-item">
                                                <img src="images/${image}"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-lg-6">
                            <h3 class="title-food">${food.getFoodName()}</h3>
                            <div class="rate mb-3">
                                <span style="color: #fbc531"><ion-icon name="star"></ion-icon></span>
                                <span style="font-weight: 600">4.7</span>
                                <span style="font-size: 14px; color: #636e72">(300 feedback)</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <div class="price-box mb-3 mr-3">
                                    $${food.getFoodPrice()}
                                </div>

                                <div class="box-qty mb-3">
                                    <div class="min">
                                        <ion-icon name="remove"></ion-icon>
                                    </div>
                                    <div class="amount">
                                        <label class="input-amount-food d-flex align-items-center justify-content-center mb-0">0</label>
                                        <input name="amount" id="input-amount-food" hidden/>
                                    </div>
                                    <div class="max">
                                        <ion-icon name="add"></ion-icon>
                                    </div>
                                </div>
                            </div>
                            <div class="box-desc mb-3">
                                <h4>Description</h4>
                                <p>${food.getFoodDescription()}</p>
                            </div>
                            <button class="btn-add-to-cart">
                                Add to Cart
                                <ion-icon name="cart"></ion-icon>
                            </button>
                        </div>                    
                    </div>
                </c:forEach>
            </div>

            <div class="feed-back" style="text-align: center">
                <span class="txt5 m-10 mb-2 d-block">
                    Comments
                </span>
                <div class="box-comment container p-4 bg-white rounded">
                    <c:forEach items="${food_comments}" var="comment">
                        <div class="user">
                            <img src="images/default-user-icon.png" alt="icon user"/>
                            <div class="d-flex align-items-start justify-content-center flex-col">
                                <p class="username">${comment.getUsername()}</p>
                                <p class="comment">${comment.getComment()}</p>
                            </div>
                        </div>
                    </c:forEach>


                    <form class="edit-comment" action="" method="POST">
                        <input name="comment" type="text" placeholder="Let everyone know their thoughts about this dish here..." />
                        <input name="username" type="text" hidden value="${username}"/>
                        <input name="food_id" type="text" hidden value="${food_id}"/>
                        <button class="btn btn-danger">Send</button>
                    </form>
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


        <script type="text/javascript">
            const mainDisplayImage = document.querySelector(".box-img img");
            const slideImageItem = document.querySelectorAll(".slide-item img");
            const btnDecAmount = document.querySelector(".min");
            const btnIncAmount = document.querySelector(".max");
            const displayAmount = document.querySelector(".amount label");
            const formAddToCart = document.querySelector("#form-add-to-cart");
            const inputAmount = document.querySelector("#amount");
            const btnAddToCart = document.querySelector(".btn-add-to-cart");
            let amount = 0;

            slideImageItem.forEach((img) => {
                img.addEventListener("click", () => {
                    mainDisplayImage.src = img.src;
                })
            })

            displayAmount.textContent = amount;

            btnDecAmount.addEventListener("click", () => {
                if (amount > 0) {
                    amount--;
                }
                displayAmount.textContent = amount;
            })

            btnIncAmount.addEventListener("click", () => {
                amount++;
                displayAmount.textContent = amount;
            })


            btnAddToCart.addEventListener("click", () => {
                if (amount > 0) {
                    inputAmount.value = amount;
                    formAddToCart.submit();
                } else {
                    alert("Amount food must be than more 0");
                }
            });
        </script>
    </body>
</html>
