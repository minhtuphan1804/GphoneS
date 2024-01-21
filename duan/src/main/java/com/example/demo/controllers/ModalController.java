package com.example.demo.controllers;

import com.example.demo.models.Anh;
import com.example.demo.models.Camera;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.HangKhachHang;
import com.example.demo.models.HangSanPham;
import com.example.demo.models.ManHinh;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import com.example.demo.services.AnhService;
import com.example.demo.services.CameraService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.ChipService;
import com.example.demo.services.DungLuongPinService;
import com.example.demo.services.HangKhachHangService;
import com.example.demo.services.HangSanPhamService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.ManHinhService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.PinService;
import com.example.demo.services.RamService;
import com.example.demo.services.RomService;
import com.example.demo.services.SanPhamService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ModalController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    ChipService chipService;
    @Autowired
    RamService ramService;
    @Autowired
    RomService romService;
    @Autowired
    PinService pinService;
    @Autowired
    HangSanPhamService hangSanPhamService;
    @Autowired
    DungLuongPinService dungLuongPinService;
    @Autowired
    ManHinhService manHinhService;
    @Autowired
    CameraService cameraService;
    @Autowired
    AnhService anhService;
    @Autowired
    HangKhachHangService hangKhachHangService;

    @Autowired
    KhachHangService khachHangService;


    @PostMapping("/chi-tiet-san-pham/modal-add-pin")
    public String addPin(Model model, @ModelAttribute("Pin") @Valid Pin pin, BindingResult bindingResult) {

        List<Pin> page = pinService.getAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll());
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());

            model.addAttribute("mauSac", new MauSac());


            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthempin",0);
            return "home/layout";
        }
        if (pinService.existPin(pin.getLoaiPin(),pin.getCongNghePin(),pin.getDungLuongPin())) {
            model.addAttribute("list", page);
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll());
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());

            model.addAttribute("mauSac", new MauSac());


            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbPinTrung", "Pin trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthempin",0);
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "CHIP" + pinService.findAll().size();
        pin.setMa(ma);
        pin.setNgayTao(date);
        pin.setTinhTrang(0);
        pinService.add(pin);
        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/modal-add-mau-sac")
    public String addMauSac(Model model, @ModelAttribute("mauSac") @Valid MauSac mauSac, BindingResult bindingResult) {
        List<MauSac> page = mauSacService.getAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());

            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
//            model.addAttribute("mauSac", new MauSac()); bỏ bỏ bỏ bỏ bỏ
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemmausac",0);
            return "home/layout";
        }
        if (mauSacService.exitstByTen(mauSac.getTen())){
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());

            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
//            model.addAttribute("mauSac", new MauSac()); bỏ bỏ bỏ bỏ bỏ
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbMauSacTrung", "Màu sắc trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemmausac",0);
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "MS" + mauSacService.findAll().size();
        mauSac.setMa(ma);
//        mauSac.setNgayTao(date);
        mauSac.setTinhTrang(0);
        mauSacService.add(mauSac);

        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/modal-add-chip")
    public String addPin(Model model, @ModelAttribute("chip") @Valid Chip chip, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        List<Chip> page = chipService.getAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());

            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemchip",0);

            return "home/layout";
        }
        if (chipService.existChip(chip.getTen(),chip.getLoaiChip())) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());

            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbChipTrung", "Chip trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemchip",0);

            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "CHIP" + chipService.findAll().size();
        chip.setMa(ma);
        chip.setNgayTao(date);
        chip.setTinhTrang(0);
        chipService.add(chip);
        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/modal-add-rom")
    public String addRom(Model model, @ModelAttribute("rom") @Valid Rom rom, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        List<Rom> page = romService.getAll0();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());

            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemrom",0);

            return "home/layout";
        }
        if (romService.existRom(rom.getDungLuong())) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());

            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbRomTrung", "Rom trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemrom",0);

            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "CHIP" + chipService.findAll().size();
        rom.setMa(ma);
        rom.setNgayTao(date);
        rom.setTinhTrang(0);
        romService.add(rom);
        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/modal-add-ram")
    public String addRam(Model model, @ModelAttribute("ram") @Valid Ram ram, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        List<Ram> page = ramService.getAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());

            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemram",0);

            return "home/layout";
        }
        if (ramService.existRam(ram.getDungLuong())) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());

            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbRamTrung", "Ram trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemram",0);

            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "RAM" + ramService.findAll().size();
        ram.setMa(ma);
        ram.setNgayTao(date);
        ram.setTinhTrang(0);
        ramService.add(ram);
        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
    }

    @PostMapping("/chi-tiet-san-pham/modal-add-san-pham")
    public String addSanPham(Model model, @ModelAttribute("sanPham") @Valid SanPham sanPham, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        List<SanPham> page = sanPhamService.getAll0();

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());

            model.addAttribute("rom", new Rom());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemsanpham",0);

            return "home/layout";
        }
        if (sanPhamService.existSanPham(sanPham.getTen())) {
            model.addAttribute("list", page);
            model.addAttribute("chitietsanpham", new ChiTietSanPham());
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());

            model.addAttribute("rom", new Rom());
            model.addAttribute("mauSac", new MauSac());
//
//
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("tbSanPhamTrung", "Sản phẩm trùng!");



            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("modalthemsanpham",0);

            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "SP" + ramService.findAll().size();
        sanPham.setMa(ma);
        sanPham.setNgayTao(date);
        sanPham.setTinhTrang(0);
        sanPhamService.add(sanPham);
        model.addAttribute("list", page);
        model.addAttribute("Pin", new Pin());
        model.addAttribute("chip", new Chip());
        model.addAttribute("ram", new Ram());
        model.addAttribute("rom", new Rom());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("chitietsanpham", new ChiTietSanPham());


        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

        return "home/layout";
//        return "redirect:/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/khach-hang/modal-add-hang-khach-hang")
    public String addHangKhachHang(Model model, @ModelAttribute("hangKhachHang") @Valid HangKhachHang hangKhachHang, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            model.addAttribute("dulieu", hangKhachHangService.getALL0());
            return "../khach-hangkhach-hang";
        }
        Integer sl = hangKhachHangService.findAll().size() + 1;
        String mhd = "MHKH" + sl;
        hangKhachHang.setMa(mhd);
        hangKhachHang.setNgayTao(date);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHang.setTinhTrang(0);
        hangKhachHangService.add(hangKhachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("/san-pham/modal-add-man-hinh")
    public String addManHinh(Model model, @ModelAttribute("ManHinh") @Valid ManHinh manHinh, BindingResult bindingResult,
                             @RequestParam("num") Optional<Integer> num, @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hangSP", new HangSanPham());
            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("camera", new Camera());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemmanhinh",0);

            return "home/layout";
        }
        if (manHinhService.existManHinh(manHinh.getThongSo(),manHinh.getLoaiCamUng(),manHinh.getTiLeKhungHinh(),manHinh.getDoPhanGiai(),manHinh.getTanSoQuet(),manHinh.getCongNghe())){
            model.addAttribute("hangSP", new HangSanPham());
            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("camera", new Camera());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("tbTrungManHinh", "Màn hình này đã tồn tại");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemmanhinh",0);

            return "home/layout";
        }
        manHinh.setNgayTao(Date.valueOf(LocalDate.now()));
        manHinh.setMa("MH" + String.valueOf(manHinhService.findAll().size() + 1));
        manHinhService.add(manHinh);
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        List<ManHinh> list = manHinhService.getAll();
        model.addAttribute("listManHinh", list);
        model.addAttribute("hangSP", new HangSanPham());
        model.addAttribute("dulieuxem", new SanPham());
        model.addAttribute("ManHinh", new ManHinh());
        model.addAttribute("camera", new Camera());
        model.addAttribute("anh", new Anh());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../san-pham/add.jsp");

        return "home/layout";

        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/san-pham/modal-add-camera")
    public String addCamera(Model model, @ModelAttribute("camera") @Valid Camera camera, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("hangSP", new HangSanPham());

            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("ManHinh", new ManHinh());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemcamera",0);

            return "home/layout";
        }
        if (cameraService.existCamera(camera.getThongSo())){
            model.addAttribute("hangSP", new HangSanPham());

            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("ManHinh", new ManHinh());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("tbTrungCamera", "Camera đã tồn tại");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemcamera",0);

            return "home/layout";
        }
        camera.setNgayTao(Date.valueOf(LocalDate.now()));
        camera.setMa("MH" + String.valueOf(cameraService.findAll().size() + 1));
        cameraService.add(camera);
        List<Camera> list = cameraService.getAll();
        model.addAttribute("listCamera", list);
        model.addAttribute("hangSP", new HangSanPham());
        model.addAttribute("dulieuxem", new SanPham());
        model.addAttribute("ManHinh", new ManHinh());
        model.addAttribute("camera", new Camera());
        model.addAttribute("anh", new Anh());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../san-pham/add.jsp");

        return "home/layout";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/san-pham/modal-add-hang-sp")
    public String addHangSanPham(Model model, @ModelAttribute("hangSP") @Valid HangSanPham dulieuxem, BindingResult bindingResult, @RequestParam("num") Optional<Integer> num,
                                 @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("ManHinh", new ManHinh());
            model.addAttribute("camera", new Camera());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemhang",0);

            return "home/layout";
        }
        if (hangSanPhamService.existHang(dulieuxem.getTen())){
            model.addAttribute("dulieuxem", new SanPham());
            model.addAttribute("ManHinh", new ManHinh());
            model.addAttribute("camera", new Camera());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("tbTrungHang", "Hãng trùng");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            model.addAttribute("modalthemhang",0);

            return "home/layout";
        }
        dulieuxem.setNgayTao(Date.valueOf(LocalDate.now()));
        dulieuxem.setMa("H" + String.valueOf(hangSanPhamService.findAll().size() + 1));
        hangSanPhamService.add(dulieuxem);
        model.addAttribute("hangSP", new HangSanPham());
        model.addAttribute("dulieuxem", new SanPham());
        model.addAttribute("ManHinh", new ManHinh());
        model.addAttribute("camera", new Camera());
        model.addAttribute("anh", new Anh());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../san-pham/add.jsp");

        return "home/layout";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/san-pham/modal-add-anh")
    public String addAnh(Model model, @ModelAttribute("anh") @Valid Anh anh,
                         BindingResult bindingResult,
                         @RequestParam("anh1s") MultipartFile anh1,
                         @RequestParam("anh2s") MultipartFile anh2,
                         @RequestParam("anh3s") MultipartFile anh3) throws IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../san-pham/add.jsp");
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
        model.addAttribute("hangSP", new HangSanPham());
        model.addAttribute("dulieuxem", new SanPham());
        model.addAttribute("ManHinh", new ManHinh());
        model.addAttribute("camera", new Camera());
        model.addAttribute("anh", new Anh());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("tbHien",1);
        model.addAttribute("contentPage", "../san-pham/add.jsp");

        return "home/layout";
    }
}
