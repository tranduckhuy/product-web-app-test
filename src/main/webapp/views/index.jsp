<%-- 
    Document   : index
    Created on : Oct 21, 2023, 2:18:48 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="me.duchuy.productwebapp.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String name = (String) session.getAttribute("name");
    if (name == null) {
        response.sendRedirect("login");
    }

    List<Product> listProduct = (List<Product>) session.getAttribute("listProduct");

    String action = (String) request.getAttribute("act");
    
    int total = (int) request.getAttribute("total");

%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Web App</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    </head>
    <body style="margin-bottom: 100px;">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Trang chủ</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="./list-product?page=1">Sản phẩm <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./form-product">Tạo mới sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./logout">Đăng xuất</a>
                    </li>
                </ul>
            </div>
            <div class="ml-auto">
                <span class="navbar-text">Xin chào, <strong id="userName"><%= name%></strong></span>
            </div>
        </nav>

        <!-- Danh sách sản phẩm dạng card -->
        <div class="container mt-4">
            <h1>Danh Sách Sản Phẩm</h1>
            <div class="d-flex container justify-content-start mt-3 mb-3">
                <p class="m-0">Total: <%= total%> products </p>
            </div>
            <!-- Phần tìm kiếm -->
            <form action="search-product" method="get">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm" id="searchInput" name="search">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-outline-secondary">Tìm kiếm</button>
                    </div>
                </div>
            </form>
            <div class="row">

                <%
                    for (Product product : listProduct) {
                %>
                <div class="col-md-4">
                    <div class="card">

                        <img src="<%= product.getImage()%>" class="card-img-top px-4 mt-3" alt="Sản phẩm 1" style="width: 100%; height: 270px">
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getName()%></h5>
                            <p class="card-text"><%= product.getDescription()%></p>
                            <p class="card-text"><strong>Danh mục:</strong> <%= product.getCategory()%> </p>
                        </div>
                        <div class="card-footer">
                            <a class="btn btn-danger" href="./delete-product?productId=<%= product.getId()%>" >Xóa</a>
                        </div>
                    </div>
                </div>

                <% }%>
            </div>
        </div>


        <%
            if (action != null) {

                String p = request.getParameter("page");
                int pageNum = 1;

                if (page != null && p.matches("\\d+")) {
                    pageNum = Integer.parseInt(p);
                }%>

        <nav aria-label="..." class="d-flex justify-content-center mb-4 mt-4">
            <ul class="pagination pagination-circle">
                <li class="page-item">
                    <a class="page-link" href="list-product?page=<%= (pageNum > 1) ? (pageNum - 1) : 1%>">Previous</a>
                </li>

                <li class="page-item <%= (pageNum == 1) ? " active" : ""%>" aria-current="page">
                    <a class="page-link" href="list-product?page=1">1</span></a>
                </li>
                <%
                    if (total > 6) {
                        for (int i = 0; i <= (total - 6) / 6; i++) {%>
                <li class="page-item <%= (pageNum == (i + 2)) ? " active" : ""%>"><a class="page-link" href="list-product?page=<%= i + 2%>"><%= i + 2%></a></li>
                    <% }
                        }
                    %>

                <li class="page-item">
                    <a class="page-link" href="list-product?page=<%= (pageNum < (total / 6 + 1)) ? (pageNum + 1) : (total / 6 + 1)%>">Next</a>
                </li>
            </ul>
        </nav>    
        <%}
        %>    

        <!-- Footer -->
        <!-- Footer -->
        <footer class="bg-dark text-white p-3" style="position: fixed;bottom: 0; width: 100%; z-index: 999;">
            <div class="container">
                <p class="text-center">© 2023 Website của bạn. Tất cả quyền được bảo lưu.</p>
            </div>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>
</html>
