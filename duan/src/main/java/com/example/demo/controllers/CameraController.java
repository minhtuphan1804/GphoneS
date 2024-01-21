package com.example.demo.controllers;

import com.example.demo.models.Camera;
import com.example.demo.services.CameraService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    private CameraService cameraService;

    public Integer kt(Integer so) {
        if (so == Integer.valueOf(0)) {
            return Integer.valueOf(1);
        }
        return so;
    }

    @GetMapping("hien-thi")
    public String hienthi(@ModelAttribute("camera") Camera camera, Model model) {
        List<Camera> list = cameraService.getAll();
        model.addAttribute("listCamera", list);
        model.addAttribute("contentPage", "../camera/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("camera") Camera camera) {
        List<Camera> page = cameraService.getAll1();
        model.addAttribute("listCamera", page);
        model.addAttribute("contentPage", "../camera/camera-delete.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("camera") Camera camera) {
        model.addAttribute("camera", new Camera());
        model.addAttribute("contentPage", "../camera/add.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        Camera hsp = cameraService.findById(id);
        model.addAttribute("camera", hsp);
        model.addAttribute("contentPage", "../camera/update.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("camera") Camera camera, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../camera/add.jsp");
            return "home/layout";
        }
        camera.setNgayTao(Date.valueOf(LocalDate.now()));
        camera.setTinhTrang(0);
        camera.setMa("CAM" + (String.valueOf(cameraService.findAll().size() + 1)));
        cameraService.add(camera);
        return "redirect:/camera/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("camera") @Valid Camera camera,
                         BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../camera/update.jsp");
            return "home/layout";
        }
        Camera hsp = cameraService.findById(id);
        Date ngayTao = hsp.getNgayTao();
        camera.setNgayTao(ngayTao);
        camera.setMa(hsp.getMa());
        // Gán ngày hiện tại
        camera.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        camera.setTinhTrang(hsp.getTinhTrang());
        cameraService.update(id, camera);
        return "redirect:/camera/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @ModelAttribute("camera") Camera camera) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        camera.setNgayCapNhat(date);
        cameraService.updateTT();
        List<Camera> page = cameraService.getAll1();
        model.addAttribute("contentPage", "../camera/camera-delete.jsp");
        model.addAttribute("listCamera", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("camera") Camera camera) {

        Camera camera1 = cameraService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        camera1.setNgayCapNhat(date);
        camera1.setTinhTrang(1);
        cameraService.update(id, camera1);
        List<Camera> page = cameraService.getAll();
        model.addAttribute("contentPage", "../camera/hien-thi.jsp");
        model.addAttribute("listCamera", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("camera") Camera camera) {
        Camera camera1 = cameraService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        camera1.setNgayCapNhat(date);

        camera1.setTinhTrang(0);
        cameraService.update(id, camera1);
        List<Camera> page = cameraService.getAll1();
        model.addAttribute("contentPage", "../camera/camera-delete.jsp");
        model.addAttribute("listCamera", page);
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("camera") Camera camera, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<Camera> list = cameraService.getAll();
            model.addAttribute("listCamera", list);
            model.addAttribute("contentPage", "../camera/hien-thi.jsp");
            return "home/layout";
        } else {
            List<Camera> list = cameraService.search0(search);
            model.addAttribute("listCamera", list);
            model.addAttribute("contentPage", "../camera/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("camera") Camera camera, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            List<Camera> list = cameraService.getAll1();
            model.addAttribute("listCamera", list);
            model.addAttribute("contentPage", "../camera/camera-delete.jsp");
            return "home/layout";
        } else {
            List<Camera> list = cameraService.search1(search);
            model.addAttribute("listCamera", list);
            model.addAttribute("contentPage", "../camera/camera-delete.jsp");
            return "home/layout";
        }

    }
}
