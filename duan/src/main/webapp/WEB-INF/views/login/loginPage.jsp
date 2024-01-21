<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %><!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="../../../vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../../../vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>GPhoneS Store</title>
</head>

<body>

<section class="vh-100" style="background-color: #bccff9;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="../../uploads/GPhoneS.png"
                                 alt="login form"  style="border-radius: 1rem 0 0 1rem;" />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form:form action="/security/login" method="post">

                                    <div class="d-flex align-items-center mb-3 pb-1">

                                        <span class="h1 fw-bold mb-0">Xin chào</span>
                                    </div>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Đăng nhập bằng tài khoản của bạn</h5>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="username">Tài khoản</label>
                                        <input type="text" id="username" class="form-control form-control-lg" name="username" />

                                        <span class="text-danger" id="usernameError"></span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="password">Mật khẩu</label>
                                        <input type="password" id="password" class="form-control form-control-lg" name="password" />

                                        <span class="text-danger" id="passwordError"></span>
                                    </div>

                                    <div class="mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" id="loginBtt" onclick="validate()">Đăng nhập</button>
                                        <div style="color: red">${tb}</div>
                                    </div>

                                    <a class="small text-muted" href="/quen-mat-khau"
                                       style="font-size: larger;color: #00A2FF">Quên
                                        mật khẩu?</a>
                                    <hr>
                                    <br>
                                    <p>
                                        <a type="button" class="btn btn-outline-dark btn-lg btn-block"
                                           style="font-size: larger; font-weight: bold" href="/dang-ky-tai-khoan">Đăng ký
                                            tài khoản</a>
                                            <%--                                        <button type="button"--%>
                                            <%--                                                data-bs-toggle="modal" class="btn btn-info"--%>
                                            <%--                                                data-bs-target="#dangKy" style="color: #393f81;">Đăng kí tài khoản--%>
                                            <%--                                        </button>--%>
                                    </p>
                                    <a href="/oauth2/authorization/google" class="btn btn-outline-dark btn-lg btn-block" style="font-weight: bold;"><svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 488 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"/></svg>   Google</a>
                                </form:form>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script>
    function validate(){
        var un=document.getElementById("username").value;
        var pw=document.getElementById("password").value;
        var bt=document.getElementById("loginBtt");
        if (un.trim()===""){
            document.getElementById("usernameError").innerHTML="Tài khoản không được để trống";
            bt.type="button";
            return false;
        }else if (pw.trim()===""){
            document.getElementById("usernameError").innerHTML="";

            document.getElementById("passwordError").innerHTML="Mật khẩu không được để trống";
            bt.type="button";
            return false;
        }
        else{
            document.getElementById("passwordError").innerHTML="";
            bt.type="submit";
            return false;
        }
    }
    function checkhkh() {
        var tenhkh = document.getElementById("tenkh").value;
        var sdtkh = document.getElementById("sdtkh").value;
        var email = document.getElementById("emailkh").value;
        var taiKhoan = document.getElementById("taiKhoanKhachHang").value;
        var matKhau = document.getElementById("matKhauKhachHang").value;

        if (
            tenhkh.trim().length < 6 || tenhkh == ''
        ) {
            document.getElementById("btkh").type = "button";
            document.getElementById("tenkh1").innerHTML = "Không để trống ,Tên ít nhất 6 ký tự";
            return false;
        } else {
            document.getElementById("tenkh1").innerHTML = "";
            var regex = /^0\d{9}$/;
            var regexemail = /^.{8,}@gmail\\.com$/

            if (regex.test(sdtkh)) {
                document.getElementById("sdtkh1").innerHTML = "";
                document.getElementById("btkh").type = "submit";
                return true;
            } else {
                document.getElementById("sdtkh1").innerHTML = "SDT phải 10 số và bắt đầu là số 0";
                if (regexemail.test(email)) {
                    document.getElementById("email1").innerHTML = "";
                    document.getElementById("btkh").type = "submit";
                    return true;
                } else {
                    document.getElementById("email1").innerHTML = "email chưa đúng";
                    if (taiKhoan.trim().length < 6 || taiKhoan == '') {
                        document.getElementById("btkh").type = "button";
                        document.getElementById("taiKhoan1").innerHTML = "Tài khoản không được để trống và có ít nhất 6 ký tự ";
                        return false;
                    } else {
                        document.getElementById("taiKhoan1").innerHTML = "";
                        if (matKhau == '') {
                            document.getElementById("btkh").type = "button";
                            document.getElementById("matKhau1").innerHTML = "Mật khẩu không được để trống";
                            return false;
                        } else {
                            document.getElementById("matKhau1").innerHTML = "";
                            document.getElementById("btkh").type = "summit";
                            alert('Đăng ký tài khoản thành công.');
                            return true;
                        }
                    }
                }
            }
        }
    }

</script>
</body>
<script src="../../../vendor/global/global.min.js"></script>
<script src="../../../js/quixnav-init.js"></script>
<script src="../../../js/custom.min.js"></script>


<!-- Vectormap -->
<script src="../vendor/raphael/raphael.min.js"></script>
<%--    <script src="./vendor/morris/morris.min.js"></script>--%>


<script src="../../../vendor/circle-progress/circle-progress.min.js"></script>
<script src="../../../vendor/chart.js/Chart.bundle.min.js"></script>

<script src="../../../vendor/gaugeJS/dist/gauge.min.js"></script>

<!--  flot-chart js -->
<script src="../../../vendor/flot/jquery.flot.js"></script>
<script src="../../../vendor/flot/jquery.flot.resize.js"></script>

<!-- Owl Carousel -->
<script src="../../../vendor/owl-carousel/js/owl.carousel.min.js"></script>

<!-- Counter Up -->
<script src="../../../vendor/jqvmap/js/jquery.vmap.min.js"></script>
<script src="../../../vendor/jqvmap/js/jquery.vmap.usa.js"></script>
<script src="../../../vendor/jquery.counterup/jquery.counterup.min.js"></script>

<!-- Data table -->
<script src="../../../vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="../../../js/plugins-init/datatables.init.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script src="../../../js/select-2.js"></script>
</html>