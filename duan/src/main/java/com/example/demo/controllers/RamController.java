package com.example.demo.controllers;


import com.example.demo.models.Ram;
import com.example.demo.services.RamService;
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

@RequestMapping("/ram")
@Controller
public class RamController {
    @Autowired
    private RamService ramService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("ram") Ram ram) {
        ram.setTinhTrang(0);
        List<Ram> page = ramService.getAll();
        model.addAttribute("listRam", page);
        model.addAttribute("contentPage", "../ram/ram.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("ram") Ram ram) {
        model.addAttribute("ram", new Ram());
        model.addAttribute("contentPage", "../ram/add.jsp");
        return "home/layout";
    }

    @PostMapping("/add-ram")
    public String addMauSac(Model model, @ModelAttribute("ram") @Valid Ram ram, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../ram/add.jsp");
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Integer sl = ramService.findAll().size();
        String maRam = "";
        if (sl < 10) {
            maRam = "MRam0" + sl;
        } else {
            maRam = "MRam" + sl;
        }
        ram.setMa(maRam);
        ram.setNgayTao(date);
        ram.setNgayCapNhat(date);
        ram.setTinhTrang(0);
        ramService.add(ram);
        return "redirect:/ram/hien-thi";
    }

    @GetMapping("/detail-ram/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Ram ram = ramService.findById(id);
        model.addAttribute("ram", ram);
        List<Ram> page = ramService.getAll();
        model.addAttribute("contentPage", "../ram/ram-update.jsp");
        model.addAttribute("listRam", page);
        return "home/layout";
    }

    @PostMapping("/update-ram/{id}")
    public String updateRam(Model model, @PathVariable("id") UUID id, @ModelAttribute("ram") @Valid Ram ram,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "../ram/ram-update";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        ram.setNgayCapNhat(date);
        ramService.update(id, ram);
        return "redirect:/ram/hien-thi";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("ram") Ram ram) {
        List<Ram> page = ramService.getAll1();
        model.addAttribute("contentPage", "../ram/ram-delete.jsp");
        model.addAttribute("listRam", page);
        return "home/layout";
    }

    @GetMapping("/update-all-status")
    public String updateTT(Model model, @ModelAttribute("ram") Ram ram) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ram.setNgayCapNhat(date);
        ramService.updateTT();
        List<Ram> page = ramService.getAll1();
        model.addAttribute("contentPage", "../ram/ram-delete.jsp");
        model.addAttribute("listRam", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("ram") Ram ram) {
        Ram ram1 = ramService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ram1.setNgayCapNhat(date);
        ram1.setTinhTrang(1);
        ramService.update(id, ram1);
        List<Ram> page = ramService.getAll();
        model.addAttribute("contentPage", "../ram/ram.jsp");
        model.addAttribute("listRam", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("ram") Ram ram) {
        Ram ram1 = ramService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ram1.setNgayCapNhat(date);
        ram1.setTinhTrang(0);
        ramService.update(id, ram1);
        List<Ram> page = ramService.getAll1();
        model.addAttribute("contentPage", "../ram/ram-delete.jsp");
        model.addAttribute("listRam", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("ram") Ram ram, @RequestParam("search") String search) {
        List<Ram> list = ramService.search0(search);
        model.addAttribute("listRam", list);
        model.addAttribute("contentPage", "../ram/ram.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("ram") Ram ram, @RequestParam("search") String search) {
        List<Ram> list = ramService.search1(search);
        model.addAttribute("listRam", list);
        model.addAttribute("contentPage", "../ram/ram-delete.jsp");
        return "home/layout";
    }
}

