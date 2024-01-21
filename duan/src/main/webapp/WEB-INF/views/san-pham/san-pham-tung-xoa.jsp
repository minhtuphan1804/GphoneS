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
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Sản
                Phẩm </a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Sản Phẩm đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm mới sản
                phẩm</a>
        </li>
        <a href="/san-pham/khoi-phuc-het" class="btn btn-outline-danger btn-icon-text"
           style="float: right; margin-left: 720px"
           tabindex="-1"
           role="button"
           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
            <i class="ti-reload btn-icon-prepend"></i>
            Status All
        </a>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Sản Phẩm</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/san-pham/search2" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black; width: 2900px">
                            <thead>
                            <tr>
                                <th>Mã SP</th>
                                <th>Ngày tạo</th>
                                <th>Ngày cập nhật</th>
                                <th>Tên SP</th>
                                <th>Hãng</th>
                                <th>Màn hình</th>
                                <th>Camera</th>
                                <th>Thông số bluetooth</th>
                                <th>Hỗ trợ mạng</th>
                                <th>Cổng giao tiếp</th>
                                <th>Thông số wifi</th>
                                <th>Kich thước</th>
                                <th>Trọng lượng</th>
                                <th>Chất liệu máy</th>
                                <th>Hệ điều hành</th>
                                <th>Số khe sim</th>
                                <th>Tình trạng</th>
                                <th>Mô tả</th>
                                <th >Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${hsp}" var="list" varStatus="i">
                                <tr>
                                    <td>${list.ma}</td>
                                    <td>${list.ngayTao}</td>
                                    <td>${list.ngayCapNhat}</td>
                                    <td>${list.ten}</td>
                                    <td>${list.hangSanPham.ten}</td>
                                    <td>${list.manHinh.thongSo}</td>
                                    <td>${list.camera.thongSo}</td>
                                    <td>${list.bluetooth}</td>
                                    <td>${list.hoTroMang}</td>
                                    <td>${list.congGiaoTiep}</td>
                                    <td>${list.thongSoWifi}</td>
                                    <td>${list.kichThuoc}</td>
                                    <td>${list.trongLuong}</td>
                                    <td>${list.chatLieu}</td>
                                    <td>${list.heDieuHanh}</td>
                                    <td>${list.soSim}</td>
                                    <td><c:if test="${list.tinhTrang==0}">Hoạt động</c:if>
                                        <c:if test="${list.tinhTrang==1}">Ngừng hoạt động</c:if>
                                    </td>
                                    <td>${list.moTa}</td>
                                    <td>
                                        <a href="/san-pham/khoi-phuc/${list.id}" class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Status</a>
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
