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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
</head>
<style>
    .notifications {
        position: absolute;
        top: 30px;
        right: 20px;
    }

    .toast2 {
        position: relative;
        padding: 10px;
        color: #fff;
        margin-bottom: 10px;
        width: 400px;
        display: grid;
        grid-template-columns: 70px 1fr 70px;
        border-radius: 5px;
        --color: #0abf30;
        background-image: linear-gradient(
                to right, #0abf3055, #22242f 30%
        );
        animation: show 0.3s ease 1 forwards
    }

    .toast2 i {
        color: var(--color);
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: x-large;
    }

    .toast2 .title {
        font-size: x-large;
        font-weight: bold;
    }

    .toast2 span, .toast i:nth-child(3) {
        color: #fff;
        opacity: 0.6;
    }

    .toast1 {
        position: relative;
        padding: 10px;
        color: #fff;
        margin-bottom: 10px;
        width: 400px;
        display: grid;
        grid-template-columns: 70px 1fr 70px;
        border-radius: 5px;
        --color: #0abf30;
        background-image: linear-gradient(
                to right, #0abf3055, #22242f 30%
        );
        animation: show 0.3s ease 1 forwards
    }

    .toast1 i {
        color: var(--color);
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: x-large;
    }

    .toast1 .title {
        font-size: x-large;
        font-weight: bold;
    }

    .toast1 span, .toast i:nth-child(3) {
        color: #fff;
        opacity: 0.6;
    }

    @keyframes show {
        0% {
            transform: translateX(100%);
        }
        40% {
            transform: translateX(-5%);
        }
        80% {
            transform: translateX(0%);
        }
        100% {
            transform: translateX(-10%);
        }
    }

    .toast1::before {
        position: absolute;
        bottom: 0;
        left: 0;
        background-color: var(--color);
        width: 100%;
        height: 3px;
        content: '';
        box-shadow: 0 0 10px var(--color);
        animation: timeOut 5s linear 1 forwards
    }

    .toast2::before {
        position: absolute;
        bottom: 0;
        left: 0;
        background-color: red;
        width: 100%;
        height: 3px;
        content: '';
        box-shadow: 0 0 10px var(--color);
        animation: timeOut 5s linear 1 forwards
    }

    @keyframes timeOut {
        to {
            width: 0;
        }
    }

    .toast1.error {
        --color: #f24d4c;
        background-image: linear-gradient(
                to right, #f24d4c55, #22242F 30%
        );
    }

    .toast1.warning {
        --color: #e9bd0c;
        background-image: linear-gradient(
                to right, #e9bd0c55, #22242F 30%
        );
    }

    .toast1.info {
        --color: #3498db;
        background-image: linear-gradient(
                to right, #3498db55, #22242F 30%
        );
    }
</style>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin sản
                phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/hien-thi-tung-xoa" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản phẩm đã xóa</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true" role="tab">Thêm mới sản phẩm</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab" style="color: black">
        <form:form action="/san-pham/add" method="post" modelAttribute="dulieuxem"
                   enctype="multipart/form-data">
            <div class="col-12 grid-margin">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Sản Phẩm</h4>
                        <form class="form-sample">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="ten">Tên:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="ten" id="ten"/>
                                            <form:errors path="ten" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="heDieuHanh">Hệ điều hành:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="heDieuHanh"
                                                        id="heDieuHanht"/>
                                            <form:errors path="heDieuHanh" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="soSim">Số khe sim:</label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" path="soSim" type="number"/>
                                            <form:errors path="soSim" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="bluetooth">Kết nối
                                            bluetooth:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="bluetooth"/>
                                            <form:errors path="bluetooth" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Hỗ trợ mạng:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="hoTroMang"/>
                                            <form:errors path="hoTroMang" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label">Cổng giao tiếp:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="congGiaoTiep"/>
                                            <form:errors path="congGiaoTiep" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="thongSoWifi">Thông số Wifi:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="thongSoWifi"/>
                                            <form:errors path="thongSoWifi" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="kichThuoc">Kích thước </label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="kichThuoc"/>
                                            <form:errors path="kichThuoc" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="trongLuong">Trọng lượng:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="trongLuong"/>
                                            <form:errors path="trongLuong" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="chatLieu">Chất liệu:</label>
                                        <div class="col-sm-9">
                                            <form:input type="text" class="form-control" path="chatLieu"/>
                                            <form:errors path="chatLieu" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="anh">Hình ảnh:</label>
                                        <div class="col-sm-9">
                                            <div class="row">
                                                <div class="d-flex align-items-center ">
                                                    <div class="col-11">
                                                        <form:select path="anh" class="form-control" id="selectAnh"
                                                                     cssStyle="font-weight: bold; width: 100%">
                                                            <option selected disabled>Hình ảnh</option>
                                                            <form:options items="${listAnh}" itemLabel="ten"
                                                                          itemValue="id"/>
                                                        </form:select>
                                                        <span class="text-danger">${errorAnh}</span>
                                                    </div>
                                                    <div class="col-1">
                                                        <a type="button" data-bs-toggle="modal"
                                                           data-bs-target="#exampleModalAnh">
                                                            <img src="/uploads/plus.png"
                                                            />
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="hangSanPham">Hãng Sản Phẩm:</label>
                                        <div class="col-sm-9">
                                            <div class="row">
                                                <div class="d-flex align-items-center">
                                                    <div class="col-11">
                                                        <form:select path="hangSanPham" class="form-control"
                                                                     id="selectHang"
                                                                     cssStyle="font-weight: bold; width: 100%">
                                                            <option selected disabled>Tên Hãng</option>
                                                            <form:options items="${listHangSP}" itemLabel="ten"
                                                                          itemValue="id"/>
                                                        </form:select>
                                                        <span class="text-danger">${errorHang}</span>
                                                    </div>
                                                    <div class="col-1">
                                                        <a type="button" data-bs-toggle="modal" id="modalHang"
                                                           data-bs-target="#exampleModalHangSanPham">
                                                            <img src="/uploads/plus.png"
                                                            />
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="manHinh">Màn hình:</label>
                                        <div class="col-sm-9">
                                            <div class="row">
                                                <div class="d-flex align-items-center">
                                                    <div class="col-11">
                                                        <form:select path="manHinh" class="form-control"
                                                                     id="selectManHinh"
                                                                     cssStyle="font-weight: bold; width: 100%">
                                                            <option selected disabled>Thông số màn</option>
                                                            <form:options items="${listManHinh}" itemLabel="thongSo"
                                                                          itemValue="id"/>
                                                        </form:select>
                                                        <span class="text-danger">${errorManHinh}</span>
                                                    </div>
                                                    <div class="col-1">
                                                        <a type="button" data-bs-toggle="modal" id="modalManHinh"
                                                           data-bs-target="#exampleModalManHinh">
                                                            <img src="/uploads/plus.png"
                                                            />
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="camera">Camera:</label>
                                        <div class="col-sm-9">
                                            <div class="row">
                                                <div class="d-flex align-items-center">
                                                    <div class="col-11">
                                                        <form:select path="camera" class="form-control "
                                                                     id="selectChip">
                                                            <option selected disabled>Thông số camera</option>
                                                            <form:options items="${listCamera}" itemLabel="thongSo"
                                                                          itemValue="id"/>
                                                        </form:select>
                                                        <span class="text-danger">${errorCamera}</span>

                                                    </div>
                                                    <div class="col-1">
                                                        <a type="button" data-bs-toggle="modal" id="modalCamera"
                                                           data-bs-target="#exampleModalCamera">
                                                            <img src="/uploads/plus.png"
                                                            />
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group row">
                                        <label class="col-sm-3 col-form-label" for="moTa">Mô tả::</label>
                                        <div class="col-sm-12">
                                            <form:textarea type="text" class="form-control" path="moTa"/>
                                            <form:errors path="moTa" cssClass="error text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Thêm Thông Tin
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<div class="modal fade" id="exampleModalManHinh" tabindex="-1" aria-labelledby="exampleModalLabelManHinh"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelManHinh">Màn Hình</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/san-pham/modal-add-man-hinh" method="post" modelAttribute="ManHinh">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <form class="form-sample">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="thongSo"><b>Thông
                                                    số
                                                    màn hình:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="thongSo"/>
                                                    <form:errors path="thongSo" cssStyle="color: red"></form:errors>
                                                    <span class="text-danger">${tbTrungManHinh}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="loaiCamUng"><b>Loại
                                                    cảm ứng màn hình:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="loaiCamUng"/>
                                                    <form:errors path="loaiCamUng"
                                                                 cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="tiLeKhungHinh"><b>Tỉ
                                                    lệ khung hình:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="tiLeKhungHinh"/>
                                                    <form:errors path="tiLeKhungHinh"
                                                                 cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="doPhanGiai"><b>Độ
                                                    phân
                                                    giải:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="doPhanGiai"/>
                                                    <form:errors path="doPhanGiai"
                                                                 cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="tanSoQuet"><b>Tần
                                                    số
                                                    quét:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="tanSoQuet"/>
                                                    <form:errors path="tanSoQuet"
                                                                 cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="congNghe"><b>Công
                                                    nghệ
                                                    màn hình:</b></form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="congNghe"/>
                                                    <form:errors path="congNghe"
                                                                 cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label" path="moTa"><b>Mô
                                                    tả:</b></form:label>
                                                <div class="col-sm-12">
                                                    <form:input class="form-control" placeholder="" path="moTa"/>
                                                    <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div style="text-align: center">
                                                <button type="submit" class="btn btn-primary mr-2"
                                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                    Thêm Thông Tin
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalHangSanPham" tabindex="-1" aria-labelledby="exampleModalLabelHangSanPham"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelHangSanPham">Hãng SP</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/san-pham/modal-add-hang-sp" method="post" modelAttribute="hangSP">
                    <div class="card">
                        <div class="card-body">
                            <form class="forms-sample">
                                <div class="form-group">
                                    <form:label path="ten"><b>Tên:</b></form:label>
                                    <form:input path="ten" class="form-control" id="nhapTen"/>
                                    <form:errors path="ten" cssStyle="color: red"></form:errors>
                                    <span class="text-danger" >${tbTrungHang}</span>
                                </div>

                                <div class="form-group">
                                    <form:label path="xuatSu"><b>Xuất sứ:</b></form:label>
                                    <form:input path="xuatSu" class="form-control" id="nhapXuatSu"/>
                                    <span class="text-danger" id="nhapXuatSu1"></span>
                                    <form:errors path="xuatSu" cssStyle="color: red"></form:errors>
                                </div>

                                <div class="form-group">
                                    <form:label path="moTa"><b>Mô Tả:</b></form:label>
                                    <form:textarea class="form-control" id="moTa" path="moTa"
                                                   rows="3"/>
                                </div>
                                <div style="text-align: center">
                                    <button type="submit" class="btn btn-primary mr-2"
                                            id="btnHang">
                                        Thêm Thông Tin
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalCamera" tabindex="-1" aria-labelledby="exampleModalLabelCamera"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelCamera">Camera</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/san-pham/modal-add-camera" method="post" modelAttribute="camera">
                    <div class="card">
                        <div class="card-body">
                            <form class="forms-sample">
                                <div class="form-group">
                                    <form:label path="thongSo"><b>Thông số Camera:</b></form:label>
                                    <form:input class="form-control" placeholder="" path="thongSo"/>
                                    <form:errors path="thongSo" cssStyle="color: red"/>
                                    <span class="text-danger">${tbTrungCamera}</span>
                                </div>
                                <div class="form-group">
                                    <form:label path="moTa"><b>Mô tả:</b></form:label>
                                    <form:input class="form-control" placeholder="" path="moTa"/>
                                    <form:errors path="moTa" cssStyle="color: red"/>
                                </div>
                                <div style="text-align: center">
                                    <button type="submit" class="btn btn-primary mr-2"
                                            >
                                        Thêm Thông Tin
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalAnh" tabindex="-1" aria-labelledby="exampleModalLabelAnh"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelAnh">Ảnh</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/san-pham/modal-add-anh" method="post" modelAttribute="anh"
                           enctype="multipart/form-data">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-4">
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi1">
                                                <img id="preview-anh1-2" class="preview-image" src="" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh1s" accept="image/jpeg, image/png"
                                                       id="anhmoi1">
                                            </div>
                                        </div>
                                        <div style="display: none">
                                            <form:input path="anh1"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi2">
                                                <img id="preview-anh2-2" class="preview-image" src="" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh2s" accept="image/jpeg, image/png"
                                                       id="anhmoi2">
                                            </div>
                                        </div>
                                        <div style="display: none">
                                            <form:input path="anh2"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi3">
                                                <img id="preview-anh3-2" class="preview-image" src="" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh3s" accept="image/jpeg, image/png"
                                                       id="anhmoi3">
                                            </div>
                                        </div>
                                        <div style="display: none">
                                            <form:input path="anh3"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="ten"><b>Tên:</b></form:label>
                                <form:input class="form-control" placeholder="" path="ten"/>
                                <form:errors path="ten" cssClass="text-danger"/>
                            </div>
                            <div class="form-group">
                                <form:label path="moTa"><b>Mô tả:</b></form:label>
                                <form:input class="form-control" placeholder="" path="moTa"/>
                                <form:errors path="moTa" cssClass="text-danger"/>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-primary mr-2" onclick="return myFunction1()">
                                Thêm Thông Tin
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${thongBaoSP != null}">
    <div id="modalError" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-error swal2-animate-error-icon" style="display: block;">
                                    <span class="swal2-x-mark swal2-animate-x-mark"><span
                                            class="swal2-x-mark-line-left"></span><span
                                            class="swal2-x-mark-line-right"></span></span></div>
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBaoSP}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="buttons" style="display: none" >
    <button id="btapdungthanhcong">btapdungthanhcong</button>
</div>
</body>
<script>
    let apdungthanhcong = document.querySelector('.notifications');
    // let btt = document.getElementById('btt');

    // var btt = document.getElementById("btt");
    let btapdungthanhcong =document.getElementById('btapdungthanhcong');
    function createToast1() {

        let newToast1 = document.createElement('div');
        newToast1.innerHTML = `
            <div class="toast1" >
                <i class="fa-solid fa-circle-check"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>Thêm thành công!</span>
                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        apdungthanhcong.appendChild(newToast1);
        newToast1.timeOut = setTimeout(
            () => newToast1.remove(), 5000
        )
    }
    btapdungthanhcong.onclick=function () {
        createToast1();
    }

    <c:if test="${tbHien==1}">btapdungthanhcong.click();</c:if>
    function validateFormHang() {
        // Lấy giá trị từ các ô input
        var ten = document.getElementById("nhapTen").value;
        var xuatSu = document.getElementById("nhapXuatSu").value;

        // Khai báo biến để theo dõi việc xác thực
        var isValid = true;
        var btnPin = document.getElementById("btnHang");

        // Kiểm tra từng ô input và hiển thị thông báo lỗi nếu cần
        if (ten.trim() === "") {
            document.getElementById("nhapTen1").innerHTML = "Tên hãng không được để trống";
            btnPin.type = "button";
            return false;
        } else {
            document.getElementById("nhapTen1").innerHTML = ""; // Xóa thông báo lỗi
            if (xuatSu.trim() === "") {
                document.getElementById("nhapXuatSu1").innerHTML = "Xuất sứ không được để trống";
                btnPin.type = "button";
                return false;
            } else {
                document.getElementById("nhapXuatSu1").innerHTML = "";
                btnPin.type = "summit";
                return true;
            }
        }
    }

    const imageInput = document.getElementById('anhmoi');

    const previewImage2 = document.getElementById('preview-image-2');

    imageInput.addEventListener('change', function () {
        const file = imageInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage2.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewImage2.src = '';
        }
    });
</script>
<script>
    const imageInput1 = document.getElementById('anhmoi1');

    const previewAnh12 = document.getElementById('preview-anh1-2');

    imageInput1.addEventListener('change', function () {
        const file = imageInput1.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh12.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh12.src = '';
        }
    });
    const imageInput2 = document.getElementById('anhmoi2');

    const previewAnh22 = document.getElementById('preview-anh2-2');

    imageInput2.addEventListener('change', function () {
        const file = imageInput2.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh22.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh22.src = '';
        }
    });
    const imageInput3 = document.getElementById('anhmoi3');

    const previewAnh32 = document.getElementById('preview-anh3-2');

    imageInput3.addEventListener('change', function () {
        const file = imageInput3.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh32.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh32.src = '';
        }
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</html>
