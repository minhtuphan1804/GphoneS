package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.services.*;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
public class BanHangTaiQuayController {
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
    private BanHangOnLinerepository banHangOnLinerepository;
    @Autowired
    MailerService mailer;
    @Autowired
    DataIntermediateService dataIntermediateService;

    private HoaDon hoaDonnn = new HoaDon();
    private UUID idHoaDon = null;
    private BigDecimal total = BigDecimal.ZERO;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        hoaDonnn = null;
        List<HoaDon> list = hoaDonService.find();
        model.addAttribute("listHoaDon", list);
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        model.addAttribute("HoaDon", hoaDonnn);

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


        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-hoa-don")
    public String addHoaDon(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        List<HoaDon> list = hoaDonService.find();
        if (list.size() >= 6) {
            hoaDonnn = null;
            model.addAttribute("HoaDon", hoaDonnn);
            model.addAttribute("thongBaoHoaDon", "Đã quá số lượng hóa đơn chờ");
            model.addAttribute("listHoaDon", list);
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("listDiaChi", diaChiService.findAll0());
            model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
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
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            NhanVien nhanVien = nhanVienService.findById(SecurityUtil.getId().getId());
            HoaDon hd = new HoaDon();
            String mhd = "";
            Integer sl = hoaDonService.findAll().size();
            if (sl < 10) {
                mhd = "MHD0" + sl;
            } else {
                mhd = "MHD" + sl;
            }
            hd.setMa(mhd);
            hd.setTinhTrang(0);
            hd.setTinhTrangGiaoHang(0);
            hd.setLoai(0);
            hd.setNgayTao(Date.valueOf(LocalDate.now()));
            hd.setNhanVien(nhanVien);
            hoaDonService.add(hd);
            List<HoaDon> listt = hoaDonService.find();
            model.addAttribute("listHoaDon", listt);
            model.addAttribute("thongBaoHoaDon1", "Thêm hóa đơn chờ thành công");
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("listDiaChi", diaChiService.findAll0());
            model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
            model.addAttribute("HoaDon", hoaDonnn);

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
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/thong-tin-hoa-don/{id}")
    public String thongTin(Model model,
                           @ModelAttribute("HoaDon") HoaDon hoaDon, @PathVariable("id") UUID id,
                           @ModelAttribute("modalAddKhachHang") KhachHang khachHang, @RequestParam("pageNum") Optional<Integer> num,
                           @RequestParam(name = "size", defaultValue = "2", required = false) Integer size) {

        HoaDon hd = hoaDonService.findById(id);
        idHoaDon = id;
        hoaDonnn = hd;
        model.addAttribute("HoaDon", hd);
        List<HoaDon> list = hoaDonService.find();
        model.addAttribute("listHoaDon", list);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        model.addAttribute("listHoaDonChiTiet", listHDCT);
        model.addAttribute("idHoaDon", String.valueOf(id));
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");

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
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/them-san-pham/{id}")
    public List<IMEI> themSanPham(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon,
                                  @PathVariable("id") UUID id, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        if (hoaDonnn.getMa() == null) {
            model.addAttribute("thongBao", "Chưa chọn hóa đơn");
            List<HoaDon> list = hoaDonService.find();
            model.addAttribute("listHoaDon", list);
        }
        model.addAttribute("HoaDon", hoaDonnn);
        List<IMEI> list = imeiService.getIMEI(id);
        System.out.println(list);
        return list;

    }

    @GetMapping("/hien-thi-san-pham")
    public String hienThiSanPham(Model model) {
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/remove/{id}")
    public String updateTrangThai(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang, @PathVariable("id") UUID id) {
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
            hd.setTinhTrangGiaoHang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setTinhTrangGiaoHang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }
        List<HoaDon> listHD = hoaDonService.find();
        model.addAttribute("HoaDon", null);
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("idHoaDon", String.valueOf(id));
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        model.addAttribute("thongBaoHoaDon1", "Xóa hóa đơn chờ thành công!");
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");

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
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/search-san-pham")
    public List<ChiTietSanPham> search(Model model, @RequestParam("search-san-pham") String search,
                                       @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang
    ) {
        List<ChiTietSanPham> listCT = chiTietSanPhamService.search(search);
        return listCT;

    }

    @ResponseBody
    @GetMapping("/search-imei")
    public List<IMEI> searchIMEI(Model model, @RequestParam("search-imei") String search,
                                 @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        List<IMEI> listIMEI = imeiService.searchOn(search);
        System.out.println(listIMEI);
        model.addAttribute("HoaDon", hoaDonnn);
        model.addAttribute("listImei", listIMEI);
        List<HoaDon> list = hoaDonService.find();
        model.addAttribute("listHoaDon", list);

        return listIMEI;
    }

    @GetMapping("/them-imei/{id}")
    public String addIMEI(Model model, @PathVariable("id") UUID id,
                          @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        BigDecimal total = BigDecimal.ZERO;
        IMEI imei = imeiService.findById(id);
        if (imei.getTinhTrang() == 0) {
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
            ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(id);
            ct.setSoLuong(ct.getSoLuong() - 1);
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            ct.setNgayCapNhat(date);
            imeiService.updatImeiChoXuLy(date, id);
            if (banHangOnLinerepository.tongimeiTT0cua1ctsp(ct.getId()) == 0) {
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
                List<HoaDon> listHD = hoaDonService.find();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("HoaDon", hoaDonnn);
                model.addAttribute("listHang", hangSanPhamService.findAll0());
                model.addAttribute("listMauSac", mauSacService.findAll0());
                model.addAttribute("listChip", chipService.findAll0());
                model.addAttribute("listRam", ramService.findAll0());
                model.addAttribute("listRom", romService.findAll0());
                model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
                model.addAttribute("listManHinh", manHinhService.findAll0());
                model.addAttribute("listCamera", cameraService.findAll0());
                model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
                model.addAttribute("listKhachHang", khachHangService.findAll0());
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());

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
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
                List<HoaDon> listHD = hoaDonService.find();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("HoaDon", hoaDonnn);
                model.addAttribute("listHang", hangSanPhamService.findAll0());
                model.addAttribute("listMauSac", mauSacService.findAll0());
                model.addAttribute("listChip", chipService.findAll0());
                model.addAttribute("listRam", ramService.findAll0());
                model.addAttribute("listRom", romService.findAll0());
                model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
                model.addAttribute("listManHinh", manHinhService.findAll0());
                model.addAttribute("listCamera", cameraService.findAll0());
                model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
                model.addAttribute("listKhachHang", khachHangService.findAll0());
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());

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
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
                return "home/layout";
            }
        } else {
            model.addAttribute("tong", String.valueOf(hoaDonnn.getTongTien()));
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
            model.addAttribute("listHoaDonChiTiet", list);
            List<HoaDon> listHD = hoaDonService.find();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("HoaDon", hoaDonnn);
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("listDiaChi", diaChiService.findAll0());
            model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());

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
            model.addAttribute("thongBaoHoaDon", "Sản phẩm đã bán hoặc đang chờ xử lý");
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
            return "home/layout";
        }

    }


    @GetMapping("/delete-hoa-don-chi-tiet/{id}")
    public String deleteHDCT(Model model, @PathVariable("id") UUID id,
                             @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {

        HoaDonChiTiet hd = hoaDonChiTietService.findById(id);
        ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(hd.getImei().getId());
        ct.setSoLuong(ct.getSoLuong() + 1);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ct.setNgayCapNhat(date);
        chiTietSanPhamService.update1(ct);
        imeiService.updatImei1(date, id);
        hoaDonChiTietService.delete(id);
        if (ct.getSoLuong() > 0) {
            ct.setTinhTrang(0);
        }
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

        System.out.println(total);
        hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
        model.addAttribute("tong", String.valueOf(total));
        model.addAttribute("listHoaDonChiTiet", list);
        model.addAttribute("HoaDon", hoaDonnn);
        List<HoaDon> listHD = hoaDonService.find();
        model.addAttribute("listHoaDon", listHD);
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        return "redirect:/ban-hang/thong-tin-hoa-don/" + idHoaDon;
    }

    @PostMapping("/xac-nhan/{id}")
    public String xacNhan(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @PathVariable("id") UUID id,
                          @ModelAttribute("modalAddKhachHang") KhachHang khachHang, @ModelAttribute("modalAddDiaChi") DiaChi DiaChi) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setKhachHang(hoaDon.getKhachHang());
        hd.setDiaChi(hoaDon.getDiaChi());
        hd.setNhanVien(hoaDonnn.getNhanVien());
        hd.setTongTien(hoaDon.getTongTien());
        hd.setQuyDoi(hoaDon.getQuyDoi());
        hd.setSdt(hoaDon.getSdt());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hd.setNgayCapNhat(date);
//        hd.setNgayNhan(date);
//        hd.setNgayShip(date);
        hd.setGhiChu(hoaDon.getGhiChu());
        hd.setTinhTrang(1);
        hd.setTinhTrangGiaoHang(0);
        hd.setNguoiNhan(hoaDon.getKhachHang().getHoTen());
        hoaDonService.thanhToan(hd);
        idHoaDon = id;
        hoaDonnn = hd;
        model.addAttribute("HoaDon", hoaDonService.findById(id));
        model.addAttribute("listKhachHang", khachHangService.khachHangThanhToan(id));
        model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(id).getKhachHang().getId()));
        model.addAttribute("listNhanVien", nhanVienService.nhanVienThanhToan(id));
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(id));
        model.addAttribute("contentPage", "../ban-hang/thanh-toan.jsp");
        return "home/layout";
    }

    @PostMapping("/thanh-toan/{id}")
    public ResponseEntity<byte[]> thanhToan(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @PathVariable("id") UUID id,
                                            @ModelAttribute("modalAddDiaChi") DiaChi DiaChi) {
        HoaDon hd = hoaDonService.findById(id);
        KhachHang kh = khachHangService.findById(hd.getKhachHang().getId());
        BigDecimal d = hd.getTongTien().divide(BigDecimal.valueOf(10000));
        Integer themDiem = Integer.valueOf(d.intValue());
        kh.setDiem(kh.getDiem() + themDiem);
        khachHangService.update(kh.getId(), kh);
        hd.setKhachHang(hoaDon.getKhachHang());
        hd.setDiaChi(hoaDon.getDiaChi());
        hd.setNhanVien(hoaDon.getNhanVien());
        hd.setTongTien(hoaDon.getTongTien());
        hd.setQuyDoi(hoaDon.getQuyDoi());
        hd.setSdt(hoaDon.getSdt());
        hd.setTinhTrangGiaoHang(3);
        hd.setPhiShip(BigDecimal.valueOf(0));

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hd.setNgayCapNhat(date);
        hd.setNgayNhan(date);
        hd.setNgayShip(date);
        hd.setGhiChu(hoaDon.getGhiChu());
        hd.setTinhTrang(2);
        hd.setNgayThanhToan(date);
        hd.setHinhThucThanhToan(hoaDon.getHinhThucThanhToan());
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                hdct.setTinhTrang(1);
                hoaDonChiTietService.update(hdct.getId(), hdct);
                imeiService.updatImei(date, hdct.getId());
            }
        }
        if (hoaDon.getNguoiNhan().isEmpty()) {
            hd.setNguoiNhan(hoaDon.getKhachHang().getHoTen());
            hoaDonService.thanhToan(hd);
            ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonTaiQuay(id);
            byte[] pdfBytes = responseEntity.getBody();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } else {
            hd.setNguoiNhan(hoaDon.getNguoiNhan());
            hoaDonService.thanhToan(hd);
            ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonTaiQuay(id);
            byte[] pdfBytes = responseEntity.getBody();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don_" + id + ".pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }

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

        return "ban-hang/bang-loc";
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
        return "ban-hang/bang-loc";
    }


    @GetMapping("/modal-khach-hang")
    public String modalKhachHang(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
        model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add-khach-hang")
    public String addKhachHang(Model model, @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        KhachHang khh = new KhachHang();
        String randomPW = generateRandomPassword(8);
        khh.setMatKhau(randomPW);
        String hashedPassword = BCrypt.hashpw(randomPW, BCrypt.gensalt());
        khachHang.setMatKhau(hashedPassword);
        khachHang.setMa("KH" + String.valueOf(khachHangService.findAll().size()) + 1);
        khachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        khachHang.setTinhTrang(0);
        khachHangService.add(khachHang);
        KhachHang khachHang1 = khachHangService.findById(khachHang.getId());
        mailer.queue(khachHang1.getEmail(), "Bạn đã đăng kí tài khoản thành công", "TK: " + khachHang1.getTaiKhoan() + "\nMK: " + khh.getMatKhau());
        return "redirect:/ban-hang/thong-tin-hoa-don/" + idHoaDon;
    }

    @GetMapping("/them-gio-hang/{soImei}")
    public String ScanQrCode(Model model, @PathVariable("soImei") String id, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        BigDecimal total = BigDecimal.ZERO;

        IMEI imei = imeiService.searchSoImei(id);
        if (imei == null) {
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
            model.addAttribute("listHoaDonChiTiet", list);
            List<HoaDon> listHD = hoaDonService.find();
            model.addAttribute("listHoaDon", listHD);
            model.addAttribute("HoaDon", hoaDonnn);
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("listDiaChi", diaChiService.findAll0());
            model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());

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
            model.addAttribute("thongBaoHoaDon", "Sản phẩm vừa quét đã bán hoặc đang chờ xử lý");
            model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
            ct.setNgayCapNhat(date);
            imeiService.updatImeiChoXuLy(date, imei.getId());
            if (banHangOnLinerepository.tongimeiTT0cua1ctsp(ct.getId()) == 0) {
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
                List<HoaDon> listHD = hoaDonService.find();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("HoaDon", hoaDonnn);
                model.addAttribute("listHang", hangSanPhamService.findAll0());
                model.addAttribute("listMauSac", mauSacService.findAll0());
                model.addAttribute("listChip", chipService.findAll0());
                model.addAttribute("listRam", ramService.findAll0());
                model.addAttribute("listRom", romService.findAll0());
                model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
                model.addAttribute("listManHinh", manHinhService.findAll0());
                model.addAttribute("listCamera", cameraService.findAll0());
                model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
                model.addAttribute("listKhachHang", khachHangService.findAll0());
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());

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
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
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
                List<HoaDon> listHD = hoaDonService.find();
                model.addAttribute("listHoaDon", listHD);
                model.addAttribute("HoaDon", hoaDonnn);
                model.addAttribute("listHang", hangSanPhamService.findAll0());
                model.addAttribute("listMauSac", mauSacService.findAll0());
                model.addAttribute("listChip", chipService.findAll0());
                model.addAttribute("listRam", ramService.findAll0());
                model.addAttribute("listRom", romService.findAll0());
                model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
                model.addAttribute("listManHinh", manHinhService.findAll0());
                model.addAttribute("listCamera", cameraService.findAll0());
                model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll0());
                model.addAttribute("listKhachHang", khachHangService.findAll0());
                model.addAttribute("listDiaChi", diaChiService.findAll0());
                model.addAttribute("listHangKhachHang", hangKhachHangService.findAll0());
                model.addAttribute("thongBaoHoaDon1", "Sản phẩm đã thêm vào hóa đơn");
                model.addAttribute("contentPage", "../ban-hang/hien-thi.jsp");
                return "home/layout";
            }
        }
    }

    @PostMapping("/search-hoa-don-chi-tiet-thanh-toan")
    public String searchHoaDonChiTietThanhToan(Model model, @RequestParam("search-hoa-don-chi-tiet-thanh-toan") String search,
                                               @ModelAttribute("modalAddDiaChi") DiaChi DiaChi) {
        if (search.isEmpty()) {
            model.addAttribute("thongBaoHoaDonChiTiet", "Mời nhập thông tin cần tìm kiếm");
            model.addAttribute("HoaDon", hoaDonService.findById(idHoaDon));
            model.addAttribute("listKhachHang", khachHangService.khachHangThanhToan(idHoaDon));
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
            model.addAttribute("listNhanVien", nhanVienService.nhanVienThanhToan(idHoaDon));
            model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(idHoaDon));
            model.addAttribute("contentPage", "../ban-hang/thanh-toan.jsp");
            return "home/layout";
        } else {
            List<HoaDonChiTiet> list = hoaDonChiTietService.searchHDCTBanHangTaiQuay(idHoaDon, search);
            model.addAttribute("HoaDon", hoaDonService.findById(idHoaDon));
            model.addAttribute("listKhachHang", khachHangService.khachHangThanhToan(idHoaDon));
            model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
            model.addAttribute("listNhanVien", nhanVienService.nhanVienThanhToan(idHoaDon));
            model.addAttribute("listHoaDonChiTiet", list);
            model.addAttribute("contentPage", "../ban-hang/thanh-toan.jsp");
            return "home/layout";
        }
    }

    @PostMapping("/add-dia-chi")
    public String addDiaChi(Model model, @ModelAttribute("modalAddDiaChi") DiaChi diaChi) {
        diaChi.setMa("DC" + String.valueOf(diaChiService.findAll().size()) + 1);
        diaChi.setNgayTao(Date.valueOf(LocalDate.now()));
        diaChi.setTinhTrang(0);
        diaChi.setKhachHang(khachHangService.newKhachHang(idHoaDon));
        diaChiService.add(diaChi);
        model.addAttribute("HoaDon", hoaDonService.findById(idHoaDon));
        model.addAttribute("listKhachHang", khachHangService.khachHangThanhToan(idHoaDon));
        model.addAttribute("listDiaChi", diaChiService.diaChiThanhToan(hoaDonService.findById(idHoaDon).getKhachHang().getId()));
        model.addAttribute("listNhanVien", nhanVienService.nhanVienThanhToan(idHoaDon));
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(idHoaDon));
        model.addAttribute("modalAddDiaChi", new DiaChi());
        model.addAttribute("contentPage", "../ban-hang/thanh-toan.jsp");
        return "home/layout";
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    @ResponseBody
    @GetMapping("/search-hoa-don-chi-tiet")
    public List<HoaDonChiTiet> searchHoaDonChiTiet(Model model, @RequestParam("search-hoa-don-chi-tiet") String search,
                                                   @ModelAttribute("HoaDon") HoaDon hoaDon, @ModelAttribute("modalAddKhachHang") KhachHang khachHang) {
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.searchHDCTBanHangTaiQuay(idHoaDon, search);
        model.addAttribute("HoaDon", hoaDonnn);
        List<HoaDon> list = hoaDonService.find();
        model.addAttribute("listHoaDon", list);
        return listHoaDonChiTiets;
    }

}
