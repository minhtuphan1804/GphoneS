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

    <title>Giỏ hàng</title>
    <%--căn đều--%>
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

        /*div{*/
        /*    border: 1px solid red;*/
        /*}*/
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
                                        <form action="/thong-tin-ca-nhan-khach-hang" method="post" style="display: none">
                                            <input value="${idkhachhang}" name="idKhachHang" style="display: none">
                                            <button style="" class="btn btn-primary" type="submit" id="taikhoancuatoi">Tài khoản của tôi</button>
                                        </form>
                                        <a  class="btn btn-primary" type="submit" onclick="anbt()">Tài khoản của tôi</a>

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
                        <a href="#" class="logo">
                            <h2 class="logo" style="margin: 20px;color: white;font-family: 'Times New Roman'">GPhoneS
                                Store</h2>
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form action="/ban-hang-online/trang-chu/tim-kiem" method="post">
                            <div class="input-with-button">

                                <input type="text" id="searchInput" name="trangchutimkiem" placeholder="Tìm kiếm sản phẩm...">
                                <%--                                    <button class="search-btn" type="submit">Search</button>--%>



                            </div>
                        </form>

                        <div  style="  position: absolute;
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
                                                                giá:</label>${ht.basoOchammotlamGHDGKG2()}đ -
                                                            <del class="product-old-price">${ht.basoOchammotlamGHDG2()} đ</del>
                                                        </c:if>
                                                        <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)<=0}">
                                                            <label style="font-weight: bold">Đơn
                                                                giá:</label>
                                                            ${ht.basoOchammotlamGHDG2()} đ
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
                                            <label>Tổng:</label><label id="tongtienghtt">${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}</label><label>đ</label>

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
<div style="width: 75%;margin-left: 12.5%; " id="content" class="shadow p-3 mb-5 bg-body-tertiary rounded">
    <c:if test="${listghct.size()>0}">
        <div style="float: right;width: 25%" class="border border-light">
            <div>

                <div id="tongsanphamchon" style="float:right;">
                        ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()} sản phẩm
                </div>
                <div style="float:left;">Bạn đã chọn :</div>
                <br>
            </div>
            <br>

            <div>

                <div id="tongthanhtien" style="float:right;">
                    <label id="tongthanhtien1">${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}</label>
                    <label>VND</label>
                </div>
                <div style="float:left;">Tổng tiền</div>
                <br>
            </div>
            <br>
            <c:if test="${banhangonline.ListghTheoidghvsTT1(listghct.get(0).gioHang.id).size()>0}">
                <form action="/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang" method="post">
                    <input name="idgh" value="${listghct.get(0).gioHang.id}" style="display: none">
                    <button type="submit" onclick="return check75tieu('${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}');" class="btn btn-success" style="text-align: center; width:100%">Mua hàng
                    </button>
                </form>
            </c:if>
        </div>

        <div style="width: 70%">
            <table class="table table-hover">
                <thead>
                <tr style="background-color: #70c0b1">
                    <th><input type="checkbox" name="checktong"
                               onclick="chonhetgiohangtong('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
                    </th>
                    <th colspan="2" style="text-align: center">Sản Phẩm</th>
                    <th>Đơn Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listghct}" var="ht" varStatus="stt">
                    <tr>
                        <td>
                            <input type="checkbox" name="checkidgh" value="${ht.id}"
                                   onclick="chonsanphamgiohang('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>
                        </td>
                        <td align="center">
                            <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40" height="40"
                                 style="border-radius:50% 50% 50% 50%">
                        </td>
                        <td style="text-align: center">
                            <a href="/ban-hang-online/chi-tiet-san-pham/${ht.chiTietSanPham.id}">
                                ${ht.chiTietSanPham.sanPham.ten}-${ht.chiTietSanPham.mauSac.ten}<br>
                                ${ht.chiTietSanPham.ram.dungLuong}-${ht.chiTietSanPham.rom.dungLuong}<br>
                            </a>

                        </td>
                        <td>

                            <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)>0}">
                                <div name="checkdongiakhigiam">
                                        ${ht.basoOchammotlamGHDGKG()}
<%--                                            <script>--%>
<%--                                                document.write(--%>
<%--                                                    Number('${ht.basoOchammotlamGHDGKG()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                                                );--%>
<%--                                            </script>--%>

                                            đ</div>
                                -
                                <del class="product-old-price">
                                        ${ht.basoOchammotlamGHDG()}
<%--                                            <script>--%>
<%--                                                document.write(--%>
<%--                                                    Number('${ht.basoOchammotlamGHDG()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                                                );--%>
<%--                                            </script>--%>
                                            đ</del>
                            </c:if>
                            <c:if test="${banhangonline.tonggiamgia(ht.chiTietSanPham.id)<=0}">
                                <div name="checkdongiakhigiam">
                                        ${ht.basoOchammotlamGHDG()}
<%--                                            <script>--%>
<%--                                                document.write(--%>
<%--                                                    Number('${ht.basoOchammotlamGHDG()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                                                );--%>
<%--                                            </script>--%>
                                    đ</div>
                            </c:if>

                        </td>
                        <td>
                                <%--                 <input type="text" min="1" max="${banhangonline.soluongcon(ht.chiTietSanPham.id)}" value="${ht.soLuong}" name="checksoluong"  style="height: 1cm">--%>
                            <button type="button" style="background-color: white ; border: none" name="checktru1"
                                    onclick="tru1donvi('${stt.index}','${ht.id}')">-
                            </button>
                            <input type="number" value="${ht.soLuong}" min="1"
                                   max="${banhangonline.soluongcon(ht.chiTietSanPham.id)}" name="checksoluong"
                                   oninput="myFunction('${stt.index}','${ht.soLuong}','${ht.id}')">
                            <button type="button" style="background-color: white ; border: none" name="checkthem1"
                                    onclick="them1donvi('${stt.index}','${ht.id}')">+
                            </button>
                        </td>
                        <td>
                            <div name="checkthanhtien">
                                    ${ht.tichDONGIAvsSL()}
<%--                                        <script>--%>
<%--                                            document.write(--%>
<%--                                                Number('${ht.tichDONGIAvsSL()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                                            );--%>
<%--                                        </script>--%>
                            </div>

                        </td>
                        <td>
                            <button onclick="xoamotghct('${ht.id}','${ht.gioHang.id}');"
                                    style="background-color: white; border: none">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                </svg>
                            </button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
        </div>
    </c:if>


    <%--    <div>--%>
    <%--        <c:if test="${listghct.size()>0}">--%>
    <%--            <table class="table table-hover">--%>
    <%--                <thead>--%>
    <%--                <tr>--%>
    <%--                    <th><input type="checkbox" name="checktong"--%>
    <%--                               onclick="chonhetgiohangtong('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>--%>
    <%--                    </th>--%>
    <%--                    <th>Ảnh</th>--%>
    <%--                    <th>Sản Phẩm</th>--%>
    <%--                    <th>Đơn Giá</th>--%>
    <%--                    <th>Số lượng</th>--%>
    <%--                    <th>Số tiền</th>--%>
    <%--                    <th>Thao tác</th>--%>
    <%--                </tr>--%>
    <%--                </thead>--%>
    <%--                <tbody>--%>
    <%--                <c:forEach items="${listghct}" var="ht" varStatus="stt">--%>
    <%--                    <tr>--%>
    <%--                        <td>--%>
    <%--                            <input type="checkbox" name="checkidgh" value="${ht.id}"--%>
    <%--                                   onclick="chonsanphamgiohang('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>--%>
    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                            <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40" height="40"--%>
    <%--                                 style="border-radius:50% 50% 50% 50%">--%>
    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                                ${ht.chiTietSanPham.sanPham.ten},${ht.chiTietSanPham.sanPham.hangSanPham.ten},${ht.chiTietSanPham.sanPham.camera.thongSo},${ht.chiTietSanPham.sanPham.manHinh.thongSo},<br>--%>
    <%--                                ${ht.chiTietSanPham.mauSac.ten},${ht.chiTietSanPham.ram.dungLuong},${ht.chiTietSanPham.rom.dungLuong},<br>--%>
    <%--                                ${ht.chiTietSanPham.pin.loaiPin},${ht.chiTietSanPham.pin.dungLuongPin.thongSo},${ht.chiTietSanPham.chip.ten}.--%>
    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                            <div name="checkdongiakhigiam">${ht.donGiaKhiGiam}</div>--%>
    <%--                            đ---%>
    <%--                            <del class="product-old-price">${ht.donGia}</del>--%>
    <%--                            đ--%>

    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                                &lt;%&ndash;                 <input type="text" min="1" max="${banhangonline.soluongcon(ht.chiTietSanPham.id)}" value="${ht.soLuong}" name="checksoluong"  style="height: 1cm">&ndash;%&gt;--%>
    <%--                            <button type="button" name="checktru1" onclick="tru1donvi('${stt.index}','${ht.id}')">---%>
    <%--                            </button>--%>
    <%--                            <input type="number" value="${ht.soLuong}" min="1"--%>
    <%--                                   max="${banhangonline.soluongcon(ht.chiTietSanPham.id)}" name="checksoluong"--%>
    <%--                                   oninput="myFunction('${stt.index}','${ht.soLuong}','${ht.id}')">--%>
    <%--                            <button type="button" name="checkthem1" onclick="them1donvi('${stt.index}','${ht.id}')">+--%>
    <%--                            </button>--%>
    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                            <div name="checkthanhtien"></div>--%>
    <%--                            vnd--%>
    <%--                        </td>--%>
    <%--                        <td>--%>
    <%--                            <button onclick="xoamotghct('${ht.id}','${ht.gioHang.id}');">Xoa sản phẩm</button>--%>
    <%--                        </td>--%>

    <%--                    </tr>--%>
    <%--                </c:forEach>--%>
    <%--                </tbody>--%>
    <%--            </table>--%>
    <%--            <br>--%>
    <%--            <div>--%>
    <%--                <div id="tongsanphamchon" style="float: right">--%>
    <%--                        ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()}--%>
    <%--                </div>--%>
    <%--                <div style="float: right">Bạn đã chọn :</div>--%>
    <%--            </div>--%>


    <%--            <br><br>--%>

    <%--            <div>--%>
    <%--                <div style="float: right">VND</div>--%>
    <%--                <div id="tongthanhtien" style="float: right;margin-right: 10px">--%>
    <%--                        ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}--%>
    <%--                </div>--%>

    <%--                <div style="float: right">Tổng tiền :</div>--%>
    <%--            </div>--%>
    <%--            <br><br>--%>

    <%--            <c:if test="${banhangonline.ListghTheoidghvsTT1(listghct.get(0).gioHang.id).size()>0}">--%>
    <%--                <form action="/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang" method="post">--%>
    <%--                    <input name="idgh" value="${listghct.get(0).gioHang.id}" style="display: none">--%>
    <%--                    <button style="float: right" type="submit">Mua hàng</button>--%>
    <%--                </form>--%>
    <%--            </c:if>--%>
    <%--        </c:if>--%>
    <%--    </div>--%>


</div>


<div style="position: fixed;
top: 50%;left: 50%;transform: translate(-50%,-50%);
display: none;z-index: 2;width: 7cm;height: 5cm;
background-color: #0b3564;text-align: center;
color: white;border-radius: 5% 5% 5% 5%"
     id="tbmuahang">
    <img style="border-radius: 50% 50% 50% 50%;width: 1.2cm;height: 1.2cm;margin-top: 20px"
         src="https://banner2.cleanpng.com/20180815/olc/kisspng-clip-art-computer-icons-x-mark-check-mark-image-wrong-incorrect-delete-abort-png-image-picpng-5b744132b85bd6.9451175115343455227551.jpg"
         >

    <h2 style="color: white;font-size: 20px;margin-top: 20px">


        Chỉ chấp nhận tổng <br><= 75.000.000

    </h2>
</div>



<%--<button id="btloadgd" onclick=" loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');"></button>--%>
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
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

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

        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');


    };

    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                formatAndDisplayValue("tongtienghtt");
                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }


    function thanhtienbenghct() {
        //     var checkdongiakhigiam = document.getElementsByName('checkdongiakhigiam');
        //     var checksoluong = document.getElementsByName('checksoluong');
        //     var checkthanhtien = document.getElementsByName('checkthanhtien');
        //     for (var i = 0; i < checkthanhtien.length; i++) {
        //
        //         checkthanhtien[i].innerHTML = parseInt(checkdongiakhigiam[i].innerHTML) * parseInt(checksoluong[i].value);
        //     }
    };
    thanhtienbenghct();

    function loadgiaodienghctbanhang(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;


                // Gọi hàm với ID của thẻ div cần định dạng
                formatAndDisplayValue('tongthanhtien1');
                loadScripts();

            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
    }

    function loadScripts() {
        const scriptsToLoad = [
            // '/jsbanhang/jquery.min.js',
            // '/jsbanhang/bootstrap.min.js',
            // '/jsbanhang/slick.min.js',
            // '/jsbanhang/nouislider.min.js',
            // '/jsbanhang/jquery.zoom.min.js',
            // '/jsbanhang/main.js'

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

    // Gọi hàm với ID của thẻ div cần định dạng
    formatAndDisplayValue('tongthanhtien1');
    formatAndDisplayValue("tongtienghtt");
</script>
<script>
    var searchResultsContainer = document.getElementById("searchResults");

    document.getElementById("searchInput").addEventListener("input", function() {
        var searchValue = this.value.trim().toLowerCase();
        showSearchResults(searchValue);
    });

    function showSearchResults(searchValue) {
        if (searchValue.length > 0) {
            // document.getElementById('saochep').innerHTML=   document.getElementById('searchInput').value
            loadgiaodientimkiemTrangchu("/ban-hang-online/trang-chu/tim-kiem-loc/"+document.getElementById('searchInput').value);
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
<script>
    function check75tieu(tongtien) {
        var chuoiSoNguyen = tongtien;
        var soNguyen = parseInt(chuoiSoNguyen, 10);
     if(soNguyen>75000000){
         alert(" Chỉ thanh toán với đơn hàng tối đa 75.000.000 vnd")
         document.getElementById('tbmuahang').style.display = '';
         setTimeout(function () {
             document.getElementById('tbmuahang').style.display = 'none';
         }, 2000); // 2000 milliseconds tương đương với 2 giây

         return false;
     }else {
        return true;
     }
    }
</script>
<!-- jQuery Plugins -->
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>

</body>
</html>
