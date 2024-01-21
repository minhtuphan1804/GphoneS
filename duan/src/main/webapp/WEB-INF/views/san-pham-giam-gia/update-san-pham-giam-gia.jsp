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
            <a class="nav-link" href="/san-pham-giam-gia/hien-thi" role="tab">Thông tin sản phẩm giảm giá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thông tin chi tiết sản phẩm giảm giá</a>
        </li>

    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/san-pham-giam-gia/update/${sanphamgiamgiaupdate.id}" class="container" method="post" modelAttribute="sanphamgiamgiaupdate">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Thêm Sản phẩm giảm giá</h4>
                        <form class="forms-sample">
                            <div class="form-group">
                                <form:select path="chiTietSanPham" class="form-control"
                                             cssStyle="font-weight: bold; width: 100%" id="selectSanPham">
                                    <option selected disabled>Sản phẩm</option>
                                    <form:options items="${listCTSP}" itemLabel="sanPham.ten" itemValue="id"/>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="khuyenMai">Khuyến mãi:</form:label>
                                <form:select path="khuyenMai" class="form-control">
                                    <form:options items="${listKM}" itemLabel="ten" itemValue="id"/>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <form:label path="moTa"><b>Mô Tả:</b></form:label>
                                <form:textarea path="moTa" class="form-control"></form:textarea>
                                <form:errors path="moTa" cssStyle="color: red"></form:errors>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Update
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </form:form>
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách sản phẩm giảm giá</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/san-pham-giam-gia/search-on" method="post">
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
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Mã khuyến mãi</th>
                                <th scope="col">Tình trạng</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listSpgg}" var="spgg" varStatus="index">
                                <tr>
                                    <td>${spgg.chiTietSanPham.sanPham.ten}</td>
                                    <td>${spgg.khuyenMai.ten}</td>

                                    <td>${spgg.tinhTrang==0?"Dang dien ra":"Da ket thuc"}</td>

                                    <td>${spgg.moTa}</td>

                                    <td>
                                        <a href="/san-pham-giam-gia/view-update/${spgg.id}"
                                           class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Update</a>
                                        <a href="/san-pham-giam-gia/delete/${spgg.id}"
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
        <%--phân trang--%>
        <div align="center">
            <div class="btn-group" role="group" aria-label="Basic example">
                <ul class="pagination justify-content-center pagination-lg">
                    <li class="page-item"><a class="page-link" href="/san-pham-giam-gia/hien-thi?pageNum=0"><</a></li>
                    <c:forEach begin="1" end="${total}" varStatus="status">
                        <li class="page-item">
                            <a href="${pageContext.request.contextPath}/san-pham-giam-gia/hien-thi?pageNum=${status.index -1}"
                               class="page-link">${status.index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link"
                                             href="/san-pham-giam-gia/hien-thi?pageNum=${total-1}">></a></li>
                </ul>
            </div>
        </div>
        <%--kết thúc phân trang--%>
    </div>
</div>
</body>
</html>
