package com.example.demo.controllers;

import com.example.demo.models.ChucVu;
import com.example.demo.models.NhanVien;
import com.example.demo.services.ChucVuService;
import com.example.demo.services.MailerService;
import com.example.demo.services.NhanVienService;
import com.example.demo.util.FileUploadUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    MailerService mailer;
//    @Autowired
//    private SMSService smsService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        List<NhanVien> page = nhanVienService.getAll();
        model.addAttribute("contentPage", "../nhanvien/nhan-vien.jsp");
        model.addAttribute("listNhanVien", page);
        model.addAttribute("listChucVu", chucVuService.findAll());
        return "home/layout";
    }


    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        List<NhanVien> page = nhanVienService.getAll1();
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-delete.jsp");
        model.addAttribute("listNhanVien", page);
        return "home/layout";
    }

    @GetMapping("/loc")
    public String hienThiLoc(Model model,
                             @RequestParam("ten1") String tenChucVu,
                             @RequestParam("gioiTinh1") String gioiTinh) {
        List<NhanVien> filteredNhanViens = nhanVienService.searchByChucVuAndGioiTinh(tenChucVu, gioiTinh);
        List<ChucVu> listChucVu = chucVuService.findAll();

        // Đặt danh sách chức vụ vào mô hình
        model.addAttribute("listChucVu", listChucVu);
        model.addAttribute("contentPage", "../nhanvien/nhan-vien.jsp");
        model.addAttribute("listNhanVien", filteredNhanViens);
        return "home/layout";
    }


    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien,
                          @ModelAttribute("chucVu") ChucVu chucVu) {

        nhanVien.setTinhTrang(0);
        nhanVien.setGioiTinh(true);
//        nhanVien.setMatKhau(nhanVien.getMatKhau());
        List<ChucVu> listChucVu = chucVuService.findAll();
        model.addAttribute("listChucVu", listChucVu);
//        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-add.jsp");

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

    @PostMapping("/add")
    public String addNhanVien(Model model, @ModelAttribute("nhanVien") @Valid NhanVien nhanVien,
                              BindingResult bindingResult,
                              @ModelAttribute("chucVu") ChucVu chucVu,
                              @RequestParam("email") String email,
                              @RequestParam("images") MultipartFile multipartFile) throws IOException {


        if (bindingResult.hasErrors()) {

            List<ChucVu> listChucVu = chucVuService.findAll();
            nhanVien.setTinhTrang(0);
            nhanVien.setGioiTinh(true);


//            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listChucVu", listChucVu);
            model.addAttribute("contentPage", "../nhanvien/nhan-vien-add.jsp");

            return "home/layout";
//            return "../nhanvien/nhan-vien-add.jsp";
        }
        NhanVien nvien = new NhanVien();
        String randomPassword = generateRandomPassword(8);
        nvien.setMatKhau(randomPassword);

        String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
        nhanVien.setMatKhau(hashedPassword);
        String rawPassword = randomPassword;
        String hashedPasswords = hashedPassword; // Mật khẩu đã được mã hóa
        boolean matches = BCrypt.checkpw(rawPassword, hashedPassword);
        System.out.println(matches);
        System.out.println("MK Nè " + randomPassword.toString());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "src/main/webapp/uploads/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        nhanVien.setUrlAnh(fileName);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        Sort sort = Sort.by("ma").descending();
        Integer nv = nhanVienService.findAll().size();
        String maNV = "";
        if (nv < 10) {
            maNV = "MaNV0" + nv;
        } else {
            maNV = "MaNV" + nv;
        }


        nhanVien.setMa(maNV);
        nhanVien.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        nhanVien.setNgayTao(date);
        nhanVien.setTinhTrang(0);
        nhanVien.setEmail(email);
        nhanVienService.add(nhanVien);
        List<ChucVu> listChucVu = chucVuService.findAll();

        List<NhanVien> page = nhanVienService.getAll();
        NhanVien nvv = nhanVienService.findById(nhanVien.getId());
        String phone = nvv.getSdt();
        String message = "Chào mừng bạn đã đăng ký tài khoản. Mật khẩu của bạn là: " + nvien.getMatKhau();
//        smsService.sendSMS(phone, message);
        mailer.queue(nvv.getEmail(), "Bạn đã đăng kí tài khoản thành công", "TK: " + nvv.getTaiKhoan() + "\nMK: " + nvien.getMatKhau());
        model.addAttribute("listChucVu", listChucVu);
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", page);

        model.addAttribute("contentPage", "../nhanvien/nhan-vien.jsp");
        return "redirect:/nhan-vien/hien-thi";
//        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        List<ChucVu> listChucVu = chucVuService.findAll();
        model.addAttribute("listChucVu", listChucVu);
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-update.jsp");
        return "home/layout";
    }


    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id,
                         @ModelAttribute("nhanVien") @Valid NhanVien nhanVien,
                         BindingResult bindingResult,
                         @RequestParam("checkanh1") String checkanh1,
                         @RequestParam("anh1s") MultipartFile anh1
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            List<ChucVu> listChucVu = chucVuService.findAll();
            model.addAttribute("listChucVu", listChucVu);
            model.addAttribute("contentPage", "../nhanvien/nhan-vien-update.jsp");
            return "home/layout";

        }
        NhanVien nv = nhanVienService.findById(id);
        nhanVien.setId(id);
        nhanVien.setMa(nv.getMa());
        nhanVien.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        nhanVien.setNgayTao(nv.getNgayTao());
        nhanVien.setTinhTrang(0);
        nhanVien.setTaiKhoan(nv.getTaiKhoan());
        nhanVien.setLuong(nv.getLuong());
        nhanVien.setMatKhau(nv.getMatKhau());
        System.out.println(nv.toString());
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());
        if (checkanh1.equals("cu1")) {
        } else {
            if (fileName1.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            }
            nhanVien.setUrlAnh(fileName1);
        }
        nhanVienService.update(id, nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }


    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("nhanVien") NhanVien
            nhanVien, @RequestParam("search") String search) {
        List<NhanVien> list = nhanVienService.search0(search);
        model.addAttribute("listNhanVien", list);
        model.addAttribute("contentPage", "../nhanvien/nhan-vien.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("nhanVien") NhanVien
            nhanVien, @RequestParam("search") String search) {
        List<NhanVien> list = nhanVienService.search1(search);
        model.addAttribute("listNhanVien", list);
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-delete.jsp");
        return "home/layout";
    }

    @GetMapping("/update-all-status")
    public String updateTT(Model model,
                           @ModelAttribute("nhanVien") NhanVien nhanVien) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        nhanVien.setNgayCapNhat(date);

        nhanVienService.updateTT();
        List<NhanVien> page = nhanVienService.getAll1();
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-delete.jsp");
        model.addAttribute("listNhanVien", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id,
                                       @ModelAttribute("nhanVien") NhanVien nhanVien) {

        NhanVien nhanVien1 = nhanVienService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        nhanVien1.setNgayCapNhat(date);
        nhanVien1.setTinhTrang(1);
        nhanVienService.update(id, nhanVien1);
        List<NhanVien> page = nhanVienService.getAll();
        model.addAttribute("contentPage", "../nhanvien/nhan-vien.jsp");
        model.addAttribute("listNhanVien", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID
            id, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        NhanVien nhanVien1 = nhanVienService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        nhanVien1.setNgayCapNhat(date);

        nhanVien1.setTinhTrang(0);
        nhanVienService.update(id, nhanVien1);
        List<NhanVien> page = nhanVienService.getAll1();
        model.addAttribute("contentPage", "../nhanvien/nhan-vien-delete.jsp");
        model.addAttribute("listNhanVien", page);
        return "home/layout";
    }


    @PostMapping("/modal-add-chuc-vu")
    public String addChuVuModal(Model model, @ModelAttribute("chucVu") @Valid ChucVu chucVu, BindingResult
            bindingResult
//                                @ModelAttribute("nhanVien") @Valid NhanVien nhanVien
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Integer sl = chucVuService.findAll().size();
        String maCV = "";

        if (sl < 10) {
            maCV = "MaCV0" + sl;
        } else {
            maCV = "MaCV" + sl;
        }

        chucVu.setMa(maCV);
        chucVu.setNgayTao(date);
        chucVu.setNgayCapNhat(date);
        chucVu.setTinhTrang(0);

        if (bindingResult.hasErrors()) {
            model.addAttribute("chucVuError", "true"); // Đặt một thuộc tính để chỉ định có lỗi
        } else {

            chucVuService.add(chucVu);
//            return "redirect:/nhan-vien/view-add"; // Sau khi thêm chức vụ, điều hướng đến trang thêm nhân viên
        }
//        return "nhanvien/nhan-vien-add.jsp"; // Trả về trang hiện thị modal
        return "redirect:/nhan-vien/view-add"; // Sau khi thêm chức vụ, điều hướng đến trang thêm nhân viên
    }


}
