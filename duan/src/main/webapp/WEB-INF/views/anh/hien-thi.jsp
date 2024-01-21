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
    <title>GPhoneS Store </title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <div>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                   aria-controls="description" aria-selected="true">Thông tin ảnh</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/anh/view-add" role="tab" onclick="return myFunction1()">Thêm thông tin
                    ảnh</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/anh/hien-thi-delete" role="tab" onclick="return myFunction3()">Ảnh đã xoá</a>
            </li>
        </ul>
    </div>
    <div>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="description" role="tabpanel"
                 aria-labelledby="description-tab">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title" style="float: left">Danh sách Màn hình</h4>
                            <%--            Tìm kiếm               --%>
                            <form action="/anh/search-0" method="post">
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
                                <table id="example" class="display" style="color: black;width: 1200px">
                                    <thead>
                                    <tr>
                                        <th>Mã</th>
                                        <th>Ngày tạo</th>
                                        <th>Tên</th>
                                        <th>Ảnh 1</th>
                                        <th>Ảnh 2</th>
                                        <th>Ảnh 3</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Tình trạng</th>
                                        <th>Mô tả</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="list" varStatus="i" items="${listAnh}">
                                        <tr>
                                            <td>${list.ma}</td>
                                            <td>${list.ngayTao}</td>
                                            <td>${list.ten}</td>
                                            <td align="center">
                                                <img src="../../../uploads/${list.anh1}" width="40" height="40"
                                                     style="border-radius:50% 50% 50% 50%">

                                            </td>
                                            <td align="center">
                                                <img src="../../../uploads/${list.anh2}" width="40" height="40"
                                                     style="border-radius:50% 50% 50% 50%">

                                            </td>
                                            <td align="center">
                                                <img src="../../../uploads/${list.anh3}" width="40" height="40"
                                                     style="border-radius:50% 50% 50% 50%">

                                            </td>
                                            <td>${list.ngayCapNhat}</td>
                                            <td>
                                                <c:if test="${list.tinhTrang==0}">Hoạt động</c:if>
                                                <c:if test="${list.tinhTrang==1}">Ngừng hoạt động</c:if>
                                            </td>
                                            <td>${list.moTa}</td>
                                            <td>
                                                <a class="btn btn-warning btn-icon-text"
                                                   href="/anh/detail/${list.id}"
                                                   onclick="return myFunction2()">
                                                    <i class="ti-file btn-icon-prepend"></i>
                                                    Update</a>
                                                <a class="btn btn-danger btn-icon-text"
                                                   href="/anh/update-status/${list.id}"
                                                   onclick="return myFunction6()"><i
                                                        class="ti-reload btn-icon-prepend"></i>
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
    </div>
</div>
</body>
<script>
    function myFunction1() {
        let text = "Bạn chắc chắn muốn sang trang thêm thêm";
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
        let text = "Bạn chắc chắn muốn sang trang trạng thái đã xóa";
        let kt = confirm(text);
        if (kt == true) {
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

    function myFunction6() {
        let text = "Bạn chắc chắn muốn thay đổi trạng thái";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }


</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</html>