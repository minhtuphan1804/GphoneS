package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.IMEI;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.services.*;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.util.QRCodeGenerator;
import com.google.zxing.WriterException;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/chi-tiet-san-pham")

@Controller
public class ChiTietSanPhamController {
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
    IMEIService imeiService;
    @Autowired
    BanHangOnlineService banHangOnlineService;
    @Autowired
    BanHangOnLinerepository banHangOnLinerepository;
    private int ton = 0;
    private Date ngay;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("pageNum") Optional<Integer> pageNum
    ) {
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
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
        model.addAttribute("listCTSP", chiTietSanPhamPage);


        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
//        model.addAttribute("batmodalthemsolg", 0);
//        model.addAttribute("idctspdcchon", idctsp);

        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-da-xoa")
    public String hienThiDaXoa(Model model, @RequestParam("pageNum") Optional<Integer> pageNum
    ) {
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOff();
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamPage);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute(name = "Pin") Pin pin,
                          @ModelAttribute(name = "chip") Chip chip,
                          @ModelAttribute(name = "ram") Ram ram,
                          @ModelAttribute(name = "mauSac") MauSac mauSac,
                          @ModelAttribute(name = "rom") Rom rom,
                          @ModelAttribute(name = "sanPham") SanPham sanPham,
                          @ModelAttribute(name = "chitietsanpham") ChiTietSanPham chiTietSanPham) {
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


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "chitietsanpham") ChiTietSanPham chiTietSanPham,
                      BindingResult result, Model model, @RequestParam("images") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors() || chiTietSanPham.getSanPham() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
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
            model.addAttribute("errorSanPham", "Hãy chọn sản phẩm!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");
            model.addAttribute("banHangOnlineService", banHangOnlineService);
            model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getChip() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorChip", "Hãy chọn Chip!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getRom() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
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
            model.addAttribute("errorRom", "Hãy chọn Rom!");
            model.addAttribute("banHangOnlineService", banHangOnlineService);
            model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getMauSac() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorMauSac", "Hãy chọn Màu sắc!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getRam() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorRam", "Hãy chọn Ram!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getPin() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorPin", "Hãy chọn Pin!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (chiTietSanPhamService.existsDuplicate(chiTietSanPham.getSanPham(), chiTietSanPham.getChip(), chiTietSanPham.getRom(), chiTietSanPham.getRam(), chiTietSanPham.getPin(), chiTietSanPham.getMauSac())) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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

            model.addAttribute("thongBaoCTSP1", "Chi tiết sản phẩm đã tồn tại!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        model.addAttribute("thongBaoCTSP", "Thêm thành công <a href=\"/chi-tiet-san-pham/hien-thi-het-hang\">Xem ngay</a>");



        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "src/main/webapp/uploads/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        chiTietSanPham.setUrlAnh(fileName);
        LocalDate localDate = LocalDate.now();
        chiTietSanPham.setNgayTao(Date.valueOf(localDate));
        chiTietSanPham.setTinhTrang(0);
        chiTietSanPham.setSoLuong(1);
        chiTietSanPhamService.add(chiTietSanPham);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();

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
        model.addAttribute("listCTSP", chiTietSanPhamPage);
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        return "home/layout";


    }

    @GetMapping("/view-update/{id}")
    public String viewupdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("chitietsanphamupdate") ChiTietSanPham chiTietSanPham,
                             @ModelAttribute(name = "Pin") Pin pin,
                             @ModelAttribute(name = "chip") Chip chip,
                             @ModelAttribute(name = "ram") Ram ram,
                             @ModelAttribute(name = "mauSac") MauSac mauSac,
                             @ModelAttribute(name = "rom") Rom rom,
                             @ModelAttribute(name = "sanPham") SanPham sanPham) {
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());

        ChiTietSanPham chiTietSanPham1 = chiTietSanPhamService.findById(id);
        ngay = Date.valueOf(chiTietSanPham1.getNgayTao().toString());

        model.addAttribute("chitietsanphamupdate", chiTietSanPham1);
        model.addAttribute("ctsp", chiTietSanPham1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

        return "home/layout";


    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") UUID id, @Valid @ModelAttribute("chitietsanphamupdate") ChiTietSanPham chiTietSanPham,
                         BindingResult result,
                         @RequestParam("anh1s") MultipartFile anh1,
                         @RequestParam("checkanh1") String checkanh1,
                         Model model,
                         @ModelAttribute(name = "Pin") Pin pin,
                         @ModelAttribute(name = "Chip") Chip chip,
                         @ModelAttribute(name = "Ram") Ram ram,
                         @ModelAttribute(name = "MauSac") MauSac mauSac,
                         @ModelAttribute(name = "Rom") Rom rom,
                         @ModelAttribute(name = "SanPham") SanPham sanPham) throws IOException {
        if (result.hasErrors() || chiTietSanPham.getSanPham() == null) {

            model.addAttribute("banHangOnlineService", banHangOnlineService);
            model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
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
            model.addAttribute("errorSanPham", "Hãy chọn sản phẩm!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getChip() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorChip", "Hãy chọn Chip!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getRom() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorRom", "Hãy chọn Rom!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getMauSac() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorMauSac", "Hãy chọn Màu sắc!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getRam() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorRam", "Hãy chọn Ram!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        if (result.hasErrors() || chiTietSanPham.getPin() == null) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());

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
            model.addAttribute("errorPin", "Hãy chọn Pin!");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }

        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());
        if (checkanh1.equals("cu1")) {
        } else {
            if (fileName1.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            }
            chiTietSanPham.setUrlAnh(fileName1);
        }
        ChiTietSanPham ct = chiTietSanPhamService.findById(id);
        LocalDate localDate = LocalDate.now();
        chiTietSanPham.setNgayCapNhat(Date.valueOf(localDate));
        chiTietSanPham.setNgayTao(ngay);
        chiTietSanPham.setTinhTrang(0);
        chiTietSanPham.setKhuyenMai(ct.getKhuyenMai());
        chiTietSanPham.setSoLuong(ct.getSoLuong());

        chiTietSanPhamService.update(id, chiTietSanPham);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
        for (ChiTietSanPham chiTietSanPham1 : chiTietSanPhamPage) {
            if (chiTietSanPham1.getSoLuong() == 0) {
                chiTietSanPham1.setTinhTrang(1);
            } else {
                chiTietSanPham1.setTinhTrang(0);
            }
            chiTietSanPhamService.add(chiTietSanPham1);
        }

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
        model.addAttribute("listCTSP", chiTietSanPhamPage);
        model.addAttribute("thongBaoCTSP", "Thành công");
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";

    }


    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        List<IMEI> listImei = imeiService.statusCTSP(id);
        if (listImei.isEmpty()) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(id);
            chiTietSanPham.setTinhTrang(1);
            chiTietSanPham.setSoLuong(0);
            chiTietSanPhamService.add(chiTietSanPham);
            List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOn();
            model.addAttribute("listCTSP", page);
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
            model.addAttribute("thongBaoCTSP", "Đổi trạng thái thành công!");
            model.addAttribute("banHangOnlineService", banHangOnlineService);
            model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
            model.addAttribute("imei", new IMEI());
            String ma = "IMEI" + imeiService.findAll().size();
            model.addAttribute("ma", ma);
            model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
            return "home/layout";

        } else {
//            model.addAttribute("thongBao", "Sản phẩm này còn hàng tồn, không thể đổi trạng thái");
            List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOn();
            model.addAttribute("listCTSP", page);
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
            model.addAttribute("thongBaoCTSP1", "Sản phẩm này còn hàng tồn, không thể đổi trạng thái");
            model.addAttribute("banHangOnlineService", banHangOnlineService);
            model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
            model.addAttribute("imei", new IMEI());
            String ma = "IMEI" + imeiService.findAll().size();
            model.addAttribute("ma", ma);
            model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/khoi-phuc/{id}")
    public String khoiPhuc(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(id);
        chiTietSanPham.setTinhTrang(0);
        chiTietSanPhamService.add(chiTietSanPham);
        List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOff();
        model.addAttribute("list", page);
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
        model.addAttribute("thongBaoCTSP", "Khôi phục thành công!");
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("listCTSP", chiTietSanPhamService.getAllTTOff());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc-tat-ca")
    public String khoiPhucAll(@RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) throws IOException {

        List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamService.finAllTTOff();
        for (ChiTietSanPham ctsp : chiTietSanPhams) {
            ctsp.setTinhTrang(0);
            ctsp.setNgayCapNhat(Date.valueOf(LocalDate.now()));
            chiTietSanPhamService.add(ctsp);
        }
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("list", page.getContent());
        model.addAttribute("size", page.getSize());
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
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        model.addAttribute("page", page.getNumber());
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        System.in.read();
        return "redirect:/chi-tiet-san-pham/hien-thi-het-hang";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("search") String search,
                         @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                         @ModelAttribute(name = "Pin") Pin pin,
                         @ModelAttribute(name = "chip") Chip chip,
                         @ModelAttribute(name = "ram") Ram ram,
                         @ModelAttribute(name = "mauSac") MauSac mauSac,
                         @ModelAttribute(name = "rom") Rom rom,
                         @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.search(search);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);

        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
//        model.addAttribute("batmodalthemsolg", 0);
//        model.addAttribute("idctspdcchon", idctsp);

        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @PostMapping("/search-da-xoa")
    public String searchDaXoa(Model model, @RequestParam("search") String search,
                              @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                              @ModelAttribute(name = "Pin") Pin pin,
                              @ModelAttribute(name = "chip") Chip chip,
                              @ModelAttribute(name = "ram") Ram ram,
                              @ModelAttribute(name = "mauSac") MauSac mauSac,
                              @ModelAttribute(name = "rom") Rom rom,
                              @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.search1(search);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        ChiTietSanPham chiTietSanPham1 = chiTietSanPhamService.findById(id);
        model.addAttribute("chitietsanpham", chiTietSanPham1);
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("total", chiTietSanPhamPage.getTotalPages());
        model.addAttribute("size", chiTietSanPhamPage.getSize());
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
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        model.addAttribute("page", chiTietSanPhamPage.getNumber());
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("listCTSP", chiTietSanPhamPage.getContent());
        return "home/layout";

    }

    @PostMapping("/loc")
    public String loc(Model model, @RequestParam(value = "hang", required = false) UUID hang,
                      @RequestParam(value = "idSanPham", required = false) UUID idSanPhamm,
                      @RequestParam(value = "ram", required = false) UUID Ram,
                      @RequestParam(value = "rom", required = false) UUID Rom,
                      @RequestParam(value = "dungLuongPin", required = false) UUID dungLuongPin,
                      @RequestParam(value = "chip", required = false) UUID Chip,
                      @RequestParam(value = "manHinh", required = false) UUID moTaMan,
                      @RequestParam(value = "camera", required = false) UUID moTaCam,
                      @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                      @ModelAttribute(name = "Pin") Pin pin,
                      @ModelAttribute(name = "chip") Chip chip,
                      @ModelAttribute(name = "ram") Ram ram,
                      @ModelAttribute(name = "mauSac") MauSac mauSac,
                      @ModelAttribute(name = "rom") Rom rom,
                      @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.loc(idSanPhamm, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
//        model.addAttribute("batmodalthemsolg", 0);
//        model.addAttribute("idctspdcchon", idctsp);

        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }


    //thắng thêm
    @GetMapping("/hien-thi-het-hang")
    public String hienThisanphamhethang(Model model, @RequestParam("pageNum") Optional<Integer> pageNum
            , @ModelAttribute("imei") IMEI imei
    ) {
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
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
        model.addAttribute("listCTSP", chiTietSanPhamPage);


        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "";
        if (imeiService.findAll().size() < 10) {
            ma = "IMEI0" + imeiService.findAll().size();
        } else {
            ma = "IMEI" + imeiService.findAll().size();
        }
        model.addAttribute("ma", ma);

        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        return "home/layout";
    }


    @PostMapping("/searchhethang")
    public String searchhethang(Model model, @RequestParam("search") String search,
                                @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                                @ModelAttribute(name = "Pin") Pin pin,
                                @ModelAttribute(name = "chip") Chip chip,
                                @ModelAttribute(name = "ram") Ram ram,
                                @ModelAttribute(name = "mauSac") MauSac mauSac,
                                @ModelAttribute(name = "rom") Rom rom,
                                @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.search(search);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);

        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);

        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        return "home/layout";
    }

    @PostMapping("/lochethang")
    public String lochethang(Model model, @RequestParam(value = "hang", required = false) UUID hang,
                             @RequestParam(value = "ram", required = false) UUID Ram,
                             @RequestParam(value = "idSanPham", required = false) UUID idSanPham,
                             @RequestParam(value = "rom", required = false) UUID Rom,
                             @RequestParam(value = "dungLuongPin", required = false) UUID dungLuongPin,
                             @RequestParam(value = "chip", required = false) UUID Chip,
                             @RequestParam(value = "manHinh", required = false) UUID moTaMan,
                             @RequestParam(value = "camera", required = false) UUID moTaCam,
                             @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                             @ModelAttribute(name = "Pin") Pin pin,
                             @ModelAttribute(name = "chip") Chip chip,
                             @ModelAttribute(name = "ram") Ram ram,
                             @ModelAttribute(name = "mauSac") MauSac mauSac,
                             @ModelAttribute(name = "rom") Rom rom,
                             @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.loc(idSanPham, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
//        model.addAttribute("batmodalthemsolg", 0);
//        model.addAttribute("idctspdcchon", idctsp);
//        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");

        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        return "home/layout";
    }


    @PostMapping("/locngungban")
    public String locngungban(Model model, @RequestParam(value = "hang", required = false) UUID hang,
                              @RequestParam(value = "ram", required = false) UUID Ram,
                              @RequestParam(value = "rom", required = false) UUID Rom,
                              @RequestParam(value = "idSanPham", required = false) UUID idSanPham,
                              @RequestParam(value = "dungLuongPin", required = false) UUID dungLuongPin,
                              @RequestParam(value = "chip", required = false) UUID Chip,
                              @RequestParam(value = "manHinh", required = false) UUID moTaMan,
                              @RequestParam(value = "camera", required = false) UUID moTaCam,
                              @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                              @ModelAttribute(name = "Pin") Pin pin,
                              @ModelAttribute(name = "chip") Chip chip,
                              @ModelAttribute(name = "ram") Ram ram,
                              @ModelAttribute(name = "mauSac") MauSac mauSac,
                              @ModelAttribute(name = "rom") Rom rom,
                              @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = banHangOnLinerepository.locctspngungban(idSanPham,hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listCTSP", list);
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
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);


        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-da-xoa.jsp");
        return "home/layout";
    }


    @GetMapping("/view-them-nhanh-solg/{idctsp}/{tengiaodien}")
    public String viewAd(Model model, @ModelAttribute("imei") IMEI imei
            , @RequestParam("pageNum") Optional<Integer> pageNum,
                         @PathVariable("idctsp") UUID idctsp,
                         @PathVariable("tengiaodien") String tengiaodien

    ) {

        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
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
        model.addAttribute("listCTSP", chiTietSanPhamPage);


        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("batmodalthemsolg", 0);
        model.addAttribute("idctspdcchon", idctsp);


        if (tengiaodien.equals("giaodienhienthi")) {
            model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");

        } else if (tengiaodien.equals("giaodienhethang")) {
            model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        }


        return "home/layout";
    }

    @PostMapping("/themsolg/{tengiaodien}")
    public String add(@Valid @ModelAttribute(name = "imei") IMEI imei,
                      BindingResult result, Model model,
                      @PathVariable("tengiaodien") String tengiaodien
    ) throws IOException, WriterException {
//
        model.addAttribute("banHangOnlineService", banHangOnlineService);
        model.addAttribute("banHangOnLinerepository", banHangOnLinerepository);
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
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
        model.addAttribute("listCTSP", chiTietSanPhamPage);


        if (result.hasErrors() || imei.getChiTietSanPham() == null) {

            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
            String ma = "";
            if (imeiService.findAll().size() < 10) {
                ma = "IMEI0" + imeiService.findAll().size();
            } else {
                ma = "IMEI" + imeiService.findAll().size();
            }
            model.addAttribute("ma", ma);
//            model.addAttribute("tbidctsp", "Hãy chọn sản phẩm!");
            model.addAttribute("batmodalthemsolg", 0);
            model.addAttribute("idctspdcchon", imei.getChiTietSanPham().getId());
            if (tengiaodien.equals("giaodienhienthi")) {
                model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");

            } else if (tengiaodien.equals("giaodienhethang")) {
                model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
            }
            return "home/layout";

        }


        if (imeiService.existImei(imei.getSoImei())) {
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());

            String ma = "";
            if (imeiService.findAll().size() < 10) {
                ma = "IMEI0" + imeiService.findAll().size();
            } else {
                ma = "IMEI" + imeiService.findAll().size();
            }
            model.addAttribute("ma", ma);
            model.addAttribute("tbimei", "IMEI này đã có trong dữ liệu");
//            model.addAttribute("thongBaoCTSP1", "Imei đã tồn tại!");
            model.addAttribute("batmodalthemsolg", 0);
            model.addAttribute("idctspdcchon", imei.getChiTietSanPham().getId());
            if (tengiaodien.equals("giaodienhienthi")) {
                model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");

            } else if (tengiaodien.equals("giaodienhethang")) {
                model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
            }
            return "home/layout";
        }


        String projectRootPath = System.getProperty("user.dir");
        String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
        QRCodeGenerator.generatorQRCode(imei, outputFolderPath);
        imei.setMaQr(imei.getSoImei() + ".png");

        LocalDate localDate = LocalDate.now();
        imei.setNgayTao(Date.valueOf(localDate));
        imei.setTinhTrang(0);
        imeiService.add(imei);


        String ma = "";
        if (imeiService.findAll().size() < 10) {
            ma = "IMEI0" + imeiService.findAll().size();
        } else {
            ma = "IMEI" + imeiService.findAll().size();
        }

        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("ma", ma);
        model.addAttribute("tbidctsp", "Thêm imei thành công!");
        model.addAttribute("imei", new IMEI());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("batmodalthemsolg", 0);
        model.addAttribute("idctspdcchon", imei.getChiTietSanPham().getId());
        if (tengiaodien.equals("giaodienhienthi")) {
            model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");

        } else if (tengiaodien.equals("giaodienhethang")) {
            model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-het-hang.jsp");
        }
        return "home/layout";


    }
    //thắng hết thêm
}
