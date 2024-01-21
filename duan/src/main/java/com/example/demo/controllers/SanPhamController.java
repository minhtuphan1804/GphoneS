package com.example.demo.controllers;

import com.example.demo.models.Anh;
import com.example.demo.models.Camera;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.HangSanPham;
import com.example.demo.models.IMEI;
import com.example.demo.models.ManHinh;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.services.AnhService;
import com.example.demo.services.BanHangOnlineService;
import com.example.demo.services.CameraService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.ChipService;
import com.example.demo.services.DungLuongPinService;
import com.example.demo.services.HangSanPhamService;
import com.example.demo.services.IMEIService;
import com.example.demo.services.ManHinhService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.PinService;
import com.example.demo.services.RamService;
import com.example.demo.services.RomService;
import com.example.demo.services.SanPhamService;
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
//@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    HangSanPhamService hangSanPhamService;
    @Autowired
    ManHinhService manHinhService;
    @Autowired
    CameraService cameraService;
    @Autowired
    AnhService anhService;
    @Autowired
    IMEIService imeiService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

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
    DungLuongPinService dungLuongPinService;

    @Autowired
    BanHangOnlineService banHangOnlineService;
    @Autowired
    BanHangOnLinerepository banHangOnLinerepository;

    @GetMapping("/san-pham/hien-thi")
    public String hienthi(@ModelAttribute("dulieuxem") SanPham dulieuxem,
                          @ModelAttribute("ManHinh") ManHinh manHinh,
                          @ModelAttribute("hangSP") HangSanPham hangSanPham,
                          @ModelAttribute("camera") Camera camera,
                          @ModelAttribute("anh") Anh anh,
                          Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        List<SanPham> list = sanPhamService.getAll0();
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("hsp", list);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/hien-thi-tung-xoa")
    public String hienthi1(@ModelAttribute("dulieuxem") SanPham dulieuxem,
                           @ModelAttribute("ManHinh") ManHinh manHinh,
                           @ModelAttribute("hangSP") HangSanPham hangSanPham,
                           @ModelAttribute("camera") Camera camera,
                           @ModelAttribute("anh") Anh anh,
                           Model model, @RequestParam("num") Optional<Integer> num,
                           @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        List<SanPham> list = sanPhamService.getall1();
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("hsp", list);
        model.addAttribute("contentPage", "../san-pham/san-pham-tung-xoa.jsp");

        return "home/layout";
    }

    @GetMapping("/san-pham/view-add")
    public String viewAdd(Model model, @ModelAttribute("dulieuxem") SanPham dulieuxem,
                          @ModelAttribute("ManHinh") ManHinh manHinh,
                          @ModelAttribute("hangSP") HangSanPham hangSanPham,
                          @ModelAttribute("anh") Anh anh,
                          @ModelAttribute("camera") Camera camera) {
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("dulieuxem", new SanPham());
        model.addAttribute("contentPage", "../san-pham/add.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/detail/{id}")
    public String viewupdate(@PathVariable("id") UUID id, @ModelAttribute("dulieuxem") SanPham dulieuxem,
                             @ModelAttribute("ManHinh") ManHinh manHinh,
                             @ModelAttribute("hangSP") HangSanPham hangSanPham,
                             @ModelAttribute("camera") Camera camera,
                             @ModelAttribute("anh") Anh anh,
                             Model model, @RequestParam("num") Optional<Integer> num,
                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        SanPham hsp = sanPhamService.findById(id);
        model.addAttribute("dulieuxem", hsp);
        Pageable pageable = PageRequest.of(num.orElse(0), size);
        Page<SanPham> list = sanPhamService.getAll(pageable);
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("contentPage", "../san-pham/update.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "home/layout";
    }

    @PostMapping("/san-pham/add")
    public String add(Model model, @ModelAttribute("dulieuxem") @Valid SanPham dulieuxem,
                      BindingResult bindingResult,
                      @ModelAttribute("hangSP") HangSanPham hangSanPham,
                      @ModelAttribute("camera") Camera camera,
                      @ModelAttribute("ManHinh") ManHinh manHinh,
                      @ModelAttribute("anh") Anh anh) {
        if (bindingResult.hasErrors() || dulieuxem.getAnh() == null) {
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("errorAnh", "Hãy chọn ảnh!");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || dulieuxem.getHangSanPham() == null) {
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("errorHang", "Hãy chọn hãng!");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || dulieuxem.getManHinh() == null) {
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("errorManHinh", "Hãy chọn màn hình!");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            return "home/layout";
        }
        if (bindingResult.hasErrors() || dulieuxem.getCamera() == null) {
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());
            model.addAttribute("errorCamera", "Hãy chọn camera!");
            model.addAttribute("contentPage", "../san-pham/add.jsp");
            return "home/layout";
        }
        if (sanPhamService.trungDuLieu(dulieuxem.getTen(), dulieuxem.getHangSanPham(), dulieuxem.getCamera(), dulieuxem.getManHinh(), dulieuxem.getAnh())) {
            model.addAttribute("hangSP", new HangSanPham());
            model.addAttribute("ManHinh", new ManHinh());
            model.addAttribute("camera", new Camera());
            model.addAttribute("anh", new Anh());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listHangSP", hangSanPhamService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("listAnh", anhService.findAll0());

            model.addAttribute("thongBaoSP", "Sản phẩm đã tồn tại!");
            model.addAttribute("contentPage", "../san-pham/add.jsp");

            return "home/layout";
        }

        dulieuxem.setNgayTao(Date.valueOf(LocalDate.now()));
        dulieuxem.setMa("SP" + String.valueOf(sanPhamService.findAll().size() + 1));
        dulieuxem.setTinhTrang(0);
        sanPhamService.add(dulieuxem);
        List<SanPham> list = sanPhamService.getAll0();
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listAnh", anhService.findAll0());
        model.addAttribute("hsp", list);
        model.addAttribute("thongBaoCTSP", "Thêm sản phẩm thành công!");

        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/san-pham/update/{id}")
    public String update(Model model, @ModelAttribute("dulieuxem") @Valid SanPham dulieuxem,
                         BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../san-pham/update.jsp");
            return "home/layout";
        }
        SanPham hsp = sanPhamService.findById(id);
        dulieuxem.setMa(hsp.getMa());
        dulieuxem.setId(hsp.getId());
        dulieuxem.setNgayTao(hsp.getNgayTao());
        // Gán ngày hiện tại
        dulieuxem.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        dulieuxem.setTinhTrang(hsp.getTinhTrang());
        sanPhamService.update(id, dulieuxem);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/san-pham/delete/{id}")
    public String delete(Model model, @ModelAttribute("dulieuxem") SanPham dulieuxem, @PathVariable("id") UUID
            id, @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        List<IMEI> listImei = imeiService.statusSanPham(id);
        if (listImei.isEmpty()) {
            SanPham sp1 = sanPhamService.findById(id);
            sp1.setTinhTrang(1);
            sp1.setNgayCapNhat(Date.valueOf(LocalDate.now()));
            sanPhamService.add(sp1);
            List<SanPham> list = sanPhamService.getAll0();
            model.addAttribute("hsp", list);
            model.addAttribute("thongBaoCTSP", "Thành công");

            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        } else {
            List<SanPham> list = sanPhamService.getAll0();
            model.addAttribute("hsp", list);
            model.addAttribute("thongBaoCTSP1", "Sản phẩm này còn hàng tồn, không thể đổi trạng thái");

            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/san-pham/khoi-phuc/{id}")
    public String khoiphuc(Model model, @ModelAttribute("dulieuxem") SanPham
            dulieuxem, @PathVariable("id") UUID id, @RequestParam("num") Optional<Integer> num,
                           @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        SanPham sp1 = sanPhamService.findById(id);
        sp1.setTinhTrang(0);
        sp1.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        sanPhamService.add(sp1);
        List<SanPham> list = sanPhamService.getall1();
        model.addAttribute("hsp", list);
        model.addAttribute("contentPage", "../san-pham/san-pham-tung-xoa.jsp");
        return "home/layout";

    }

    @PostMapping("/san-pham/search")
    public String search(@RequestParam("search") String search, @ModelAttribute("dulieuxem") SanPham dulieuxem,
                         @ModelAttribute("ManHinh") ManHinh manHinh,
                         @ModelAttribute("hangSP") HangSanPham hangSanPham,
                         @ModelAttribute("camera") Camera camera,
                         Model model, @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        List<SanPham> list = sanPhamService.search(search);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @PostMapping("/san-pham/search2")
    public String search2(@RequestParam("search") String search, @ModelAttribute("dulieuxem") SanPham
            dulieuxem,
                          @ModelAttribute("ManHinh") ManHinh manHinh,
                          @ModelAttribute("hangSP") HangSanPham hangSanPham,
                          @ModelAttribute("camera") Camera camera,
                          Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        List<SanPham> list = sanPhamService.search2(search);
        model.addAttribute("contentPage", "../san-pham/san-pham-tung-xoa.jsp");
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listHangSP", hangSanPhamService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @GetMapping("/san-pham/khoi-phuc-het")
    public String khoiphuchet(
            Model model,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size
    ) {
        sanPhamService.update0();
        List<SanPham> list = sanPhamService.getall1();
        model.addAttribute("hsp", list);
        model.addAttribute("contentPage", "../san-pham/san-pham-tung-xoa.jsp");
        return "home/layout";
    }

    @PostMapping("/san-pham/loc")
    public String loc(Model model, @RequestParam(value = "hang", required = false) UUID hang,
                      @RequestParam(value = "ManHinh", required = false) UUID moTaMan,
                      @RequestParam(value = "camera", required = false) UUID moTaCam,
                      @ModelAttribute("sanpham") SanPham sanPham
    ) {
        List<SanPham> list = sanPhamService.loc(hang, moTaMan, moTaCam);
        model.addAttribute("hsp", list);
        model.addAttribute("listHangSP", hangSanPhamService.findAll());
        model.addAttribute("listManHinh", manHinhService.findAll());
        model.addAttribute("listCamera", cameraService.findAll());
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/hien-thi-loc")
    public String hienThiLoc(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                             @ModelAttribute("ManHinh") ManHinh manHinh,
                             @ModelAttribute("hangSP") HangSanPham hangSanPham,
                             @ModelAttribute("camera") Camera camera,
                             @ModelAttribute("anh") Anh anh
    ) {
        List<SanPham> chiTietSanPhamPage = sanPhamService.getAll0();
        model.addAttribute("list", chiTietSanPhamPage);
        model.addAttribute("listHang", hangSanPhamService.findAll());
        model.addAttribute("listManHinh", manHinhService.findAll());
        model.addAttribute("listCamera", cameraService.findAll());
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/them-vao-ctsp/{id}")
    public String themVaoCTSP(Model model, @PathVariable("id") UUID id,@ModelAttribute(name = "Pin") Pin pin,
                              @ModelAttribute(name = "chip") Chip chip,
                              @ModelAttribute(name = "ram") Ram ram,
                              @ModelAttribute(name = "mauSac") MauSac mauSac,
                              @ModelAttribute(name = "rom") Rom rom,
                              @ModelAttribute(name = "sanPham") SanPham sanPham,
                              @ModelAttribute(name = "chitietsanpham") ChiTietSanPham chiTietSanPham) {
        SanPham sanPham1 = sanPhamService.findById(id);
        model.addAttribute("selectedSanPham",sanPham1);

        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
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



        return "home/layout";

    }
}
