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
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/nhan-vien/hien-thi" role="tab"
               aria-controls="description" aria-selected="true">Thông tin nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/nhan-vien/view-add" role="tab">Thêm nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/nhan-vien/hien-thi-delete" role="tab">Nhân viên đã xoá</a>
        </li>
    </ul>
</div>

<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Nhân viên</h4>

                    <br>
                    <br>
                    <div class="row">
                        <%--                        <div class="col">--%>
                        <form method="get" action="/nhan-vien/loc" style="color: black">
                            <label for="chucVu">Chức vụ:</label>
                            <select id="chucVu" name="ten1">
                                <option value="">Chọn chức vụ</option>
                                <c:forEach items="${listChucVu}" var="chucVu">
                                    <option value="${chucVu.ten}">${chucVu.ten}</option>
                                </c:forEach>
                            </select>

                            <label for="gioiTinh">Giới tính:</label>
                            <select id="gioiTinh" name="gioiTinh1">
                                <%--                            <option value="">Tất cả</option>--%>
                                <option value="true">Nam</option>
                                <option value="false">Nữ</option>
                                <!-- Thêm các option cho giới tính khác -->
                            </select>
                            <button type="submit" class="btn btn-primary">Lọc</button>
                        </form>
                        <%--                        </div>--%>


                        <div class="col">
                            <%--            Tìm kiếm               --%>
                            <form action="/nhan-vien/search-0" method="post">
                                <div class="input-group" style="width: 30%; float: right">
                                    <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                           aria-label="Bạn tìm gì..." name="search">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black;width: 2000px">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Ngày tạo</th>
                                <th>Mã</th>
                                <th>Ảnh</th>
                                <th>Họ tên</th>
                                <th>Giới tính</th>
                                <th>SĐT</th>
                                <th>Tài khoản</th>
                                <th>Chức vụ</th>
                                <th>Lương</th>
                                <th>Quê quán</th>
                                <th>Ngày cập nhật</th>
                                <th>Tình trạng</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listNhanVien}" var="nhanVien" varStatus="i">
                                <tr>
                                    <th scope="row">${i.index+1}</th>
                                    <td>${nhanVien.ngayTao}</td>
                                    <td>${nhanVien.ma}</td>
                                    <td align="center">
                                        <img src="/../../uploads/${nhanVien.urlAnh}" width="40" height="40"></td>
                                    <td>${nhanVien.hoTen}</td>
                                    <td>
                                        <c:if test="${nhanVien.gioiTinh == true}">Nam</c:if>
                                        <c:if test="${nhanVien.gioiTinh == false}">Nữ</c:if>
                                    </td>
                                    <td>${nhanVien.sdt}</td>
                                    <td>${nhanVien.taiKhoan}</td>
                                    <td>${nhanVien.chucVu.ten}</td>
                                    <td>${nhanVien.luong}</td>
                                    <td>${nhanVien.queQuan}</td>
                                    <td>${nhanVien.ngayCapNhat}</td>
                                    <td>${nhanVien.trangThai()}</td>
                                    <td>
                                        <a href="/nhan-vien/detail/${nhanVien.id}"
                                           class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Update</a>
                                        <a href="/nhan-vien/update-status/${nhanVien.id}"
                                           class="btn btn-danger btn-icon-text"
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
