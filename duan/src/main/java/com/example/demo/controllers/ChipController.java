package com.example.demo.controllers;

import com.example.demo.models.Chip;
import com.example.demo.services.ChipService;
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

@RequestMapping("/chip")
@Controller
public class ChipController {

    @Autowired
    private ChipService chipService;


    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("chip") Chip chip) {
        List<Chip> page = chipService.getAll();
        model.addAttribute("contentPage", "../chip/chip.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("chip") Chip chip) {
        List<Chip> page = chipService.getAll1();
        model.addAttribute("contentPage", "../chip/chip-delete.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chip") Chip chip) {
        model.addAttribute("chip", new Chip());
        model.addAttribute("contentPage", "../chip/add.jsp");
        return "home/layout";
    }


    @PostMapping("/add")
    public String addPin(Model model, @ModelAttribute("chip") @Valid Chip chip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../chip/add.jsp");
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "CHIP" + (chipService.findAll().size() + 1);
        chip.setMa(ma);
        chip.setNgayTao(date);
        chip.setTinhTrang(0);
        return "redirect:/chip/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updatePin(Model model, @PathVariable("id") UUID id, @ModelAttribute("chip") @Valid Chip chip, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "../chip/chip-update";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chip.setNgayCapNhat(date);
        chipService.update(id, chip);

        return "redirect:/chip/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @ModelAttribute("chip") Chip chip) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chip.setNgayCapNhat(date);

        chipService.updateTT();
        List<Chip> page = chipService.getAll1();
        model.addAttribute("contentPage", "../chip/chip-delete.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("chip") Chip chip) {
        Chip chip1 = chipService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chip1.setNgayCapNhat(date);
        chip1.setTinhTrang(1);
        chipService.update(id, chip1);
        List<Chip> page = chipService.getAll();
        model.addAttribute("contentPage", "../chip/chip.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("chip") Chip chip) {
        Chip chip1 = chipService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chip1.setNgayCapNhat(date);

        chip1.setTinhTrang(0);
        chipService.update(id, chip1);
        List<Chip> page = chipService.getAll1();
        model.addAttribute("contentPage", "../chip/chip-delete.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        Chip sp = chipService.findById(id);
        model.addAttribute("chip", sp);
        List<Chip> page = chipService.getAll();
        model.addAttribute("contentPage", "../chip/chip-update.jsp");
        model.addAttribute("listChip", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("chip") Chip chip, @RequestParam("search") String search) {
        List<Chip> list = chipService.search0(search);
        model.addAttribute("listChip", list);
        model.addAttribute("contentPage", "../chip/chip.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("chip") Chip chip, @RequestParam("search") String search) {
        List<Chip> list = chipService.search1(search);
        model.addAttribute("listChip", list);
        model.addAttribute("contentPage", "../chip/chip-delete.jsp");
        return "home/layout";
    }
}
