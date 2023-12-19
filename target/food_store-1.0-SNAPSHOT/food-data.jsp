<%-- 
    Document   : category_data
    Created on : Dec 5, 2023, 12:57:44 PM
    Author     : nguyendatdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>        
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>

        <title>Menu Data</title>
    </head>
    <body>
        <div class="overlay">

        </div>

        <div class="form-edit-food-image">
            <div class="btn-close-form">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                </svg>
            </div>

            <h3 class="text-center mt-3 mb-3">Edit main image food</h3>

            <form action="./edit-main-image-food" method="post" class="d-flex flex-column" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="input-food-id-edit-image" class="form-label">Food Id</label>
                    <input type="text" class="form-control" id="input-food-id-edit-image" name="food-id">
                </div>
                <label for="input-edit-main-image-food" class="upload-type">
                    Choose image file
                </label>
                <input id="input-edit-main-image-food" hidden type="file" name="new-food-image" multiple/>

                <button class="btn btn-warning btn-edit-image-food mt-3" type="submit" >
                    Update
                </button>
            </form>
        </div>


        <div class="form-add">
            <div class="btn-close-form">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                </svg>
            </div>

            <h3 class="text-center mt-3 mb-3">Add data</h3>
            <form action="./add-food" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="category-name-add" class="form-label">Food Name</label>
                    <input type="text" class="form-control" id="category-name-add" name="food-name">
                </div>
                <div class="mb-3">
                    <label for="parent-id-add-b" class="form-label">Food Description</label>
                    <input type="text" class="form-control" id="parent-id-add-b" name="food-description">
                </div>
                <div class="mb-3">
                    <label for="parent-id-add" class="form-label">Food Price</label>
                    <input type="number" class="form-control" id="parent-id-add" name="food-price">
                </div>
                <div class="d-flex align-items-center justify-content-between">
                    <div class="">
                        <label for="parent-id-a" class="form-label btn btn-primary btn-choose-image-file">Choose food image</label>
                        <input type="file" class="form-control" id="parent-id-a" name="food-image" multiple hidden>
                    </div>

                    <div class="show-image ms-3">
                        <img src="images/default-image.png" alt="image" style="max-width: 200px" class="rounded-1"/>
                    </div>
                </div>

                <div class="mb-3 d-flex flex-column">
                    <p>Choose menu</p>
                    <div>
                        <c:forEach items="${menus}" var="value">
                            <label for="parent-id-add-c-${value.getMenuId()}" class="form-label btn btn-primary btn-choose-menu input-menu-id">${value.getMenuName()}</label>
                            <input type="radio" class="form-control" id="parent-id-add-c-${value.getMenuId()}" name="menu-id" value="${value.getMenuId()}" hidden>
                        </c:forEach>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>


        <div class="form-edit">
            <div class="btn-close-form">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                </svg>
            </div>

            <h3 class="text-center mt-3 mb-3">Edit data</h3>
            <form action="./edit-food" method="post" enctype="multipart/form-data">
                <div class="box-image mb-3">
                    <div class="grid-box-image mb-3">
                    </div>
                    <input type="text" name="food-image" id="input-food-image" hidden/>
                    <label class="btn btn-primary mb-3" for="add-more-food-images">
                        Add more image
                        <input type="file" multiple hidden name="food-images" id="add-more-food-images" />
                    </label>
                    <div style="flex: 1" class="">
                        <input type="text" class="form-control" id="food-id-edit" name="id" hidden>
                        <div class="mb-3">
                            <label for="food-name-edit" class="form-label">Food Name</label>
                            <input type="text" class="form-control" id="food-name-edit" name="food-name">
                        </div>
                        <div class="mb-3">
                            <label for="food-description-edit" class="form-label">Food Description</label>
                            <input type="text" class="form-control" id="food-description-edit" name="food-description">
                        </div>
                        <div class="mb-3">
                            <label for="food-price-edit" class="form-label">Food Price</label>
                            <input type="number" class="form-control" id="food-price-edit" name="food-price">
                        </div>
                        <div class="mb-3 d-flex flex-column">
                            <p>Menu Id</p>
                            <div>
                                <c:forEach items="${menus}" var="value">
                                    <label for="menu-id-edit-${value.getMenuId()}" data-focus="${value.getMenuId()}" class="form-label btn btn-primary btn-choose-menu label-menu-id-edit">${value.getMenuName()}</label>
                                    <input type="radio" class="form-control input-menu-id-edit" id="menu-id-edit-${value.getMenuId()}" name="menu-id" value="${value.getMenuId()}" hidden>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Edit</button>
            </form>
        </div>

        <%@include file="dashboard-nav.jsp" %>
        <div class="container">
            <h1 class="text-center mt-3 mb-3">Food Data</h1>
            <div class="group-btn d-flex align-items-center justify-content-between">
                <div class="group-btn d-flex align-items-center justify-content-between">
                    <form class="input-group d-flex align-items-center justify-content-center" style="max-width: 320px" action="" method="GET">
                        <input type="text" class="form-control" placeholder="Search ..." name="search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary ms-2" type="submit">Search</button>
                        </div>
                    </form>
                </div>
                <button class="btn btn-info btn-add-category text-light">
                    Add new food
                </button>
            </div>
            <table class="table w-100">
                <thead>
                    <tr>
                        <th scope="col">Food id</th>
                        <th scope="col">Food image</th>
                        <th scope="col">Food name</th>
                        <th scope="col">Food description</th>                         
                        <th scope="col">Food price</th>
                        <th scope="col">Menu id</th>
                        <th scope="col">
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${foods}" var="value">
                        <tr>
                            <th scope="row">${value.getFoodId()}</th>
                            <td><img src="images/${value.getFoodImage()}" alt="food" style="max-width: 200px" class="rounded-3"/></td>
                            <td>${value.getFoodName()}</td>
                            <td>${value.getFoodDescription()}</td>
                            <td>${value.getFoodPrice()}</td>
                            <td>${value.getMenuId()}</td>
                            <td>
                                <div class="d-flex align-items-center justify-content-center w-fit">
                                    <form action="./delete-food" method="post" class="me-2">
                                        <input name="id" value="${value.getFoodId()}" hidden/>
                                        <button class="btn btn-danger btn-delete-food" type="button" >Delete</button>
                                    </form>
                                    <button class="btn btn-primary btn-edit-category me-2" data-edit="${value.getFoodId()}">Edit</button>

                                    <button class="btn btn-warning btn-edit-image-food" data-edit="${value.getFoodId()}">
                                        Image
                                    </button>
                                </div>
                            </td>                                    
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="d-flex align-items-center justify-content-center w-100">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <c:url var="previousUrl" value="${searchPath}">
                                    <c:param name="page" value="${currentPage - 1}" />
                                </c:url>
                                <a class="page-link" href="<c:out value="${previousUrl}" />" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPage}">
                            <li class="page-item <c:if test="${currentPage eq i}">active</c:if>">
                                <c:url var="pageUrl" value="${searchPath}">
                                    <c:param name="page" value="${i}" />
                                </c:url>
                                <a class="page-link" href="<c:out value="${pageUrl}" />">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPage}">
                            <li class="page-item">
                                <c:url var="nextUrl" value="${searchPath}">
                                    <c:param name="page" value="${currentPage + 1}" />
                                </c:url>
                                <a class="page-link" href="<c:out value="${nextUrl}" />" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </body>

    <!-- Script-->

    <script type="text/javascript">
        const fetchData = (apiUrl, method = 'GET', data = null) => {
            let requestOptions = {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: data ? JSON.stringify(data) : null
            };
            if (method === 'POST') {
                requestOptions = {
                    method: method,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    mode: 'no-cors',
                    body: data
                };
            }

            return fetch(apiUrl, requestOptions)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error: ${response.status}`);
                        }
                        return response.json();
                    })
                    .catch(error => {
                        console.error('Fetch error:', error);
                        throw error;
                    });
        };

        const btnEditCategory = document.querySelectorAll(".btn-edit-category");
        const formData = document.querySelector(".form-edit");
        const formAdd = document.querySelector(".form-add");
        const overlay = document.querySelector(".overlay");
        const btnAdd = document.querySelector(".btn-add-category");
        const btnCloseForm = document.querySelectorAll(".btn-close-form");
        const inputMenuId = document.querySelector("#category-id");
        const inputMenuName = document.querySelector("#category-name");
        const inputMenuDescription = document.querySelector("#parent-id");
        const btnChooseMenu = document.querySelectorAll(".btn-choose-menu");
        const inputFoodImage = document.querySelector("input[name='food-image']");
        const btnChooseMenuImage = document.querySelector(".btn-choose-image-file");
        const boxImage = document.querySelector(".grid-box-image");
        const inputEditFoodName = document.querySelector("#food-name-edit");
        const inputEditFoodPrice = document.querySelector("#food-price-edit");
        const inputEditFoodDescription = document.querySelector("#food-description-edit");
        const boxDisplayImage = document.querySelector(".show-image img");
        const inputEditFoodId = document.querySelector("#food-id-edit");
        const labelEditMenuIds = document.querySelectorAll(".label-menu-id-edit");
        const btnDeleteFood = document.querySelectorAll(".btn-delete-food");
        const inputAddMoreFoodImage = document.querySelector('#add-more-food-images');
        const inputFoodImageEdit = document.querySelector("#input-food-image");
        const inputMenuIdEdit = document.querySelectorAll(".input-menu-id-edit");
        const formEditFoodImage = document.querySelector(".form-edit-food-image");
        const btnEditFoodImage = document.querySelectorAll(".btn-edit-image-food");
        const inputIdEditFoodImage = document.querySelector("#input-food-id-edit-image");
        const labelInputEditMainImageFood = document.querySelector("label[for='input-edit-main-image-food']");
        const inputEditMainImageFood = document.querySelector("#input-edit-main-image-food");

        inputEditMainImageFood.addEventListener("input", (e) => {
            labelInputEditMainImageFood.textContent = e.target.files[0].name;
        })

        btnEditFoodImage.forEach(btn => {
            btn.addEventListener("click", () => {
                const idEdit = btn.getAttribute("data-edit");
                inputIdEditFoodImage.value = idEdit.toString();

                formEditFoodImage.classList.add("active");
                overlay.classList.add("active");
            })
        })



        btnDeleteFood.forEach((btn) => {
            btn.addEventListener("click", () => {
                const form = btn.parentElement;
                if (confirm("Are you sure to delete it ?")) {
                    form.submit();
                }
            })
        });

        inputFoodImage.addEventListener("input", (e) => {
            btnChooseMenuImage.textContent = "File selected: " + (e.target.files[0].name);
            boxDisplayImage.src = URL.createObjectURL(e.target.files[0]);
        })

        let prevBtnChooseMenu = null;



        btnChooseMenu.forEach((btn) => {
            btn.addEventListener("click", () => {
                console.log(prevBtnChooseMenu);
                if (prevBtnChooseMenu != null) {
                    prevBtnChooseMenu.classList.remove("btn-warning");
                }

                btn.classList.add("btn-warning");
                prevBtnChooseMenu = btn;
            });
        })

        btnEditCategory.forEach((btn) => {
            btn.addEventListener("click", (event) => {
                event.preventDefault();
                overlay.classList.add("active");
                formData.classList.add("active");
                const idEdit = btn.getAttribute("data-edit");

                fetchData("./get-food-by-id?id=" + idEdit, "GET").then(data => {
                    inputEditFoodId.value = data.foodId;
                    inputEditFoodName.value = data.foodName;
                    inputEditFoodDescription.value = data.foodDescription;
                    inputEditFoodPrice.value = data.foodPrice;
                    inputFoodImageEdit.value = data.foodImage;

                    inputMenuIdEdit.forEach((input) => {
                        if (input.value === data.menuId.toString()) {
                            input.checked = true;
                        }
                    })


                    fetchData("./get-food-image-by-id?id=" + idEdit, "GET").then(dataImage => {
                        boxImage.textContent = "";
                        let images = [];
                        images.push(data.foodImage);
                        if (dataImage.length > 0) {
                            dataImage.forEach((image) => {
                                images.push(image.imageName);
                            })
                        }


                        images.forEach((img) => {
                            // <img src="images/spaghetti-bolognese-36.jpg" style="max-width: 200px; height: auto" class="rounded-3" alt="food image"/>
                            const imgTag = document.createElement("img");
                            imgTag.setAttribute("src", "images/" + img);
                            imgTag.style.maxWidth = "200px";
                            imgTag.classList.add("rounded-3");
                            imgTag.setAttribute("alt", "food image");
                            boxImage.appendChild(imgTag);
                        })
                    })

                    labelEditMenuIds.forEach((label) => {
                        if (label.getAttribute("data-focus") === data.menuId.toString()) {
                            label.classList.add("btn-warning");
                            prevBtnChooseMenu = label;
                        } else {
                            label.classList.remove("btn-warning");
                        }
                    });
                });
            });
        });

        btnCloseForm.forEach((btn) => {
            btn.addEventListener("click", () => {
                overlay.classList.remove("active");
                btn.parentElement.classList.remove("active");
            });
        });

        btnAdd.addEventListener("click", (event) => {
            event.preventDefault();
            overlay.classList.add("active");
            formAdd.classList.add("active");
        });

        inputAddMoreFoodImage.addEventListener("input", (e) => {

            const files = e.target.files;
            for (let i = 0; i < files.length; i++) {
                const src = URL.createObjectURL(files[i]);
                const imgTag = document.createElement("img");
                imgTag.setAttribute("src", src);
                imgTag.style.maxWidth = "200px";
                imgTag.classList.add("rounded-3");
                imgTag.setAttribute("alt", "food image");
                boxImage.appendChild(imgTag);
            }
        })

    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>

