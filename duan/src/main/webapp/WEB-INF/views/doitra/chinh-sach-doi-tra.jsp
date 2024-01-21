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
        p {
            color: black;
            font-size: 15px;
            margin-top: 7px;
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
                                        <a href="/ban-hang-online/chinh-sach-doi-tra" class="btn btn-primary" style=""
                                           onclick="">Chính sách đổi trả</a>
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
                                                <br>
                                                <div style="border: 1px solid;height: 2cm">
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
                <li><a href="/ban-hang-online/chinh-sach-doi-tra" style="color: #00A2FF">/CHÍNH SÁCH ĐỔI HOẶC TRẢ HÀNG</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->

<!-- /NAVIGATION -->

<br>
<main style="margin-left: 5cm; margin-right: 5cm">
    <div class="body">
        <br>
        <h2>I. Chính sách đổi sản phẩm của GphoneS Store</h2>
        <br>
        <p>Để mang đến sự thuận tiện và trải nghiệm tốt nhất cho khách hàng, GphoneS Store có những chính sách phù hợp khi khách hàng có nhu cầu đổi hoặc trả sản phẩm(hiện tại cửa hàng chúng tôi chỉ cho đổi hàng, và chưa thể trả hàng). Chúng tôi luôn mong muốn mang lại cho khách hàng những sản phẩm và chất lượng phục vụ tốt nhất.</p>
        <p>Việc đổi hàng sản phẩm đi kèm một số điều kiện cụ thể, Quý khách vui lòng tham khảo thông tin chi tiết bên dưới:</p>
        <br>
        <table>
            <thead>
            <tr style="">
                <th style="width: 250px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px">
                    Mục
                </th>
                <th style="width: 1000px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px">
                    Nội dung chính sách
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td style="border: 2px solid black; font-weight: bold">1) Đối tượng áp dụng</td>
                <td style="border: 2px solid black;">
                    <p>Khách hàng</p>
                    <p>* Áp dụng đối với sản phẩm mua hàng online qua website và trực tiếp tại <strong>GphoneS
                        Store</strong></p>
                    <p>* Chúng tôi không hỗ trợ cho những đơn hàng mua tại địa điểm khác.
                    </p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">2) Điều kiện đổi hàng</td>
                <td style="border: 2px solid black;">
                    <p>* Sản phẩm không trùng khớp về màu sắc, mẫu mã, size theo đơn hàng thì sẽ không được hỗ trợ đổi hàng</p>
                    <p>* Lỗi kỹ thuật do nhà sản xuất.</p>
                    <p>* Sản phẩm đổi hàng phải còn mới, nguyên vẹn, không bị dính nước, không có dấu hiệu rơi vỡ</p>
                    <p>* Sản phẩm phải còn đầy đủ tem, nhãn mác, thẻ bảo hành và hoá đơn mua hàng.</p>
                    <p>* Sản phẩm đổi hàng còn trong thời hạn cho phép đổi hàng.</p>
<%--                    <p>* Khách hàng chỉ có thể đổi sản phẩm với điều kiện <strong>GphoneS Store</strong> không có sản--%>
<%--                        phẩm cùng loại khác (màu sắc,--%>
<%--                        mẫu mã, kích thước) thay thế.</p>--%>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">3) Thời hạn đổi hàng</td>
                <td style="border: 2px solid black;">
                    <p>* <strong> 7 ngày</strong> kể từ ngày khách nhận được hàng</p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">4) Thời hạn thực hiện đổi hàng</td>
                <td style="border: 2px solid black;">
                    <p>* Thời hạn đổi sản phẩm: 7 ngày đối với khách hàng mua online và 7 ngày đối với khách hàng mua trực
                        tiếp tại cửa hàng kể từ ngày khách nhận được hàng.</p>
                    <p>* Nếu vượt quá thời gian quy định trên thì chúng tôi sẽ không nhận đổi sản phẩm với bất kì lý do nào.</p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">5) Hình thức đổi hàng</td>
                <td style="border: 2px solid black;">
                    <P>* Sản phẩm chỉ được đổi một lần duy nhất
                        <br>
                        Quý khách vui lòng đến trực tiếp cửa hàng GPhoneS Store ghi trên hoá đơn mua hàng.
                        <br>
                       * Chúng tôi chỉ xác nhận đổi hàng sang những sản phẩm có cùng model máy như cùng dòng máy,
                        dung lượng bố nhớ, dung lượng ram, cùng 1 dòng chip đối với những máy chạy hệ điều hành android và có thể khác màu máy so với sản phẩm cần đổi
<%--                        Đối với khách hàng ở khu vực khác, vui lòng gọi điện trực tiếp đến số điện thoại 098123456 hoặc--%>
<%--                        gửi yêu cầu qua mail gphonespluss@gmail.com Sau khi nhận được mail phản hồi xác nhận đồng ý từ--%>
<%--                        GPhoneS, Quý khách đóng gói sản phẩm kèm đầy đủ phụ kiện, hoá đơn, phiếu bảo hành, …và gửi về--%>
                    <p> * Địa chỉ: số 3 phố Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội.
                    </p> <p> * Gmail: gphonespluss@gmail.com</p>
                        <br>
                       * Chúng tôi chỉ xác nhận đổi hàng sang những sản phẩm bằng giá của sản phẩm cần
                        đổi
                    </P>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">6) Lý do đổi hàng</td>
                <td style="border: 2px solid black;">
                    <P><b>* Lỗi phần cứng:</b> Lỗi phần cứng là lỗi xảy ra do hư hỏng phần cứng của điện thoại, chẳng hạn như màn hình bị nứt, camera bị mờ, loa bị rè,...<br>
                        <b>* Lỗi thiết kế:</b> Lỗi thiết kế là lỗi xảy ra do thiết kế của điện thoại không đúng với thiết kế ban đầu về kích thước, thiếu tính năng theo thiết kế ban đầu ví dụ: sản phẩm sai so với thiết kế thực tế(sai kích thước, thiếu chức năng mặc định của điện thoại...)
                    </p>

                </td>
            </tr>
            </tbody>
        </table>
        <br><p><b>Ví dụ:</b> Iphone 15 pro max có dung lượng ram là 16GB, dung lượng bộ nhớ 1TB, màu trắng có thể đổi sang 1 chiếc Iphone 15 pro max có ram 16GB, dung lượng bộ nhớ 1TB và có thể sang màu khác như đen, vàng nếu khách hàng muốn đổi, hoặc nếu màu tương tự hết chúng tôi xin phép khách hàng có thể đổi sang màu đang có sẵn trong của hàng
    </p>
<br>
        <h2>II. Chính sách trả sản phẩm của GphoneS Store</h2>
        <br>
        <table>
            <thead>
            <tr style="">
                <th style="width: 250px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px">
                    Mục
                </th>
                <th style="width: 1000px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px">
                    Nội dung chính sách
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td style="border: 2px solid black; font-weight: bold">1) Đối tượng áp dụng</td>
                <td style="border: 2px solid black;">
                    <p>Khách hàng</p>
                    <p>* Áp dụng đối với sản phẩm mua hàng online qua website và trực tiếp tại <strong>GphoneS
                        Store</strong></p>
                    <p>* Chúng tôi không hỗ trợ cho những đơn hàng mua tại địa điểm khác.
                    </p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">2) Điều kiện trả hàng</td>
                <td style="border: 2px solid black;">
                    <p>* Sản phẩm không trùng khớp về màu sắc, mẫu mã, size theo đơn hàng thì sẽ không được hỗ trợ trả hàng</p>
                    <p>* Lỗi kỹ thuật do nhà sản xuất.</p>
                    <p>* Sản phẩm trả hàng phải còn mới, nguyên vẹn, không bị dính nước, không có dấu hiệu rơi vỡ</p>
                    <p>* Sản phẩm phải còn đầy đủ tem, nhãn mác, thẻ bảo hành và hoá đơn mua hàng.</p>
                    <p>* Sản phẩm khách muốn trả hàng còn trong thời hạn cho phép trả hàng.</p>
                    <%--                    <p>* Khách hàng chỉ có thể đổi sản phẩm với điều kiện <strong>GphoneS Store</strong> không có sản--%>
                    <%--                        phẩm cùng loại khác (màu sắc,--%>
                    <%--                        mẫu mã, kích thước) thay thế.</p>--%>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">3) Thời hạn trả hàng</td>
                <td style="border: 2px solid black;">
                    <p>* <strong> 7 ngày </strong> kể từ ngày khách nhận được hàng </p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">4) Thời hạn thực hiện trả hàng</td>
                <td style="border: 2px solid black;">
                    <p>* Thời hạn trả sản phẩm: 7 ngày kể từ ngày khách nhận được hàng đối với khách hàng mua online và 7 ngày đối với khách hàng mua trực
                        tiếp tại cửa hàng</p>
                    <p>* Nếu vượt quá thời gian quy định(vượt quá 7 ngày) thì chúng tôi sẽ không nhận trả sản phẩm với bất kì lý do nào.</p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">5) Hình thức trả hàng</td>
                <td style="border: 2px solid black;">
                    <P>* Sản phẩm chỉ được trả khi phù hợp với chính sách của cửa hàng
                        <br>
                       * Quý khách vui lòng đến trực tiếp cửa hàng GPhoneS Store ghi trên hoá đơn mua hàng.
                        <br>
                       <p> * Địa chỉ: số 3 phố Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội.</p>
                    <p> * Gmail: gphonespluss@gmail.com</p>

                    </P>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">6) Hoàn tiền cho khách</td>
                <td style="border: 2px solid black;">
                    <p> * Khách hàng sẽ được hoàn 95% số tiền so với số tiền được ghi trong hóa đơn.</p>
               <p>* Nếu sản phẩm khách muốn trả là sản phẩm lúc khách mua hàng được giảm giá thì sẽ được cửa hàng trả lại hoàn toàn tiền. </p>
                </td>
            </tr>
            <tr>
                <td style="border: 2px solid black;font-weight: bold">7) Lý do trả hàng</td>
                <td style="border: 2px solid black;">
                    <P><b>* Lỗi phần cứng:</b> Lỗi phần cứng là lỗi xảy ra do hư hỏng phần cứng của điện thoại, chẳng hạn như màn hình bị nứt, camera bị mờ, loa bị rè,...<br>
                        <b>* Lỗi thiết kế:</b> Lỗi thiết kế là lỗi xảy ra do thiết kế của điện thoại không đúng với thiết kế ban đầu về kích thước, thiếu tính năng theo thiết kế ban đầu ví dụ: sản phẩm sai so với thiết kế thực tế(sai kích thước, thiếu chức năng mặc định của điện thoại...)

<%--                        <b>* Sản phẩm không phù hợp với người dùng:</b> khách hàng không hài lòng về sản phẩm của cửa hàng, không hài lòng về màu sắc, chất liệu...--%>

                    </p>

                </td>
            </tr>
            </tbody>
        </table>
        <br><p>
        <b>Ví dụ:</b> Khách hàng mua Iphone 15 pro max có dung lượng ram là 16GB, dung lượng bộ nhớ 1TB, màu trắng được ghi rõ ràng trong hóa đơn thì khi sản phẩm bị lỗi khách hàng được quyền trả sản phẩm đó cho cửa hàng và nhận lại 95% số tiền so với số tiền trong hóa đơn. Nếu lúc mua sản phẩm này đang được giảm giá thì khách hàng sẽ được cửa hàng hoàn trả lại đúng số tiền ban đầu. Cảm ơn quý khách!

        <br>
        <p><i class="fa fa-exclamation-triangle" style=" font-weight: bold; color: red"> LƯU Ý:</i>
            * GPhoneS Store khuyến khích Quý khách hàng nên chụp ảnh sản phẩm trước khi đổi hoặc trả hàng hóa, việc lưu giữ sản
            phẩm sẽ giúp khách hàng làm bằng chứng nếu có những vấn đề phát sinh trong quá trình đổi, trả hàng.</p>

    </div>
</main>
<br>
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


