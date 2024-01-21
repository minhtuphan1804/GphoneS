package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.repositories.DonDatHangRepository;
import com.example.demo.services.BanHangOnlineService;
import com.example.demo.services.CameraService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.ChipService;
import com.example.demo.services.DataIntermediateService;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.DoiTraChiTietService;
import com.example.demo.services.DungLuongPinService;
import com.example.demo.services.HangSanPhamService;
import com.example.demo.services.HoaDonChiTietService;
import com.example.demo.services.HoaDonService;
import com.example.demo.services.IMEIService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.ManHinhService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.PinService;
import com.example.demo.services.QuyDoiService;
import com.example.demo.services.RamService;
import com.example.demo.services.RomService;
import com.example.demo.services.SanPhamService;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("hoa-don")
@Controller
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private QuyDoiService quyDoiService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IMEIService imeiService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    DataIntermediateService dataIntermediateService;

    @Autowired
    private BanHangOnlineService banHangOnlineService;
    @Autowired
    private DoiTraChiTietService doiTraChiTietService;
    @Autowired
    private BanHangOnLinerepository banHangOnLinerepository;
    @Autowired
    private DonDatHangRepository donDatHangRepository;

    private int dem = 0;
    private UUID idHoaDon = null;
    BigDecimal total = BigDecimal.ZERO;
    private HoaDon hoaDonnn = new HoaDon();
    private int loai = 0;
    private int httt = 0;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> page = hoaDonService.hoaDon();
        List<HoaDon> list = new ArrayList();
        for (HoaDon hd : page
        ) {
            if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                list.add(hd);
            }
        }
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @GetMapping("/hien-thi-xac-nhan")
    public String hienThiXacNhan(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> page = hoaDonService.hoaDon();
        List<HoaDon> list = new ArrayList();
        for (HoaDon hd : page
        ) {
            if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                list.add(hd);
            }
        }
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @GetMapping("/hien-thi-cho-giao")
    public String hienThiChoGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> list = hoaDonService.hoaDonChoGiaoHang();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @GetMapping("/hien-thi-dang-giao")
    public String hienThiDangGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> list = hoaDonService.hoaDonDangGiaoHang();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @GetMapping("/hien-thi-hoan-tat")
    public String hienThiHoanTat(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> list = hoaDonService.hoaDonHoanTat();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @GetMapping("/hien-thi-da-huy")
    public String hhoaDonDaHuy(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        dem = 0;
        List<HoaDon> list = hoaDonService.hoaDonDaHuy();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
        model.addAttribute("listHoaDon", list);
        return "home/layout";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                         @RequestParam("search") String search,
                         @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> page = hoaDonService.search(search, soTienQuyDoi);
        List<HoaDon> list = new ArrayList();
        for (HoaDon hd : page
        ) {
            if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                list.add(hd);
            }
        }
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/search-xac-nhan")
    public String searchXacNhan(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                @RequestParam("search") String search,
                                @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> page = hoaDonService.search(search, soTienQuyDoi);
        List<HoaDon> list = new ArrayList();
        for (HoaDon hd : page
        ) {
            if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                list.add(hd);
            }
        }
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/search-cho-giao-hang")
    public String searchChoGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                    @RequestParam("search") String search,
                                    @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> list = hoaDonService.searchChoGiaoHang(search, soTienQuyDoi);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/search-dang-giao-hang")
    public String searchDangGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                     @RequestParam("search") String search,
                                     @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> list = hoaDonService.searchDangGiaoHang(search, soTienQuyDoi);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/search-hoan-tat")
    public String searchHoanTat(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                @RequestParam("search") String search,
                                @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> list = hoaDonService.searchHoanTat(search, soTienQuyDoi);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }

    @PostMapping("/search-da-huy")
    public String searchDaHuy(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                              @RequestParam("search") String search,
                              @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi) {

        dem = 1;
        model.addAttribute("dem", dem);
        List<HoaDon> list = hoaDonService.searchDaHuy(search, soTienQuyDoi);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
        model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
        return "home/layout";
    }


    @PostMapping("/loc")
    public String loc1(Model model,
                       @RequestParam(value = "nhanVien", required = false) UUID idNV,
                       @RequestParam(value = "khachHang", required = false) UUID idKH,
                       @RequestParam(value = "diaChi", required = false) UUID idDC,
                       @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                       @RequestParam(value = "startDate", required = false) String startDate,
                       @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                       @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                       @RequestParam(value = "endDate", required = false) String endDate,
                       @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                       @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                       @ModelAttribute("nhanVien") NhanVien nhanVien,
                       @ModelAttribute("khachHang") KhachHang khachHang,
                       @ModelAttribute("diaChi") DiaChi diaChi,
                       @ModelAttribute("hoaDon") HoaDon hoaDon,
                       @RequestParam("pageNum") Optional<Integer> pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }

    @PostMapping("/loc-xac-nhan")
    public String locXacNhan(Model model,
                             @RequestParam(value = "nhanVien", required = false) UUID idNV,
                             @RequestParam(value = "khachHang", required = false) UUID idKH,
                             @RequestParam(value = "diaChi", required = false) UUID idDC,
                             @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                             @RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                             @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                             @RequestParam(value = "endDate", required = false) String endDate,
                             @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                             @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                             @ModelAttribute("nhanVien") NhanVien nhanVien,
                             @ModelAttribute("khachHang") KhachHang khachHang,
                             @ModelAttribute("diaChi") DiaChi diaChi,
                             @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            for (HoaDon hd : listHoaDon
            ) {
                if (hd.getNhanVien() != null && hd.getKhachHang() != null) {
                    list.add(hd);
                }
            }
            model.addAttribute("listHoaDon", list);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }

    @PostMapping("/loc-cho-giao-hang")
    public String locChoGiaoHang(Model model,
                                 @RequestParam(value = "nhanVien", required = false) UUID idNV,
                                 @RequestParam(value = "khachHang", required = false) UUID idKH,
                                 @RequestParam(value = "diaChi", required = false) UUID idDC,
                                 @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                                 @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                                 @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                                 @ModelAttribute("nhanVien") NhanVien nhanVien,
                                 @ModelAttribute("khachHang") KhachHang khachHang,
                                 @ModelAttribute("diaChi") DiaChi diaChi,
                                 @ModelAttribute("hoaDon") HoaDon hoaDon,
                                 @RequestParam("pageNum") Optional<Integer> pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locChoGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-cho-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }

    @PostMapping("/loc-dang-giao-hang")
    public String locDangGiaoHang(Model model,
                                  @RequestParam(value = "nhanVien", required = false) UUID idNV,
                                  @RequestParam(value = "khachHang", required = false) UUID idKH,
                                  @RequestParam(value = "diaChi", required = false) UUID idDC,
                                  @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                                  @RequestParam(value = "startDate", required = false) String startDate,
                                  @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                                  @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                                  @RequestParam(value = "endDate", required = false) String endDate,
                                  @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                                  @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                                  @ModelAttribute("nhanVien") NhanVien nhanVien,
                                  @ModelAttribute("khachHang") KhachHang khachHang,
                                  @ModelAttribute("diaChi") DiaChi diaChi,
                                  @ModelAttribute("hoaDon") HoaDon hoaDon,
                                  @RequestParam("pageNum") Optional<Integer> pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locDangGiaoHang(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-dang-giao-hang.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }

    @PostMapping("/loc-hoan-tat")
    public String locHoanTat(Model model,
                             @RequestParam(value = "nhanVien", required = false) UUID idNV,
                             @RequestParam(value = "khachHang", required = false) UUID idKH,
                             @RequestParam(value = "diaChi", required = false) UUID idDC,
                             @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                             @RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                             @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                             @RequestParam(value = "endDate", required = false) String endDate,
                             @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                             @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                             @ModelAttribute("nhanVien") NhanVien nhanVien,
                             @ModelAttribute("khachHang") KhachHang khachHang,
                             @ModelAttribute("diaChi") DiaChi diaChi,
                             @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locHoanTat(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-hoan-tat.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }

    @PostMapping("/loc-da-huy")
    public String locDaHuy(Model model,
                           @RequestParam(value = "nhanVien", required = false) UUID idNV,
                           @RequestParam(value = "khachHang", required = false) UUID idKH,
                           @RequestParam(value = "diaChi", required = false) UUID idDC,
                           @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                           @RequestParam(value = "startDate", required = false) String startDate,
                           @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                           @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                           @RequestParam(value = "endDate", required = false) String endDate,
                           @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                           @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                           @ModelAttribute("nhanVien") NhanVien nhanVien,
                           @ModelAttribute("khachHang") KhachHang khachHang,
                           @ModelAttribute("diaChi") DiaChi diaChi,
                           @ModelAttribute("hoaDon") HoaDon hoaDon,
                           @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<HoaDon> list = new ArrayList<>();
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locDaHuy(idKH, idNV, idDC, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-da-huy.jsp");
            model.addAttribute("thongBaoLoc", "Lọc thông tin thành công");
            return "home/layout";
        }
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 4;
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        List<DoiTraChiTiet> listDoiTraChiTiet = doiTraChiTietService.doiTraChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("dem", dem);
        HoaDon hoaDon = hoaDonService.findById(id);
        loai = hoaDon.getLoai();
        httt = hoaDon.getHinhThucThanhToan();
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("listDoiTraChiTiet", listDoiTraChiTiet);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/xuat-pdf-cho-giao/{id}")
    public ResponseEntity<byte[]> xuatPDF(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @GetMapping("/xuat-pdf-hoan-tat/{id}")
    public ResponseEntity<byte[]> xuatPDFHoanTat(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }


    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @ModelAttribute("khachHang") KhachHang khachHang,
                          @ModelAttribute("nhanVien") NhanVien nhanVien,
                          @ModelAttribute("diaChi") DiaChi diaChi
//                          @ModelAttribute("quyDoi") QuyDoi quyDoi
    ) {

        hoaDon.setTinhTrang(0);
        List<KhachHang> listKhachHang = khachHangService.findAll0();
        model.addAttribute("listKhachHang", listKhachHang);

        List<NhanVien> listNhanVien = nhanVienService.findAll();
        model.addAttribute("listNhanVien", listNhanVien);
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listDiaChi", listDiaChi);

//        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
//        model.addAttribute("listQuyDoi", listQuyDoi);
//        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho.jsp");

        return "home/layout";
    }

    @GetMapping("/add")
    public String addHoaDon(Model model
    ) {
        List<HoaDon> list = hoaDonService.find();
        if (list.size() > 6) {
            return "redirect:/hoa-don/hien-thi";
        } else {
            Integer hd = hoaDonService.findAll().size() + 1;
            String maHD = "";
            if (hd < 9) {
                maHD = "HD0" + hd;
            } else {
                maHD = "HD" + hd;
            }
            NhanVien nhanVien = nhanVienService.findById(SecurityUtil.getId().getId());
            HoaDon hdc = new HoaDon();
            hdc.setMa(maHD);
            hdc.setTinhTrang(0);
            hdc.setLoai(0);
            hdc.setNgayTao(Date.valueOf(LocalDate.now()));
            hdc.setNhanVien(nhanVien);
            hoaDonService.add(hdc);
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "redirect:/hoa-don/hien-thi";
        }
    }

    //update thông tin khi trạng thái là đang chờ, chờ xác nhận...
    @GetMapping("/view-update-da-xac-nhan/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        dem = 4;
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(id).getKhachHang().getId()));
        HoaDon hoaDon = hoaDonService.findById(id);
        loai = hoaDonnn.getLoai();
        httt = hoaDonnn.getHinhThucThanhToan();
        hoaDonnn = hoaDon;
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("tong", hoaDon.getTongTien());
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../hoadon/hoa-don-view-update-da-xac-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update-cho-giao/{id}")
    public String viewUpdateChoGiao(Model model, @PathVariable("id") UUID id) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        dem = 4;
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(id).getKhachHang().getId()));
        HoaDon hoaDon = hoaDonService.findById(id);
        loai = hoaDonnn.getLoai();
        httt = hoaDonnn.getHinhThucThanhToan();
        hoaDonnn = hoaDon;
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("tong", hoaDon.getTongTien());
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
        return "home/layout";
    }

    //
    @PostMapping("/update-da-xac-nhan/{id}")
    public String Update(Model model, @PathVariable("id") UUID id,
                         @ModelAttribute("hoaDon") HoaDon hoaDon) {
        HoaDon hd = hoaDonService.findById(id);
        if (hd.getLoai() == 1) {
            LocalDate ngayShip = hoaDon.getNgayShip().toLocalDate();
            int check = LocalDate.now().compareTo(ngayShip);
            System.out.println(check);
            if (check > 0) {
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId()));
                model.addAttribute("thongBao", "Ngày ship không được nhỏ hơn ngày hiện tại");
                model.addAttribute("contentPage", "../hoadon/hoa-don-view-update-da-xac-nhan.jsp");
                return "home/layout";
            } else if (check < 0) {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDon.setLoai(hoaDonnn.getLoai());
                hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
                hoaDon.setTinhTrangGiaoHang(1);
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setPhiShip(hoaDonnn.getPhiShip());

                if (hoaDonnn.getTinhTrang() == 2) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();

                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImei(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    }
                } else if (hoaDonnn.getTinhTrang() == 3) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImeiChoXuLy(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    }
                } else {
                    hoaDon.setId(id);
                    hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                    hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                    hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                    hoaDonService.update(id, hoaDon);
                }
                hoaDonnn = hoaDon;
                return "redirect:/hoa-don/hien-thi-xac-nhan";
            } else {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setLoai(hoaDonnn.getLoai());
                hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
                hoaDon.setTinhTrangGiaoHang(2);
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDon.setPhiShip(hoaDonnn.getPhiShip());
                if (hoaDonnn.getTinhTrang() == 2) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImei(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    }
                } else if (hoaDonnn.getTinhTrang() == 3) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImeiChoXuLy(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    }
                } else {
                    hoaDon.setId(id);
                    hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                    hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                    hoaDonService.update(id, hoaDon);
                }
                hoaDonnn = hoaDon;
                return "redirect:/hoa-don/hien-thi-xac-nhan";
            }
        } else {
            if (hoaDon.getTinhTrang() == 8) {
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hd.setKhachHang(hoaDonnn.getKhachHang());
                hd.setLoai(loai);
                hd.setHinhThucThanhToan(httt);

                LocalDate ngayCapNhat = LocalDate.now();
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
                if (!list.isEmpty()) {
                    for (HoaDonChiTiet hdct : list
                    ) {
                        ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                        ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                        long millis = System.currentTimeMillis();
                        Date date = new Date(millis);
                        ctsp.setNgayTao(date);
                        if (ctsp.getSoLuong() > 0) {
                            ctsp.setTinhTrang(0);
                        }
                        chiTietSanPhamService.update1(ctsp);
                        imeiService.updatImei1(date, hdct.getId());
                        hdct.setTinhTrang(8);
                        hoaDonChiTietService.update(hdct.getId(), hdct);
                    }
                    hd.setTinhTrang(8);
                    hd.setTinhTrangGiaoHang(8);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                    return "redirect:/hoa-don/hien-thi-xac-nhan";
                } else {
                    hd.setTinhTrang(8);
                    hd.setTinhTrangGiaoHang(8);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                    return "redirect:/hoa-don/hien-thi-xac-nhan";
                }
            } else if (hoaDon.getTinhTrang() == 2) {
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hd.setKhachHang(hoaDonnn.getKhachHang());
                hd.setNgayShip(Date.valueOf(LocalDate.now()));
                hd.setNgayNhan(Date.valueOf(LocalDate.now()));

                LocalDate ngayCapNhat = LocalDate.now();
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
                if (!list.isEmpty()) {
                    for (HoaDonChiTiet hdct : list
                    ) {
                        long millis = System.currentTimeMillis();
                        Date date = new Date(millis);
                        imeiService.updatImei(date, hdct.getId());
                        hdct.setTinhTrang(1);
                        hoaDonChiTietService.update(hdct.getId(), hdct);
                    }
                    hd.setTinhTrang(2);
                    hd.setTinhTrangGiaoHang(3);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                } else {
                    hd.setTinhTrang(2);
                    hd.setTinhTrangGiaoHang(3);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                }
            } else {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setNgayShip(Date.valueOf(LocalDate.now()));
                hoaDon.setNgayNhan(Date.valueOf(LocalDate.now()));
                hoaDon.setPhiShip(BigDecimal.valueOf(30000));
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDonService.update(id, hoaDon);
            }
            hoaDonnn = hoaDon;
            return "redirect:/hoa-don/hien-thi-xac-nhan";
        }

    }

    @PostMapping("/update-cho-giao/{id}")
    public String UpdateChoGiao(Model model, @PathVariable("id") UUID id,
                                @ModelAttribute("hoaDon") HoaDon hoaDon) {
        HoaDon hd = hoaDonService.findById(id);
        if (hd.getLoai() == 1) {
            LocalDate ngayShip = hoaDon.getNgayShip().toLocalDate();
            int check = LocalDate.now().compareTo(ngayShip);
            System.out.println(check);
            if (check > 0) {
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId()));
                model.addAttribute("thongBao", "Ngày ship không được nhỏ hơn ngày hiện tại");
                model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
                return "home/layout";
            } else if (check < 0) {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDon.setLoai(hoaDonnn.getLoai());
                hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
                hoaDon.setTinhTrangGiaoHang(1);
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setPhiShip(hoaDonnn.getPhiShip());

                if (hoaDonnn.getTinhTrang() == 2) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();

                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImei(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    }
                } else if (hoaDonnn.getTinhTrang() == 3) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImeiChoXuLy(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    }
                } else {
                    hoaDon.setId(id);
                    hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                    hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                    hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                    hoaDonService.update(id, hoaDon);
                }
                hoaDonnn = hoaDon;
                return "redirect:/hoa-don/hien-thi-cho-giao";
            } else {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setLoai(hoaDonnn.getLoai());
                hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
                hoaDon.setTinhTrangGiaoHang(2);
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDon.setPhiShip(hoaDonnn.getPhiShip());
                if (hoaDonnn.getTinhTrang() == 2) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImei(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDon.setNgayThanhToan(hoaDonnn.getNgayThanhToan());
                        hoaDonService.update(id, hoaDon);
                    }
                } else if (hoaDonnn.getTinhTrang() == 3) {
                    hoaDon.setTinhTrang(hoaDonnn.getTinhTrang());
                    LocalDate ngayCapNhat = LocalDate.now();
                    List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
                    if (!list.isEmpty()) {
                        for (HoaDonChiTiet hdct : list
                        ) {
                            long millis = System.currentTimeMillis();
                            Date date = new Date(millis);
                            imeiService.updatImeiChoXuLy(date, hdct.getId());
                            hdct.setTinhTrang(1);
                            hoaDonChiTietService.update(hdct.getId(), hdct);
                        }
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    } else {
                        hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                        hoaDonService.update(id, hoaDon);
                    }
                } else {
                    hoaDon.setId(id);
                    hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                    hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                    hoaDonService.update(id, hoaDon);
                }
                hoaDonnn = hoaDon;
                return "redirect:/hoa-don/hien-thi-cho-giao";
            }
        } else {
            if (hoaDon.getTinhTrang() == 8) {
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hd.setKhachHang(hoaDonnn.getKhachHang());
                hd.setLoai(loai);
                hd.setHinhThucThanhToan(httt);

                LocalDate ngayCapNhat = LocalDate.now();
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
                if (!list.isEmpty()) {
                    for (HoaDonChiTiet hdct : list
                    ) {
                        ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                        ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                        long millis = System.currentTimeMillis();
                        Date date = new Date(millis);
                        ctsp.setNgayTao(date);
                        if (ctsp.getSoLuong() > 0) {
                            ctsp.setTinhTrang(0);
                        }
                        chiTietSanPhamService.update1(ctsp);
                        imeiService.updatImei1(date, hdct.getId());
                        hdct.setTinhTrang(8);
                        hoaDonChiTietService.update(hdct.getId(), hdct);
                    }
                    hd.setTinhTrang(8);
                    hd.setTinhTrangGiaoHang(8);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                    return "redirect:/hoa-don/hien-thi-cho-giao";
                } else {
                    hd.setTinhTrang(8);
                    hd.setTinhTrangGiaoHang(8);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                    return "redirect:/hoa-don/hien-thi-cho-giao";
                }
            } else if (hoaDon.getTinhTrang() == 2) {
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hd.setKhachHang(hoaDonnn.getKhachHang());
                hd.setNgayShip(Date.valueOf(LocalDate.now()));
                hd.setNgayNhan(Date.valueOf(LocalDate.now()));

                LocalDate ngayCapNhat = LocalDate.now();
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
                if (!list.isEmpty()) {
                    for (HoaDonChiTiet hdct : list
                    ) {
                        long millis = System.currentTimeMillis();
                        Date date = new Date(millis);
                        imeiService.updatImei(date, hdct.getId());
                        hdct.setTinhTrang(1);
                        hoaDonChiTietService.update(hdct.getId(), hdct);
                    }
                    hd.setTinhTrang(2);
                    hd.setTinhTrangGiaoHang(3);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                } else {
                    hd.setTinhTrang(2);
                    hd.setTinhTrangGiaoHang(3);
                    hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                    hoaDonService.update(id, hd);
                }
            } else {
                hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDon.setKhachHang(hoaDonnn.getKhachHang());
                hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                hoaDon.setNgayTao(hoaDonnn.getNgayTao());
                hoaDon.setNgayShip(Date.valueOf(LocalDate.now()));
                hoaDon.setNgayNhan(Date.valueOf(LocalDate.now()));
                hoaDon.setPhiShip(BigDecimal.valueOf(30000));
                hoaDon.setMaGiaoDich(hoaDonnn.getMaGiaoDich());
                hoaDonService.update(id, hoaDon);
            }
            hoaDonnn = hoaDon;
            return "redirect:/hoa-don/hien-thi-cho-giao";
        }

    }


    @ResponseBody
    @GetMapping("/search-hdct")
    public List<HoaDonChiTiet> searchHDCT(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 5;
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @ResponseBody
    @GetMapping("/search-hdct-update")
    public List<HoaDonChiTiet> searchHDCTUpdate(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 5;
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @GetMapping("/delete-hoa-don-chi-tiet/{id}")
    public String deleteHDCT(Model model, @PathVariable("id") UUID id,
                             @ModelAttribute("hoaDon") HoaDon hoaDon) {
//Thắng thêm
        UUID idctsp = hoaDonChiTietService.findById(id).getImei().getChiTietSanPham().getId();
        HoaDon HoaDonlienquan = hoaDonChiTietService.findById(id).getHoaDon();
        /*thắng hết*/
        HoaDonChiTiet hd = hoaDonChiTietService.findById(id);
        ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(hd.getImei().getId());
        ct.setSoLuong(ct.getSoLuong() + 1);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ct.setNgayCapNhat(date);
        if (ct.getSoLuong() > 0) {
            ct.setTinhTrang(0);
        }
        chiTietSanPhamService.update1(ct);
        imeiService.updatImei1(date, id);
        hoaDonChiTietService.delete(id);
        //Thắng thêm


        for (DonDatHang ddh : banHangOnLinerepository.dsDDHtheoIDHD(HoaDonlienquan.getId())) {

            if (banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(HoaDonlienquan.getId(), ddh.getChiTietSanPham().getId()).size() > 0) {
                ddh.setSoLuong(banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(HoaDonlienquan.getId(), ddh.getChiTietSanPham().getId()).size());
                donDatHangRepository.save(ddh);
            } else {
                donDatHangRepository.deleteById(ddh.getId());

            }
        }

//Thắng kết thúc
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
        if (list.isEmpty()) {
            hoaDonnn.setTongTien(BigDecimal.ZERO);
            hoaDonnn.setTinhTrang(0);
            hoaDonnn.setMa(hoaDonnn.getMa());
            hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        } else {
            // Tính tổng đơn giá trong list hóa đơn chi tiết còn lại
            BigDecimal subTotal = list.stream()
                    .map(hdd -> hdd.getDonGia())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Set tổng tiền bằng tổng đơn giá trong list hóa đơn chi tiết còn lại
            total = subTotal;
            hoaDonnn.setTongTien(subTotal);
            hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        }
        hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
        model.addAttribute("tong", String.valueOf(total));
        model.addAttribute("listHoaDonChiTiet", list);
        model.addAttribute("hoaDon", hoaDonnn);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        return "redirect:/hoa-don/view-update/" + idHoaDon;
    }

    @GetMapping("/them-imei/{id}")
    public String addIMEI(Model model, @PathVariable("id") UUID id,
                          @ModelAttribute("hoaDon") HoaDon hoaDon) {
        BigDecimal total = BigDecimal.ZERO;
        IMEI imei = imeiService.findById(id);
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setImei(imei);
        hdct.setHoaDon(hoaDonnn);
        hdct.setTinhTrang(0);
        if (imei.getChiTietSanPham().getKhuyenMai() == null) {
            hdct.setDonGia(imei.getChiTietSanPham().getGiaBan());
        } else {
            System.out.println(108);
            BigDecimal giam = BigDecimal.valueOf(imei.getChiTietSanPham().getKhuyenMai().getSoTienGiam()).divide(BigDecimal.valueOf(100));

// Sử dụng phương thức multiply() để nhân giá tiền với tỷ lệ giảm giá
            hdct.setDonGia(imei.getChiTietSanPham().getGiaBan().subtract(imei.getChiTietSanPham().getGiaBan().multiply(giam)));
            System.out.println(giam);
        }
        hdct.setSoLuong(1);
        hoaDonChiTietService.add(hdct);
        //Thắng thêm

        int kiemtracotrongdonchua = 0;
        for (DonDatHang ddh : banHangOnLinerepository.dsDDHtheoIDHD(hoaDonnn.getId())) {
            if (ddh.getChiTietSanPham().getId() == imeiService.findById(id).getChiTietSanPham().getId()) {
                ddh.setSoLuong(banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(hoaDonnn.getId(), imeiService.findById(id).getChiTietSanPham().getId()).size());
                kiemtracotrongdonchua = 1;
                donDatHangRepository.save(ddh);
            }
        }
        if (kiemtracotrongdonchua == 0) {
            DonDatHang ddhthem = new DonDatHang();
            ddhthem.setSoLuong(1);
            ddhthem.setHoaDon(hoaDonnn);
            ddhthem.setDonGia(imeiService.findById(id).getChiTietSanPham().getGiaBan());
            int dongiakhigiamddh = imeiService.findById(id).getChiTietSanPham().getGiaBan().intValue() / 100 * (100 - banHangOnlineService.tonggiamgia(String.valueOf(imeiService.findById(id).getChiTietSanPham().getId())));
            ddhthem.setDonGiaKhiGiam(BigDecimal.valueOf(Long.valueOf(String.valueOf(dongiakhigiamddh))));
            ddhthem.setTinhTrang(1);
            ddhthem.setChiTietSanPham(imeiService.findById(id).getChiTietSanPham());
            donDatHangRepository.save(ddhthem);
        }
//Thắng kết thúc
        ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(id);
        ct.setSoLuong(ct.getSoLuong() - 1);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ct.setNgayCapNhat(date);
        imeiService.updatImeiChoXuLy(date, id);
        if (ct.getSoLuong() == 0) {
            ct.setTinhTrang(1);
            chiTietSanPhamService.update1(ct);
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
            for (HoaDonChiTiet hd : list
            ) {
                total = total.add(hd.getDonGia());
                if (hoaDonnn.getLoai() == 1) {
                    if (hoaDonnn.getPhiShip() == null) {
                        hoaDonnn.setTongTien(total);
                    } else {
                        hoaDonnn.setTongTien(total.add(hoaDonnn.getPhiShip()));
                    }
                } else {
                    hoaDonnn.setTongTien(total);
                }
                hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            }
            hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
            System.out.println(total);
            model.addAttribute("tong", String.valueOf(total));
            model.addAttribute("listHoaDonChiTiet", list);
            model.addAttribute("hoaDon", hoaDonnn);
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
            model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
            model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
            return "home/layout";
        } else {
            chiTietSanPhamService.update1(ct);
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
            for (HoaDonChiTiet hd : list
            ) {
                total = total.add(hd.getDonGia());
                if (hoaDonnn.getLoai() == 1) {
                    if (hoaDonnn.getPhiShip() == null) {
                        hoaDonnn.setTongTien(total);
                    } else {
                        hoaDonnn.setTongTien(total.add(hoaDonnn.getPhiShip()));
                    }
                } else {
                    hoaDonnn.setTongTien(total);
                }
                hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            }
            hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
            System.out.println(total);
            model.addAttribute("tong", String.valueOf(total));
            model.addAttribute("listHoaDonChiTiet", list);
            model.addAttribute("hoaDon", hoaDonnn);
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
            model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
            model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/xac-nhan-giao-hang-cho-giao/{id}")
    public String xacNhanGiaoHang(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrangGiaoHang(2);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        if (hd.getNgayShip() == null) {
            hd.setNgayShip(Date.valueOf(LocalDate.now()));
        }
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-cho-giao";
    }

    @GetMapping("/xac-nhan-giao-hang-da-xac-nhan/{id}")
    public String xacNhanGiaoHangDẫcNhan(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrangGiaoHang(2);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        if (hd.getNgayShip() == null) {
            hd.setNgayShip(Date.valueOf(LocalDate.now()));
        }
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-xac-nhan";
    }

    @GetMapping("/xac-nhan-giao-hang-hoan-tat/{id}")
    public String giaoHangHoanTat(Model model, @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonService.findById(id);
        KhachHang kh = khachHangService.findById(hd.getKhachHang().getId());
        BigDecimal d = hd.getTongTien().divide(BigDecimal.valueOf(100000));
        Integer themDiem = Integer.valueOf(d.intValue());
        kh.setDiem(kh.getDiem() + themDiem);
        khachHangService.update(kh.getId(), kh);
        hd.setTinhTrangGiaoHang(3);
        hd.setTinhTrang(2);
        if (hd.getHinhThucThanhToan() == 0) {
            hd.setNgayThanhToan(Date.valueOf(LocalDate.now()));
        }
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hd.setNgayNhan(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }

        return "redirect:/hoa-don/hien-thi-dang-giao";
    }

    @GetMapping("/xac-nhan/{id}")
    public String xacNhan(Model model, @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                if (hd.getTinhTrang() == 3) {
                    System.out.println(hdct.getId());
                    imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getId());
                }

            }
        }
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/huy-cho-xac-nhan/{id}")
    public String huyHoaDon(Model model, @PathVariable("id") UUID id
            , @ModelAttribute("hoaDon") HoaDon hoaDon
    ) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        LocalDate ngayCapNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                ctsp.setNgayTao(date);
                if (ctsp.getSoLuong() > 0) {
                    ctsp.setTinhTrang(0);
                }
                chiTietSanPhamService.update1(ctsp);
                imeiService.updatImei1(date, hdct.getId());
                hdct.setTinhTrang(8);
                hoaDonChiTietService.update(hdct.getId(), hdct);
            }
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }

banHangOnLinerepository.updateTTdonDatHangkhiDASULytheoIDHD(id);
        model.addAttribute("hoaDon", hd);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        System.in.read();
        return "redirect:/hoa-don/hien-thi"; // Chuyển hướng về trang danh sách hóa đơn sau khi hủy
    }

    @GetMapping("/xac-nhan-huy-cho-xac-nhan/{id}")
    public String huy(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(8);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/xac-nhan-huy-hoan-tien-cho-xac-nhan/{id}")
    public String huyVaHoanTien(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(9);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/huy-da-xac-nhan/{id}")
    public String huyHoaDonDaXacNhan(Model model, @PathVariable("id") UUID id
            , @ModelAttribute("hoaDon") HoaDon hoaDon
    ) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        LocalDate ngayCapNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                ctsp.setNgayTao(date);
                if (ctsp.getSoLuong() > 0) {
                    ctsp.setTinhTrang(0);
                }
                chiTietSanPhamService.update1(ctsp);
                imeiService.updatImei1(date, hdct.getId());
                hdct.setTinhTrang(8);
                hoaDonChiTietService.update(hdct.getId(), hdct);
            }
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }
        model.addAttribute("hoaDon", hd);
        System.in.read();
        return "redirect:/hoa-don/hien-thi-xac-nhan"; // Chuyển hướng về trang danh sách hóa đơn sau khi hủy
    }

    @GetMapping("/xac-nhan-huy-da-xac-nhan/{id}")
    public String huyDaXacNhan(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(8);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-xac-nhan";
    }

    @GetMapping("/xac-nhan-huy-hoan-tien-da-xac-nhan/{id}")
    public String huyVaHoanTienDaXacNhan(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(9);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-xac-nhan";
    }

    @GetMapping("/huy-cho-giao/{id}")
    public String huyHoaDonDangGiao(Model model, @PathVariable("id") UUID id
            , @ModelAttribute("hoaDon") HoaDon hoaDon
    ) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        LocalDate ngayCapNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                ctsp.setNgayTao(date);
                if (ctsp.getSoLuong() > 0) {
                    ctsp.setTinhTrang(0);
                }
                chiTietSanPhamService.update1(ctsp);
                imeiService.updatImei1(date, hdct.getId());
                hdct.setTinhTrang(8);
                hoaDonChiTietService.update(hdct.getId(), hdct);
            }
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }
        model.addAttribute("hoaDon", hd);
        model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
        System.in.read();
        return "redirect:/hoa-don/hien-thi-cho-giao"; // Chuyển hướng về trang danh sách hóa đơn sau khi hủy
    }

    @GetMapping("/xac-nhan-huy-dang-giao/{id}")
    public String huyDangGiao(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(8);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-dang-giao";
    }

    @GetMapping("/xac-nhan-huy-hoan-tien-dang-giao/{id}")
    public String huyVaHoanTienDangGiao(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(9);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-dang-giao";
    }

    @GetMapping("/huy-dang-giao/{id}")
    public String huyHoaDonChoGiao(Model model, @PathVariable("id") UUID id
            , @ModelAttribute("hoaDon") HoaDon hoaDon
    ) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        LocalDate ngayCapNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                ctsp.setNgayTao(date);
                if (ctsp.getSoLuong() > 0) {
                    ctsp.setTinhTrang(0);
                }
                chiTietSanPhamService.update1(ctsp);
                imeiService.updatImei1(date, hdct.getId());
                hdct.setTinhTrang(8);
                hoaDonChiTietService.update(hdct.getId(), hdct);
            }
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }
        model.addAttribute("hoaDon", hd);
        model.addAttribute("contentPage", "../hoadon/hoa-don-xac-nhan.jsp");
        System.in.read();
        return "redirect:/hoa-don/hien-thi-dang-giao"; // Chuyển hướng về trang danh sách hóa đơn sau khi hủy
    }

    @GetMapping("/xac-nhan-huy-cho-giao/{id}")
    public String huyChoGiao(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(8);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-cho-giao";
    }

    @GetMapping("/xac-nhan-huy-hoan-tien-cho-giao/{id}")
    public String huyVaHoanTienChoGiao(Model model, @PathVariable("id") UUID id) throws IOException {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(9);
        hd.setTinhTrangGiaoHang(8);

        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getId());
            }
        }
        System.in.read();
        return "redirect:/hoa-don/hien-thi-cho-giao";
    }

// alo nút hủy hóa đơn online đâu
    //    ///thắng làm
    @GetMapping("/hien-thi/{hientb}/{idhdneucan}")
    public String hienThi1(Model model,
                           @ModelAttribute("hoaDon") HoaDon hoaDon,
                           @PathVariable("hientb") String hientb,
                           @PathVariable("idhdneucan") String idhdneucan
    ) {
        dem = 0;
        List<HoaDon> page = hoaDonService.hoaDon();
        List<HoaDon> list = new ArrayList();
        for (HoaDon hd : page
        ) {
            if (hd.getNhanVien() == null || hd.getKhachHang() == null) {
                list.add(hd);
            }
        }
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        model.addAttribute("listHoaDon", list);

        if (hientb.equals("tbxacnhanHDonline")) {
            model.addAttribute("batthongbaobenhoadon", "Xác nhận hóa đơn online thành công " + hoaDonService.findById(UUID.fromString(idhdneucan)).getMa());
        }
        return "home/layout";
    }

    //    hết thắng làm
    @GetMapping("/them-gio-hang/{soImei}")
    public String ScanQrCode(Model model, @PathVariable("soImei") String id, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        BigDecimal total = BigDecimal.ZERO;

        IMEI imei = imeiService.searchSoImei(id);
        if (imei == null) {
            List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(idHoaDon);
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
            HoaDon hoaDon = hoaDonService.findById(idHoaDon);
            loai = hoaDonnn.getLoai();
            httt = hoaDonnn.getHinhThucThanhToan();
            hoaDonnn = hoaDon;
            model.addAttribute("thongBaoHoaDon", "Sản phẩm vừa quét đã bán hoặc đang chờ xử lý");
            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("tong", hoaDon.getTongTien());
            model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
            return "home/layout";
        } else {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setImei(imei);
            hdct.setHoaDon(hoaDonnn);
            hdct.setTinhTrang(0);
            if (imei.getChiTietSanPham().getKhuyenMai() == null) {
                hdct.setDonGia(imei.getChiTietSanPham().getGiaBan());
            } else {
                System.out.println(108);
                BigDecimal giam = BigDecimal.valueOf(imei.getChiTietSanPham().getKhuyenMai().getSoTienGiam()).divide(BigDecimal.valueOf(100));
                int giaMoi = (int) Math.floor(Double.valueOf(String.valueOf(imei.getChiTietSanPham().getGiaBan().subtract(imei.getChiTietSanPham().getGiaBan().multiply(giam)))));

                hdct.setDonGia(BigDecimal.valueOf(giaMoi));
                System.out.println(giam);
            }
            hdct.setSoLuong(1);
            hoaDonChiTietService.add(hdct);
            ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(imei.getId());
            ct.setSoLuong(ct.getSoLuong() - 1);
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            ct.setNgayTao(date);
            imeiService.updatImeiChoXuLy(date, imei.getId());
            if (ct.getSoLuong() == 0) {
                ct.setTinhTrang(1);
                chiTietSanPhamService.update1(ct);
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
                for (HoaDonChiTiet hd : list
                ) {
                    total = total.add(hd.getDonGia());
                    hoaDonnn.setTongTien(total);
                    hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                }
                hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
                System.out.println(total);
                model.addAttribute("tong", String.valueOf(total));
                model.addAttribute("listHoaDonChiTiet", list);
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
                return "home/layout";
            } else {
                chiTietSanPhamService.update1(ct);
                List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
                for (HoaDonChiTiet hd : list
                ) {
                    total = total.add(hd.getDonGia());
                    hoaDonnn.setTongTien(total);
                    hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                }
                hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
                System.out.println(total);
                model.addAttribute("tong", String.valueOf(total));
                model.addAttribute("listHoaDonChiTiet", list);
                model.addAttribute("hoaDon", hoaDonnn);
                model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
                return "home/layout";
            }
        }
    }

}
