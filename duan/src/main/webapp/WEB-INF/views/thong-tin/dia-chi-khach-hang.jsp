<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%--phan trang--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Trang chủ</title>
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

        /*tuananh*/
        .container1 {
            display: flex;
            margin-left: 5cm;
            margin-right: 5cm;
            /*border: 1px solid red;*/
        }

        .middle-column1 {
            flex: 1;
            padding: 10px;
            /*border: 1px solid #ccc;*/
            box-sizing: border-box;
        }
        .left-column1{
            width: 6cm;
            /*border: 1px solid blue;*/
        }

        .middle-column1 {
            /*border: 1px solid red;*/
            width:  calc(100% - 6cm); /* Tính toán kích thước cột giữa */

        }


        .tab-container {
            display: flex;
            width: 300px; /* Điều chỉnh chiều rộng theo ý muốn */
        }

        .tab-menu {
            /*background-color: #333;*/
            color: black;

            width: 100%; /* Điều chỉnh chiều rộng menu tab */
        }

        .tab {
            padding: 10px;
            cursor: pointer;
            font-weight: bold;
            font-size: 18px;
        }
        .add-button {
            background-color: red; /* Màu đỏ */
            color: #fff;
            /*padding: 5px 10px;*/
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .add-button:hover {
            background-color: darkred; /* Màu đỏ tối khi di chuột qua */
        }


        .close {
            position: absolute;
            top: 0;
            right: 0;
            padding: 5px 10px;
            cursor: pointer;
        }


        .address-container {
            /*border: 1px solid #ccc;*/
            padding: 10px;
            margin: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .buttons {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            justify-content: flex-end;
        }
        .p{
            font-size: 15px;
        }
        .bold {
            font-weight: bold; /* Địa chỉ đậm hơn */
        }

        .update-button {
            background-color: #008000; /* Màu xanh */
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            margin-top: 5px;
        }

        .default-button {
            padding: 5px 10px;
            border: 1px solid #0000ff; /* Viền xung quanh */
            border-radius: 5px;
            margin-top: 5px; /* Khoảng cách 2px từ phía trên */
        }



        .tab-content {
            width: 100%; /* Điều chỉnh chiều rộng nội dung tab */
        }

        .tab-pane {
            display: none;
        }

        .tab-pane.active {
            display: block;
        }
    </style>
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
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}"
                                           class="btn btn-primary">Đơn hàng</a>
                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/chinh-sach-doi-tra" class="btn btn-primary" style="" onclick="">Chính sách đổi trả</a>
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
                                                <div style="height: 2cm">
                                                    <div style="width: 80%;float: right">
                                                        <label style="font-weight: bold">Sản
                                                            phẩm:</label>${ht.chiTietSanPham.sanPham.ten}-
                                                            ${ht.chiTietSanPham.rom.dungLuong}-${ht.chiTietSanPham.mauSac.ten}.

                                                        <br>
                                                        <label style="font-weight: bold">Số lượng:</label> ${ht.soLuong}<br>
                                                        <label style="tbackground-color: white;border: 1px solid white">${ht.donGiaKhiGiam}đ</label>
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
                                            <h5>
                                                Tổng:${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}
                                                đ</h5>
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




<div class="container1">
    <div class="left-column1" style="width: 6cm;">
        <!-- Nội dung cột trái -->
        <div class="row">
            <div class="col-md-2">
                <img id="preview-image-2" class="preview-image" src="../../../uploads/${kh.anh}" alt=""
                     width="40px" height="40px%"
                     style="border-radius:50% 50% 50% 50%;border: 2px solid #999999">
            </div>
            <div class="col-md-10">
                <p style="margin-left: 0.5cm"><strong>${kh.hoTen}</strong></p>
                <%--                <p style="margin-left: 0.5cm">     (Sửa hồ sơ!)</p>--%>
            </div>

        </div>

        <div class="row">
            <div class="tab-container">
                <div class="tab-menu">
                    <div class="tab-menu" style="margin-top: 10px">
                        <a href="/thong-tin-ca-nhan-khach-hang"><div class="tab">Hồ sơ</div></a>
                        <a href="/dia-chi-khach-hang" style="color: red"><div class="tab">Địa chỉ</div></a>
                        <a href="/pass-khach-hang"><div class="tab">Đổi mật khẩu</div></a>

                    </div>
            </div>
        </div>


        <br>

    </div>
</div>

    <div class="middle-column1" style="font-size: 15px">
        <!-- Nội dung cột giữa -->
        <div class="row" style="display: flex; justify-content: space-between; align-items: center;">
            <div class="col1">
                <div style="display: flex; align-items: center;">
            <p style="margin-left: 0.5cm; font-size: 20px"><strong>Địa chỉ</strong></p>
            <button style="margin-left: 16cm; font-size: 18px" id="modalthemdiachi" class="add-button" data-bs-toggle="modal" data-bs-target="#themdiachi">+ Thêm địa chỉ mới</button>
                </div>
                <p style="margin-left: 0.5cm">     (Quản lý địa chỉ nhận hàng!)</p>

            </div>

            <!-- Modal -->
            <P style="display: none">
                    ${kh.id}
            </P>
        </div>

        <div style="font-size: 18px; color: black">
            <p style="font-weight:bold; margin-left: 10px"> Tên đăng nhập: ${kh.hoTen}</p>
        </div>

        <c:forEach var="diaChi" items="${diaChiList}">

            <div class="address-container">
                <div class="address-details">
                    <p style="font-weight: bold; font-size: 15px">${kh.hoTen} | ${kh.sdt}</p>
                    <p>${diaChi.diaChi}, ${diaChi.quan}, ${diaChi.thanhPho}</p>
                </div>
                <div class="buttons">

                    <a href="/view-update-dia-chi-khach-hang/${diaChi.id}" id="update-address-button"  class="update-button" >Cập nhật</a>
                    <a href="/xoa-dia-chi-khach-hang/${diaChi.id}" class="default-button">Xóa</a>
                </div>
            </div>
            <hr>
        </c:forEach>

    </div>

</div>

<!-- The Modal -->
<div class="modal" id="themdiachi">
    <div class="modal-dialog" style="margin-top: 5cm">
        <div class="modal-content">


            <p style="text-align: center;font-size: 20px">Thêm địa chỉ</p>
            <!-- Modal body -->
            <div class="modal-body">
                <form:form action="/add-dia-chi-kh" method="post" modelAttribute="diaChiKH">
                    <table class="table" style="height: 100%">
                        <tr>
                            <th>
                                <form:label class="form-label" path="diaChi">Địa chỉ:
                                    <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="tenhkh" class="form-control" placeholder="" path="diaChi" value=""/>
                            </th>
                            <th>
                                <form:label class="form-label" path="quan">Quận/Huyện:
                                    <form:errors path="quan" cssStyle="color: red"></form:errors></form:label>
                                <form:input id="quan" class="form-control" placeholder=""  path="quan" onkeyup="setHuyen(this)" />
                            </th>
                            <th style="display: none">
                                <form:label class="form-label" path="huyen">Huyện: <form:errors path="huyen" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="huyen" class="form-control" placeholder=""  path="huyen" />
                            </th>
                        </tr>
                        <tr>
                            <th>  <form:label class="form-label" path="thanhPho">Thành Phố: <form:errors path="thanhPho" cssStyle="color: red"></form:errors>
                            </form:label>
                                <form:input id="tp" class="form-control" placeholder=""  path="thanhPho" />

                            </th>
                            <th>
                                <form:label class="form-label" path="moTa">Mô tả:
                                    <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:textarea id="motahkh" class="form-control" placeholder="" path="moTa" />

                            </th>
                        </tr>
                    </table>

                    <div align="center">
                        <button type="submit" class="btn btn-warning"  > Thêm
                        </button>

                    </div>
                </form:form>
            </div>



        </div>
    </div>
</div>


<!-- The Modal -->
<BUTTON   id="modalupdatediachi" class="update-button" data-bs-toggle="modal" data-bs-target="#suadiachi" style="display: none">Cập nhật</BUTTON>

<div class="modal" id="suadiachi">
    <div class="modal-dialog" style="margin-top: 5cm">
        <div class="modal-content">


            <p style="text-align: center;font-size: 20px">Update địa chỉ</p>
            <!-- Modal body -->
            <div class="modal-body">
                <form:form action="/update-dia-chi-kh" method="post" modelAttribute="diaChiKHupdate">
                    <div style="display: none">
                        <form:input  class="form-control" placeholder="" path="id" value=""/>
                        <form:input  class="form-control" placeholder="" path="ma" value=""/>
                        <form:input  class="form-control" placeholder="" path="ngayTao" value=""/>
                        <form:input  class="form-control" placeholder="" path="tinhTrang" value=""/>
                        <form:input  class="form-control" placeholder="" path="khachHang" value=""/>



                    </div>
                    <table class="table" style="height: 100%">
                        <tr>
                            <th>
                                <form:label class="form-label" path="diaChi">Địa chỉ:
                                    <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input  class="form-control" placeholder="" path="diaChi" value=""/>
                            </th>
                            <th>
                                <form:label class="form-label" path="quan">Quận/Huyện:
                                    <form:errors path="quan" cssStyle="color: red"></form:errors></form:label>
                                <form:input id="quan1" class="form-control" placeholder=""  path="quan" onkeyup="setHuyen1(this)" />
                            </th>
                            <th style="display: none" >
                                <form:label class="form-label" path="huyen">Huyện: <form:errors path="huyen" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="huyen1" class="form-control" placeholder=""  path="huyen" />
                            </th>
                        </tr>
                        <tr>
                            <th>  <form:label class="form-label" path="thanhPho">Thành Phố: <form:errors path="thanhPho" cssStyle="color: red"></form:errors>
                            </form:label>
                                <form:input  class="form-control" placeholder=""  path="thanhPho" />

                            </th>
                            <th>
                                <form:label class="form-label" path="moTa">Mô tả:
                                    <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:textarea  class="form-control" placeholder="" path="moTa" />

                            </th>
                        </tr>
                    </table>

                    <div align="center">
                        <button type="submit" class="btn btn-warning"  > Sửa
                        </button>

                    </div>
                </form:form>
            </div>



        </div>
    </div>
</div>



<br>
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






<script>
    function openTab(tabIndex) {
        var tabPanes = document.querySelectorAll('.tab-pane');
        for (var i = 0; i < tabPanes.length; i++) {
            tabPanes[i].classList.remove('active');
        }
        document.getElementById('tab' + tabIndex).classList.add('active');
    }


</script>
<script>

    const imageInput = document.getElementById('anhmoi');
    const previewImage2 = document.getElementById('preview-image-2');

    imageInput.addEventListener('change', function () {

        const file = imageInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage2.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewImage2.src = '';
        }
        document.getElementById('cucheck').value = 'moi';
    });
</script>

<script>

    function chonhetgiohangtongTRANGCHU(idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    };

    function chonsanphamgiohangTT(vt, idctgh, idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        var vt1 = parseInt(vt);
        // var id1=encodeURIComponent(id);
        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    };

    function thongbaothemvaogiohang(idctsp) {
        //chạy 2 lần mới ấn dc
        loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/' + idctsp);
        loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/' + idctsp);
        document.getElementById('thongbaothemgiohang').style.display = '';
        setTimeout(function () {
            document.getElementById('thongbaothemgiohang').style.display = 'none';
        }, 2000); // 2000 milliseconds tương đương với 2 giây

    };


    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                thanhtienbenghct();
                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        document.getElementById('thanhlocctsp').style.display = 'none';

    }

    function loadgiaodienghctbanhang(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;
                thanhtienbenghct();
                loadScripts();
                loadSelect2();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        document.getElementById('thanhlocctsp').style.display = 'none';

    }


    function loadbenloc(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('dssanphamloc');
                content.innerHTML = data;
                loadScripts();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        document.getElementById('thanhlocctsp').style.display = 'none';
    }

    function thanhlocctsp(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {

                const content = document.getElementById('thanhlocctsp');
                content.innerHTML = data;

                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        document.getElementById('thanhlocctsp').style.display = 'none';
    }

    function locbenctsp(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;

                loadScripts();
                loadSelect2();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        document.getElementById('thanhlocctsp').style.display = 'block';
    }

    function loadInterface(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;

                loadScripts();
                loadSelect2();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        document.getElementById('thanhlocctsp').style.display = 'none';


        if (interfaceUrl.includes('/ban-hang-online/chi-tiet-san-pham/')) {
            <c:forEach  items="${listsp}" var="ht" varStatus="stt">
            var kt = '/ban-hang-online/chi-tiet-san-pham/' + '${ht.id}';
            if (kt == interfaceUrl) {
                thanhlocctsp('/ban-hang-online/thanh-loc-ctsp/' + '${ht.id}');
                document.getElementById('thanhlocctsp').style.display = 'block';
            }
            </c:forEach>

        }
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
            '/jsbanhang/main.js'

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
                loadSelect2();
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }


    function loadSelect2() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp
        $('#hangds1').select2({
            theme: 'bootstrap-5'
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
        });
        $('#ramds1').select2({
            theme: 'bootstrap-5'
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
        });

        $('#sands1').select2({
            theme: 'bootstrap-5'
        });

        $('#diachids1').select2({
            theme: 'bootstrap-5'
        });

        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }


</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Lấy tham chiếu đến liên kết và biểu mẫu
        var diaChiLink = document.getElementById("diaChiLink");
        var diaChiForm = document.getElementById("diaChiForm");

        // Thêm một sự kiện nhấp chuột vào liên kết
        diaChiLink.addEventListener("click", function (event) {
            // Ngăn chặn hành vi mặc định của liên kết (chuyển hướng trang)
            event.preventDefault();

            // Kiểm tra xem biểu mẫu có hợp lệ không và sau đó gửi biểu mẫu
            if (diaChiForm) {
                diaChiForm.submit();
            }
        });
    });
</script>




<script>
     <c:if test="${batmodal==1}">
     document.getElementById("modalthemdiachi").click();
     </c:if>
     <c:if test="${batmodalupdate==1}">
     document.getElementById("modalupdatediachi").click();
     </c:if>

    function setHuyen(input) {
        const huyenInput = document.getElementById("huyen");
        huyenInput.value = input.value;
    }
    function setHuyen1(input) {
        const huyenInput = document.getElementById("huyen1");
        huyenInput.value = input.value;
    }
</script>

<script>
    function anbt() {
        document.getElementById('taikhoancuatoi').click();
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
