package com.example.demo.controllers;


import com.example.demo.models.ChucVu;
import com.example.demo.services.ChucVuService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/chuc-vu")
@Controller
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("chucVu") ChucVu chucVu) {
        List<ChucVu> page = chucVuService.getAll();
        model.addAttribute("listChucVu", page);
        model.addAttribute("contentPage", "../chucvu/chuc-vu.jsp");
        return "home/layout";
    }

    @PostMapping("/add-chuc-vu")
    public String addMauSac(Model model, @ModelAttribute("chucVu") @Valid ChucVu chucVu, BindingResult bindingResult) {
        List<ChucVu> page = chucVuService.getAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../chucvu/chuc-vu.jsp");
            model.addAttribute("listChucVu", page);
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Integer sl = chucVuService.findAll().size();
        String maCV = "";
        if (sl < 10) {
            maCV = "MaCV0" + sl;
        } else {
            maCV = "MaCV" + sl;
        }
//        String maR = "Ram" + ramService.findAll().size();
        chucVu.setMa(maCV);
        chucVu.setNgayTao(date);
        chucVu.setNgayCapNhat(date);
        chucVu.setTinhTrang(0);
        chucVuService.add(chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/detail-chuc-vu/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        ChucVu chucVu = chucVuService.findById(id);
        model.addAttribute("chucVu", chucVu);
        model.addAttribute("contentPage", "../chucvu/chuc-vu-update.jsp");
        return "home/layout";
    }

    @PostMapping("/update-chuc-vu/{id}")
    public String updateRam(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") @Valid ChucVu chucVu,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "../chucvu/chuc-vu-update.jsp";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
//        chucVu.setNgayTao(date);
        chucVu.setNgayCapNhat(date);
        chucVuService.update(id, chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("chucVu") ChucVu chucVu) {

        List<ChucVu> page = chucVuService.getAll1();
        model.addAttribute("contentPage", "../chucvu/chuc-vu-delete.jsp");
        model.addAttribute("listChucVu", page);
        return "home/layout";
    }

    @GetMapping("/update-all-status")
    public String updateTT(Model model,
                           @ModelAttribute("chucVu") ChucVu chucVu) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chucVu.setNgayCapNhat(date);
        chucVuService.updateTT();
        List<ChucVu> page = chucVuService.getAll1();
        model.addAttribute("contentPage", "../chucvu/chuc-vu-delete.jsp");
        model.addAttribute("listChucVu", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id,
                               @ModelAttribute("chucVu") ChucVu chucVu) {
        ChucVu chucVu1 = chucVuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chucVu1.setNgayCapNhat(date);
        chucVu1.setTinhTrang(1);
        chucVuService.update(id, chucVu1);
        List<ChucVu> page = chucVuService.getAll();
        model.addAttribute("contentPage", "../chucvu/chuc-vu.jsp");
        model.addAttribute("listChucVu", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id,
                              @ModelAttribute("chucVu") ChucVu chucVu) {
        ChucVu chucVu1 = chucVuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chucVu.setNgayCapNhat(date);
        chucVu1.setTinhTrang(0);
        chucVuService.update(id, chucVu1);
        List<ChucVu> page = chucVuService.getAll1();
        model.addAttribute("contentPage", "../chucvu/chuc-vu-delete.jsp");
        model.addAttribute("listChucVu", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("chucVu") ChucVu chucVu, @RequestParam("search") String search) {
        List<ChucVu> list = chucVuService.search0(search);
        model.addAttribute("listChucVu", list);
        model.addAttribute("contentPage", "../chucvu/chuc-vu.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("chucVu") ChucVu chucVu,
                          @RequestParam("search") String search) {
        List<ChucVu> list = chucVuService.search1(search);
        model.addAttribute("listChucVu", list);
        model.addAttribute("contentPage", "../chucvu/chuc-vu-delete.jsp");
        return "home/layout";
    }

}

