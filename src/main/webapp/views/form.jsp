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
        <title>Create new Product</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    </head>
    <body style="overflow-y: scroll">

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
        <div class="container mt-4" style="margin-bottom: 100px">
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
                    <input type="file" class="form-control-file" value="upload" accept=".jpg" id="fileButton">
                    <input name="proImage" type="text" id="proImage" style="display: none">
                    
                    <label for="fileButton">Hình ảnh sản phẩm</label>
                    <div id="imgDiv"></div>
                    <progress value="0" max="100" id="uploader">0%</progress>
                </div>
                <button type="submit" class="btn btn-primary">Tạo sản phẩm</button>
            </form>
        </div>

        <!-- Footer -->
        <footer class="bg-dark text-white p-3" style="position: fixed; bottom: 0; width: 100%;">
            <div class="container">
                <p class="text-center">© 2023 Website của bạn. Tất cả quyền được bảo lưu.</p>
            </div>
        </footer>

        <script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>

        <script type="text/javascript">

            const firebaseConfig = {
                apiKey: "AIzaSyCzLRlj0ijulhG1pEEiXuwDfnB2Zhr7_3c",
                authDomain: "test-firebase-ha.firebaseapp.com",
                projectId: "test-firebase-ha",
                storageBucket: "test-firebase-ha.appspot.com",
                messagingSenderId: "845619558606",
                appId: "1:845619558606:web:84bf3b20dd69f55c04789f",
                measurementId: "G-V2CP3DVRC1"
            };
            firebase.initializeApp(firebaseConfig);

            var image = '';
            // firebase bucket name
            // REPLACE WITH THE ONE YOU CREATE
            // ALSO CHECK STORAGE RULES IN FIREBASE CONSOLE
            var fbBucketName = 'images';

            // get elements
            var uploader = document.getElementById('uploader');
            var fileButton = document.getElementById('fileButton');

            // listen for file selection
            fileButton.addEventListener('change', function (e) {

                // what happened
                console.log('file upload event', e);

                // get file
                var file = e.target.files[0];

                // create a storage ref
                const storageRef = firebase.storage().ref(file.name);
                // upload file
                var uploadTask = storageRef.put(file);

                // The part below is largely copy-pasted from the 'Full Example' section from
                // https://firebase.google.com/docs/storage/web/upload-files

                // update progress bar
                uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
                        function (snapshot) {
                            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
                            var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                            uploader.value = progress;
                            console.log('Upload is ' + progress + '% done');
                            switch (snapshot.state) {
                                case firebase.storage.TaskState.PAUSED: // or 'paused'
                                    console.log('Upload is paused');
                                    break;
                                case firebase.storage.TaskState.RUNNING: // or 'running'
                                    console.log('Upload is running');
                                    break;
                            }
                        }, function (error) {

                    // A full list of error codes is available at
                    // https://firebase.google.com/docs/storage/web/handle-errors
                    switch (error.code) {
                        case 'storage/unauthorized':
                            // User doesn't have permission to access the object
                            break;

                        case 'storage/canceled':
                            // User canceled the upload
                            break;

                        case 'storage/unknown':
                            // Unknown error occurred, inspect error.serverResponse
                            break;
                    }
                }, function () {
                    // Upload completed successfully, now we can get the download URL
                    // save this link somewhere, e.g. put it in an input field
                    var downloadURL = uploadTask.snapshot.downloadURL;
                    image = downloadURL;
                    console.log('downloadURL ===>', downloadURL);
                    let divLocation = document.getElementById("imgDiv");
                    let imgElement = document.createElement("img");
                    imgElement.src = downloadURL;
                    imgElement.width = 130;
                    imgElement.height = 130;
                    console.log('pic ==', downloadURL)
                    divLocation.append(imgElement);
                    document.getElementById('proImage').value = downloadURL;
                });

            });

        </script>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
    </body>
</html>
