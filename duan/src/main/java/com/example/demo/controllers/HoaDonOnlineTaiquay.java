package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.repositories.DonDatHangRepository;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.*;
import com.example.demo.util.SecurityUtil;
import com.example.demo.viewmodels.DSfullSanPhamDatHangOnline;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
@Controller
public class HoaDonOnlineTaiquay {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private IMEIService imeiService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private RomService romService;
    @Autowired
    private PinService pinService;
    @Autowired
    private HangSanPhamService hangSanPhamService;
    @Autowired
    private HangKhachHangService hangKhachHangService;
    @Autowired
    private DungLuongPinService dungLuongPinService;
    @Autowired
    private ManHinhService manHinhService;
    @Autowired
    private CameraService cameraService;
    @Autowired
    private BanHangOnlineService banHangOnlineService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private DataIntermediateService dataService;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DoiTraChiTietService doiTraChiTietService;
    @Autowired
    private BanHangOnLinerepository banHangOnLinerepository;
    @Autowired
    private DonDatHangRepository donDatHangRepository;

    @Autowired
    DoiTraService doiTraService;

    @GetMapping("/hoa-don-online/hien-thi")
    public String hienThi(
            Model model

    ) {


        model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());

        model.addAttribute("contentPage", "../hoa-don-online-tai-quay/hoa-don.jsp");
        model.addAttribute("banhangonline", banHangOnlineService);

        return "home/layout";
    }

    @GetMapping("/hoa-don-online/xac-nhan/detail/{idhd}")
    public String giaohangHDonline(
            Model model,
            @PathVariable("idhd") UUID idhd

    ) {
//        HoaDon hdxn=hoaDonService.findById(idhd);

//        hdxn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));

        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());
        model.addAttribute("listghcthd", banHangOnLinerepository.dsDDHtheoIDHD(idhd));
        model.addAttribute("listhdct", banHangOnLinerepository.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("contentPage", "../hoa-don-online-tai-quay/xem-hoa-don.jsp");


        return "home/layout";
    }

    @PostMapping("/hoa-don-online/imei-vao-hdct/{idhd}")
    public String nhapthemvaohdct(
            Model model,
            @PathVariable("idhd") UUID idhd,
            @RequestParam("imeithem") String imeithem

    ) {
        IMEI imeichon = banHangOnLinerepository.timIMEItheosoImei(imeithem.trim());
        Integer ktimei = 0;  //kiểm tra imei nhập có thuộc ctsp nào trong đơn đặt hàng ko/ 0:ko có,1:co
        DonDatHang ddhchon = new DonDatHang();
        if (imeichon == null) {
            model.addAttribute("tbkhithemimei", "Imei này không tồn tại trong danh sách");
        } else {
            if (imeichon.getTinhTrang() == 0) {

                for (DonDatHang ddh : banHangOnLinerepository.dsDDHtheoIDHD(idhd)) {

                    if (ddh.getChiTietSanPham().getId() == imeichon.getChiTietSanPham().getId()) {
                        ktimei = 1;
                        ddhchon = ddh;
                    }
                }

                //kiem tra số 1 ctsp theo hd và ctsp trong bảng hdct >=< so sl ctsp trong đơn đặt cùng hd
                if (ktimei == 1) {

                    if (banHangOnLinerepository.sl1ctspTRONGhdctTHEOidhdVSisctsp(idhd, imeichon.getChiTietSanPham().getId()) < ddhchon.getSoLuong()) {
                        //them hsct
                        HoaDonChiTiet hdctcanthem = new HoaDonChiTiet();
                        hdctcanthem.setHoaDon(hoaDonService.findById(idhd));
                        hdctcanthem.setImei(banHangOnLinerepository.timIMEItheosoImei(imeithem));
                        hdctcanthem.setSoLuong(1);
                        hdctcanthem.setTinhTrang(0);
                        hdctcanthem.setDonGia(ddhchon.getDonGiaKhiGiam());
                        hoaDonChiTietService.add(hdctcanthem);
                        // cập nhật imei tt=3
                        imeichon.setTinhTrang(3);
                        imeiService.add(imeichon);
                        model.addAttribute("tbkhithemimei", "Thêm Imei vào HDCT thành công");
                    } else {
                        model.addAttribute("tbkhithemimei", "Sản phẩm này trong hdct đã đủ , ko cần thêm");
                    }
                } else {
                    model.addAttribute("tbkhithemimei", "Imei này không thuộc ctsp nào đặt hàng");
                }
            } else {
                model.addAttribute("tbkhithemimei", "Imei này không trong trạng thái đang bán");
            }
        }


        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());
        model.addAttribute("listghcthd", banHangOnLinerepository.dsDDHtheoIDHD(idhd));
        model.addAttribute("listhdct", banHangOnLinerepository.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("contentPage", "../hoa-don-online-tai-quay/xem-hoa-don.jsp");


        return "home/layout";
    }


    @GetMapping("/hoa-don-online/xoa-ctsp/{idhdct}")
    public String xoa1chitietsanpham(
            Model model,
            @PathVariable("idhdct") UUID idhdct

    ) {
        HoaDon hd = hoaDonChiTietService.findById(idhdct).getHoaDon();
        IMEI im = hoaDonChiTietService.findById(idhdct).getImei();
        im.setTinhTrang(0);
        hoaDonChiTietService.delete(idhdct);
        imeiService.add(im);

        model.addAttribute("tbkhithemimei", " Bỏ Imei thành công");
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());
        model.addAttribute("listghcthd", banHangOnLinerepository.dsDDHtheoIDHD(hd.getId()));
        model.addAttribute("listhdct", banHangOnLinerepository.timhoadonchitiettheoidhd(hd.getId()));
        model.addAttribute("contentPage", "../hoa-don-online-tai-quay/xem-hoa-don.jsp");


        return "home/layout";
    }

    @GetMapping("/hoa-don-online/excel-don-dat-hang/{idhd}")
    public String exportExcelHD(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("idhd") String idhd) throws ServletException, IOException {

        // thời gian theo 3 tháng gần nhẩt


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet;

        if (idhd.equals("full")) {
            sheet = workbook.createSheet("Danh sách full sản phẩm cần lấy ");

        } else {
            sheet = workbook.createSheet("Danh sách sản phẩm cần lấy của của " + hoaDonService.findById(UUID.fromString(idhd)).getMa());  // tiên file
        }
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderTop(BorderStyle.THIN);

        // Tăng độ rộng excel
        sheet.setDefaultColumnWidth(20);

        Cell cell;


        if (idhd.equals("full")) {

            // Gộp ô ở dòng thứ 0:
            Row row0 = sheet.getRow(0);
            if (row0 == null) {
                row0 = sheet.createRow(0);
            }
            if (row0.getLastCellNum() == -1) {
                row0.createCell(0);
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            cell = row0.createCell(0);
            cell.setCellValue("Danh sách full sản phẩm cần lấy");
            cell.setCellStyle(borderStyle);


        } else {

            // Gộp ô ở dòng thứ 0:
            Row row0 = sheet.getRow(0);
            if (row0 == null) {
                row0 = sheet.createRow(0);
            }
            if (row0.getLastCellNum() == -1) {
                row0.createCell(0);
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            cell = row0.createCell(0);
            cell.setCellValue("Danh sách sản phẩm cần lấy của của " + hoaDonService.findById(UUID.fromString(idhd)).getMa());
            cell.setCellStyle(borderStyle);


        }


        // Tạo tiêu đề bảng
        Row row = sheet.createRow(1);


        cell = row.createCell(0);
        cell.setCellValue("Tên sản phẩm");
        cell = row.createCell(1);
        cell.setCellValue("hãng sản phẩm");
        cell = row.createCell(2);
        cell.setCellValue("màu");
        cell = row.createCell(3);
        cell.setCellValue("Ram");
        cell = row.createCell(4);
        cell.setCellValue("số lượng");


        // Tạo kiểu font đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Tạo kiểu cell
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);

        // Áp dụng kiểu cell cho dòng đầu
        Row headerRow = sheet.getRow(1);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 2;


        if (idhd.equals("full")) {
            List<DSfullSanPhamDatHangOnline> listDonDatHang = banHangOnLinerepository.dsfullDDHcoTT0ladoixacnhan();
            for (DSfullSanPhamDatHangOnline hoadon : listDonDatHang) {
                ChiTietSanPham ctsp = chiTietSanPhamService.findById(UUID.fromString(hoadon.getidctsp()));
                row = sheet.createRow(rowNum);
                cell = row.createCell(0);
                cell.setCellValue(ctsp.getSanPham().getTen());

                cell = row.createCell(1);
                cell.setCellValue(ctsp.getSanPham().getHangSanPham().getTen());

                cell = row.createCell(2);
                cell.setCellValue(ctsp.getMauSac().getTen());

                cell = row.createCell(3);
                cell.setCellValue(ctsp.getRam().getDungLuong());


                cell = row.createCell(4);
                cell.setCellValue(hoadon.getslctsp());
                rowNum++;

            }

        } else {

            List<DonDatHang> ListDHtheo1HD = banHangOnLinerepository.dsDDHtheoIDHD(UUID.fromString(idhd));
            for (DonDatHang hoadon : ListDHtheo1HD) {
                ChiTietSanPham ctsp = hoadon.getChiTietSanPham();

                row = sheet.createRow(rowNum);
                cell = row.createCell(0);
                cell.setCellValue(ctsp.getSanPham().getTen());

                cell = row.createCell(1);
                cell.setCellValue(ctsp.getSanPham().getHangSanPham().getTen());

                cell = row.createCell(2);
                cell.setCellValue(ctsp.getMauSac().getTen());

                cell = row.createCell(3);
                cell.setCellValue(ctsp.getRam().getDungLuong());


                cell = row.createCell(4);
                cell.setCellValue(hoadon.getSoLuong());
                rowNum++;

            }
        }


        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        if (idhd.equals("full")) {

            response.setHeader("Content-Disposition", "attachment; filename=danh-sach-full-san-pham-can-lay.xlsx");


        } else {
            response.setHeader("Content-Disposition", "attachment; filename=danh-sach-san-pham-can-lay-hoa-don-" + hoaDonService.findById(UUID.fromString(idhd)).getMa() + ".xlsx");

        }
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }


    @GetMapping("/hoa-don-online/xac-nhan/thanh-cong/{idhd}")
    public String xacnhanđonhangthanhcong(
            Model model,
            @PathVariable("idhd") UUID idhd

    ) {
        Integer kiemtrasl = 0;

        for (DonDatHang ddh : banHangOnLinerepository.dsDDHtheoIDHD(idhd)
        ) {
            if (ddh.getSoLuong() != banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(idhd, ddh.getChiTietSanPham().getId()).size()) {
                kiemtrasl = 1;
            }
        }
        if (kiemtrasl == 1) {

            model.addAttribute("tbkhithemimei", "Sản phẩm chưa đủ số lượng với đơn đặt hàng");

            model.addAttribute("banhangonline", banHangOnlineService);
            model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());
            model.addAttribute("listghcthd", banHangOnLinerepository.dsDDHtheoIDHD(idhd));
            model.addAttribute("listhdct", banHangOnLinerepository.timhoadonchitiettheoidhd(idhd));
            model.addAttribute("contentPage", "../hoa-don-online-tai-quay/xem-hoa-don.jsp");


            return "home/layout";
        } else {
            HoaDon hd = hoaDonService.findById(idhd);
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hd.setNgayCapNhat(java.sql.Date.valueOf(LocalDate.now()));
            hd.setPhiShip(BigDecimal.valueOf(30000));
            hoaDonService.add(hd);

            List<HoaDonChiTiet> listHoaDonChiTiet = banHangOnLinerepository.timhoadonchitiettheoidhd(idhd);

            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                if (hd.getTinhTrang() == 3) {
                    System.out.println(hdct.getId());
                    imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
                }

            }
            banHangOnLinerepository.updateTTdonDatHangkhiDASULytheoIDHD(idhd);

            return "redirect:/hoa-don/hien-thi/tbxacnhanHDonline/" + idhd;

        }


    }


    @GetMapping("/hoa-don-online/them-hoa-don-chi-tiet/{idhd}/{soimei}")
    public String themhdct(
            Model model,
            @PathVariable("idhd") UUID idhd,
            @PathVariable("soimei") String soimei

    ) {
        IMEI imeichon = banHangOnLinerepository.timIMEItheosoImei(soimei.trim());
        Integer ktimei = 0;  //kiểm tra imei nhập có thuộc ctsp nào trong đơn đặt hàng ko/ 0:ko có,1:co
        DonDatHang ddhchon = new DonDatHang();
        if (imeichon == null) {
            model.addAttribute("tbkhithemimei", "Imei này không tồn tại trong danh sách");
        } else {
            if (imeichon.getTinhTrang() == 0) {

                for (DonDatHang ddh : banHangOnLinerepository.dsDDHtheoIDHD(idhd)) {

                    if (ddh.getChiTietSanPham().getId() == imeichon.getChiTietSanPham().getId()) {
                        ktimei = 1;
                        ddhchon = ddh;
                    }
                }

                //kiem tra số 1 ctsp theo hd và ctsp trong bảng hdct >=< so sl ctsp trong đơn đặt cùng hd
                if (ktimei == 1) {

                    if (banHangOnLinerepository.sl1ctspTRONGhdctTHEOidhdVSisctsp(idhd, imeichon.getChiTietSanPham().getId()) < ddhchon.getSoLuong()) {
                        //them hsct
                        HoaDonChiTiet hdctcanthem = new HoaDonChiTiet();
                        hdctcanthem.setHoaDon(hoaDonService.findById(idhd));
                        hdctcanthem.setImei(banHangOnLinerepository.timIMEItheosoImei(soimei.trim()));
                        hdctcanthem.setSoLuong(1);
                        hdctcanthem.setTinhTrang(0);
                        hdctcanthem.setDonGia(ddhchon.getDonGiaKhiGiam());
                        hoaDonChiTietService.add(hdctcanthem);
                        // cập nhật imei tt=3
                        imeichon.setTinhTrang(3);
                        imeiService.add(imeichon);
                        model.addAttribute("tbkhithemimei", "Thêm Imei vào HDCT thành công");
                    } else {
                        model.addAttribute("tbkhithemimei", "Sản phẩm này trong hdct đã đủ , ko cần thêm");
                    }
                } else {
                    model.addAttribute("tbkhithemimei", "Imei này không thuộc ctsp nào đặt hàng");
                }
            } else {
                model.addAttribute("tbkhithemimei", "Imei này không trong trạng thái đang bán");
            }
        }


        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("dulieu", banHangOnLinerepository.dsHDonlineloai1());
        model.addAttribute("listghcthd", banHangOnLinerepository.dsDDHtheoIDHD(idhd));
        model.addAttribute("listhdct", banHangOnLinerepository.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("contentPage", "../hoa-don-online-tai-quay/xem-hoa-don.jsp");


        return "home/layout";
    }

}
