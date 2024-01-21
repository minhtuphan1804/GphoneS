package com.example.demo.controllers;

import com.example.demo.models.DungLuongPin;
import com.example.demo.services.DungLuongPinService;
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

@RequestMapping("/dung-luong-pin")
@Controller
public class DungLuongPinController {

    @Autowired
    private DungLuongPinService dungLuongPinService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPi) {
        List<DungLuongPin> page = dungLuongPinService.getAll();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<DungLuongPin> page = dungLuongPinService.getAll1();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        model.addAttribute("dungLuongPin", new DungLuongPin());
        model.addAttribute("contentPage", "../dungluongpin/add.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String addDungLuongPin(Model model, @ModelAttribute("dungLuongPin") @Valid DungLuongPin dungLuongPin, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../dungluongpin/add.jsp");
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "DLP" + (dungLuongPinService.findAll().size() + 1);
        dungLuongPin.setMa(ma);
        dungLuongPin.setNgayTao(date);
        dungLuongPin.setTinhTrang(0);

        dungLuongPinService.add(dungLuongPin);
        return "redirect:/dung-luong-pin/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updateDungLuongPin(Model model, @PathVariable("id") UUID id,
                                     @ModelAttribute("dungLuongPin") @Valid DungLuongPin dungLuongPin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "../dungluongpin/dung-luong-pin-update";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin.setNgayCapNhat(date);
        dungLuongPinService.update(id, dungLuongPin);
        return "redirect:/dung-luong-pin/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin.setNgayCapNhat(date);

        dungLuongPinService.updateTT();
        System.out.println("cc: ");
        List<DungLuongPin> page = dungLuongPinService.getAll1();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        DungLuongPin dungLuongPin1 = dungLuongPinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin1.setNgayCapNhat(date);
        dungLuongPin1.setTinhTrang(1);
        dungLuongPinService.update(id, dungLuongPin1);
        List<DungLuongPin> page = dungLuongPinService.getAll();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id,
                              @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        DungLuongPin dungLuongPin1 = dungLuongPinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin1.setNgayCapNhat(date);

        dungLuongPin1.setTinhTrang(0);
        dungLuongPinService.update(id, dungLuongPin1);
        List<DungLuongPin> page = dungLuongPinService.getAll1();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        DungLuongPin sp = dungLuongPinService.findById(id);
        model.addAttribute("dungLuongPin", sp);
        List<DungLuongPin> page = dungLuongPinService.getAll();
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-update.jsp");
        model.addAttribute("listDungLuongPin", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @RequestParam("search") String sreach, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<DungLuongPin> listDungLuongPin = dungLuongPinService.search0(sreach);
        model.addAttribute("listDungLuongPin", listDungLuongPin);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @RequestParam("search") String sreach, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<DungLuongPin> listDungLuongPin = dungLuongPinService.search1(sreach);
        model.addAttribute("listDungLuongPin", listDungLuongPin);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        return "home/layout";
    }
}
