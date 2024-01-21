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
    <title>GPhoneS Store </title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/chip/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Chip </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chip/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin
                Chip </a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Chip đã xoá</a>
        </li>
        <a href="/chip/update-tt" class="btn btn-outline-danger btn-icon-text" style="float: right; margin-left: 720px"
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
                    <h4 class="card-title" style="float: left">Danh sách đã xoá</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/chip/search-1" method="post">
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
                        <table id="example" class="display" style="color: black;width: 1400px">
                            <thead>
                            <tr>
                                <th>Mã</th>
                                <th>Tình Trạng</th>
                                <th>Tên</th>
                                <th>Loại Chip</th>
                                <th>Số Nhân</th>
                                <th>Ngày Tạo</th>
                                <th>Ngày Cập Nhật</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listChip}" var="chip">
                                <tr>
                                    <td>${chip.ma}</td>
                                    <td>${chip.ngayTao}</td>
                                    <td>${chip.ten}</td>
                                    <td>${chip.loaiChip}</td>
                                    <td>${chip.soNhan}</td>
                                    <td>
                                        <c:if test="${chip.tinhTrang == 0}">Còn</c:if>
                                        <c:if test="${chip.tinhTrang == 1}">Hết</c:if>
                                    </td>
                                    <td>${chip.ngayCapNhat}</td>
                                    <td>
                                        <a href="/chip/reset-status/${chip.id}" class="btn btn-danger btn-icon-text"
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
