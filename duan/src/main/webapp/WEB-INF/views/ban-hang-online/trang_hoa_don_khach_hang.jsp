<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <%--thong bao--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <%--    table--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Hóa đơn</title>
    <!-- Thêm Bootstrap và jQuery -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


    <%--căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick.css"/>
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/nouislider.min.css"/>

    <!-- Font Awesome Icon -->

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/style.css"/>

    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <style>
        /* CSS cho modal */

        input[type="radio"] {
            display: none;
        }


        .input-with-button {
            display: flex; /* Sử dụng flexbox để căn chỉnh nút bên trong input */
            border: 1px solid #ccc; /* Tạo đường viền xung quanh hộp tìm kiếm */
            border-radius: 25px; /* Đặt bán kính tròn cho hộp tìm kiếm */
            overflow: hidden; /* Loại bỏ nút nếu nó bị tràn ra ngoài hộp */
        }

        .input-with-button input {
            flex: 1; /* Làm cho input mở rộng để lấp đầy hộp */
            border: none; /* Loại bỏ đường viền của input */
            padding: 10px; /* Đặt khoảng cách nội dung bên trong input */
            outline: none; /* Loại bỏ đường viền khi focus vào input */
        }

        .input-with-button button {
            background: #007bff; /* Màu nền của nút */
            color: #fff; /* Màu chữ trắng */
            border: none; /* Loại bỏ đường viền của nút */
            padding: 10px 20px; /* Đặt khoảng cách nội dung bên trong nút */
            cursor: pointer; /* Biến con trỏ thành bàn tay khi trỏ vào nút */
        }

        .cart-dropdown {
            border-radius: 10px;
            width: 180px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            padding: 10px;
            margin-top: 10px;
        }

        .cart-dropdown a {
            display: block;
            width: 100%;
            padding: 10px;
            text-decoration: none;
            text-align: center;
            color: #fff;
            background-color: #fff;
            margin-bottom: 10px;
        }

        .cart-dropdown a:hover {
            background-color: #0056b3;
        }


        .tabtab {
            margin-left: 5cm;
        }

        article {

            overflow: hidden;
            position: relative;
            /* Thêm dòng này để các phần tử con có thể sử dụng vị trí tuyệt đối */
        }

        article a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .nav-underline {
            position: absolute;
            bottom: 0;
            left: 0;
            height: 2px;
            background-color: red;
            transition: width 0.3s ease;
        }

        .tab-content {
            display: none;
        }

        #tab00 {
            display: none;
        }


        .card-title {
            font-size: 20px;
            font-weight: bold;
        }

        .table-responsive {
            border: 1px solid #ccc;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-family: sans-serif;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        td {
            text-align: center;
        }

        .modal-dialog {
            max-width: 1000px;
            position: absolute;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            margin-left: 600px;
            margin-top: 500px;
        }

        .modal-dialog {

            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        .modal-content {
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        a:not([href]):not([tabindex]) {
            color: white;
        }
        a:not([href]):not([tabindex]):hover {
            color: white;
        }
    </style>


</head>

<body>
<!-- HEADER -->
<header>
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-envelope-o"></i> gphones@gmail.com</a></li>
            </ul>
            <ul class="header-links pull-right">
                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/login"><i class="fa fa-user-o"></i> Chưa đăng nhập:<input id="tkmkidkhachhang"
                                                                                            type="text"
                                                                                            style="display: none"
                                                                                            value="${idkhachhang}"></a>
                    </li>

                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <!-- Cart -->
                    <li>
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                                    <span>
                                    <i class="fa fa-user-o"></i>
                                            ${khachhangdangnhap.hoTen}
                                        <input id="tkmkidkhachhang" type="text" style="display: none"
                                               value="${idkhachhang}">
                                    </span>
                            </a>
                            <div class="cart-dropdown"
                                 style="border-radius: 10px;width: 3.5cm;margin-top: 10px;width: 180px">
                                <div>
                                    <div>
                                        <form action="/thong-tin-ca-nhan-khach-hang" method="post"
                                              style="display: none">
                                            <input value="${idkhachhang}" name="idKhachHang" style="display: none">
                                            <button style="" class="btn btn-primary" type="submit" id="taikhoancuatoi">
                                                Tài khoản của tôi
                                            </button>
                                        </form>
                                        <a class="btn btn-primary" type="submit" onclick="anbt()" style="background-color:#00aff0">Tài khoản của tôi</a>

                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/full/xem"
                                           class="btn btn-primary" style="background-color:#00aff0">Đơn hàng</a>
                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/chinh-sach-doi-tra"
                                           class="btn btn-primary" style="background-color:#00aff0">Chính sách đổi hàng</a>
                                    </div>
                                    <div>
                                        <a href="/logout" class="btn btn-primary"  onclick="" style="background-color:#00aff0">Đăng xuất</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- /Cart -->
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <h2 class="logo" style="margin: 20px;color: white;font-family: 'Times New Roman'">GPhoneS
                            Store</h2>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form>
                            <div class="input-with-button">
                                <input class="input" placeholder="Tìm kiếm sản phẩm...">
                                <button class="search-btn">Search</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <div>

                        </div>
                        <!-- /Wishlist -->

                        <!-- Cart -->


                        <div class="dropdown" id="giohangtrangchu" >
                            <c:if test="${idkhachhang!='1'}">
                                <c:if test="${listghct.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
                                    >
                                        <i  class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm">
                                        <div class="cart-list">

                                            <c:forEach items="${listghct}" var="ht" varStatus="stt">
                                                <br><hr>
                                                <div style="border: 1px ;height: 2cm">
                                                    <div style="width: 80%;float: right">
                                                        <label style="font-weight: bold">Sản
                                                            phẩm:</label>${ht.chiTietSanPham.sanPham.ten}-
                                                            ${ht.chiTietSanPham.rom.dungLuong}-${ht.chiTietSanPham.mauSac.ten}.

                                                        <br>
                                                        <label style="font-weight: bold">Số lượng:</label> ${ht.soLuong}<br>
                                                        <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)>0}">
                                                            <label style="font-weight: bold">Đơn
                                                                giá:</label>${ht.basoOchammotlamGHDGKG()}đ -
                                                            <del class="product-old-price">${ht.basoOchammotlamGHDG()} đ</del>
                                                        </c:if>
                                                        <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)<=0}">
                                                            <label style="font-weight: bold">Đơn
                                                                giá:</label>
                                                            ${ht.basoOchammotlamGHDG()} đ
                                                        </c:if>
                                                    </div>
                                                    <div style="width: 18%;">
                                                        <input type="checkbox" name="checkidghTT" value="${ht.id}"
                                                               onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>


                                                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}"
                                                             width="50" height="50"
                                                             style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()}
                                                Sản phẩm được chọn</small>
                                            <br>
                                            <label>Tổng:</label><label
                                                id="tongtienghtt">${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}</label><label>đ</label>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                                <input type="checkbox" name="checktongTT"
                                                       onclick="chonhetgiohangtongTRANGCHU('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${listghct.size()<=0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">0</div>
                                    </a>
                                    <div class="cart-dropdown" style="width: 500px;">
                                        <div class="cart-summary">
                                            <small> 0 Sản phẩm được chọn</small>
                                            <h5>Tổng:0 đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${idkhachhang=='1'}">

                                <a class="dropdown-toggle" aria-expanded="true" href="/login">
                                    <i class="fa fa-shopping-cart"></i>
                                    <span>Giỏ hàng</span>
                                    <div class="qty">0</div>
                                </a>


                            </c:if>

                        </div>

                        <!-- /Cart -->


                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->
            <%--                main-nav nav  navbar-nav--%>
            <ul class=" main-nav nav ">

                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/ban-hang-online/hien-thi">TRANG CHỦ</a></li>
                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <li><a href="/ban-hang-online/home">TRANG CHỦ</a></li>
                </c:if>

                <li><a href="/ban-hang-online/dien-thoai-thong-minh">ĐIỆN THOẠI THÔNG MINH</a></li>
                <%--                <li><a style="color: red" href="/ban-hang-online/hoa-don-online/${id}">CÁC ĐƠN HÀNG</a></li>--%>

            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->
<br>

<article style="margin-left: 15%;width: 70%" class="shadow p-3 mb-5 bg-body-tertiary rounded">

    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/full/xem"
       <c:if test="${mau=='full'}">style="color: red" </c:if>
    >Tất cả</a>

    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/0/xem"
       <c:if test="${mau=='0'}">style="color: red" </c:if>
    >Chờ xử lý(TT giao hàng)</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/1/xem"
       <c:if test="${mau=='1'}">style="color: red" </c:if>
    >Đã xác nhận</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/3/xem"
       <c:if test="${mau=='3'}">style="color: red" </c:if>
    >Chờ thanh toán</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/2/xem"
       <c:if test="${mau=='2'}">style="color: red" </c:if>
    >Đã thanh toán</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/danggiao/xem"
       <c:if test="${mau=='danggiao'}">style="color: red" </c:if>
    >Đang giao hàng</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/thanhcong/xem"
       <c:if test="${mau=='thanhcong'}">style="color: red" </c:if>
    >Hoàn thành</a>
    <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/8/xem"
       <c:if test="${mau=='8'}">style="color: red" </c:if>
    >Đã hủy</a>
</article>

<main style="width: 95%;margin-left: 2.5%; " id="content" class="shadow p-3 mb-5 bg-body-tertiary rounded">
    <%--    <c:if test="${listhdkh.size()>0}">--%>
    <div id="tab00" class="tab-content" style="display: block; color: red">



        <div>
            <table id="tentable" class="table table-bordered" style="width: 100%;text-align: center">
                <thead>
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Ngày đặt</th>
                    <th>Ngày TT</th>
                    <th>SĐT</th>
                    <th>Địa chỉ nhận</th>
                    <th>Trạng thái</th>
                    <th>HTTT</th>
                    <th>Giao hàng</th>
                    <th>Đổi hàng</th>
                    <th >Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <fmt:parseDate var="now" value="${now}" pattern="yyyy-MM-dd"/>

                <c:forEach items="${listhdkh}" var="ht" varStatus="stt">
                    <tr>
                        <td>${ht.ma}</td>
                        <td>${ht.ngayTao}</td>
                        <td>${ht.ngayThanhToan}</td>
                        <td>${ht.sdt}</td>
                        <td>
                            <div style="width: 5cm; overflow: hidden;">${ht.diaChi.diaChi},${ht.diaChi.quan},${ht.diaChi.huyen},${ht.diaChi.thanhPho}</div>
                        </td>
                        <td>
                            <c:if test="${ht.tinhTrang==0}">
                                <p>Chờ xử lý</p>
                            </c:if>
                            <c:if test="${ht.tinhTrang==1}">
                                <p>Đã xác nhận</p>
                            </c:if>
                            <c:if test="${ht.tinhTrang==2}">
                                <p>Đã Thanh toán</p>
                            </c:if>
                            <c:if test="${ht.tinhTrang==3}">
                                <p>Chờ thanh toán</p>
                            </c:if>
                            <c:if test="${ht.tinhTrang==8}">
                                <p>Đã hủy</p>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${ht.hinhThucThanhToan==0}">
                                <p>Thanh toán khi nhận hàng</p>
                            </c:if>
                            <c:if test="${ht.hinhThucThanhToan==1}">
                                <p>Thanh toán online</p>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${ht.tinhTrangGiaoHang==0}">
                                <p>Chờ xử lý</p>
                            </c:if>
                            <c:if test="${ht.tinhTrangGiaoHang==1}">
                                <p>Chuẩn bị đơn hàng</p>
                            </c:if>
                            <c:if test="${ht.tinhTrangGiaoHang==2}">
                                <p>Đang giao hàng</p>
                            </c:if>
                            <c:if test="${ht.tinhTrangGiaoHang==3}">
                                <p>Giao hàng thành công</p>
                            </c:if>
                            <c:if test="${ht.tinhTrangGiaoHang==8}">
                                <p>Đơn hàng đã hủy</p>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${  ht.tinhTrang == 2 && ht.tinhTrangGiaoHang == 3 && checkHDlist0.contains(ht.ma) }">
                                <p>Chờ xử lý</p>
                            </c:if>

                            <c:if test="${  ht.tinhTrang == 2 && ht.tinhTrangGiaoHang == 3 && checkHDlist2.contains(ht.ma) }">
                                <p>
                                    Đổi hàng thành công
                                </p>
                            </c:if>
                            <c:if test="${  ht.tinhTrang == 2 && ht.tinhTrangGiaoHang == 3 && checkHDlist1.contains(ht.ma) }">
                                <p>
                                    Từ chối đổi hàng
                                </p>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${ht.tinhTrang==0}">
                                <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Chi tiết
                                </a><br>
                                <a class="btn btn-danger"
                                   href="/ban-hang-online/xem-hoa-don-chi-tiet/huy-hoa-don/${ht.id}/${mau}/huy"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Hủy hoá đơn</a>

                            </c:if>
                            <c:if test="${ht.tinhTrang==1}">
                                <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Chi
                                    tiết
                                </a><br>
                                <a class="btn btn-danger"
                                   href="/ban-hang-online/xem-hoa-don-chi-tiet/huy-hoa-don/${ht.id}/${mau}/huy"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Hủy hoá đơn</a>

                            </c:if>
                            <c:if test="${ht.tinhTrang==2}">
                                <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Chi
                                    tiết
                                </a><br>
                            </c:if>
                            <c:if test="${ht.tinhTrang==3}">

                                <c:if test="${ht.tinhTrangGiaoHang==0 || ht.tinhTrangGiaoHang==1 }">

                                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                    >Chi
                                        tiết </a><br>
                                    <a class="btn btn-danger"
                                       href="/ban-hang-online/xem-hoa-don-chi-tiet/huy-hoa-don/${ht.id}/${mau}/huy"
                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                    >Hủy hoá đơn</a>

                                </c:if>
                                <c:if test="${ht.tinhTrangGiaoHang!=0 && ht.tinhTrangGiaoHang!=1 }">
                                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                    >Chi
                                        tiết </a><br>
                                </c:if>
                            </c:if>
                            <c:if test="${ht.tinhTrang==8}">
                                <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Chi
                                    tiết
                                </a><br>
                            </c:if>
                            <c:if test="${ht.tinhTrang == 2 && ht.tinhTrangGiaoHang == 3 && checkHDlist3.contains(ht.ma) && checkList.contains(ht.ma) }">
                                <button type="button" class="btn btn-primary doi-tra-btn" data-id="${ht.id}"
                                        onclick="showChonSanPhamModal(this)">
                                    Đổi hàng
                                </button><br>
                            </c:if>
                            <c:if test="${ checkHDlist0.contains(ht.ma) }">
                                <a class="btn btn-primary" style="background-color: #ff0000" href="/ban-hang-online/huy/${ht.id}" type="button"
                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Hủy đổi hàng</a><br>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>


</main>
<%--<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content" style="margin-top: 500px;margin-left: -10px">--%>
<%--            <div class="modal-body">--%>
<%--                <div class="col-lg-12 grid-margin stretch-card">--%>
<%--                    <div class="card">--%>
<%--                        <div>--%>
<%--                            <div class="card-body">--%>
<%--                                <h4 class="card-title" style="float: left">Danh sách sản phẩm</h4>--%>
<%--                                <div>--%>
<%--                                    <!-- Ô chọn tất cả ở đây -->--%>
<%--                                </div>--%>
<%--                                <div class="table-responsive">--%>
<%--                                    <div>--%>
<%--                                        <table class="table table-bordered" style="min-width: 845px; color: black">--%>
<%--                                            <thead>--%>
<%--                                            <tr>--%>
<%--                                                <th>--%>
<%--                                                    <input type="checkbox" id="selectAllCheckbox">--%>
<%--                                                </th>--%>
<%--                                                <th>Tên Sản Phẩm</th>--%>
<%--                                                <th>Ảnh</th>--%>
<%--                                                <th>Hãng</th>--%>
<%--                                                <th>Giá</th>--%>
<%--                                                <th>Hình thức</th>--%>
<%--                                                <th>Ghi chú</th>--%>
<%--                                            </tr>--%>
<%--                                            </thead>--%>
<%--                                            <tbody class="san_pham_search" style="text-align: center;height: 3cm"--%>
<%--                                                   id="modalTableBody">--%>
<%--                                            </tbody>--%>
<%--                                        </table>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                            </div>--%>
<%--                            <button class="btn btn-primary" id="confirmButton">Xác nhận</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- /NEWSLETTER -->

<!-- FOOTER -->
<footer id="footer">
    <!-- top footer -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">About Us</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                            ut.</p>
                        <ul class="footer-links">
                            <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                            <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                            <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Categories</h3>
                        <ul class="footer-links">
                            <li><a href="#">Hot deals</a></li>
                            <li><a href="#">Laptops</a></li>
                            <li><a href="#">Smartphones</a></li>
                            <li><a href="#">Cameras</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Information</h3>
                        <ul class="footer-links">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Orders and Returns</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Service</h3>
                        <ul class="footer-links">
                            <li><a href="#">My Account</a></li>
                            <li><a href="#">View Cart</a></li>
                            <li><a href="#">Wishlist</a></li>
                            <li><a href="#">Track My Order</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /top footer -->

    <!-- bottom footer -->
    <div id="bottom-footer" class="section">
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="footer-payments">
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul>
                    <span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i
                            class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
                                                                                target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /bottom footer -->
</footer>
</body>

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="margin-top: 500px;margin-left: 10cm">
            <div class="modal-body">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div>
                            <div class="card-body">
                                <h4 class="card-title" style="float: left">Danh sách sản phẩm</h4>
                                <div>
                                    <!-- Ô chọn tất cả ở đây -->
                                </div>
                                <div class="table-responsive">
                                    <div>
                                        <table class="table table-bordered" style="min-width: 845px; color: black">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <input type="checkbox" id="selectAllCheckbox">
                                                </th>
                                                <th>Tên Sản Phẩm</th>
                                                <th>Ảnh</th>
                                                <th>Hãng</th>
                                                <th>Giá</th>
                                                <th>Hình thức</th>
                                                <th>Ghi chú</th>
                                            </tr>
                                            </thead>
                                            <tbody class="san_pham_search" style="text-align: center;height: 3cm"
                                                   id="modalTableBody">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                            <button class="btn btn-primary" id="confirmButton">Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>--%>
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>--%>


<script>
    function loadScripts(callback) {
        const scriptsToLoad = [
            'https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js',
            'https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js',
            'https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js',
        ];

        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);
            } else {
                // Gọi callback khi tất cả script đã được tải
                if (typeof callback === 'function') {
                    callback();
                }
            }
        }

        loadScript(0);
    }

    loadScripts(function () {

        function showChonSanPhamModal(button) {
            var hoadonId = button.getAttribute("data-id");
            console.log("id hoas ddon " + hoadonId);

            $.ajax({
                type: "GET",
                url: "/detail-test/" + hoadonId,
                success: function (data) {
                    $("#myModal").modal("show");
                    console.log(data);
                    updateModalContent(data, hoadonId);
                },
                error: function (error) {
                    console.log("Lỗi:", error);
                }
            });
        }

        function updateModalContent(data, hoadonId) {
            // Log để kiểm tra giá trị hoadonId và sự kiện được kích hoạt
            console.log("HoadonId trong updateModalContent:", hoadonId);

            // Xóa dữ liệu cũ trong bảng
            $("#modalTableBody").empty();

            // Thêm dữ liệu mới từ AJAX vào bảng
            data.forEach(function (item, index) {
                var row = "<tr>" +
                    "<td><input type='checkbox' class='select-checkbox' data-index='" + index + "' data-id='" + item.id + "'></td>" +
                    "<td>" + item.imei.chiTietSanPham.sanPham.ten + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.giaBan + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.sanPham.hangSanPham.ten + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.giaBan + "</td>" +
                    "<td>" +

                    "Đổi hàng" +



                    "</td>" +
                    "<td class='ghi-chu-td' >" +
                    "<input type='text' class='form-control ghiChuInput' placeholder='Ghi chú'>" +
                    "</td>" +
                    "</tr>";
                $("#modalTableBody").append(row);
            });


            $("#confirmButton").click(function () {
                var selectedItems = [];
                var isValid = true; // Flag to track if all inputs are valid

                // Lặp qua các hàng được chọn để lấy thông tin
                $(".select-checkbox:checked").each(function () {
                    var itemId = $(this).data("id");
                    var ghiChuInput = $(this).closest("tr").find(".ghiChuInput");
                    var ghiChu = ghiChuInput.val();

                    // Kiểm tra nếu input rỗng
                    if (!ghiChu.trim()) {
                        isValid = false;
                        // Hiển thị thông báo lỗi
                        alert("Vui lòng nhập giá trị cho tất cả các ô Ghi chú.");
                        // Dừng vòng lặp
                        return false;
                    }

                    selectedItems.push({
                        hdctId: itemId,
                        ghiChu: ghiChu
                    });
                });

                // Nếu có ít nhất một input không hợp lệ, không thực hiện Ajax request
                if (!isValid) {
                    return;
                }

                // Gửi danh sách thông tin đến controller
                $.ajax({
                    type: "GET",
                    url: "/xac-nhan-khachhang",
                    data: {
                        hdctIds: selectedItems.map(item => item.hdctId),
                        hoadonId: hoadonId,
                        ghiChuList: selectedItems.map(item => item.ghiChu)
                    },
                    success: function (response) {
                        // Xử lý kết quả từ controller nếu cần
                        location.reload();
                    },
                    error: function (error) {
                        console.error("Error:", error);
                    }
                });
            });



            // Xử lý sự kiện khi người dùng chọn ô "Chọn tất cả"
            $("#selectAllCheckbox").change(function () {
                var isChecked = $(this).prop("checked");
                $(".select-checkbox").prop("checked", isChecked).trigger("change");
            });

            // Xử lý sự kiện khi người dùng chọn ô checkbox trong hàng cụ thể
            $(".select-checkbox").change(function () {
                var selectedItems = [];

                $(".select-checkbox:checked").each(function () {
                    var itemId = $(this).data("id");
                    selectedItems.push(itemId);
                });

                console.log("Selected Items:", selectedItems);

                // Gửi danh sách itemId đến controller
                $.ajax({
                    type: "GET",
                    url: "/doi-tra-khachhang",
                    traditional: true,
                    data: {
                        hdctIds: selectedItems,
                        hoadonId: hoadonId
                    },
                    success: function (response) {
                        // Xử lý kết quả từ controller nếu cần
                    },
                    error: function (error) {
                        console.error("Error:", error);
                    }
                });
            });

            // Xử lý sự kiện khi người dùng chọn giá trị trong combobox
            $(".hinhThucSelect").change(function () {
                var index = $(this).closest("tr").index();
                var chonSanPhamBtn = $(".chonSanPhamBtn").eq(index);

                // Ẩn/hiện button Chọn sản phẩm dựa trên giá trị của combobox
                if ($(this).val() === 'doiSanPham') {
                    chonSanPhamBtn.show();
                } else {
                    chonSanPhamBtn.hide();
                }
            });
        }

// Gọi hàm showChonSanPhamModal khi các script đã được tải
        window.showChonSanPhamModal = showChonSanPhamModal;
    });
</script>


<script src="/jsbanhang/bootstrap.min.js"></script>

<!-- Include DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Include DataTables JS -->
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        $('#tentable').DataTable({
            "pagingType": "full_numbers",
            "searching": true
        });
    });
</script>
<script>

    function chonhetgiohangtongTRANGCHU(idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);


        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);

        }


        // alert("vdvdvd")
    };

    function chonsanphamgiohangTT(vt, idctgh, idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        var vt1 = parseInt(vt);
        // var id1=encodeURIComponent(id);
        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

        }


    };

    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                formatAndDisplayValue("tongtienghtt");
                // loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }









    loadSelect2diachi();
</script>
<script>
    function anbt() {
        document.getElementById('taikhoancuatoi').click();
    }
    function formatAndDisplayValue(elementId) {
        // Lấy giá trị từ thẻ div
        var originalValue = document.getElementById(elementId).textContent;

        // Chuyển đổi giá trị sang dạng có dấu chấm phân cách hàng nghìn
        var formattedValue = Number(originalValue).toLocaleString('vi-VN');

        // Gán giá trị đã định dạng lại vào thẻ div
        document.getElementById(elementId).textContent = formattedValue;
    }

    formatAndDisplayValue("tongtienghtt");
</script>





<button data-bs-toggle="modal" data-bs-target="#modalSuccess"  id="thongbaohuyhoadon" style="display: block">
    Click
</button>
<div id="modalSuccess" class="modal fade">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="width: 10cm;height: 7cm;margin-left: 17cm;margin-top: 7cm">
            <div class="modal-body" >
                <div class="row">
                    <div class="col-12">
                        <div class="swal2-icon swal2-success swal2-animate-success-icon"
                             style="display: block;">
                            <div class="swal2-success-circular-line-left"
                                 style="background: rgb(255, 255, 255);"></div>
                            <span class="swal2-success-line-tip swal2-animate-success-line-tip"></span> <span
                                class="swal2-success-line-long swal2-animate-success-line-long"></span>
                            <div class="swal2-success-ring"></div>
                            <div class="swal2-success-fix" style="background: rgb(255, 255, 255);"></div>
                            <div class="swal2-success-circular-line-right"
                                 style="background: rgb(255, 255, 255);"></div>
                        </div>
                        <h4 style="color: #10ae05;margin: 10px;text-align: center">${ndtb}</h4>
                    </div>
                    <div class="col-12" style="align-items: center;margin-top: 20px">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    <c:if test="${hientbhuy==0}">
    document.getElementById("thongbaohuyhoadon").click();
    </c:if>

</script>

<script>
    function loadScripts(callback) {
        const scriptsToLoad = [
            'https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js',
            'https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js',
            'https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js',
        ];

        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);
            } else {
                // Gọi callback khi tất cả script đã được tải
                if (typeof callback === 'function') {
                    callback();
                }
            }
        }

        loadScript(0);
    }

    loadScripts(function () {

        function showChonSanPhamModal(button) {
            var hoadonId = button.getAttribute("data-id");
            console.log("id hoas ddon " + hoadonId);

            $.ajax({
                type: "GET",
                url: "/detail-test/" + hoadonId,
                success: function (data) {
                    $("#myModal").modal("show");
                    console.log(data);
                    updateModalContent(data, hoadonId);
                },
                error: function (error) {
                    console.log("Lỗi:", error);
                }
            });
        }

        function updateModalContent(data, hoadonId) {
            // Log để kiểm tra giá trị hoadonId và sự kiện được kích hoạt
            console.log("HoadonId trong updateModalContent:", hoadonId);

            // Xóa dữ liệu cũ trong bảng
            $("#modalTableBody").empty();

            // Thêm dữ liệu mới từ AJAX vào bảng
            data.forEach(function (item, index) {
                var row = "<tr>" +
                    "<td><input type='checkbox' class='select-checkbox' data-index='" + index + "' data-id='" + item.id + "'></td>" +
                    "<td>" + item.imei.chiTietSanPham.sanPham.ten + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.giaBan + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.sanPham.hangSanPham.ten + "</td>" +
                    "<td>" + item.imei.chiTietSanPham.giaBan + "</td>" +
                    "<td>" +

                    "Đổi hàng" +



                    "</td>" +
                    "<td class='ghi-chu-td' >" +
                    "<input type='text' class='form-control ghiChuInput' placeholder='Ghi chú'>" +
                    "</td>" +
                    "</tr>";
                $("#modalTableBody").append(row);
            });
            $("#confirmButton").click(function () {
                var selectedItems = [];
                var isValid = true; // Flag to track if all inputs are valid

                // Lặp qua các hàng được chọn để lấy thông tin
                $(".select-checkbox:checked").each(function () {
                    var itemId = $(this).data("id");
                    var ghiChuInput = $(this).closest("tr").find(".ghiChuInput");
                    var ghiChu = ghiChuInput.val();

                    // Kiểm tra nếu input rỗng
                    if (!ghiChu.trim()) {
                        isValid = false;
                        // Hiển thị thông báo lỗi
                        alert("Vui lòng nhập giá trị cho tất cả các ô Ghi chú.");
                        // Dừng vòng lặp
                        return false;
                    }

                    selectedItems.push({
                        hdctId: itemId,
                        ghiChu: ghiChu
                    });
                });

                // Nếu có ít nhất một input không hợp lệ, không thực hiện Ajax request
                if (!isValid) {
                    return;
                }

                // Gửi danh sách thông tin đến controller
                $.ajax({
                    type: "GET",
                    url: "/xac-nhan-khachhang",
                    data: {
                        hdctIds: selectedItems.map(item => item.hdctId),
                        hoadonId: hoadonId,
                        ghiChuList: selectedItems.map(item => item.ghiChu)
                    },
                    success: function (response) {
                        // Xử lý kết quả từ controller nếu cần
                        location.reload();
                    },
                    error: function (error) {
                        console.error("Error:", error);
                    }
                });
            });



            // Xử lý sự kiện khi người dùng chọn ô "Chọn tất cả"
            $("#selectAllCheckbox").change(function () {
                var isChecked = $(this).prop("checked");
                $(".select-checkbox").prop("checked", isChecked).trigger("change");
            });

            // Xử lý sự kiện khi người dùng chọn ô checkbox trong hàng cụ thể
            $(".select-checkbox").change(function () {
                var selectedItems = [];

                $(".select-checkbox:checked").each(function () {
                    var itemId = $(this).data("id");
                    selectedItems.push(itemId);
                });

                console.log("Selected Items:", selectedItems);

                // Gửi danh sách itemId đến controller
                $.ajax({
                    type: "GET",
                    url: "/doi-tra-khachhang",
                    traditional: true,
                    data: {
                        hdctIds: selectedItems,
                        hoadonId: hoadonId
                    },
                    success: function (response) {
                        // Xử lý kết quả từ controller nếu cần
                    },
                    error: function (error) {
                        console.error("Error:", error);
                    }
                });
            });

            // Xử lý sự kiện khi người dùng chọn giá trị trong combobox
            $(".hinhThucSelect").change(function () {
                var index = $(this).closest("tr").index();
                var chonSanPhamBtn = $(".chonSanPhamBtn").eq(index);

                // Ẩn/hiện button Chọn sản phẩm dựa trên giá trị của combobox
                if ($(this).val() === 'doiSanPham') {
                    chonSanPhamBtn.show();
                } else {
                    chonSanPhamBtn.hide();
                }
            });
        }

// Gọi hàm showChonSanPhamModal khi các script đã được tải
        window.showChonSanPhamModal = showChonSanPhamModal;
    });
</script>
</html>
