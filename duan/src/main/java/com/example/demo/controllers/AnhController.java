package com.example.demo.controllers;

import com.example.demo.models.Anh;
import com.example.demo.services.AnhService;
import com.example.demo.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/anh")
public class AnhController {
    @Autowired
    private AnhService anhService;

    @GetMapping("hien-thi")
    public String hienThi(@ModelAttribute("anh") Anh anh, Model model) {
        List<Anh> list = anhService.getAll();
        model.addAttribute("listAnh", list);
        model.addAttribute("contentPage", "../anh/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("anh") Anh anh) {

        List<Anh> page = anhService.getAll1();
        model.addAttribute("contentPage", "../anh/anh-delete.jsp");
        model.addAttribute("listAnh", page);
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("anh") Anh anh) {
        model.addAttribute("anh", new Anh());
        model.addAttribute("contentPage", "../anh/add.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @ModelAttribute("anh") Anh anh, @PathVariable("id") UUID id) {
        model.addAttribute("anh", anhService.findById(id));
        model.addAttribute("contentPage", "../anh/update.jsp");
        return "home/layout";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model,
                           @ModelAttribute("anh") Anh anh) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        anh.setNgayCapNhat(date);
        anhService.updateTT();
        List<Anh> page = anhService.getAll1();
        model.addAttribute("contentPage", "../anh/anh-delete.jsp");
        model.addAttribute("listAnh", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("anh") Anh anh) {

        Anh anh1 = anhService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        anh1.setNgayCapNhat(date);
        anh1.setTinhTrang(1);
        anhService.update(id, anh1);
        List<Anh> page = anhService.getAll();
        model.addAttribute("contentPage", "../anh/hien-thi.jsp");
        model.addAttribute("listAnh", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("anh") Anh anh) {
        Anh anh1 = anhService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        anh1.setNgayCapNhat(date);

        anh1.setTinhTrang(0);
        anhService.update(id, anh1);
        List<Anh> page = anhService.getAll1();
        model.addAttribute("contentPage", "../anh/anh-delete.jsp");
        model.addAttribute("listAnh", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("anh") Anh anh, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<Anh> list = anhService.getAll();
            model.addAttribute("listAnh", list);
            model.addAttribute("contentPage", "../anh/hien-thi.jsp");
            return "home/layout";
        } else {
            List<Anh> list = anhService.search0(search);
            model.addAttribute("listAnh", list);
            model.addAttribute("contentPage", "../anh/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("anh") Anh anh, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<Anh> list = anhService.getAll1();
            model.addAttribute("listAnh", list);
            model.addAttribute("contentPage", "../anh/anh-delete.jsp");
            return "home/layout";
        } else {
            List<Anh> list = anhService.search1(search);
            model.addAttribute("listAnh", list);
            model.addAttribute("contentPage", "../anh/anh-delete.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/add")
    public String add(Model model,
                      @ModelAttribute("anh") @Valid Anh anh,
                      BindingResult bindingResult,
                      @RequestParam("anh1s") MultipartFile anh1,
                      @RequestParam("anh2s") MultipartFile anh2,
                      @RequestParam("anh3s") MultipartFile anh3
    ) throws IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../anh/add.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = anhService.findAll().size() + 1;
        if (sl < 9) {
            mhd = "ANH0" + sl;
        } else {
            mhd = "ANH" + sl;
        }
        anh.setMa(mhd);
        anh.setNgayTao(date);
        anh.setTinhTrang(0);

        // Xử lý ảnh 1
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());

        if (fileName1.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            anh.setAnh1(fileName1);
        }

        // Xử lý ảnh 2
        String fileName2 = StringUtils.cleanPath(anh2.getOriginalFilename());

        if (fileName2.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName2, anh2);
            anh.setAnh2(fileName2);
        }

        // Xử lý ảnh 3
        String fileName3 = StringUtils.cleanPath(anh3.getOriginalFilename());

        if (fileName3.equals("")) {

        } else {
            String uploadDir = "src/main/webapp/uploads/";
            FileUploadUtil.saveFile(uploadDir, fileName3, anh3);
            anh.setAnh3(fileName3);
        }
        anhService.add(anh);
        return "redirect:/anh/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updateDongSP(Model model, @ModelAttribute("anh") @Valid Anh anh,
                               BindingResult bindingResult,
                               @PathVariable("id") UUID id,
                               @RequestParam("checkanh1") String checkanh1,
                               @RequestParam("checkanh2") String checkanh2,
                               @RequestParam("checkanh3") String checkanh3,
                               @RequestParam("anh1s") MultipartFile anh1,
                               @RequestParam("anh2s") MultipartFile anh2,
                               @RequestParam("anh3s") MultipartFile anh3
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../anh/update.jsp");
            return "home/layout";
        }
        Anh tt = anhService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        anh.setNgayCapNhat(date);
        anh.setNgayTao(tt.getNgayTao());
        anh.setTinhTrang(tt.getTinhTrang());
        anh.setMa(tt.getMa());
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());
        if (checkanh1.equals("cu1")) {
        } else {
            if (fileName1.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            }
            anh.setAnh1(fileName1);
        }
        String fileName2 = StringUtils.cleanPath(anh2.getOriginalFilename());
        if (checkanh2.equals("cu2")) {
        } else {
            if (fileName2.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName2, anh2);
            }
            anh.setAnh2(fileName2);
        }
        String fileName3 = StringUtils.cleanPath(anh3.getOriginalFilename());
        if (checkanh3.equals("cu3")) {
        } else {
            if (fileName3.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName3, anh3);
            }
            anh.setAnh3(fileName3);
        }
        anhService.update(id, anh);
        return "redirect:/anh/hien-thi";
    }
}
