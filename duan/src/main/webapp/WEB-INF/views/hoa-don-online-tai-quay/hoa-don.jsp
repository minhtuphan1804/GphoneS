<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
</head>

<body>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">

                <div class="col-sm-12">
                    <div class="card-box table-responsive">

                        <table id="example" class="display" style="color: black; width: 3500px">
                            <thead>
                            <tr>
                                <th>Mã hóa đơn</th>
                                <th>Ngày Tạo</th>
                                <th>Tên khách hàng</th>
                                <th>Tên nhân viên</th>
                                <th>Địa chỉ</th>
                                <th>SĐT</th>
                                <th>Tổng tiền</th>
                                <th>Trạng thái hóa đơn</th>
                                <th>Trạng thái giao hàng</th>
                                <th>Loại HĐ</th>
                                <th>Hình thức thanh toán</th>
                                <th>Mã giao dịch</th>
                                <th>Ngày Thanh Toán</th>
                                <th>Ngày nhận</th>
                                <th>Ngày ship</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${dulieu}" var="hoaDon">
                                <tr>
                                    <td>${hoaDon.ma}</td>
                                    <td>${hoaDon.ngayTao}</td>
                                    <td>${hoaDon.khachHang.hoTen}</td>
                                    <td>${hoaDon.nhanVien.hoTen}</td>
                                    <td>${hoaDon.diaChi.diaChi}-${hoaDon.diaChi.quan}-${hoaDon.diaChi.huyen}-${hoaDon.diaChi.thanhPho}</td>
                                    <td>${hoaDon.sdt}</td>
                                    <td>${hoaDon.tongTien}</td>
                                    <td>
                                        <c:if test="${hoaDon.tinhTrang == 0}">Đang chờ</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 1}">Đã xác nhận</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 2}">Đã thanh toán</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 3}">Chờ thanh toán</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 8}">Đã hủy</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 9}">Đã hủy và hoàn tiền</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${hoaDon.tinhTrangGiaoHang == 0}">Chờ xử lý</c:if>
                                        <c:if test="${hoaDon.tinhTrangGiaoHang == 1}">Chờ giao hàng</c:if>
                                        <c:if test="${hoaDon.tinhTrangGiaoHang == 2}">Đang giao hàng</c:if>
                                        <c:if test="${hoaDon.tinhTrangGiaoHang == 3}">Giao hàng hoàn tất</c:if>
                                        <c:if test="${hoaDon.tinhTrangGiaoHang == 8}">Đã hủy</c:if>
                                    </td>
                                    <td><c:if test="${hoaDon.loai == 1}">HĐ online</c:if>
                                        <c:if test="${hoaDon.loai == 0}">HĐ quầy</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 1}">Online</c:if>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 0}">Tiền mặt</c:if>
                                    </td>
                                    <td>${hoaDon.maGiaoDich}</td>
                                    <td>${hoaDon.ngayThanhToan}</td>
                                    <td>${hoaDon.ngayNhan}</td>
                                    <td>${hoaDon.ngayShip}</td>
                                    <td>
                                        <a href="/hoa-don/detail/${hoaDon.id}" class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail</a>
                                        <c:if test="${hoaDon.loai== 1 && hoaDon.nhanVien== null && (hoaDon.tinhTrang == 2 || hoaDon.tinhTrang==3)}">
                                            <a href="/hoa-don-online/xac-nhan/detail/${hoaDon.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button" style="border: 10px red"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-check-circle-fill"
                                                     viewBox="0 0 16 16">
                                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                </svg>
                                                Xác nhận đơn hàng</a>
                                        </c:if>
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 1 || hoaDon.tinhTrangGiaoHang == 0) && hoaDon.nhanVien!= null && hoaDon.tinhTrang != 9}">
                                            <a href="/hoa-don-online/giao-hang/${hoaDon.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-truck" viewBox="0 0 16 16">
                                                    <path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5zm1.294 7.456A1.999 1.999 0 0 1 4.732 11h5.536a2.01 2.01 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456M12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2"/>
                                                </svg>
                                                Giao hàng</a>
                                        </c:if>

                                        <c:if test="${hoaDon.loai== 1 && hoaDon.tinhTrangGiaoHang == 2 && (hoaDon.tinhTrang==2||hoaDon.tinhTrang==3) && hoaDon.nhanVien!= null}">
                                            <a href="/hoa-don/xac-nhan-giao-hang-hoan-tat/${hoaDon.id}"
                                               class="btn btn-outline-success btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <img src="/uploads/shipped.png">
                                                Giao hàng hoàn tất
                                            </a>
                                        </c:if>
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 1||hoaDon.tinhTrangGiaoHang == 2) && hoaDon.hinhThucThanhToan == 0}">

                                            <a href="/hoa-don/xac-nhan-huy/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn online thanh toán khi nhận hàng</a>

                                        </c:if>
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 1||hoaDon.tinhTrangGiaoHang == 2) && hoaDon.hinhThucThanhToan == 1}">

                                            <a href="/hoa-don/xac-nhan-huy-hoan-tien/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn online thanh toán VNPay</a>
                                        </c:if>
                                        <c:if test="${hoaDon.loai== 0 && (hoaDon.tinhTrang == 0|| hoaDon.tinhTrang == 1|| hoaDon.tinhTrang == 3)}">
                                            <a href="/hoa-don/huy/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn tại quầy</a>
                                        </c:if>
                                        <c:if test="${(hoaDon.tinhTrang == 0|| hoaDon.tinhTrang == 1|| hoaDon.tinhTrang == 3 &&
                                         hoaDon.tinhTrangGiaoHang != 2 && hoaDon.tinhTrangGiaoHang != 3 || (hoaDon.tinhTrang == 2 && hoaDon.loai==1  && hoaDon.tinhTrangGiaoHang != 2 && hoaDon.tinhTrangGiaoHang != 3))
                                          && hoaDon.tinhTrang != 9 && hoaDon.khachHang!= null && hoaDon.nhanVien!= null }">
                                            <a href="/hoa-don/view-update/${hoaDon.id}"
                                               class="btn btn-warning btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-reload btn-icon-prepend"></i>
                                                Update thông tin</a>
                                        </c:if>
                                        <c:if test="${(hoaDon.tinhTrang == 2||hoaDon.tinhTrang == 3) && hoaDon.nhanVien!= null}">
                                            <a href="/hoa-don/xuat-pdf/${hoaDon.id}"
                                               class="btn btn-outline-success btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Xuất PDF</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>



</body>
</html>