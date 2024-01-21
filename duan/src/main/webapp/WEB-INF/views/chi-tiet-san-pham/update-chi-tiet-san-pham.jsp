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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Quay lại</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true" role="tab">Cập nhật chi tiết sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi-da-xoa" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản phẩm đã xóa</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi-het-hang" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản phẩm hết hàng</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/chi-tiet-san-pham/update/${chitietsanphamupdate.id}" method="post"
                   modelAttribute="chitietsanphamupdate" enctype="multipart/form-data">
            <%--    <div class="col-md-6 grid-margin stretch-card" >--%>
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Update chi tiết sản phẩm</h4>
                    <form class="forms-sample" justify-content-center>
                        <div class="row">
                            <div class="col-md-12 mx-auto">
                                <div class="form-group">
                                    <div align="center">
                                        <br>
                                        <div style="display: none">
                                            <input style="" type="text" name="checkanh1" value="cu1" id="cucheck1">
                                            <br>
                                        </div>
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi1">
                                                <img id="preview-anh1-2" class="preview-image"
                                                     src="/uploads/${chitietsanphamupdate.urlAnh}" alt=""
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
                                            <div style="display: none">
                                                <form:input path="urlAnh"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="color: black">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="sanPham" class="form-control" id="selectSanPham"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Sản phẩm</option>
                                                <form:options items="${listSanPham}" itemLabel="ten" itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorSanPham}</span>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#exampleModalSanPham">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="chip" class="form-control" id="selectChip"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Chip</option>
                                                <form:options items="${listChip}" itemLabel="ten" itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorChip}</span>

                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal" data-bs-target="#exampleModalChip">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="rom" class="form-control" id="selectRom"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Dung lượng lưu trữ</option>
                                                <form:options items="${listRom}" itemLabel="dungLuong" itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorRom}</span>

                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal" data-bs-target="#exampleModalRom">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="mauSac" class="form-control" id="selectMauSac"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Màu sắc</option>
                                                <form:options items="${listMauSac}" itemLabel="ten" itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorMauSac}</span>

                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#exampleModalMauSac">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="ram" class="form-control" id="selectRam"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Dung lượng bộ nhớ</option>
                                                <form:options items="${listRam}" itemLabel="dungLuong" itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorRam}</span>

                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal" data-bs-target="#exampleModalRam">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="pin" class="form-control" id="selectPin"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Pin</option>
                                                <form:options items="${listPin}" itemLabel="dungLuongPin.thongSo"
                                                              itemValue="id"/>
                                            </form:select>
                                            <span class="text-danger">${errorPin}</span>

                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal" data-bs-target="#exampleModalPin">
                                                <img src="/uploads/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                                            <form:input class="form-control" placeholder="" path="giaBan"
                                                        type="number"/>
                                            <form:errors path="giaBan" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                            <form:textarea class="form-control" placeholder="" path="moTa"/>
                                            <form:errors path="moTa" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:label class="form-label" path="namBaoHanh">Năm bảo hành:</form:label>
                                            <form:input class="form-control" placeholder="" path="namBaoHanh"
                                                        type="number"/>
                                            <form:errors path="namBaoHanh" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-primary mr-2"
                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                Cập Nhật Thông Tin
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <%--    </div>--%>
        </form:form>

        <%--kết thúc phân trang--%>
    </div>
</div>
<div class="modal fade" id="exampleModalPin" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelPin">Add New Pin</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-pin" method="post" modelAttribute="Pin">
                    <div class="mb-3">
                        <form:label path="loaiPin"><b>Loại Pin:</b></form:label>
                        <form:input path="loaiPin" class="form-control" id="loaiPin"/>
                        <span class="text-danger" id="loaiPinError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="congNghePin"><b>Công nghệ Pin:</b></form:label>
                        <form:input path="congNghePin" class="form-control" id="congNghePin"/>
                        <span class="text-danger" id="congNghePinError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control" rows="3"/>

                    </div>
                    <div class="mb-3">
                        <form:label path="dungLuongPin"><b>Dung Lượng:</b></form:label>
                        <form:select path="dungLuongPin" class="form-control">
                            <form:options items="${dungLuongPin}" itemLabel="thongSo" itemValue="id"/>
                        </form:select>
                    </div>
                    <div class="text-center">
                            <%--                        <button type="submit" class="btn btn-primary" onclick="validateForm()" >Thêm Thông Tin</button>--%>
                        <button type="submit" class="btn btn-primary mr-2"
                                onclick="return validateFormPin()">
                            Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<%--Chip--%>
<div class="modal fade" id="exampleModalChip" tabindex="-1" aria-labelledby="exampleModalLabelChip" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelChip">Add New Chip</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-chip" method="post" modelAttribute="chip">
                    <div class="mb-3">
                        <form:label path="ten"><b>Tên:</b></form:label>
                        <form:input path="ten" class="form-control" id="tenChip"/>
                        <span class="text-danger" id="tenChipError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="loaiChip"><b>Loại Chip:</b></form:label>
                        <form:input path="loaiChip" class="form-control" id="loaiChip"/>
                        <span class="text-danger" id="loaiChipError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="soNhan"><b>Số Nhân:</b></form:label>
                        <form:input path="soNhan" class="form-control" type="number" id="soNhan"/>
                        <span class="text-danger" id="soNhanError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="tinhTrang"><b>Tình Trạng:</b></form:label>
                        <br>
                        <form:radiobutton path="tinhTrang" label="Thế hệ mới" value="0"/>
                        <form:radiobutton path="tinhTrang" label="Thế hệ cũ" value="1"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control"/>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" id="btnChip"
                                onclick="return validateFormChip()">Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<%--Màu sắc--%>
<div class="modal fade" id="exampleModalMauSac" tabindex="-1" aria-labelledby="exampleModalLabelMauSac"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelMauSac">Add New Color</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-mau-sac" method="post" modelAttribute="mauSac">
                    <div class="mb-3">
                        <form:label path="ten"><b>Tên:</b></form:label>
                        <form:input path="ten" class="form-control" id="tenMauSac"/>
                        <span class="text-danger" id="tenMauSacError"></span>
                    </div>
                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control" rows="3"/>
                        <form:errors path="moTa" class="text-danger"/>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"
                                onclick="return validateFormMauSac()">Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalRam" tabindex="-1" aria-labelledby="exampleModalLabelRam" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelRam">Add New RAM</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-ram" method="post" modelAttribute="ram">
                    <div class="mb-3">
                        <form:label path="dungLuong"><b>Dung Lượng Ram:</b></form:label>
                        <form:select path="dungLuong" class="form-control" id="dungLuong">
                            <form:option value="4GB">4GB</form:option>
                            <form:option value="8GB">8GB</form:option>
                            <form:option value="16GB">16GB</form:option>
                            <form:option value="32GB">32GB</form:option>
                            <form:option value="64GB">64GB</form:option>
                            <form:option value="128GB">128GB</form:option>

                        </form:select>
                    </div>

                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control" rows="3"/>
                        <form:errors path="moTa" class="text-danger"/>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"
                        >Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalRom" tabindex="-1" aria-labelledby="exampleModalLabelRom" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelRom">Add New ROM</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-rom" method="post" modelAttribute="rom">
                    <div class="mb-3">
                        <form:label path="dungLuong"><b>Dung Lượng:</b></form:label>
                        <form:select path="dungLuong" class="form-control">
                            <form:option value="16GB">16GB</form:option>
                            <form:option value="32GB">32GB</form:option>
                            <form:option value="64GB">64GB</form:option>
                            <form:option value="128GB">128GB</form:option>
                            <form:option value="256GB">256GB</form:option>
                            <form:option value="512GB">512GB</form:option>
                            <form:option value="1TB">1TB</form:option>
                        </form:select>

                    </div>

                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control" rows="3"/>
                        <form:errors path="moTa" class="text-danger"/>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"
                        >Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalSanPham" tabindex="-1" aria-labelledby="exampleModalLabelSanPham"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelSanPham">Add New Sản Phẩm</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/chi-tiet-san-pham/modal-add-san-pham" method="post" modelAttribute="sanPham">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="mb-3">
                                <form:label path="hangSanPham"><b>Hãng Sản Phẩm:</b></form:label>
                                <form:select path="hangSanPham" items="${listHang}" itemValue="id" itemLabel="ten"
                                             class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="manHinh"><b>Màn Hình:</b></form:label>
                                <form:select path="manHinh" items="${listManHinh}" itemValue="id" itemLabel="thongSo"
                                             class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="ten"><b>Tên:</b></form:label>
                                <form:input path="ten" class="form-control" id="tenSanPham"/>
                                <span class="text-danger" id="tenSanPhamError"></span>
                            </div>
                            <div class="mb-3">
                                <form:label path="heDieuHanh"><b>Hệ điều hành:</b></form:label>
                                <form:select path="heDieuHanh" id="heDieuHanht" class="form-control">
                                    <form:option value="Android">Android</form:option>
                                    <form:option value="IOS">IOS</form:option>
                                </form:select>
                                <form:errors path="heDieuHanh" cssClass="error text-danger"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="soSim"><b>Số khe sim:</b></form:label>
                                <form:select path="soSim" class="form-control">
                                    <form:option value="1">1 khe sim vật lý</form:option>
                                    <form:option value="2">2 khe sim vật lý</form:option>
                                    <form:option value="3">1 khe sim vật lý, 1 esim</form:option>
                                </form:select>
                                <form:errors path="soSim" cssClass="error text-danger"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <form:label path="bluetooth"><b>Kết nối bluetooth:</b></form:label>
                                <form:select path="bluetooth" class="form-control">
                                    <form:option value="Bluetooth 1.2">Bluetooth 1.2</form:option>
                                    <form:option value="Bluetooth 2.1">Bluetooth 2.1</form:option>
                                    <form:option value="Bluetooth 3.0">Bluetooth 3.0</form:option>
                                    <form:option value="Bluetooth 4.0">Bluetooth 4.0</form:option>
                                    <form:option value="Bluetooth 4.1">Bluetooth 4.1</form:option>
                                    <form:option value="Bluetooth 4.2">Bluetooth 4.2</form:option>
                                    <form:option value="Bluetooth 5.0">Bluetooth 5.0</form:option>
                                </form:select>
                                <form:errors path="bluetooth" cssClass="error text-danger"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="hoTroMang"><b>Hỗ trợ mạng:</b></form:label>
                                <form:select path="hoTroMang" class="form-control">
                                    <form:option value="3G">3G</form:option>
                                    <form:option value="4G">4G</form:option>
                                    <form:option value="5G">5G</form:option>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="camera"><b>Camera:</b></form:label>
                                <form:select path="camera" items="${listCamera}" itemValue="id" itemLabel="thongSo"
                                             class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="trongLuong"><b>Trọng lượng(g):</b></form:label>
                                <form:input path="trongLuong" class="form-control" id="trongLuong"/>
                                <span class="text-danger" id="trongLuongError"></span>
                            </div>
                            <div class="mb-3">
                                <form:label path="chatLieu"><b>Chất liệu:</b></form:label>
                                <form:input path="chatLieu" class="form-control" id="chatLieu"/>
                                <span class="text-danger" id="chatLieuError"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <form:label path="congGiaoTiep"><b>Cổng giao tiếp:</b></form:label>
                                <form:select path="congGiaoTiep" class="form-control">
                                    <form:option value="Micro USB">Micro USB</form:option>
                                    <form:option value="Lightning">Lightning</form:option>
                                    <form:option value="USB-C">USB-C (Type C)</form:option>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="thongSoWifi"><b>Thông số Wifi:</b></form:label>
                                <form:select path="thongSoWifi" class="form-control">
                                    <form:option value="2.4GHz">2.4GHz</form:option>
                                    <form:option value="5GHz">5GHz</form:option>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="kichThuoc"><b>Kích thước sản phẩm (Chiều dài(mm) x Chiều rộng(mm) x Độ
                                    dày(mm)):</b></form:label>
                                <form:input path="kichThuoc" class="form-control"/>
                                <span class="text-danger" id="kichThuocError"></span>
                            </div>

                            <div class="mb-3">
                                <form:label path="moTa"><b>Mô tả:</b></form:label>
                                <form:textarea path="moTa" class="form-control"></form:textarea>
                                <form:errors path="moTa" cssClass="error text-danger"/>
                            </div>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-success" id="btt" onclick="return validateFormSanPham()">
                            Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${thongBaoCTSP1 != null}">
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
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBaoCTSP1}</h4>
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
<c:if test="${thongBaoCTSP != null}">
    <div id="modalSuccess" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-success swal2-animate-success-icon"
                                 style="display: block;">
                                <div class="swal2-success-circular-line-left"
                                     style="background: rgb(255, 255, 255);"></div>
                                <span class="swal2-success-line-tip swal2-animate-success-line-tip"></span> <span
                                    class="swal2-success-line-long swal2-animate-success-line-long"></span>
                                <div class="swal2-success-ring"></div>
                                <div class="swal2-success-fix" style="background: rgb(255, 255, 255);"></div>
                                <div class="swal2-success-circular-line-right"
                                     style="background: rgb(255, 255, 255);"></div>
                            </div>
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoCTSP}</h4>
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
</body>
<script>
    function validateFormSanPham() {
        var tenSanPham = document.getElementById("tenSanPham").value;
        var kichThuoc = document.getElementById("kichThuoc").value;
        var trongLuong = document.getElementById("trongLuong").value;
        var chatLieu = document.getElementById("chatLieu").value;
        var btt = document.getElementById("btt");
        var isValid = true;
        if (tenSanPham.trim() === "" || tenSanPham.trim().length < 6) {
            document.getElementById("tenSanPhamError").innerHTML = "Tên sản phẩm không được để trống và phải có tối thiểu 6 kí tự";
            btt.type = "button";
            return false;
        } else {
            document.getElementById("tenSanPhamError").innerHTML = ""; // Xóa thông báo lỗi
            if (kichThuoc.trim() === "") {
                document.getElementById("kichThuocError").innerHTML = "Kích thước không được để trống";
                btt.type = "button";
                return false;
            } else {
                document.getElementById("kichThuocError").innerHTML = ""; // Xóa thông báo lỗi
                if (trongLuong.trim() === "") {
                    document.getElementById("trongLuongError").innerHTML = "Trọng lượng không được để trống";
                    btt.type = "button";
                    return false;
                } else {
                    document.getElementById("trongLuongError").innerHTML = ""; // Xóa thông báo lỗi
                    if (chatLieu.trim() === "") {
                        document.getElementById("chatLieuError").innerHTML = "Chất liệu không được để trống";
                        btt.type = "button";

                        return false;
                    } else {
                        document.getElementById("chatLieuError").innerHTML = ""; // Xóa thông báo lỗi
                        btt.type = "submit";
                        return true;
                    }
                }
            }


        }


    }

    function validateFormPin() {
        // Lấy giá trị từ các ô input
        var loaiPin = document.getElementById("loaiPin").value;
        var congNghePin = document.getElementById("congNghePin").value;
        var moTa = document.getElementById("moTa").value;

        // Khai báo biến để theo dõi việc xác thực
        var isValid = true;

        // Kiểm tra từng ô input và hiển thị thông báo lỗi nếu cần
        if (loaiPin.trim() === "" || loaiPin.trim().length < 6) {
            document.getElementById("loaiPinError").innerHTML = "Loại Pin không được để trống và phải lớn hơn 6 kí tự";
            isValid = false;
        } else {
            document.getElementById("loaiPinError").innerHTML = ""; // Xóa thông báo lỗi
        }

        if (congNghePin.trim() === "") {
            document.getElementById("congNghePinError").innerHTML = "Công nghệ Pin không được để trống";
            isValid = false;
        } else {
            document.getElementById("congNghePinError").innerHTML = "";


        }

        if (loaiPin.trim() === "" || congNghePin.trim() === "") {
            alert("Hãy điền đủ thông tin!");
            isValid = false;
        }
        // Nếu có lỗi, ngăn form được gửi đi
        if (!isValid) {
            return false;
        }
    }

    function validateFormChip() {
        // Lấy giá trị từ các ô input
        var tenChip = document.getElementById("tenChip").value;
        var loaiChip = document.getElementById("loaiChip").value;
        var soNhan = parseInt(document.getElementById("soNhan").value);
        var btnChip = document.getElementById("btnChip");

        // Khai báo biến để theo dõi việc xác thực
        var isValid = true;

        // Kiểm tra từng ô input và hiển thị thông báo lỗi nếu cần
        if (tenChip.trim() === "" || tenChip.trim().length <= 6) {
            document.getElementById("tenChipError").innerHTML = "Tên chip không được để trống và tối thiểu có 6 kí tự";
            btnChip.type = "button";
            return false;
        } else {
            document.getElementById("tenChipError").innerHTML = ""; // Xóa thông báo lỗi
            if (loaiChip.trim() === "" || loaiChip.trim().length <= 2) {
                document.getElementById("loaiChipError").innerHTML = "Loại chip không được để trống";
                btnChip.type = "button";
                return false;
            } else {
                document.getElementById("loaiChipError").innerHTML = "";
                if (soNhan <= 0) {
                    document.getElementById("soNhanError").innerHTML = "Số nhân phải lớn hơn 0";
                    btnChip.type = "button";
                    return false;
                } else {
                    document.getElementById("soNhanError").innerHTML = "";
                    btnChip.type = "submit";
                    return true;
                }
            }

        }
        if (tenChip.trim() === "" || loaiChip.trim() === "") {
            alert("Hãy điền đủ thông tin!");
            isValid = false;
        }
        // Nếu có lỗi, ngăn form được gửi đi
        if (!isValid) {
            return false;
        }
    }

    function validateFormMauSac() {
        var tenMauSac = document.getElementById("tenMauSac").value;
        var isValid = true;
        if (tenMauSac.trim() === 0 || tenMauSac.trim().length < 6) {
            document.getElementById("tenMauSacError").innerHTML = "Tên màu sắc không được để trống và phải lớn hơn 6 kí tự";
            isValid = false;
        } else {
            document.getElementById("tenMauSacError").innerHTML = "";
        }
        if (!isValid) {
            return false;
        }

    }

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
        document.getElementById('cucheck1').value = 'moi1';
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>





</html>
