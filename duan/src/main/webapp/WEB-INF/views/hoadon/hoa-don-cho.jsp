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
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>

</head>
<body>

<div class="main">
    <div>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
            <li class="nav-item">
                <a class="nav-link" href="/hoa-don/hien-thi" role="tab"> Thông tin hóa đơn</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" id="description-tab" data-toggle="tab" href="hoa-don/hien-thi" role="tab"
                   aria-controls="description" aria-selected="true">Thêm hóa đơn chờ</a>
            </li>

        </ul>
    </div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <div class="col-12 grid-margin">
                <div class="card">
                    <div class="card-body">
                        <form:form action="/hoa-don/add" method="post" modelAttribute="hoaDon">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">

                                        <form:label path="khachHang"
                                                    class="col-sm-3 col-form-label">khách hàng:</form:label>
                                        <div class="col-sm-9">
                                            <form:select path="khachHang" items="${listKhachHang}"
                                                         class="form-control select2"
                                                         itemLabel="hoTen"
                                                         itemValue="id" id="searchKhachHang">
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Nhân viên:
                                        </label>
                                        <div class="col-sm-9">
                                            <form:select path="nhanVien" items="${listNhanVien}"
                                                         class="form-control select2"
                                                         itemLabel="hoTen"
                                                         itemValue="id" id="searchNhanVien">
                                            </form:select>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">SĐT:</label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder=""
                                                        path="sdt"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Tổng tiền:
                                        </label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="tongTien" name="tongTien"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row" style="display: none">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                    </div>
                                    <label class="col-sm-3 col-form-label">Quy đổi:
                                    </label>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Ngày tạo:
                                            <div id="tb" style="color: crimson;float: right"></div>
                                        </label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" type="date"
                                                        value="${hoaDon.ngayTao}"
                                                        placeholder=""
                                                        path="ngayTao"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group row">--%>
<%--                                        <label class="col-sm-3 col-form-label">Ngày nhận:--%>
<%--                                            <div style="color: crimson;float: right"></div>--%>
<%--                                        </label>--%>
<%--                                        <div class="col-sm-9">--%>
<%--                                            <form:input class="form-control" type="date"--%>
<%--                                                        value="${hoaDon.ngayNhan}"--%>
<%--                                                        placeholder=""--%>
<%--                                                        path="ngayNhan"/>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <div class="form-group row">--%>
<%--                                        <label class="col-sm-3 col-form-label">Ngày ship:--%>
<%--                                            <div style="color: crimson;float: right"></div>--%>
<%--                                        </label>--%>
<%--                                        <div class="col-sm-9">--%>
<%--                                            <form:input class="form-control" type="date"--%>
<%--                                                        value="${hoaDon.ngayShip}"--%>
<%--                                                        placeholder=""--%>
<%--                                                        path="ngayShip"/>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>

                            <div align="center">
                                <BUTTON type="submit" class="btn btn-warning" style="" id="bt"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Thêm hóa đơn
                                </BUTTON>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    $('#searchKhachHang').select2({
        theme: 'bootstrap-5'
    });
    $('#searchNhanVien').select2({
        theme: 'bootstrap-5'
    });
    $('#searchDiaChi').select2({
        theme: 'bootstrap-5'
    });


</script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</html>
