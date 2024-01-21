<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%--phan trang--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Electro - HTML Ecommerce Template</title>
    <%--căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />


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
    <link rel="stylesheet" href="/cssbanhang/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/style.css"/>

    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
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
        label {
            display: inline-block;
            padding: 5px 10px;
            background-color: #e0e0e0;
            border: 1px solid #ccc;
            cursor: pointer;
        }

        /* Khi label được nhấp vào, thay đổi màu nền để biểu thị lựa chọn */
        input[type="radio"]:checked + label {
            background-color: #007bff;
            color: #fff;
        }
    </style>

</head>

<body>




<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
            </ul>
            <ul class="header-links pull-right">
                <li><a href="#"><i class="fa fa-dollar"></i> USD</a></li>
                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/login"><i class="fa fa-user-o"></i> Chưa đăng nhập:<input id="tkmkidkhachhang" type="text" style="display: none" value="${idkhachhang}"></a></li>

                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <!-- Cart -->
                    <li>
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" >
                                    <span>
                                    <i class="fa fa-user-o"></i>
                                            ${khachhangdangnhap.hoTen}
                                        <input id="tkmkidkhachhang" type="text" style="display: none" value="${idkhachhang}">
                                    </span>
                            </a>
                            <div class="cart-dropdown" style="border-radius: 10px;width: 3.5cm;margin-top: 10px">
                                <div >
                                    <div >
                                        Tài khoản của tôi
                                    </div>

                                    <form action="/ban-hang-online/hoa-don-online" method="post">
                                        <input name="idkh" value="${idkhachhang}" style="display: none">
                                        <button type="submit">Đơn hàng</button>
                                    </form>
                                    <div >
                                        Đăng xuất
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
                        <a href="#" class="logo">
                            <%--							<img src="../../img/logo.png" alt="">--%>
                            <img src="/img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form>
                            <select class="input-select">
                                <option value="0">All Categories</option>
                                <option value="1">Category 01</option>
                                <option value="1">Category 02</option>
                            </select>
                            <input class="input" placeholder="Search here">
                            <button class="search-btn">Search</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <div>
                            <a href="#">
                                <i class="fa fa-heart-o"></i>
                                <span>Your Wishlist</span>
                                <div class="qty">2</div>
                            </a>
                        </div>
                        <!-- /Wishlist -->

                        <!-- Cart -->


                        <div class="dropdown" id="giohangtrangchu" >
                            <c:if test="${idkhachhang!='1'}">
                                <c:if test="${listghct.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm" >
                                        <div class="cart-list">

                                            <c:forEach items="${listghct}" var="ht" varStatus="stt">
                                                <br>
                                                <div style="border: 1px solid;height: 2cm"  >
                                                    <div style="width: 80%;float: right">

                                                        <a href="#">
                                                            Sản phẩm:${ht.chiTietSanPham.sanPham.ten},Rom:${ht.chiTietSanPham.rom.dungLuong},Màu:${ht.chiTietSanPham.mauSac.ten}.
                                                        </a>
                                                        <br>
                                                        Số lượng:${ht.soLuong}<br>
                                                            ${ht.donGiaKhiGiam}đ-<label style="text-decoration: line-through;background-color: white;border: 1px solid white">${ht.donGia}</label>đ
                                                    </div>
                                                    <div style="width: 18%;">
                                                        <input type="checkbox" name="checkidghTT" value="${ht.id}" onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>


                                                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="50" height="50" style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()} Sản phẩm được chọn</small>
                                            <h5>Tổng:${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()} đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                                <input type="checkbox" name="checktongTT" onclick="chonhetgiohangtongTRANGCHU('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
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
                                    <div class="cart-dropdown">
                                        <div class="cart-list">


                                        </div>
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

                                <a class="dropdown-toggle"  aria-expanded="true"  href="/login" >
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
                    <li ><a href="/ban-hang-online/hien-thi" >TRANG CHỦ</a></li>
                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <li ><a href="/ban-hang-online/dang-nhap/hien-thi" >TRANG CHỦ</a></li>
                </c:if>
                <li><a href="#">ƯU ĐÃI HẤP DẪN</a></li>
                <li><a href="#">LOẠI</a></li>
                <li><a href="/ban-hang-online/dien-thoai-thong-minh" >ĐIỆN THOẠI THÔNG MINH</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->





<div id="thanhlocxemchitietsanpham" >
    <p id="vt"></p>
    <div style="position: absolute;margin-left: 57%;width: 43%;margin-top: 10%;z-index: 2">
        <p style="display: none" id="tenspctsp">${motctsp.sanPham.ten}</p>
        Màu sắc:
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">

            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="ms${stt1.index}" name="mauSac1" value="${ht1.mauSac.ten}" ${ht1.mauSac.ten==motctsp.mauSac.ten ?"checked":""} onchange="clickradio2lan();" >
                    <label for="ms${stt1.index}" style="border: 1px solid #00A000">${ht1.mauSac.ten}</label>
                </c:if>
            </c:if>

            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0" end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.mauSac.ten==checkht1.mauSac.ten}">
                                <c:set var = "checkck" scope = "session" value = "${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="ms${stt1.index}" name="mauSac1" value="${ht1.mauSac.ten}" ${ht1.mauSac.ten==motctsp.mauSac.ten ?"checked":""} onchange="clickradio2lan();">
                        <label for="ms${stt1.index}" style="border: 1px solid #00A000">${ht1.mauSac.ten}</label>

                    </c:if>
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>
        <br>
        <br>
        Rom:
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">

            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="rom${stt1.index}" name="rom1" value="${ht1.rom.dungLuong}" ${ht1.rom.dungLuong==motctsp.rom.dungLuong ?"checked":""} onchange="clickradio2lan();">
                    <label for="rom${stt1.index}" style="border: 1px solid #00A000">${ht1.rom.dungLuong}</label>
                </c:if>
            </c:if>

            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0" end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.rom.dungLuong==checkht1.rom.dungLuong}">
                                <c:set var = "checkck" scope = "session" value = "${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="rom${stt1.index}" name="rom1" value="${ht1.rom.dungLuong}" ${ht1.rom.dungLuong==motctsp.rom.dungLuong ?"checked":""} onchange="clickradio2lan();">
                        <label for="rom${stt1.index}" style="border: 1px solid #00A000">${ht1.rom.dungLuong}</label>
                    </c:if>
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                </c:if>
            </c:if>
        </c:forEach>

        <br>
        <br>
        Ram:
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">
            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="ram${stt1.index}" name="ram1" value="${ht1.ram.dungLuong}" ${ht1.ram.dungLuong==motctsp.ram.dungLuong ?"checked":""} onchange="clickradio2lan();">
                    <label for="ram${stt1.index}" style="border: 1px solid #00A000">${ht1.ram.dungLuong}</label>
                </c:if>
            </c:if>
            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0" end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.ram.dungLuong==checkht1.ram.dungLuong}">
                                <c:set var = "checkck" scope = "session" value = "${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="ram${stt1.index}" name="ram1" value="${ht1.ram.dungLuong}" ${ht1.ram.dungLuong==motctsp.ram.dungLuong ?"checked":""} onchange="clickradio2lan();">
                        <label for="ram${stt1.index}" style="border: 1px solid #00A000">${ht1.ram.dungLuong}</label>
                    </c:if>
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>

        <br>
        <br>
        Chíp:
        <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="ht1" varStatus="stt1">
            <c:if test="${stt1.index==0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <input type="radio" id="chip${stt1.index}" name="chip1" value="${ht1.chip.ten}" ${ht1.chip.ten==motctsp.chip.ten ?"checked":""} onchange="clickradio2lan();">
                    <label for="chip${stt1.index}" style="border: 1px solid #00A000">${ht1.chip.ten}</label>
                </c:if>
            </c:if>
            <c:if test="${stt1.index>0}">
                <c:if test="${banhangonline.soluongcon(ht1.id)>0}">
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                    <c:forEach items="${banhangonline.ListctspTheoidsp(motctsp.sanPham.id)}" var="checkht1" begin="0" end="${stt1.index-1}">
                        <c:if test="${banhangonline.soluongcon(checkht1.id)>0}">
                            <c:if test="${ht1.chip.ten==checkht1.chip.ten}">
                                <c:set var = "checkck" scope = "session" value = "${0}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${checkck==-1}">
                        <input type="radio" id="chip${stt1.index}" name="chip1" value="${ht1.chip.ten}" ${ht1.chip.ten==motctsp.chip.ten ?"checked":""} onchange="clickradio2lan();">
                        <label for="chip${stt1.index}" style="border: 1px solid #00A000">${ht1.chip.ten}</label>
                    </c:if>
                    <c:set var = "checkck" scope = "session" value = "${-1}"/>
                </c:if>
            </c:if>

        </c:forEach>

    </div>
</div>

<main id="content1">
    <!-- SECTION -->
    <div class="section"  >
        <!-- container -->
        <div class="container"  >
            <!-- row -->
            <div  style=""  >
                <%--            <!-- Product details -->--%>
                <div  style="width: 42%;float: right">
                    <div class="product-details">
                        <h2 class="product-name">${motctsp.sanPham.ten}</h2>
                        <div>
                            <div class="product-rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                            <a class="review-link" href="#">10 Review(s) | Add your review</a>
                        </div>
                        <div>
                            <h4 class="product-price"><span style="font-size:15px">₫</span>${motctsp.giaBan-motctsp.giaBan/100*banhangonline.tonggiamgia(motctsp.id)}-<del class="product-old-price">${motctsp.giaBan}<span style="font-size:15px">₫</span></del></h4>
                            <span class="product-available">In Stock</span>
                        </div>



                        <div class="add-to-cart" style="margin-top: 7cm">
                            Số lượng :<label style="background: white;color: red;border: 1px solid white" id="thongbaosoluong"></label>
                            <br>
                            <c:if test="${idkhachhang=='1'}">
                                <div class="qty-label">
                                    <div class="" style="margin-left: 1cm">
                                        <BUTTON class="qty-down">-</BUTTON>
                                        <input type="number" value="1" min="1" max="${banhangonline.soluongcon(motctsp.id)}" id="input2" style="width: 2cm" name="solg">
                                        <BUTTON class="qty-up">+</BUTTON>
                                    </div>
                                </div>
                                <label style="background: white;border: 1px solid white">Số lượng còn :${banhangonline.soluongcon(motctsp.id)}</label>
                                <br><br>
                                <a href="/login">
                                    <button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                                    <a href="/ban-hang-online/chi-tiet-san-pham/${motctsp.id}" id="loadlaictsp"></a>
                                </a>
                                <a href="/login">
                                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Mua ngay</button>
                                </a>
                            </c:if>
                            <c:if test="${idkhachhang!='1'}">
                                <form action="" method="post" id="formctsp">

                                    <div class="qty-label">
                                        <div class="" style="margin-left: 1cm">
                                            <BUTTON class="qty-down" type="button">-</BUTTON>
                                            <input type="number" value="0" min="0" max="${banhangonline.soluongcon(motctsp.id)-banhangonline.sl1ctsptronggh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId(),motctsp.id)}" id="input2" style="width: 2cm" name="solg">
                                            <BUTTON class="qty-up" type="button">+</BUTTON>
                                        </div>
                                    </div>


                                    <label style="background: white;border: 1px solid white">Số lượng còn :${banhangonline.soluongcon(motctsp.id)}</label>
                                    <br><br>

                                        <%--                            <p>${idkhachhang}----${motctsp.id}</p>--%>
                                    <button class="add-to-cart-btn" type="button" onclick="thongbaothemvaogiohang('${motctsp.id}');"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                                    <button class="add-to-cart-btn" id="btthanhtoam" type="button" onclick="clickthanhtoanctsp()"><i class="fa fa-shopping-cart"></i> Mua ngay</button>


                                    <input style="display:none;" value="${idkhachhang}" name="idkh">
                                    <input style="display:none;" value="${motctsp.id}" name="idctsp">

                                </form>
                            </c:if>


                        </div>
                        <%--<p>${idkhachhang}</p>--%>
                        <ul class="product-btns">
                            <li><a href="#"><i class="fa fa-heart-o"></i> add to wishlist</a></li>
                            <li><a href="#"><i class="fa fa-exchange"></i> add to compare</a></li>
                        </ul>

                        <ul class="product-links">
                            <li>Category:</li>
                            <li><a href="#">Headphones</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>

                        <ul class="product-links">
                            <li>Share:</li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                        </ul>
                        <br>
                        <p>
                        <div>
                            *Hãng sản phẩm:${motctsp.sanPham.hangSanPham.ten}<br>
                            *Camera:${motctsp.sanPham.camera.thongSo}<br>
                            *Màn:${motctsp.sanPham.manHinh.thongSo}<br>
                            *Màu:${motctsp.mauSac.ten}<br>
                            *Ram:${motctsp.ram.dungLuong}<br>
                            *Rom:${motctsp.rom.dungLuong}<br>
                            *Pin:${motctsp.pin.loaiPin}<br>
                            *Dung lượng pin:${motctsp.pin.dungLuongPin.thongSo}<br>
                            *Chip:${motctsp.chip.ten}<br>
                        </div>
                        </p>

                    </div>
                </div>
                <!-- /Product details -->
                <!-- Product main img -->
                <%--            class="col-md-5 col-md-push-2"--%>
                <div  style="height: 48%;width: 50%;margin-left: 2%" >
                    <div id="product-main-img">
                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt="" style="width: 100%;height: 12cm">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt="" style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt="" style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt="" style="width: 100%;height: 12cm; " >
                        </div>
                    </div>
                </div>
                <!-- /Product main img -->



                <!-- Product thumb imgs -->
                <%--            class="col-md-2  col-md-pull-5"--%>
                <div style=" width: 17.2cm; margin-top: 10px; height: 5cm;" align="center">
                    <div id="product-imgs" style=" width: 5cm;height: 16cm;margin-top: -5.5cm; transform: rotate(270deg);">

                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt="" style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""  style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""  style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt="" style="height: 5cm;width:4.9cm;transform: rotate(90deg);" >
                        </div>
                    </div>
                </div>

                <%--            <!-- /Product thumb imgs -->--%>



                <!-- Product tab -->
                <div class="col-md-12" >
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
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab1  -->

                            <!-- tab2  -->
                            <div id="tab2" class="tab-pane fade in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
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
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
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
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
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
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
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
                                                        <input id="star5" name="rating" value="5" type="radio"><label for="star5"></label>
                                                        <input id="star4" name="rating" value="4" type="radio"><label for="star4"></label>
                                                        <input id="star3" name="rating" value="3" type="radio"><label for="star3"></label>
                                                        <input id="star2" name="rating" value="2" type="radio"><label for="star2"></label>
                                                        <input id="star1" name="rating" value="1" type="radio"><label for="star1"></label>
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
                <!-- /product tab -->

            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /SECTION -->

</main>



<!-- Carousel -->
<div id="demo11" class="carousel slide" data-bs-ride="false" >
    <!-- Indicators/dots -->
    <div class="carousel">
        <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev" >
            <<
        </button>
        <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
            <button type="button" data-bs-target="#demo11" data-bs-slide-to="${trang.index-1}"  >${trang.index}</button>
        </c:forEach>
        <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next">
            >>
        </button>
    </div>
    </button>

    <!-- The slideshow/carousel -->
    <div class="carousel-inner" >
        <c:set var = "vitri" scope = "session" value = "${-1}"/>
        <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
        <c:set var = "salary" scope = "session" value = "${1}"/>
        <c:if test = "${trang.index <2}">
        <div class="carousel-item active">
            </c:if>
            <c:if test = "${trang.index >=2}">
            <div class="carousel-item ">
                </c:if>


                <div class="container px-0 px-lg-4 mt-0">
                    <div class="row gx-0 gx-lg-0 row-cols-0 row-cols-md-0 row-cols-xl-4 justify-content-center"  style="width: 100%">
                        <c:forEach items="${listsp}" var="ht" varStatus="stt">
                            <c:if test="${banhangonline.soluongcon(ht.id)>0}">
                                <c:if test = "${stt.index > vitri }">
                                    <%--                            phân trang số 9 là 8 dữ liệu--%>
                                    <c:if test = "${salary <9}">
                                        <!-- product -->

                                        <div class="product" style="margin-left: 1%;width: 24%;" >
                                            <div class="product-img" >
                                                <img src="../../../uploads/${ht.urlAnh}" style="width: 90%;height: 6cm;margin-left: 5%" alt="">
                                                <div class="product-label">
                                                    <span class="sale">-${giamgia.tonggiamgia(ht.id)}%</span>
                                                    <span class="new">Giảm giá</span>
                                                </div>
                                            </div>
                                            <div class="product-body" style="text-align: left;word-wrap: break-word;z-index: 2">
                                                    <%--                    <p class="product-category">Điện thoại</p>--%>
                                                <h3 class="product-name"><a href="#">${ht.sanPham.ten}</a></h3>
                                                <h4 class="product-price"><span style="font-size:15px">₫</span>${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}-<del class="product-old-price">${ht.giaBan}<span style="font-size:15px">₫</span></del></h4>
                                                    <%--                                            ${ht.tinhTrang} +  ${ht.sanPham.tinhTrang}--%>
                                                <p class="product-category">Đã bán :${banhangonline.soluongdaban(ht.id)}--Còn :${banhangonline.soluongcon(ht.id)}</p>
                                                <div  >
                                                    *Hãng sản phẩm:${ht.sanPham.hangSanPham.ten}<br>
                                                    *Camera:${ht.sanPham.camera.thongSo}<br>
                                                    *Màn:${ht.sanPham.manHinh.thongSo}<br>
                                                    *Màu:${ht.mauSac.ten}<br>
                                                    *Ram:${ht.ram.dungLuong}<br>
                                                    *Rom:${ht.rom.dungLuong}<br>
                                                    *Pin:${ht.pin.loaiPin}<br>
                                                    *Dung lượng pin:${ht.pin.dungLuongPin.thongSo}<br>
                                                    *Chip:${ht.chip.ten}<br>


                                                </div>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">Thêm vào danh sách yêu thích</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">Thêm để so sánh</span></button>
                                                    <button class="quick-view" ><a href="/ban-hang-online/chi-tiet-san-pham/${ht.id}" ><i class="fa fa-exchange"></i></a><span class="tooltipp" >Xem chi tiết</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart" style="z-index: 1">
                                                <c:if test="${idkhachhang!='1'}">
                                                    <button class="add-to-cart-btn" onclick="thongbaothemvaogiohang('${ht.id}');"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                                                </c:if>
                                                <c:if test="${idkhachhang=='1'}">
                                                    <a href="/login">
                                                        <button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                                                    </a>
                                                </c:if>                                       </div>
                                        </div>
                                        <!-- /product -->
                                        <c:set var = "vitri" scope = "session" value = "${stt.index}"/>
                                        <c:set var = "salary" scope = "session" value = "${salary+1}"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <br><br><br><br>
            </div>
            </c:forEach>
        </div>
        <!-- Indicators/dots -->

        <div class="carousel">
            <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev" >
                <<
            </button>
            <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                <button type="button" data-bs-target="#demo11" data-bs-slide-to="${trang.index-1}"  >${trang.index}</button>
            </c:forEach>
            <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next">
                >>
            </button>
        </div>
    </div>
</div>
<!-- Carousel -->


<!-- NEWSLETTER -->
<div id="newsletter" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <div class="newsletter">
                    <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                    <form>
                        <input class="input" type="email" placeholder="Enter Your Email">
                        <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                    </form>
                    <ul class="newsletter-follow">
                        <li>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
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
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
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
                    <ul class="footer-payments" >
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul  >
                    <span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
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
<div style="position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);z-index: 2;width: 10cm;height: 1cm;background-color: #00FF00;text-align: center;display: none ;border: 5px solid red" id="thongbaothemgiohang">
    <h2>Đã Thêm vào Giỏ hàng</h2>
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
            document.getElementById('thongbaosoluong').innerHTML="";
        }else {
            document.getElementById('thongbaosoluong').innerHTML="Số lượng sản phẩm này trong giỏ hàng  đã đạt mức tối đa";
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
            document.getElementById('thongbaosoluong').innerHTML="";
        }else {
            document.getElementById('thongbaosoluong').innerHTML="Số lượng sản phẩm này  đã đạt mức giới hạn";
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

        if(document.getElementsByName('checktongTT')[0].checked==true){
            var    link='/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/'+idgh;
            loadgiaodienghctbanhangTrangChu(link);


        }else{
            var     link='/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/'+idgh;

            loadgiaodienghctbanhangTrangChu(link);


        }
        // clickradio();
    };
    function chonsanphamgiohangTT(vt,idctgh,idgh) {

        var vt1=parseInt(vt);

        if(document.getElementsByName('checkidghTT')[vt1].checked==true){
            var    link='/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/'+idctgh+'/0/'+idgh;

            loadgiaodienghctbanhangTrangChu(link);


        }else{
            var  link='/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/'+idctgh+'/1/'+idgh;

            loadgiaodienghctbanhangTrangChu(link);

        }
        // clickradio();
    };
    function thongbaothemvaogiohang(idctsp) {
        loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/'+idctsp);
        clickradio();
        document.getElementById('thongbaothemgiohang').style.display='';
        setTimeout(function() {
            document.getElementById('thongbaothemgiohang').style.display='none';
        }, 2000); // 2000 milliseconds tương đương với 2 giây

    };


    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
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
                        document.getElementById('thongbaosoluong').innerHTML="";
                    }else {
                        document.getElementById('thongbaosoluong').innerHTML="Số lượng sản phẩm này trong giỏ hàng  đã đạt mức tối đa";
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
                        document.getElementById('thongbaosoluong').innerHTML="";
                    }else {
                        document.getElementById('thongbaosoluong').innerHTML="Số lượng sản phẩm này  đã đạt mức giới hạn";
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

