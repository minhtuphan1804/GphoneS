package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DiaChi;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.IMEI;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import com.example.demo.models.QuyDoi;
import com.example.demo.services.BanHangOnlineService;
import com.example.demo.services.CameraService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.ChipService;
import com.example.demo.services.DataIntermediateService;
import com.example.demo.services.DiaChiService;
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

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/don-hang")
public class DonHangController {
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
    private DungLuongPinService dungLuongPinService;
    @Autowired
    private ManHinhService manHinhService;
    @Autowired
    private CameraService cameraService;

    private UUID idHoaDon = null;
    BigDecimal total = BigDecimal.ZERO;
    private HoaDon hoaDonnn = new HoaDon();

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("donHang") HoaDon donHang) {

        List<HoaDon> page = hoaDonService.donHang();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
        model.addAttribute("listHoaDon", page);
        return "home/layout";
    }

    @PostMapping("/loc")
    public String locDonHang(Model model,
                             @RequestParam(value = "nhanVien", required = false) UUID idNV,
                             @RequestParam(value = "khachHang", required = false) UUID idKH,
                             @RequestParam(value = "diaChi", required = false) UUID idDC,
                             @RequestParam(value = "trangThai", required = false) Integer trangThai,
                             @RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                             @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                             @RequestParam(value = "endDate", required = false) String endDate,
                             @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                             @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                             @ModelAttribute("nhanVien") NhanVien nhanVien,
                             @ModelAttribute("khachHang") KhachHang khachHang,
                             @ModelAttribute("diaChi") DiaChi diaChi,
                             @ModelAttribute("hoaDon") HoaDon hoaDon) {

        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("donHang") HoaDon hoaDon,
                         @RequestParam("search") String search) {
        List<HoaDon> list = hoaDonService.searchDonHang(search);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        HoaDon hoaDon = hoaDonService.findById(id);
        model.addAttribute("donHang", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../don-hang/don-hang-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/xuat-pdf/{id}")
    public ResponseEntity<byte[]> xuatPDF(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonOnline(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        List<NhanVien> listNhanVien = nhanVienService.nhanVienThanhToan(id);
        List<KhachHang> listKhachHang = khachHangService.khachHangThanhToan(id);
        List<DiaChi> listDiaChi = diaChiService.diaChiThanhToan(hoaDonService.findById(id).getKhachHang().getId());
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        HoaDon hoaDon = hoaDonService.findById(id);
        hoaDonnn = hoaDon;
        model.addAttribute("donHang", hoaDon);
        model.addAttribute("tong", hoaDon.getTongTien());
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());

        model.addAttribute("contentPage", "../don-hang/don-hang-view-update.jsp");
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/search-hdct")
    public List<HoaDonChiTiet> searchHDCT(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
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
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @GetMapping("/xac-nhan-giao-hang/{id}")
    public String xacNhanGiaoHang(Model model, @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrangGiaoHang(2);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        if(String.valueOf(hd.getNgayShip())==null){
            hd.setNgayShip(Date.valueOf(LocalDate.now()));
        }
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
            }
        }
        return "redirect:/don-hang/hien-thi";
    }

    @GetMapping("/xac-nhan-giao-hang-hoan-tat/{id}")
    public String giaoHangHoanTat(Model model, @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonService.findById(id);
        KhachHang kh= khachHangService.findById(hd.getKhachHang().getId());
        BigDecimal d=hd.getTongTien().divide(BigDecimal.valueOf(10000));
        Integer themDiem= Integer.valueOf(d.intValue());
        kh.setDiem(kh.getDiem()+themDiem);
        khachHangService.update(kh.getId(),kh);
        hd.setTinhTrangGiaoHang(3);
        hd.setTinhTrang(2);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hd.setNgayNhan(Date.valueOf(LocalDate.now()));
        if(hd.getLoai()==0){
            hd.setNgayThanhToan(Date.valueOf(LocalDate.now()));
        }
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImei(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
            }
        }
        return "redirect:/don-hang/hien-thi";
    }

    @GetMapping("/xac-nhan-huy/{id}")
    public String huy(Model model, @PathVariable("id") UUID id) {
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
                imeiService.updatImei1(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
            }
        }
        return "redirect:/don-hang/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String Update(Model model, @PathVariable("id") UUID id,
                         @ModelAttribute("donHang") HoaDon hoaDon) {
        LocalDate ngayShip = hoaDon.getNgayShip().toLocalDate();
        int check = LocalDate.now().compareTo(ngayShip);
        System.out.println(check);
        if (check > 0) {
            model.addAttribute("listDiaChi", diaChiService.findAll0());
            model.addAttribute("donHang", hoaDonnn);
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId()));
            model.addAttribute("thongBao", "Ngày ship không được nhỏ hơn ngày hiện tại");
            model.addAttribute("contentPage", "../don-hang/don-hang-view-update.jsp");
            return "home/layout";
        } else if (check < 0) {
            hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDon.setKhachHang(hoaDonnn.getKhachHang());
            hoaDon.setLoai(hoaDonnn.getLoai());
            hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
            hoaDon.setTinhTrangGiaoHang(1);
            hoaDon.setNgayTao(hoaDonnn.getNgayTao());
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
                    hoaDonService.update(id, hoaDon);
                } else {
                    hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
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
            return "redirect:/don-hang/hien-thi";
        } else {
            hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDon.setKhachHang(hoaDonnn.getKhachHang());
            hoaDon.setLoai(hoaDonnn.getLoai());
            hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
            hoaDon.setTinhTrangGiaoHang(2);
            hoaDon.setNgayTao(hoaDonnn.getNgayTao());
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
                    hoaDonService.update(id, hoaDon);
                } else {
                    hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
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
            return "redirect:/don-hang/hien-thi";
        }

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
                    imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
                }

            }
        }
        return "redirect:/don-hang/hien-thi";
    }

    @GetMapping("/delete-hoa-don-chi-tiet/{id}")
    public String deleteHDCT(Model model, @PathVariable("id") UUID id,
                             @ModelAttribute("donHang") HoaDon hoaDon) {

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
            hoaDonnn.setTongTien(subTotal.add(hoaDonnn.getPhiShip()));
            hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        }
        hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
        model.addAttribute("tong", String.valueOf(total));
        model.addAttribute("listHoaDonChiTiet", list);
        model.addAttribute("donHang", hoaDonnn);
        model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(hoaDonnn.getId()).getKhachHang().getId()));
        return "redirect:/don-hang/view-update/" + idHoaDon;
    }

    @GetMapping("/loc/ban-hang-tai-quay/{hang}/{rom}/{manHinh}/{camera}/{ram}/{chip}/{dungLuongPin}/{giaBanMin}/{giaBanMax}")
    public String loc(Model model
            , @PathVariable("hang") String hang,
                      @PathVariable("ram") String ram,
                      @PathVariable("rom") String rom,
                      @PathVariable("dungLuongPin") String dungLuongPin,
                      @PathVariable("chip") String chip,
                      @PathVariable("manHinh") String moTaMan,
                      @PathVariable("camera") String moTaCam
    ) {
        List<ChiTietSanPham> list = banHangOnlineService.locbanhang(hang, moTaCam, moTaMan, "null", ram, rom, "null", dungLuongPin, chip, "null");
        model.addAttribute("listChiTietSanPham", list);
        System.out.println("fjdkjffhkfhsf");

        return "don-hang/bang-loc";
    }

    @GetMapping("/loc/ban-hang-tai-quay/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}")
    public String locbanhang(
            Model model,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5,
            @PathVariable("x6") String x6,
            @PathVariable("x7") String x7,
            @PathVariable("x8") String x8,
            @PathVariable("x9") String x9,
            @PathVariable("x10") String x10
    ) {

        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listChiTietSanPham", banHangOnlineService.locbanhang(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10));
        return "don-hang/bang-loc";
    }

    @GetMapping("/them-imei/{id}")
    public String addIMEI(Model model, @PathVariable("id") UUID id,
                          @ModelAttribute("donHang") HoaDon hoaDon) {
        BigDecimal total = BigDecimal.ZERO;
        IMEI imei = imeiService.findById(id);
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setImei(imei);
        hdct.setHoaDon(hoaDonnn);
        hdct.setTinhTrang(0);
        if (imei.getChiTietSanPham().getKhuyenMai()==null) {
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
                hoaDonnn.setTongTien(total.add(hoaDonnn.getPhiShip()));
                hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            }
            hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
            System.out.println(total);
            model.addAttribute("tong", String.valueOf(total));
            model.addAttribute("listHoaDonChiTiet", list);
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("donHang", hoaDonnn);
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(hoaDonnn.getId()).getKhachHang().getId()));
            return "redirect:/don-hang/view-update/" + idHoaDon;
        } else {
            chiTietSanPhamService.update1(ct);
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
            for (HoaDonChiTiet hd : list
            ) {
                total = total.add(hd.getDonGia());
                hoaDonnn.setTongTien(total.add(hoaDonnn.getPhiShip()));
                hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            }
            hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
            System.out.println(total);
            model.addAttribute("tong", String.valueOf(total));
            model.addAttribute("listHoaDonChiTiet", list);
            model.addAttribute("donHang", hoaDonnn);
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(hoaDonnn.getId()).getKhachHang().getId()));
            return "redirect:/don-hang/view-update/" + idHoaDon;
        }
    }
}
