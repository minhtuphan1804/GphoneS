<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%--phan trang--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Electro - HTML Ecommerce Template</title>
    <%--căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>


    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/bootstrap.min.css"/>

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
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>


    <style>


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


        /* Khi label được nhấp vào, thay đổi màu nền để biểu thị lựa chọn */
        input[type="radio"]:checked + label {

            color: red;
        }
        input[type="radio"]:checked + label div{

            display: block;
        }
        label div {
            display: none;
        }
        .product-info-table {
            border-collapse: collapse; /* Loại bỏ khoảng cách giữa các ô */
            width: 100%; /* Đảm bảo bảng rộng 100% */
        }

        .product-info-table tr {
            border-bottom: 1px solid #ccc; /* Thêm dòng kẻ dưới mỗi hàng */
        }

        .product-info-table td {
            padding: 8px; /* Tùy chỉnh khoảng cách nội dung trong ô */
        }

        .info-label {
            font-weight: bold; /* In đậm nhãn */
        }

        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: #fff;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            border: 1px solid #000;
        }

        .quantity-control {
            display: flex;
            align-items: center;
            justify-content: flex-end;

        }

        .qty-button {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 18px;
            padding: 5px 10px;
            margin: 0;
        }

        .qty-input {
            width: 40px;
            text-align: center;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .qty-button.qty-down {
            border-radius: 3px 0 0 3px;
        }

        .qty-button.qty-up {
            border-radius: 0 3px 3px 0;
        }

        .qty-button:hover {
            background-color: #2471a3;
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
<%--style="position: fixed;--%>
<%--top: 50%;left: 50%;transform: translate(-50%,-50%);--%>
<%--display: none;z-index: 2;width: 7cm;height: 3cm;--%>
<%--background-color: #0b3564;text-align: center;--%>
<%--color: white;border-radius: 5% 5% 5% 5%"--%>

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
                        <!-- Wishlist -->
                        <div>
                            <%--                            <a href="#">--%>
                            <%--                                <i class="fa fa-heart-o"></i>--%>
                            <%--                                <span>Your Wishlist</span>--%>
                            <%--                                <div class="qty">2</div>--%>
                            <%--                            </a>--%>
                        </div>
                        <!-- /Wishlist -->

                        <!-- Cart -->


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
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->
<br><br>
<div class="section shadow mb-5 bg-body-tertiary rounded" style="width:80%;margin-left: 10%" >
<div id="thanhlocxemchitietsanpham">
    <p id="vt"></p>
    <div style="position: absolute;margin-left: 46%;width: 43%;margin-top: 8%;z-index: 2;">
        <p style="display: none" id="tenspctsp">${motctsp.sanPham.ten}</p>

        <label style="">Màu sắc: </label>

        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">

            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="ms${stt1.index}" name="mauSac1"
                           value="${ht1.mauSac.ten}" ${ht1.mauSac.ten==motctsp.mauSac.ten ?"checked":""}
                           onchange="clickradio2lan();">
                    <label for="ms${stt1.index}"
                           style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">

                        <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                            ${ht1.mauSac.ten}

                    </label>
                </c:if>
            </c:if>

            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var="checkck" scope="session" value="${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0"
                               end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.mauSac.ten==checkht1.mauSac.ten}">
                                <c:set var="checkck" scope="session" value="${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="ms${stt1.index}" name="mauSac1"
                               value="${ht1.mauSac.ten}" ${ht1.mauSac.ten==motctsp.mauSac.ten ?"checked":""}
                               onchange="clickradio2lan();">
                        <label for="ms${stt1.index}"
                               style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                            <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                                ${ht1.mauSac.ten}</label>

                    </c:if>
                    <c:set var="checkck" scope="session" value="${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>
        <br>
        <br>
        <label>Rom: </label>
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">

            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="rom${stt1.index}" name="rom1"
                           value="${ht1.rom.dungLuong}" ${ht1.rom.dungLuong==motctsp.rom.dungLuong ?"checked":""}
                           onchange="clickradio2lan();">
                    <label for="rom${stt1.index}"
                           style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                        <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                            ${ht1.rom.dungLuong}</label>
                </c:if>
            </c:if>

            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var="checkck" scope="session" value="${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0"
                               end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.rom.dungLuong==checkht1.rom.dungLuong}">
                                <c:set var="checkck" scope="session" value="${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="rom${stt1.index}" name="rom1"
                               value="${ht1.rom.dungLuong}" ${ht1.rom.dungLuong==motctsp.rom.dungLuong ?"checked":""}
                               onchange="clickradio2lan();">
                        <label for="rom${stt1.index}"
                               style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                            <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                                ${ht1.rom.dungLuong}</label>
                    </c:if>
                    <c:set var="checkck" scope="session" value="${-1}"/>
                </c:if>
            </c:if>
        </c:forEach>
        <br>
        <br>
        <label>Ram: </label>
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">
            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="ram${stt1.index}" name="ram1"
                           value="${ht1.ram.dungLuong}" ${ht1.ram.dungLuong==motctsp.ram.dungLuong ?"checked":""}
                           onchange="clickradio2lan();">
                    <label for="ram${stt1.index}"
                           style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                        <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                            ${ht1.ram.dungLuong}</label>
                </c:if>
            </c:if>
            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var="checkck" scope="session" value="${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0"
                               end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.ram.dungLuong==checkht1.ram.dungLuong}">
                                <c:set var="checkck" scope="session" value="${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="ram${stt1.index}" name="ram1"
                               value="${ht1.ram.dungLuong}" ${ht1.ram.dungLuong==motctsp.ram.dungLuong ?"checked":""}
                               onchange="clickradio2lan();">
                        <label for="ram${stt1.index}"
                               style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                            <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                                ${ht1.ram.dungLuong}</label>
                    </c:if>
                    <c:set var="checkck" scope="session" value="${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>
        <br>
        <br>
        <label>Chip: </label>
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">
            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="chip${stt1.index}" name="chip1"
                           value="${ht1.chip.ten}" ${ht1.chip.ten==motctsp.chip.ten ?"checked":""}
                           onchange="clickradio2lan();">
                    <label for="chip${stt1.index}"
                           style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                        <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                            ${ht1.chip.ten}</label>
                </c:if>
            </c:if>
            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var="checkck" scope="session" value="${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0"
                               end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.chip.ten==checkht1.chip.ten}">
                                <c:set var="checkck" scope="session" value="${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="chip${stt1.index}" name="chip1"
                               value="${ht1.chip.ten}" ${ht1.chip.ten==motctsp.chip.ten ?"checked":""}
                               onchange="clickradio2lan();">
                        <label for="chip${stt1.index}"
                               style="border: 1px solid #e0e0e0;heightfont-size:13px;margin-left:5px">
                            <div style="color:white;margin-left: 10px;
                        background-color: red;float: right;
                        border-radius: 100% 0 0 0;height: 100%">✔</div>
                                ${ht1.chip.ten}</label>
                    </c:if>
                    <c:set var="checkck" scope="session" value="${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>
    </div>
</div>

<main id="content1" style=" ">
    <!-- SECTION -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div style="">
                <%--            <!-- Product details -->--%>
                <div style="width: 42%;float: right">
                    <div class="product-details">
                        <h2 class="product-name">${motctsp.sanPham.ten}</h2>

                        <div>
<%--                            <h4 class="product-price" ><span--%>
<%--                                    style="font-size:15px"></span>${banhangonline.sotienkhidagiam(motctsp.id)}₫ ---%>
<%--                                <del class="product-old-price">${motctsp.basoOchammotlam()}<span style="font-size:15px">₫</span>--%>
<%--                                </del>--%>
<%--                            </h4>--%>
                            <c:if test="${banhangonline.tonggiamgia(motctsp.id)>0}">
                                <h4 class="product-price" ><span
                                        style="font-size:15px"></span>${banhangonline.sotienkhidagiam(motctsp.id)}₫ -
                                    <del class="product-old-price">${motctsp.basoOchammotlam()}<span style="font-size:15px">₫</span>
                                    </del>
                                </h4>
                            </c:if>
                            <c:if test="${banhangonline.tonggiamgia(motctsp.id)<=0}">
<%--                                <h4 class="product-price">--%>
<%--                                        &lt;%&ndash;                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}&ndash;%&gt;--%>
<%--                                        ${ht.basoOchammotlam()}--%>
<%--                                    <span> ₫</span>--%>
<%--                                </h4>--%>
                                <h4 class="product-price" ><span
                                        style="font-size:15px"></span> ${motctsp.basoOchammotlam()}₫
                                </h4>
                            </c:if>

                        </div>
                        <div class="add-to-cart" style="margin-top: 5cm">

                            <c:if test="${idkhachhang=='1'}">
                                <div class="qty-label">
                                    <div class="" style="margin-left: 0cm">


                                        <label>Số lượng :</label>
                                        <div class="qty-label">
                                            <div class="" style="margin-left: 0cm">
                                                <BUTTON class="qty-down">-</BUTTON>
                                                <input type="number" value="1" min="1"
                                                       max="${banhangonline.soluongcon(motctsp.id)}" id="input2"
                                                       style="width: 2cm" name="solg">
                                                <BUTTON class="qty-up">+</BUTTON>
                                            </div>
                                        </div>
                                        <label style="background: white;border: 1px solid white">Số lượng còn
                                            :${banhangonline.soluongcon(motctsp.id)}</label>
                                        <br>
                                        <label style="background: white;color: red;border: 1px solid white"
                                               id="thongbaosoluong"></label><br>
                                        <a href="/login">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Thêm vào
                                                giỏ hàng
                                            </button>
                                            <a href="/ban-hang-online/chi-tiet-san-pham/${motctsp.id}"
                                               id="loadlaictsp"></a>
                                        </a>
                                        <a href="/login">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Mua ngay
                                            </button>
                                        </a>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${idkhachhang!='1'}">

                                <form action="" method="post" id="formctsp">
                                    <label>Số lượng :</label>
                                    <div class="qty-label">
                                        <div class="" style="margin-left: 0cm">
                                            <BUTTON class="qty-down" type="button">-</BUTTON>
                                            <input type="number" value="0" min="0"
<%--                                                   max="${banhangonline.soluongcon(motctsp.id)-banhangonline.sl1ctsptronggh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId(),motctsp.id)}"--%>
                                                   max="${banhangonline.soluongcon(motctsp.id)}"
                                                   id="input2" style="width: 2cm" name="solg">
                                            <BUTTON class="qty-up" type="button">+</BUTTON>
                                        </div>
                                    </div>


                                    <label style="background: white;border: 1px solid white">Số lượng còn
                                        :${banhangonline.soluongcon(motctsp.id)}</label>
                                    <br>
                                    <label style="background: white;color: red;border: 1px solid white"
                                           id="thongbaosoluong"></label><br>
                                        <%--                            <p>${idkhachhang}----${motctsp.id}</p>--%>
                                    <button class="add-to-cart-btn" type="button"
                                            onclick="thongbaothemvaogiohang('${motctsp.id}','${banhangonline.soluongcon(motctsp.id)-banhangonline.sl1ctsptronggh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId(),motctsp.id)}');">
                                        <i
                                            class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng
                                    </button>




                                    <button class="add-to-cart-btn" id="btthanhtoam" type="button"
                                            <c:if test="${banhangonline.tonggiamgia(motctsp.id)<=0}">
                                            onclick="clickthanhtoanctsp('${motctsp.giaBan}')"
                                    </c:if>
                                            <c:if test="${banhangonline.tonggiamgia(motctsp.id)>0}">


                                                onclick="clickthanhtoanctsp('${banhangonline.sotienkhidagiam3(motctsp.id)}')"

                                            </c:if>

                                                ><i class="fa fa-shopping-cart"></i> Mua ngay

                                    </button>




                                    <input style="display:none;" value="${idkhachhang}" name="idkh">
                                    <input style="display:none;" value="${motctsp.id}" name="idctsp">

                                </form>
                            </c:if>


                        </div>


                        <div class="product-description">

                        </div>

                        <div class="row">
                            <button type="button" class=" btn btn-info" data-bs-toggle="modal"
                                    data-bs-target="#myModalxemctsp">
                                Xem thêm cấu hình chi tiết
                            </button>


                            <!-- The Modal -->
                            <div class="modal" id="myModalxemctsp">
                                <div class="modal-dialog">
                                    <div class="modal-content" style="margin-top: 6cm">

                                        <h4>Thông số kỹ thuật ${motctsp.sanPham.ten} ${motctsp.ram.dungLuong} ${motctsp.rom.dungLuong}</h4>
                                        <table class="product-info-table">
                                            <tr>
                                                <td class="info-label">Hãng sản phẩm:</td>
                                                <td>${motctsp.sanPham.hangSanPham.ten}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Camera:</td>
                                                <td>${motctsp.sanPham.camera.thongSo}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Màn:</td>
                                                <td>${motctsp.sanPham.manHinh.thongSo}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Màu:</td>
                                                <td>${motctsp.mauSac.ten}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Ram:</td>
                                                <td>${motctsp.ram.dungLuong}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Rom:</td>
                                                <td>${motctsp.rom.dungLuong}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Pin:</td>
                                                <td>${motctsp.pin.loaiPin}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Dung lượng pin:</td>
                                                <td>${motctsp.pin.dungLuongPin.thongSo}</td>
                                            </tr>
                                            <tr>
                                                <td class="info-label">Chip:</td>
                                                <td>${motctsp.chip.ten}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

                <!-- /Product details -->
                <!-- Product main img -->
                <%--            class="col-md-5 col-md-push-2"--%>
                <div style="height: 48%;width: 50%;margin-left: 2%">
                    <div id="product-main-img">
                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt="" style="width: 100%;height: 12cm">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>
                    </div>
                </div>
                <!-- /Product main img -->


                <!-- Product thumb imgs -->
                <%--            class="col-md-2  col-md-pull-5"--%>
                <div style=" width: 17.2cm;  height: 5cm;" align="center">
                    <div id="product-imgs"
                         style=" width: 5cm;height: 16cm;margin-top: -5.5cm; transform: rotate(270deg);">

                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>
                    </div>
                </div>
                <div class="col-md-12" style="margin-top: 4cm">
                    <div id="product-tab">
                        <!-- product tab nav -->
                        <ul class="tab-nav">
                            <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                            <li><a data-toggle="tab" href="#tab2">Details</a></li>
                            <li><a data-toggle="tab" href="#tab3">Reviews (3)</a></li>
                        </ul>
                        <!-- /product tab nav -->

                        <!-- product tab content -->
                        <div class="tab-content">
                            <!-- tab1  -->
                            <div id="tab1" class="tab-pane fade in active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                            tempor
                                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                            nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                            consequat.
                                            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                                            eu
                                            fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt
                                            in
                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab1  -->

                            <!-- tab2  -->
                            <div id="tab2" class="tab-pane fade in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                            tempor
                                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                            nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                            consequat.
                                            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                                            eu
                                            fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt
                                            in
                                            culpa qui officia deserunt mollit anim id est laborum.</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab2  -->

                            <!-- tab3  -->
                            <div id="tab3" class="tab-pane fade in">
                                <div class="row">
                                    <!-- Rating -->
                                    <div class="col-md-3">
                                        <div id="rating">
                                            <div class="rating-avg">
                                                <span>4.5</span>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                            </div>
                                            <ul class="rating">
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div style="width: 80%;"></div>
                                                    </div>
                                                    <span class="sum">3</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div style="width: 60%;"></div>
                                                    </div>
                                                    <span class="sum">2</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /Rating -->

                                    <!-- Reviews -->
                                    <div class="col-md-6">
                                        <div id="reviews">
                                            <ul class="reviews">
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do
                                                            eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do
                                                            eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do
                                                            eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                            </ul>
                                            <ul class="reviews-pagination">
                                                <li class="active">1</li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /Reviews -->

                                    <!-- Review Form -->
                                    <div class="col-md-3">
                                        <div id="review-form">
                                            <form class="review-form">
                                                <input class="input" type="text" placeholder="Your Name">
                                                <input class="input" type="email" placeholder="Your Email">
                                                <textarea class="input" placeholder="Your Review"></textarea>
                                                <div class="input-rating">
                                                    <span>Your Rating: </span>
                                                    <div class="stars">
                                                        <input id="star5" name="rating" value="5" type="radio"><label
                                                            for="star5"></label>
                                                        <input id="star4" name="rating" value="4" type="radio"><label
                                                            for="star4"></label>
                                                        <input id="star3" name="rating" value="3" type="radio"><label
                                                            for="star3"></label>
                                                        <input id="star2" name="rating" value="2" type="radio"><label
                                                            for="star2"></label>
                                                        <input id="star1" name="rating" value="1" type="radio"><label
                                                            for="star1"></label>
                                                    </div>
                                                </div>
                                                <button class="primary-btn">Submit</button>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- /Review Form -->
                                </div>
                            </div>
                            <!-- /tab3  -->
                        </div>
                        <!-- /product tab content  -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</div>
<br>
<br>
<div class="section shadow mb-5 bg-body-tertiary rounded" style="width:80%;margin-left: 10%" >
<h2 style="text-align: center;font-family: 'Times New Roman'; color: red">Sản Phẩm Liên Quan</h2>
<br>
<br>
<div id="demo11" class="carousel slide" data-bs-ride="false">
    <!-- Indicators/dots -->
    <div class="carousel">
        <div class="carousel-inner">
            <c:set var="vitri" scope="session" value="${-1}"/>
            <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
            <c:set var="salary" scope="session" value="${1}"/>
            <c:if test="${trang.index <2}">
            <div class="carousel-item active">
                </c:if>
                <c:if test="${trang.index >=2}">
                <div class="carousel-item " >
                    </c:if>
                    <div class="container px-0 px-lg-4 mt-0" >
                        <div class="row gx-0 gx-lg-0 row-cols-0 row-cols-md-0 row-cols-xl-4 justify-content-center"
                             style="width: 100%;">
                            <c:forEach items="${listsp}" var="ht" varStatus="stt">
                                <c:if test="${banhangonline.soluongcon(ht.id)>0}">
                                    <c:if test="${stt.index > vitri }">
                                        <%--                            phân trang số 9 là 8 dữ liệu--%>
                                        <c:if test="${salary <9}">
                                            <!-- product -->
                                            <div class="product" style="margin-left: 1%;width: 24%;">
                                                <a href="/ban-hang-online/chi-tiet-san-pham/${ht.id}">
                                                    <div class="product-img">
                                                        <img src="/uploads/${ht.urlAnh}" style="width: 100%;height: 6cm"
                                                             alt="">
                                                        <c:if test="${giamgia.tonggiamgia(ht.id)>0}">
                                                            <div class="product-label">
                                                                <span class="sale">-${giamgia.tonggiamgia(ht.id)}%</span>
                                                                <span class="new">Giảm giá</span>
                                                            </div>
                                                        </c:if>

                                                    </div>
                                                    <div class="product-body"
                                                         style="text-align: left;word-wrap: break-word;">
                                                        <p class="product-category">Điện thoại</p>
                                                        <h3 class="product-name"><a href="#">${ht.sanPham.ten}</a></h3>
                                                            <%--                                                        <div>Đã bán :${banhangonline.soluongdaban(ht.id)}</div>--%>
                                                        <c:if test="${giamgia.tonggiamgia(ht.id)>0}">
                                                            <h4 class="product-price">
                                                                    <%--                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}--%>
                                                                    ${banhangonline.sotienkhidagiam2(ht.id)}
                                                                <span> ₫</span>
                                                                <del class="product-old-price"
                                                                     style="float: right">${ht.basoOchammotlam2()}<span>₫</span>
                                                                </del>
                                                            </h4>
                                                        </c:if>
                                                        <c:if test="${giamgia.tonggiamgia(ht.id)<=0}">
                                                            <h4 class="product-price">
                                                                    <%--                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}--%>
                                                                    ${ht.basoOchammotlam2()}
                                                                <span> ₫</span>
                                                            </h4>
                                                        </c:if>
                                                            <%--                                                    <div class="product-rating"></div>--%>
                                                            <%--                                                    <div class="product-btns" align="center">--%>
                                                            <%--                                                        <button class="add-to-compare"><a href="/homes"><i--%>
                                                            <%--                                                                class="fa fa-exchange"></i></a><span class="tooltipp">Thêm để so sánh</span>--%>
                                                            <%--                                                        </button>--%>
                                                            <%--                                                        <button class="quick-view"><a--%>
                                                            <%--                                                                href="/ban-hang-online/chi-tiet-san-pham/${ht.id}"><i--%>
                                                            <%--                                                                class="fa fa-eye"></i></a><span class="tooltipp">Xem chi tiết</span>--%>
                                                            <%--                                                        </button>--%>
                                                            <%--                                                    </div>--%>
                                                    </div>
                                                </a>
                                            </div>
<%--                                            <div class="product" style="margin-left: 1%;width: 24%;">--%>
<%--                                                <div class="product-img">--%>
<%--                                                    <img src="../../../uploads/${ht.urlAnh}"--%>
<%--                                                         style="width: 100%;height: 6cm;" alt="">--%>
<%--                                                    <c:if test="${giamgia.tonggiamgia(ht.id)>0}">--%>
<%--                                                        <div class="product-label">--%>
<%--                                                            <span class="sale">-${giamgia.tonggiamgia(ht.id)}%</span>--%>
<%--                                                            <span class="new">Giảm giá</span>--%>
<%--                                                        </div>--%>
<%--                                                    </c:if>--%>
<%--                                                </div>--%>
<%--                                                <div class="product-body"--%>
<%--                                                     style="text-align: left;word-wrap: break-word;z-index: 2">--%>
<%--                                                        &lt;%&ndash;                    <p class="product-category">Điện thoại</p>&ndash;%&gt;--%>
<%--                                                    <h3 class="product-name"><a href="#">${ht.sanPham.ten}</a></h3>--%>
<%--                                                            <c:if test="${giamgia.tonggiamgia(ht.id)>0}">--%>
<%--                                                                <h4 class="product-price">--%>
<%--                                                                        &lt;%&ndash;                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}&ndash;%&gt;--%>
<%--                                                                        ${banhangonline.sotienkhidagiam(ht.id)}--%>
<%--                                                                    <span> ₫</span>--%>
<%--                                                                    <del class="product-old-price"--%>
<%--                                                                         style="float: right">${ht.basoOchammotlam()}<span>₫</span>--%>
<%--                                                                    </del>--%>
<%--                                                                </h4>--%>
<%--                                                            </c:if>--%>
<%--                                                            <c:if test="${giamgia.tonggiamgia(ht.id)<=0}">--%>
<%--                                                                <h4 class="product-price">--%>
<%--                                                                        &lt;%&ndash;                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}&ndash;%&gt;--%>
<%--                                                                        ${ht.basoOchammotlam()}--%>
<%--                                                                    <span> ₫</span>--%>
<%--                                                                </h4>--%>
<%--                                                            </c:if>--%>
<%--                                                    <div class="product-rating"></div>--%>
<%--                                                    <div class="product-btns" align="center">--%>
<%--                                                        <button class="add-to-compare"><a href="/homes"><i--%>
<%--                                                                class="fa fa-exchange"></i></a><span class="tooltipp">Thêm để so sánh</span>--%>
<%--                                                        </button>--%>
<%--                                                        <button class="quick-view"><a--%>
<%--                                                                href="/ban-hang-online/chi-tiet-san-pham/${ht.id}"><i--%>
<%--                                                                class="fa fa-eye"></i></a><span class="tooltipp">Xem chi tiết</span>--%>
<%--                                                        </button>--%>
<%--                                                    </div>--%>

<%--                                                </div>--%>
<%--                                                <div class="add-to-cart" style="z-index: 1">--%>
<%--                                                    <c:if test="${idkhachhang!='1'}">--%>
<%--                                                        <button class="add-to-cart-btn"--%>
<%--                                                                onclick="thongbaothemvaogiohang('${ht.id}');"><i--%>
<%--                                                                class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng--%>
<%--                                                        </button>--%>
<%--                                                    </c:if>--%>
<%--                                                    <c:if test="${idkhachhang=='1'}">--%>
<%--                                                        <a href="/login">--%>
<%--                                                            <button class="add-to-cart-btn"><i--%>
<%--                                                                    class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng--%>
<%--                                                            </button>--%>
<%--                                                        </a>--%>
<%--                                                    </c:if></div>--%>
<%--                                            </div>--%>
                                            <!-- /product -->
                                            <c:set var="vitri" scope="session" value="${stt.index}"/>
                                            <c:set var="salary" scope="session" value="${salary+1}"/>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <br> <br> <br>
                </div>
                </c:forEach>
            </div>
            <!-- Indicators/dots -->
<br><br>
            <br><br>
            <div class="carousel text-center">
                <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev" style="background-color: white">
                    <<
                </button>
                <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                    <button type="button" data-bs-target="#demo11" style="background-color: white"
                            data-bs-slide-to="${trang.index-1}">${trang.index}</button>
                </c:forEach>
                <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next" style="background-color: white">
                    >>
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<br>
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
<div style="position: fixed;
top: 50%;left: 50%;transform: translate(-50%,-50%);
display: none;z-index: 2;width: 7cm;height: 3cm;
background-color: #0b3564;text-align: center;
color: white;border-radius: 5% 5% 5% 5%"
     id="thongbaothemgiohang">
    <img style="border-radius: 50% 50% 50% 50%;width: 1.2cm;height: 1.2cm;margin-top: 20px"
         src="data:image/png;base64,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">
    <h2 style="color: white;font-size: 20px;margin-top: 20px">Đã Thêm vào Giỏ hàng</h2>
</div>

<div style="position: fixed;
top: 50%;left: 50%;transform: translate(-50%,-50%);
display: none;z-index: 2;width: 7cm;height: 5cm;
background-color: #0b3564;text-align: center;
color: white;border-radius: 5% 5% 5% 5%"
     id="thongbaothemgiohang1">
    <img style="border-radius: 50% 50% 50% 50%;width: 1.2cm;height: 1.2cm;margin-top: 20px"
         src="https://banner2.cleanpng.com/20180815/olc/kisspng-clip-art-computer-icons-x-mark-check-mark-image-wrong-incorrect-delete-abort-png-image-picpng-5b744132b85bd6.9451175115343455227551.jpg"
    >

    <h2 style="color: white;font-size: 20px;margin-top: 20px">


        Chỉ chấp nhận tổng <br><= 75.000.000

    </h2>
</div>
<script>
    // /////////////////////////////////////////
    // Lấy thẻ input bằng ID
    var input2 = document.getElementById("input2");

    // Lấy nút "+" và "-" bằng class name
    var qtyUp = document.querySelector(".qty-up");
    var qtyDown = document.querySelector(".qty-down");

    // Thêm sự kiện input để kiểm tra giá trị khi người dùng nhập
    input2.addEventListener("input", function () {
        // Loại bỏ các ký tự không phải số khỏi giá trị nhập vào
        this.value = this.value.replace(/[^0-9]/g, '');

        // Lấy giá trị hiện tại của input2
        var value = parseInt(this.value, 10);

        // Lấy giá trị min và max từ thuộc tính min và max của input2
        var min = parseInt(input2.getAttribute("min"), 10);
        var max = parseInt(input2.getAttribute("max"), 10);

        // Kiểm tra giá trị nhập vào có nằm trong khoảng min->max không
        if (!isNaN(value)) {
            if (!isNaN(min) && value < min) {
                input2.value = min;
            } else if (!isNaN(max) && value > max) {
                input2.value = max;
            }
        }
    });

    // Thêm sự kiện click cho nút "+"
    qtyUp.addEventListener("click", function () {
        // alert("taco---"+input2.value)
        // Lấy giá trị hiện tại của input2
        var value = parseInt(input2.value, 10);

        // Lấy giá trị min và max từ thuộc tính min và max của input2
        var min = parseInt(input2.getAttribute("min"), 10);
        var max = parseInt(input2.getAttribute("max"), 10);

        // Tăng giá trị lên 1 đơn vị nếu không vượt quá max
        if (!isNaN(max) && value < max) {
            input2.value = value + 1;
            document.getElementById('thongbaosoluong').innerHTML = "";
        } else {
            document.getElementById('thongbaosoluong').innerHTML = "Số lượng sản phẩm này đã đạt mức tối đa để thêm  ";
        }
    });

    // Thêm sự kiện click cho nút "-"
    qtyDown.addEventListener("click", function () {
        // Lấy giá trị hiện tại của input2
        var value = parseInt(input2.value, 10);

        // Lấy giá trị min và max từ thuộc tính min và max của input2
        var min = parseInt(input2.getAttribute("min"), 10);
        var max = parseInt(input2.getAttribute("max"), 10);

        // Giảm giá trị xuống 1 đơn vị nếu không vượt quá min
        if (!isNaN(min) && value > min) {
            input2.value = value - 1;
            document.getElementById('thongbaosoluong').innerHTML = "";
        } else {
            document.getElementById('thongbaosoluong').innerHTML = "Số lượng sản phẩm này  đã đạt mức giới hạn";
        }
    });

    // Thêm sự kiện blur để kiểm tra khi người dùng bấm ra ngoài ô input
    input2.addEventListener("blur", function () {
        // Lấy giá trị hiện tại của input2
        var value = parseInt(input2.value, 10);

        // Lấy giá trị min từ thuộc tính min của input2
        var min = parseInt(input2.getAttribute("min"), 10);

        // Nếu không có giá trị nhập vào, đặt giá trị về min
        if (isNaN(value) || value < min) {
            input2.value = min;
        }
    });

    // ///////////////////////////////////////////
    function clickradio2lan() {
        clickradio();
    }

    function chonhetgiohangtongTRANGCHU(idgh) {

        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            loadgiaodienghctbanhangTrangChu(link);


        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;

            loadgiaodienghctbanhangTrangChu(link);


        }
        // clickradio();
    };

    function chonsanphamgiohangTT(vt, idctgh, idgh) {

        var vt1 = parseInt(vt);

        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;

            loadgiaodienghctbanhangTrangChu(link);


        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;

            loadgiaodienghctbanhangTrangChu(link);

        }
        // clickradio();
    };

    function thongbaothemvaogiohang(idctsp,maxgan) {
        var solgchon=document.getElementById("input2").value;
        // alert(solgchon)
 var maxkt=maxgan;
if(solgchon<=0){
    document.getElementById('thongbaosoluong').innerHTML="chưa nhập số lượng"
}else {
    if(parseInt(solgchon)>parseInt(maxkt)){
        document.getElementById('thongbaosoluong').innerHTML=" tối đa thêm tiếp ="+maxkt;
    }else {
        loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/' + idctsp+'/'+solgchon);
        clickradio();
        document.getElementById('thongbaothemgiohang').style.display = '';
        setTimeout(function () {
            document.getElementById('thongbaothemgiohang').style.display = 'none';
        }, 2000); // 2000 milliseconds tương đương với 2 giây
    }
}



    };


    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                formatAndDisplayValue("tongtienghtt");
                loadScripts1();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }

    function locbenctsp(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content1');

                content.innerHTML = data;

// /////////////////////////////////////////
// Lấy thẻ input bằng ID
                var input2 = document.getElementById("input2");

// Lấy nút "+" và "-" bằng class name
                var qtyUp = document.querySelector(".qty-up");
                var qtyDown = document.querySelector(".qty-down");

// Thêm sự kiện input để kiểm tra giá trị khi người dùng nhập
                input2.addEventListener("input", function () {
                    // Loại bỏ các ký tự không phải số khỏi giá trị nhập vào
                    this.value = this.value.replace(/[^0-9]/g, '');

                    // Lấy giá trị hiện tại của input2
                    var value = parseInt(this.value, 10);

                    // Lấy giá trị min và max từ thuộc tính min và max của input2
                    var min = parseInt(input2.getAttribute("min"), 10);
                    var max = parseInt(input2.getAttribute("max"), 10);

                    // Kiểm tra giá trị nhập vào có nằm trong khoảng min->max không
                    if (!isNaN(value)) {
                        if (!isNaN(min) && value < min) {
                            input2.value = min;
                        } else if (!isNaN(max) && value > max) {
                            input2.value = max;
                        }
                    }
                });

// Thêm sự kiện click cho nút "+"
                qtyUp.addEventListener("click", function () {
                    // alert("taco---"+input2.value)
                    // Lấy giá trị hiện tại của input2
                    var value = parseInt(input2.value, 10);

                    // Lấy giá trị min và max từ thuộc tính min và max của input2
                    var min = parseInt(input2.getAttribute("min"), 10);
                    var max = parseInt(input2.getAttribute("max"), 10);

                    // Tăng giá trị lên 1 đơn vị nếu không vượt quá max
                    if (!isNaN(max) && value < max) {
                        input2.value = value + 1;
                        document.getElementById('thongbaosoluong').innerHTML = "";
                    } else {
                        document.getElementById('thongbaosoluong').innerHTML = "Số lượng sản phẩm này trong giỏ hàng  đã đạt mức tối đa";
                    }
                });

// Thêm sự kiện click cho nút "-"
                qtyDown.addEventListener("click", function () {
                    // Lấy giá trị hiện tại của input2
                    var value = parseInt(input2.value, 10);

                    // Lấy giá trị min và max từ thuộc tính min và max của input2
                    var min = parseInt(input2.getAttribute("min"), 10);
                    var max = parseInt(input2.getAttribute("max"), 10);

                    // Giảm giá trị xuống 1 đơn vị nếu không vượt quá min
                    if (!isNaN(min) && value > min) {
                        input2.value = value - 1;
                        document.getElementById('thongbaosoluong').innerHTML = "";
                    } else {
                        document.getElementById('thongbaosoluong').innerHTML = "Số lượng sản phẩm này  đã đạt mức giới hạn";
                    }
                });

// Thêm sự kiện blur để kiểm tra khi người dùng bấm ra ngoài ô input
                input2.addEventListener("blur", function () {
                    // Lấy giá trị hiện tại của input2
                    var value = parseInt(input2.value, 10);

                    // Lấy giá trị min từ thuộc tính min của input2
                    var min = parseInt(input2.getAttribute("min"), 10);

                    // Nếu không có giá trị nhập vào, đặt giá trị về min
                    if (isNaN(value) || value < min) {
                        input2.value = min;
                    }
                });
// ///////////////////////////////////////////
                loadScripts();
                loadScripts();

            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
    }


    function loadScripts() {
        const scriptsToLoad = [
            'https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js',
            'https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js',
            'https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js',
            'https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js',
            '/jsbanhang/jquery.min.js',
            '/jsbanhang/bootstrap.min.js',
            '/jsbanhang/slick.min.js',
            '/jsbanhang/nouislider.min.js',
            '/jsbanhang/jquery.zoom.min.js',
            '/jsbanhang/main.js',


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
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    function loadScripts1() {
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
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

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
<!-- jQuery Plugins -->
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
</body>
</html>

