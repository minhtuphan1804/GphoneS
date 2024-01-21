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
    <title>Đăng ký tài khoản </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="../../../vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../../../vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- Favicon icon -->
</head>
<body>
<section class="vh-100" style="background-color: #bccff9;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-7">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-12 col-lg-12 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">
                                <div class="row">
                                    <div class="col-12">
                                        <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Khôi phục mật
                                            khẩu</h3>
                                    </div>
                                    <div class="col-12">
                                        <h4 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Nếu đã có tài khoản
                                            bạn có thể
                                            <a class="small text-muted" href="/login"
                                               style="font-size: large"><p style="color: red">Đăng nhập tại đây</p></a>
                                        </h4>
                                    </div>
                                    <div class="col-12">
                                        <form action="/login/quen-mat-khau" method="post">
                                            <form class="form-sample">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group row">
                                                            <label class="col-sm-3 col-form-label">Tài
                                                                Khoản:
                                                            </label>
                                                            <div class="col-sm-9">
                                                                <input class="form-control" placeholder=""
                                                                       name="taiKhoan-quen" id="tai-khoan-quen"/>
                                                                <label id="taiKhoan-quen1" style="color: red"></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="form-group row">
                                                            <label class="col-sm-3 col-form-label">Email:
                                                            </label>
                                                            <div class="col-sm-9">
                                                                <input class="form-control" placeholder=""
                                                                       name="email-quen" id="email-quenn"/>
                                                                <label id="email-quan1" style="color: red"></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <label style="color:red;text-align: center">${thongBao}</label>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div style="text-align: center">
                                                            <button type="submit" class="btn btn-primary mr-2"
                                                                    id="btkh" onclick="return checkhkh()">
                                                                Khôi phục
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    function validate() {
        var un = document.getElementById("username").value;
        var pw = document.getElementById("password").value;
        var bt = document.getElementById("loginBtt");
        if (un.trim() === "") {
            document.getElementById("usernameError").innerHTML = "Tài khoản không được để trống";
            bt.type = "button";
            return false;
        } else if (pw.trim() === "") {
            document.getElementById("usernameError").innerHTML = "";

            document.getElementById("passwordError").innerHTML = "Mật khẩu không được để trống";
            bt.type = "button";
            return false;
        } else {
            document.getElementById("passwordError").innerHTML = "";
            bt.type = "submit";
            return false;
        }
    }

    function checkhkh() {
        var email = document.getElementById("email-quenn").value;
        var taiKhoan = document.getElementById("tai-khoan-quen").value;
        var regexemail = /^.{8,}@gmail\\.com$/

        if (regexemail.test(email)) {
            document.getElementById("email-quan1").innerHTML = "";
            document.getElementById("btkh").type = "submit";
            return true;
        } else {
            document.getElementById("email-quan1").innerHTML = "email chưa đúng";
            if (taiKhoan.trim().length < 6 || taiKhoan == '') {
                document.getElementById("btkh").type = "button";
                document.getElementById("taiKhoan-quen1").innerHTML = "Tài khoản không được để trống và có ít nhất 6 ký tự ";
                return false;
            } else {
                document.getElementById("taiKhoan-quen1").innerHTML = "";
                document.getElementById("btkh").type = "summit";
                return true;
            }
        }
    }
</script>
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
