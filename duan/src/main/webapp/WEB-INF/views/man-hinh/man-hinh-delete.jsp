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
    <title>GPhoneS Store</title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/man-hinh/hien-thi" role="tab" onclick="return myFunction4()">Thông tin màn
                hình </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/man-hinh/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin màn
                hình </a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Màn hình đã xoá</a>
        </li>
        <a href="/camera/update-tt" class="btn btn-outline-danger btn-icon-text"
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
                    <h4 class="card-title" style="float: left">Danh sách Màn hình</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/man-hinh/search-1" method="post">
                        <div class="row">
                            <div class="col-8">
                                <h6 style="float: right; margin: 14px;color: red">${thongBao}</h6></div>
                            <div class="col-4">
                                <div class="input-group" style="width: 100%; float: left">
                                    <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                           aria-label="Bạn tìm gì..." name="search">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary" type="submit"
                                                onclick="return myFunction5()">Search
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black;width: 2400px">
                            <thead>
                            <tr>
                                <th>Mã</th>
                                <th>Ngày tạo</th>
                                <th>Thông số</th>
                                <th>Loại cảm ứng</th>
                                <th>Tỉ lệ khung hình</th>
                                <th>Công nghệ màn hình</th>
                                <th>Độ phân giải màn hình</th>
                                <th>Tần số quét</th>
                                <th>Ngày cập nhật</th>
                                <th>Tình trạng</th>
                                <th>Mô tả</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="list" varStatus="i" items="${listManHinh}">
                                <tr>
                                    <td>${list.ma}</td>
                                    <td>${list.ngayTao}</td>
                                    <td>${list.thongSo}</td>
                                    <td>${list.loaiCamUng}</td>
                                    <td>${list.tiLeKhungHinh}</td>
                                    <td>${list.congNghe}</td>
                                    <td>${list.doPhanGiai}</td>
                                    <td>${list.tanSoQuet}</td>
                                    <td>${list.ngayCapNhat}</td>
                                    <td>
                                        <c:if test="${list.tinhTrang==0}">Hoạt động</c:if>
                                        <c:if test="${list.tinhTrang==1}">Ngừng hoạt động</c:if>
                                    </td>
                                    <td>${list.moTa}</td>
                                    <td>
                                        <a href="/man-hinh/reset-status/${list.id}" class="btn btn-danger btn-icon-text"
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
<script>
    function myFunction1() {
        let text = "Bạn chắc chắn muốn thêm";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction2() {
        let text = "Bạn chắc chắn muốn sửa";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction3() {
        let text = "Bạn chắc chắn muốn thay đổi trạng thái";
        let kt = confirm(text);
        if (kt == true) {
            confirm("Thay đổi trạng thái thành công");
            return true
        } else {
            return false;
        }
    }

    function myFunction4() {
        let text = "Bạn chắc chắn muốn về lại trang hiển thị";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction5() {
        let text = "Bạn chắc chắn muốn tìm kiếm thông tin";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }


</script>
</html>
