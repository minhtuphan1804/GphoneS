<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<body >
<div>
    <br>
    <c:if test="${hd.tinhTrangGiaoHang==0}">
        <c:if test="${hd.tinhTrang!=8}">
            <button class="btn btn-primary" style="float: right ;margin-right: 65px" data-bs-toggle="modal"
                    data-bs-target="#modalthaydoithongtinnhanhang" id="capnhatthongtingiaohang">Thay đổi thông tin
                nhận hàng
            </button>
        </c:if>
    </c:if>
    <br><br>
    <div class="row" style="margin-left: 5%;width: 90%">
        <div class="col-6">
            <div class="row" style="margin: 20px">
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Mã đơn hàng:</label>
                    <div class="col-sm-9">
                        <input type="text" value="${hd.ma}" disabled style="width: 97%" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Người mua:</label>
                    <div class="col-sm-9">
                        <input type="text" value="${hd.khachHang.hoTen}" disabled style="width: 97%"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">SĐT giao hàng:</label>
                    <div class="col-sm-9">
                        <input type="text" value="${hd.sdt}" disabled style="width: 97%" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
            <div class="row" style="margin: 20px">
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Ngày mua:</label>
                    <div class="col-sm-9">
                        <input type="text" value="${hd.ngayTao}" disabled style="width: 97%" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Địa chỉ nhận hàng:</label>
                    <div class="col-sm-9">
                        <input type="text"
                               value="${hd.diaChi.diaChi},${hd.diaChi.quan},${hd.diaChi.huyen},${hd.diaChi.thanhPho}"
                               disabled style="width: 97%"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Tổng tiền:</label>
                    <div class="col-sm-9">
                        <input type="text" value="${hd.tongTien} VNĐ" disabled style="width: 97%"
                               class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-left: 2.5%;width: 95%">
        <div class="col-4">
            <div class="row" style="margin: 20px">
                <div class="form-group row">
                    <label class="col-sm-6 col-label" style="padding: 5px">Phương thức thanh toán:</label>
                    <div class="col-sm-6">
                        <c:if test="${hd.hinhThucThanhToan==2}">
                            <input type="text" value="Chưa chọn" disabled style="width: 97%" class="form-control"/>
                        </c:if>
                        <c:if test="${hd.hinhThucThanhToan==0}">
                            <input type="text" value="Tiền mặt" disabled style="width: 97%" class="form-control"/>
                        </c:if>
                        <c:if test="${hd.hinhThucThanhToan==1}">
                            <input type="text" value="Chuyển khoản" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="row" style="margin: 20px">
                <div class="form-group row">
                    <label class="col-sm-3 col-label" style="padding: 5px">Trạng thái:</label>
                    <div class="col-sm-9">
                        <c:if test="${hd.tinhTrang==0}">
                            <input type="text" value="Chờ xử lý" disabled style="width: 97%" class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrang==1}">
                            <input type="text" value="Đã xác nhận" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrang==2}">
                            <input type="text" value="Đã Thanh toán" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrang==3}">
                            <input type="text" value="Chờ thanh toán" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrang==8}">
                            <input type="text" value="Đã hủy" disabled style="width: 97%" class="form-control"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="row" style="margin: 20px">
                <div class="form-group row">
                    <label class="col-sm-5 col-label" style="padding: 5px">Trạng thái giao hàng:</label>
                    <div class="col-sm-7">
                        <c:if test="${hd.tinhTrangGiaoHang==0}">
                            <input type="text" value="Chờ xử lý" disabled style="width: 97%" class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrangGiaoHang==1}">
                            <input type="text" value="Chuẩn bị giao" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrangGiaoHang==2}">
                            <input type="text" value="Đang giao" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrangGiaoHang==3}">
                            <input type="text" value="Giao hàng hoàn tất" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                        <c:if test="${hd.tinhTrangGiaoHang==8}">
                            <input type="text" value="Đã hủy" disabled style="width: 97%"
                                   class="form-control"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<div>
    <c:if test="${hd.tinhTrang==1}">
        <div class="row" style="text-align: center;">
            <div class="col-6">
                <a class="btn btn-info"
                   href="/ban-hang-online/xem-hoa-don-chi-tiet/thanh-toan-khi-nhan-hang/${hd.id}">Thanh toán
                    tiền mặt khi nhận hàng</a>
            </div>
            <div class="col-6">
                <a class="btn btn-info"
                   href="/pay/${hd.tongTien}/${hd.id}">Thanh toán
                    VNPay</a>
            </div>
        </div>
    </c:if>
</div>
<br>
<br>
<table class="table table-striped" style="text-content: center;width: 90%;margin-left: 5%;">
    <thead>
    <tr>
        <th>Mã đơn hàng</th>
        <th>Ảnh sản phẩm</th>
        <th>Sản phẩm</th>
        <th>Khuyến mãi (%/SP)</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Tổng</th>
        <th>Tình trạng</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="dem" scope="session" value="${0}"/>
    <c:forEach items="${listhdct}" var="ht" varStatus="stt">
        <c:if test="${stt.index==0}">
            <tr>
                <td>${ht.hoaDon.ma}</td>
                <td>
                    <img src="../../../uploads/${ht.imei.chiTietSanPham.urlAnh}" width="40" height="40"
                         style="border-radius:50% 50% 50% 50%">
                </td>
                <td>
                        ${ht.imei.chiTietSanPham.sanPham.ten} - ${ht.imei.chiTietSanPham.mauSac.ten}
                    - ${ht.imei.chiTietSanPham.ram.dungLuong} - ${ht.imei.chiTietSanPham.rom.dungLuong}.
                </td>
                <td>
                        ${ht.phantram()}%
                </td>

                <td>
                        ${ht.convertDongiaHDCT()}  VND
                </td>
                <td>
                        ${banhangonline.listIMEItheoIDHDvsIDCTSP(ht.hoaDon.id,ht.imei.chiTietSanPham.id).size()}
                </td>
                <td>${banhangonline.dongiaVSsoluongXemHDCT(ht.hoaDon.id,ht.imei.chiTietSanPham.id)}
                    VND
                </td>
                <td>
                    <c:if test="${ht.hoaDon.tinhTrang==0}">
                        <p>Chờ xử lý</p>
                    </c:if>
                    <c:if test="${ht.hoaDon.tinhTrang==1}">
                        <p>Đã xác nhận</p>
                    </c:if>
                    <c:if test="${ht.hoaDon.tinhTrang==2}">
                        <p>Đã Thanh toán</p>
                    </c:if>
                    <c:if test="${ht.hoaDon.tinhTrang==3}">
                        <p>Chờ thanh toán</p>
                    </c:if>
                    <c:if test="${ht.hoaDon.tinhTrang==8}">
                        <p>Đã hủy</p>
                    </c:if>
                </td>
                <td>
                    <c:if test="${ht.hoaDon.tinhTrangGiaoHang==0}">
                        <c:if test="${ht.hoaDon.tinhTrang==0}">
                            <a onclick="xoahdct('${ht.id}');" class="btn btn-primary">Hủy</a>
                        </c:if>
                    </c:if>
                        <%--                        <c:if test="${ht.hoaDon.tinhTrang==1}">--%>

                        <%--                        </c:if>--%>
                        <%--                        <c:if test="${ht.hoaDon.tinhTrang==2}">--%>

                        <%--                        </c:if>--%>
                        <%--                        <c:if test="${ht.hoaDon.tinhTrang==3}">--%>

                        <%--                        </c:if>--%>
                        <%--                        <c:if test="${ht.hoaDon.tinhTrang==8}">--%>

                        <%--                        </c:if>--%>
                </td>
            </tr>
            <c:set var="dem" scope="session" value="${dem+1}"/>
        </c:if>
        <c:if test="${stt.index>0}">
            <c:set var="checkck" scope="session" value="${0}"/>
            <c:forEach items="${listhdct}" var="ht1" varStatus="stt1" begin="0" end="${stt.index-1}">
                <c:if test="${ht.imei.chiTietSanPham.id==ht1.imei.chiTietSanPham.id}">
                    <c:set var="checkck" scope="session" value="${1}"/>
                </c:if>
            </c:forEach>
            <c:if test="${checkck==0}">
                <tr>
                    <td>${ht.hoaDon.ma}</td>
                    <td>
                        <img src="../../../uploads/${ht.imei.chiTietSanPham.urlAnh}" width="40" height="40"
                             style="border-radius:50% 50% 50% 50%">
                    </td>
                    <td>
                            ${ht.imei.chiTietSanPham.sanPham.ten} -
                            ${ht.imei.chiTietSanPham.mauSac.ten} - ${ht.imei.chiTietSanPham.ram.dungLuong}
                        - ${ht.imei.chiTietSanPham.rom.dungLuong}.
                    </td>
                    <td>
                            ${ht.phantram()}%
                    </td>

                    <td>
                            ${ht.convertDongiaHDCT()}  VND
                    </td>
                    <td>
                            ${banhangonline.listIMEItheoIDHDvsIDCTSP(ht.hoaDon.id,ht.imei.chiTietSanPham.id).size()}
                    </td>
                    <td>${banhangonline.dongiaVSsoluongXemHDCT(ht.hoaDon.id,ht.imei.chiTietSanPham.id)}
                        VND
                    </td>
                    <td>
                        <c:if test="${ht.hoaDon.tinhTrang==0}">
                            <p>Chờ xử lý</p>
                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==1}">
                            <p>Đã xác nhận</p>
                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==2}">
                            <p>Đã Thanh toán</p>
                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==3}">
                            <p>Chờ thanh toán</p>
                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==8}">
                            <p>Đã hủy</p>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${ht.hoaDon.tinhTrangGiaoHang==0}">
                            <c:if test="${ht.hoaDon.tinhTrang==0}">
                                <a onclick="xoahdct('${ht.id}');" class="btn btn-primary">Hủy</a>
                            </c:if>
                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==1}">

                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==2}">

                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==3}">

                        </c:if>
                        <c:if test="${ht.hoaDon.tinhTrang==8}">

                        </c:if>
                    </td>
                </tr>
                <c:set var="dem" scope="session" value="${dem+1}"/>
            </c:if>
        </c:if>
    </c:forEach>
    </tbody>
</table>

<div class="modal" id="modalthaydoithongtinnhanhang">

    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content" style="">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Thay đổi thông tin nhận hàng</h4>
            </div>
            <form action="/ban-hang-online/hoa-don-chi-tiet/cap-nhat-thong-tin-dat-hang" method="post">
                <!-- Modal body -->
                <div class="modal-body">
                    <input type="text" value="${hd.id}" name="idhd" style="display: none">
                    <div>Số điện thoại:<label style="background: white;color: red;border: 1px solid white"
                                              id="sodienthoai"></label></div>
                    <br>
                    <input type="text" id="b" name="sodienthoai" value="${hd.sdt}" style="width: 100%">
                    <br>
                    <br>
                    <div>Địa chỉ nhận hàng:<label style="background: white;color: red;border: 1px solid white"
                                                  id="c"></label></div>
                    <br>
                    <button type="button" style="float: right" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#modalthemdiachidathang">Thêm địa chỉ
                    </button>
                    <select id="diachids1" name="diachi" style="width: 70%;height: 1cm">
                        <c:forEach items="${banhangonline.Listdiachimotkhachang(idkhachhang)}" var="ht">
                            <option value="${ht.id}" ${ht.id==hd.id ?"selected":""} style="height: 1cm">
                                    ${ht.diaChi},${ht.quan},${ht.huyen},${ht.thanhPho}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger" onclick="return checksdt();">Cập nhật</button>
                    <%--                <button type="button" style="display: none" id="btthemdiachidathang" data-bs-dismiss="modal"></button>--%>
                </div>


            </form>
        </div>
    </div>
</div>

<div class="modal" id="modalthemdiachidathang">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content " style="">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Thêm địa chỉ</h4>
            </div>
            <!-- Modal body -->
            <form action="/ban-hang-online/hoa-don-chi-tiet/them-dia-chi" method="post">
                <div class="modal-body">
                    <div style="margin-left:2.5cm ">
                        <div>Địa chỉ:<label style="background: white;color: red;border: 1px solid white"
                                            id="tb1"></label></div>
                        <input type="text" id="themdiachidathangdiachi" name="diachi"><br>
                        <div>Quận:<label style="background: white;color: red;border: 1px solid white"
                                         id="tb2"></label></div>
                        <input type="text" id="themdiachidathangquan" name="quan"><br>
                        <div>Huyện:<label style="background: white;color: red;border: 1px solid white"
                                          id="tb3"></label></div>
                        <input type="text" id="themdiachidathanghuyen" name="huyen"><br>
                        <div>Thành phố:<label style="background: white;color: red;border: 1px solid white"
                                              id="tb4"></label></div>
                        <input type="text" id="themdiachidathangthanhpho" name="thanhpho"><br>
                        <input type="text" value="${hd.id}" name="idhd" style="display: none">
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger" onclick="return themdiachikhachhang();">Thêm
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%----%>

<button style="display: none" type="button" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#myModal1" id="thongbaotrangthaigiaohangbat">0000000
</button>
<!-- The Modal -->
<div class="modal" id="myModal1">
    <div class="modal-dialog" style="border: 1px solid red;margin-top: 3cm;">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Heading</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        id="thongbaotrangthaigiaohangtat"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                Hàng đang giao không thể thay đổi thông tin giao hàng
            </div>

        </div>
    </div>
</div>
</body>

</html>