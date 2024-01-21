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
    <!-- modal -->
</head>
<style>


    .notifications {
        position: absolute;
        top: 30px;
        right: 20px;
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

    .toast1 span, .toast1 i:nth-child(3) {
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
        background-color: seagreen;
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

    .dataTables_filter {
        display: block;
    }

    .dataTables_info {
        display: block;
    }
</style>
<body>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">

                <div class="col-sm-12">
                    <div class="card-box table-responsive">

                        <div align="center">

                            <div class="ban-hang" style="align-items: center">
                                <video
                                        style="border: 1px "
                                        id="video1"
                                        autoplay="true"
                                        width="200px"
                                        height="120px"
                                ></video>
                            </div>


                            <br>
                            <form action="/hoa-don-online/imei-vao-hdct/${listghcthd.get(0).hoaDon.id}" method="post"
                                  style="margin-left: 1cm;">
                                <div class="input-group" style="width: 40%">
                                    <input style="height: 1cm" type="text" class="form-control" name="imeithem"
                                           placeholder="Thêm imei">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-sm btn-primary"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            Thêm vào hóa đơn
                                        </button>
                                    </div>
                                </div>
                                <%--                                <P style="color: red"> ${tbkhithemimei}</P>--%>
                            </form>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>

<div class="tab-content">
    <div class="tab-pane fade show active" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="card">
            <div class="card-body">

                <div class="col-sm-12">
                    <div class="card-box table-responsive">
                        <a href="/hoa-don-online/xac-nhan/thanh-cong/${listghcthd.get(0).hoaDon.id}"
                           style="float: right" class="btn btn-success"
                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                        >XÁC NHẬN ĐƠN HÀNG</a>
                        <button style="float: right;margin-right: 1cm" type="button" class="btn btn-primary"
                                data-bs-toggle="modal" data-bs-target="#myModal">
                            Thông tin Hóa đơn ${listghcthd.get(0).hoaDon.ma}
                        </button>
                        <H4>THÔNG TIN HÓA ĐƠN CHI TIẾT</H4>

                        <table id="example2" class="display" style="color: black;">
                            <thead>
                            <tr>
                                <th>Ảnh</th>
                                <th>Tên Sản Phẩm</th>
                                <th>Hãng</th>
                                <th>Màu Sắc</th>
                                <th>Ram</th>
                                <th>Dung Lượng Bộ Nhớ</th>
                                <th>Số IMEI</th>
                                <th>Xóa</th>
                            </tr>
                            </thead>
                            <tbody id="table-search">
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listhdct}" var="hdct">
                                <tr>
                                    <td align="center">
                                        <img src="/uploads/${hdct.imei.chiTietSanPham.urlAnh}" width="40"
                                             height="40">
                                    </td>
                                    <td>${hdct.imei.chiTietSanPham.sanPham.ten}</td>
                                    <td>${hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten}</td>
                                    <td>${hdct.imei.chiTietSanPham.mauSac.ten}</td>
                                    <td>${hdct.imei.chiTietSanPham.ram.dungLuong}</td>
                                    <td>${hdct.imei.chiTietSanPham.rom.dungLuong}</td>
                                    <td>${hdct.imei.soImei}</td>
                                    <td>
                                        <a href="/hoa-don-online/xoa-ctsp/${hdct.id}" class="btn btn-danger"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                        >Xóa</a>
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

<div>
    <div class="tab-content" style="width: 49%;float: right;height: 100%">
        <div class="tab-pane fade show active" role="tabpanel"
             aria-labelledby="description-tab">
            <div class="card">
                <div class="card-body">
                    <div class="col-sm-12">
                        <div class="card-box table-responsive">

                            <H4>TỔNG HỢP IMEI HÓA ĐƠN CHI TIẾT</H4>
                            <table id="example3" style="color: black">
                                <br>
                                <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Sản Phẩm</th>
                                    <th>Số lượng</th>
                                </tr>
                                </thead>
                                <c:set var="dem" scope="session" value="${0}"/>
                                <c:forEach items="${listhdct}" var="ht" varStatus="stt">
                                    <c:if test="${stt.index==0}">
                                        <tr>
                                            <td>
                                                <img src="/uploads/${ht.imei.chiTietSanPham.urlAnh}" width="40"
                                                     height="40">
                                            </td>
                                            <td>
                                                    ${ht.imei.chiTietSanPham.sanPham.ten}, <br>
                                                hãng: ${ht.imei.chiTietSanPham.sanPham.hangSanPham.ten},<br>
                                                màu: ${ht.imei.chiTietSanPham.mauSac.ten},<br>
                                                ram: ${ht.imei.chiTietSanPham.ram.dungLuong},<br>
                                                rom:${ht.imei.chiTietSanPham.rom.dungLuong}<br>
                                            </td>
                                            <td>
                                                    ${banhangonline.listIMEItheoIDHDvsIDCTSP(ht.hoaDon.id,ht.imei.chiTietSanPham.id).size()}
                                            </td>


                                        </tr>

                                        <c:set var="dem" scope="session" value="${dem+1}"/>
                                    </c:if>
                                    <c:if test="${stt.index>0}">
                                        <c:set var="checkck" scope="session" value="${0}"/>
                                        <c:forEach items="${listhdct}" var="ht1" varStatus="stt1" begin="0"
                                                   end="${stt.index-1}">
                                            <c:if test="${ht.imei.chiTietSanPham.id==ht1.imei.chiTietSanPham.id}">
                                                <c:set var="checkck" scope="session" value="${1}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${checkck==0}">

                                            <tr>
                                                <td>
                                                    <img src="/uploads/${ht.imei.chiTietSanPham.urlAnh}" width="40"
                                                         height="40">
                                                </td>
                                                <td>
                                                        ${ht.imei.chiTietSanPham.sanPham.ten}, <br>
                                                    hãng: ${ht.imei.chiTietSanPham.sanPham.hangSanPham.ten},<br>
                                                    màu: ${ht.imei.chiTietSanPham.mauSac.ten},<br>
                                                    ram: ${ht.imei.chiTietSanPham.ram.dungLuong},<br>
                                                    rom${ht.imei.chiTietSanPham.rom.dungLuong}<br>
                                                </td>
                                                <td>
                                                        ${banhangonline.listIMEItheoIDHDvsIDCTSP(ht.hoaDon.id,ht.imei.chiTietSanPham.id).size()}
                                                </td>


                                            </tr>

                                            <c:set var="dem" scope="session" value="${dem+1}"/>
                                        </c:if>
                                    </c:if>
                                </c:forEach>


                                <c:forEach items="${listhdct}" var="ht">

                                </c:forEach>
                                </tbody>

                            </table>
                            <br><br><br><br>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>

    <div class="tab-content" style="width: 49%;float: left;height: 100%; ">
        <div class="tab-pane fade show active" role="tabpanel"
             aria-labelledby="description-tab">
            <div class="card">
                <div class="card-body">
                    <div class="col-sm-12">
                        <div class="card-box table-responsive">
                            <H4>DANH SÁCH SẢN PHẨM TRONG ĐƠN ĐẶT HÀNG</H4>


                            <div class="basic-dropdown" style="float: right">
                                <div class="dropdown">
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown">
                                        <i class="ti-export btn-icon-prepend"></i>
                                        Xuất Excel
                                    </button>
                                    <div class="dropdown-menu">
                                        <a href="/hoa-don-online/excel-don-dat-hang/${listghcthd.get(0).hoaDon.id}"
                                           class="dropdown-item" tabindex="-1">Sản phẩm đặt của hóa
                                            đơn ${listghcthd.get(0).hoaDon.ma} </a>
                                        <a href="/hoa-don-online/excel-don-dat-hang/full" class="dropdown-item"
                                           tabindex="-1">Tất cả sản phẩm đặt hàng</a>

                                    </div>
                                </div>
                            </div>
                            <br><br>
                            <table id="example" style="color: black;width: 20cm">
                                <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Sản Phẩm</th>
                                    <th>Giá mua</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listghcthd}" var="ht" varStatus="stt">
                                    <tr>
                                        <td>
                                            <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="40"
                                                 height="40"
                                            >
                                        </td>
                                        <td style=";color: black">
                                                ${ht.chiTietSanPham.sanPham.ten}, <br>
                                            hãng: ${ht.chiTietSanPham.sanPham.hangSanPham.ten},<br>
                                            màu: ${ht.chiTietSanPham.mauSac.ten},<br>
                                            ram: ${ht.chiTietSanPham.ram.dungLuong},<br>

                                            rom:${ht.chiTietSanPham.rom.dungLuong}

                                        </td>
                                        <td>
                                                ${ht.basoOchammotlamGHDGKG()} đ
                                        </td>
                                        <td>
                                                ${ht.soLuong}
                                        </td>
                                        <td>
                                                ${ht.tichDONGIAvsSL()} đ

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


<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 150%;margin-left: -25%">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Mã hóa đơn :${listghcthd.get(0).hoaDon.ma}</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div style="float: right;width: 77%">
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).sdt}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).convertTongtien()} đ<br>
                    ${banhangonline.convertgiatien(banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).phiShip)}
                    đ<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).nguoiNhan}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ngayTao}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ngayCapNhat}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ngayNhan}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ngayShip}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ngayThanhToan}<br>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrang==2}">
                        Đã thanh toán<br>
                    </c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrang==3}">
                        Chờ thanh toán<br>
                    </c:if>
                    Hóa đơn Online<br>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).hinhThucThanhToan==0}">
                        Tiền mặt<br>
                    </c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).hinhThucThanhToan==1}">
                        Chuyển khoản<br>
                    </c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrangGiaoHang == 0}">Chờ xử lý
                        <br></c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrangGiaoHang == 1}">Chờ giao hàng
                        <br></c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrangGiaoHang == 2}">Đang giao hàng
                        <br></c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrangGiaoHang == 3}">Giao hàng hoàn tất
                        <br></c:if>
                    <c:if test="${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).tinhTrangGiaoHang == 8}">Đã hủy<br></c:if>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).ghiChu}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).khachHang.hoTen}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).nhanVien.hoTen}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).diaChi.diaChi}-${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).diaChi.quan}-${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).diaChi.huyen}-${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).diaChi.thanhPho}<br>
                    ${banhangonline.timhdtheomahd(listghcthd.get(0).hoaDon.ma).maGiaoDich}<br>


                </div>
                <div style="float: left;width: 23%">
                    Số điện thoại nhận:<br>
                    Tổng tiền:<br>
                    Phí ship:<br>
                    Người nhận:<br>
                    Ngày tạo:<br>
                    Ngày cập nhật:<br>
                    Ngày nhận:<br>
                    Ngày ship:<br>
                    Ngày thanh toán:<br>
                    Tình trạng:<br>
                    Loại:<br>
                    Hình thức thanh toán:<br>
                    Tình trạng Giao Hàng:<br>
                    Ghi chú:<br>
                    Khách Hàng:<br>
                    Nhân Viên:<br>
                    Địa Chỉ:<br>
                    Mã giao dịch:<br>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<button id="error" style="display: none">Error</button>
<script>
    let error = document.getElementById('error');
    let notifications = document.querySelector('.notifications');

    function createToast() {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
            <div class="toast1 Success" style="height: 2cm;">
                <i class="fa-solid fa-circle-exclamation"></i>
                <div class="content">
                    <div class="title" style="font-size:20px">${tbkhithemimei}</div>

                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        notifications.appendChild(newToast);
        newToast.timeOut = setTimeout(
            () => newToast.remove(), 5000
        )
    }

    error.onclick = function () {
        let type = 'error';
        let icon = 'fa-solid fa-circle-exclamation';
        let title = 'Error';
        let text = 'This is a error toast.';
        createToast(type, icon, title, text);
    }

    <c:if test="${tbkhithemimei !=null}">document.getElementById('error').click()
    </c:if>
</script>
</body>
<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>


    let scanner = new Instascan.Scanner({video: document.getElementById('video1')});
    Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
            scanner.start(cameras[0]);
        } else {
            alert('Cameras found');
        }
    }).catch(function (e) {
        console.error(e);
    });

    scanner.addListener("scan", function (qrcode) {
        // Chuyển người dùng đến trang controller khi quét thành công
        window.location.href = "/hoa-don-online/them-hoa-don-chi-tiet/" + "${listghcthd.get(0).hoaDon.id}" + "/" + qrcode;

    });
</script>
</html>