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

        <div class="form-add">
            <div class="btn-close-form">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                </svg>
            </div>

            <h3 class="text-center mt-3 mb-3">Add data</h3>
            <form action="./add-menu" method="post">
                <div class="mb-3">
                    <label for="category-name-add" class="form-label">Menu Name</label>
                    <input type="text" class="form-control" id="category-name-add" name="menu-name">
                </div>
                <div class="mb-3">
                    <label for="parent-id-add" class="form-label">Menu Description</label>
                    <input type="text" class="form-control" id="parent-id-add" name="menu-description">
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
            <form action="./edit-menu" method="post">
                <input type="number" class="form-control input-category-id" name="id" id="category-id" hidden>
                <div class="mb-3">
                    <label for="category-name" class="form-label">Menu Name</label>
                    <input type="text" class="form-control" id="category-name" name="menu-name">
                </div>
                <div class="mb-3">
                    <label for="parent-id" class="form-label">Menu Description</label>
                    <input type="text" class="form-control" id="parent-id" name="menu-description">
                </div>
                <button type="submit" class="btn btn-primary">Edit</button>
            </form>
        </div>

        <%@include file="dashboard-nav.jsp" %>
        <div class="container">
            <h1 class="text-center mt-3 mb-3">Menu Data</h1>
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
                    Add new category
                </button>
            </div>
            <table class="table w-100">
                <thead>
                    <tr>
                        <th scope="col">Menu id</th>
                        <th scope="col">Menu name</th>
                        <th scope="col">Menu description</th>
                        <th scope="col">
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${menus}" var="value">
                        <tr>
                            <th scope="row">${value.getMenuId()}</th>
                            <td>${value.getMenuName()}</td>
                            <td>${value.getMenuDescription()}</td>
                            <td>
                                <div class="d-flex align-items-center justify-content-center w-fit">
                                    <form action="./delete-menu" method="post" class="me-2 form-delete-menu">
                                        <input name="id" value="${value.getMenuId()}" hidden/>
                                        <button class="btn btn-danger btn-delete-menu" type="button">Delete</button>
                                    </form>
                                    <button class="btn btn-primary btn-edit-category" data-edit="${value.getMenuId()}">Edit</button>
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
        const btnDeleteMenu = document.querySelectorAll(".btn-delete-menu");

        btnDeleteMenu.forEach((btn) => {
            btn.addEventListener("click", () => {
                const form = btn.parentElement;
                if (confirm("Are you sure to delete it ?")) {
                    form.submit();
                }
            })
        })
        
        
        btnEditCategory.forEach((btn) => {
            btn.addEventListener("click", (event) => {
                event.preventDefault();
                overlay.classList.add("active");
                formData.classList.add("active");

                const idEdit = btn.getAttribute("data-edit");
                fetchData("./get-menu-by-id?id=" + idEdit, "GET").then(data => {
                    inputMenuId.value = data.menuId;
                    inputMenuName.value = data.menuName;
                    inputMenuDescription.value = data.menuDescription;
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
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>

