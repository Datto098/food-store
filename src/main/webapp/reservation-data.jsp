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
        <title>Reservation Data</title>
    </head>
    <body>

        <%@include file="dashboard-nav.jsp" %>
        <div class="container">
            <h1 class="text-center mt-3 mb-3">Reservation Data</h1>
            <div class="group-btn d-flex align-items-center justify-content-between">
                <div class="group-btn d-flex align-items-center justify-content-between">
                    <form class="input-group d-flex align-items-center justify-content-center" style="max-width: 320px" action="" method="GET">
                        <input type="text" class="form-control" placeholder="Search ..." name="search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary ms-2" type="submit">Search</button>
                        </div>
                    </form>
                </div>
            </div>
            <table class="table w-100">
                <thead>
                    <tr>
                        <th scope="col">Reservation id</th>
                        <th scope="col">Date</th>
                        <th scope="col">People</th>
                        <th scope="col">Name</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Status</th>
                        <th scope="col">
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reservations}" var="value">
                        <tr>
                            <th scope="row">${value.getReservationId()}</th>
                            <td>${value.getReservationDate()}</td>
                            <td>${value.getPeople()}</td>                            
                            <td>${value.getName()}</td>
                            <td>${value.getPhone()}</td>
                            <td>${value.getEmail()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${value.getStatus() == 0}">
                                        <p class="pending_approval btn btn-warning mb-0">Pending approval</p>
                                    </c:when>
                                    <c:when test="${value.getStatus() == 1}">
                                        <p class="approved btn btn-success mb-0">Approved</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="cancelled btn btn-danger mb-0">Cancelled</p>
                                    </c:otherwise>
                                </c:choose>


                            </td>
                            <td>
                                <div class="d-flex align-items-center justify-content-center w-fit">
                                    <form action="./approved-reservation" method="post" class="me-2">
                                        <input name="id" value="${value.getReservationId()}" hidden/>                                        <input name="id" value="${value.getReservationId()}" hidden/>
                                        <input name="status" value="1" hidden/>
                                        <button class="btn btn-primary" type="submit">Approved</button>
                                    </form>
                                    <form action="./approved-reservation" method="post" class="me-2">
                                        <input name="id" value="${value.getReservationId()}" hidden/>
                                        <input name="status" value="2" hidden/>
                                        <button class="btn btn-danger" type="submit">Cancelled</button>
                                    </form>
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

