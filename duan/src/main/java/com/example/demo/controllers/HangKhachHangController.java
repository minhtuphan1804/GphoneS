package com.example.demo.controllers;

import com.example.demo.models.HangKhachHang;
import com.example.demo.services.HangKhachHangService;
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
//@RequestMapping("/hang-khach-hang")
public class HangKhachHangController {
    @Autowired
    HangKhachHangService hangKhachHangService;


    public Integer kt(Integer so){
        if(so==Integer.valueOf(0)){
            return Integer.valueOf(1);
        }
        return so;
    }

    @GetMapping("/hang-khach-hang/hien-thi")
    public String hienThi(
            Model model,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
        return "home/layout";
    }


    @GetMapping("/hang-khach-hang/hang-khach-hang-tung-xoa")
    public String hienThihkhtungxoa(
            Model model,
//            @ModelAttribute("hkh") HangKhachHang hangKhachHang,
            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getall11(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getall1().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-tungxoa.jsp");
        return "home/layout";
    }



    @GetMapping("/hang-khach-hang/hang-khach-hang-tungxoa/khoi-phuc/{id}")
    public String khoiphuc(
            Model model,
            @PathVariable("id") UUID id,
//            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        HangKhachHang hkh1=hangKhachHangService.findById(id);
        hkh1.setTinhTrang(0);
        hkh1.setNgayCapNhat(date);
        hangKhachHangService.add(hkh1);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getall11(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getall1().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-tungxoa.jsp");
//        return "home/layout";
        return "redirect:/hang-khach-hang/hang-khach-hang-tung-xoa";
    }



    @GetMapping("/hang-khach-hang/hang-khach-hang-tungxoa/khoi-phuc-het")
    public String khoiphuchet(
            Model model,
//            @PathVariable("id") UUID id,
//            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

//        HangKhachHang hkh1=hangKhachHangService.findById(id);
//        hkh1.setTinhTrang(0);
//        hangKhachHangService.add(hkh1);
        hangKhachHangService.update0();
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getall11(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getall1().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-tungxoa.jsp");
        return "home/layout";
    }
    @PostMapping("/hang-khach-hang/tim-kiem")
    public String timmt(
            Model model,
            @RequestParam("matk") String mt,
            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu",hangKhachHangService.TimMT(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
        return "home/layout";
    }



    @PostMapping("/hang-khach-hang/tim-kiem/{id}")
    public String timmtupdate(
            Model model,
            @PathVariable("id") UUID id,
            @RequestParam("matk") String mt,
            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        model.addAttribute("HKHHangKhachHang", hangKhachHangService.findById(id));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", hangKhachHangService.TimMT(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);
        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-update.jsp");
        return "home/layout";
    }







    @PostMapping("/hang-khach-hang/tim-kiem-tung-xoa")
    public String timmttungxoa(
            Model model,
            @RequestParam("matk") String mt,
//            @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size


    ) {

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getall11(pageable);

        model.addAttribute("dulieu",hangKhachHangService.timkiemMT1(mt));
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getall1().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-tungxoa.jsp");
        return "home/layout";
    }
    @GetMapping("/hang-khach-hang/remove/{id}")

    public String remove(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
                         @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        HangKhachHang hangKhachHang1 = hangKhachHangService.findById(id);
        hangKhachHang1.setTinhTrang(1);
        hangKhachHang1.setNgayCapNhat(date);
        hangKhachHangService.add(hangKhachHang1);
//        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
//        model.addAttribute("dulieu", hangKhachHangService.getALL0());
//        model.addAttribute("tong", hangKhachHangService.getALL0().size());

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);


        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
        return "home/layout";
    }



    @GetMapping("/hang-khach-hang/remove/{id}/{idsp}")
    public String removeupdate(Model model,
                               @PathVariable("id") UUID id,
                               @PathVariable("idsp") UUID idsp,
                               @ModelAttribute("HKHHangKhachHang") HangKhachHang HKHHangKhachHang,
                               @RequestParam("num") Optional<Integer> num,
                               @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {
        HangKhachHang hangKhachHang1 = hangKhachHangService.findById(id);
        hangKhachHang1.setTinhTrang(1);
        hangKhachHangService.add(hangKhachHang1);
//        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
//        model.addAttribute("dulieu", hangKhachHangService.getALL0());
//        model.addAttribute("tong", hangKhachHangService.getALL0().size());


        model.addAttribute("HKHHangKhachHang", hangKhachHangService.findById(idsp));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);
        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-update.jsp");
        return "home/layout";
    }


    @GetMapping("/hang-khach-hang/view-update/{id}")
    public String viewUpdate(Model model,
                             @PathVariable("id") UUID id,
                             @ModelAttribute("HKHHangKhachHang") HangKhachHang hangKhachHang,
                             @RequestParam("num") Optional<Integer> num,
                             @RequestParam(name = "size", defaultValue = "5", required = false) Integer size

    ) {

        model.addAttribute("HKHHangKhachHang", hangKhachHangService.findById(id));
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);
        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-update.jsp");
        return "home/layout";
    }


    @PostMapping("/hang-khach-hang/update")
    public String updateDongSP(Model model,
//                               @ModelAttribute("hkh")  HangKhachHang hangKhachHang,
//
                               @ModelAttribute("HKHHangKhachHang") @Valid HangKhachHang hangKhachHang,
                               BindingResult bindingResult,
                               @RequestParam("num") Optional<Integer> num,
                               @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        if (bindingResult.hasErrors()) {

            Sort sort = Sort.by("ma").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

            model.addAttribute("dulieu", list.getContent());
            model.addAttribute("total", kt(list.getTotalPages()));
            model.addAttribute("tong", hangKhachHangService.getALL0().size());
            model.addAttribute("service", hangKhachHangService);
            model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang-update.jsp");
            return "home/layout";
        }


        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHang.setNgayTao(hangKhachHangService.findById(hangKhachHang.getId()).getNgayTao());
        hangKhachHangService.add(hangKhachHang);
//        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
//        model.addAttribute("dulieu", hangKhachHangService.getALL0());
//        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);
        model.addAttribute("HKHHangKhachHang",new HangKhachHang());
        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
        return "home/layout";
    }


    @PostMapping("/hang-khach-hang/add")
    public String updateadd(Model model,
//                            @ModelAttribute("hkh")  HangKhachHang hangKhachHang,
//
                            @ModelAttribute("HKHHangKhachHang") @Valid HangKhachHang hangKhachHang,
                            BindingResult bindingResult,
                            @RequestParam("num") Optional<Integer> num,
                            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        if (bindingResult.hasErrors()) {
            Sort sort = Sort.by("ma").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

            model.addAttribute("dulieu", list.getContent());
            model.addAttribute("total", kt(list.getTotalPages()));
            model.addAttribute("tong", hangKhachHangService.getALL0().size());
            model.addAttribute("service", hangKhachHangService);

            model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");
            return "home/layout";
        }
        Integer sl = hangKhachHangService.findAll().size() + 1;
        String mhd="";
        if(sl<9){
            mhd = "MHKH0" + sl;
        }else {
            mhd = "MHKH" + sl;
        }
        hangKhachHang.setMa(mhd);
        hangKhachHang.setNgayTao(date);
        hangKhachHang.setNgayCapNhat(date);
        hangKhachHang.setTinhTrang(0);
        hangKhachHangService.add(hangKhachHang);
//        model.addAttribute("dulieu", hangKhachHangService.getALL0());
//        model.addAttribute("tong", hangKhachHangService.getALL0().size());

        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangKhachHang> list = hangKhachHangService.getAll00(pageable);

        model.addAttribute("dulieu", list.getContent());
        model.addAttribute("total", kt(list.getTotalPages()));
        model.addAttribute("tong", hangKhachHangService.getALL0().size());
        model.addAttribute("service", hangKhachHangService);
        model.addAttribute("HKHHangKhachHang",new HangKhachHang());
        model.addAttribute("contentPage","../hang-khach-hang/hang-khach-hang.jsp");

        return "home/layout";
    }

}
