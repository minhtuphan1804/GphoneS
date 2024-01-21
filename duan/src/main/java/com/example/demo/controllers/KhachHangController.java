package com.example.demo.controllers;

import com.example.demo.models.GioHang;
import com.example.demo.models.HangKhachHang;
import com.example.demo.models.KhachHang;
import com.example.demo.services.GioHangService;
import com.example.demo.services.HangKhachHangService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.MailerService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
//@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    KhachHangService khachHangService;
    @Autowired
    HangKhachHangService hangKhachHangService;

    @Autowired
    GioHangService gioHangService;
    @Autowired
    MailerService mailer;

    public Integer kt(Integer so){
        if(so==Integer.valueOf(0)){
            return Integer.valueOf(1);
        }
        return so;
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
    @GetMapping("/khach-hang/hien-thi")
    public String hienThi(
            Model model,
            @ModelAttribute("kh") KhachHang khachHang,
            @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        khachHang.setGioiTinh(true);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<KhachHang> list = khachHangService.findAll00();

        model.addAttribute("dulieu", list);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll00().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang.jsp");
        return "home/layout";

    }
    @PostMapping("/khach-hang/tim-kiem")
    public String timkiem(Model model,
                          @RequestParam("matk") String matk,
                          @ModelAttribute("kh") KhachHang khachHang,
                          @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
                          @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        model.addAttribute("dulieu", khachHangService.timkiem(matk));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll00().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang.jsp");

        return "home/layout";
    }


    @PostMapping("/khach-hang/tim-kiem-tung-xoa")
    public String timkiemtungxoa(Model model,
                                 @RequestParam("matk") String matk,
                                 @ModelAttribute("kh") KhachHang khachHang,
                                 @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
                                 @RequestParam("num") Optional<Integer> num,
                                 @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {
        model.addAttribute("dulieu", khachHangService.timkiem1(matk));
        model.addAttribute("hkh", hangKhachHangService.getall1());
        model.addAttribute("tong", khachHangService.findAll11().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang-tungxoa.jsp");
        return "home/layout";
    }

    @GetMapping("/khach-hang/khach-hang-tung-xoa")
    public String hienThiTungXoa(
            Model model,
            @ModelAttribute("kh") KhachHang khachHang,
            @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {

        khachHang.setGioiTinh(true);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<KhachHang> list = khachHangService.findAll11();
        model.addAttribute("dulieu", list);

        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll11().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang-tungxoa.jsp");
        return "home/layout";

    }

    @GetMapping("/khach-hang/remove/{id}")
    public String remove(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("kh") KhachHang khachHang,
                         @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
                         @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        KhachHang khachHang1 = khachHangService.findById(id);
        khachHang1.setTinhTrang(1);
        khachHang1.setNgayCapNhat(date);
        khachHangService.add(khachHang1);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<KhachHang> list = khachHangService.findAll00();
        model.addAttribute("dulieu", list);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll00().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang.jsp");
        return "home/layout";

    }

    @GetMapping("/khach-hang/khoi-phuc/{id}")
    public String khoiphuc(Model model,
                           @PathVariable("id") UUID id,
                           @ModelAttribute("kh") KhachHang khachHang,
                           @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
                           @RequestParam("num") Optional<Integer> num,
                           @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        KhachHang khachHang1 = khachHangService.findById(id);
        khachHang1.setTinhTrang(0);
        khachHang1.setNgayCapNhat(date);
        khachHangService.add(khachHang1);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<KhachHang> list = khachHangService.findAll11();
        model.addAttribute("dulieu", list);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll11().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang-tungxoa.jsp");
        return "home/layout";

    }

    @GetMapping("/khach-hang/khoi-phuc-het")
    public String khoiphuchet(Model model,
                              @ModelAttribute("kh") KhachHang khachHang,
                              @ModelAttribute("hangKhachHang") HangKhachHang hangKhachHang,
                              @RequestParam("num") Optional<Integer> num,
                              @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        List<KhachHang> khachHang1 = khachHangService.findAll11();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        for (KhachHang vt:khachHang1){
            vt.setTinhTrang(0);
            vt.setNgayCapNhat(date);
            khachHangService.add(vt);
        }
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<KhachHang> list = khachHangService.findAll11();
        model.addAttribute("dulieu", list);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll11().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang-tungxoa.jsp");

        return "home/layout";

    }

    @GetMapping("/khach-hang/view-update/{id}")
    public String viewUpdate(Model model,
                             @PathVariable("id") UUID id,
                             @ModelAttribute("kh") KhachHang khachHang,
                             @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang
    ) {
        khachHang.setTinhTrang(0);
        khachHang.setGioiTinh(true);
        model.addAttribute("kh", khachHangService.findById(id));
        model.addAttribute("hkh", hangKhachHangService.getALL0());

        model.addAttribute("contentPage","../khach-hang/khach-hang-update.jsp");
        return "home/layout";
    }
    @PostMapping("/khach-hang/them-hang-khach-hang-update")
    public String themhkhupdate(Model model,
                                @RequestParam("idkh") UUID id,
                                @ModelAttribute("kh") KhachHang khachHang,
                                @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang

    ) {
        Integer sl = hangKhachHangService.findAll().size() + 1;
        String mhd="";
        if(sl<10){
            mhd = "MHKH0" + sl;
        }else {
            mhd = "MHKH" + sl;
        }

        hangKhachHang.setMa(mhd);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hangKhachHang.setNgayCapNhat(date);

        hangKhachHangService.add(hangKhachHang);
        model.addAttribute("kh", khachHangService.findById(id));
        model.addAttribute("hkh", hangKhachHangService.getALL0());

        model.addAttribute("KHHangKhachHang",new HangKhachHang());
        model.addAttribute("contentPage","../khach-hang/khach-hang-update.jsp");
        return "home/layout";
    }

    @PostMapping("/khach-hang/them-hang-khach-hang-add")
    public String themhkhuadd(Model model,
                              @ModelAttribute("kh") KhachHang khachHang,
                              @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang
    ) {
        khachHang.setTinhTrang(0);
        khachHang.setGioiTinh(true);
        Integer sl = hangKhachHangService.findAll().size() + 1;
        String mhd="";
        if(sl<10){
            mhd = "MHKH0" + sl;
        }else {
            mhd = "MHKH" + sl;
        }
        hangKhachHang.setMa(mhd);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHangService.add(hangKhachHang);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("KHHangKhachHang",new HangKhachHang());
        model.addAttribute("contentPage","../khach-hang/khach-hang-add.jsp");
        return "home/layout";
    }

    @GetMapping("/khach-hang/view-detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("kh") KhachHang khachHang,
                         @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        khachHang.setTinhTrang(0);
        khachHang.setGioiTinh(true);
        model.addAttribute("kh", khachHangService.findById(id));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tthdkh",khachHangService.getHD());
        Sort sort = Sort.by("getmahd()").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size);
        Page<KhachHangLSMuaHang> list = khachHangService.getLSMuaHangPage(pageable,id);
        model.addAttribute("lsmuahang", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage","../khach-hang/khach-hang-lsmuahang.jsp");
        return "home/layout";
    }

    @GetMapping("/khach-hang/view-add")

    public String viewadd(Model model,
                          @ModelAttribute("kh") KhachHang khachHang,
                          @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang
    ) {
        khachHang.setTinhTrang(0);
        khachHang.setGioiTinh(true);
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("contentPage","../khach-hang/khach-hang-add.jsp");
        return "home/layout";
    }

    @PostMapping("/khach-hang/update")
    public String updateDongSP(Model model, @ModelAttribute("kh") @Valid KhachHang khachHang,
                               BindingResult bindingResult,
                               @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang,
                               @RequestParam("checkanh") String checkanh,
                               @RequestParam("images") MultipartFile multipartFile,
                               @RequestParam("num") Optional<Integer> num,
                               @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            khachHang = khachHangService.findById(khachHang.getId());
            model.addAttribute("hkh", hangKhachHangService.getALL0());
            model.addAttribute("contentPage","../khach-hang/khach-hang-update.jsp");
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang.setNgayCapNhat(date);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(checkanh.equals("cu")){
        }else {
            if(fileName.equals("")){
            }else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
            khachHang.setAnh(fileName);
        }
        khachHangService.add(khachHang);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhachHang> list = khachHangService.getALL0(pageable);
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll00().size());
        model.addAttribute("contentPage","../khach-hang/khach-hang.jsp");
        return "home/layout";
    }

    @PostMapping("/khach-hang/add")
    public String updateadd(Model model,
                            @ModelAttribute("kh") @Valid KhachHang khachHang,
                            BindingResult bindingResult,
                            @ModelAttribute("KHHangKhachHang") HangKhachHang hangKhachHang,
                            @RequestParam("images") MultipartFile multipartFile,
                            @RequestParam("num") Optional<Integer> num,
                            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) throws IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            model.addAttribute("hkh", hangKhachHangService.getALL0());
            khachHang.setGioiTinh(true);
            model.addAttribute("dulieu", khachHangService.findAll00());
            model.addAttribute("hkh", hangKhachHangService.getALL0());
            model.addAttribute("tong", khachHangService.findAll00().size());
            model.addAttribute("contentPage","../khach-hang/khach-hang-add.jsp");
            return "home/layout";
        }
        KhachHang khh=new KhachHang();
        String randomPW=generateRandomPassword(8);
        khh.setMatKhau(randomPW);
        String hashedPassword = BCrypt.hashpw(randomPW, BCrypt.gensalt());
        khachHang.setMatKhau(hashedPassword);

        String mhd="";
        Integer sl = khachHangService.findAll().size() + 1;
        if(sl<9){
            mhd = "MKH0" + sl;
        }else {
            mhd = "MKH" + sl;
        }
        khachHang.setMa(mhd);
        khachHang.setNgayTao(date);
        khachHang.setNgayCapNhat(date);
        khachHang.setTinhTrang(0);

//FileUploadUtil
        System.out.println("taco---"+multipartFile);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("taco1---"+fileName);
        if(fileName.equals("")){

        }else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        khachHang.setAnh(fileName);
        khachHangService.add(khachHang);
        //      them gh
        khachHangService.findAll();
        String mghkh="";
        Integer slgh = gioHangService.findAll().size();
        if(slgh<10){
            mghkh = "MGH0" + slgh;
        }else {
            mghkh = "MGH" + slgh;
        }
        GioHang ghkh=new GioHang();
        ghkh.setMa(mghkh);
        for (KhachHang kh11: khachHangService.findAll()){
            if(kh11.getMa().equals(mhd)){
                ghkh.setKhachHang(kh11);
                break;
            }
        }
        gioHangService.add(ghkh);
// het them gh
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhachHang> list = khachHangService.getALL0(pageable);
        KhachHang khachHang1=khachHangService.findById(khachHang.getId());
        mailer.queue(khachHang1.getEmail(), "Bạn đã đăng kí tài khoản thành công", "TK: " + khachHang1.getTaiKhoan() + "\nMK: " + khh.getMatKhau());
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("hkh", hangKhachHangService.getALL0());
        model.addAttribute("tong", khachHangService.findAll00().size());

        model.addAttribute("contentPage","../khach-hang/khach-hang.jsp");

        return "home/layout";
    }

}