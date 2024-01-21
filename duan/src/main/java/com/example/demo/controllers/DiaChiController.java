package com.example.demo.controllers;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.KhachHangService;
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
import java.util.Optional;
import java.util.UUID;

@Controller

public class DiaChiController {
    @Autowired
    DiaChiService diaChiService;
    @Autowired
    KhachHangService khachHangService;


    public Integer kt(Integer so){
        if(so==Integer.valueOf(0)){
            return Integer.valueOf(1);
        }
        return so;
    }

    @GetMapping("/dia-chi/hien-thi")
    public String hienThi(
            Model model,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
            @ModelAttribute("dc") DiaChi diaChi,
//            @ModelAttribute("kh") KhachHang khachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1
    ) {
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
        return "home/layout";
    }

    @PostMapping("/dia-chi/add-khach-hang")
    public String themNhanhKH(
            Model model,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
            @ModelAttribute("dc") DiaChi diaChi,
//            @ModelAttribute("kh") KhachHang khachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1


    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String mhd="";
        Integer sl = khachHangService.findAll().size() + 1;
        if(sl<9){
            mhd = "MKH0" + sl;
        }else {
            mhd = "MKH" + sl;
        }
        khachHang1.setMa(mhd);
        khachHang1.setNgayTao(date);
        khachHang1.setNgayCapNhat(date);
        khachHang1.setTinhTrang(0);
        khachHang1.setEmail("..........@gmail.com");
        khachHang1.setTaiKhoan(".....");
        khachHang1.setMatKhau("......");
        khachHang1.setDiem(1);



        khachHangService.add(khachHang1);

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("khmodal",new KhachHang());

        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
        return "home/layout";
    }

    @PostMapping("/dia-chi/add-khach-hang/{id}")
    public String themNhanhKHud(
            Model model,
            @PathVariable("id") UUID id,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
            @ModelAttribute("dc") DiaChi diaChi,
//            @ModelAttribute("kh") KhachHang khachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1


    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String mhd="";
        Integer sl = khachHangService.findAll().size() + 1;
        if(sl<9){
            mhd = "MKH0" + sl;
        }else {
            mhd = "MKH" + sl;
        }
        khachHang1.setMa(mhd);
        khachHang1.setNgayTao(date);
        khachHang1.setNgayCapNhat(date);
        khachHang1.setTinhTrang(0);
        khachHang1.setEmail("..........@gmail.com");
        khachHang1.setTaiKhoan(".....");
        khachHang1.setMatKhau("......");
        khachHang1.setDiem(1);



        khachHangService.add(khachHang1);



        model.addAttribute("dc", diaChiService.findById(id));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("khmodal",new KhachHang());
        model.addAttribute("contentPage","../dia-chi/dia-chi-update.jsp");
        return "home/layout";
    }


    @GetMapping("/dia-chi/dia-chi-tung-xoa")
    public String hienThihkhtungxoa(
            Model model,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
//            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getall11(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getall1().size());
        model.addAttribute("service", diaChiService);


        model.addAttribute("contentPage","../dia-chi/dia-chi-tungxoa.jsp");
        return "home/layout";
    }
    //
//
//
    @GetMapping("/dia-chi/dia-chi-tung-xoa/khoi-phuc/{id2}")
    public String khoiphuc1(
            Model model,
            @PathVariable("id2") UUID id2,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi hkh1=diaChiService.findById(id2);
        hkh1.setTinhTrang(0);
        hkh1.setNgayCapNhat(date);
        diaChiService.add(hkh1);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getall11(pageable);
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getall1().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("contentPage","../dia-chi/dia-chi-tungxoa.jsp");
        return "redirect:/dia-chi/dia-chi-tung-xoa";
//        return "home/layout";
    }



    @GetMapping("/dia-chi/dia-chi-tungxoa/khoi-phuc-het")
    public String khoiphuchet(
            Model model,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {


        diaChiService.update0();
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getall11(pageable);
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getall1().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("contentPage","../dia-chi/dia-chi-tungxoa.jsp");
        return "home/layout";
    }

    @PostMapping("/dia-chi/tim-kiem")
    public String timmt(
            Model model,
            @RequestParam("matk") String mt,
            @ModelAttribute("dc") DiaChi diaChi,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1

    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu",diaChiService.TimMT(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());

        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
        return "home/layout";
    }


    @PostMapping("/dia-chi/tim-kiem/{id}")
    public String timmtid(

            Model model,
            @PathVariable("id") UUID id,
            @RequestParam("matk") String mt,
            @ModelAttribute("dc") DiaChi diaChi,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1

    ) {

        model.addAttribute("dc", diaChiService.findById(id));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", diaChiService.TimMT(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());

        model.addAttribute("contentPage","../dia-chi/dia-chi-update.jsp");
        return "home/layout";
    }

    @PostMapping("/dia-chi/tim-kiem-tung-xoa")
    public String timmttungxoa(
            Model model,
            @RequestParam("matk") String mt,
//            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @ModelAttribute("khmodal") KhachHang khachHang1


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getall11(pageable);

        model.addAttribute("dulieu",diaChiService.timkiemMT1(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getall1().size());
        model.addAttribute("service", diaChiService);


        model.addAttribute("contentPage","../dia-chi/dia-chi-tungxoa.jsp");
        return "home/layout";
    }


    @GetMapping("/dia-chi/remove/{id}")
    public String remove(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("dc") DiaChi diaChi,
                         @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                         @ModelAttribute("khmodal") KhachHang khachHang1

    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi hangKhachHang1 = diaChiService.findById(id);
        hangKhachHang1.setTinhTrang(1);
        hangKhachHang1.setNgayCapNhat(date);
        diaChiService.add(hangKhachHang1);


        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());

        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
        return "home/layout";
    }


    @GetMapping("/dia-chi/view-update/{id}")
    public String viewUpdate(Model model,
                             @PathVariable("id") UUID id,
                             @ModelAttribute("dc") DiaChi hangKhachHang,
                             @RequestParam("num") Optional<Integer> num,
                             @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                             @ModelAttribute("khmodal") KhachHang khachHang1

    ) {

        model.addAttribute("dc", diaChiService.findById(id));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);
        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("contentPage","../dia-chi/dia-chi-update.jsp");
        return "home/layout";
    }
    //
//
    @PostMapping("/dia-chi/update")
    public String updateDongSP(Model model,
//                               @ModelAttribute("hkh")  HangKhachHang hangKhachHang,
//
                               @ModelAttribute("dc") @Valid DiaChi hangKhachHang,
                               BindingResult bindingResult,
                               @RequestParam("num") Optional<Integer> num,
                               @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                               @ModelAttribute("khmodal") KhachHang khachHang1
    ) {
        if (bindingResult.hasErrors()) {

            Sort sort = Sort.by("ma").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<DiaChi> list = diaChiService.getAll00(pageable);
            model.addAttribute("dulieu", list.getContent());
            model.addAttribute("total", kt(list.getTotalPages()));
            model.addAttribute("tong", diaChiService.getALL0().size());
            model.addAttribute("service", diaChiService);
            model.addAttribute("kh", khachHangService.findAll00());
            model.addAttribute("contentPage","../dia-chi/dia-chi-update.jsp");
            return "home/layout";
        }


        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHang.setNgayTao(diaChiService.findById(hangKhachHang.getId()).getNgayTao());
        diaChiService.add(hangKhachHang);
//        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
//        model.addAttribute("dulieu", diaChiService.getALL0());
//        model.addAttribute("tong", diaChiService.getALL0().size());
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("dc",new DiaChi());
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
        return "home/layout";
    }


    @PostMapping("/dia-chi/add")
    public String updateadd(Model model,
//                            @ModelAttribute("hkh")  HangKhachHang hangKhachHang,
//
                            @ModelAttribute("dc") @Valid DiaChi hangKhachHang,
                            BindingResult bindingResult,
                            @RequestParam("num") Optional<Integer> num,
                            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                            @ModelAttribute("khmodal") KhachHang khachHang1
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            Sort sort = Sort.by("ma").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<DiaChi> list = diaChiService.getAll00(pageable);

            model.addAttribute("dulieu", list.getContent());
            model.addAttribute("total", kt(list.getTotalPages()));
            model.addAttribute("tong", diaChiService.getALL0().size());
            model.addAttribute("service", diaChiService);

            model.addAttribute("kh", khachHangService.findAll00());
            model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");
            return "home/layout";
        }
        Integer sl = diaChiService.findAll().size() + 1;
        String mhd="";
        if(sl<9){
            mhd = "MDC0" + sl;
        }else {
            mhd = "MDC" + sl;
        }
        hangKhachHang.setMa(mhd);
        hangKhachHang.setNgayTao(date);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHang.setTinhTrang(0);
        diaChiService.add(hangKhachHang);
//        model.addAttribute("dulieu", hangKhachHangService.getALL0());
//        model.addAttribute("tong", hangKhachHangService.getALL0().size());

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", diaChiService.getALL0().size());
        model.addAttribute("service", diaChiService);
        model.addAttribute("dc",new DiaChi());
        model.addAttribute("kh", khachHangService.findAll00());
        model.addAttribute("contentPage","../dia-chi/dia-chi.jsp");

        return "home/layout";
    }

}
