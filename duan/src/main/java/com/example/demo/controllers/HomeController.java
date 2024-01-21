//package com.example.demo.controllers;
//
//import com.example.demo.models.HoaDon;
//import com.example.demo.services.ChiTietSanPhamService;
//import com.example.demo.services.DataIntermediateService;
//import com.example.demo.services.IMEIService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//public class HomeController {
//
//    @Autowired
//    DataIntermediateService dataIntermediateService;
//    @Autowired
//    ChiTietSanPhamService chiTietSanPhamService;
//    @Autowired
//    IMEIService imeiService;
//
//
//    @RequestMapping("/home")
//    private String showHome(Model model){
//        model.addAttribute("nhanVien", dataIntermediateService.getSharedDataNhanVien());
//        Integer tongSP = chiTietSanPhamService.getCountTongSP();
//        Integer tongSPDaBan = chiTietSanPhamService.tongSPDaBan();
//        Integer tongDH = chiTietSanPhamService.cacDonHang();
//        Integer tongKHang = chiTietSanPhamService.soKhachHang();
//        Integer tongNVien = chiTietSanPhamService.soNV();
//
//        Integer tongDonHangCho = chiTietSanPhamService.cacDonHangChoXuLy();
//        model.addAttribute("tongSPDaBan", tongSPDaBan);
//        model.addAttribute("tongSoLuong", tongSP);
//        model.addAttribute("tongDonHang", tongDH);
//        model.addAttribute("tongKH",tongKHang);
//        model.addAttribute("tongNV",tongNVien);
//        model.addAttribute("tongDHCho",tongDonHangCho);
//
//
//
//
//        return "home/home";
//    }
//
//
//    @GetMapping("/homes")
//    private String showHomes(Model model){
//        return  "ban-hang-online/so-sanh-san-pham";
////                "chip/basic-table";
//    }
//}
package com.example.demo.controllers;

import com.example.demo.services.DataIntermediateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @Autowired
    DataIntermediateService dataIntermediateService;

    @RequestMapping("/home")
    private String showHome(Model model){
        model.addAttribute("nhanVien", dataIntermediateService.getSharedDataNhanVien());
        return  "home/layout";
//                "chip/basic-table";
    }

    @GetMapping("/homes")
    private String showHomes(Model model){
        return  "ban-hang/uc-toastr";
//                "chip/basic-table";
    }
}