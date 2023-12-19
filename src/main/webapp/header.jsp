<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!-- Header -->
<header>
    <!-- Header desktop -->
    <div class="wrap-menu-header gradient1 trans-0-4">
        <div class="container h-full">
            <div class="wrap_header trans-0-3">
                <!-- Logo -->
                <div class="logo">
                    <a href="./home">
                        <img src="images/icons/logo.png" alt="IMG-LOGO" data-logofixed="images/icons/logo2.png">
                    </a>
                </div>

                <!-- Menu -->
                <div class="wrap_menu p-l-45 p-l-0-xl">
                    <nav class="menu">
                        <ul class="main_menu">
                            <c:forEach items="${categories}" var="value">
                                <li>
                                    <a href="./${value.getHrefValue()}" style="text-transform: uppercase">${value.getCategoryName()}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>

                <!-- Social -->
                <div class="social flex-w flex-l-m p-r-20">
                    <c:choose>
                        <c:when test="${is_admin}">
                            <a class="mr-2" href="./dashboard" class="mr-2 d-flex align-items-center justify-content-center">ADMIN</a>
                            <a href="./shopping-cart" class="mr-2 d-flex align-items-center justify-content-center pos-relative" style="font-size: 20px">
                                <ion-icon name="cart-outline"></ion-icon>
                                <span class="shopping-cart-amount">${amount_shopping_cart}</span>
                            </a>
                            <a href="./logout" class="mr-2 d-flex align-items-center justify-content-center" style="font-size: 20px"><ion-icon name="log-out-outline"></ion-icon></a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${username != null}">
                                    <a href="./shopping-cart" class="mr-2 d-flex align-items-center justify-content-center pos-relative" style="font-size: 20px">
                                        <ion-icon name="cart-outline"></ion-icon>
                                        <span class="shopping-cart-amount">${amount_shopping_cart}</span>
                                    </a>
                                    <a href="./logout" class="mr-2 d-flex align-items-center justify-content-center" style="font-size: 20px; gap: 10px"><ion-icon name="log-out-outline"></ion-icon></a>
                                        </c:when>
                                        <c:otherwise>
                                    <a class="mr-2" href="./login" class="mr-2 d-flex align-items-center justify-content-center">LOGIN</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                    <button class="btn-show-sidebar m-l-33 trans-0-4"></button>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Sidebar -->
<aside class="sidebar trans-0-4">
    <!-- Button Hide sidebar -->
    <button class="btn-hide-sidebar ti-close color0-hov trans-0-4"></button>

    <!-- - -->
    <ul class="menu-sidebar p-t-95 p-b-70">
        <c:forEach items="${categories}" var="value">
            <li class="t-center m-b-13">
                <a href="./${value.getHrefValue()}" style="text-transform: uppercase" class="txt19">${value.getCategoryName()}</a>
            </li>
        </c:forEach>
    </ul>

    <!-- - -->
    <div class="gallery-sidebar t-center p-l-60 p-r-60 p-b-40">
        <!-- - -->
        <h4 class="txt20 m-b-33">
            Gallery
        </h4>

        <!-- Gallery -->
        <div class="wrap-gallery-sidebar flex-w">
            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-01.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-01.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-02.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-02.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-03.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-03.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-05.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-05.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-06.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-06.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-07.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-07.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-09.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-09.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-10.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-10.jpg" alt="GALLERY">
            </a>

            <a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-11.jpg" data-lightbox="gallery-footer">
                <img src="images/photo-gallery-thumb-11.jpg" alt="GALLERY">
            </a>
        </div>
    </div>
</aside>