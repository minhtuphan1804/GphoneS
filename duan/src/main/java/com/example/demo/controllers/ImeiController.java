package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.IMEIRepository;
import com.example.demo.services.*;
import com.example.demo.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/imei")
public class ImeiController {
    @Autowired
    IMEIService imeiService;
    @Autowired
    IMEIRepository imeiRepository;
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
    private Date ngay;
    private UUID idCu;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        List<IMEI> imeiPage = imeiService.getImeiOn();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";

    }

    @PostMapping("/search-on")
    public String searchOn(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOn(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";

    }

    @PostMapping("/search-off-1")
    public String searchOff1(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOff(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/imei-da-ban.jsp");
        return "home/layout";
    }

    @PostMapping("/search-off-2")
    public String searchOff2(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOff2(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/imei-da-xoa.jsp");
        return "home/layout";
    }


    @GetMapping("/hien-thi-da-ban")
    public String hienThiDaBan(Model model) {
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        List<IMEI> imeiPage = imeiService.getImeiOfff();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/imei-da-ban.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-da-xoa")
    public String hienThiDaXoa(Model model) {
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        List<IMEI> imeiPage = imeiService.getImeiOff3();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/imei-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-imei-loi")
    public String hienThiImeiLoi(Model model, @ModelAttribute("IMEILoi") IMEI imeiLoi) {
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        List<IMEI> imeiPage = imeiService.findImeiLoi();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/imei-loi.jsp");
        return "home/layout";
    }

    @PostMapping("/search-imei-loi")
    public String searchImeiLoi(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchImeiLoi(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/imei-loi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc-imei-loi/{id}")
    public String khoiPhucImeiLoi(@PathVariable("id") UUID id, Model model, @ModelAttribute("IMEILoi") IMEI imeiLoi) {
        imeiLoi = imeiRepository.findById(id).orElse(null);
        idCu = id;
        model.addAttribute("IMEILoi", imeiLoi);
        model.addAttribute("listImei", imeiService.findImeiLoi());
        model.addAttribute("batmodaldetailupdatekm", 0);
        model.addAttribute("contentPage", "../imei/imei-loi.jsp");
        return "home/layout";
    }

    @PostMapping("/thay-doi-imei-loi")
    public String CapNhatIMEI(Model model, @ModelAttribute("IMEILoi") IMEI imeiLoi,
                              @RequestParam("imeiMoi") String newIMEI) throws IOException, WriterException {
        if (newIMEI.isEmpty() || newIMEI.length() < 15 || newIMEI.length() > 15) {
            model.addAttribute("thongBao", "IMEI không được để trống và có độ dài là 15 ký tự");
            imeiLoi = imeiRepository.findById(idCu).orElse(null);
            model.addAttribute("IMEILoi", imeiLoi);
            model.addAttribute("listImei", imeiService.findImeiLoi());
            model.addAttribute("batmodaldetailupdatekm", 0);
            model.addAttribute("contentPage", "../imei/imei-loi.jsp");
            return "home/layout";
        } else {
            Pattern pattern = Pattern.compile("^([0-9]{15})$");
            boolean valid = pattern.matcher(newIMEI).matches();
            if (valid) {
                if (imeiService.existImei(newIMEI)) {
                    model.addAttribute("thongBao", "IMEI này đã có trong dữ liệu");
                    imeiLoi = imeiRepository.findById(idCu).orElse(null);
                    model.addAttribute("IMEILoi", imeiLoi);
                    model.addAttribute("listImei", imeiService.findImeiLoi());
                    model.addAttribute("batmodaldetailupdatekm", 0);
                    model.addAttribute("contentPage", "../imei/imei-loi.jsp");
                    return "home/layout";
                }
                IMEI imei = imeiService.findById(idCu);
                String lichSuThayDoi = "Thông tin IMEI cũ: "
                        + imei.getChiTietSanPham().getSanPham().getTen() + " - "
                        + imei.getChiTietSanPham().getSanPham().getHangSanPham().getTen() + " - "
                        + imei.getChiTietSanPham().getChip().getTen() + " - "
                        + imei.getChiTietSanPham().getRam().getDungLuong() + " - "
                        + imei.getChiTietSanPham().getRom().getDungLuong() + " - "
                        + imei.getSoImei();
                imei.setLichSu(lichSuThayDoi);
                imei.setSoImei(newIMEI);
                imei.setNgayCapNhat(Date.valueOf(LocalDate.now()));
                imei.setTinhTrang(0);
                String projectRootPath = System.getProperty("user.dir");
                String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
                QRCodeGenerator.generatorQRCode(imei, outputFolderPath);
                imei.setMaQr(imei.getSoImei() + ".png");
                imeiService.update(idCu, imei);
                model.addAttribute("thongBaoThanhCong", "IMEI đã được cập nhật thành công");
                model.addAttribute("listImei", imeiService.findImeiLoi());
                model.addAttribute("contentPage", "../imei/imei-loi.jsp");
                return "home/layout";
            } else {
                model.addAttribute("thongBao", "IMEI không đúng định dạng");
                imeiLoi = imeiRepository.findById(idCu).orElse(null);
                model.addAttribute("IMEILoi", imeiLoi);
                model.addAttribute("listImei", imeiService.findImeiLoi());
                model.addAttribute("batmodaldetailupdatekm", 0);
                model.addAttribute("contentPage", "../imei/imei-loi.jsp");
                return "home/layout";
            }
        }

    }

    @GetMapping("/khoi-phuc-tat-ca-imei-loi")
    public String khoiPhucAllImeiLoi() {
        List<IMEI> list = imeiService.findImeiLoi();
        for (IMEI imei : list) {
            imei.setTinhTrang(0);
            imeiService.add(imei);
        }
        return "redirect:/imei/hien-thi-imei-loi";
    }


    @GetMapping("/khoi-phuc/{id}")
    public String khoiPhuc(@PathVariable("id") UUID id) {
        IMEI imei = imeiService.findById(id);
        imei.setTinhTrang(0);
        imeiService.add(imei);
        return "redirect:/imei/hien-thi-da-xoa";
    }

    @GetMapping("/khoi-phuc-tat-ca")
    public String khoiPhucAll() throws IOException {
        List<IMEI> list = imeiService.findAll3();
        for (IMEI imei : list) {
            imei.setTinhTrang(0);
            imeiService.add(imei);
        }
        System.in.read();
        return "redirect:/imei/hien-thi-da-xoa";
    }

    @GetMapping("/view-add")
    public String viewAd(Model model, @ModelAttribute("imei") IMEI imei) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/add-imei.jsp");

        return "home/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "imei") IMEI imei,
                      BindingResult result, Model model) throws IOException, WriterException {

        if (result.hasErrors() || imei.getChiTietSanPham() == null) {

            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
            String ma = "IMEI" + imeiService.findAll().size();
            model.addAttribute("ma", ma);
            model.addAttribute("tb", "Hãy chọn sản phẩm!");
            model.addAttribute("contentPage", "../imei/add-imei.jsp");
            return "home/layout";

        }
        String ma = "IMEI" + (imeiService.findAll().size() + 1);
        imei.setMa(ma);

        if (imeiService.existImei(imei.getSoImei())) {
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());


            model.addAttribute("ma", ma);
            model.addAttribute("thongBao", "IMEI này đã có trong dữ liệu");
            model.addAttribute("thongBaoCTSP1", "Imei đã tồn tại!");
            model.addAttribute("contentPage", "../imei/add-imei.jsp");
            return "home/layout";
        }

        String projectRootPath = System.getProperty("user.dir");
        String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
        QRCodeGenerator.generatorQRCode(imei, outputFolderPath);
        imei.setMaQr(imei.getSoImei() + ".png");

        LocalDate localDate = LocalDate.now();
        imei.setNgayTao(Date.valueOf(localDate));
        imeiService.add(imei);
        List<IMEI> imeiPage = imeiService.getImeiOn();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("thongBaoCTSP", "Thêm imei thành công!");

        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";


    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id) {
        imeiService.delete(id);
        return "redirect:/imei/hien-thi";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("imeiupdate") IMEI imei) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        IMEI imei1 = imeiService.findById(id);
        model.addAttribute("imeiupdate", imei1);
        model.addAttribute("contentPage", "../imei/update-imei.jsp");
        ngay = Date.valueOf(imei1.getNgayTao().toString());
        return "home/layout";


    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("imeiupdate") IMEI imei,
                         BindingResult result) throws IOException, WriterException {
        if (result.hasErrors()) {
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
            model.addAttribute("contentPage", "../imei/update-imei.jsp");
            return "home/layout";
        }
        IMEI imei1 = imeiService.findById(id);
        imei1.setMa(imei.getMa());
        imei1.setNgayTao(ngay);
        imei1.setChiTietSanPham(imei.getChiTietSanPham());
        imei1.setSoImei(imei.getSoImei());
        imei1.setMoTa(imei.getMoTa());
        LocalDate localDate = LocalDate.now();
        imei1.setNgayCapNhat(Date.valueOf(localDate));
        imei1.setTinhTrang(imei.getTinhTrang());
        if (imei1.getSoImei().equals(imei.getSoImei())) {
            imeiService.update(id, imei1);
            return "redirect:/imei/hien-thi";
        } else {
            String projectRootPath = System.getProperty("user.dir");
            String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
            QRCodeGenerator.generatorQRCode(imei, outputFolderPath);
            imei.setMaQr(imei.getSoImei() + ".png");
            imeiService.update(id, imei1);
            return "redirect:/imei/hien-thi";
        }
    }

    @ResponseBody
    @GetMapping("/show-qr/{id}")
    public List<IMEI> showQR(Model model, @PathVariable("id") UUID id) {
        List<IMEI> list = imeiService.showIMEI(id);
        System.out.println(1);
        return list;
    }

    @GetMapping("/chinh-sach")
    public String hienThiChinhSach(Model model) {
        model.addAttribute("contentPage", "../doitra/chinh-sach-admin.jsp");
        return "home/layout";

    }

    //chinh-sach-imei-loi
    @GetMapping("/chinh-sach-xu-ly-imei-loi")
    public String chinhSachXuLyImeiLoi(Model model) {
//        List<IMEI> imeiPage = imeiService.findImeiLoi();
//        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/chinh-sach-xu-ly-imei-loi.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-chua-ban")
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
        List<IMEI> list = imeiRepository.locChuaBan(idSanPhamm, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listImei", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("thongBaoCTSP", "Lọc dữ liệu thành công");

        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-da-ban")
    public String locDaBan(Model model, @RequestParam(value = "hang", required = false) UUID hang,
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
        List<IMEI> list = imeiRepository.locDaBan(idSanPhamm, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listImei", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("thongBaoCTSP", "Lọc dữ liệu thành công");

        model.addAttribute("contentPage", "../imei/imei-da-ban.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-da-xoa")
    public String locDaXoa(Model model, @RequestParam(value = "hang", required = false) UUID hang,
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
        List<IMEI> list = imeiRepository.locDaXoa(idSanPhamm, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listImei", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("thongBaoCTSP", "Lọc dữ liệu thành công");

        model.addAttribute("contentPage", "../imei/imei-da-xoa.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-loi")
    public String locLoi(Model model, @RequestParam(value = "hang", required = false) UUID hang,
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
                         @ModelAttribute(name = "sanPham") SanPham sanPham
                        , @ModelAttribute("IMEILoi") IMEI imeiLoi) {
        List<IMEI> list = imeiRepository.locLoi(idSanPhamm, hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listImei", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");

        model.addAttribute("contentPage", "../imei/imei-loi.jsp");
        return "home/layout";
    }
}
