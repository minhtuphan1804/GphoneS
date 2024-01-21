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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>


    <!-- Favicon icon -->

    <style>
        p {
            color: black;
            font-size: 18px;
            margin-top: 7px;
        }
    </style>

</head>
<body>
<div>

    <H2> Các chính sách tại cửa hàng</H2>
    <br>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/imei/chinh-sach" role="tab"
               aria-controls="description" aria-selected="true" style="color: blue">Chính sách đổi - trả sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/chinh-sach-xu-ly-imei-loi" role="tab" style="color: black"
            >Chính sách sử lý imei lỗi</a>
        </li>
    </ul>
</div>

<div class="body1" style="margin-left: 10px">
                        <br>
    <br>
                        <h2>I. Chính sách đổi sản phẩm của GphoneS Store</h2>
                        <br>
                        <p>Để mang đến sự thuận tiện và trải nghiệm tốt nhất cho khách hàng, GphoneS Store có những chính sách phù hợp khi khách hàng có nhu cầu đổi hoặc trả sản phẩm(hiện tại cửa hàng chúng tôi chỉ cho đổi hàng, và chưa thể trả hàng). Chúng tôi luôn mong muốn mang lại cho khách hàng những sản phẩm và chất lượng phục vụ tốt nhất.</p>
                        <p>Việc đổi hàng sản phẩm đi kèm một số điều kiện cụ thể, Quý khách vui lòng tham khảo thông tin chi tiết bên dưới:</p>
                        <br>
                        <table>
                            <thead>
                            <tr style="">
                                <th style="width: 250px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px; color: black">
                                    Mục
                                </th>
                                <th style="width: 1000px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px; color: black">
                                    Nội dung chính sách
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="border: 2px solid black; font-weight: bold; color: black">1) Đối tượng áp dụng</td>
                                <td style="border: 2px solid black;">
                                    <p>Khách hàng</p>
                                    <p>* Áp dụng đối với sản phẩm mua hàng online qua website và trực tiếp tại <strong>GphoneS
                                        Store</strong></p>
                                    <p>* Chúng tôi không hỗ trợ cho những đơn hàng mua tại địa điểm khác.
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">2) Điều kiện đổi hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* Sản phẩm không trùng khớp về màu sắc, mẫu mã, size theo đơn hàng thì sẽ không được hỗ trợ đổi hàng</p>
                                    <p>* Lỗi kỹ thuật do nhà sản xuất.</p>
                                    <p>* Sản phẩm đổi hàng phải còn mới, nguyên vẹn, không bị dính nước, không có dấu hiệu rơi vỡ</p>
                                    <p>* Sản phẩm phải còn đầy đủ tem, nhãn mác, thẻ bảo hành và hoá đơn mua hàng.</p>
                                    <p>* Sản phẩm đổi hàng còn trong thời hạn cho phép đổi hàng.</p>
                                    <%--                    <p>* Khách hàng chỉ có thể đổi sản phẩm với điều kiện <strong>GphoneS Store</strong> không có sản--%>
                                    <%--                        phẩm cùng loại khác (màu sắc,--%>
                                    <%--                        mẫu mã, kích thước) thay thế.</p>--%>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">3) Thời hạn đổi hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* <strong> 7 ngày</strong> kể từ ngày khách nhận được hàng</p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">4) Thời hạn thực hiện đổi hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* Thời hạn đổi sản phẩm: 7 ngày đối với khách hàng mua online và 7 ngày đối với khách hàng mua trực
                                        tiếp tại cửa hàng kể từ ngày khách nhận được hàng.</p>
                                    <p>* Nếu vượt quá thời gian quy định trên thì chúng tôi sẽ không nhận đổi sản phẩm với bất kì lý do nào.</p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">5) Hình thức đổi hàng</td>
                                <td style="border: 2px solid black;">
                                    <P>* Sản phẩm chỉ được đổi một lần duy nhất
                                        <br>
                                        Quý khách vui lòng đến trực tiếp cửa hàng GPhoneS Store ghi trên hoá đơn mua hàng.
                                        <br>
                                        * Chúng tôi chỉ xác nhận đổi hàng sang những sản phẩm có cùng model máy như cùng dòng máy,
                                        dung lượng bố nhớ, dung lượng ram, cùng 1 dòng chip đối với những máy chạy hệ điều hành android và có thể khác màu máy so với sản phẩm cần đổi
                                        <%--                        Đối với khách hàng ở khu vực khác, vui lòng gọi điện trực tiếp đến số điện thoại 098123456 hoặc--%>
                                        <%--                        gửi yêu cầu qua mail gphonespluss@gmail.com Sau khi nhận được mail phản hồi xác nhận đồng ý từ--%>
                                        <%--                        GPhoneS, Quý khách đóng gói sản phẩm kèm đầy đủ phụ kiện, hoá đơn, phiếu bảo hành, …và gửi về--%>
                                    <p> <b>* Địa chỉ: số 3 phố Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội.</b></p>
                                    <p> <b>* Gmail: gphonespluss@gmail.com</b></p>

                                    <p>
                                    * Chúng tôi chỉ xác nhận đổi hàng sang những sản phẩm bằng giá của sản phẩm cần
                                    đổi
                                    </P>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">6) Lý do đổi hàng</td>
                                <td style="border: 2px solid black;">
                                    <P><b>* Lỗi phần cứng:</b> Lỗi phần cứng là lỗi xảy ra do hư hỏng phần cứng của điện thoại, chẳng hạn như màn hình bị nứt, camera bị mờ, loa bị rè,...<br>
                                        <b>* Lỗi phần mềm:</b> Lỗi phần mềm là lỗi xảy ra do lỗi trong hệ điều hành hoặc ứng dụng của điện thoại, chẳng hạn như điện thoại bị treo, bị lag, không thể mở ứng dụng,...<br>
                                        <b>* Lỗi thiết kế:</b> Lỗi thiết kế là lỗi xảy ra do thiết kế của điện thoại không đúng với thiết kế ban đầu về kích thước, thiếu tính năng theo thiết kế ban đầu<br>ví dụ: sản phẩm sai so với thiết kế thực tế(sai kích thước, thiếu chức năng mặc định của điện thoại...)
                                    </p>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <br><p><b>Ví dụ:</b> Iphone 15 pro max có dung lượng ram là 16GB, dung lượng bộ nhớ 1TB, màu trắng có thể đổi sang 1 chiếc Iphone 15 pro max có ram 16GB, dung lượng bộ nhớ 1TB và có thể sang màu khác như đen, vàng nếu khách hàng muốn đổi, hoặc nếu màu tương tự hết chúng tôi xin phép khách hàng có thể đổi sang màu đang có sẵn trong của hàng
                    </p>
                        <br>
                        <h2>II. Chính sách trả sản phẩm của GphoneS Store</h2>
                        <br>
                        <table>
                            <thead>
                            <tr style="">
                                <th style="width: 250px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px; color: black">
                                    Mục
                                </th>
                                <th style="width: 1000px; border: 2px solid black;padding-top: 0.5cm;text-align: center; padding-bottom: 0.5cm; font-size: 18px; color: black">
                                    Nội dung chính sách
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="border: 2px solid black; font-weight: bold; color: black">1) Đối tượng áp dụng</td>
                                <td style="border: 2px solid black;">
                                    <p>Khách hàng</p>
                                    <p>* Áp dụng đối với sản phẩm mua hàng online qua website và trực tiếp tại <strong>GphoneS
                                        Store</strong></p>
                                    <p>* Chúng tôi không hỗ trợ cho những đơn hàng mua tại địa điểm khác.
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">2) Điều kiện trả hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* Sản phẩm không trùng khớp về màu sắc, mẫu mã, size theo đơn hàng thì sẽ không được hỗ trợ trả hàng</p>
                                    <p>* Lỗi kỹ thuật do nhà sản xuất.</p>
                                    <p>* Sản phẩm trả hàng phải còn mới, nguyên vẹn, không bị dính nước, không có dấu hiệu rơi vỡ</p>
                                    <p>* Sản phẩm phải còn đầy đủ tem, nhãn mác, thẻ bảo hành và hoá đơn mua hàng.</p>
                                    <p>* Sản phẩm khách muốn trả hàng còn trong thời hạn cho phép trả hàng.</p>
                                    <%--                    <p>* Khách hàng chỉ có thể đổi sản phẩm với điều kiện <strong>GphoneS Store</strong> không có sản--%>
                                    <%--                        phẩm cùng loại khác (màu sắc,--%>
                                    <%--                        mẫu mã, kích thước) thay thế.</p>--%>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">3) Thời hạn trả hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* <strong> 7 ngày </strong> kể từ ngày khách nhận được hàng </p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">4) Thời hạn thực hiện trả hàng</td>
                                <td style="border: 2px solid black;">
                                    <p>* Thời hạn trả sản phẩm: 7 ngày kể từ ngày khách nhận được hàng đối với khách hàng mua online và 7 ngày đối với khách hàng mua trực
                                        tiếp tại cửa hàng</p>
                                    <p>* Nếu vượt quá thời gian quy định(vượt quá 7 ngày) thì chúng tôi sẽ không nhận trả sản phẩm với bất kì lý do nào.</p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">5) Hình thức trả hàng</td>
                                <td style="border: 2px solid black;">
                                    <P>* Sản phẩm chỉ được trả khi phù hợp với chính sách của cửa hàng
                                        <br>
                                        * Quý khách vui lòng đến trực tiếp cửa hàng GPhoneS Store ghi trên hoá đơn mua hàng.
                                        <br>
                                    <p> <b>* Địa chỉ: số 3 phố Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội.</b></p>
                                    <p> <b>* Gmail: gphonespluss@gmail.com</b></p>

                                    </P>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">6) Hoàn tiền cho khách</td>
                                <td style="border: 2px solid black;">
                                    <p> * Khách hàng sẽ được hoàn <b 95%</p> số tiền so với số tiền được ghi trong hóa đơn.</p>
                                    <p>* Nếu sản phẩm khách muốn trả là sản phẩm lúc khách mua hàng được giảm giá thì sẽ được cửa hàng trả lại hoàn toàn tiền. </p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: 2px solid black;font-weight: bold; color: black">7) Lý do trả hàng</td>
                                <td style="border: 2px solid black;">
                                    <P><b>* Lỗi phần cứng:</b> Lỗi phần cứng là lỗi xảy ra do hư hỏng phần cứng của điện thoại, chẳng hạn như màn hình bị nứt, camera bị mờ, loa bị rè,...<br>
                                        <b>* Lỗi phần mềm:</b> Lỗi phần mềm là lỗi xảy ra do lỗi trong hệ điều hành hoặc ứng dụng của điện thoại, chẳng hạn như điện thoại bị treo, bị lag, không thể mở ứng dụng,...<br>

                                        <b>* Lỗi thiết kế:</b>   Lỗi thiết kế là lỗi xảy ra do thiết kế của điện thoại không đúng với thiết kế ban đầu về kích thước, thiếu tính năng theo thiết kế ban đầu<br>
                                        ví dụ: sản phẩm sai so với thiết kế thực tế(sai kích thước, thiếu chức năng mặc định của điện thoại...)

                                        <%--                        <b>* Sản phẩm không phù hợp với người dùng:</b> khách hàng không hài lòng về sản phẩm của cửa hàng, không hài lòng về màu sắc, chất liệu...--%>

                                    </p>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <br><p>
                        <b>Ví dụ:</b> Khách hàng mua Iphone 15 pro max có dung lượng ram là 16GB, dung lượng bộ nhớ 1TB, màu trắng được ghi rõ ràng trong hóa đơn thì khi sản phẩm bị lỗi khách hàng được quyền trả sản phẩm đó cho cửa hàng và nhận lại 95% số tiền so với số tiền trong hóa đơn. Nếu lúc mua sản phẩm này đang được giảm giá thì khách hàng sẽ được cửa hàng hoàn trả lại đúng số tiền ban đầu. Cảm ơn quý khách!

                        <br>
                        <p><i class="fa fa-exclamation-triangle" style=" font-weight: bold; color: red"> LƯU Ý:</i>
                            * GPhoneS Store khuyến khích Quý khách hàng nên chụp ảnh sản phẩm trước khi đổi hoặc trả hàng hóa, việc lưu giữ sản
                            phẩm sẽ giúp khách hàng làm bằng chứng nếu có những vấn đề phát sinh trong quá trình đổi, trả hàng.</p>

                    </div>




</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>
