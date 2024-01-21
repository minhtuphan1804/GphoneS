

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<body>
<c:forEach items="${listctsp}" var="ht" varStatus="stt">
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
            Mã khuyến mãi:${ht.khuyenMai.ma}<br>
            Tên khuyến mãi:${ht.khuyenMai.ten}<br>
            Bắt đầu:${ht.khuyenMai.ngayBatDau}<br>
            Kết thúc:${ht.khuyenMai.ngayKetThuc}
        </td>
        <td>
                ${banhangonline.convertgiatien(ht.giaBan-ht.giaBan/100*khuyenMaiRepository.tonggiamgia(ht.id))}  VND

        </td>
        <td>
            <c:if test="${ht.khuyenMai==null}">
                <a
                        onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')"
                        class="btn btn-success" style="width: 3cm"
                >Áp dụng</a>
            </c:if>
            <c:if test="${ht.khuyenMai!=null}">
                <c:if test="${ht.khuyenMai.tinhTrang!=0}">
                    <c:if test="${ht.khuyenMai.id==kmchon.id}">
                        <a   onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                             class="btn btn-success" style="background-color: red;width: 3cm"
                        >Hủy áp dụng</a>

                    </c:if>
                    <c:if test="${ht.khuyenMai.id!=kmchon.id}">
                        <a   onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')"
                             class="btn btn-success" style="width: 3cm"
                        >Áp dụng</a>
                        <a   onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                             class="btn btn-success" style="background-color: red;width: 3cm"
                        >Hủy áp dụng</a>
                    </c:if>

                </c:if>
                <c:if test="${ht.khuyenMai.tinhTrang==0}">
                    <c:if test="${ht.khuyenMai.id==kmchon.id}">
                        <a   onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                             class="btn btn-success"  style="background-color: red;width: 3cm"
                        >Hủy áp dụng</a>

                    </c:if>
                    <c:if test="${ht.khuyenMai.id!=kmchon.id}">
                        <a   onclick="apdungKMvsCTSP('${ht.id}','${kmchon.id}')" style="width: 3cm"
                             class="btn btn-success"
                        >Áp dụng</a>
                        <a   onclick="HuyapdungkmVS1ctsp('${ht.id}')"
                             class="btn btn-success" style="background-color: red;width: 3cm"
                        >Hủy áp dụng</a>
                    </c:if>

                </c:if>
            </c:if>

        </td>

    </tr>
</c:forEach>

</body>
</html>
