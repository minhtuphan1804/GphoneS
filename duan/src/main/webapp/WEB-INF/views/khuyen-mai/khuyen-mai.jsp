<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>Focus - Bootstrap Admin Dashboard </title>
    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
    <%--    loc theo gia--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.js"></script>
    <%--    can deu--%>

    <!-- Favicon icon -->


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
</head>
<body>
<!-- The Modal -->
<div class="modal" id="myModalthemkm">
    <div class="modal-dialog">
        <div class="modal-content">
            <br>
            <!-- Modal Header -->
            <div>
                <h4 style="text-align: center">Thêm khuyến mại</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form:form action="/khuyen-mai/add" method="post" modelAttribute="km" id="formlink">


                    <br>

                    Tên khuyến mại: <form:errors path="ten" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="ten" value=""/>


                    <br>

                    Ngày bắt đầu:<form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="ngayBatDau" type="datetime-local"/>


                    <br>


                    Ngày kết thúc:<label style="color: red">${momdalthongbaongayKT}</label><form:errors
                        path="ngayKetThuc" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="ngayKetThuc" type="datetime-local"/>


                    <br>

                    % giảm(0->50):<form:errors path="soTienGiam" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="soTienGiam" type="number"
                                oninput="validateInput(this)"/>

                    <br>


                    Mô tả:<form:errors path="moTa" cssStyle="color: red"></form:errors>
                    <form:textarea id="motahkh" class="form-control" placeholder="" path="moTa"/>

                    <br>

                    <div align="center">
                        <button type="submit" class="btn btn-warning" id="bthkh"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Thêm khuyến mại
                        </button>
                    </div>


                </form:form>


            </div>

        </div>
    </div>
</div>


<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">


                <form action="/khuyen-mai/tim-kiem" method="post" style="margin-left: 1cm;">
                    <div class="input-group" style="width: 30%; float: right">
                        <input style="height: 1cm" type="text" class="form-control" name="matk"
                               placeholder="Mã hoặc tên">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-sm btn-primary"
                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                </form>


                <select style="float: right;margin-right: 8cm;height: 1cm" id="selectOption"
                        onchange="choncomboboxtinhtrangkm()">
                    <option style=" font-weight: bold;"><a>Trạng thái khuyến mại</a></option>
                    <option value="all" data-link="/khuyen-mai/tinh-trang/all"
                            <c:if test="${dongbo=='all'}">selected</c:if>><a>Tất cả</a></option>
                    <option value="2" data-link="/khuyen-mai/tinh-trang/2" <c:if test="${dongbo=='2'}">selected</c:if>>
                        <a>Chưa diễn ra</a></option>
                    <option value="0" data-link="/khuyen-mai/tinh-trang/0" <c:if test="${dongbo=='0'}">selected</c:if>>
                        <a>Đang diễn ra</a></option>
                    <option value="1" data-link="/khuyen-mai/tinh-trang/1" <c:if test="${dongbo=='1'}">selected</c:if>>
                        <a>Đã kết thúc</a></option>
                </select>


                <button type="button" id="modalthemkm" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#myModalthemkm">
                    Thêm khuyến mại
                </button>
                <br>
                <br>
                <div class="col-sm-12">
                    <div class="card-box table-responsive">

                        <table id="example3" class="display" style="color: black">
                            <thead>
                            <tr>

                                <th>Mã</th>
                                <th>Ngày tạo</th>
                                <th>Tên</th>
                                <th>% giảm</th>
                                <th>Ngày bắt đầu</th>
                                <th>Ngày kết thúc</th>
                                <th>Ngày cập nhật</th>
                                <th>Tình trạng</th>
                                <th>Mô tả</th>
                                <th>Chức năng</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dulieu}" var="ht" varStatus="stt">
                                <tr>

                                    <td>${ht.ma}</td>
                                    <td>${ht.ngayTao}</td>
                                    <td>${ht.ten} </td>
                                    <td>${ht.soTienGiam}</td>
                                    <td>${ht.ngayBatDau}</td>
                                    <td>${ht.ngayKetThuc}</td>
                                    <td>${ht.ngayCapNhat}</td>
                                    <td>
                                        <c:if test="${ht.tinhTrang==0}">
                                            <p style="color: blue">
                                                Khuyến mại đang áp dụng
                                            </p>

                                        </c:if>
                                        <c:if test="${ht.tinhTrang==1}">
                                            <p style="color: red">
                                                Khuyến mại đã hết hạn
                                            </p>
                                        </c:if>
                                        <c:if test="${ht.tinhTrang==2}">
                                            <p style="color: #00FF00">
                                                Khuyến mại chưa bắt đầu
                                            </p>
                                        </c:if>

                                    </td>
                                    <td>${ht.moTa}</td>

                                    <td>
                                        <c:if test="${ht.tinhTrang==1}">
                                            <a href="/khuyen-mai/detail-khuyen-mai/${ht.id}" class="btn btn-success"
                                               onclick="return confirm('Bạn muốn xem khuyến mại')"
                                               style="background-color: yellow;width: 4cm">Xem khuyến mại</a>
                                        </c:if>

                                        <c:if test="${ht.tinhTrang!=1}">
                                            <a href="/khuyen-mai/ap-dung-khuyen-mai/${ht.id}" class="btn btn-success"
                                               style="width: 4cm"
                                               onclick="return confirm('Bạn muốn áp dụng khuyến mại')">Áp dụng</a>
                                            <a href="/khuyen-mai/detail-khuyen-mai/${ht.id}" class="btn btn-success"
                                               onclick="return confirm('Bạn muốn xem khuyến mại')"
                                               style="background-color: yellow;width: 4cm">Xem khuyến mại</a>
                                        </c:if>
                                        <a href="/khuyen-mai/huy-khuyen-mai/${ht.id}" class="btn btn-success"
                                           style="background-color: red;width: 4cm"
                                           onclick="return confirm('Các chi tiết sản phẩm đang ap dụng khuyến mại mày sẽ không áp dụng khuyến mại này nữa')">Hủy </a>

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


<button style="display: none" id="modalapdung1khuyenmai" type="button" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#myModalapdungkhuyemmai">
    Bat modal ap dung 1 khuyeen mai
</button>

<!-- The Modal -->
<div class="modal" id="myModalapdungkhuyemmai">
    <div class="modal-dialog modal-xl">
        <div class="modal-content" style="width: 37cm;margin-left: -3cm">

            <!-- Modal Header -->
            <div class="apdungthanhcong" style="position: fixed;
top: 8%;left: 80%;transform: translate(-50%,-50%);
display: block;z-index: 2;width: 7cm;height: 1cm;
"></div>
            <div style="margin-top: 0.5cm;z-index: 22">
                <h4 align="center">Áp dụng khuyến mại: ${kmchon.ma}</h4>

            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="table-responsive">
                    <div>
                        <%--loc--%>
                        <div style="width: 6cm;float: right;margin-right: 6cm;margin-top: 0cm">
                            <label class="range-label">Khoảng tiền:</label>
                            <div id="slider" class="slider"></div>

                            <label id="thongbaokhoang" style="color: red"></label>
                            <div>
                                Từ: <input id="value1" value="0" style="width: 2cm">
                                đến <input id="value2" value="${max}" style="width: 2cm">
                            </div>
                            <div id="max" style="display: none">${max}</div>
                        </div>
                        <div style="width: 75%;padding-left: 1cm">


                            <label style="width: 4cm">
                                <select class="form-control" id="hangds1" onchange="clickcombobox()">
                                    <option selected value="null">Hãng sản phẩm</option>
                                    <c:forEach items="${hangds}" var="ht">
                                        <option value="${ht.ten}">${ht.ten}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="camds1" onchange="clickcombobox()">
                                    <option selected value="null">Camera</option>
                                    <c:forEach items="${camds}" var="ht">
                                        <option value="${ht.thongSo}">${ht.thongSo}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="mands1" onchange="clickcombobox()">
                                    <option selected value="null">Màn hình</option>
                                    <c:forEach items="${mands}" var="ht">
                                        <option value="${ht.thongSo}">${ht.thongSo}</option>
                                    </c:forEach>
                                </select>
                            </label>

                            <label style="width: 4cm">
                                <select class="form-control" id="mauds1" onchange="clickcombobox()">
                                    <option selected value="null">Màu sắc</option>
                                    <c:forEach items="${mauds}" var="ht">
                                        <option value="${ht.ten}">${ht.ten}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="ramds1" onchange="clickcombobox()">
                                    <option selected value="null">Ram</option>
                                    <c:forEach items="${ramds}" var="ht">
                                        <option value="${ht.dungLuong}">${ht.dungLuong}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <br>
                            <label style="width: 4cm">
                                <select class="form-control" id="romds1" onchange="clickcombobox()">
                                    <option selected value="null">Rom</option>
                                    <c:forEach items="${romds}" var="ht">
                                        <option value="${ht.dungLuong}">${ht.dungLuong}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="pinds1" onchange="clickcombobox()">
                                    <option selected value="null">Pin</option>
                                    <c:forEach items="${pinds}" var="ht">
                                        <option value="${ht.loaiPin}">${ht.loaiPin}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="dungds1" onchange="clickcombobox()">
                                    <option selected value="null">Dung lượng pin</option>
                                    <c:forEach items="${dungds}" var="ht">
                                        <option value="${ht.thongSo}">${ht.thongSo}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="chipds1" onchange="clickcombobox()">
                                    <option selected value="null">Chíp</option>
                                    <c:forEach items="${chipds}" var="ht">
                                        <option value="${ht.ten}">${ht.ten}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label style="width: 4cm">
                                <select class="form-control" id="sands1" onchange="clickcombobox()">
                                    <option selected value="null">Sản phẩm</option>
                                    <c:forEach items="${sands}" var="ht">
                                        <option value="${ht.ten}">${ht.ten}</option>
                                    </c:forEach>
                                </select>
                            </label>


                            <div id="vt"></div>

                        </div>

                        <%--loc kết thúc--%>


                        <table id="example" class="display" style="color: black;">

                            <thead>
                            <tr style="background-color: #70c0b1">
                                <th>
                                    Ảnh
                                </th>
                                <th>Sản Phẩm</th>
                                <th>Đơn Giá</th>
                                <th>% giảm</th>
                                <th>Đơn giá khi giảm</th>
                                <th>Chức năng</th>

                            </tr>
                            </thead>
                            <tbody id="dssanphamloc">
                            <c:forEach items="${khuyenMaiRepository.getall0CTSP()}" var="ht" varStatus="stt">
                                <tr>
                                    <td>
                                        <img src="../../../uploads/${ht.urlAnh}" width="40" height="40"
                                             style="border-radius:50% 50% 50% 50%">
                                    </td>
                                    <td>
                                        sản phẩm:${ht.sanPham.ten},màu:${ht.mauSac.ten}<br>
                                        dung lượng:${ht.ram.dungLuong},rom:${ht.rom.dungLuong}<br>
                                    </td>
                                    <td>
                                            ${ht.basoOchammotlam()} VND
                                    </td>
                                    <td>

                                            ${ht.khuyenMai.soTienGiam}% <br>
                                        Mã khuyến mại:${ht.khuyenMai.ma}<br>
                                        Tên khuyến mại:${ht.khuyenMai.ten}<br>
                                        Bắt đầu:${ht.khuyenMai.ngayBatDau}<br>
                                        Kết thúc:${ht.khuyenMai.ngayKetThuc}
                                    </td>
                                    <td>
                                            ${banhangonline.convertgiatien(ht.giaBan-ht.giaBan/100*khuyenMaiRepository.tonggiamgia(ht.id))}
                                        VND
                                    </td>
                                    <td>
                                        <c:if test="${ht.khuyenMai==null}">
                                            <a
                                                    onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')"
                                                    class="btn btn-success"
                                            >Áp dụng</a>
                                        </c:if>
                                        <c:if test="${ht.khuyenMai!=null}">
                                            <c:if test="${ht.khuyenMai.tinhTrang!=0}">
                                                <c:if test="${ht.khuyenMai.id==kmchon.id}">
                                                    <a onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                                                       class="btn btn-success" style="background-color: red;width: 3cm"
                                                    >Hủy áp dụng</a>

                                                </c:if>
                                                <c:if test="${ht.khuyenMai.id!=kmchon.id}">
                                                    <a onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')"
                                                       class="btn btn-success" style="width: 3cm"
                                                    >Áp dụng</a>
                                                    <a onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                                                       class="btn btn-success" style="background-color: red;width: 3cm"
                                                    >Hủy áp dụng</a>
                                                </c:if>

                                            </c:if>
                                            <c:if test="${ht.khuyenMai.tinhTrang==0}">
                                                <c:if test="${ht.khuyenMai.id==kmchon.id}">
                                                    <a onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                                                       class="btn btn-success" style="background-color: red;width: 3cm"

                                                    >Hủy áp dụng</a>

                                                </c:if>
                                                <c:if test="${ht.khuyenMai.id!=kmchon.id}">
                                                    <a onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')"
                                                       class="btn btn-success" style="width: 3cm"
                                                    >Áp dụng</a>
                                                    <a onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                                                       class="btn btn-success" style="background-color: red;width: 3cm"
                                                    >Hủy áp dụng</a>
                                                </c:if>

                                            </c:if>
                                        </c:if>

                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <br>
        </div>
    </div>
</div>


<div class="buttons" style="display: none">
    <button id="success">Success</button>
</div>


<button style="display: none" type="button" id="modaldetailupdatekm" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#myModalsuaKM">
    Open modal
</button>

<!-- The Modal -->
<div class="modal" id="myModalsuaKM">
    <div class="modal-dialog">
        <div class="modal-content">
            <br>
            <!-- Modal Header -->
            <div class="apdungthanhcongupdate" style="margin-left: 85%;width:10%;z-index: 88;position: absolute"></div>

            <div>
                <h4 style="text-align: center">UPDATE khuyến mại</h4>
                <h4 style="text-align: center">Mã khuyến mại:${kmchon.ma}</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form:form action="/khuyen-mai/update" method="post" modelAttribute="kmupdate" id="formlink">
                    <div style="display: none">
                        <form:input class="form-control" placeholder="" path="id" value=""/>
                        <form:input class="form-control" placeholder="" path="ma" value=""/>
                        <form:input class="form-control" placeholder="" path="ngayTao" value=""/>
                        <form:input class="form-control" placeholder="" path="ngayCapNhat" value=""/>
                        <form:input class="form-control" placeholder="" path="loaiGiamGia" value=""/>
                        <form:input class="form-control" placeholder="" path="hinhThucGiamGia" value=""/>
                        <form:input placeholder="" path="ngayBatDau" type="text" id="input2"/>
                        <form:input placeholder="" path="ngayKetThuc" type="text" id="input4"/>
                    </div>


                    <br>

                    Tên khuyến mại: <form:errors path="ten" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="ten" value=""/>


                    <br>

                    Ngày bắt đầu:<form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>
                    <input value="${NBTCC}" name="NBDupdate" type="datetime-local" style="width: 100%" id="input1"
                           oninput="updateInput2(this.value)">


                    <br><br>


                    Ngày kết thúc:<label style="color: red">${momdalthongbaongayKT1}</label><form:errors
                        path="ngayKetThuc" cssStyle="color: red"></form:errors><br>
                    <input value="${NKTCC}" name="NKTupdate" type="datetime-local" style="width: 100%" id="input3"
                           oninput="updateInput4(this.value)">


                    <br><br>

                    % giảm(0->50):<form:errors path="soTienGiam" cssStyle="color: red"></form:errors>
                    <form:input class="form-control" placeholder="" path="soTienGiam" type="number"
                                oninput="validateInput(this)"/>

                    <br>


                    Mô tả:<form:errors path="moTa" cssStyle="color: red"></form:errors>
                    <form:textarea id="motahkh" class="form-control" placeholder="" path="moTa"/>

                    <br>

                    <div align="center">
                        <button type="submit" class="btn btn-warning" id=""
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            UPDATE
                        </button>
                    </div>


                </form:form>


            </div>

        </div>
    </div>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>


<div class="buttons" style="display: none">
    <button id="btapdungthanhcong">btapdungthanhcong</button>
</div>
<div class="buttons" style="display: none">
    <button id="btapdungthanhcongupdate">btapdungthanhcong</button>
</div>
<script>
    function choncomboboxtinhtrangkm() {
        if (confirm('Bạn có muốn thực hiện thao tác này không ? ')) {
            var selectElement = document.getElementById("selectOption");
            // Get the selected option value and its associated link
            var selectedOption = selectElement.value;
            var selectedLink = selectElement.options[selectElement.selectedIndex].getAttribute("data-link");

            // Navigate to the selected link
            window.location.href = selectedLink;
        } else {
            return false
        }
    }
</script>
<script>
    // Hàm chuyển đổi định dạng thời gian
    function convertDateFormat(inputDateTime1) {


        // Chuỗi thời gian đầu vào
        var inputDateTime = inputDateTime1.replace("T", " ");


        // Chuyển đổi chuỗi thành đối tượng Date
        var parsedDate = new Date(inputDateTime);


        // Kiểm tra nếu parsedDate không hợp lệ
        if (isNaN(parsedDate.getTime())) {
            return "";
        }

        // Lấy thông tin ngày, tháng, năm, giờ, phút và giây
        var day = parsedDate.getDate();
        var month = parsedDate.getMonth() + 1; // Tháng trong JavaScript bắt đầu từ 0
        var year = parsedDate.getFullYear();
        var hours = parsedDate.getHours();
        var minutes = parsedDate.getMinutes();
        var seconds = parsedDate.getSeconds();

        // Định dạng lại chuỗi theo định dạng mong muốn
        var formattedMonth = (month < 10 ? '0' : '') + month;

        var formattedDay = (day < 10 ? '0' : '') + day;
        var formattedHours = (hours < 10 ? '0' : '') + hours;
        var formattedMinutes = (minutes < 10 ? '0' : '') + minutes;
        var formattedSeconds = (seconds < 10 ? '0' : '') + seconds;

        var outputDateTime = formattedDay + "-" + formattedMonth + "-" + year + " " + formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;


        return outputDateTime;
    }


    // Hàm được gọi khi giá trị của input3 thay đổi
    function updateInput4(value) {


        document.getElementById('input4').value = convertDateFormat(value);
        ;
    }

    // Hàm được gọi khi giá trị của input1 thay đổi
    function updateInput2(value) {
        // Lấy thẻ input thứ hai và cập nhật giá trị của nó
        document.getElementById('input2').value = convertDateFormat(value);
    }
</script>

<script>
    let apdungthanhcong = document.querySelector('.apdungthanhcong');
    let btapdungthanhcong = document.getElementById('btapdungthanhcong');

    function createToast1() {
        let newToast1 = document.createElement('div');
        newToast1.innerHTML = `
            <div class="toast1" style="background-color:red">
                <i class="fa-solid fa-circle-check"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>Áp dụng khuyến mại thành công</span>
                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        apdungthanhcong.appendChild(newToast1);
        newToast1.timeOut = setTimeout(
            () => newToast1.remove(), 1500
        )
    }

    btapdungthanhcong.onclick = function () {


        createToast1();
    }

</script>
<button style="display: none" id="error1"></button>
<script>
    let error1 = document.getElementById('error1');

    function createToast4() {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
            <div class="toast2" style=" background-image:
                    linear-gradient(
                            to right, #f24d4c55, #22242F 30%
                    );" >
                <i style="color: red" class="fa-solid fa-circle-exclamation"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>Hủy Áp dụng khuyến mại thành công</span>
                </div>
                <i style="color: red" class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        apdungthanhcong.appendChild(newToast);
        newToast.timeOut = setTimeout(
            () => newToast.remove(), 1500
        )


    }

    error1.onclick = function () {
        createToast4();
        // createToast();
    }
</script>
<script>

    function validateInput(input) {
        // Xóa mọi ký tự không phải số và giới hạn tối đa 2 ký tự
        input.value = input.value.replace(/[^0-9]/g, '').substring(0, 2);

    }

</script>
<script>

    <c:if test="${momdalthemkm==0}">
    document.getElementById('modalthemkm').click();
    </c:if>
</script>

<button id="error" style="display: none">Error</button>

<script>
    let notifications = document.querySelector('.notifications');
    let success = document.getElementById('success');


    function createToast() {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
            <div class="toast1">
                <i class="fa-solid fa-circle-check"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>${NDTB}</span>
                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        notifications.appendChild(newToast);
        newToast.timeOut = setTimeout(
            () => newToast.remove(), 1500
        )
    }

    success.onclick = function () {


        createToast();
    }

</script>
<script>
    let error = document.getElementById('error');

    function createToast3() {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
            <div class="toast2" style=" background-image:
                    linear-gradient(
                            to right, #f24d4c55, #22242F 30%
                    );" >
                <i style="color: red" class="fa-solid fa-circle-exclamation"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>Hủy khuyến mại thành công</span>
                </div>
                <i style="color: red" class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        notifications.appendChild(newToast);
        newToast.timeOut = setTimeout(
            () => newToast.remove(), 1500
        )


    }

    error.onclick = function () {
        createToast3();
        // createToast();
    }
</script>
<script>
    let btapdungthanhcongupdate = document.getElementById('btapdungthanhcongupdate');

    function createToast2() {
        let newToast2 = document.createElement('div');
        newToast2.innerHTML = `
            <div class="toast1">
                <i class="fa-solid fa-circle-check"></i>
                <div class="content">
                    <div class="title">Thành công</div>
                    <span>Sửa khuyến mại thành công</span>
                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        notifications.appendChild(newToast2);
        newToast2.timeOut = setTimeout(
            () => newToast2.remove(), 1500
        )
    }

    btapdungthanhcongupdate.onclick = function () {


        createToast2();
    }

</script>
<script>
    <c:if test="${tbThemKMOK==0}">
    document.getElementById('success').click();
    </c:if>
</script>
<script>
    <c:if test="${tbUPDATEkmOK==0}">
    document.getElementById('btapdungthanhcongupdate').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalapdungkm==0}">
    document.getElementById('modalapdung1khuyenmai').click();
    </c:if>
    <c:if test="${tbapdungKMvsCTSP==0}">
    document.getElementById('modalapdung1khuyenmai').click();
    document.getElementById('btapdungthanhcong').click();
    </c:if>
    <c:if test="${batmodaldetailupdatekm==0}">
    document.getElementById('modaldetailupdatekm').click();
    </c:if>
</script>
<script>
    var slider = document.getElementById('slider');
    var value1 = document.getElementById('value1');
    var value2 = document.getElementById('value2');
    var max = parseFloat(document.getElementById('max').innerHTML);
    var isSliding = false;

    noUiSlider.create(slider, {
        start: [0, max], // Giá trị mặc định cho hai chấm chòn
        connect: true,    // Kết nối giữa hai chấm chòn
        range: {
            'min': 0,
            'max': max
        }
    });


    value1.addEventListener('input', function () {

        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([parseInt(value1.value), null]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) > parseFloat(value2.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = "";
            clickcombobox();
        }
    });

    value2.addEventListener('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([null, parseInt(value2.value)]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) < parseFloat(value1.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = " ";
            clickcombobox();
        }
    });


    slider.noUiSlider.on('update', function (values, handle) {
        if (!isSliding) {
            if (handle === 0) {
                value1.value = Math.round(values[0]); // Làm tròn giá trị xuống số nguyên

            } else {
                value2.value = Math.round(values[1]); // Làm tròn giá trị xuống số nguyên

            }
            clickcombobox();

        }
    });
</script>
<script>

    loadSelect3();

    function loadSelect3() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp


        $('#hangds1').select2({
            theme: 'bootstrap-5',
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#ramds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });

        $('#sands1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });

        $('#diachids1').select2({
            theme: 'bootstrap-5'
            ,
            dropdownParent: $('#myModalapdungkhuyemmai')
        });

        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }


    function clickcombobox() {
        var x1 = encodeURIComponent(document.getElementById("hangds1").value);
        var x2 = encodeURIComponent(document.getElementById("camds1").value);
        var x3 = encodeURIComponent(document.getElementById("mands1").value);
        var x4 = encodeURIComponent(document.getElementById("mauds1").value);
        var x5 = encodeURIComponent(document.getElementById("ramds1").value);
        var x6 = encodeURIComponent(document.getElementById("romds1").value);
        var x7 = encodeURIComponent(document.getElementById("pinds1").value);
        var x8 = encodeURIComponent(document.getElementById("dungds1").value);
        var x9 = encodeURIComponent(document.getElementById("chipds1").value);
        var x10 = encodeURIComponent(document.getElementById("sands1").value);
        var x11 = encodeURIComponent(document.getElementById("value1").value);
        var x12 = encodeURIComponent(document.getElementById("value2").value);
        if (document.getElementById("value1").value.trim() === '' || document.getElementById("value2").value.trim() === '') {
            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";
        } else {
            if (parseFloat(x11) > parseFloat(x12)) {
                document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

            } else {
                document.getElementById("thongbaokhoang").innerHTML = "";

                var link = '/khuyen-mai/loc-ban-hang/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + x7 + '/' + x8 + '/' + x9 + '/' + x10 + '/' + x11 + '/' + x12;
                // document.getElementById("vt").innerHTML=link
                loadbenloc(link);
                // document.getElementById("demo").innerHTML = "You selected: " + x;
            }
        }
    }

    function HuyapdungkmVS1ctsp(idctsp) {
        var x1 = encodeURIComponent(document.getElementById("hangds1").value);
        var x2 = encodeURIComponent(document.getElementById("camds1").value);
        var x3 = encodeURIComponent(document.getElementById("mands1").value);
        var x4 = encodeURIComponent(document.getElementById("mauds1").value);
        var x5 = encodeURIComponent(document.getElementById("ramds1").value);
        var x6 = encodeURIComponent(document.getElementById("romds1").value);
        var x7 = encodeURIComponent(document.getElementById("pinds1").value);
        var x8 = encodeURIComponent(document.getElementById("dungds1").value);
        var x9 = encodeURIComponent(document.getElementById("chipds1").value);
        var x10 = encodeURIComponent(document.getElementById("sands1").value);
        var x11 = encodeURIComponent(document.getElementById("value1").value);
        var x12 = encodeURIComponent(document.getElementById("value2").value);

        if (confirm('Bạn có muốn thực hiện thao tác này không ? ')) {
            if (document.getElementById("value1").value.trim() === '' || document.getElementById("value2").value.trim() === '') {
                document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";
            } else {
                if (parseFloat(x11) > parseFloat(x12)) {
                    document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

                } else {
                    document.getElementById("thongbaokhoang").innerHTML = "";

                    var link = '/khuyen-mai/huy-ap-dung-vao-1-ctsp/' + idctsp + '/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + x7 + '/' + x8 + '/' + x9 + '/' + x10 + '/' + x11 + '/' + x12;
                    ;
                    // document.getElementById("vt").innerHTML=link
                    loadbenloc(link);
                    // document.getElementById("demo").innerHTML = "You selected: " + x;
                    document.getElementById('error1').click();

                    // var  link1 = '/khuyen-mai/loc-ban-hang/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + x7 + '/' + x8 + '/' + x9 + '/' + x10 + '/' + x11 + '/' + x12;
                    //   // document.getElementById("vt").innerHTML=link
                    //   loadbenloc(link1);
                }
            }
        } else {
            return false;
        }


    }

    function apdungKMvsCTSP(idctsp, idkm) {
        var x1 = encodeURIComponent(document.getElementById("hangds1").value);
        var x2 = encodeURIComponent(document.getElementById("camds1").value);
        var x3 = encodeURIComponent(document.getElementById("mands1").value);
        var x4 = encodeURIComponent(document.getElementById("mauds1").value);
        var x5 = encodeURIComponent(document.getElementById("ramds1").value);
        var x6 = encodeURIComponent(document.getElementById("romds1").value);
        var x7 = encodeURIComponent(document.getElementById("pinds1").value);
        var x8 = encodeURIComponent(document.getElementById("dungds1").value);
        var x9 = encodeURIComponent(document.getElementById("chipds1").value);
        var x10 = encodeURIComponent(document.getElementById("sands1").value);
        var x11 = encodeURIComponent(document.getElementById("value1").value);
        var x12 = encodeURIComponent(document.getElementById("value2").value);
        if (confirm('Bạn có muốn thực hiện thao tác này không ? ')) {
            if (document.getElementById("value1").value.trim() === '' || document.getElementById("value2").value.trim() === '') {
                document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";
            } else {
                if (parseFloat(x11) > parseFloat(x12)) {
                    document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

                } else {
                    document.getElementById("thongbaokhoang").innerHTML = "";

                    var link = '/khuyen-mai/chi-tiet-san-pham-ap-dung-khuyen-mai/' + idctsp + '/' + idkm + '/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + x7 + '/' + x8 + '/' + x9 + '/' + x10 + '/' + x11 + '/' + x12;
                    // document.getElementById("vt").innerHTML=link
                    loadbenloc(link);
                    // document.getElementById("demo").innerHTML = "You selected: " + x;
                    document.getElementById('btapdungthanhcong').click();

                    //  link = '/khuyen-mai/loc-ban-hang/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + x7 + '/' + x8 + '/' + x9 + '/' + x10 + '/' + x11 + '/' + x12;
                    // // document.getElementById("vt").innerHTML=link
                    // loadbenloc(link);
                }
            }
        } else {
            return false;
        }
    }

    function loadbenloc(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('dssanphamloc');
                content.innerHTML = data;


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        // document.getElementById('thanhlocctsp').style.display='none';
    }
</script>
<script>
    <c:if test="${tbhuyKM==0}">
    document.getElementById('error').click();
    </c:if>

    <c:if test="${tbhuyapdungKM==0}">
    document.getElementById('error1').click();
    </c:if>
</script>

</html>












