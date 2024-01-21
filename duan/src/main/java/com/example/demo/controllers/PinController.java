package com.example.demo.controllers;

import com.example.demo.models.DungLuongPin;
import com.example.demo.models.Pin;
import com.example.demo.services.DungLuongPinService;
import com.example.demo.services.PinService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/pin")
@Controller
public class PinController {

    @Autowired
    private PinService pinService;

    @Autowired
    private DungLuongPinService dungLuongPinService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("pin") Pin pin,
                          @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<Pin> page = pinService.getAll();
        model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("contentPage", "../pin/pin.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("pin") Pin pin,
                          @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("pin", new Pin());
        model.addAttribute("contentPage", "../pin/add.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThi1(Model model, @ModelAttribute("pin") Pin pin) {
        List<Pin> page = pinService.getAll1();
        model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("contentPage", "../pin/pin-delete.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }


    @PostMapping("/add")
    public String addPin(Model model, @ModelAttribute("pin") @Valid Pin pin, BindingResult bindingResult,
                         @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("contentPage", "../pin/add.jsp");
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "PIN" + (pinService.findAll().size() + 1);
        pin.setMa(ma);
        pin.setNgayTao(date);
        pin.setTinhTrang(0);
        pinService.add(pin);
        return "redirect:/pin/hien-thi";
    }

    @PostMapping("/modal-add")
    public String addDungLuongPin(Model model, @ModelAttribute("dungLuongPin") @Valid DungLuongPin dungLuongPin, BindingResult bindingResult) {
        List<DungLuongPin> page = dungLuongPinService.getAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../pin/pin.jsp");
            model.addAttribute("listDungLuongPin", page);
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "DLP" + dungLuongPinService.findAll().size();
        dungLuongPin.setMa(ma);
        dungLuongPin.setNgayTao(date);
        dungLuongPin.setTinhTrang(0);

        dungLuongPinService.add(dungLuongPin);
        model.addAttribute("listDungLuongPin", page);
        return "redirect:/pin/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updatePin(Model model, @PathVariable("id") UUID id, @ModelAttribute("pin") @Valid Pin pin, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
            return "../pin/pin-update";
        }
        model.addAttribute("listDungLuongPin", dungLuongPinService.findAll0());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        pin.setNgayCapNhat(date);
        pin.setNgayTao(pin.getNgayTao());
        pinService.update(id, pin);
        return "redirect:/pin/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @ModelAttribute("pin") Pin pin) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        pin.setNgayCapNhat(date);

        pinService.updateTT();
        List<Pin> page = pinService.getAll1();
        model.addAttribute("contentPage", "../pin/pin-delete.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("pin") Pin pin) {
        Pin pin1 = pinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        pin1.setNgayCapNhat(date);
        pin1.setTinhTrang(1);
        pinService.update(id, pin1);
        List<Pin> page = pinService.getAll();
        model.addAttribute("contentPage", "../pin/pin.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @ModelAttribute("pin") Pin pin) {
        Pin pin1 = pinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        pin1.setNgayCapNhat(date);

        pin1.setTinhTrang(0);
        pinService.update(id, pin1);
        List<Pin> page = pinService.getAll1();
        model.addAttribute("contentPage", "../pin/pin-delete.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        Pin sp = pinService.findById(id);
        model.addAttribute("pin", sp);
        List<Pin> page = pinService.getAll();
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("contentPage", "../pin/pin-update.jsp");
        model.addAttribute("listPin", page);
        return "home/layout";
    }
    @PostMapping("/search-0")
    public String search0(Model model, @RequestParam("search") String sreach, @ModelAttribute("pin") Pin pin) {
        List<Pin> listPin = pinService.search0(sreach);
        model.addAttribute("listPin", listPin);
        model.addAttribute("contentPage", "../pin/pin.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @RequestParam("search") String sreach, @ModelAttribute("pin") Pin pin) {
        List<Pin> listPin = pinService.search1(sreach);
        model.addAttribute("listPin", listPin);
        model.addAttribute("contentPage", "../pin/pin-delete.jsp");
        return "home/layout";
    }

    @GetMapping("/export-excel")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ database
        List<Pin> listPin = pinService.findAll();

        // Tạo đối tượng XSSFWorkbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo bảng dữ liệu
        XSSFSheet sheet = workbook.createSheet("Danh sách pin");

        // Tạo tiêu đề bảng
        Row row = sheet.createRow(0);
        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("Mã");
        cell = row.createCell(1);
        cell.setCellValue("Loại Pin");
        cell = row.createCell(2);
        cell.setCellValue("Công nghệ Pin");
        cell = row.createCell(3);
        cell.setCellValue("Ngày Tạo");
        cell = row.createCell(4);
        cell.setCellValue("Ngày Cập Nhật");
        cell = row.createCell(5);
        cell.setCellValue("Tình Trạng");
        cell = row.createCell(6);
        cell.setCellValue("Mô Tả");
        cell = row.createCell(7);
        cell.setCellValue("Dung Lượng");

        // Tạo đường viền cho tiêu đề bảng
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);

        for (int i = 0; i < 8; i++) {
            cell = row.getCell(i);
            cell.setCellStyle(headerStyle);
        }

        // Thêm dữ liệu vào bảng
        int rowNum = 1;
        for (Pin pin : listPin) {
            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(pin.getMa());
            cell = row.createCell(1);
            cell.setCellValue(pin.getLoaiPin());
            cell = row.createCell(2);
            cell.setCellValue(pin.getCongNghePin());

            // Tạo ô kiểu date
            XSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));

            cell = row.createCell(3);
            cell.setCellValue(pin.getNgayTao());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(4);
            cell.setCellValue(pin.getNgayCapNhat());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(5);
            cell.setCellValue(pin.getTinhTrang() == 0 ? "Hoạt động" : "Ngừng hoạt động");

            cell = row.createCell(6);
            cell.setCellValue(pin.getMoTa());
            cell = row.createCell(7);
            cell.setCellValue(pin.getDungLuongPin().getThongSo());

            rowNum++;
        }

        // Lưu tệp excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Trả về tệp excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=danh-sach-pin.xlsx");
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return null;
    }


    @GetMapping("/export-pdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Pin> listPin = pinService.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Danh sách pin");

            // Tạo tiêu đề bảng
            Row row = sheet.createRow(0);
            Cell cell;
            cell = row.createCell(0);
            cell.setCellValue("Mã");
            cell = row.createCell(1);
            cell.setCellValue("Loại Pin");
            cell = row.createCell(2);
            cell.setCellValue("Công nghệ Pin");
            cell = row.createCell(3);
            cell.setCellValue("Ngày Tạo");
            cell = row.createCell(4);
            cell.setCellValue("Ngày Cập Nhật");
            cell = row.createCell(5);
            cell.setCellValue("Tình Trạng");
            cell = row.createCell(6);
            cell.setCellValue("Mô Tả");
            cell = row.createCell(7);
            cell.setCellValue("Dung Lượng");

            // Thêm dữ liệu vào bảng

            int rowNum = 1;
            for (Pin pin : listPin) {
                row = sheet.createRow(rowNum);
                cell = row.createCell(0);
                cell.setCellValue(pin.getMa());
                cell = row.createCell(1);
                cell.setCellValue(pin.getLoaiPin());
                cell = row.createCell(2);
                cell.setCellValue(pin.getCongNghePin());
                cell = row.createCell(3);
                cell.setCellValue(pin.getNgayTao());
                cell = row.createCell(4);
                cell.setCellValue(pin.getNgayCapNhat());
                cell = row.createCell(5);
                cell.setCellValue(pin.getTinhTrang());
                cell = row.createCell(6);
                cell.setCellValue(pin.getMoTa());
                cell = row.createCell(7);
                cell.setCellValue(pin.getDungLuongPin().getThongSo());

                rowNum++;
            }

            // Ghi dữ liệu PDF trực tiếp vào HttpServletResponse
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=danh-sach-pin.pdf");
            // Đặt mã charset là UTF-8 để tránh lỗi font chữ
            response.setCharacterEncoding("UTF-8");

            try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
                workbook.write(servletOutputStream);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }
}
