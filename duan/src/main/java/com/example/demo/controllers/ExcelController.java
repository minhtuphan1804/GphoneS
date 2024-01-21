package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.IMEI;
import com.example.demo.models.SanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.HoaDonChiTietService;
import com.example.demo.services.HoaDonService;
import com.example.demo.services.IMEIService;
import com.example.demo.services.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExcelController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IMEIService imeiService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;


    private XWPFDocument document;


    @GetMapping("/hoa-don/export-excel")
    public String exportExcelHD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findAllByCreatedAtAfter(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách hoá đơn đã thanh toán");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue("Đã thanh toán");

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-hoa-don-theo-ngay-thanh-toan.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/hoa-don/export-excel-ngay-nhan")
    public String exportExcelNgayNhan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findAllByNgayNhan(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách hoá đơn theo ngày nhận");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue("Đã thanh toán");

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-hoa-don-theo-ngay-nhan.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/hoa-don/export-excel-ngay-ship")
    public String exportExcelNgayShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findAllByNgayShip(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách hoá đơn theo ngày ship");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue(hoadon.getTinhTrang() == 2 ? "Đã thanh toán":"Chờ thanh toán");

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-hoa-don-theo-ngay-ship.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel")
    public String exportExcelCTSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAll0();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-con-kinh-doanh.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-tiet-san-pham-ngung-kinh-doanh")
    public String exportExcelCTSPNgungKinhDoanh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.finAllTTOff();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-ngung-kinh-doanh.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/imei/export-excel")
    public String exportExcelImei(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        // thời gian theo 3 tháng gần nhẩt
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date startDate =  calendar.getTime();

        // Lấy dữ liệu từ database
        List<IMEI> listIMEI = imeiService.findAll0();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách imei");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Mã");
        cell = row.createCell(2);
        cell.setCellValue("Số imei");
        cell = row.createCell(3);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(4);
        cell.setCellValue("Ngày cập nhật");
        cell = row.createCell(5);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(6);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (IMEI imei : listIMEI) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);

            cell = row.createCell(0);
            cell.setCellValue(imei.getChiTietSanPham().getSanPham().getTen());


            cell = row.createCell(1);
            cell.setCellValue(imei.getMa());


            cell = row.createCell(2);
            cell.setCellValue(imei.getSoImei());

            cell = row.createCell(3);
            cell.setCellValue(imei.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(4);
            cell.setCellValue(imei.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(5);
            cell.setCellValue("Chưa bán");

            cell = row.createCell(6);
            cell.setCellValue(imei.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-imei-chua-ban.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/imei/export-excel-imei-da-ban")
    public String exportExcelImeiDaBan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        // thời gian theo 3 tháng gần nhẩt
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date startDate =  calendar.getTime();

        // Lấy dữ liệu từ database
        List<IMEI> listIMEI = imeiService.getImeiOff();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách imei");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Mã");
        cell = row.createCell(2);
        cell.setCellValue("Số imei");
        cell = row.createCell(3);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(4);
        cell.setCellValue("Ngày cập nhật");
        cell = row.createCell(5);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(6);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (IMEI imei : listIMEI) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);

            cell = row.createCell(0);
            cell.setCellValue(imei.getChiTietSanPham().getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(imei.getMa());

            cell = row.createCell(2);
            cell.setCellValue(imei.getSoImei());

            cell = row.createCell(3);
            cell.setCellValue(imei.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(4);
            cell.setCellValue(imei.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(5);
            cell.setCellValue(imei.getTinhTrang() == 1 ? "Dã bán" : "Chờ xử lý");

            cell = row.createCell(6);
            cell.setCellValue(imei.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-imei-da-ban.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/imei/export-excel-imei-loi")
    public String exportExcelImeiLoi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        // thời gian theo 3 tháng gần nhẩt
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date startDate =  calendar.getTime();

        // Lấy dữ liệu từ database
        List<IMEI> listIMEI = imeiService.findImeiLoi();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách imei");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã");
        cell = row.createCell(1);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(2);
        cell.setCellValue("Số imei");
        cell = row.createCell(3);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(4);
        cell.setCellValue("Ngày cập nhật");
        cell = row.createCell(5);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(6);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (IMEI imei : listIMEI) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);

            cell = row.createCell(0);
            cell.setCellValue(imei.getMa());


            cell = row.createCell(1);
            cell.setCellValue(imei.getChiTietSanPham().getSanPham().getTen());


            cell = row.createCell(2);
            cell.setCellValue(imei.getSoImei());

            cell = row.createCell(3);
            cell.setCellValue(imei.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(4);
            cell.setCellValue(imei.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(5);
            cell.setCellValue("Imei lỗi");

            cell = row.createCell(6);
            cell.setCellValue(imei.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-imei-da-ban.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/san-pham/export-excel")
    public String exportExcelSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        // thời gian theo 3 tháng gần nhẩt
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<SanPham> listSanPham = sanPhamService.findAll0();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(2);
        cell.setCellValue("Hãng");
        cell = row.createCell(3);
        cell.setCellValue("Màn hình");
        cell = row.createCell(4);
        cell.setCellValue("Camera");
        cell = row.createCell(5);
        cell.setCellValue("Thông số bluetooth");
        cell = row.createCell(6);
        cell.setCellValue("Hõ trợ mạng");
        cell = row.createCell(7);
        cell.setCellValue("Cổng giao tiếp");
        cell = row.createCell(8);
        cell.setCellValue("Thông số wifi");
        cell = row.createCell(9);
        cell.setCellValue("Kích thước");
        cell = row.createCell(10);
        cell.setCellValue("Trọng lượng");
        cell = row.createCell(11);
        cell.setCellValue("Chất liệu máy");
        cell = row.createCell(12);
        cell.setCellValue("Hệ điều hành");
        cell = row.createCell(13);
        cell.setCellValue("Số khe sim");
        cell = row.createCell(14);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(15);
        cell.setCellValue("Ngày cập nhật");
        cell = row.createCell(16);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(17);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (SanPham sanPham : listSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(sanPham.getMa());

            cell = row.createCell(1);
            cell.setCellValue(sanPham.getTen());

            cell = row.createCell(2);
            cell.setCellValue(sanPham.getHangSanPham().getTen());

            cell = row.createCell(3);
            cell.setCellValue(sanPham.getManHinh().getThongSo());

            cell = row.createCell(4);
            cell.setCellValue(sanPham.getCamera().getThongSo());

            cell = row.createCell(5);
            cell.setCellValue(sanPham.getBluetooth());

            cell = row.createCell(6);
            cell.setCellValue(sanPham.getHoTroMang());

            cell = row.createCell(7);
            cell.setCellValue(sanPham.getCongGiaoTiep());

            cell = row.createCell(8);
            cell.setCellValue(sanPham.getThongSoWifi());

            cell = row.createCell(9);
            cell.setCellValue(sanPham.getKichThuoc());

            cell = row.createCell(10);
            cell.setCellValue(sanPham.getTrongLuong());

            cell = row.createCell(11);
            cell.setCellValue(sanPham.getChatLieu());

            cell = row.createCell(12);
            cell.setCellValue(sanPham.getHeDieuHanh());

            cell = row.createCell(13);
            cell.setCellValue(sanPham.getSoSim());

            cell = row.createCell(14);
            cell.setCellValue(sanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(15);
            cell.setCellValue(sanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(16);
            cell.setCellValue(sanPham.getTinhTrang() == 0 ? "Hoạt động" : "Còn hoạt động");

            cell = row.createCell(17);
            cell.setCellValue(sanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-san-pham-con-kinh-doanh.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/san-pham/export-excel-san-pham-da-ngung-kinh-doanh")
    public String exportExcelSanPhamNgungKinhDoanh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        // thời gian theo 3 tháng gần nhẩt
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<SanPham> listSanPham = sanPhamService.findAll1();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(2);
        cell.setCellValue("Hãng");
        cell = row.createCell(3);
        cell.setCellValue("Màn hình");
        cell = row.createCell(4);
        cell.setCellValue("Camera");
        cell = row.createCell(5);
        cell.setCellValue("Thông số bluetooth");
        cell = row.createCell(6);
        cell.setCellValue("Hõ trợ mạng");
        cell = row.createCell(7);
        cell.setCellValue("Cổng giao tiếp");
        cell = row.createCell(8);
        cell.setCellValue("Thông số wifi");
        cell = row.createCell(9);
        cell.setCellValue("Kích thước");
        cell = row.createCell(10);
        cell.setCellValue("Trọng lượng");
        cell = row.createCell(11);
        cell.setCellValue("Chất liệu máy");
        cell = row.createCell(12);
        cell.setCellValue("Hệ điều hành");
        cell = row.createCell(13);
        cell.setCellValue("Số khe sim");
        cell = row.createCell(14);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(15);
        cell.setCellValue("Ngày cập nhật");
        cell = row.createCell(16);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(17);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (SanPham sanPham : listSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(sanPham.getMa());

            cell = row.createCell(1);
            cell.setCellValue(sanPham.getTen());

            cell = row.createCell(2);
            cell.setCellValue(sanPham.getHangSanPham().getTen());

            cell = row.createCell(3);
            cell.setCellValue(sanPham.getManHinh().getThongSo());

            cell = row.createCell(4);
            cell.setCellValue(sanPham.getCamera().getThongSo());

            cell = row.createCell(5);
            cell.setCellValue(sanPham.getBluetooth());

            cell = row.createCell(6);
            cell.setCellValue(sanPham.getHoTroMang());

            cell = row.createCell(7);
            cell.setCellValue(sanPham.getCongGiaoTiep());

            cell = row.createCell(8);
            cell.setCellValue(sanPham.getThongSoWifi());

            cell = row.createCell(9);
            cell.setCellValue(sanPham.getKichThuoc());

            cell = row.createCell(10);
            cell.setCellValue(sanPham.getTrongLuong());

            cell = row.createCell(11);
            cell.setCellValue(sanPham.getChatLieu());

            cell = row.createCell(12);
            cell.setCellValue(sanPham.getHeDieuHanh());

            cell = row.createCell(13);
            cell.setCellValue(sanPham.getSoSim());

            cell = row.createCell(14);
            cell.setCellValue(sanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(15);
            cell.setCellValue(sanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(16);
            cell.setCellValue(sanPham.getTinhTrang() == 0 ? "Hoạt động" : "Còn hoạt động");

            cell = row.createCell(17);
            cell.setCellValue(sanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-san-pham-ngung-kinh-doanh.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/don-hang/export-excel")
    public String exportExcelDonHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findDonHangByCreatedAtAfter(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách đơn hàng đã thanh toán");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue(hoadon.getTinhTrang());

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-don-hang-theo-ngay-thanh-toan.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/don-hang/export-excel-ngay-nhan")
    public String exportExcelDonHangNgayNhan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findDonHangByNgayNhan(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách hoá đơn theo ngày nhận");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue(hoadon.getTinhTrang());

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-don-hang-theo-ngay-nhan.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/don-hang/export-excel-ngay-ship")
    public String exportExcelDonHangNgayShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date startDate = calendar.getTime();

        // Lấy dữ liệu từ database
        List<HoaDon> listHoaDon = hoaDonService.findDonHangByNgayShip(startDate);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách hoá đơn theo ngày ship");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã hoá đơn");
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        cell = row.createCell(2);
        cell.setCellValue("Tên nhân viên");
        cell = row.createCell(3);
        cell.setCellValue("Địa chỉ");
//        cell = row.createCell(4);
//        cell.setCellValue("Điểm quy đổi");
        cell = row.createCell(4);
        cell.setCellValue("SĐT nhận hàng");
        cell = row.createCell(5);
        cell.setCellValue("Tổng tiền");
        cell = row.createCell(6);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(7);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(8);
        cell.setCellValue("Ngày thanh toán");
        cell = row.createCell(9);
        cell.setCellValue("Ngày nhận");
        cell = row.createCell(10);
        cell.setCellValue("Ngày ship");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (HoaDon hoadon : listHoaDon) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(hoadon.getMa());

            String hoTen = null;
            if (hoadon.getKhachHang() != null) {
                hoTen = hoadon.getKhachHang().getHoTen();
            }
            if (hoTen == null) {
                hoTen = " ";
            } else {
                cell = row.createCell(1);
                cell.setCellValue(hoTen);
            }


            cell = row.createCell(2);
            cell.setCellValue(hoadon.getNhanVien().getHoTen());

            String diaChi = null;
            if (hoadon.getDiaChi() != null) {
                diaChi = hoadon.getDiaChi().getDiaChi();
            }
            if (diaChi == null) {
                diaChi = " ";
            } else {
                cell = row.createCell(3);
                cell.setCellValue(diaChi);
            }
//            cell = row.createCell(4);
//            cell.setCellValue(hoadon.getQuyDoi().getDiemQuyDoi());

            String sdt = null;
            if (hoadon.getSdt() != null) {
                sdt = hoadon.getSdt();
            }
            if (sdt == null) {
                sdt = " ";
            } else {
                cell = row.createCell(4);
                cell.setCellValue(sdt);
            }

            String tongTien = null;
            if (hoadon.getSdt() != null) {
                tongTien = String.valueOf(hoadon.getTongTien());
            }
            if (tongTien == null) {
                tongTien = " ";
            } else {
                cell = row.createCell(5);
                cell.setCellValue(tongTien);
            }

            cell = row.createCell(6);
            cell.setCellValue(hoadon.getTinhTrang());

            cell = row.createCell(7);
            cell.setCellValue(hoadon.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(8);
            cell.setCellValue(hoadon.getNgayThanhToan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(9);
            cell.setCellValue(hoadon.getNgayNhan());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(10);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(hoadon.getNgayShip());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-don-hang-theo-ngay-ship.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-hang/{id}")
    public String exportExcelCTSPTheoHang(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllHang(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-hang.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-cam/{id}")
    public String exportExcelCTSPTheoCam(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllCam(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-cam.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-ram/{id}")
    public String exportExcelCTSPTheoRam(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllRam(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-ram.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-rom/{id}")
    public String exportExcelCTSPTheoRom(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllRom(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-rom.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-pin/{id}")
    public String exportExcelCTSPTheoPin(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllPin(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-pin.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-chip/{id}")
    public String exportExcelCTSPTheoChip(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllChip(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-chip.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }

    @GetMapping("/chi-tiet-san-pham/export-excel-chi-theo-man/{id}")
    public String exportExcelCTSPTheoMan(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lấy dữ liệu từ database
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.findAllMan(id);

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết sản phẩm");

        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(14);

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);

        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("Màu sắc");
        cell = row.createCell(2);
        cell.setCellValue("Chip");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("Rom");
        cell = row.createCell(5);
        cell.setCellValue("Pin");
        cell = row.createCell(6);
        cell.setCellValue("Màn hình");
        cell = row.createCell(7);
        cell.setCellValue("Camera");
        cell = row.createCell(8);
        cell.setCellValue("Hãng");
        cell = row.createCell(9);
        cell.setCellValue("Giá bán");
        cell = row.createCell(10);
        cell.setCellValue("Ngày tạo");
        cell = row.createCell(11);
        cell.setCellValue("Ngày cập nhập");
        cell = row.createCell(12);
        cell.setCellValue("Tình trạng");
        cell = row.createCell(13);
        cell.setCellValue("Năm bảo hành");
        cell = row.createCell(14);
        cell.setCellValue("Số lượng tồn");
        cell = row.createCell(15);
        cell.setCellValue("Mô tả");

        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (ChiTietSanPham chiTietSanPham : listChiTietSanPham) {
            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(chiTietSanPham.getSanPham().getTen());

            cell = row.createCell(1);
            cell.setCellValue(chiTietSanPham.getMauSac().getTen());

            cell = row.createCell(2);
            cell.setCellValue(chiTietSanPham.getChip().getTen());

            cell = row.createCell(3);
            cell.setCellValue(chiTietSanPham.getRam().getDungLuong());

            cell = row.createCell(4);
            cell.setCellValue(chiTietSanPham.getRom().getDungLuong());

            cell = row.createCell(5);
            cell.setCellValue(chiTietSanPham.getPin().getDungLuongPin().getThongSo());

            cell = row.createCell(6);
            cell.setCellValue(chiTietSanPham.getSanPham().getManHinh().getThongSo());
            cell = row.createCell(7);
            cell.setCellValue(chiTietSanPham.getSanPham().getCamera().getThongSo());
            cell = row.createCell(8);
            cell.setCellValue(chiTietSanPham.getSanPham().getHangSanPham().getTen());

            cell = row.createCell(9);
            cell.setCellValue(String.valueOf(chiTietSanPham.getGiaBan()));

            cell = row.createCell(10);
            cell.setCellValue(chiTietSanPham.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(11);
            cell.setCellValue(chiTietSanPham.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(12);
            cell.setCellValue(chiTietSanPham.getTinhTrang() == 0 ? "Còn kinh doanh" : "Ngừng kinh doanh");

            cell = row.createCell(13);
            cell.setCellValue(chiTietSanPham.getNamBaoHanh());


            cell = row.createCell(14);
            cell.setCellValue(chiTietSanPham.getSoLuong());

            cell = row.createCell(15);
            cell.setCellValue(chiTietSanPham.getMoTa());

            rowNum++;

        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-chi-tiet-san-pham-theo-man.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }
}
