package com.example.demo.controllers;

import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.models.*;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.*;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.security.SecureRandom;
@Controller
public class profileController {
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
    private MailerService mailer;
    @Autowired
    private GioHangChiTietService gioHangChiTietService ;
    @Autowired
    private GioHangService gioHangService ;
    @Autowired
    private DataIntermediateService dataService;
    @Autowired
    private KhachHangRepository khachHangRepository;



    // Bắt đầu bán hàng
    public Integer kt(Integer so){
        if(so==Integer.valueOf(0)){
            return Integer.valueOf(1);
        }
        return so;
    }


    private String idkhachhang="1";

    @PostMapping("/thong-tin-ca-nhan-khach-hang")
    public String profile(
            Model model,
            @RequestParam("idKhachHang") UUID idkh,
            @ModelAttribute("kh") KhachHang khachHang

    ){
         idkhachhang = String.valueOf(khachHangService.findById(idkh).getId());
        return "redirect:/thong-tin-ca-nhan-khach-hang";
    }

    @GetMapping("/thong-tin-ca-nhan-khach-hang")
    public String profileHT(
            Model model,

            @ModelAttribute("kh") KhachHang khachHang

    ){
        khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",idkhachhang);
        model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong",1);
        return "thong-tin/profile-khach-hang";
    }

    @PostMapping("/update-thong-tin-ca-nhan-khach-hang")
    public String UpdateProfile(
            Model model,
            @ModelAttribute("kh") @Valid KhachHang khachHang,
            BindingResult bindingResult,
           @RequestParam("checkanh") String checkanh,
            @RequestParam("images") MultipartFile multipartFile
    ) throws IOException{

        if (bindingResult.hasErrors()) {
            khachHang =khachHangService.findById(UUID.fromString(idkhachhang));

            double tong=0;
            Integer lamchon=0;
            for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
                if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                    tong=tong+1;
                    lamchon=lamchon+1;
                }
            }
            double tb=tong/3;
            lamchon=lamchon/3;
            if(tb % 1 >0){
                lamchon=lamchon+1;
            }
            model.addAttribute("lamchon",lamchon);
            model.addAttribute("giamgia",banHangOnlineService);
            model.addAttribute("banhangonline",banHangOnlineService);

            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang",idkhachhang);

            model.addAttribute("hkh", hangKhachHangService.getALL0());


//        giohang
            model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong",1);
            return "thong-tin/profile-khach-hang";

        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang.setNgayCapNhat(date);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(fileName.equals("")){

        }else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        khachHang.setAnh(fileName);
        khachHangService.add(khachHang);

//        --------------ve trang caaan thiet


        return "redirect:/thong-tin-ca-nhan-khach-hang";
//        return "redirect:/thong-tin-ca-nhan-khach-hang";
    }



    @GetMapping("/pass-khach-hang")
    public String DoiPass(
            Model model,

            @ModelAttribute("kh") KhachHang khachHang
    ){
        khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",idkhachhang);
        model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("hkh", hangKhachHangService.getALL0());

//        giohang
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong",1);
        return "thong-tin/doi-mat-khau-khach-hang";
    }


    @PostMapping("/update-mat-khau-khach-hang")
    public String DoiPassKH(
            Model model,
            @RequestParam("mat-khau-cu") String oldp,
            @RequestParam("mat-khau-moi") String newp,
            @RequestParam("xac-nhan-mat-khau") String conp,
            @ModelAttribute("kh") @Valid KhachHang khachHang,

            BindingResult bindingResult

    ) throws IOException {

        khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",idkhachhang);
        model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("hkh", hangKhachHangService.getALL0());

//        giohang
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong",1);

        boolean matches = BCrypt.checkpw(oldp, khachHang.getMatKhau());

        if (!matches || !newp.equalsIgnoreCase(conp)) {
            model.addAttribute("thongBao1", "Bạn chưa nhập mật khẩu hoặc mật bạn nhập không đúng!");
            model.addAttribute("thongBao3", "Bạn chưa nhập mật khẩu mật khẩu mới hoặc chưa khớp với mật khẩu xác nhận");

            khachHang =khachHangService.findById(UUID.fromString(idkhachhang));

            model.addAttribute("lamchon",lamchon);
            model.addAttribute("giamgia",banHangOnlineService);
            model.addAttribute("banhangonline",banHangOnlineService);

            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang",idkhachhang);
            model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("hkh", hangKhachHangService.getALL0());

//        giohang
            model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong",1);


            return "thong-tin/doi-mat-khau-khach-hang";
        } else {
            if(newp.trim().isEmpty()){

                model.addAttribute("thongBao3", "Không để trống");

                khachHang =khachHangService.findById(UUID.fromString(idkhachhang));

                model.addAttribute("lamchon",lamchon);
                model.addAttribute("giamgia",banHangOnlineService);
                model.addAttribute("banhangonline",banHangOnlineService);

                model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
                model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
                model.addAttribute("idkhachhang",idkhachhang);
                model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
                model.addAttribute("hkh", hangKhachHangService.getALL0());

//        giohang
                model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
                model.addAttribute("tttong",1);


                return "thong-tin/doi-mat-khau-khach-hang";
            }
            // Đổi mật khẩu thành công

            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            khachHang.setNgayCapNhat(date);
            String matKhau = newp;
            String hashedPassword = BCrypt.hashpw(matKhau, BCrypt.gensalt());
            khachHang.setMatKhau(hashedPassword);

            khachHangService.add(khachHang);

            KhachHang newnv = khachHangService.findById(UUID.fromString(idkhachhang));
            mailer.queue(newnv.getEmail(), "Bạn đã thay đổi mật khẩu thành công", "Tài khoản: " + newnv.getTaiKhoan() + "\nMật khẩu mới: " + matKhau);
            return "redirect:/login";
        }
    }

    @GetMapping("/dia-chi-khach-hang")
    public String diaChi(
            Model model,
            @ModelAttribute("kh") KhachHang khachHang,
            @ModelAttribute("diaChiKH") DiaChi diaChi,
            @ModelAttribute("diaChiKHupdate") DiaChi diaChiupdate

    ){

        List<DiaChi> diaChiList = khachHangService.getAllDiaChiByKhachHangId(UUID.fromString(idkhachhang));
        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("diaChiList", diaChiList); // Danh sách địa chỉ của khách hàng


        khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",idkhachhang);
        model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong",1);
        return "thong-tin/dia-chi-khach-hang";
    }



    @PostMapping("/add-dia-chi-kh")
 public String addDiaChi(Model model,@ModelAttribute("diaChiKH") @Valid DiaChi diaChi,
                         BindingResult bindingResult, //chú ý,bindingResult phải ngay dưới Valid thì mới biết check cái nào
                         @ModelAttribute("diaChiKHupdate") DiaChi diaChiupdate,
                         @ModelAttribute("kh") KhachHang khachHang
                         ){
        KhachHang khachHang1 =khachHangService.findById(UUID.fromString(idkhachhang));
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        if (bindingResult.hasErrors()) {

            List<DiaChi> diaChiList = khachHangService.getAllDiaChiByKhachHangId(UUID.fromString(idkhachhang));
            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("diaChiList", diaChiList); // Danh sách địa chỉ của khách hàng


            khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
            double tong=0;
            Integer lamchon=0;
            for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
                if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                    tong=tong+1;
                    lamchon=lamchon+1;
                }
            }
            double tb=tong/3;
            lamchon=lamchon/3;
            if(tb % 1 >0){
                lamchon=lamchon+1;
            }

            model.addAttribute("lamchon",lamchon);
            model.addAttribute("giamgia",banHangOnlineService);
            model.addAttribute("banhangonline",banHangOnlineService);

            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang",idkhachhang);
            model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
            model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong",1);


            model.addAttribute("batmodal",1);
            return "thong-tin/dia-chi-khach-hang";



        }

        Integer sl = diaChiService.findAll().size() + 1;
        String mhd="";
        if(sl<9){
            mhd = "MDC0" + sl;
        }else {
            mhd = "MDC" + sl;
        }
       diaChi.setMa(mhd);
       diaChi.setNgayTao(date);
       diaChi.setNgayCapNhat(date);
       diaChi.setTinhTrang(0);
       diaChi.setKhachHang(khachHangService.findById(UUID.fromString(idkhachhang)));
        diaChiService.add(diaChi);


           return "redirect:/dia-chi-khach-hang";
    }




    @GetMapping("/view-update-dia-chi-khach-hang/{iddc}")
    public String viewupdatediaChi(
            Model model,
            @ModelAttribute("kh") KhachHang khachHang,
            @ModelAttribute("diaChiKH") DiaChi diaChi,
            @ModelAttribute("diaChiKHupdate") DiaChi diaChiupdate,
            @PathVariable("iddc") UUID iddc

    ){
        model.addAttribute("diaChiKHupdate", diaChiService.findById(iddc)); // Danh sách địa chỉ của khách hàng
        model.addAttribute("batmodalupdate",1); // Danh sách địa chỉ của khách hàng

        List<DiaChi> diaChiList = khachHangService.getAllDiaChiByKhachHangId(UUID.fromString(idkhachhang));
        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("diaChiList", diaChiList); // Danh sách địa chỉ của khách hàng


        khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",idkhachhang);
        model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong",1);
        return "thong-tin/dia-chi-khach-hang";
    }

    @PostMapping("/update-dia-chi-kh")
    public String updateDiaChi(Model model,

                               @ModelAttribute("diaChiKHupdate") @Valid DiaChi diaChiupdate,
                               BindingResult bindingResult,
                               @ModelAttribute("kh") KhachHang khachHang,
                               @ModelAttribute("diaChiKH")  DiaChi diaChi
                              ){
        KhachHang khachHang1 =khachHangService.findById(UUID.fromString(idkhachhang));
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        if (bindingResult.hasErrors()) {

            model.addAttribute("batmodalupdate",1); // Danh sách địa chỉ của khách hàng

            List<DiaChi> diaChiList = khachHangService.getAllDiaChiByKhachHangId(UUID.fromString(idkhachhang));
            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("diaChiList", diaChiList); // Danh sách địa chỉ của khách hàng


            khachHang =khachHangService.findById(UUID.fromString(idkhachhang));
            double tong=0;
            Integer lamchon=0;
            for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
                if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                    tong=tong+1;
                    lamchon=lamchon+1;
                }
            }
            double tb=tong/3;
            lamchon=lamchon/3;
            if(tb % 1 >0){
                lamchon=lamchon+1;
            }

            model.addAttribute("lamchon",lamchon);
            model.addAttribute("giamgia",banHangOnlineService);
            model.addAttribute("banhangonline",banHangOnlineService);

            model.addAttribute("khachhangdangnhap",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang",idkhachhang);
            model.addAttribute("kh",khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
            model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong",1);
            model.addAttribute("diaChiKH",new DiaChi()); // Danh sách địa chỉ của khách hàng

            return "thong-tin/dia-chi-khach-hang";

        }



        diaChiupdate.setNgayCapNhat(date);
        diaChiService.add(diaChiupdate);


        return "redirect:/dia-chi-khach-hang";
    }



    @GetMapping("/xoa-dia-chi-khach-hang/{iddc}")
    public String xoadiachi(
            Model model,
            @ModelAttribute("kh") KhachHang khachHang,
            @ModelAttribute("diaChiKH") DiaChi diaChi,
            @ModelAttribute("diaChiKHupdate") DiaChi diaChiupdate,
            @PathVariable("iddc") UUID iddc

    ){
        DiaChi dc1=diaChiService.findById(iddc);
        dc1.setTinhTrang(1);
      diaChiService.add(dc1);
      return "redirect:/dia-chi-khach-hang";
    }

}
