package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.GioHangChiTiet;
import com.example.demo.models.KhuyenMai;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.repositories.KhuyenMaiRepository;
import com.example.demo.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Controller
public class KhuyenMaiController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private IMEIService imeiService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private RomService romService;
    @Autowired
    private PinService pinService;
    @Autowired
    private HangSanPhamService hangSanPhamService;
    @Autowired
    private HangKhachHangService hangKhachHangService;
    @Autowired
    private DungLuongPinService dungLuongPinService;
    @Autowired
    private ManHinhService manHinhService;
    @Autowired
    private CameraService cameraService;
    @Autowired
    private BanHangOnlineService banHangOnlineService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private DataIntermediateService dataService;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DoiTraChiTietService doiTraChiTietService;

    @Autowired
    DoiTraService doiTraService;

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Scheduled(fixedRate = 1000)
    public void soCTSPbangsoIMEI() {
        for (ChiTietSanPham km : chiTietSanPhamService.findAll()) {
//            km.setSoLuong(banHangOnlineService.soluongcon(String.valueOf(km.getId())));
//            chiTietSanPhamService.add(km);
        }
        khuyenMaiRepository.xoalienketKM1();
    }

    ;

    @Scheduled(fixedRate = 1000)
    public void kiemtrangayhientaiVSkhoangtimegiamgia() {
        for (KhuyenMai km : khuyenMaiRepository.findAll()) {
            String batdau = km.getNgayBatDau();
            String ketthuc = km.getNgayKetThuc();

// Chuyển đổi chuỗi abc thành đối tượng LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime batdauDateTime = LocalDateTime.parse(batdau, formatter);
            LocalDateTime ketthucDateTime = LocalDateTime.parse(ketthuc, formatter);

// Lấy thời gian hiện tại và định dạng nó
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);


            // Kiểm tra xem currentDateTime có nằm trong khoảng [batdauDateTime, ketthucDateTime] không
            if (currentDateTime.isAfter(batdauDateTime) && currentDateTime.isBefore(ketthucDateTime)) {
                km.setTinhTrang(0);
                khuyenMaiRepository.save(km);
            } else if (currentDateTime.isBefore(batdauDateTime)) {
                km.setTinhTrang(2); // hoặc làm gì đó tương ứng với trường hợp này
                khuyenMaiRepository.save(km);
            } else {
                km.setTinhTrang(1);
                khuyenMaiRepository.save(km);
            }
        }
        khuyenMaiRepository.xoalienketKM1();
    }

    ;

    @Scheduled(fixedRate = 1000)
    public void loadtiengiamghct() {
        for (GioHangChiTiet ghct : gioHangChiTietService.findAll()) {

            ChiTietSanPham ctsp = chiTietSanPhamService.findById(ghct.getChiTietSanPham().getId());
            UUID idkm = null;
            try {
                idkm = ctsp.getKhuyenMai().getId();
            } catch (Exception e) {
                idkm = null;
            }
            if (idkm == null) {
                ghct.setDonGiaKhiGiam(ctsp.getGiaBan());
                gioHangChiTietService.add(ghct);
            } else {
                KhuyenMai km = khuyenMaiRepository.findById(idkm).get();
                if (km.getTinhTrang() == 0) {
                    Long giagoc = Long.valueOf(String.valueOf(ctsp.getGiaBan()));
                    Long phamtramgiam = Long.valueOf(String.valueOf(km.getSoTienGiam()));
                    ghct.setDonGiaKhiGiam(BigDecimal.valueOf(giagoc - giagoc / 100 * phamtramgiam));
                    gioHangChiTietService.add(ghct);
                } else {
                    ghct.setDonGiaKhiGiam(ctsp.getGiaBan());
                    gioHangChiTietService.add(ghct);
                }
            }

            GioHangChiTiet ghctdg = ghct;
            ghctdg.setDonGia(ctsp.getGiaBan());
            gioHangChiTietService.add(ghctdg);
        }
    }

    ;


    public String dinhangCHUOIsangJSP(String ngaycan) {
        // Input date and time string
//        String inputDateTime = "29-11-2023 17:09:00";
        String inputDateTime = ngaycan;

        // Define the input format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Parse the input string
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);

        // Define the output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Format the LocalDateTime to the desired output format
        return dateTime.format(outputFormatter);

    }

    public Integer kt(Integer so) {
        if (so == Integer.valueOf(0)) {
            return Integer.valueOf(1);
        }
        return so;
    }

    public String dinhdangngaytujsp(String inputString) {


        // Tạo đối tượng LocalDateTime từ chuỗi
        LocalDateTime dateTime = LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


    }


    public boolean isNgayKetThucAfterNgayBatDau(String ngayBatDau, String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi
        LocalDateTime ngayBT = LocalDateTime.parse(ngayBatDau, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime ngayKT = LocalDateTime.parse(ngayKetThuc, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        String ngayBT1 = ngayBT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String ngayKT1 = ngayKT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime batDauDateTime = LocalDateTime.parse(ngayBT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKT1, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(batDauDateTime);
    }

    public boolean isNgayKetThucAfterNgayBatDauupdate(String ngayBatDau, String ngayKetThuc) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime batDauDateTime = LocalDateTime.parse(ngayBatDau, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKetThuc, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(batDauDateTime);
    }

    public boolean isNgayKetThucAfterNgayHienTai(String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi

        LocalDateTime ngayKT = LocalDateTime.parse(ngayKetThuc, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String ngayHT1 = currentDateTime.format(formatter);
        String ngayKT1 = ngayKT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime hienTaiDateTime = LocalDateTime.parse(ngayHT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKT1, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(hienTaiDateTime);
    }

    public boolean isNgayKetThucAfterNgayHienTaiupdate(String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi


        // Định dạng lại theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String ngayHT1 = currentDateTime.format(formatter);


        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime hienTaiDateTime = LocalDateTime.parse(ngayHT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKetThuc, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(hienTaiDateTime);
    }

    @GetMapping("/khuyen-mai/hien-thi")
    public String hienThi(
            Model model,

            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
//            @ModelAttribute("khmodal") KhachHang khachHang1
    ) {
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);

//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "home/layout";
    }

//    @GetMapping("/khuyen-mai/hien-thi1")
//    public String hienThi11(
//            Model model
//
//
//    ) {
//
//        model.addAttribute("contentPage","../khuyen-mai/vi-du.jsp");
//        return "khuyen-mai/vi-du";
//    }


    @PostMapping("/khuyen-mai/add")
    public String updateadd(Model model,
//
//
                            @ModelAttribute("km") @Valid KhuyenMai khuyenMai,
                            BindingResult bindingResult,
//                            @RequestParam("num") Optional<Integer> num,
//                            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate
//
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);
        if (bindingResult.hasErrors()) {
            model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//            Sort sort = Sort.by("ma").descending();
//            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//            Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//            model.addAttribute("total", kt(list.getTotalPages()));

            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("momdalthemkm", 0);
            return "home/layout";
        }

        if (isNgayKetThucAfterNgayBatDau(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc()) == false) {
            model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
            Sort sort = Sort.by("ma").descending();
//            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//            Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);

            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//            model.addAttribute("total", kt(list.getTotalPages()));

            model.addAttribute("momdalthongbaongayKT", "Ngày kết thúc phải sau ngày bắt đầu");
            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("momdalthemkm", 0);

            model.addAttribute("hangds", hangSanPhamService.findAll0());
            model.addAttribute("camds", cameraService.findAll0());
            model.addAttribute("mands", manHinhService.findAll0());
            model.addAttribute("mauds", mauSacService.findAll0());
            model.addAttribute("ramds", ramService.findAll0());
            model.addAttribute("romds", romService.findAll0());
            model.addAttribute("pinds", pinService.findAll0());
            model.addAttribute("dungds", dungLuongPinService.findAll0());
            model.addAttribute("chipds", chipService.findAll0());
            model.addAttribute("sands", sanPhamService.findAll0());
            //        //gia max
            Long max = Long.valueOf('0');
            for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

                if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                    max = Long.valueOf(String.valueOf(ct.getGiaBan()));
                }
            }
//System.out.println("taco---"+max);
            model.addAttribute("max", String.valueOf(max));
            return "home/layout";
        }


        if (isNgayKetThucAfterNgayHienTai(khuyenMai.getNgayKetThuc()) == false) {
//            model.addAttribute("khuyenMaiRepository",khuyenMaiRepository);
//            Sort sort = Sort.by("ma").descending();
//            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//            Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);

            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//            model.addAttribute("total", kt(list.getTotalPages()));

            model.addAttribute("momdalthongbaongayKT", "Ngày kết thúc phải sau ngày hiện tại");
            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("momdalthemkm", 0);

            model.addAttribute("hangds", hangSanPhamService.findAll0());
            model.addAttribute("camds", cameraService.findAll0());
            model.addAttribute("mands", manHinhService.findAll0());
            model.addAttribute("mauds", mauSacService.findAll0());
            model.addAttribute("ramds", ramService.findAll0());
            model.addAttribute("romds", romService.findAll0());
            model.addAttribute("pinds", pinService.findAll0());
            model.addAttribute("dungds", dungLuongPinService.findAll0());
            model.addAttribute("chipds", chipService.findAll0());
            model.addAttribute("sands", sanPhamService.findAll0());
            //        //gia max
            Long max = Long.valueOf('0');
            for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

                if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                    max = Long.valueOf(String.valueOf(ct.getGiaBan()));
                }
            }
//System.out.println("taco---"+max);
            model.addAttribute("max", String.valueOf(max));
            return "home/layout";
        }
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(0, 1, sort);
        List<KhuyenMai> litskm1 = khuyenMaiRepository.danhsachgiamdan();
        String mhd = "";
        if (litskm1.size() <= 0) {
            mhd = "MKM00";
        } else {
            String inputString = litskm1.get(0).getMa();

            // Sử dụng phương thức substring để tách chuỗi
            String prefix = inputString.substring(0, 3); // Lấy 3 ký tự đầu tiên
            String suffix = inputString.substring(3);


            Integer sl = Integer.valueOf(suffix) + 1;

            if (sl < 10) {
                mhd = "MKM0" + sl;
            } else {
                mhd = "MKM" + sl;
            }
        }

        khuyenMai.setMa(mhd);
        khuyenMai.setLoaiGiamGia("%");
        khuyenMai.setHinhThucGiamGia("%");
        khuyenMai.setNgayTao(formattedDateTime);
        khuyenMai.setNgayCapNhat(formattedDateTime);
        khuyenMai.setNgayBatDau(dinhdangngaytujsp(khuyenMai.getNgayBatDau()));
        khuyenMai.setNgayKetThuc(dinhdangngaytujsp(khuyenMai.getNgayKetThuc()));
        khuyenMai.setTinhTrang(0);
        khuyenMaiRepository.save(khuyenMai);


        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("km", new KhuyenMai());
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
        model.addAttribute("tbThemKMOK", 0);
        model.addAttribute("NDTB", "Thêm khuyến mãi thành công");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "home/layout";
    }

    UUID idkmdangchon = null;

    @GetMapping("/khuyen-mai/ap-dung-khuyen-mai/{idkm}")
    public String apdungkhuyenmai(
            Model model,
            @PathVariable("idkm") UUID idkm,
            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        KhuyenMai kmchon = khuyenMaiRepository.findById(idkm).orElse(null);
        model.addAttribute("kmchon", kmchon);
        idkmdangchon = idkm;
        model.addAttribute("batmodalapdungkm", 0);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "home/layout";
    }


    @GetMapping("/khuyen-mai/huy-ap-dung-vao-1-ctsp/{idctsp}/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}/{x11}/{x12}")
    public String huyapdungkmvao1CTSP(
            Model model,
            @PathVariable("idctsp") UUID idctsp,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5,
            @PathVariable("x6") String x6,
            @PathVariable("x7") String x7,
            @PathVariable("x8") String x8,
            @PathVariable("x9") String x9,
            @PathVariable("x10") String x10,
            @PathVariable("x11") String x11,
            @PathVariable("x12") String x12


    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        khuyenMaiRepository.HuyApDungKMvs1ctsp(idctsp);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
        model.addAttribute("kmchon", khuyenMaiRepository.findById(idkmdangchon).orElse(null));
        model.addAttribute("listctsp", banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12))));


        return "khuyen-mai/single_pase_giao-dien-loc-khuyen-mai";
    }

    @GetMapping("/khuyen-mai/chi-tiet-san-pham-ap-dung-khuyen-mai/{idctsp}/{idkm}/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}/{x11}/{x12}")
    public String apdungkmVSctsp(
            Model model,
            @PathVariable("idctsp") UUID idctsp,
            @PathVariable("idkm") UUID idkm,
            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate,
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5,
            @PathVariable("x6") String x6,
            @PathVariable("x7") String x7,
            @PathVariable("x8") String x8,
            @PathVariable("x9") String x9,
            @PathVariable("x10") String x10,
            @PathVariable("x11") String x11,
            @PathVariable("x12") String x12

    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(idctsp);
        ctsp.setKhuyenMai(khuyenMaiRepository.findById(idkm).orElse(null));
        chiTietSanPhamService.add(ctsp);
//        model.addAttribute("tbapdungKMvsCTSP",0);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);

        model.addAttribute("listctsp", banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12))));

        KhuyenMai kmchon = khuyenMaiRepository.findById(idkm).orElse(null);
        model.addAttribute("kmchon", kmchon);
//        model.addAttribute("batmodalapdungkm",0);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
//        model.addAttribute("dulieu", list.getContent());
//        model.addAttribute("total", kt(list.getTotalPages()));
//        model.addAttribute("contentPage","../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "khuyen-mai/single_pase_giao-dien-loc-khuyen-mai";
    }


    @GetMapping("/khuyen-mai/detail-khuyen-mai/{idkm}")
    public String detaidkmdeupdate(
            Model model,
            @PathVariable("idkm") UUID idkm,
            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        khuyenMaiupdate = khuyenMaiRepository.findById(idkm).orElse(null);
        String NBTCC = khuyenMaiupdate.getNgayBatDau();
        String NKTCC = khuyenMaiupdate.getNgayKetThuc();

        model.addAttribute("NBTCC", dinhangCHUOIsangJSP(NBTCC));
        model.addAttribute("NKTCC", dinhangCHUOIsangJSP(NKTCC));

        model.addAttribute("kmupdate", khuyenMaiupdate);
        model.addAttribute("kmupdate", khuyenMaiupdate);
        model.addAttribute("kmchon", khuyenMaiupdate);
        model.addAttribute("batmodaldetailupdatekm", 0);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "home/layout";
    }


    @PostMapping("/khuyen-mai/update")
    public String updateKM(Model model,
                           @ModelAttribute("kmupdate") @Valid KhuyenMai khuyenMaiupdate,
                           BindingResult bindingResult,
                           @ModelAttribute("km") KhuyenMai khuyenMai
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);
        if (bindingResult.hasErrors()) {
            if (khuyenMaiupdate.getNgayBatDau().isEmpty() == true && khuyenMaiupdate.getNgayKetThuc().isEmpty() == false) {
                model.addAttribute("NKTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayKetThuc()));
            } else if (khuyenMaiupdate.getNgayBatDau().isEmpty() == false && khuyenMaiupdate.getNgayKetThuc().isEmpty() == true) {
                model.addAttribute("NBTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayBatDau()));
            } else if (khuyenMaiupdate.getNgayBatDau().isEmpty() == false && khuyenMaiupdate.getNgayKetThuc().isEmpty() == false) {
                model.addAttribute("NBTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayBatDau()));
                model.addAttribute("NKTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayKetThuc()));
            } else {
            }
            model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("batmodaldetailupdatekm", 0);

            model.addAttribute("hangds", hangSanPhamService.findAll0());
            model.addAttribute("camds", cameraService.findAll0());
            model.addAttribute("mands", manHinhService.findAll0());
            model.addAttribute("mauds", mauSacService.findAll0());
            model.addAttribute("ramds", ramService.findAll0());
            model.addAttribute("romds", romService.findAll0());
            model.addAttribute("pinds", pinService.findAll0());
            model.addAttribute("dungds", dungLuongPinService.findAll0());
            model.addAttribute("chipds", chipService.findAll0());
            model.addAttribute("sands", sanPhamService.findAll0());
            Long max = Long.valueOf('0');
            for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

                if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                    max = Long.valueOf(String.valueOf(ct.getGiaBan()));
                }
            }
            model.addAttribute("max", String.valueOf(max));
            return "home/layout";
        }

        if (isNgayKetThucAfterNgayBatDauupdate(khuyenMaiupdate.getNgayBatDau(), khuyenMaiupdate.getNgayKetThuc()) == false) {
            model.addAttribute("NBTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayBatDau()));
            model.addAttribute("NKTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayKetThuc()));
            model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//            Sort sort = Sort.by("ma").descending();
//            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//            Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);

            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//            model.addAttribute("total", kt(list.getTotalPages()));

            model.addAttribute("momdalthongbaongayKT1", "Ngày kết thúc phải sau ngày bắt đầu");
            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("batmodaldetailupdatekm", 0);

            model.addAttribute("hangds", hangSanPhamService.findAll0());
            model.addAttribute("camds", cameraService.findAll0());
            model.addAttribute("mands", manHinhService.findAll0());
            model.addAttribute("mauds", mauSacService.findAll0());
            model.addAttribute("ramds", ramService.findAll0());
            model.addAttribute("romds", romService.findAll0());
            model.addAttribute("pinds", pinService.findAll0());
            model.addAttribute("dungds", dungLuongPinService.findAll0());
            model.addAttribute("chipds", chipService.findAll0());
            model.addAttribute("sands", sanPhamService.findAll0());
            //        //gia max
            Long max = Long.valueOf('0');
            for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

                if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                    max = Long.valueOf(String.valueOf(ct.getGiaBan()));
                }
            }
//System.out.println("taco---"+max);
            model.addAttribute("max", String.valueOf(max));
            return "home/layout";
        }


        if (isNgayKetThucAfterNgayHienTaiupdate(khuyenMaiupdate.getNgayKetThuc()) == false) {
            model.addAttribute("NBTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayBatDau()));
            model.addAttribute("NKTCC", dinhangCHUOIsangJSP(khuyenMaiupdate.getNgayKetThuc()));
            model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);

            model.addAttribute("dulieu", khuyenMaiRepository.findAll());

            model.addAttribute("momdalthongbaongayKT1", "Ngày kết thúc phải sau ngày hiện tại");
            model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
            model.addAttribute("batmodaldetailupdatekm", 0);

            model.addAttribute("hangds", hangSanPhamService.findAll0());
            model.addAttribute("camds", cameraService.findAll0());
            model.addAttribute("mands", manHinhService.findAll0());
            model.addAttribute("mauds", mauSacService.findAll0());
            model.addAttribute("ramds", ramService.findAll0());
            model.addAttribute("romds", romService.findAll0());
            model.addAttribute("pinds", pinService.findAll0());
            model.addAttribute("dungds", dungLuongPinService.findAll0());
            model.addAttribute("chipds", chipService.findAll0());
            model.addAttribute("sands", sanPhamService.findAll0());
            //        //gia max
            Long max = Long.valueOf('0');
            for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

                if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                    max = Long.valueOf(String.valueOf(ct.getGiaBan()));
                }
            }
//System.out.println("taco---"+max);
//System.out.println("taco---"+max);
            model.addAttribute("max", String.valueOf(max));
            return "home/layout";
        }


        khuyenMaiupdate.setNgayCapNhat(formattedDateTime);
        khuyenMaiRepository.save(khuyenMaiupdate);


        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("km", new KhuyenMai());
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");
        model.addAttribute("tbUPDATEkmOK", 0);

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        return "home/layout";
    }


    @PostMapping("/khuyen-mai/tim-kiem")
    public String timmt(
            Model model,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate,
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("km") KhuyenMai khuyenMai,
            @RequestParam("matk") String mt

    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);

        model.addAttribute("dulieu", khuyenMaiRepository.timkiemKM(mt));
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);


        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        model.addAttribute("tbThemKMOK", 0);
        model.addAttribute("NDTB", "Tìm kiếm thành công");
        return "home/layout";
    }


    @GetMapping("/khuyen-mai/loc-ban-hang/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}/{x11}/{x12}")
    public String locbanhang(
            Model model,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5,
            @PathVariable("x6") String x6,
            @PathVariable("x7") String x7,
            @PathVariable("x8") String x8,
            @PathVariable("x9") String x9,
            @PathVariable("x10") String x10,
            @PathVariable("x11") String x11,
            @PathVariable("x12") String x12
    ) {

        KhuyenMai kmchon = khuyenMaiRepository.findById(idkmdangchon).orElse(null);
        model.addAttribute("kmchon", kmchon);

        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);
        model.addAttribute("listctsp", banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12))));

        return "khuyen-mai/single_pase_giao-dien-loc-khuyen-mai";
    }


    @GetMapping("/khuyen-mai/huy-khuyen-mai/{idkm}")
    public String xoakhuyenmai(
            Model model,
            @PathVariable("idkm") UUID idkm,
            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
//            @ModelAttribute("khmodal") KhachHang khachHang1
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        khuyenMaiRepository.XoaKhuyenMai(idkm);

        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);

//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));


        model.addAttribute("tbhuyKM", 0);
        return "home/layout";
    }


    @GetMapping("khuyen-mai/tinh-trang/{tinhtrang}")
    public String loctinhtrang(
            Model model,

            @ModelAttribute("km") KhuyenMai khuyenMai,
            @ModelAttribute("kmupdate") KhuyenMai khuyenMaiupdate,
//            @RequestParam("num") Optional<Integer> num,
//            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
//            @ModelAttribute("khmodal") KhachHang khachHang1
            @PathVariable("tinhtrang") String tinhtrang
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("khuyenMaiRepository", khuyenMaiRepository);

//        Sort sort = Sort.by("ma").descending();
//        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
//        Page<KhuyenMai> list = khuyenMaiRepository.fillalllPageable(pageable);
        if (tinhtrang.equals("all")) {
            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
            model.addAttribute("dongbo", "all");
        } else if (tinhtrang.equals("2")) {
            model.addAttribute("dulieu", khuyenMaiRepository.TimTrangThaiKM(2));
            model.addAttribute("dongbo", "2");

        } else if (tinhtrang.equals("0")) {
            model.addAttribute("dulieu", khuyenMaiRepository.TimTrangThaiKM(0));
            model.addAttribute("dongbo", "0");

        } else if (tinhtrang.equals("1")) {
            model.addAttribute("dulieu", khuyenMaiRepository.TimTrangThaiKM(1));
            model.addAttribute("dongbo", "1");

        } else {
            model.addAttribute("dulieu", khuyenMaiRepository.findAll());
            model.addAttribute("dongbo", "all");
        }

//        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("contentPage", "../khuyen-mai/khuyen-mai.jsp");

        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        //        //gia max
        Long max = Long.valueOf('0');
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {

            if (Long.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Long.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));
        model.addAttribute("tbThemKMOK", 0);
        model.addAttribute("NDTB", "Lọc thành công");
        return "home/layout";
    }

    @GetMapping("/vi-du")
    public String xoakhuyenmai(
            Model model
    ) {
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("dulieu", khuyenMaiRepository.findAll());
        return "khuyen-mai/vidu";
    }

}
