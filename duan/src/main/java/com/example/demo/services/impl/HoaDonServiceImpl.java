package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DoiTra;
import com.example.demo.models.DoiTraChiTiet;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.DoiTraChiTietRepository;
import com.example.demo.repositories.DoiTraRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.repositories.IMEIRepository;
import com.example.demo.services.HoaDonService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;

@Service

public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    DoiTraRepository doiTraRepository;
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    IMEIRepository imeiRepository;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    DoiTraChiTietRepository doiTraChiTietRepository;

    @Override
    public Page<HoaDon> getAll(Pageable pageable) {
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public List<HoaDon> donHang() {
        return hoaDonRepository.donHang();
    }

    @Override
    public List<HoaDon> hoaDonKH(UUID id) {
        return hoaDonRepository.hoaDonKH(id);
    }

    ;

    @Override
    public List<HoaDon> hoaDon() {
        return hoaDonRepository.hoaDon();
    }

    @Override
    public List<HoaDon> hoaDonChoGiaoHang() {
        return hoaDonRepository.hoaDonChoGiaoHang();
    }

    @Override
    public List<HoaDon> hoaDonDangGiaoHang() {
        return hoaDonRepository.hoaDonDangGiaoHang();
    }

    @Override
    public List<HoaDon> hoaDonHoanTat() {
        return hoaDonRepository.hoaDonHoanTat();
    }

    @Override
    public List<HoaDon> hoaDonDaHuy() {
        return hoaDonRepository.hoaDonDaHuy();
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> searchDoiTra(String search) {
        return hoaDonRepository.searchDoiTra(search);
    }

    @Override
    public List<HoaDon> find() {
        return hoaDonRepository.find();
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon update(UUID id, HoaDon hoaDon) {
        if (id != null) {
            HoaDon hoaDonUpdate = hoaDonRepository.findById(id).orElse(null);
            if (hoaDonUpdate != null) {
                BeanUtils.copyProperties(hoaDon, hoaDonUpdate);
                hoaDonRepository.save(hoaDonUpdate);
            }
        }
        return null;
    }

    @Override
    public HoaDon thanhToan(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
            if (hoaDon != null) {
                hoaDonRepository.delete(hoaDon);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(UUID id, int tinhTrang, Date ngayCapNhat) {
        hoaDonRepository.update(id, tinhTrang, ngayCapNhat);
    }

    @Override
    public void autoUpdate() {
        Date getDate = Date.valueOf(LocalDate.now());
        List<HoaDon> list = listAuto();

        for (HoaDon hd : list) {
            List<HoaDonChiTiet> hdct = hoaDonChiTietRepository.getHoaDonChiTiet(hd.getId());
            System.out.println(hdct);

            if (!hdct.isEmpty()) {
                for (HoaDonChiTiet ct : hdct) {
                    ChiTietSanPham ctsp = chiTietSanPhamRepository.getChiTiet(ct.getImei().getId());
                    ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    ctsp.setNgayTao(date);
                    if (ctsp.getSoLuong() > 0) {
                        ctsp.setTinhTrang(0);
                    }
                    chiTietSanPhamRepository.save(ctsp);
                    imeiRepository.updateImei1(date, ct.getId());
                    ct.setTinhTrang(8);
                    hoaDonChiTietRepository.save(ct);
                }
                // Cập nhật tình trạng và ngày cập nhật sau khi xử lý tất cả HoaDonChiTiet
                hd.setTinhTrang(8);
                hd.setTinhTrangGiaoHang(8);
                hd.setGhiChu("Treo hóa đơn quá lâu");
                hd.setNgayCapNhat(getDate);
                hoaDonRepository.save(hd);
            } else {
                // Cập nhật tình trạng và ngày cập nhật sau khi xử lý tất cả HoaDonChiTiet
                hd.setTinhTrang(8);
                hd.setTinhTrangGiaoHang(8);
                hd.setGhiChu("Treo hóa đơn quá lâu");
                hd.setNgayCapNhat(getDate);
                hoaDonRepository.save(hd);
            }
        }
    }

    private List<HoaDon> listAuto() {
        Date getDate = Date.valueOf(LocalDate.now());
        List<HoaDon> list = hoaDonRepository.hoaDon();
        List<HoaDon> resultList = new ArrayList<>();
        for (HoaDon hd : list
        ) {
            if ((hd.getTinhTrang() == 0 || hd.getTinhTrang() == 1) && hd.getLoai() == 0) {
                resultList.add(hd);
            }
        }
        return resultList;
    }

    @Override
    public List<HoaDon> search(String ten, BigDecimal soTienQuyDoi) {
        return hoaDonRepository.search(ten, soTienQuyDoi);
    }

    @Override
    public List<HoaDon> searchChoGiaoHang(String ten, BigDecimal soTienQuyDoi) {
        return hoaDonRepository.searchChoGiaoHang(ten, soTienQuyDoi);
    }

    @Override
    public List<HoaDon> searchDangGiaoHang(String ten, BigDecimal soTienQuyDoi) {
        return hoaDonRepository.searchDangGiaoHang(ten, soTienQuyDoi);
    }

    @Override
    public List<HoaDon> searchHoanTat(String ten, BigDecimal soTienQuyDoi) {
        return hoaDonRepository.searchHoanTat(ten, soTienQuyDoi);
    }

    @Override
    public List<HoaDon> searchDaHuy(String ten, BigDecimal soTienQuyDoi) {
        return hoaDonRepository.searchDaHuy(ten, soTienQuyDoi);
    }

    @Override
    public List<HoaDon> loc1(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                             Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    ) {
        return hoaDonRepository.loc1(idKH, idNV, idDC, loai,
                startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate
        );
    }

    @Override
    public List<HoaDon> locChoGiaoHang(UUID idKH, UUID idNV, UUID idDC, Integer loai, Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate) {
        return hoaDonRepository.locChoGiaoHang(idKH, idNV, idDC, loai, startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate);
    }

    @Override
    public List<HoaDon> locDangGiaoHang(UUID idKH, UUID idNV, UUID idDC, Integer loai, Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate) {
        return hoaDonRepository.locDangGiaoGiaoHang(idKH, idNV, idDC, loai, startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate);
    }

    @Override
    public List<HoaDon> locHoanTat(UUID idKH, UUID idNV, UUID idDC, Integer loai, Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate) {
        return hoaDonRepository.locHoanTat(idKH, idNV, idDC, loai, startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate);
    }

    @Override
    public List<HoaDon> locDaHuy(UUID idKH, UUID idNV, UUID idDC, Integer loai, Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate) {
        return hoaDonRepository.locDaHuy(idKH, idNV, idDC, loai, startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate);
    }

    @Override
    public List<HoaDon> locDonHang(UUID idKH, UUID idNV, UUID idDC, Integer trangThai, Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate) {
        return hoaDonRepository.locDonHang(idKH, idNV, idDC, trangThai,
                startDate, endDate, shipStartDate, shipEndDate, receiveStartDate, receiveEndDate);
    }

    @Override
    public List<HoaDon> findAllByCreatedAtAfter(java.util.Date startDate) {
        return hoaDonRepository.findAllByCreatedAtAfter(startDate);
    }

    @Override
    public List<HoaDon> findAllByNgayNhan(java.util.Date startDate) {
        return hoaDonRepository.findAllByNgayNhan(startDate);
    }

    @Override
    public List<HoaDon> findAllByNgayShip(java.util.Date startDate) {
        return hoaDonRepository.findAllByNgayShip(startDate);
    }

    @Override
    public List<HoaDon> searchDonHang(String ten) {
        return hoaDonRepository.searchDonHang(ten);
    }

    @Override
    public List<HoaDon> findDonHangByCreatedAtAfter(java.util.Date startDate) {
        return hoaDonRepository.findDonHangByCreatedAtAfter(startDate);
    }

    @Override
    public List<HoaDon> findDonHangByNgayNhan(java.util.Date startDate) {
        return hoaDonRepository.findDonHangByNgayNhan(startDate);
    }

    @Override
    public List<HoaDon> findDonHangByNgayShip(java.util.Date startDate) {
        return hoaDonRepository.findDonHangByNgayShip(startDate);
    }

    @Override
    public ResponseEntity<byte[]> generatePdfDonTaiQuay(UUID hoaDonId) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            // Tạo nội dung HTML cho hóa đơn (thay đổi cho phù hợp với mẫu HTML của bạn)
            StringBuilder htmlContentBuilder = new StringBuilder();
            htmlContentBuilder.append("<html><head>");
            htmlContentBuilder.append("<meta charset=\"UTF-8\">");
            htmlContentBuilder.append("<title>Đơn hàng online</title>");
            htmlContentBuilder.append("<style>");
            htmlContentBuilder.append("body {\n" +
                    "    font-family: Arial, sans-serif;\n" +
                    "    line-height: 1.6;\n" +
                    "    background-color: #f9f9f9;\n" +
                    "    padding: 20px;\n" +
                    "    width: 1000px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    color: #338dbc;" +
                    "    text-align: center;\n" +
                    "    font-size: 24px;\n" +
                    "    margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;" +
                    "}\n" +
                    "\n" +
                    "h3 {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;\n" +
                    "    text-align: center;" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "    border-bottom: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "th {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "tr:hover {\n" +
                    "    background-color: #f5f5f5;\n" +
                    "}\n" +
                    "\n" +
                    "h1.order-details-title {\n" +
                    "    margin-top: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "p.footer-text {\n" +
                    "    margin-top: 30px;\n" +
                    "    text-align: center;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "    min-width: 1300px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    "\n" +
                    ".header {\n" +
                    "    text-align: center;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    ".footer {\n" +
                    "    text-align: center;\n" +
                    "    margin-top: 50px;\n" +
                    "    padding-top: 20px;\n" +
                    "    border-top: 1px solid #ddd;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".logo {\n" +
                    "    width: 100px;\n" +
                    "    height: auto;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table th, .product-table td {\n" +
                    "    text-align: left;\n" +
                    "}\n" +
                    "\n" +
                    ".total-amount {\n" +
                    "    font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    "/* Add more styles as needed */\n");
            htmlContentBuilder.append("</style>");
            htmlContentBuilder.append("<body>");

            //Các nội dung của html
            htmlContentBuilder.append("<h1>").append("GPhoneS Store").append("</h1>");

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            // Thêm thông tin đơn hàng

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(dateFormat.format(hoaDon.getNgayTao())).append("</p>");
            htmlContentBuilder.append("<p>Khách hàng đặt: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
            htmlContentBuilder.append("<p>Người nhận: ").append(hoaDon.getNguoiNhan()).append("</p>");
            htmlContentBuilder.append("<p>Số điện thoại người nhận: ").append(hoaDon.getSdt()).append("</p>");
            htmlContentBuilder.append("<p>Loại hóa đơn: ").append(hoaDon.getLoai() == 0 ? "Hóa đơn tại quầy" : "Hóa đơn Online").append("</p>");
            htmlContentBuilder.append("<p>Hình thức thanh toán: ").append(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản").append("</p>");
            htmlContentBuilder.append("<p>Trạng thái đơn: Đã thanh toán</p>");
            htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");


            String formattedTongTienDonHang = numberFormat.format(hoaDon.getTongTien());
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h3>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Thành tiền</th></tr>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.getHoaDonChiTiet(hoaDonId)) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
                        .append(" (").append(hoaDonChiTiet.getImei().getChiTietSanPham().getChip().getTen())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getMauSac().getTen())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getRam().getDungLuong())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getRom().getDungLuong())
                        .append("/").append(hoaDonChiTiet.getImei().getSoImei())
                        .append(")")
                        .append("</td>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            htmlContentBuilder.append("<p>Phí Ship: ").append(hoaDon.getPhiShip()).append("</p>");
            DoiTra dt = doiTraRepository.getDoiTraByHoaDon(hoaDonId);
            if (dt != null) {
                if (dt.getTinhTrang() == 2) {
                    List<DoiTraChiTiet> list = doiTraChiTietRepository.doiTraChiTiet(hoaDonId);
                    if (list.size() != 0) {
                        htmlContentBuilder.append("<h3>").append("Danh sách sản phẩm đổi hàng").append("</h3>");
                        htmlContentBuilder.append("<table>");
                        htmlContentBuilder.append("<tr><th>Sản phẩm cần đổi</th><th>Sản phẩm đổi mới/hoàn trả</th><th>Lý do đổi trả</th></tr>");
                        for (DoiTraChiTiet doiTraChiTiet : list) {
                            NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                            String fomatTienSanPham = fomatTien.format(doiTraChiTiet.getDonGia());
                            String fomatTienSanPhamCu = fomatTien.format(doiTraChiTiet.getHoaDonChiTiet().getDonGia());
                            htmlContentBuilder.append("<tr>");
                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getSanPham().getTen())
                                    .append(" (").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getSoImei()).append(")").append("</td>");
                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
                                    .append(" (").append(doiTraChiTiet.getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
                                    .append(doiTraChiTiet.getImei().getSoImei()).append(")").append("</td>");
                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getLyDo()).append("</td>");
                            htmlContentBuilder.append("</tr>");
                        }
                        htmlContentBuilder.append("</table>");
                    }
                }
            }
            htmlContentBuilder.append("<h3>Xin chân thành cảm ơn sự ủng hộ của bạn dành cho GPhoneS Store!</h3>");
            htmlContentBuilder.append("</body></html>");
            // Gọi phương thức tạo file PDF từ nội dung HTML, sử dụng thư viện iText
            byte[] pdfBytes = createPdfFromHtml(htmlContentBuilder);
            // Thiết lập thông tin phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don_" + hoaDonId + ".pdf");
            // Trả về file PDF dưới dạng byte[]
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<byte[]> generatePdfPhieu(UUID hoaDonId) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            // Tạo nội dung HTML cho hóa đơn (thay đổi cho phù hợp với mẫu HTML của bạn)
            StringBuilder htmlContentBuilder = new StringBuilder();
            htmlContentBuilder.append("<html><head>");
            htmlContentBuilder.append("<meta charset=\"UTF-8\">");
            htmlContentBuilder.append("<title>Đơn hàng online</title>");
            htmlContentBuilder.append("<style>");
            htmlContentBuilder.append("body {\n" +
                    "    font-family: Arial, sans-serif;\n" +
                    "    line-height: 1.6;\n" +
                    "    background-color: #f9f9f9;\n" +
                    "    padding: 20px;\n" +
                    "    width: 2000px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    color: #338dbc;" +
                    "    text-align: center;\n" +
                    "    font-size: 24px;\n" +
                    "    margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;" +
                    "}\n" +
                    "\n" +
                    "h3 {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;\n" +
                    "    text-align: center;" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "    border-bottom: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "th {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "tr:hover {\n" +
                    "    background-color: #f5f5f5;\n" +
                    "}\n" +
                    "\n" +
                    "h1.order-details-title {\n" +
                    "    margin-top: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "p.footer-text {\n" +
                    "    margin-top: 30px;\n" +
                    "    text-align: center;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "    min-width: 1300px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    "\n" +
                    ".header {\n" +
                    "    text-align: center;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    ".footer {\n" +
                    "    text-align: center;\n" +
                    "    margin-top: 50px;\n" +
                    "    padding-top: 20px;\n" +
                    "    border-top: 1px solid #ddd;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".logo {\n" +
                    "    width: 100px;\n" +
                    "    height: auto;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table th, .product-table td {\n" +
                    "    text-align: left;\n" +
                    "}\n" +
                    "\n" +
                    ".total-amount {\n" +
                    "    font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    "/* Add more styles as needed */\n");
            htmlContentBuilder.append("</style>");
            htmlContentBuilder.append("<body>");

            //Các nội dung của html
            htmlContentBuilder.append("<h1>").append("GPhoneS Store").append("</h1>");

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            // Thêm thông tin đơn hàng

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(dateFormat.format(hoaDon.getNgayTao())).append("</p>");
            htmlContentBuilder.append("<p>Khách hàng đặt: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
            htmlContentBuilder.append("<p>Người nhận: ").append(hoaDon.getNguoiNhan()).append("</p>");
            htmlContentBuilder.append("<p>Số điện thoại người nhận: ").append(hoaDon.getSdt()).append("</p>");
            htmlContentBuilder.append("<p>Loại hóa đơn: ").append(hoaDon.getLoai() == 0 ? "Hóa đơn tại quầy" : "Hóa đơn Online").append("</p>");
            htmlContentBuilder.append("<p>Hình thức thanh toán: ").append(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản").append("</p>");
            htmlContentBuilder.append("<p>Trạng thái đơn: Đã thanh toán</p>");
            htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");


            String formattedTongTienDonHang = numberFormat.format(hoaDon.getTongTien());
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h3>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Thành tiền</th></tr>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.getHoaDonChiTiet(hoaDonId)) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
                        .append(" (").append(hoaDonChiTiet.getImei().getChiTietSanPham().getChip().getTen())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getMauSac().getTen())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getRam().getDungLuong())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getRom().getDungLuong())
                        .append("/").append(hoaDonChiTiet.getImei().getSoImei())
                        .append(")")
                        .append("</td>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            htmlContentBuilder.append("<p>Phí Ship: ").append(hoaDon.getPhiShip()).append("</p>");

            DoiTra dt = doiTraRepository.getDoiTraByHoaDon(hoaDonId);
            if (dt != null ) {
                if(dt.getTinhTrang() == 2){
                    List<DoiTraChiTiet> list = doiTraChiTietRepository.doiTraChiTiet(hoaDonId);

                    if (list.size() != 0) {
                        htmlContentBuilder.append("<h3>").append("Danh sách sản phẩm đổi hàng").append("</h3>");
                        htmlContentBuilder.append("<table>");
                        htmlContentBuilder.append("<tr><th>Sản phẩm cần đổi</th><th>Sản phẩm đổi mới/hoàn trả</th><th>Lý do đổi trả</th></tr>");
                        for (DoiTraChiTiet doiTraChiTiet : list) {
                            if (doiTraChiTiet.getHinhThucDoiTra()==0){
                                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

                                htmlContentBuilder.append("<tr>");
                                htmlContentBuilder.append("<td>").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getSanPham().getTen())
                                        .append(" (").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getSoImei()).append(")").append("</td>");
                                htmlContentBuilder.append("<td>").append(doiTraChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
                                        .append(" (").append(doiTraChiTiet.getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getImei().getSoImei()).append(")").append("</td>");
                                htmlContentBuilder.append("<td>").append(doiTraChiTiet.getLyDo()).append("</td>");
                                htmlContentBuilder.append("</tr>");
                            }else{
                                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

                                String fomatTienSanPhamdoitra = fomatTien.format(doiTraChiTiet.getTienDoiTra());

                                htmlContentBuilder.append("<tr>");
                                htmlContentBuilder.append("<td>").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getSanPham().getTen())
                                        .append(" (").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
                                        .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getSoImei()).append(")").append("</td>");
                                htmlContentBuilder.append("<td>").append("Sản phẩm trả hàng,Tiền hoàn trả khách:").append(fomatTienSanPhamdoitra).append("</td>");
                                htmlContentBuilder.append("<td>").append(doiTraChiTiet.getLyDo()).append("</td>");
                                htmlContentBuilder.append("</tr>");
                            }


                        }
                        htmlContentBuilder.append("</table>");
                    }
                }
            }
            htmlContentBuilder.append("<h3>Xin chân thành cảm ơn sự ủng hộ của bạn dành cho GPhoneS Store!</h3>");
            htmlContentBuilder.append("</body></html>");

            // Gọi phương thức tạo file PDF từ nội dung HTML, sử dụng thư viện iText
            byte[] pdfBytes = createPdfFromHtml(htmlContentBuilder);

            // Thiết lập thông tin phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "phieu_doi_hang" + hoaDonId + ".pdf");

            // Trả về file PDF dưới dạng byte[]
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<byte[]> generatePdfDonOnline(UUID hoaDonId) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            // Tạo nội dung HTML cho hóa đơn (thay đổi cho phù hợp với mẫu HTML của bạn)
            StringBuilder htmlContentBuilder = new StringBuilder();
            htmlContentBuilder.append("<html><head>");
            htmlContentBuilder.append("<meta charset=\"UTF-8\">");
            htmlContentBuilder.append("<title>Đơn hàng online</title>");
            htmlContentBuilder.append("<style>");
            htmlContentBuilder.append("body {\n" +
                    "    font-family: Arial, sans-serif;\n" +
                    "    line-height: 1.6;\n" +
                    "    background-color: #f9f9f9;\n" +
                    "    padding: 20px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    color: #338dbc;" +
                    "    text-align: center;\n" +
                    "    font-size: 24px;\n" +
                    "    margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;" +
                    "}\n" +
                    "\n" +
                    "h3 {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;\n" +
                    "    text-align: center;" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "    border-bottom: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "th {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "tr:hover {\n" +
                    "    background-color: #f5f5f5;\n" +
                    "}\n" +
                    "\n" +
                    "h1.order-details-title {\n" +
                    "    margin-top: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "p.footer-text {\n" +
                    "    margin-top: 30px;\n" +
                    "    text-align: center;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "    max-width: 600px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    "\n" +
                    ".header {\n" +
                    "    text-align: center;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    ".footer {\n" +
                    "    text-align: center;\n" +
                    "    margin-top: 50px;\n" +
                    "    padding-top: 20px;\n" +
                    "    border-top: 1px solid #ddd;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".logo {\n" +
                    "    width: 100px;\n" +
                    "    height: auto;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table th, .product-table td {\n" +
                    "    text-align: left;\n" +
                    "}\n" +
                    "\n" +
                    ".total-amount {\n" +
                    "    font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    "/* Add more styles as needed */\n");
            htmlContentBuilder.append("</style>");
            htmlContentBuilder.append("<body>");

            //Các nội dung của html
            htmlContentBuilder.append("<h1>").append("GPhoneS Store").append("</h1>");

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            // Thêm thông tin đơn hàng
            java.util.Date ngayTao = hoaDon.getNgayTao();
//            String formattedTienGiam;
//            KhuyenMai km = hoaDon.getKhuyenMai();
//
//            // Kiểm tra nếu khuyến mãi là null hoặc không có id
//            if (km == null || km.getId() == null) {
//                formattedTienGiam = "0 VNĐ";
//            } else {
//                BigDecimal tienGiamToiDa = BigDecimal.valueOf(km.getGiaTriToiThieu());
//
//                BigDecimal tienGiamHoaDon = hoaDon.getTien_giam();
//
//                if (tienGiamHoaDon == null) {
//                    formattedTienGiam = "0 VNĐ";
//                } else if (tienGiamHoaDon.compareTo(tienGiamToiDa) >= 0) {
//                    formattedTienGiam = numberFormat.format(tienGiamHoaDon);
//                } else {
//                    formattedTienGiam = tienGiamHoaDon + "%";
//                }
//            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(dateFormat.format(hoaDon.getNgayTao())).append("</p>");
            htmlContentBuilder.append("<p>Khách hàng đặt: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
            htmlContentBuilder.append("<p>Người nhận: ").append(hoaDon.getNguoiNhan()).append("</p>");
            htmlContentBuilder.append("<p>Số điện thoại người nhận: ").append(hoaDon.getSdt()).append("</p>");
            htmlContentBuilder.append("<p>Loại hóa đơn: ").append(hoaDon.getLoai() == 0 ? "Hóa đơn tại quầy" : "Hóa đơn Online").append("</p>");
            htmlContentBuilder.append("<p>Hình thức thanh toán: ").append(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản").append("</p>");
            htmlContentBuilder.append("<p>Trạng thái đơn: Đã thanh toán</p>");
            htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");


            String formattedTongTienDonHang = numberFormat.format(hoaDon.getTongTien());
//            String formattedTongTienHoaDon = numberFormat.format(hoaDon.getTongTienHoaDon());
            // Thêm chi tiết đơn hàng
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h1>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Thành tiền</th></tr>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.getHoaDonChiTiet(hoaDonId)) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
                        .append(" (").append(hoaDonChiTiet.getImei().getChiTietSanPham().getChip().getTen())
                        .append("/").append(hoaDonChiTiet.getImei().getChiTietSanPham().getMauSac().getTen()).append(")")
                        .append("</td>");

                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            htmlContentBuilder.append("<p>Phí Ship: ").append(hoaDon.getPhiShip()).append("</p>");
//            htmlContentBuilder.append("<p>Tiền giảm: ").append(formattedTienGiam).append("</p>");
//            htmlContentBuilder.append("<p>Tổng tiền thanh toán: ").append(formattedTongTienHoaDon).append("</p>");

            htmlContentBuilder.append("<h3>Xin chân thành cảm ơn sự ủng hộ của bạn dành cho GPhoneS Store!</h3>");
            htmlContentBuilder.append("</body></html>");

            // Gọi phương thức tạo file PDF từ nội dung HTML, sử dụng thư viện iText
            byte[] pdfBytes = createPdfFromHtml(htmlContentBuilder);

            // Thiết lập thông tin phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don_" + hoaDonId + ".pdf");

            // Trả về file PDF dưới dạng byte[]
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }

    private byte[] createPdfFromHtml(StringBuilder htmlContent) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent.toString(), outputStream, converterProperties);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
