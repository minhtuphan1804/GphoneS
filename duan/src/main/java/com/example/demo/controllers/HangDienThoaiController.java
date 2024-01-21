package com.example.demo.controllers;

import com.example.demo.models.HangSanPham;
import com.example.demo.services.HangSanPhamService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
//@RequestMapping("/hang-san-pham")
public class HangDienThoaiController {
    @Autowired
    HangSanPhamService hangSanPhamService;

    @GetMapping("/hang-dien-thoai/hien-thi")
    public String hienthi(@ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        List<HangSanPham> list = hangSanPhamService.getAll0();
        model.addAttribute("contentPage", "../hang-dien-thoai/hien-thi.jsp");
        model.addAttribute("hsp", list);
        return "home/layout";
    }

    @GetMapping("/hang-dien-thoai/hien-thi-tung-xoa")
    public String hienthixoa(@ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        List<HangSanPham> list = hangSanPhamService.getall1();
        model.addAttribute("contentPage", "../hang-dien-thoai/hang-tung-xoa.jsp");
        model.addAttribute("hsp", list);
        return "home/layout";
    }

    @GetMapping("/hang-dien-thoai/view-add")
    public String viewAdd(@ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        model.addAttribute("dulieuxem", new HangSanPham());
        model.addAttribute("contentPage", "../hang-dien-thoai/add.jsp");
        return "home/layout";
    }

    @GetMapping("/hang-dien-thoai/detail/{id}")
    public String viewupdate(@PathVariable("id") UUID id, @ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        HangSanPham hsp = hangSanPhamService.findById(id);
        model.addAttribute("dulieuxem", hsp);
        model.addAttribute("contentPage", "../hang-dien-thoai/update.jsp");
        return "home/layout";
    }

    @PostMapping("/hang-dien-thoai/add")
    public String add(Model model, @ModelAttribute("dulieuxem") @Valid HangSanPham dulieuxem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../hang-dien-thoai/add.jsp");
            return "home/layout";
        }

        dulieuxem.setNgayTao(Date.valueOf(LocalDate.now()));
        dulieuxem.setMa("H" + String.valueOf(hangSanPhamService.findAll().size() + 1));
        hangSanPhamService.add(dulieuxem);
        return "redirect:/hang-dien-thoai/hien-thi";
    }

    @PostMapping("/hang-dien-thoai/update/{id}")
    public String update(Model model, @ModelAttribute("dulieuxem") @Valid HangSanPham dulieuxem, BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../hang-dien-thoai/update.jsp");

            return "../hang-dien-thoai/update";
        }
        HangSanPham hsp = hangSanPhamService.findById(id);
        Date ngayTao = hsp.getNgayTao();
        hsp.setTen(dulieuxem.getTen());
        hsp.setNgayTao(ngayTao);
        // Gán ngày hiện tại
        hsp.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hsp.setTinhTrang(dulieuxem.getTinhTrang());
        hsp.setMoTa(dulieuxem.getMoTa());
        hsp.setXuatSu(dulieuxem.getXuatSu());
        hangSanPhamService.update(id, hsp);
        return "redirect:/hang-dien-thoai/hien-thi";
    }

    @GetMapping("../hang-dien-thoai/delete/{id}")
    public String delete(Model model, @ModelAttribute("dulieuxem") HangSanPham dulieuxem, @PathVariable("id") UUID id) {
        HangSanPham rom = hangSanPhamService.findById(id);
        rom.setTinhTrang(1);
        hangSanPhamService.add(rom);
//        Pageable pageable = PageRequest.of(num.orElse(0), size);
//        Page<HangSanPham> list = hangSanPhamService.getAll0(pageable);
//        model.addAttribute("contentPage","../hang-dien-thoai/hien-thi.jsp");
//        model.addAttribute("hsp", list.getContent());
//        model.addAttribute("total", list.getTotalPages());
        return "redirect:/hang-dien-thoai/hien-thi";

    }

    @PostMapping("/hang-dien-thoai/search")
    public String search(@RequestParam("search") String search, @ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        List<HangSanPham> list = hangSanPhamService.search(search);

        model.addAttribute("contentPage", "../hang-dien-thoai/hien-thi.jsp");
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @PostMapping("/hang-dien-thoai/search2")
    public String search2(@RequestParam("search") String search, @ModelAttribute("dulieuxem") HangSanPham dulieuxem, Model model) {
        List<HangSanPham> list = hangSanPhamService.search2(search);


        model.addAttribute("contentPage", "../hang-dien-thoai/hien-thi.jsp");
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @GetMapping("/hang-dien-thoai/khoi-phuc/{id}")
    public String khoiphuc(
            Model model,
            @PathVariable("id") UUID id

    ) {
        HangSanPham rom1 = hangSanPhamService.findById(id);
        rom1.setTinhTrang(0);
        hangSanPhamService.add(rom1);
        List<HangSanPham> list = hangSanPhamService.getall1();

        model.addAttribute("contentPage", "../hang-dien-thoai/hang-tung-xoa.jsp");
        model.addAttribute("hsp", list);
        return "home/layout";
    }

    @GetMapping("/hang-dien-thoai/khoi-phuc-het")
    public String updateTT(Model model,
                           @ModelAttribute("dulieuxem") HangSanPham dulieuxem) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dulieuxem.setNgayCapNhat(date);
        hangSanPhamService.updateTT();
        List<HangSanPham> page = hangSanPhamService.getall1();
        model.addAttribute("contentPage", "../hang-dien-thoai/hang-tung-xoa.jsp");
        model.addAttribute("hsp", page);
        return "home/layout";
    }
}
