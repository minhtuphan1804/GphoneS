<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<style>
    .carousel-prev {
        background-color: white;
        color: black;
        font-size: 16px;
        border: none;
        transition: background-color 0.3s, color 0.3s;
    }

    .carousel-prev:hover {
        background-color: #0056b3; /* Màu nền khi di chuột qua */
        color: #fff; /* Màu chữ khi di chuột qua */
    }

    .carousel-next {
        background-color: white; /* Màu nền nút */
        color: black; /* Màu chữ trên nút */
        font-size: 16px; /* Kích cỡ chữ */
        border: none; /* Loại bỏ viền nút */
        transition: background-color 0.3s, color 0.3s;
    }

    .carousel-next:hover {
        background-color: #0056b3; /* Màu nền khi di chuột qua */
        color: #fff; /* Màu chữ khi di chuột qua */
    }

    .form-control {
        background-color: #f5f5f5; /* Màu nền combobox */
        color: #333; /* Màu chữ */
        border: 1px solid #ccc; /* Viền */
        border-radius: 5px; /* Góc bo tròn */
        width: 200px; /* Chiều rộng */
        height: 40px;
    }

    .form-control:hover {
        background-color: #007bff; /* Màu nền khi di chuột vào */
        border-color: #007bff; /* Màu viền khi di chuột vào */
        color: #fff; /* Màu chữ khi di chuột vào */
    }
</style>
<body>

<div id="demo11" class="carousel slide" data-bs-ride="false" >
    <!-- Indicators/dots -->

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

                                                    <c:if test="${giamgia.tonggiamgia(ht.id)>0}">
                                                        <h4 class="product-price">
                                                                <%--                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}--%>
                                                                ${banhangonline.sotienkhidagiam(ht.id)}
                                                            <span> ₫</span>
                                                            <del class="product-old-price"
                                                                 style="float: right">${ht.basoOchammotlam()}<span>₫</span>
                                                            </del>
                                                        </h4>
                                                    </c:if>
                                                    <c:if test="${giamgia.tonggiamgia(ht.id)<=0}">
                                                        <h4 class="product-price">
                                                                <%--                                                            ${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}--%>
                                                                ${ht.basoOchammotlam()}
                                                            <span> ₫</span>
                                                        </h4>
                                                    </c:if>
                                                    <div>Đã bán :${banhangonline.soluongdaban(ht.id)}</div>
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

        <div class="carousel text-center">
            <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev">
                <<
            </button>
            <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                <button class="carousel-prev" type="button" data-bs-target="#demo11"
                        data-bs-slide-to="${trang.index-1}">${trang.index}</button>
            </c:forEach>
            <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next">
                >>
            </button>
        </div>
    </div>
</div>
</body>

</html>