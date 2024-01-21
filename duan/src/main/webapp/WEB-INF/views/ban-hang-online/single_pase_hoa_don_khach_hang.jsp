<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<body>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã đơn hàng</th>
        <th>Ngày đặt</th>
        <th>Địa chỉ nhận</th>
        <th>Trạng thái đơn hàng</th>
        <th>Hình thức thanh toán</th>
        <th>Trạng thái giao hàng</th>
        <th>Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listhdkh}" var="ht" varStatus="stt">
        <tr>
            <td>${stt.index+1}</td>
            <td>${ht.ma}</td>
            <td>${ht.ngayTao}</td>
            <td>${ht.diaChi.diaChi},${ht.diaChi.quan},${ht.diaChi.huyen},${ht.diaChi.thanhPho}</td>
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
                <c:if test="${ht.hinhThucThanhToan==2}">
                    <input type="text" value="Chưa chọn" disabled>
                </c:if>
                <c:if test="${ht.hinhThucThanhToan==0}">
                    <input type="text" value="Tiền mặt" disabled>
                </c:if>
                <c:if test="${ht.hinhThucThanhToan==1}">
                    <input type="text" value="Chuyển khoản" disabled>
                </c:if>
            </td>
            <td>
                <c:if test="${ht.tinhTrangGiaoHang==0}">
                    <input type="text" value="Chưa giao" disabled>
                </c:if>
                <c:if test="${ht.tinhTrangGiaoHang!=0}">
                    <input type="text" value="Chưa tìm hiểu" disabled>
                </c:if>
            </td>

            <td>
                <c:if test="${ht.tinhTrang==0}">
                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Xem thông
                        tin chi tiết</a>
                    <a class="btn btn-danger" onclick="huyhoadonkhachhang('${ht.id}')">Hủy hóa đơn</a>
                </c:if>
                <c:if test="${ht.tinhTrang==1}">
                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Xem thông
                        tin chi tiết</a>
                    <a class="btn btn-danger" onclick="huyhoadonkhachhang('${ht.id}')">Hủy hóa đơn</a>
                </c:if>
                <c:if test="${ht.tinhTrang==2}">
                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Xem thông
                        tin chi tiết</a>
                </c:if>
                <c:if test="${ht.tinhTrang==3}">
                    <c:if test="${ht.tinhTrangGiaoHang==0 || ht.tinhTrangGiaoHang==1 }">

                        <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Chi tiết đơn hàng</a>
                        <a class="btn btn-danger" onclick="huyhoadonkhachhang('${ht.id}')">Hủy hóa đơn</a>

                    </c:if>
                    <c:if test="${ht.tinhTrangGiaoHang!=0 && ht.tinhTrangGiaoHang!=1 }">

                        <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Chi tiết đơn hàng</a>

                    </c:if>
                </c:if>
                <c:if test="${ht.tinhTrang==8}">
                    <a class="btn btn-info" href="/ban-hang-online/xem-hoa-don-chi-tiet/${ht.id}">Xem thông
                        tin chi tiết</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>

</html>