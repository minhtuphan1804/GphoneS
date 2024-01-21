<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Thông tin cá nhân</title>
</head>
<body>
<div style="width: 100%;height: auto">

    <form:form action="/login/doi-mat-khau/${us.id}" method="post" modelAttribute="us"
               enctype="multipart/form-data">
        <form class="form-sample">
            <div class="card">
                <div class="card-body">
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
                                         src="/uploads/${us.urlAnh}" alt=""
                                         width="100%" height="100%"
                                         style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                    <br><br>
                                    <label style="color: black;">${us.hoTen}</label>
                                </label>
                                <br>
                                <div style="display: none">
                                    <input type="file" name="anh1s" accept="image/jpeg, image/png"
                                           id="anhmoi1">
                                </div>
                            </div>
                            <div style="display: none">
                                <form:input path="urlAnh"/>
                                <form:input path="ma"/>
                                <form:input path="taiKhoan"/>
                                <form:input path="hoTen"/>
                                <form:input path="ngaySinh"/>
                                <form:input path="canCuoc"/>
                                <form:input path="sdt"/>
                                <form:input path="email"/>
                                <form:input path="gioiTinh"/>
                                <form:input path="queQuan"/>
                                <form:input path="luong"/>
                                <form:input path="tinhTrang"/>
                                <form:input path="ngayTao"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" href="/thong-tin-ca-nhan" role="tab">Thông tin cá nhân</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#personal-information"
                           role="tab" aria-controls="description" aria-selected="true">Đổi mật
                            khẩu</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content">
                <div class="tab-pane fade show active" id="personal-information">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Mật khẩu cũ:</label>
                                        <div class="col-sm-9 password-container">
                                            <input id="oldpassword" name="mat-khau-cu"
                                                   class="form-control" type="password"
                                                   placeholder="*******"/>
                                            <label style="color: red" id="old-error">${thongBao1}</label>
                                            <br>
                                            <label class="password-toggle" id="old-password-toggle"
                                                   onclick="togglePassword1()">
                                                Show
                                            </label>

                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Mật khẩu mới:</label>
                                        <div class="col-sm-9 password-container">
                                            <input id="newpassword" name="mat-khau-moi"
                                                   class="form-control" type="password"
                                                   placeholder="*******"/>
                                            <label style="color: red" id="new-error">${thongBao2}</label>
                                            <br>
                                            <label class="password-toggle" id="new-password-toggle"
                                                   onclick="togglePassword2()">
                                                Show
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Xác nhận mật khẩu:</label>
                                        <div class="col-sm-9 password-container">
                                            <input id="confirmpassword" name="xac-nhan-mat-khau"
                                                   class="form-control" type="password"
                                                   placeholder="*******"/>
                                            <label style="color: red" id="confirm-error">${thongBao3}</label>
                                            <br>
                                            <label class="password-toggle" id="confirm-password-toggle"
                                                   onclick="togglePassword3()">
                                                Show
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="return checkhkh()" id="btkh">
                                    Đổi mật khẩu
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </form:form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    const oldpasswordInput = document.getElementById('oldpassword');
    const oldpasswordToggle = document.getElementById('old-password-toggle');

    function togglePassword1() {
        if (oldpasswordInput.type === 'password') {
            oldpasswordInput.type = 'text';
            oldpasswordToggle.innerText = 'Hide';
        } else {
            oldpasswordInput.type = 'password';
            oldpasswordToggle.innerText = 'Show';
        }
    };
    const newpasswordInput = document.getElementById('newpassword');
    const newpasswordToggle = document.getElementById('new-password-toggle');

    function togglePassword2() {
        if (newpasswordInput.type === 'password') {
            newpasswordInput.type = 'text';
            newpasswordToggle.innerText = 'Hide';
        } else {
            newpasswordInput.type = 'password';
            newpasswordToggle.innerText = 'Show';
        }
    };
    const confirmpasswordInput = document.getElementById('confirmpassword');
    const confirmpasswordToggle = document.getElementById('confirm-password-toggle');

    function togglePassword3() {
        if (confirmpasswordInput.type === 'password') {
            confirmpasswordInput.type = 'text';
            confirmpasswordToggle.innerText = 'Hide';
        } else {
            confirmpasswordInput.type = 'password';
            confirmpasswordToggle.innerText = 'Show';
        }
    };

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

    function checkhkh() {
        var old = document.getElementById("oldpassword").value;
        var newp = document.getElementById("newpassword").value;
        var con = document.getElementById("confirmpassword").value;

        if (
            old.trim().length < 6 || old == ''
        ) {
            document.getElementById("btkh").type = "button";
            document.getElementById("old-error").innerHTML = "Không để trống ,Mật khẩu ít nhất 6 ký tự";
            return false;
        } else {
            document.getElementById("old-error").innerHTML = "";
            if (newp.trim().length < 6 || newp == '') {
                document.getElementById("new-error").innerHTML = "Không để trống ,Mật khẩu ít nhất 6 ký tự";
                document.getElementById("btkh").type = "button";
                return false;
            } else {
                document.getElementById("new-error").innerHTML = "";
                if (con.trim().length < 6 || con == '') {
                    document.getElementById("confirm-error").innerHTML = "Không để trống ,Mật khẩu ít nhất 6 ký tự";
                    document.getElementById("btkh").type = "button";
                    return false;
                } else {
                    document.getElementById("confirm-error").innerHTML = "";
                    document.getElementById("btkh").type = "summit";
                    return true;
                }
            }
        }
    }
</script>
</html>