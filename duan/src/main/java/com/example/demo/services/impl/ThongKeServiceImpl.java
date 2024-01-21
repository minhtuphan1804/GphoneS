package com.example.demo.services.impl;

import com.example.demo.DTO.DoanhThuHang;
import com.example.demo.DTO.DoanhThuKhachHang;
import com.example.demo.DTO.DoanhThuNhanVien;
import com.example.demo.DTO.DoanhThuSanPham;
import com.example.demo.DTO.DoanhThuTheoThang;
import com.example.demo.DTO.SoLuongDoiTraHang;
import com.example.demo.models.HoaDon;
import com.example.demo.repositories.ThongKeRepository;
import com.example.demo.services.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private ThongKeRepository thongKeRepository;

    @Override
    public int countHD() {
        return thongKeRepository.countHD();
    }

    @Override
    public int avgHD() {
        return   thongKeRepository.avgHD();
    }

    @Override
    public List<DoanhThuTheoThang> doanhThu() {
        return thongKeRepository.doanhThu();
    }

    @Override
    public List<DoanhThuTheoThang> selectedYear() {
        return thongKeRepository.selectedYear();
    }

    @Override
    public List<DoanhThuTheoThang> loctheonam(Integer Nam) {
        return thongKeRepository.loctheonam(Nam);
    }

    @Override
    public List<DoanhThuSanPham> doanhThuSanPham() {
        return thongKeRepository.doanhThuSanPham();
    }

    @Override
    public List<DoanhThuHang> doanhThuHang() {
        return thongKeRepository.doanhThuHang();
    }

    @Override
    public List<DoanhThuHang> locdoanhThuHang(Date startDate, Date endDate) {
        return thongKeRepository.locdoanhThuHang(startDate, endDate);
    }

    @Override
    public List<DoanhThuSanPham> selectedHang() {
        return thongKeRepository.selectedHang();
    }

    @Override
    public List<DoanhThuSanPham> locHang(String ten) {
        return thongKeRepository.locHang(ten);
    }

    @Override
    public List<DoanhThuNhanVien> doanhThuNhanVien() {
        return thongKeRepository.doanhThuNhanVien();
    }

    @Override
    public List<DoanhThuNhanVien> locDoanhThuNhanVien(Date startDate, Date endDate) {
        return thongKeRepository.locDoanhThuNhanVien(startDate, endDate);
    }

    @Override
    public List<DoanhThuKhachHang> doanhThuKhachHang() {
        return thongKeRepository.doanhThuKhachHang();
    }

    @Override
    public List<DoanhThuKhachHang> doanhThuKhachHangGioiTinh() {
        return thongKeRepository.doanhThuKhachHangGioiTinh();
    }

    @Override
    public List<DoanhThuKhachHang> locDoanhThuKhachHang(Date startDate, Date endDate) {
        return thongKeRepository.locDoanhThuKhachHang(startDate,endDate);
    }

    @Override
    public List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh(Date startDate, Date endDate) {
        return thongKeRepository.locDoanhThuKhachHangGioiTinh(startDate,endDate);
    }

    @Override
    public List<SoLuongDoiTraHang> locSoLuongDoiTraHang(Date startDate, Date endDate) {
        return thongKeRepository.locSoLuongDoiTraHang(startDate, endDate);
    }

    @Override
    public List<SoLuongDoiTraHang> locSoLuongSanPhamLoi(Date startDate, Date endDate) {
        return thongKeRepository.locSoLuongSanPhamLoi(startDate, endDate);
    }

    @Override
    public List<SoLuongDoiTraHang> SoLuongDoiTraHang() {
        return thongKeRepository.SoLuongDoiTraHang();
    }

//    @Override
//    public List<DoanhThuTheoThang> getDoanhThusInYear(int year) {
//        return thongKeRepository.getDoanhThusInYear(year);
//    }
}
