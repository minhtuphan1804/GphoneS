package com.example.demo.controllers;

import com.example.demo.models.ManHinh;
import com.example.demo.services.ManHinhService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/man-hinh")
public class ManHinhController {
    @Autowired
    private ManHinhService manHinhService;

    @GetMapping("hien-thi")
    public String hienThi(@ModelAttribute("manHinh") ManHinh manHinh, Model model) {
        List<ManHinh> list = manHinhService.getAll();
        model.addAttribute("listManHinh", list);
        model.addAttribute("contentPage", "../man-hinh/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("manHinh") ManHinh manHinh) {
        List<ManHinh> page = manHinhService.getAll1();
        model.addAttribute("contentPage", "../man-hinh/man-hinh-delete.jsp");
        model.addAttribute("listManHinh", page);
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("manHinh") ManHinh manHinh) {
        model.addAttribute("manHinh", new ManHinh());
        model.addAttribute("contentPage", "../man-hinh/add.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        ManHinh hsp = manHinhService.findById(id);
        model.addAttribute("manHinh", hsp);
        model.addAttribute("contentPage", "../man-hinh/update.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute("manHinh") @Valid ManHinh manHinh, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../man-hinh/add.jsp");
            return "home/layout";
        }
        manHinh.setNgayTao(Date.valueOf(LocalDate.now()));
        manHinh.setTinhTrang(0);
        manHinh.setMa("MH" + (String.valueOf(manHinhService.findAll().size() + 1)));
        manHinhService.add(manHinh);
        return "redirect:/man-hinh/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("manHinh") @Valid ManHinh manHinh, BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../man-hinh/update.jsp");
            return "home/layout";
        }
        ManHinh hsp = manHinhService.findById(id);
        manHinh.setMa(hsp.getMa());
        manHinh.setTinhTrang(hsp.getTinhTrang());
        manHinh.setNgayTao(hsp.getNgayTao());
        manHinh.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        manHinhService.update(id, manHinh);
        return "redirect:/man-hinh/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @ModelAttribute("manHinh") ManHinh manHinh) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        manHinh.setNgayCapNhat(date);
        manHinhService.updateTT();
        List<ManHinh> page = manHinhService.getAll1();
        model.addAttribute("contentPage", "../man-hinh/man-hinh-delete.jsp");
        model.addAttribute("listManHinh", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("manHinh") ManHinh manHinh) {
        ManHinh manHinh1 = manHinhService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        manHinh1.setNgayCapNhat(date);
        manHinh1.setTinhTrang(1);
        manHinhService.update(id, manHinh1);
        List<ManHinh> page = manHinhService.getAll();
        model.addAttribute("contentPage", "../man-hinh/hien-thi.jsp");
        model.addAttribute("listManHinh", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("manHinh") ManHinh manHinh) {
        ManHinh manHinh1 = manHinhService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        manHinh1.setNgayCapNhat(date);

        manHinh1.setTinhTrang(0);
        manHinhService.update(id, manHinh1);
        List<ManHinh> page = manHinhService.getAll1();
        model.addAttribute("contentPage", "../man-hinh/man-hinh-delete.jsp");
        model.addAttribute("listManHinh", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("manHinh") ManHinh manHinh, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<ManHinh> list = manHinhService.getAll();
            model.addAttribute("listManHinh", list);
            model.addAttribute("contentPage", "../man-hinh/hien-thi.jsp");
            return "home/layout";
        } else {
            List<ManHinh> list = manHinhService.search0(search);
            model.addAttribute("listManHinh", list);
            model.addAttribute("contentPage", "../man-hinh/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("manHinh") ManHinh manHinh, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<ManHinh> list = manHinhService.getAll1();
            model.addAttribute("listManHinh", list);
            model.addAttribute("contentPage", "../man-hinh/man-hinh-delete.jsp");
            return "home/layout";
        } else {
            List<ManHinh> list = manHinhService.search1(search);
            model.addAttribute("listManHinh", list);
            model.addAttribute("contentPage", "../man-hinh/man-hinh-delete.jsp");
            return "home/layout";
        }

    }
}
