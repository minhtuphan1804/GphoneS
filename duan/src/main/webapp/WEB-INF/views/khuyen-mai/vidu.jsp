<html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <link rel="icon" type="image/png" sizes="16x16" href="../../../images/favicon.png">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../../../vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="../../../vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../../../vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
<%--    <link href="../../../css/style.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="../../../css/app.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
------------------------------------------------------


    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
</head>
<body>
<table id="example1">
    <thead>
    <tr>

        <th>Mã</th>
        <th>Tên</th>
        <th>% giảm</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Ngày tạo</th>
        <th>Ngày cập nhật</th>
        <th>Tình trạng</th>
        <th>Mô tả</th>
        <th>Chức năng</th>

    </tr>
    </thead>
    <tbody >
    <!-- Your data rows go here -->

    <c:forEach items="${dulieu}" var="ht" varStatus="stt">
        <tr>

            <td>${ht.ma}</td>
            <td>${ht.ten} </td>
            <td>${ht.soTienGiam}</td>
            <td>${ht.ngayBatDau}</td>
            <td>${ht.ngayKetThuc}</td>
            <td>${ht.ngayTao}</td>
            <td>${ht.ngayCapNhat}</td>
            <td>
                <c:if test="${ht.tinhTrang==0}">
                    Khuyến mãi đang áp dụng
                </c:if>
                <c:if test="${ht.tinhTrang==1}">
                    Khuyến mãi đã hết hạn
                </c:if>
                <c:if test="${ht.tinhTrang==2}">
                    Khuyến mãi chưa bắt đầu
                </c:if>

            </td>
            <td>${ht.moTa}</td>

            <td>
                <c:if test="${ht.tinhTrang==1}">
                    <a href="/khuyen-mai/detail-khuyen-mai/${ht.id}" class="btn btn-success"
                       onclick="return confirm('Bạn muốn xem khuyến mãi')">Xem khuyến mãi</a>
                </c:if>

                <c:if test="${ht.tinhTrang!=1}">
                    <a href="/khuyen-mai/ap-dung-khuyen-mai/${ht.id}" class="btn btn-success"
                       onclick="return confirm('Bạn muốn áp dụng khuyến mãi')">Áp dụng</a>
                    <a href="/khuyen-mai/detail-khuyen-mai/${ht.id}" class="btn btn-success"
                       onclick="return confirm('Bạn muốn xem khuyến mãi')">Xem khuyến mãi</a>
                </c:if>
                <a href="/khuyen-mai/huy-khuyen-mai/${ht.id}" class="btn btn-success"
                   onclick="return confirm('Các chi tiết sản phẩm đang ap dụng khuyến mãi mày sẽ không áp dụng khuyến mãi này nữa')">Hủy </a>

            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<table id="example2">
    <thead>
    <tr>
        <th>Ảnh</th>
    </tr>
    </thead>
    <tbody >
    <!-- Your data rows go here -->


    <tr>
        <td>1</td>
    </tr>
    <tr>
        <td>2</td>
    </tr>
    <tr>
        <td>3</td>
    </tr>
    <tr>
        <td>4</td>
    </tr>
    <tr>
        <td>5</td>
    </tr>
    <tr>
        <td>6</td>
    </tr>
    <tr>
        <td>7</td>
    </tr>
    <tr>
        <td>8</td>
    </tr>
    <tr>
        <td>9</td>
    </tr>
    <tr>
        <td>10</td>
    </tr>
    <tr>
        <td>11</td>
    </tr>
    </tbody>
</table>
<script>

        $(document).ready(function() {
        $('#example1').DataTable({
            "pagingType": "full_numbers", // Add this line for full pagination
            "searching": true // Add this line to enable searching
        });

        $('#example2').DataTable({
        "pagingType": "full_numbers", // Add this line for full pagination
        "searching": true // Add this line to enable searching
    });
    });

</script>
</body>
</html>