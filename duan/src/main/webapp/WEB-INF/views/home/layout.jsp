<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>GPhoneS Store </title>
    <!-- Favicon icon -->

    <link rel="icon" type="image/png" sizes="16x16" href="../../../images/favicon.png">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="../../../vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../../../vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../css/app.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>

</head>

<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="sk-three-bounce">
        <div class="sk-child sk-bounce1"></div>
        <div class="sk-child sk-bounce2"></div>
        <div class="sk-child sk-bounce3"></div>
    </div>
</div>
<!--*******************
    Preloader end
********************-->


<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <a href="index.html" class="brand-logo">
            <img class="logo-abbr" src="../../../uploads/GPhoneS.png" alt="">
            <h3 style="color: white;font-family: 'Times New Roman';margin: 30px">GPhoneS</h3>
        </a>

        <div class="nav-control">
            <div class="hamburger">
                <span class="line"></span><span class="line"></span><span class="line"></span>
            </div>
        </div>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <div class="header">
        <div class="header-content">
            <nav class="navbar navbar-expand">
                <div class="collapse navbar-collapse justify-content-between">
                    <div class="header-left">
                        <div class="search_bar dropdown">
                        </div>
                    </div>

                    <ul class="navbar-nav header-right">
                        <li class="nav-item dropdown header-profile">
                            <a class="nav-link" href="#" role="button" data-toggle="dropdown">

                                <security:authorize access="isAuthenticated()">
                                    hi, <security:authentication property="principal.username"/>
                                </security:authorize>
                                <security:authorize access="!isAuthenticated()">
                                    <a href="/login">Login</a>

                                </security:authorize>


                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a href="/thong-tin-ca-nhan" class="dropdown-item">
                                    <i class="icon-user"></i>
                                    <span class="ml-2">Profile </span>
                                </a>
                                <a href="/logout" class="dropdown-item"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    <i class="icon-key"></i>
                                    <span class="ml-2">Logout </span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="notifications"></div>
    </div>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->

    <!--**********************************
        Sidebar start
    ***********************************-->
    <div class="quixnav">
        <div class="quixnav-scroll">
            <ul class="metismenu" id="menu">
                <li class="nav-label first">Main Menu</li>

                <li><a href="/home" aria-expanded="false">
                    <i class="icon icon-single-04"></i>Trang chủ</a>
                </li>
                <li class="nav-label">Apps</li>
                <li><a href="/ban-hang/hien-thi" aria-expanded="false"><i
                        class="icon icon-app-store"></i>Bán hàng</a>
                </li>
                <li><a href="/hoa-don/hien-thi" aria-expanded="false"><i
                        class="icon icon-chart-bar-33"></i>Hoá đơn</a>
                </li>
<%--                <li><a href="/hoa-don-online/hien-thi" aria-expanded="false"><i--%>
<%--                        class="icon icon-chart-bar-33"></i>Đơn hàng online</a>--%>
<%--                </li>--%>
                <li><a href="/doi-tra/hien-thi" aria-expanded="false">
                    <i class="ti-control-shuffle pa"></i>
                    Đổi trả</a>
                </li>
                <li><a href="/imei/chinh-sach" aria-expanded="false">
                    <i class="icon icon-book-open-2"></i>
                    Chính sách</a>
                </li>

                <li class="nav-label">Quản lý</li>
                <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                        class="icon icon-world-2"></i><span class="nav-text">Quản lý sản phẩm</span></a>
                    <ul aria-expanded="false">
                        <li><a href="/khuyen-mai/hien-thi">Khuyến mại</a></li>
                        <li><a href="/imei/hien-thi">IMEI Sản Phẩm</a></li>
                        <li><a href="/chi-tiet-san-pham/hien-thi">Chi Tiết Sản Phẩm</a></li>
                        <li><a href="/san-pham/hien-thi">Sản Phẩm</a></li>
                        <li><a href="/hang-dien-thoai/hien-thi">Hãng</a></li>
                        <li><a href="/mau-sac/hien-thi">Màu Sắc</a></li>
                        <li><a href="/camera/hien-thi">Camera</a></li>
                        <li><a href="/dung-luong-pin/hien-thi">Dung Lượng Pin</a></li>
                        <li><a href="/pin/hien-thi">Pin</a></li>
                        <li><a href="/man-hinh/hien-thi">Màn Hình</a></li>
                        <li><a href="/ram/hien-thi">Ram</a></li>
                        <li><a href="/rom/hien-thi">Rom</a></li>
                        <li><a href="/chip/hien-thi">Chip</a></li>
                        <li><a href="/anh/hien-thi">Ảnh</a></li>
                    </ul>
                </li>

                <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                        class="icon icon-plug"></i><span class="nav-text">Quản lý tài khoản</span></a>
                    <ul aria-expanded="false">
                        <li><a href="/nhan-vien/hien-thi">Nhân Viên</a></li>
                        <li><a href="/chuc-vu/hien-thi">Chức Vụ</a></li>
                        <li><a href="/khach-hang/hien-thi">Khách Hàng</a></li>
                        <li><a href="/hang-khach-hang/hien-thi">Hạng Khách Hàng</a></li>
                        <li><a href="/dia-chi/hien-thi">Địa Chỉ</a></li>
                    </ul>
                </li>


                <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                        class="icon icon-form"></i><span class="nav-text">Thống kê</span></a>
                    <ul aria-expanded="false">
                        <li><a href="/thong-ke/hien-thi">Doanh Thu theo tháng</a></li>
<%--                        <li><a href="/thong-ke/hien-thi-sp">Doanh thu theo sản phẩm</a></li>--%>
                        <li><a href="/thong-ke/hien-thi-sl-doi-tra">Số lượng đổi hàng và Doanh thu theo hãng</a></li>
                        <li><a href="/thong-ke/hien-thi-khach-hang">Doanh thu khách hàng</a></li>
                        <li><a href="/thong-ke/hien-thi-nhan-vien">Doanh thu nhân viên</a></li>

                    </ul>
                </li>
            </ul>
        </div>


    </div>
    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <!-- row -->
        <div class="container-fluid">
            <div class="right_col" role="main">
                <!-- top tiles -->
                <jsp:include page="${contentPage}"/>
                <ul class="notifications" style="margin-top: 55px"></ul>
            </div>
        </div>
    </div>
    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->

    <!--**********************************
        Footer end
    ***********************************-->

    <!--**********************************
       Support ticket button start
    ***********************************-->

    <!--**********************************
       Support ticket button end
    ***********************************-->


</div>
<script src="../../../js/app.js"></script>

<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
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


</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script src="../../../js/select-2.js"></script>
<script>

</script>
</html>

<script>
    <c:if test="${modalthemmausac==0}">
    document.getElementById("modalMauSac").click();
    </c:if>
    <c:if test="${modalthemsanpham==0}">
    document.getElementById("modalSanPham").click();
    </c:if>
    <c:if test="${modalthemchip==0}">
    document.getElementById("modalChip").click();
    </c:if>
    <c:if test="${modalthemram==0}">
    document.getElementById("modalRam").click();
    </c:if>
    <c:if test="${modalthemrom==0}">
    document.getElementById("modalRom").click();
    </c:if>
    <c:if test="${modalthempin==0}">
    document.getElementById("modalPin").click();
    </c:if>
    //ctsp

    <c:if test="${modalthemmanhinh==0}">
    document.getElementById("modalManHinh").click();

    </c:if>
    <c:if test="${modalthemhang==0}">
    document.getElementById("modalHang").click();

    </c:if>
    <c:if test="${modalthemcamera==0}">
    document.getElementById("modalCamera").click();

    </c:if>

    <c:if test="${batmodalthemsolg==0}">
    document.getElementById("modalthemsolgctsp").click();
    </c:if>


</script>
<script>
    function loadSelect2diachi() {
        $('#selectSanPham2').select2({
            theme: 'bootstrap-5',
            dropdownParent: $('#myModal11')

        });

    }
    loadSelect2diachi();
</script>