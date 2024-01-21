<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
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
    <ul class="nav nav-tabs border-top"
        id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/khach-hang/hien-thi"
               role="tab"
               class="nav-link">
                Thông tin khách hàng
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link active"
               id="description-tab"
            <%--                   data-toggle="tab"--%>
               href="/khach-hang/view-detail/${kh.id}" role="tab"
               aria-controls="description"
               aria-selected="true">
                Xem thông tin khách hàng
            </a>
        </li>
        <li class="nav-item">
            <a href="/khach-hang/khach-hang-tung-xoa"
               role="tab"
               class="nav-link">
                Khách hàng từng xóa
            </a>
        </li>
    </ul>
</div>

<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">
                <form:form action="/khach-hang/update" method="post" modelAttribute="kh"
                           enctype="multipart/form-data">
                    <P style="display: none">
                        <form:input path="id"></form:input>
                    </P>
                    <div style="display: none">
                        <input style="" type="text" name="checkanh" value="cu" id="cucheck">
                        <br>
                    </div>
                    <div align="center">
                        <br>
                        <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                               for="anhmoi">
                            <img id="preview-image-2" class="preview-image" src="../../../uploads/${kh.anh}" alt=""
                                 width="100%" height="100%"
                                 style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">

                            <br><br>
                            ẢNH
                        </label>
                        <br>
                        <div style="display: none">
                            <input type="file" name="images" accept="image/jpeg, image/png" id="anhmoi">
                        </div>
                    </div>
                    <div style="display: none">
                        <form:input path="anh"/>
                        <form:input path="ngayTao" type="date"/>
                        <form:input class="form-control" readonly="true" placeholder="" path="matKhau"/>
                        <form:input class="form-control" readonly="true" placeholder="" path="taiKhoan"/>
                    </div>
                    <form class="form-sample">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="hoTen">Họ tên:
                                        <form:errors path="hoTen" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder="" path="hoTen"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label"
                                                path="gioiTinh">Giới Tính:</form:label>
                                    <div class="col-sm-9">
                                        <div class="form-control" readonly="true">
                                            <form:radiobutton path="gioiTinh" value="true"/>Nam
                                            <form:radiobutton path="gioiTinh" value="false"
                                                              cssStyle="margin-left: 1cm"/> Nữ
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="email">Email:
                                        <form:errors path="email" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder="" path="email"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="sdt">Sdt:
                                        <form:errors path="sdt" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder="" path="sdt"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="ngaySinh">
                                        Ngày sinh:
                                        <div id="tb" style="color: crimson;float: right"></div>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder="" path="ngaySinh"
                                                    type="date"
                                                    id="ns"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="taiKhoan">Tài khoản:
                                        <form:errors path="taiKhoan" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder=""
                                                    path="taiKhoan"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label" path="diem">Điểm:
                                        <form:errors path="diem" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input class="form-control" readonly="true" placeholder="" path="diem"
                                                    type="number"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <form:label class="col-sm-3 col-form-label"
                                                path="hangKhachHang">Hãng khách hàng:
                                        <form:errors path="hangKhachHang" cssStyle="color: red"/>
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:select class="form-control" readonly="true"
                                                     path="hangKhachHang"
                                                     items="${hkh}"
                                                     itemValue="id"
                                                     itemLabel="ten"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </form:form>
                <br><br>
                <P style="font-size: 50px;text-align: center">
                    Lịch sử mua hàng
                </P>
                <br>
                <P style="color: red"> CHỌN VÀO HÓA ĐƠN ĐỂ XEM CHI TIẾT</P>
                <div class="col-sm-12">
                    <div class="card-box table-responsive">
                        <table class="table table-striped" id="datatable-responsive" style="color: black">
                            <tr>
                                <th>STT</th>
                                <th>Tên khách hàng</th>
                                <th>Tên hóa đơn</th>
                                <th>Tên nhân viên</th>
                                <th>Ngày nhận</th>
                                <th>Tổng tiền</th>
                                <th>Tổng sản phẩm</th>
                                <th>Tình trạng</th>
                            </tr>
                            <c:forEach items="${lsmuahang}" var="ht" varStatus="stt">
                                <tr data-bs-toggle="modal" data-bs-target="#myModal">
                                    <td type="text" style="display: none" value="${ht.getmahd()}">${ht.getmahd()} </td>
                                    <td>${stt.index+1}</td>
                                    <td>${ht.gettenkh()}</td>
                                    <td>${ht.getmahd()} </td>
                                    <td>${ht.gettennv()}</td>
                                    <td>${ht.getngaynhan()}</td>
                                    <td>${ht.gettongtien()}</td>
                                    <td>${ht.gettongsp()}</td>
                                    <td>${ht.gettinhtrang()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <br>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center pagination-lg">
                        <li class="page-item"><a class="page-link" href="/khach-hang/view-detail/${kh.id}?num=0"> <</a>
                        </li>

                        <c:forEach begin="1" end="${total}" varStatus="status">
                            <li class="page-item">
                                <a href="/khach-hang/view-detail/${kh.id}?num=${status.index-1}"
                                   class="page-link">${status.index}</a>
                            </li>
                        </c:forEach>

                        <li class="page-item"><a class="page-link"
                                                 href="/khach-hang/view-detail/${kh.id}?num=${total-1}"> ></a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>


<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog modal-xl ">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">MÃ HÓA ĐƠN :<label id="ganmhd" style="color: red"></label></h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <P>Danh sách sản phẩm mua</P>

                <div id="click-response">Không có sản phẩm nào</div>
                <br>

                <P>TỔNG TIỀN: <label id="tongtien"></label> VND</P>
                <P>TIỀN QUY ĐỔI: <label id="tienquydoi"></label> VND</P>
                <P>THÀNH TIỀN: <label id="thanhtien"></label> VND</P>


            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>


    function onRowClick(tableId, callback) {
        var table = document.getElementById(tableId),
            rows = table.getElementsByTagName("tr"),
            i;
        for (i = 0; i < rows.length; i++) {
            table.rows[i].onclick = function (row) {
                return function () {
                    callback(row);
                };
            }(table.rows[i]);
        }
    };

    onRowClick("datatable-responsive", function (row) {
        var value1 = row.getElementsByTagName("td")[0].innerHTML;
        var gan = '';
        var stt = 1;

        var tongtien = 0;
        var tienquydoi = 0;
        var thanhtien = 0;
        <c:forEach items="${tthdkh}" var="ht"  >




        if("${ht.getmahd()}"==value1.trim()) {


            gan = gan +
                '<tr>' +
                '<td>' + stt + '</td>' +
                <%--' <td>'+'${ht.getmahd()}'+'</td>'+--%>
                '<td>' + '${ht.getsoimei()}' + '</td>' +
                ' <td>' + '${ht.gettensp()}' + '</td>' +
                '<td>' + '${ht.getthongtin()}' + '</td>' +
                '<td>' + '${ht.getsoluong()}' + '</td>' +
                ' <td>' + '${ht.getdongia()}' + '</td>' +
                ' <td>' + '${ht.gettienquydoi()}' + '</td>' +
                ' </tr>';
            stt = stt + 1;
            tongtien = tongtien +${ht.getdongia()*ht.getsoluong()};
            if ("${ht.gettienquydoi()}".trim() === '') {
            } else {
                tienquydoi =${ht.getsoluong()};
            }
        }
        </c:forEach>
        thanhtien = tongtien - tienquydoi;
        document.getElementById("tongtien").innerHTML = tongtien;
        document.getElementById("tienquydoi").innerHTML = tienquydoi;
        document.getElementById("thanhtien").innerHTML = thanhtien;
        document.getElementById("ganmhd").innerHTML = value1;
        document.getElementById('click-response').innerHTML = '' +
            '<table class="table table-striped table-bordered dt-responsive nowrap"' +
            'cellspacing="0" width="100%"  id="bang">' +
            ' <tr> ' +
            '<th>STT  </th>' +
            // ' <th>Mã hóa đơn</th>' +
            ' <th>imei</th>' +
            ' <th>Tên sản phẩm</th>' +
            ' <th>Thông tin sản phẩm</th>' +
            ' <th>Số lượng</th>' +
            ' <th>Đơn giá</th>' +
            ' <th>Tiền quy đổi</th>' +
            ' </tr>' +
            '' +
            gan

            + ' </table>';


    });

</script>
<script>

    function thongbao() {
        var ns = document.getElementById("ns").value;
        if (confirm("Bạn muốn dùng trức năng") == true) {
            if (ns.trim() === '') {
                document.getElementById("tb").innerHTML = "Không để trống ngày sinh";
                document.getElementById("bt").type = "button"
                return false;
            } else {
                document.getElementById("bt").type = "submit";
                return true;
            }
        } else {
            return false;
        }


    }

    var checkbox = document.getElementsByName("checkanh");
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
            if (checkbox[i].value === 'cu') {
                document.getElementById('anhcu').style.display = ""
                document.getElementById('anhmoi').style.display = "none"
            } else {
                document.getElementById('anhcu').style.display = "none"
                document.getElementById('anhmoi').style.display = ""
            }
        }
    }


    document.body.addEventListener('change', function (e) {
        let target = e.target;

        switch (target.id) {
            case 'cucheck':
                document.getElementById('anhcu').style.display = ""
                document.getElementById('anhmoi').style.display = "none"
                break;
            case 'moicheck':
                document.getElementById('anhcu').style.display = "none"
                document.getElementById('anhmoi').style.display = ""
                break;

        }
    });
</script>
</html>