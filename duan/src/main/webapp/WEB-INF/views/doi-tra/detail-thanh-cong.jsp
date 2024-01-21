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
    <style>

        .custom-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .custom-table th, .custom-table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }

        .custom-table th {
            background-color: #f2f2f2;
        }

        .custom-table td img {
            max-width: 60px;
            max-height: 60px;
        }

        .custom-table button {
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .custom-table button:hover {
            background-color: #45a049;
        }


        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-table {
            border-collapse: collapse;
            width: 100%;
            border-radius: 5px;
            background-color: #fff;
        }

        .modal-table th, .modal-table td {
            border: 1px solid black;
            padding: 10px;
        }

        .modal {
            border-collapse: collapse;
            width: 100%;
            border-radius: 5px;
            background-color: #fff;
        }

        .modal-table {
            border: 1px solid black;
            padding: 10px;
        }

        .modal-title {
            font-size: 18px;
            font-weight: bold;
            text-align: center;
        }

        #yourModalId {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

            text-align: center;
        }

        #imageModal img {
            max-width: 100%;
            height: auto;
        }


        #yourModalId h2 {
            color: #333;
        }

        #yourModalId p {
            color: #666;
        }

        #yourModalId button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #yourModalId button:hover {
            background-color: #45a049;
        }


    </style>
</head>
<body>

<div>


    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/doi-tra/hien-thi" class="nav-link"
               tabindex="-1"
               role="button">
                Trang phiếu đổi trả</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Xem phiếu đổi trả</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
        <div class="col-12 grid-margin">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <br>
                            <h3 style="text-align: center;">Hóa đơn chi tiết</h3>
                            <br>

                            <div class="table-responsive custom-table">
                                <table class="display custom-table" style="color: black">
                                    <thead>
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Hãng</th>
                                        <th>Số IMEI</th>
                                        <th>Giá Bán</th>

                                    </tr>
                                    </thead>
                                    <tbody id="table-search">
                                    <!-- Dữ liệu của bảng 1 -->
                                    <c:forEach items="${hdctne}" var="hdct">
                                        <tr>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.ten}</td>
                                            <td align="center">
                                                <img src="/uploads/${hdct.imei.chiTietSanPham.urlAnh}" width="40"
                                                     height="40">
                                            </td>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten}</td>
                                            <td>${hdct.imei.soImei}</td>
                                            <td>${hdct.donGia}</td>
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

        <div class="col-12 grid-margin">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <br>
                            <h3 style="text-align: center;">Sản phẩm cần đổi mới</h3>
                            <br>

                            <div class="table-responsive custom-table">

                                <table class="display custom-table" style="color: black">
                                    <thead>
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Hãng</th>
                                        <th>Số IMEI</th>
                                        <th>Đơn Giá</th>
                                        <th>Hình thức</th>
                                    </tr>
                                    </thead>
                                    <tbody id="table-search1">
                                    <c:forEach items="${listdtct}" var="hdct" varStatus="loop">
                                        <tr class="table-row" data-imei-id="${hdct.imei.id}" onclick="openModal(this)">
                                            <td>${hdct.hoaDonChiTiet.imei.chiTietSanPham.sanPham.ten}</td>
                                            <td align="center">
                                                <img src="/uploads/${hdct.hoaDonChiTiet.imei.chiTietSanPham.urlAnh}"
                                                     width="40" height="40">
                                            </td>
                                            <td>${hdct.hoaDonChiTiet.imei.chiTietSanPham.sanPham.hangSanPham.ten}</td>
                                            <td>${hdct.hoaDonChiTiet.imei.soImei}</td>
                                            <td>${hdct.hoaDonChiTiet.donGia}</td>
                                            <td>${hdct.hinhThucDoiTra == 0 ? 'Đổi mới   ' : (hdct.hinhThucDoiTra == 1 ? 'Hoàn hàng' : '')} </td>
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
</div>


<div id="yourModalId" class="modal"
     style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 1000px; height: 300px;">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h5 class="modal-title">Sản phẩm đổi mới</h5>
        <table class="modal-table" style="color: black">
            <thead>
            <tr>
                <th>Tên Sản Phẩm</th>
                <th>Hãng</th>
                <th>Số IMEI</th>
                <th>Giá Bán</th>
                <th>Hình ảnh</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td id="tenSanPhamModal"></td>
                <td id="hangSanPhamModal"></td>
                <td id="imeiModal"></td>
                <td id="giaBanModal"></td>
                <td id="imageModal"></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>


</body>

<script>
    function openModal(element) {
        var imeiId = $(element).data('imei-id');

        $.ajax({
            type: "GET",
            url: "/doi-tra/detail-imei/" + imeiId,
            success: function (data) {
                // Xử lý dữ liệu hàng về và hiển thị trong modal
                $("#tenSanPhamModal").text(data.chiTietSanPham.sanPham.ten);
                $("#hangSanPhamModal").text(data.chiTietSanPham.sanPham.hangSanPham.ten);
                $("#imeiModal").text(data.soImei);
                $("#giaBanModal").text(data.chiTietSanPham.giaBan);
                $("#imageModal").html('<img src="/uploads/' + data.chiTietSanPham.urlAnh + '" width="80" height="80">');


                // Hiển thị modal
                $("#yourModalId").css("display", "block");
            },
            error: function () {
                console.log("Error while getting data from the server");
            }
        });
    }

    function closeModal() {
        $("#yourModalId").css("display", "none");
    }
</script>
<script>
    document.getElementById('xacNhanButton').addEventListener('click', function (event) {
        event.preventDefault();

        // Lấy giá trị doitraId từ thuộc tính data-doitraid


        // Gửi yêu cầu POST đến đường dẫn cụ thể với doitraId
        fetch("/doi-tra/xac-nhan/" + doitraId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                // Các dữ liệu khác nếu cần
            }),
        }).then(response => {
            if (response.ok) {
                // Xử lý khi yêu cầu thành công
                console.log('Xác nhận thành công');

                // Chuyển hướng trình duyệt web sau khi xác nhận thành công
                window.location.href = "/doi-tra/hien-thi";
            } else {
                // Xử lý khi yêu cầu không thành công
                console.error('Xác nhận không thành công');
            }
        }).catch(error => {
            console.error('Lỗi:', error);
        });
    });


</script>


<script>
    function xoaSanPham(hdctId) {
        // Gửi yêu cầu xóa bất đồng bộ
        $.ajax({
            url: '/doi-tra/delete-hdct/' + doitraId + '/' + hdctId,
            type: 'GET',
            success: function (data) {
                // Load lại trang sau khi xóa thành công
                location.reload();
            },
            error: function (error) {
                console.error('Lỗi khi xóa', error);
            }
        });
    }
</script>


<%--//Xóa sp cần đổi hàng--%>

<script>
    var xoahdctId; // Biến toàn cục để lưu giá trị hdctId

    // Lắng nghe sự kiện click trên button
    document.querySelectorAll('.xoaSanPhamDoiTraButton').forEach(button => {
        button.addEventListener('click', function () {
            // Lấy giá trị hdct.id từ thuộc tính data
            var hdctId = this.getAttribute('data-idhdctmoixoa');

            // Lưu giá trị hdctId vào biến toàn cục
            xoahdctId = hdctId;

            // Gọi hàm để xử lý với giá trị hdct.id
            chonSanPham();
        });
    });

    // Hàm xử lý khi click
    function chonSanPham() {
        // Sử dụng giá trị hdct.id từ biến toàn cục
        console.log('Selected hdct.id:', xoahdctId);

        // Gửi giá trị hdct.id đến trang khác thông qua Ajax
        // Có thể thực hiện gửi giá trị này trong hàm fetch
    }
</script>


<%--// chọn sản phẩm--%>
<script>
    var globalHdctId; // Biến toàn cục để lưu giá trị hdctId

    // Lắng nghe sự kiện click trên button
    document.querySelectorAll('.chonSanPhamDoiTraButton').forEach(button => {
        button.addEventListener('click', function () {
            // Lấy giá trị hdct.id từ thuộc tính data
            var hdctId = this.getAttribute('data-idhdctmoi');

            // Lưu giá trị hdctId vào biến toàn cục
            globalHdctId = hdctId;

            // Gọi hàm để xử lý với giá trị hdct.id
            chonSanPham();
        });
    });

    // Hàm xử lý khi click
    function chonSanPham() {
        // Sử dụng giá trị hdct.id từ biến toàn cục
        console.log('Selected hdct.id:', globalHdctId);

        // Gửi giá trị hdct.id đến trang khác thông qua Ajax
        // Có thể thực hiện gửi giá trị này trong hàm fetch
    }
</script>


<%--<script>--%>
<%--    // Lắng nghe sự kiện click trên button--%>
<%--    document.querySelectorAll('.chonSanPhamButton').forEach(button => {--%>
<%--        button.addEventListener('click', function() {--%>
<%--            // Lấy giá trị hdct.id từ thuộc tính data--%>
<%--            var hdctId = this.getAttribute('data-idhdct');--%>
<%--            console.log(doitraId)--%>

<%--            // Lấy giá trị doitraId từ URL--%>
<%--            // var doitraId = window.location.pathname.split('/').pop();--%>

<%--            // Chuyển hướng đến controller và truyền hdctId qua RequestParam--%>
<%--            window.location.href = "/doi-tra/them-dtct?hdctId=" + hdctId + "&doitraId=" + doitraId;--%>

<%--        });--%>
<%--    });--%>

<%--</script>--%>

<script>
    document.querySelectorAll('.chonSanPhamButton').forEach(button => {
        button.addEventListener('click', function () {
            var hdctId = this.getAttribute('data-idhdct');
            var doitraId = window.location.pathname.split('/').pop();

            // Hiển thị modal
            $('#myModal').modal('show');

            // Lưu thông tin hdctId và doitraId trong form để sử dụng khi thêm
            $('#doiTraForm').data('hdctId', hdctId);
            $('#doiTraForm').data('doitraId', doitraId);
        });
    });

    function themThongTinDoiTra() {
        var lyDo = $('#lyDo').val();
        var hienTrang = $('#hienTrangSanPham').val();
        var hinhThuc = $('#hinhThucDoiTra').val();
        var hdctId = $('#doiTraForm').data('hdctId');


        // Thực hiện thêm thông tin và chuyển hướng đến đường dẫn /doi-tra/them-dtct với các tham số truyền vào
        window.location.href = "/doi-tra/them-dtct?hdctId=" + hdctId + "&doitraId=" + doitraId + "&lyDo=" + lyDo + "&hienTrang=" + hienTrang + "&hinhThuc=" + hinhThuc;
    }
</script>


<script>
    function kiemTraGia() {
        // Lấy giá trị hdct.donGia từ biến toàn cục
        var hdctDonGia = selectedDonGia;

        // Lấy giá trị ctsp.giaBan từ dữ liệu HTML
        var ctspGiaBan = parseFloat(document.querySelector('[data-bs-target="#nhapImei"]').getAttribute('data-giaban'));

        // In ra console để kiểm tra giá trị
        console.log("hdctDonGia:", hdctDonGia);
        console.log("ctspGiaBan:", ctspGiaBan);

        // Kiểm tra điều kiện so sánh giá trị
        if (hdctDonGia === ctspGiaBan) {
            // Thực hiện hành động nếu giá trị bằng nhau
            console.log("Giá trị hợp lệ. Thực hiện thêm các hành động khác ở đây.");
        } else {
            // Thực hiện hành động nếu giá trị không bằng nhau
            console.log("Giá trị không hợp lệ. Thực hiện hành động khác nếu cần.");
        }
    }
</script>
<%--<script>--%>
<%--    function kiemTraGia() {--%>
<%--        var hdctDonGia = selectedDonGia;--%>
<%--        // Lấy giá trị ctsp.giaBan từ dữ liệu HTML--%>
<%--        var ctspGiaBan = parseFloat(document.querySelector('[data-bs-target="#nhapImei"]').getAttribute('data-giaban'));--%>

<%--        // In ra console để kiểm tra giá trị--%>
<%--        console.log("selectedDonGia:", selectedDonGia);--%>
<%--        console.log("ctspGiaBan:", ctspGiaBan);--%>

<%--        // Kiểm tra điều kiện so sánh giá trị--%>
<%--        if (selectedDonGia >= ctspGiaBan) {--%>
<%--            // Thông báo lỗi nếu giá trị không hợp lệ--%>
<%--            alert("Giá không được lớn hơn hoặc bằng " + ctspGiaBan);--%>
<%--        } else {--%>
<%--            // Thực hiện hành động nếu giá trị hợp lệ--%>
<%--            // Mở modal nhập IMEI--%>
<%--            $('#nhapImei').modal('show');--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>


<script>
    function showDoiTraModal() {
        // Hiển thị modal đổi hàng
        $("#doiTra").modal("show");
    }
</script>

<script>
    $(document).ready(function () {
        // Sự kiện click vào nút "Chọn sản phẩm"
        $(".chonSanPhamButton").on("click", function () {
            // Hiển thị modal mới
            $('#exampleModalChonSanPham').modal('show');
        });
    });
</script>

<script>
    // Định nghĩa một biến toàn cục để lưu giá trị hdct.donGia
    var selectedDonGia;

    function showChonSanPhamModalWithDonGia(donGia) {
        // Lưu giá trị hdct.donGia vào biến toàn cục
        selectedDonGia = donGia;

        // Gọi hàm hiển thị modal chọn sản phẩm ở đây
        showChonSanPhamModal();
    }

</script>

<script>
    function showChonSanPhamModal() {
        // Hiển thị modal chọn sản phẩm
        $("#exampleModalChonSanPham").modal("show");
    }

</script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>

<script>

    $('#selectKhachHang1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectNhanVien1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectDiaChi1').select2({
        theme: 'bootstrap-5'
    });
</script>

<script>
    $('button[id^="searchImei"]').on('click', async function (e) {
        const btn = $(this);
        const parentModal = btn.closest('.modal'); // Lấy modal cha gần nhất của nút "Tìm kiếm" được nhấn
        const search = parentModal.find("#imeiSearchInput").val();
        const url = "http://localhost:8080/doi-tra/search-imei?search-imei=" + search;
        if (search === "") {
            let html = `
                <tr>
                    <td colspan="4" style="text-align: center; color: red"><strong>Vui lòng nhập IMEI trước khi tìm kiếm.</strong></td>
                </tr>`;
            parentModal.find(".imei_search").html(html);
            return;
        }
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            if (data.length === 0) {
                let html = `
                <tr>
                    <td colspan="4" style="text-align: center; color: red"><strong>IMEI đang chờ xử lý hoặc đã bán!</strong></td>
                </tr>`;
                parentModal.find(".imei_search").html(html);
            } else {
                // Hiển thị dữ liệu tìm kiếm
                let html = ``;
                for (let i = 0; i < data.length; i++) {
                    const imei = data[i];

                    const tr = `
            <tr>
                <td>` + imei.chiTietSanPham.sanPham.ten + `</td>
                <td>` + imei.soImei + `</td>
                <td>` + (imei.tinhTrang == 0 ? "Chưa bán" : "Đã bán") + `</td>
               <td><a href="/doi-tra/them-imei/` + imei.id + `/` + doitraId + `/` + globalHdctId + `">Thêm IMEI</a></td>
            </tr>
            `;
                    html += tr;
                }
                parentModal.find(".imei_search").html(html);
            }
        } catch (err) {
            console.error(err)
        }
    });
</script>


<script>
    // Lấy giá trị doitraId từ tham số truyền vào URL
    var doitraId = '${param.doitraId}';

    // Thiết lập giá trị vào ô input khi trang được tải
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById('doitraIdInput').value = doitraId;
    });
</script>

<script>
    // Lấy giá trị doitraId từ tham số truyền vào URL
    var hoadonId = '${param.hoadonId}';


    // Thiết lập giá trị vào ô input khi trang được tải
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById('hoadonIdInput').value = hoadonId;
    });
</script>

<script>
    function loadInterface(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('banglocthaydoi');
                content.innerHTML = data;

                loadScripts();
                // loadSelect2();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
    }

    function loadScripts() {
        const scriptsToLoad = [
            '../../../vendor/datatables/js/jquery.dataTables.min.js',
            '../../../js/plugins-init/datatables.init.js'


        ];

        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);
                // loadSelect2();
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    function clickcombobox() {
        var x1 = encodeURIComponent(document.getElementById("hangds1").value);
        var x2 = encodeURIComponent(document.getElementById("camds1").value);
        var x3 = encodeURIComponent(document.getElementById("mands1").value);
        var x4 = encodeURIComponent(document.getElementById("mauds1").value);
        var x5 = encodeURIComponent(document.getElementById("ramds1").value);
        var x6 = encodeURIComponent(document.getElementById("romds1").value);
        // var x7 = encodeURIComponent(document.getElementById("pinds1").value);
        var x8 = encodeURIComponent(document.getElementById("dungds1").value);
        var x9 = encodeURIComponent(document.getElementById("chipds1").value);
        var x10 = encodeURIComponent(document.getElementById("sands1").value);

        var link = '/doi-tra/loc/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + 'null' + '/' + x8 + '/' + x9 + '/' + x10;
        // document.getElementById("vt").innerHTML=link
        loadInterface(link);
        // document.getElementById("demo").innerHTML = "You selected: " + x;
    }


    function loadSelect2() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp
        $('#hangds1').select2({
            theme: 'bootstrap-5'
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
        });
        $('#ramds1').select2({
            theme: 'bootstrap-5'
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
        });

        $('#sands1').select2({
            theme: 'bootstrap-5'
        });

        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }

    loadSelect2();
</script>
</html>
