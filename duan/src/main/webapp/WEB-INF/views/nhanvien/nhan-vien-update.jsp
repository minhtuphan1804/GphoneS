<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
    <style>
        .image-container {
            width: 200px;
            height: 200px;
            border-radius: 50%;
            overflow: hidden;
            display: flex;
            justify-content: center;
            align-items: center;
            border: 2px solid #ccc;
            margin: 0 auto;
            cursor: pointer; /* Bật con trỏ thành pointer để biểu thị tính năng nhấp vào */
        }

        .image-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
        }

        .input-hidden {
            display: none;
        }
    </style>
</head>
<body>
<div class="main">
    <div>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
            <li class="nav-item">
                <a class="nav-link" href="/nhan-vien/hien-thi" role="tab">Trang thông tin nhân viên</a>
            </li>
            <li class="nav-item" style="border-radius: 2%">
                <a class="nav-link" role="tab">Update nhân viên</a>
            </li>
        </ul>
    </div>
    <div>
        <div class="col-12 grid-margin">
            <div class="card">
                <div class="card-body">
                    <form:form action="/nhan-vien/update/${nhanVien.id}" method="post" modelAttribute="nhanVien"
                               enctype="multipart/form-data">
                        <form class="form-sample">
                            <div class="row">
                                <div class="col-12">
                                    <div style="display: none">
                                        <input style="" type="text" name="checkanh1" value="cu1" id="cucheck1">
                                        <br>
                                    </div>
                                    <div align="center">
                                        <br>
                                        <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                               for="anhmoi1">
                                            <img id="preview-anh1-2" class="preview-image"
                                                 src="/uploads/${nhanVien.urlAnh}" alt=""
                                                 width="100%" height="100%"
                                                 style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                            <br><br>
                                            ẢNH
                                        </label>
                                        <br>
                                        <div style="display: none">
                                            <input type="file" name="anh1s" accept="image/jpeg, image/png"
                                                   id="anhmoi1">
                                        </div>
                                    </div>
                                    <div style="display: none">
                                        <form:input path="urlAnh"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="hoTen" class="col-sm-3 col-form-label">Họ tên:</form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" id="form-control" placeholder=""
                                                        path="hoTen"/>
                                            <form:errors path="hoTen" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="email" class="col-sm-3 col-form-label">Email:</form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" id="form-control" placeholder=""
                                                        path="email"/>
                                            <form:errors path="email"
                                                         cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="sdt" class="col-sm-3 col-form-label">SĐT:</form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="sdt"/>
                                            <form:errors path="sdt" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="ngaySinh" class="col-sm-3 col-form-label">Ngày sinh:
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" type="date"
                                                        placeholder=""
                                                        path="ngaySinh"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="canCuoc" class="col-sm-3 col-form-label">CCCD:</form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="canCuoc"/>
                                            <form:errors path="canCuoc" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="gioiTinh"
                                                    class="col-sm-3 col-form-label">Giới tính:</form:label>
                                        <div class="col-sm-9" style="margin-top: 0.5cm">
                                            <form:radiobutton path="gioiTinh" value="true" checked="true"/>Nam
                                            <form:radiobutton path="gioiTinh" value="false"
                                                              cssStyle="margin-left: 1cm"/> Nữ
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label path="queQuan" class="col-sm-3 col-form-label">Quê quán:</form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="queQuan"/>
                                            <form:errors path="queQuan" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-12">
                                                <form:select path="chucVu" class="form-control" id="selectSanPham"
                                                             cssStyle="font-weight: bold; width: 100%">
                                                    <option selected disabled>Chức vụ</option>
                                                    <form:options items="${listChucVu}" itemValue="id" itemLabel="ten"/>
                                                </form:select>
                                                <form:errors path="chucVu"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    SAVE
                                </button>
                            </div>
                        </form>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    const imageInput1 = document.getElementById('anhmoi1');

    const previewAnh12 = document.getElementById('preview-anh1-2');

    imageInput1.addEventListener('change', function () {
        const file = imageInput1.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh12.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh12.src = '';
        }
        document.getElementById('cucheck1').value = 'moi1';
    });
</script>
<script>
    // const emailInput = document.getElementById('email');
    // const emailError = document.getElementById('email-error');

    const passwordInput = document.getElementById('password');
    const passwordError = document.getElementById('password-error');
    const passwordToggle = document.getElementById('password-toggle');


    // Gmail/password
    function togglePassword() {
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            passwordToggle.innerText = 'Hide';
        } else {
            passwordInput.type = 'password';
            passwordToggle.innerText = 'Show';
        }
    };

    //
    // function validateEmail() {
    //     const email = emailInput.value;
    //     const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+([a-zA-Z]{2,7}|\w{2,7}\.edu)$/; // Điều kiện email
    //     if (!emailRegex.test(email)) {
    //         emailError.textContent = 'Email không hợp lệ';
    //     } else {
    //         emailError.textContent = '';
    //     }
    // }

    // emailInput.addEventListener('blur', validateEmail);

    function thongBao() {
        // if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;
        var ngaySinh = document.getElementById("check").value;
        if (confirm("Bạn muốn dùng trức năng này không") == true) {
            if (ngaySinh.trim() === '') {
                document.getElementById("tb").innerHTML = "Không để trống ngày sinh!";
                document.getElementById("bt").type = "button"
                return false;
            } else {
                document.getElementById("tb").innerHTML = "";
                document.getElementById("bt").type = "submit";
                return true;
            }
        } else {
            return false;
        }
    };

</script>

</html>
