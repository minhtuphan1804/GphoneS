<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>


    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
</head>
<body>
<c:forEach items="${listChiTietSanPham}" var="ctsp">
    <tr>
        <td style="display: none">${ctsp.id}</td>
        <td>${ctsp.sanPham.ma}</td>
        <td>${ctsp.sanPham.ten}</td>
        <td align="center">
            <img src="/uploads/${ctsp.urlAnh}" width="40" height="40">
        </td>
        <td>${ctsp.sanPham.hangSanPham.ten}</td>
        <td>${ctsp.mauSac.ten}</td>
        <td>${ctsp.ram.dungLuong}</td>
        <td>${ctsp.rom.dungLuong}</td>
        <c:if test="${ctsp.khuyenMai==null}">
            <td>${ctsp.giaBan}</td>
        </c:if>
        <c:if test="${ctsp.khuyenMai!=null}">
            <td>${ctsp.giaBan-ctsp.giaBan*ctsp.khuyenMai.soTienGiam/100}</td>
        </c:if>
        <td>${ctsp.soLuong}</td>
        <td>
            <a class="btn btn-warning btn-icon-text"
               data-bs-toggle="modal" data-bs-target="#nhapImei">Nhập IMEI</a>
        </td>
    </tr>
</c:forEach>
</body>
</html>
