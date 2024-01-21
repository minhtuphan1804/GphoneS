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
            <a class="nav-link" href="/hang-dien-thoai/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Hãng </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hang-dien-thoai/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin
                hãng </a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Hãng đã xoá</a>
        </li>
        <a href="/hang-dien-thoai/khoi-phuc-het" class="btn btn-outline-danger btn-icon-text"
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
                    <h4 class="card-title" style="float: left">Danh sách Hãng</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/hang-dien-thoai/search2" method="post">
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
                                <th>Ngày tạo</th>
                                <th>Tên hãng</th>
                                <th>Xuất sứ</th>
                                <th>Ngày cập nhật</th>
                                <th>Tình trạng</th>
                                <th>Mô tả</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${hsp}" var="chip" varStatus="i">
                                <tr>
                                    <td>${chip.ma}</td>
                                    <td>${chip.ngayTao}</td>
                                    <td>${chip.ten}</td>
                                    <td>${chip.xuatSu}</td>
                                    <td>${chip.ngayCapNhat}</td>
                                    <td>${chip.tt()}</td>
                                    <td>${chip.moTa}</td>
                                    <td>
                                            <%--                                            <a href="/detail-chip/${chip.id}" class="btn btn-outline-primary"--%>
                                            <%--                                               tabindex="-1"--%>
                                            <%--                                               role="button"--%>
                                            <%--                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Detail</a>--%>
                                        <a href="/hang-dien-thoai/khoi-phuc/${chip.id}" class="btn btn-outline-primary"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Khôi
                                            phục</a>


                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--phân trang--%>
        <div align="center">
            <div class="btn-group" role="group" aria-label="Basic example">
                <ul class="pagination justify-content-center pagination-lg">
                    <li class="page-item"><a class="page-link" href="/hang-dien-thoai/hien-thi-tung-xoa?num=0">First</a>
                    </li>

                    <c:forEach begin="1" end="${total}" varStatus="status">
                        <li class="page-item">
                            <a href="${pageContext.request.contextPath}/hang-dien-thoai/hien-thi-tung-xoa?num=${status.index -1}"
                               class="page-link">${status.index}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item"><a class="page-link" href="/hang-dien-thoai/hien-thi-tung-xoa?num=${total-1}">Last</a>
                    </li>
                </ul>
            </div>
        </div>
        <%--kết thúc phân trang--%>
    </div>
</div>
</body>

</html>
