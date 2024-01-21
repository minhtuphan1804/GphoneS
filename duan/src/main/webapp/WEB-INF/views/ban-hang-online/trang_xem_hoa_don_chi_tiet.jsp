<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
            integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <%--    table--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Hóa đơn</title>
    <%--    căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>


    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

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
    <link rel="stylesheet" href="/cssbanhang/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/style.css"/>

    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>

    <style>
        /* CSS cho modal */
        #myModal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000; /* Đặt giá trị z-index lớn */
        }

        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        }

        /*div{*/
        /*    border: 1px solid red;*/
        /*}*/

        /* Ẩn input radio */
        input[type="radio"] {
            display: none;
        }

        /* Tạo một giao diện tùy chỉnh cho label */


        /* Khi label được nhấp vào, thay đổi màu nền để biểu thị lựa chọn */
        input[type="radio"]:checked + label {
            background-color: #007bff;
            color: #fff;
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
            background-color: #007bff;
            margin-bottom: 10px;
        }

        .cart-dropdown a:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body style="background-color: #fdfff2">
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
                            <a class="dropdown-toggle" data-toggle="dropdown">
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
                                        <a class="btn btn-primary" type="submit" onclick="anbt()">Tài khoản của tôi</a>

                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/full/xem"
                                           class="btn btn-primary">Đơn hàng</a>
                                    </div>
                                    <div>
                                        <a href="/logout" class="btn btn-primary" style="" onclick="">Đăng xuất</a>
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
                        <form action="/ban-hang-online/trang-chu/tim-kiem" method="post">
                            <div class="input-with-button">

                                <input type="text" id="searchInput" name="trangchutimkiem"
                                       placeholder="Tìm kiếm sản phẩm...">
                                <%--                                    <button class="search-btn" type="submit">Search</button>--%>


                            </div>
                        </form>

                        <div style="  position: absolute;
                        background-color: white;margin-top: 5px;
                        width: 95%; overflow: hidden;z-index: 11;
                        box-shadow: 0 1px 4px 0 rgba(0,0,0,.26);" id="searchResults" class="list-group">
                            <div id="saochep"></div>
                            <div id="danhsachloctimkiemTT" style="">

                            </div>
                        </div>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <div>

                        </div>
                        <div class="dropdown" id="giohangtrangchu">
                            <c:if test="${idkhachhang!='1'}">
                                <c:if test="${listghct.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm">
                                        <div class="cart-list">

                                            <c:forEach items="${listghct}" var="ht" varStatus="stt">
                                                <br>
                                                <hr>
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
                                                            <del class="product-old-price">${ht.basoOchammotlamGHDG()}
                                                                đ
                                                            </del>
                                                        </c:if>
                                                        <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)<=0}">
                                                            <label style="font-weight: bold">Đơn
                                                                giá:</label>
                                                            ${ht.basoOchammotlamGHDG()} đ
                                                        </c:if></div>
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
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->

<br>
<main style="width: 80%;margin-left: 12.5%" id="contentghct" class="shadow p-3 mb-5 bg-body-tertiary rounded">
    <div>
        <br>
        <c:if test="${hd.tinhTrangGiaoHang==0}">
            <c:if test="${hd.tinhTrang!=8}">
                <button class="btn btn-primary" style="float: right ;margin-right: 65px" data-bs-toggle="modal"
                        data-bs-target="#modalthaydoithongtinnhanhang" id="capnhatthongtingiaohang">Thay đổi thông tin
                    nhận hàng
                </button>
            </c:if>
        </c:if>
        <br><br>
        <div class="row" style="margin-left: 5%;width: 90%">
            <div class="col-6">
                <div class="row" style="margin: 20px">
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Mã đơn hàng:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.ma}" disabled style="width: 97%" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Người mua:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.khachHang.hoTen}" disabled style="width: 97%"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Người nhận:</label>
                        <div class="col-sm-9">
                            <input type="text"
                                   value="${hd.nguoiNhan}"
                                   disabled style="width: 97%"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">SĐT giao hàng:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.sdt}" disabled style="width: 97%" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Phương thức thanh toán:</label>
                        <div class="col-sm-9">
                            <c:if test="${hd.hinhThucThanhToan==2}">
                                <input type="text" value="Chưa chọn" disabled style="width: 97%" class="form-control"/>
                            </c:if>
                            <c:if test="${hd.hinhThucThanhToan==0}">
                                <input type="text" value="Tiền mặt" disabled style="width: 97%" class="form-control"/>
                            </c:if>
                            <c:if test="${hd.hinhThucThanhToan==1}">
                                <input type="text" value="Chuyển khoản" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Phí ship:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.convertPhiShip()}" disabled style="width: 97%" class="form-control"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="row" style="margin: 20px">
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Ngày mua:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.ngayTao}" disabled style="width: 97%" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Địa chỉ nhận hàng:</label>
                        <div class="col-sm-9">
                            <input type="text"
                                   value="${hd.diaChi.diaChi},${hd.diaChi.quan},${hd.diaChi.huyen},${hd.diaChi.thanhPho}"
                                   disabled style="width: 97%"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Tổng tiền:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.convertTongtien()} VNĐ" disabled style="width: 97%"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Mã giao dịch:</label>
                        <div class="col-sm-9">
                            <input type="text" value="${hd.maGiaoDich} " disabled style="width: 97%"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Trạng thái:</label>
                        <div class="col-sm-9">
                            <c:if test="${hd.tinhTrang==0}">
                                <input type="text" value="Chờ xử lý" disabled style="width: 97%" class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrang==1}">
                                <input type="text" value="Đã xác nhận" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrang==2}">
                                <input type="text" value="Đã Thanh toán" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrang==3}">
                                <input type="text" value="Chờ thanh toán" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrang==8}">
                                <input type="text" value="Đã hủy" disabled style="width: 97%" class="form-control"/>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-label" style="padding: 5px">Trạng thái giao hàng:</label>
                        <div class="col-sm-9">
                            <c:if test="${hd.tinhTrangGiaoHang==0}">
                                <input type="text" value="Chờ xử lý" disabled style="width: 97%" class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrangGiaoHang==1}">
                                <input type="text" value="Chuẩn bị giao" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrangGiaoHang==2}">
                                <input type="text" value="Đang giao" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrangGiaoHang==3}">
                                <input type="text" value="Giao hàng hoàn tất" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                            <c:if test="${hd.tinhTrangGiaoHang==8}">
                                <input type="text" value="Đã hủy" disabled style="width: 97%"
                                       class="form-control"/>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div>
        <c:if test="${hd.tinhTrang==1}">
            <div class="row" style="text-align: center;">
                <div class="col-6">
                    <a class="btn btn-info"
                       href="/ban-hang-online/xem-hoa-don-chi-tiet/thanh-toan-khi-nhan-hang/${hd.id}">Thanh toán
                        tiền mặt khi nhận hàng</a>
                </div>
                <div class="col-6">
                    <a class="btn btn-info"
                       href="/pay/${hd.tongTien}/${hd.id}">Thanh toán
                        VNPay</a>
                </div>
            </div>
        </c:if>
    </div>
    <br>
    <br>
    <table class="table table-striped" style="text-content: center;width: 90%;margin-left: 5%;">
        <thead>
        <tr>
            <th>Ảnh sản phẩm</th>
            <th>Sản phẩm</th>
            <th>Khuyến mãi (%/SP)</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Tổng</th>
            <th>Tình trạng</th>
            <%--            <th> Chức năng</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:set var="dem" scope="session" value="${0}"/>
        <c:forEach items="${banhangonline.dsDDHtheoIDHD(hd.id)}" var="ht" varStatus="stt">
            <%--            <c:if test="${stt.index==0}">--%>
            <%--                <tr>--%>
            <%--                    <td>--%>
            <%--                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40" height="40"--%>
            <%--                             style="border-radius:50% 50% 50% 50%">--%>
            <%--                    </td>--%>
            <%--                    <td>--%>
            <%--                            ${ht.chiTietSanPham.sanPham.ten} - ${ht.chiTietSanPham.mauSac.ten}--%>
            <%--                        - ${ht.chiTietSanPham.ram.dungLuong} - ${ht.chiTietSanPham.rom.dungLuong}.--%>
            <%--                    </td>--%>
            <%--                    <td>--%>

            <%--                            ${100-ht.donGiaKhiGiam/ht.donGia*100} %--%>
            <%--                    </td>--%>

            <%--                    <td>--%>
            <%--                            ${banhangonline.convertgiatien(ht.donGiaKhiGiam)}  VND--%>
            <%--                    </td>--%>
            <%--                    <td>--%>
            <%--                            ${ht.soLuong}--%>
            <%--                    </td>--%>
            <%--                    <td>${banhangonline.convertgiatien(ht.donGiaKhiGiam*ht.soLuong)}--%>
            <%--                        VND--%>
            <%--                    </td>--%>
            <%--                    <td>--%>
            <%--                        <c:if test="${hd.tinhTrang==0}">--%>
            <%--                            <p>Chờ xử lý</p>--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${hd.tinhTrang==1}">--%>
            <%--                            <p>Đã xác nhận</p>--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${hd.tinhTrang==2}">--%>
            <%--                            <p>Đã Thanh toán</p>--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${hd.tinhTrang==3}">--%>
            <%--                            <p>Chờ thanh toán</p>--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${hd.tinhTrang==8}">--%>
            <%--                            <p>Đã hủy</p>--%>
            <%--                        </c:if>--%>
            <%--                    </td>--%>
            <%--&lt;%&ndash;                    <td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                        <c:if test="${hd.tinhTrangGiaoHang==0}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;                            <c:if test="${ht.hoaDon.tinhTrang==0}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;                                <a onclick="xoahdct('${ht.id}');" class="btn btn-primary">Hủy</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                        </c:if>&ndash;%&gt;--%>

            <%--&lt;%&ndash;                    </td>&ndash;%&gt;--%>
            <%--                </tr>--%>
            <%--                <c:set var="dem" scope="session" value="${dem+1}"/>--%>
            <%--            </c:if>--%>
            <%--            <c:if test="${stt.index>0}">--%>
            <%--                <c:set var="checkck" scope="session" value="${0}"/>--%>
            <%--                <c:forEach items="${listhdct}" var="ht1" varStatus="stt1" begin="0" end="${stt.index-1}">--%>
            <%--                    <c:if test="${ht.imei.chiTietSanPham.id==ht1.imei.chiTietSanPham.id}">--%>
            <%--                        <c:set var="checkck" scope="session" value="${1}"/>--%>
            <%--                    </c:if>--%>
            <%--                </c:forEach>--%>
            <%--                <c:if test="${checkck==0}">--%>
            <%--                    <tr>--%>
            <%--                        <td>--%>
            <%--                            <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40" height="40"--%>
            <%--                                 style="border-radius:50% 50% 50% 50%">--%>
            <%--                        </td>--%>
            <%--                        <td>--%>
            <%--                                ${ht.chiTietSanPham.sanPham.ten} - ${ht.chiTietSanPham.mauSac.ten}--%>
            <%--                            - ${ht.chiTietSanPham.ram.dungLuong} - ${ht.chiTietSanPham.rom.dungLuong}.--%>
            <%--                        </td>--%>
            <%--                        <td>--%>

            <%--                                ${100-ht.donGiaKhiGiam/ht.donGia*100} %--%>
            <%--                        </td>--%>

            <%--                        <td>--%>
            <%--                                ${banhangonline.convertgiatien(ht.donGiaKhiGiam)}  VND--%>
            <%--                        </td>--%>
            <%--                        <td>--%>
            <%--                                ${ht.soLuong}--%>
            <%--                        </td>--%>
            <%--                        <td>${banhangonline.convertgiatien(ht.donGiaKhiGiam*ht.soLuong)}--%>
            <%--                            VND--%>
            <%--                        </td>--%>
            <%--                        <td>--%>
            <%--                            <c:if test="${hd.tinhTrang==0}">--%>
            <%--                                <p>Chờ xử lý</p>--%>
            <%--                            </c:if>--%>
            <%--                            <c:if test="${hd.tinhTrang==1}">--%>
            <%--                                <p>Đã xác nhận</p>--%>
            <%--                            </c:if>--%>
            <%--                            <c:if test="${hd.tinhTrang==2}">--%>
            <%--                                <p>Đã Thanh toán</p>--%>
            <%--                            </c:if>--%>
            <%--                            <c:if test="${hd.tinhTrang==3}">--%>
            <%--                                <p>Chờ thanh toán</p>--%>
            <%--                            </c:if>--%>
            <%--                            <c:if test="${hd.tinhTrang==8}">--%>
            <%--                                <p>Đã hủy</p>--%>
            <%--                            </c:if>--%>
            <%--                        </td>--%>
            <%--&lt;%&ndash;                        <td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                            <c:if test="${hd.tinhTrangGiaoHang==0}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;                                <c:if test="${ht.hoaDon.tinhTrang==0}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;                                    <a onclick="xoahdct('${ht.id}');" class="btn btn-primary">Hủy</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                                </c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                            </c:if>&ndash;%&gt;--%>

            <%--&lt;%&ndash;                        </td>&ndash;%&gt;--%>
            <%--                    </tr>--%>
            <%--                    <c:set var="dem" scope="session" value="${dem+1}"/>--%>
            <%--                </c:if>--%>
            <%--            </c:if>--%>

            <tr>
                <td>
                    <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40" height="40"
                         style="border-radius:50% 50% 50% 50%">
                </td>
                <td>
                        ${ht.chiTietSanPham.sanPham.ten} - ${ht.chiTietSanPham.mauSac.ten}
                    - ${ht.chiTietSanPham.ram.dungLuong} - ${ht.chiTietSanPham.rom.dungLuong}.
                </td>
                <td>

                        ${ht.phantram()} %
                </td>

                <td>
                        ${banhangonline.convertgiatien(ht.donGiaKhiGiam)} VND
                </td>
                <td>
                        ${ht.soLuong}
                </td>
                <td>${banhangonline.convertgiatien(ht.donGiaKhiGiam*ht.soLuong)}
                    VND
                </td>
                <td>
                    <c:if test="${hd.tinhTrang==0}">
                        <p>Chờ xử lý</p>
                    </c:if>
                    <c:if test="${hd.tinhTrang==1}">
                        <p>Đã xác nhận</p>
                    </c:if>
                    <c:if test="${hd.tinhTrang==2}">
                        <p>Đã Thanh toán</p>
                    </c:if>
                    <c:if test="${hd.tinhTrang==3}">
                        <p>Chờ thanh toán</p>
                    </c:if>
                    <c:if test="${hd.tinhTrang==8}">
                        <p>Đã hủy</p>
                    </c:if>
                </td>
                    <%--                    <td>--%>
                    <%--                        <c:if test="${hd.tinhTrangGiaoHang==0}">--%>
                    <%--                            <c:if test="${ht.hoaDon.tinhTrang==0}">--%>
                    <%--                                <a onclick="xoahdct('${ht.id}');" class="btn btn-primary">Hủy</a>--%>
                    <%--                            </c:if>--%>
                    <%--                        </c:if>--%>

                    <%--                    </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>


    <c:if test="${not empty listdtct}">
        <table class="table table-striped" style="text-content: center;width: 90%;margin-left: 5%;margin-bottom: 100px">

            <h3 style="text-align: center">Thông tin đổi trả</h3>
            <thead>
            <tr>
                <th>Ảnh sản phẩm</th>
                <th>Sản phẩm</th>
                <th>Ảnh sản phẩm đổi trả</th>
                <th>Sản phẩm đổi trả</th>
                <th>Tình trạng</th>

            </tr>
            </thead>
            <c:forEach items="${listdtct}" var="ht" varStatus="stt">
                <tbody>


                <c:if test="${stt.index==0}">
                    <tr>

                        <td>
                            <img src="../../../uploads/${ht.hoaDonChiTiet.imei.chiTietSanPham.urlAnh}" width="40"
                                 height="40"
                                 style="border-radius:50% 50% 50% 50%">
                        </td>
                        <td>
                                ${ht.hoaDonChiTiet.imei.chiTietSanPham.sanPham.ten}
                            - ${ht.hoaDonChiTiet.imei.chiTietSanPham.mauSac.ten}
                            - ${ht.hoaDonChiTiet.imei.chiTietSanPham.ram.dungLuong}
                            - ${ht.hoaDonChiTiet.imei.chiTietSanPham.rom.dungLuong}.
                        </td>
                        <td>
                            <img src="../../../uploads/${ht.imei.chiTietSanPham.urlAnh}" width="40" height="40"
                                 style="border-radius:50% 50% 50% 50%">
                        </td>

                        <td>

                                ${ht.imei.chiTietSanPham.sanPham.ten} - ${ht.imei.chiTietSanPham.mauSac.ten}
                            - ${ht.imei.chiTietSanPham.ram.dungLuong} - ${ht.imei.chiTietSanPham.rom.dungLuong}.
                        </td>


                        <td>
                            <c:if test="${ht.doiTra.tinhTrang==0}">
                                <p>Chờ đổi trả</p>
                            </c:if>
                            <c:if test="${ht.doiTra.tinhTrang==1}">
                                <p>Từ chối</p>
                            </c:if>
                            <c:if test="${ht.doiTra.tinhTrang==2}">
                                <p>Đã đổi trả</p>
                            </c:if>

                        </td>

                    </tr>

                </c:if>


                </tbody>

                <c:if test="${ht.doiTra.tinhTrang==0}">
                    <a class="btn btn-primary" style="background-color: #ff0000;margin-bottom: -300px;margin-left: 95%"
                       href="/ban-hang-online/huy/${ht.doiTra.id}" type="button">Hủy</a>
                </c:if>
            </c:forEach>
                <%--        <a href="/ban-hang-online/huy/${ht.doiTra.id}" type="button">Hủy</a>--%>


        </table>
    </c:if>

    <div class="modal" id="modalthaydoithongtinnhanhang">

        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content" style="">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Thay đổi thông tin nhận hàng</h4>
                </div>
                <form action="/ban-hang-online/hoa-don-chi-tiet/cap-nhat-thong-tin-dat-hang" method="post">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <input type="text" value="${hd.id}" name="idhd" style="display: none">
                        <br>
                        <div>Người nhận:<label style="background: white;color: red;border: 1px solid white"
                                               id="tbnguoinhan"></label></div>
                        <br>
                        <input type="text" id="ktnguoinhan" name="nguoinhan" value="${hd.nguoiNhan}"
                               style="width: 100%">
                        <br><br>
                        <div>Số điện thoại:<label style="background: white;color: red;border: 1px solid white"
                                                  id="sodienthoai"></label></div>
                        <br>
                        <input type="text" id="b" name="sodienthoai" value="${hd.sdt}" style="width: 100%">
                        <br>
                        <br>
                        <div>Địa chỉ nhận hàng:<label style="background: white;color: red;border: 1px solid white"
                                                      id="c">${tbdiachihanoi}</label></div>
                        <br>
                        <button type="button" style="float: right" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#modalthemdiachidathang">Thêm địa chỉ
                        </button>
                        <select id="diachids11" name="diachi" style="width: 70%;height: 1cm">
                            <c:forEach items="${banhangonline.Listdiachimotkhachang(idkhachhang)}" var="ht">
                                <option value="${ht.id}" ${ht.id==hd.id ?"selected":""} style="height: 1cm">
                                        ${ht.diaChi},${ht.quan},${ht.huyen},${ht.thanhPho}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger" onclick="return checksdt();">Cập nhật</button>
                        <%--                <button type="button" style="display: none" id="btthemdiachidathang" data-bs-dismiss="modal"></button>--%>
                    </div>


                </form>
            </div>
        </div>
    </div>

    <div class="modal" id="modalthemdiachidathang">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content " style="">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Thêm địa chỉ</h4>
                </div>
                <!-- Modal body -->
                <form action="/ban-hang-online/hoa-don-chi-tiet/them-dia-chi" method="post">
                    <div class="modal-body">

                        <div style="margin-left:2.5cm">

                            <div>Tỉnh/thành:<label style="background: white;color: red;border: 1px solid white"
                                                   id="tb4"></label></div>
                            <select name="" id="province" style="width: 5cm">
                                <%--                                    <option  value="null">Tỉnh/thành</option>--%>
                            </select>
                            <input type="text" id="themdiachidathangthanhpho" name="thanhpho" style="display: none"><br>


                            <div>Quận/Huyện:<label style="background: white;color: red;border: 1px solid white"
                                                   id="tb2"></label></div>
                            <select name="" id="district" style="width: 5cm">
                                <%--                                    <option  value="null">Quận/Huyện</option>--%>
                            </select>
                            <input type="text" id="themdiachidathangquan" name="quan" style="display: none"><br>

                            <div>Phường/xã:<label style="background: white;color: red;border: 1px solid white"
                                                  id="tb3"></label></div>
                            <select name="" id="ward" style="width: 5cm">
                                <%--                                    <option   value="null">Phường/xã</option>--%>
                            </select>
                            <input type="text" id="themdiachidathanghuyen" name="huyen" style="display: none"><br>

                            <div>Địa chỉ cụ thể:<label style="background: white;color: red;border: 1px solid white"
                                                       id="tb1"></label></div>
                            <input type="text" id="themdiachidathangdiachi" name="diachi" style="width: 5cm"><br>

                            <%--                            <h2 id="result"></h2>--%>
                        </div>
                        <input type="text" value="${hd.id}" name="idhd" style="display: none">

                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger" onclick="return themdiachikhachhang();">Thêm
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%----%>

    <button style="display: none" type="button" class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#myModal1" id="thongbaotrangthaigiaohangbat">0000000
    </button>
    <!-- The Modal -->
    <div class="modal" id="myModal1">
        <div class="modal-dialog" style="border: 1px solid red;margin-top: 3cm;">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Modal Heading</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            id="thongbaotrangthaigiaohangtat"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    Hàng đang giao không thể thay đổi thông tin giao hàng
                </div>

            </div>
        </div>
    </div>

</main>

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
<!-- /FOOTER -->

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


    if (${hienmodal==1}) {
        document.getElementById('capnhatthongtingiaohang').click();
    }


    <c:if test="${thongbaotinhtranggiaohang==1}">
    document.getElementById('thongbaotrangthaigiaohangbat').click();
    setTimeout(function () {
        document.getElementById('thongbaotrangthaigiaohangtat').click();
    }, 2000); // 2000 milliseconds tương đương với 2 giây

    </c:if>



    // function openModal() {
    //     document.getElementById('bat').click();
    // }
    //
    // setInterval(openModal, 2000);
    function loadgiaodienhoadonchitiet(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('contentghct');
                content.innerHTML = data;
                thanhtienbenghct();

                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        document.getElementById('thanhlocctsp').style.display = 'none';

    }

    function loadgiaodiendiacihdct(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giaodiendiachihdct');
                content.innerHTML = data;
                thanhtienbenghct();
                loadScripts();
                loadSelect2diachi();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


        //
    }


    function loadScripts() {
        const scriptsToLoad = [
            'https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js',
            'https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js',
            'https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js',
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
                loadSelect2diachi();
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    function loadSelect2diachi() {
        $('#diachids11').select2({
            theme: 'bootstrap-5',
            dropdownParent: $('#modalthaydoithongtinnhanhang')

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
    <c:if test="${batmodalcapnhathdctkh==0}">
    document.getElementById('capnhatthongtingiaohang').click();
    </c:if>
</script>
<script>
    var searchResultsContainer = document.getElementById("searchResults");

    document.getElementById("searchInput").addEventListener("input", function () {
        var searchValue = this.value.trim().toLowerCase();
        showSearchResults(searchValue);
    });

    function showSearchResults(searchValue) {
        if (searchValue.length > 0) {
            // document.getElementById('saochep').innerHTML=   document.getElementById('searchInput').value
            loadgiaodientimkiemTrangchu("/ban-hang-online/trang-chu/tim-kiem-loc/" + document.getElementById('searchInput').value);
            searchResultsContainer.style.display = "";
        } else {
            // Nếu không có ký tự nào, ẩn kết quả
            searchResultsContainer.style.display = "none";
        }
    }


</script>
<script>
    function loadgiaodientimkiemTrangchu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('danhsachloctimkiemTT');
                content.innerHTML = data;

            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }
</script>
<!-- jQuery Plugins -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
<script src="/jsbanhang/API.js"></script>
</body>
</html>