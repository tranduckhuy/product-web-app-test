<%-- 
    Document   : form
    Created on : Oct 21, 2023, 2:18:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Website của bạn</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Trang chủ</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="./list-product?page=1">Sản phẩm</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="./form-product">Tạo mới sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./logout">Đăng xuất</a>
                    </li>
                </ul>
            </div>
            <div class="ml-auto">
                <span class="navbar-text">Xin chào, <strong id="userName">Tên Người Dùng</strong></span>
            </div>
        </nav>

        <!-- Body: Form tạo sản phẩm -->
        <div class="container mt-4">
            <h1>Tạo sản phẩm mới</h1>
            <form action="form-product" method="post" enctype='multipart/form-data'>
                <div class="form-group">
                    <label for="tenSanPham">Tên sản phẩm</label>
                    <input type="text" class="form-control" id="tenSanPham" name="proName" placeholder="Nhập tên sản phẩm">
                </div>
                <div class="form-group">
                    <label for="moTa">Mô tả</label>
                    <textarea class="form-control" id="moTa" rows="3" name="proDescription" placeholder="Nhập mô tả sản phẩm"></textarea>
                </div>
                <div class="form-group">
                    <label for="loaiSanPham">Loại sản phẩm</label>
                    <select class="form-control" id="loaiSanPham" name="proCategory">
                        <option value="loai1">Loại 1</option>
                        <option value="loai2">Loại 2</option>
                        <option value="loai3">Loại 3</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="hinhAnh">Hình ảnh sản phẩm</label>
                    <input type="file" class="form-control-file" id="hinhAnh" name="proImage">
                </div>
                <button type="submit" class="btn btn-primary">Tạo sản phẩm</button>
            </form>
        </div>

        <!-- Footer -->
        <!-- Footer -->
        <footer class="bg-dark text-white p-3" style="position: fixed; bottom: 0; width: 100%;">
            <div class="container">
                <p class="text-center">© 2023 Website của bạn. Tất cả quyền được bảo lưu.</p>
            </div>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>
</html>
