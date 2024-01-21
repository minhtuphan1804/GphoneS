package com.example.demo.controllers;

import com.example.demo.config.Config;
import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.repositories.DonDatHangRepository;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.*;
import com.example.demo.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.security.SecureRandom;

@Component
@Controller
public class BanHangOnlineController {
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
    DoiTraService doiTraService;
    @Autowired
    DonDatHangRepository donDatHangRepository;


    // Bắt đầu bán hàng
    public Integer kt(Integer so) {
        if (so == Integer.valueOf(0)) {
            return Integer.valueOf(1);
        }
        return so;
    }

    //    private String checkGMAILorTK="0";
    private String idkhachhang = "1";

    //    @GetMapping("/ban-hang-online/dangxuat")
////    public String lgin11() {
////        idkhachhang="1";
////        System.out.println("--------"+idkhachhang);
////        return "login/loginPage";
////    }
    @GetMapping("/lgin")
    public String lgin() {
        idkhachhang = "1";
        return "login/loginPage";
    }

    @GetMapping("/ban-hang-online/hien-thi")
    public String hienThitrangchu(
            Model model
    ) {
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }

        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("listTop10sp", banHangOnlineService.top10SanPhamBanChay());
        idkhachhang = "1";
        model.addAttribute("idkhachhang", idkhachhang);
        return "ban-hang-online/trang-chu";
    }

    @GetMapping("/ban-hang-online/home")
    public String hienThitrangchudnhome(
            Model model
    ) {
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);


        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());


//        giohang
        model.addAttribute("tttong", 1);
        if (idkhachhang.equals("1")) {
            return "redirect:/";
        } else {
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            return "ban-hang-online/trang-chu";
        }

    }

    @GetMapping("/ban-hang-online/taikhoan-matkhau")
    public String hienThitrangchudn(@AuthenticationPrincipal Authentication authentication,
                                    Model model
    ) {
        if (SecurityUtil.getId() != null) {
//            checkGMAILorTK="1";

            double tong = 0;
            Integer lamchon = 0;
            for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
                if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                    tong = tong + 1;
                    lamchon = lamchon + 1;
                }
            }
            double tb = tong / 3;
            lamchon = lamchon / 3;
            if (tb % 1 > 0) {
                lamchon = lamchon + 1;
            }
            model.addAttribute("lamchon", lamchon);
            model.addAttribute("giamgia", banHangOnlineService);
            model.addAttribute("banhangonline", banHangOnlineService);

//lấy id khách hàng
//        KhachHang khachHang=  dataService.getSharedData();
////        System.out.println("id:"+khachHang.getId());
//        idkhachhang=String.valueOf(khachHang.getId());
////        idkhachhang="f33013f5-993b-45d3-9806-dd74f2007522";
// kết thúc lấy id khách hàng
            UserInfoUserDetails userDetails = SecurityUtil.getId();
            idkhachhang = String.valueOf(userDetails.getId());

            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang", idkhachhang);
//        giohang
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong", 1);
            return "redirect:/ban-hang-online/home";
        } else {
            return "redirect:/";
        }


    }

    @GetMapping("/ban-hang-online/gmail")
    public String hienThitrangchudnemail(@AuthenticationPrincipal OAuth2User oauth2User,
                                         Principal principal,
                                         Model model
    ) {
        if (oauth2User != null) {
//            checkGMAILorTK="2";
            double tong = 0;
            Integer lamchon = 0;
            for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {

                if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                    tong = tong + 1;
                    lamchon = lamchon + 1;
                }
            }
            double tb = tong / 3;
            lamchon = lamchon / 3;
            if (tb % 1 > 0) {
                lamchon = lamchon + 1;
            }
            model.addAttribute("lamchon", lamchon);
            model.addAttribute("giamgia", banHangOnlineService);
            model.addAttribute("banhangonline", banHangOnlineService);

//lay email
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");
            String idToken = oauth2User.getAttribute("id_token");
            System.out.println(principal.getName());

            System.out.println(email);
            System.out.println(name);

            // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu hay chưa


            if (khachHangRepository.getKhachHangByTaiKhoan(email).isEmpty()) {
                // Người dùng chưa tồn tại, tạo người dùng mới
//            GoogleId token= oauth2User.get;
                String mkh = "";
                Integer sl = khachHangService.findAll().size() + 1;
                if (sl < 9) {
                    mkh = "MKH0" + sl;
                } else {
                    mkh = "MKH" + sl;
                }
                List<KhachHang> khachHangList = khachHangRepository.findAll();
                String email1 = oauth2User.getAttribute("email");
                String name1 = oauth2User.getAttribute("name");
                KhachHang newUser = new KhachHang();
                newUser.setMa(mkh);
                newUser.setEmail(email1);
                newUser.setTaiKhoan(email1);
                newUser.setHoTen(name1);
                newUser.setSdt("0123456789");
                newUser.setDiem(1);
                newUser.setGioiTinh(true);
                String randomPassword = LoginController.generateRandomPassword(8);
                newUser.setTaiKhoan(randomPassword);
                newUser.setNgaySinh(Date.valueOf("1999-1-1"));
                newUser.setTinhTrang(0);
                newUser.setNgayTao(Date.valueOf(LocalDate.now()));
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


                String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
                newUser.setMatKhau(hashedPassword);

                // Lưu người dùng mới vào cơ sở dữ liệu
                khachHangRepository.save(newUser);
                //      them gh
                khachHangService.findAll();
                String mghkh = "";
                Integer slgh = gioHangService.findAll().size();
                if (slgh < 10) {
                    mghkh = "MGH0" + slgh;
                } else {
                    mghkh = "MGH" + slgh;
                }
                GioHang ghkh = new GioHang();
                ghkh.setMa(mghkh);
                for (KhachHang kh11 : khachHangService.findAll()) {
                    if (kh11.getMa().equals(mkh)) {
                        ghkh.setKhachHang(kh11);
                        idkhachhang = String.valueOf(kh11.getId());
                        break;
                    }
                }
                gioHangService.add(ghkh);
// het them gh

            } else {
                Optional<KhachHang> idkh = khachHangRepository.getKhachHangByTaiKhoan(email);
                idkhachhang = String.valueOf(idkh.get().getId());
            }

//    het lay email

            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang", idkhachhang);
//        giohang
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong", 1);
            return "redirect:/ban-hang-online/home";


        } else {
            return "redirect:/";
        }

    }


    @GetMapping("/ban-hang-online/dien-thoai-thong-minh")
    public String hienThitrangchudienthoai(
            Model model
    ) {
//
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//
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
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        //gio hang
        if (idkhachhang.equals("1")) {

        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        }
        model.addAttribute("tttong", 1);
//        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));


        return "ban-hang-online/trang-loc-sanpham";


    }

    @GetMapping("/ban-hang-online/loc-ban-hang/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}/{x11}/{x12}")
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
            @PathVariable("x10") String x10,
            @PathVariable("x11") String x11,
            @PathVariable("x12") String x12
    ) {


        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12)))) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }

        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12))));

        return "ban-hang-online/single_pase_giao-dien-loc";
    }

    @GetMapping("/ban-hang-online/chi-tiet-san-pham/{idctsp}")
    public String giohang(
            Model model,
            @PathVariable("idctsp") UUID idctsp

    ) {
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//        chi tiêt san pham
        model.addAttribute("ktcokhong", 1);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("motctsp", chiTietSanPhamService.findById(idctsp));
        System.out.println(chiTietSanPhamService.findById(idctsp).getSoLuong());
        model.addAttribute("idctsp", idctsp);
//gio hang
        if (idkhachhang.equals("1")) {

        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        }
        model.addAttribute("tttong", 1);

//danh sách sản phẩm dưới
//danh sách sản phẩm dưới

        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);

        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());

        return "ban-hang-online/trang-chi-tiet-san-pham";


    }


    @GetMapping("/ban-hang-online/loc-chi-tiet-san-pham/{x1}/{x2}/{x3}/{x4}/{x5}")
    public String locbanhangctsp(
            Model model,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5

    ) {


        List<ChiTietSanPham> listctsp = banHangOnlineService.locbanhang("null", "null", "null", x1, x3, x2, "null", "null", x4, x5);

        ChiTietSanPham motctsp = new ChiTietSanPham();
        int kt = 0;
        for (ChiTietSanPham ht : listctsp) {
            if (banHangOnlineService.soluongcon(String.valueOf(ht.getId())) > 0) {
                kt = 1;
                motctsp = ht;
                break;
            }
        }
        System.out.println("taco-" + listctsp.size());
        model.addAttribute("tensp", x5);
        model.addAttribute("ktcokhong", kt);
        model.addAttribute("motctsp", motctsp);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("banhangonline", banHangOnlineService);

        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_chi-tiet-san-pham";
    }

    @PostMapping("/ban-hang-online/trang-chi-tiet-san-pham/thanh-toan")
    public String nutthanhtoanctsp(
            Model model,
            @RequestParam("idkh") String idkh,
            @RequestParam("idctsp") UUID idctsp,
            @RequestParam("solg") Integer soluong
    ) {

//        if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).size() > 0) {
//            GioHangChiTiet ghctud = banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).get(0);
//            Integer slud = ghctud.getSoLuong() + soluong;
//            ghctud.setSoLuong(slud);
//            gioHangChiTietService.add(ghctud);
//        } else {
//            GioHangChiTiet ghct = new GioHangChiTiet();
//            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkh).get(0));
//            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
//            ghct.setSoLuong(soluong);
//            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
//            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
//            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
//            Integer phantramgiam = banHangOnlineService.tonggiamgia(String.valueOf(idctsp));
//            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
//            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
//            ghct.setDonGiaKhiGiam(dgkg1);
//            gioHangChiTietService.add(ghct);
//        }
        if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).size() > 0) {
            GioHangChiTiet ghctud = banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).get(0);
            ghctud.setSoLuong(soluong);
            ghctud.setTinhTrang(0);
            gioHangChiTietService.add(ghctud);
            banHangOnLinerepository.updatelaighctve1trumotIDGH(ghctud.getId());
        } else {
            GioHangChiTiet ghct = new GioHangChiTiet();
            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkh).get(0));
            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
            ghct.setSoLuong(soluong);
            ghct.setTinhTrang(0);
            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
            Integer phantramgiam = banHangOnlineService.tonggiamgia(String.valueOf(idctsp));
            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
            ghct.setDonGiaKhiGiam(dgkg1);
            gioHangChiTietService.add(ghct);
            banHangOnLinerepository.updatelaighctve1trumotIDGH(banHangOnLinerepository.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), chiTietSanPhamService.findById(idctsp).getId()).get(0).getId());
        }

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//        return "ban-hang-online/trang-gio-hang-chi-tiet";
        return "redirect:/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang/" + banHangOnlineService.ListghTheoidkh(idkh).get(0).getId();

    }


    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/so-luong/{idghct}/{solg}")
    public String updateslghct(
            Model model,
            @PathVariable("idghct") UUID idghct,
            @PathVariable("solg") Integer soluong
    ) {


        GioHangChiTiet ghct = gioHangChiTietService.findById(idghct);
        ghct.setSoLuong(soluong);
        gioHangChiTietService.add(ghct);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(ghct.getGioHang().getKhachHang().getId())).get(0).getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("tttong", 1);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/chon-san-pham/{idghct}/{trangthai}/{idgh}")
    public String chonspghct(
            Model model,

            @PathVariable("idghct") String idghct,
            @PathVariable("trangthai") String trangthai,
            @PathVariable("idgh") UUID idgh
    ) {
        if (idghct.equals("full")) {
            if (trangthai.equals("0")) {
                banHangOnlineService.trangthaighct(0, idgh);
                model.addAttribute("tttong", 0);
            } else {
                banHangOnlineService.trangthaighct(1, idgh);
                model.addAttribute("tttong", 1);
            }
        } else {
            GioHangChiTiet ghct = gioHangChiTietService.findById(UUID.fromString(idghct));
            if (trangthai.equals("0")) {
                ghct.setTinhTrang(0);
                gioHangChiTietService.add(ghct);
            } else {
                ghct.setTinhTrang(1);
                gioHangChiTietService.add(ghct);
            }
        }

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/xoa-mot-ghct/{idghct}/{idgh}")
    public String xoamotghct(
            Model model,
            @PathVariable("idghct") UUID idghct,
            @PathVariable("idgh") UUID idgh
    ) {
        gioHangChiTietService.delete(idghct);
        model.addAttribute("tttong", 1);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang/{idgh}")
    public String nutthanhtoantrangghctget(
            Model model,
            @PathVariable("idgh") UUID idgh
    ) {
        model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";
    }

    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang")
    public String nutthanhtoantrangghct(
            Model model,
            @RequestParam("idgh") UUID idgh
    ) {
        model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";
    }

    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-them-dia-chi")
    public String nutthemdiachi(
            Model model,
            @RequestParam("diachi") String diachi1, @RequestParam("quan") String quan,
            @RequestParam("huyen") String huyen, @RequestParam("thanhpho") String thanhpho,
            @RequestParam("idgh") UUID idgh
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi diaChi = new DiaChi();
        Integer sl = diaChiService.findAll().size();
        String mhd = "";
        if (sl < 10) {
            mhd = "MDC0" + sl;
        } else {
            mhd = "MDC" + sl;
        }
        diaChi.setMa(mhd);
        diaChi.setNgayTao(date);
        diaChi.setNgayCapNhat(date);
        diaChi.setTinhTrang(0);
        diaChi.setDiaChi(diachi1);
        diaChi.setQuan(quan);
        diaChi.setHuyen(huyen);
        diaChi.setThanhPho(thanhpho);
        KhachHang kh = khachHangService.findById(gioHangService.findById(idgh).getKhachHang().getId());
        diaChi.setKhachHang(kh);
        diaChiService.add(diaChi);

        model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));

        return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";

    }

    Long tienthanhtoan1 = Long.valueOf(0);
    UUID iddc1 = null;
    String sdt1 = "";
    UUID idgh1 = null;
    String nguoinhan1 = "";

    //    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-dat-hang/{idgh}/{tongtien}/{iddc}/{sdt}")
    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-dat-hang")
    public String nutdathang(
            Model model,
            @RequestParam("idgh1") UUID idgh,
            @RequestParam("tongtien1") BigDecimal tongtien,
            @RequestParam("iddc1") UUID iddc,
            @RequestParam("sdt1") String sdt,
            @RequestParam("tienmaORvnp") Integer tienmaORvnp,
            @RequestParam("nn1") String nguoinhan
    ) throws UnsupportedEncodingException {

        if (diaChiService.findById(iddc).getThanhPho().contains("Hà Nội")) {
            List<ChiTietSanPham> listctspkt=new ArrayList<>();
            for (GioHangChiTiet  ghct:banHangOnLinerepository.ListghTheoidghvsTT1(idgh)) {
                if(ghct.getSoLuong()>banHangOnLinerepository.soluongcon(ghct.getChiTietSanPham().getId())){
                  listctspkt.add(ghct.getChiTietSanPham());
                }
            }
            if (listctspkt.size()>0){


                model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));
                model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
                model.addAttribute("banhangonline", banHangOnlineService);
                if (idkhachhang.equals("1")) {
                    model.addAttribute("idkhachhang", idkhachhang);
                } else {
                    model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
                    model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
                }
                model.addAttribute("thongbaosoluongdathang", "Một số sản phẩm đặt quá số lượng còn sản phẩm");
                model.addAttribute("listctspkt", listctspkt);

                return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";

            }else   {

                if (tienmaORvnp == 1) {
                    //tien mat
                    //        them hd
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    HoaDon hd = new HoaDon();
                    Integer sl = hoaDonService.findAll().size();
                    String mhd = "";
                    if (sl < 10) {
                        mhd = "MHD0" + sl;
                    } else {
                        mhd = "MHD" + sl;
                    }
                    hd.setMa(mhd);
//        hd.setSdt(gioHangService.findById(idgh).getKhachHang().getSdt());
                    hd.setSdt(sdt);
                    hd.setNguoiNhan(nguoinhan);
                    Long tt = Long.valueOf(String.valueOf(tongtien)) + Long.valueOf(30000);
                    hd.setTongTien(BigDecimal.valueOf(tt));
                    hd.setPhiShip(BigDecimal.valueOf(Long.valueOf(30000)));
                    hd.setNgayTao(date);
                    hd.setNgayCapNhat(date);
                    hd.setTinhTrang(0);
                    hd.setLoai(1);
                    hd.setHinhThucThanhToan(2);
                    hd.setTinhTrangGiaoHang(0);
                    KhachHang kh = khachHangService.findById(gioHangService.findById(idgh).getKhachHang().getId());
                    hd.setKhachHang(kh);
                    DiaChi dc = diaChiService.findById(iddc);
                    hd.setDiaChi(dc);
                    hoaDonService.add(hd);
//    them hdct
//            List<GioHangChiTiet> listghct = banHangOnlineService.ListghTheoidghvsTT1(idgh);
//            for (int a = 0; a < listghct.size(); a = a + 1) {
//                for (int b = 0; b < listghct.get(a).getSoLuong(); b = b + 1) {
//                    HoaDonChiTiet hdct = new HoaDonChiTiet();
//                    hdct.setSoLuong(1);
//                    hdct.setTinhTrang(0);
//                    hdct.setDonGia(listghct.get(a).getDonGiaKhiGiam());
//                    HoaDon hd1 = banHangOnlineService.timhdtheomahd(mhd);
//                    hdct.setHoaDon(hd1);
//                    List<IMEI> listimei = banHangOnlineService.timimeitheoidctspVSttO(listghct.get(a).getChiTietSanPham().getId());
//                    hdct.setImei(listimei.get(0));
//                    hoaDonChiTietService.add(hdct);
//// cập nhật trạng thái imei
//                    IMEI imei = listimei.get(0);
//                    imei.setTinhTrang(3);
//                    imei.setNgayCapNhat(date);
//                    imeiService.add(imei);
//// cập nhật lại số lượng ctsp của imei đó
//                    ChiTietSanPham motctsp=imei.getChiTietSanPham();
//                    motctsp.setSoLuong(motctsp.getSoLuong()-1);
//                    chiTietSanPhamService.add(motctsp);
//                }
//            }
//  sao chép sản phẩm trong giỏ hàng chi tiết khi đặt hàng tt=0
                    List<GioHangChiTiet> listghct = banHangOnlineService.ListghTheoidghvsTT1(idgh);
                    for (GioHangChiTiet ghct1 : listghct) {
                        DonDatHang ddh = new DonDatHang();
                        ddh.setHoaDon(banHangOnlineService.timhdtheomahd(mhd));
                        ddh.setChiTietSanPham(ghct1.getChiTietSanPham());
                        ddh.setTinhTrang(ghct1.getTinhTrang());
                        ddh.setSoLuong(ghct1.getSoLuong());
                        ddh.setDonGia(ghct1.getDonGia());
                        ddh.setDonGiaKhiGiam(ghct1.getDonGiaKhiGiam());
                        donDatHangRepository.save(ddh);
                    }
//xoa ghct TT=0 theo idgh
                    banHangOnlineService.xoaghcttheoIDGHvsTTO(idgh);

//cập nhật hóa đơn; thanh toán khi nhận hàng
                    HoaDon hd1 = banHangOnlineService.timhdtheomahd(mhd);
                    hd1.setTinhTrang(3);//chowf thanh toan
                    hd1.setHinhThucThanhToan(0);//thanh toans khi nhan hang
                    hoaDonService.add(hd1);


                } else {
//  thanh toán online

//            return "redirect:/pay/"+idgh+"/"+tongtien+"/"+iddc+"/"+sdt;

                    idgh1 = idgh;
                    tienthanhtoan1 = Long.valueOf(String.valueOf(tongtien));
                    iddc1 = iddc;
                    sdt1 = sdt;
                    nguoinhan1 = nguoinhan;
                    System.out.println("--idgh:" + idgh + "--tien:" + tienthanhtoan1 + "--sdt:" + sdt1 + "---nn:" + nguoinhan1);
                    String vnp_Version = "2.1.0";
                    String vnp_Command = "pay";
                    String orderType = "other";
                    long amount = tienthanhtoan1 * 100;
                    String bankCode = "NCB";

                    String vnp_TxnRef = Config.getRandomNumber(8);
                    String vnp_IpAddr = "127.0.0.1";

                    String vnp_TmnCode = Config.vnp_TmnCode;

                    Map<String, String> vnp_Params = new HashMap<>();
                    vnp_Params.put("vnp_Version", vnp_Version);
                    vnp_Params.put("vnp_Command", vnp_Command);
                    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                    vnp_Params.put("vnp_Amount", String.valueOf(amount));
                    vnp_Params.put("vnp_CurrCode", "VND");

                    vnp_Params.put("vnp_BankCode", bankCode);
                    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                    vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
                    vnp_Params.put("vnp_OrderType", orderType);

                    vnp_Params.put("vnp_Locale", "vn");
//        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl+"?contractId="+contractId);
                    vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl + "?contractId=");
                    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String vnp_CreateDate = formatter.format(cld.getTime());
                    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                    cld.add(Calendar.MINUTE, 15);
                    String vnp_ExpireDate = formatter.format(cld.getTime());
                    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                    List fieldNames = new ArrayList(vnp_Params.keySet());
                    Collections.sort(fieldNames);
                    StringBuilder hashData = new StringBuilder();
                    StringBuilder query = new StringBuilder();
                    Iterator itr = fieldNames.iterator();
                    while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                            //Build hash data
                            hashData.append(fieldName);
                            hashData.append('=');
                            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            //Build query
                            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                            query.append('=');
                            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            if (itr.hasNext()) {
                                query.append('&');
                                hashData.append('&');
                            }
                        }
                    }

                    String queryUrl = query.toString();
                    String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
                    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                    String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

                    return "redirect:" + paymentUrl;


                }

//
                model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(gioHangService.findById(idgh).getKhachHang().getId())).get(0).getId()));
                model.addAttribute("tttong", 1);
                model.addAttribute("banhangonline", banHangOnlineService);
                if (idkhachhang.equals("1")) {
                    model.addAttribute("idkhachhang", idkhachhang);
                } else {
                    model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
                    model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
                }
                return "ban-hang-online/dat_hang_thanh_cong";
            }


        } else {
            model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));
            model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
            model.addAttribute("banhangonline", banHangOnlineService);
            if (idkhachhang.equals("1")) {
                model.addAttribute("idkhachhang", idkhachhang);
            } else {
                model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
                model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
            }
            model.addAttribute("thongbaodiachiHN", "Cửa hàng chưa có thể giao hàng ngoài Hà Nội, mong quý khách hàng thông cảm vì sự bất tiện này!");
            return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";
        }

    }

    @GetMapping("ban-hang-online/ketquathanhtoan")
    public String paymentCallback(
            Model model,
            @RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String contractId = queryParams.get("contractId");
        String registerServiceId = queryParams.get("registerServiceId");
        System.out.println("vnp_ResponseCode----" + vnp_ResponseCode);
        System.out.println("contractId----" + contractId);
        System.out.println("registerServiceId----" + registerServiceId);
        System.out.println("madonhang: " + queryParams.get("vnp_TxnRef"));

        // giao dien

        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }

        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);

        UUID idkh = gioHangService.findById(idgh1).getKhachHang().getId();
        model.addAttribute("khachhangdangnhap", khachHangService.findById(idkh));
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang", idkh);
        model.addAttribute("kh", khachHangService.findById(idkh));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh1));
        model.addAttribute("tttong", 1);

//        het giao dien
        // that bai: 24,rỗng, null
        // thành công:00,rỗng,null
        //hết thời gian :15,rỗng,null

        if ("00".equals(vnp_ResponseCode)) {

            // Giao dịch thành công
            // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
//hoadon
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            HoaDon hd = new HoaDon();
            Integer sl = hoaDonService.findAll().size();
            String mhd = "";
            if (sl < 10) {
                mhd = "MHD0" + sl;
            } else {
                mhd = "MHD" + sl;
            }
            hd.setMa(mhd);
//        hd.setSdt(gioHangService.findById(idgh).getKhachHang().getSdt());
            hd.setSdt(sdt1);
            hd.setMaGiaoDich(queryParams.get("vnp_TxnRef"));
            Long tt = Long.valueOf(String.valueOf(tienthanhtoan1)) + Long.valueOf(30000);
            hd.setTongTien(BigDecimal.valueOf(tt));
            hd.setPhiShip(BigDecimal.valueOf(Long.valueOf(30000)));
            hd.setNgayTao(date);
            hd.setNguoiNhan(nguoinhan1);
            hd.setNgayCapNhat(date);
            hd.setNgayThanhToan(date);
            hd.setTinhTrang(0);
            hd.setLoai(1);
            hd.setHinhThucThanhToan(1);
            hd.setTinhTrangGiaoHang(0);
            KhachHang kh = khachHangService.findById(gioHangService.findById(idgh1).getKhachHang().getId());
            hd.setKhachHang(kh);
            DiaChi dc = diaChiService.findById(iddc1);
            hd.setDiaChi(dc);
            hoaDonService.add(hd);
//    them hdct
//            List<GioHangChiTiet> listghct = banHangOnlineService.ListghTheoidghvsTT1(idgh1);
//            for (int a = 0; a < listghct.size(); a = a + 1) {
//                for (int b = 0; b < listghct.get(a).getSoLuong(); b = b + 1) {
//                    HoaDonChiTiet hdct = new HoaDonChiTiet();
//                    hdct.setSoLuong(1);
//                    hdct.setTinhTrang(0);
//                    hdct.setDonGia(listghct.get(a).getDonGiaKhiGiam());
//                    HoaDon hd1 = banHangOnlineService.timhdtheomahd(mhd);
//                    hdct.setHoaDon(hd1);
//                    List<IMEI> listimei = banHangOnlineService.timimeitheoidctspVSttO(listghct.get(a).getChiTietSanPham().getId());
//                    hdct.setImei(listimei.get(0));
//                    hoaDonChiTietService.add(hdct);
//// cập nhật trạng thái imei
//                    IMEI imei = listimei.get(0);
//                    imei.setTinhTrang(3);
//                    imei.setNgayCapNhat(date);
//                    imeiService.add(imei);
//// cập nhật lại số lượng ctsp của imei đó
//                    ChiTietSanPham motctsp = imei.getChiTietSanPham();
//                    motctsp.setSoLuong(motctsp.getSoLuong() - 1);
//                    chiTietSanPhamService.add(motctsp);
//                }
//            }


            //  sao chép sản phẩm trong giỏ hàng chi tiết khi đặt hàng tt=0
            List<GioHangChiTiet> listghct = banHangOnlineService.ListghTheoidghvsTT1(idgh1);
            for (GioHangChiTiet ghct1 : listghct) {
                DonDatHang ddh = new DonDatHang();
                ddh.setHoaDon(banHangOnlineService.timhdtheomahd(mhd));
                ddh.setChiTietSanPham(ghct1.getChiTietSanPham());
                ddh.setTinhTrang(ghct1.getTinhTrang());
                ddh.setSoLuong(ghct1.getSoLuong());
                ddh.setDonGia(ghct1.getDonGia());
                ddh.setDonGiaKhiGiam(ghct1.getDonGiaKhiGiam());
                donDatHangRepository.save(ddh);
            }
//xoa ghct TT=0 theo idgh
            banHangOnlineService.xoaghcttheoIDGHvsTTO(idgh1);
//cập nhật hóa đơn; thanh toán khi thanh toán online
            HoaDon hd1 = banHangOnlineService.timhdtheomahd(mhd);
            hd1.setNgayThanhToan(date);
            hd1.setNgayCapNhat(date);
            hd1.setTinhTrang(2);
            hd1.setLoai(1);
            hd1.setHinhThucThanhToan(1);
            hd1.setHinhThucThanhToan(1);
            hd1.setTinhTrangGiaoHang(0);
            hoaDonService.add(hd1);
//hoadonchitiet
//            List<HoaDonChiTiet> hdct = banHangOnlineService.timhoadonchitiettheoidhd(hd1.getId());
//            for (HoaDonChiTiet hdct1 : hdct
//            ) {
//                hdct1.setTinhTrang(1);
//                hoaDonChiTietService.add(hdct1);
//            }
//imei
//            List<HoaDonChiTiet> hdctchoimei = banHangOnlineService.timhoadonchitiettheoidhd(hd1.getId());
//
//            for (HoaDonChiTiet hdct2 : hdctchoimei
//            ) {
//                IMEI imei1 = hdct2.getImei();
//                imei1.setTinhTrang(1);
//                imeiService.add(imei1);
//            }


            model.addAttribute("ketquathanhtoan", 1);
            return "ban-hang-online/ket_qua_thanh_toan";
        } else {
            // Giao dịch thất bại
            // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\

            model.addAttribute("ketquathanhtoan", 0);
            return "ban-hang-online/ket_qua_thanh_toan";

        }


    }


    @GetMapping("/ban-hang-online/hoa-don-online/{id}/{tinhtrang}/{thongbao}")
    public String hoadononline(
            Model model,
            @PathVariable("id") UUID idkh,
            @PathVariable("tinhtrang") String tinhtrang,
            @PathVariable("thongbao") String thongbao

    ) {

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkh)).get(0).getId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("ngayHienTai", LocalDate.now());
        System.out.println(LocalDate.now());

        List<String> checkList = new ArrayList<>();
        List<String> checkList1 = new ArrayList<>();
        List<String> checkList2 = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (HoaDon hd : banHangOnlineService.timhoadontheoidkh(idkh)) {
            LocalDate ngayNhan = hd.getNgayNhan() != null ? hd.getNgayNhan().toLocalDate() : null;

            if (ngayNhan != null) {
                if (ngayNhan.isAfter(currentDate.minusDays(7)) || ngayNhan.isEqual(currentDate)) {
                    checkList.add(hd.getMa());
                } else {
                    checkList1.add(hd.getMa());
                }
            } else {
                checkList2.add(hd.getMa());
            }
        }


        model.addAttribute("checkList", checkList);
        model.addAttribute("checkList1", checkList1);
        model.addAttribute("checkList2", checkList2);
        System.out.println("ngay<ngayhientai" + checkList1);
        System.out.println("thoa  man" + checkList);


        List<String> checkHDlist0 = new ArrayList<>();
        List<String> checkHDlist1 = new ArrayList<>();
        List<String> checkHDlist2 = new ArrayList<>();
        List<String> checkHDlist3 = new ArrayList<>();
        List<String> checkHDlist6 = new ArrayList<>();
        List<HoaDon> listHoaDon = hoaDonService.hoaDonKH(idkh);
        for (HoaDon hdCheck : banHangOnlineService.timhoadontheoidkh(idkh)) {
            // Kiểm tra xem có đối trả liên quan đến hóa đơn không
            DoiTra doiTra = doiTraService.getDoiTraByHoaDon(hdCheck.getId());


            if (doiTra != null && doiTra.getHoaDon() != null && doiTra.getHoaDon().getId().equals(hdCheck.getId()) && doiTra.getTinhTrang() == 0) {
                checkHDlist0.add(hdCheck.getMa()); // Hóa đơn có liên quan đến đối trả
            } else if (doiTra != null && doiTra.getHoaDon() != null && doiTra.getHoaDon().getId().equals(hdCheck.getId()) && doiTra.getTinhTrang() == 1) {
                checkHDlist1.add(hdCheck.getMa());  // Hóa đơn không có liên quan đến đối trả
            } else if (doiTra != null && doiTra.getHoaDon() != null && doiTra.getHoaDon().getId().equals(hdCheck.getId()) && doiTra.getTinhTrang() == 2) {
                checkHDlist2.add(hdCheck.getMa());
            } else if (doiTra == null) {
                checkHDlist3.add(hdCheck.getMa());
            }


        }
        System.out.println("lisst0" + checkHDlist0);

        model.addAttribute("checkHDlist0", checkHDlist0);
        model.addAttribute("checkHDlist1", checkHDlist1);
        model.addAttribute("checkHDlist2", checkHDlist2);
        model.addAttribute("checkHDlist3", checkHDlist3);
        System.out.println(checkHDlist3);


        // Truyền giá trị ngày hiện tại tới form JSP

        model.addAttribute("contentPage", "../ban-hang-online/trang-hoa-don-khach-hang.jsp");

        if (tinhtrang.equals("full")) {
            model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(idkh));
            model.addAttribute("mau", "full");
        } else if (tinhtrang.equals("0")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHang0(idkh));
            model.addAttribute("mau", "0");
        } else if (tinhtrang.equals("1")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHang1(idkh));
            model.addAttribute("mau", "1");
        } else if (tinhtrang.equals("3")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHang3(idkh));
            model.addAttribute("mau", "3");
        } else if (tinhtrang.equals("2")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHang2(idkh));
            model.addAttribute("mau", "2");
        } else if (tinhtrang.equals("danggiao")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHangDangGiao(idkh));
            model.addAttribute("mau", "danggiao");
        } else if (tinhtrang.equals("thanhcong")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHangThanhCong(idkh));
            model.addAttribute("mau", "thanhcong");
        } else if (tinhtrang.equals("8")) {
            model.addAttribute("listhdkh", banHangOnLinerepository.donHang8(idkh));
            model.addAttribute("mau", "8");
        }


        if (thongbao.equals("huy")) {
            model.addAttribute("hientbhuy", 0);
            model.addAttribute("ndtb", "Hủy hóa đơn thành công");
        } else if (thongbao.equals("huydoitra")) {
            model.addAttribute("hientbhuy", 0);
            model.addAttribute("ndtb", "Hủy đổi trả thành công");
        } else if (thongbao.equals("loadtrang")) {
            model.addAttribute("hientbhuy", 0);
            model.addAttribute("ndtb", "Trạng thái hóa đơn đã thay đổi, load trang để lấy trạng thái mới nhất");
        }


//        model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(idkh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }

        return "ban-hang-online/trang_hoa_don_khach_hang";
    }


    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/{idhd}")
    public String nutxemchitiethoadon(
            Model model,
            @PathVariable("idhd") UUID idhd
    ) {
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        List<HoaDonChiTiet> hoaDonChiTiet = banHangOnlineService.timhoadonchitiettheoidhd(idhd);
        DoiTra doiTra = doiTraService.getDoiTraByHoaDon(idhd);
        if (doiTra != null) {
            // Thực hiện các thao tác với doiTra
            List<DoiTraChiTiet> doiTraChiTiet = doiTraChiTietService.getDoiTraChiTietByDoiTraId(doiTra.getId());
            model.addAttribute("listdtct", doiTraChiTiet);
            System.out.println("doi tra: " + doiTra);
        } else {
            // Xử lý khi doiTra là null
            System.out.println("Không tìm thấy đối tượng DoiTra cho idhd: " + idhd);
        }


        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";
    }

    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/huy-hoa-don/{idhd}/{tinhtrang}/{thongbao}")
    public String huyhoadon(

            @PathVariable("idhd") UUID idhd,
            @PathVariable("tinhtrang") String tinhtrang,
            @PathVariable("thongbao") String thongbao

    ) {
        if (hoaDonService.findById(idhd).getTinhTrang() == 3) {
            if (hoaDonService.findById(idhd).getTinhTrangGiaoHang() == 0 || hoaDonService.findById(idhd).getTinhTrangGiaoHang() == 1) {
                banHangOnlineService.huyhoadon(idhd);
                banHangOnLinerepository.updateTTdonDatHangkhiDASULytheoIDHD(idhd);
                return "redirect:/ban-hang-online/hoa-don-online/" + idkhachhang + "/" + tinhtrang + "/" + thongbao;
            } else {

                return "redirect:/ban-hang-online/hoa-don-online/" + idkhachhang + "/ full/loadtrang";


            }
        } else {
            return "redirect:/ban-hang-online/hoa-don-online/" + idkhachhang + "/full/loadtrang";
        }

//        for (HoaDonChiTiet hdct : banHangOnLinerepository.timhoadonchitiettheoidhd(idhd)) {
//            ChiTietSanPham motctsp = hdct.getImei().getChiTietSanPham();
//            motctsp.setSoLuong(motctsp.getSoLuong() + 1);
//            chiTietSanPhamService.add(motctsp);
//        }


//        return "redirect:/ban-hang-online/hoa-don-online/" + idkhachhang + "/" + tinhtrang + "/" + thongbao;

    }

    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/xoa-chi-tiet-san-pham/{idhdct}")
    public String xoahdct(
            Model model,
            @PathVariable("idhdct") UUID idhdct
    ) {
        System.out.println("idhdct===" + idhdct);
        model.addAttribute("hd", hoaDonChiTietService.findById(idhdct).getHoaDon());
        UUID idhd = hoaDonChiTietService.findById(idhdct).getHoaDon().getId();
        UUID idctsp = hoaDonChiTietService.findById(idhdct).getImei().getChiTietSanPham().getId();
        banHangOnlineService.updateimeiTTveOtheoIDHDvsIDCTSP(idhd, idctsp);
        banHangOnlineService.XoahdcttheoIDHDvsIDCTSP(idhd, idctsp);
        Integer tongtien = 0;
        for (HoaDonChiTiet hdct : banHangOnlineService.timhoadonchitiettheoidhd(idhd)) {
            Integer tien1ctsp = Integer.valueOf(String.valueOf(hdct.getDonGia()));
            tongtien = tongtien + tien1ctsp;
        }
        HoaDon hd = hoaDonService.findById(idhd);
        hd.setTongTien(BigDecimal.valueOf(Long.valueOf(String.valueOf(tongtien))));
        hoaDonService.add(hd);
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        return "ban-hang-online/single_pase_trang_xem_hoa_don_chi-tiet";
    }


    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/thanh-toan-khi-nhan-hang/{idhd}")
    public String ttknh(
            Model model,
            @PathVariable("idhd") UUID idhd
    ) {
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        HoaDon hd1 = hoaDonService.findById(idhd);
        hd1.setTinhTrang(3);
        hd1.setHinhThucThanhToan(0);
        hoaDonService.add(hd1);
        model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(hoaDonService.findById(idhd).getKhachHang().getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang_hoa_don_khach_hang";

    }


    @PostMapping("/ban-hang-online/hoa-don-chi-tiet/them-dia-chi")
    public String nutthemdiachihdct(
            Model model,
            @RequestParam("diachi") String diachi1, @RequestParam("quan") String quan,
            @RequestParam("huyen") String huyen, @RequestParam("thanhpho") String thanhpho,
            @RequestParam("idhd") UUID idhd
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi diaChi = new DiaChi();
        Integer sl = diaChiService.findAll().size();
        String mhd = "";
        if (sl < 10) {
            mhd = "MDC0" + sl;
        } else {
            mhd = "MDC" + sl;
        }
        diaChi.setMa(mhd);
        diaChi.setNgayTao(date);
        diaChi.setNgayCapNhat(date);
        diaChi.setTinhTrang(0);
        diaChi.setDiaChi(diachi1);
        diaChi.setQuan(quan);
        diaChi.setHuyen(huyen);
        diaChi.setThanhPho(thanhpho);
        KhachHang kh = hoaDonService.findById(idhd).getKhachHang();
        diaChi.setKhachHang(kh);
        diaChiService.add(diaChi);

        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 1);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";

    }


    @PostMapping("/ban-hang-online/hoa-don-chi-tiet/cap-nhat-thong-tin-dat-hang")
    public String capnhatthongtindathang(
            Model model,
            @RequestParam("sodienthoai") String sodienthoai,
            @RequestParam("diachi") UUID diachi,
            @RequestParam("nguoinhan") String nguoinhan,
            @RequestParam("idhd") UUID idhd
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        HoaDon hd = hoaDonService.findById(idhd);
        if (diaChiService.findById(diachi).getThanhPho().equals("Thành phố Hà Nội")) {
            if (hd.getTinhTrangGiaoHang() == 0) {
                hd.setSdt(sodienthoai);
                hd.setNguoiNhan(nguoinhan);
                hd.setDiaChi(diaChiService.findById(diachi));
                hoaDonService.add(hd);
                model.addAttribute("thongbaotinhtranggiaohang", 0);
            } else {
//            bat
                model.addAttribute("thongbaotinhtranggiaohang", 1);
            }
        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

            model.addAttribute("hd", hoaDonService.findById(idhd));
            model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
            model.addAttribute("banhangonline", banHangOnlineService);
            System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
            if (idkhachhang.equals("1")) {
                model.addAttribute("idkhachhang", idkhachhang);
            } else {
                model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
                model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
            }
            model.addAttribute("hienmodal", 0);
            model.addAttribute("batmodalcapnhathdctkh", 0);
            model.addAttribute("tbdiachihanoi", "Chỉ ship ở :Thành Phố Hà Nội");
            return "ban-hang-online/trang_xem_hoa_don_chi_tiet";

        }


        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";

    }

    @GetMapping("/ban-hang-online/xem-gio-hang")
    public String nutxemgiohang(
            Model model
    ) {

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }


        return "ban-hang-online/trang-gio-hang-chi-tiet";
    }


    @GetMapping("/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/{idghct}/{trangthai}/{idgh}")
    public String chonspghctTT(
            Model model,

            @PathVariable("idghct") String idghct,
            @PathVariable("trangthai") String trangthai,
            @PathVariable("idgh") UUID idgh
    ) {
        if (idghct.equals("full")) {
            if (trangthai.equals("0")) {
                banHangOnlineService.trangthaighct(0, idgh);
                model.addAttribute("tttong", 0);
            } else {
                banHangOnlineService.trangthaighct(1, idgh);
                model.addAttribute("tttong", 1);
            }
        } else {
            GioHangChiTiet ghct = gioHangChiTietService.findById(UUID.fromString(idghct));
            if (trangthai.equals("0")) {
                ghct.setTinhTrang(0);
                gioHangChiTietService.add(ghct);
            } else {
                ghct.setTinhTrang(1);
                gioHangChiTietService.add(ghct);
            }
        }

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }


    @GetMapping("/ban-hang-online/them-san-pham-vao-gio-hang/{idctsp}/{solg}")
    public String themvaogiohang(
            Model model,
            @PathVariable("idctsp") UUID idctsp,
            @PathVariable("solg") Integer soluong
    ) {
//        if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId(), idctsp).size() <= 0) {
//            GioHangChiTiet ghct = new GioHangChiTiet();
//            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0));
//            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
//            ghct.setSoLuong(1);
//            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
//            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
//            Long giaban1 = Long.valueOf(String.valueOf(giaban));
//            Long phantramgiam = Long.valueOf(banHangOnlineService.tonggiamgia(String.valueOf(idctsp)));
//            Long dgkg = giaban1 - giaban1 / 100 * phantramgiam;
//            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
//            ghct.setDonGiaKhiGiam(dgkg1);
//            gioHangChiTietService.add(ghct);
//        }

                if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId(), idctsp).size() > 0) {
            GioHangChiTiet ghctud = banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId(), idctsp).get(0);
            Integer slud = ghctud.getSoLuong() + soluong;
            ghctud.setSoLuong(slud);
            gioHangChiTietService.add(ghctud);
        } else {
            GioHangChiTiet ghct = new GioHangChiTiet();
            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0));
            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
            ghct.setSoLuong(soluong);
            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
            Integer phantramgiam = banHangOnlineService.tonggiamgia(String.valueOf(idctsp));
            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
            ghct.setDonGiaKhiGiam(dgkg1);
            gioHangChiTietService.add(ghct);
        }
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";

    }


    @GetMapping("/ban-hang-online/single_page_gio_hang_chi_tiet")
    public String nutxemgiohangdongbo(
            Model model
    ) {

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }

        System.out.println("**************************");

        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-chu/load-gio-hang-trang-chu/{idgh}")
    public String loadghctTT(
            Model model,
            @PathVariable("idgh") UUID idgh
    ) {


        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }

    @GetMapping("/ban-hang-online/trang-chu/load-gio-hang-trang-chu-idghct/{idghct}")
    public String loadghctTTtuidghct(
            Model model,
            @PathVariable("idghct") UUID idghct
    ) {


        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(gioHangChiTietService.findById(idghct).getGioHang().getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }


    //THÔNG TIN VỀ CHÍNH SÁCH ĐỔI TRẢ, LINK NÀY DẪN SANG CHÍNH SÁCH ĐỔI TRẢ
    @GetMapping("/ban-hang-online/chinh-sach-doi-tra")
    public String chinhSachDoiTra(
            Model model
    ) {
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);


        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());


//        giohang
        model.addAttribute("tttong", 1);
        if (idkhachhang.equals("1")) {
            return "redirect:/";
        } else {
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            return "doitra/chinh-sach-doi-tra";
        }

    }

    @GetMapping("/detail-test/{id}")
    @ResponseBody
    public List<HoaDonChiTiet> detail(@PathVariable("id") UUID id) {
        List<HoaDonChiTiet> hdct = doiTraService.getHoaDonChiTiet(id);
//        List<DoiTra> doiTra1 = doiTraService.findAll();
//
//        boolean found = false;
//
//        for (DoiTra doiTra2 : doiTra1) {
//            if (doiTra2.getHoaDon().getId().equals(id)) {
//                found = true;
//                System.out.println("Khong hop le");hu
//                break;  // Nếu tìm thấy ít nhất một bản ghi, thoát khỏi vòng lặp
//            }
//        }
//
//// Nếu không tìm thấy DoiTra nào có HoaDon có id giống với id, thêm mới
//        if (!found) {
//            HoaDon hoaDon = hoaDonService.findById(id);
//            DoiTra doiTra = new DoiTra();
//            doiTra.setMa("DT" + String.valueOf(doiTraService.findAll().size() + 1));
//            doiTra.setNgayTao(Date.valueOf(LocalDate.now()));
//            doiTra.setHoaDon(hoaDon);
//            doiTra.setKhachHang(hoaDon.getKhachHang());
//            doiTra.setTinhTrang(0);
//            doiTraService.add(doiTra);
//        }
        return hdct;
    }


    @GetMapping("/doi-tra-khachhang")
    public String doitra(Model model, @RequestParam List<UUID> hdctIds, @RequestParam UUID hoadonId) {
        HoaDon hoaDon = hoaDonService.findById(hoadonId);

        List<DoiTraChiTiet> doiTraChiTiets = new ArrayList<>();
        List<DoiTraChiTiet> list = doiTraChiTietService.getAll();
        // Lặp qua danh sách hdctIds để tạo và thiết lập thông tin đối trả chi tiết
        for (UUID hdctId : hdctIds) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findById(hdctId);
            DoiTraChiTiet doiTraChiTietss = doiTraChiTietService.findByHDCT(hdctId);
            if (doiTraChiTietss == null) {
                DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
                doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
                doiTraChiTiet.setDoiTra(doiTraService.getDoiTraByHoaDon(hoadonId));
                doiTraChiTiets.add(doiTraChiTiet);
            }
        }

        // Lưu danh sách đối trả chi tiết vào cơ sở dữ liệu
        doiTraChiTietService.saveAll(doiTraChiTiets);
        return "ban-hang-online/trang_hoa_don_khach_hang";
    }

    @GetMapping("/them-dtctne")
    public String updatethemhdct(Model model, @ModelAttribute("dulieuxem") DoiTraChiTiet dulieuxem,
                                 @RequestParam UUID doitraId, @RequestParam UUID hdctId, @RequestParam String lyDo,
                                 @RequestParam int hienTrang,
                                 @RequestParam int hinhThuc, HttpServletRequest request) {

        System.out.println("hdct id đây này ok" + hdctId);
        DoiTra doiTra = doiTraService.findById(doitraId);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findById(hdctId);

        // Kiểm tra xem HoaDonChiTiet đã tồn tại trong DoiTraChiTiet chưa
        if (!doiTraChiTietService.existsByDoiTraAndHoaDonChiTiet(doiTra, hoaDonChiTiet)) {
            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
            doiTraChiTiet.setTinhTrang(0);
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            doiTraChiTiet.setDoiTra(doiTra);
            doiTraChiTiet.setHienTrangSanPham(hienTrang);
            doiTraChiTiet.setHinhThucDoiTra(hinhThuc);
            doiTraChiTiet.setLyDo(lyDo);

            doiTraChiTietService.add(doiTraChiTiet);
        }

        return "ban-hang-online/trang-doi-tra";
    }

    @GetMapping("/xac-nhan-khachhang")
    public String xacnhankh(Model model, @RequestParam("hdctIds[]") List<UUID> hdctIds,
                            @RequestParam("hoadonId") UUID hoadonId,
//                            @RequestParam("hinhThucList[]") List<String> hinhThucList,
                            @RequestParam("ghiChuList[]") List<String> ghiChuList) {
        // Check if any data is selected
        if (hdctIds.isEmpty()) {
            // No data selected, handle accordingly
            return "/ban-hang-online/trang_hoa_don_khach_hang";


        }
        HoaDon hoaDon = hoaDonService.findById(hoadonId);
        DoiTra doiTra = new DoiTra();
        doiTra.setMa("DT" + String.valueOf(doiTraService.findAll().size() + 1));
        doiTra.setNgayTao(Date.valueOf(LocalDate.now()));
        doiTra.setHoaDon(hoaDon);
        doiTra.setKhachHang(hoaDon.getKhachHang());
        doiTra.setTinhTrang(0);
        doiTraService.add(doiTra);
        List<DoiTraChiTiet> doiTraChiTiets = new ArrayList<>();
        for (int i = 0; i < hdctIds.size(); i++) {
            UUID hdctId = hdctIds.get(i);
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findById(hdctId);
            DoiTraChiTiet doiTraChiTietss = doiTraChiTietService.findByHDCT(hdctId);

            // Cập nhật thông tin ghi chú và hình thức đổi trả


            doiTraChiTietss.setHinhThucDoiTra(0);
            doiTraChiTietss.setLyDo(ghiChuList.get(i));
            doiTraChiTietss.setDoiTra(doiTra);
            doiTraChiTiets.add(doiTraChiTietss);
        }

        // Lưu danh sách đối trả chi tiết vào cơ sở dữ liệu
        doiTraChiTietService.saveAll(doiTraChiTiets);
//        DoiTra doiTra=doiTraService.getDoiTraByHoaDon(hoadonId);
        doiTra.setTinhTrang(0);
        doiTraService.update(doiTra.getId(), doiTra);

        return "ban-hang-online/trang_hoa_don_khach_hang";
    }

    @PostMapping("/ban-hang-online/trang-chu/tim-kiem")
    public String trangchutimkiem(
            Model model,
            @RequestParam("trangchutimkiem") String trangchutimkiem
    ) {
        System.out.println("-----" + banHangOnlineService.timkiemTrangChu(trangchutimkiem).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//
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
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.timkiemTrangChu(trangchutimkiem)) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.timkiemTrangChu(trangchutimkiem));
        //gio hang
        if (idkhachhang.equals("1")) {

        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        }
        model.addAttribute("tttong", 1);
//        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));


        return "ban-hang-online/trang-loc-sanpham";


    }

    @GetMapping("/ban-hang-online/trang-chu/tim-kiem-loc/{trangchutimkiem}")
    public String loctrangchutimkiem(
            Model model,
            @PathVariable("trangchutimkiem") String trangchutimkiem
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("-------" + trangchutimkiem);
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        for (ChiTietSanPham ct : banHangOnlineService.timkiemTrangChu(trangchutimkiem)
        ) {
            if (banHangOnLinerepository.soluongcon(ct.getId()) > 0) {
                listCTSP.add(ct);
            }
        }
        model.addAttribute("listsp", listCTSP);


        return "ban-hang-online/singfle_pase_tim_kiem_trang_chu";
    }

    @GetMapping("/ban-hang-online/huy/{hoadonId}")
    public String huy(Model model, @ModelAttribute("dulieuxem") DoiTra dulieuxem,
                      @PathVariable UUID hoadonId, HttpServletRequest request
    ) {
        DoiTra doiTra = doiTraService.getDoiTraByHoaDon(hoadonId);
        System.out.println("Doi tra" + doiTra);
        List<DoiTraChiTiet> listCHiTietDoiTra = doiTraChiTietService.getDoiTraChiTietByDoiTraId(doiTra.getId());
        for (DoiTraChiTiet dtct : listCHiTietDoiTra
        ) {
            if (dtct.getHienTrangSanPham() == 0) {
                if (dtct.getImei() != null) {
                    IMEI imei1 = imeiService.findById(dtct.getImei().getId());
                    imei1.setTinhTrang(0);
                    imeiService.update(dtct.getImei().getId(), imei1);
                }
                dtct.setTinhTrang(1);
                doiTraChiTietService.update(dtct.getId(), dtct);

            } else if (dtct.getHienTrangSanPham() == 1) {

                if (dtct.getImei() != null) {
                    IMEI imei1 = imeiService.findById(dtct.getImei().getId());
                    imei1.setTinhTrang(0);
                    imeiService.update(dtct.getImei().getId(), imei1);
                }
                dtct.setTinhTrang(1);
                doiTraChiTietService.update(dtct.getId(), dtct);
            }
        }
        doiTra.setTinhTrang(1);
        doiTra.setKhachHang(khachHangService.findById(UUID.fromString(idkhachhang)));
        doiTraService.update(doiTra.getId(), doiTra);


        return "redirect:/ban-hang-online/hoa-don-online/" + doiTra.getHoaDon().getKhachHang().getId() + "/full/" + "huydoitra";


//       "/ban-hang-online/hoa-don-online/{idkh}/{tinhtranghoadon}/{thongbaonhunao}"
    }

}


