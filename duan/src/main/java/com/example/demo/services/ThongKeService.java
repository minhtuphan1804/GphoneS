package com.example.demo.services;

import com.example.demo.DTO.DoanhThuHang;
import com.example.demo.DTO.DoanhThuKhachHang;
import com.example.demo.DTO.DoanhThuNhanVien;
import com.example.demo.DTO.DoanhThuSanPham;
import com.example.demo.DTO.DoanhThuTheoThang;
import com.example.demo.DTO.SoLuongDoiTraHang;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ThongKeService {

    public int countHD();
    public int avgHD();
    public List<DoanhThuTheoThang> doanhThu();
    public List<DoanhThuTheoThang> selectedYear();
    public List<DoanhThuTheoThang> loctheonam(Integer Nam);

    public List<DoanhThuHang> doanhThuHang();
    public List<DoanhThuHang> locdoanhThuHang(Date startDate, Date endDate);

    public List<DoanhThuSanPham> doanhThuSanPham();
    public List<DoanhThuSanPham> selectedHang();
    public List<DoanhThuSanPham> locHang(String ten);
    public List<DoanhThuNhanVien> doanhThuNhanVien();

    public List<DoanhThuNhanVien> locDoanhThuNhanVien(Date startDate, Date endDate);

    public List<DoanhThuKhachHang> doanhThuKhachHang();
    public List<DoanhThuKhachHang> doanhThuKhachHangGioiTinh();

    public List<DoanhThuKhachHang> locDoanhThuKhachHang(Date startDate,Date endDate);
    public List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh( Date startDate, Date endDate);

    public List<SoLuongDoiTraHang> locSoLuongDoiTraHang(Date startDate, Date endDate);
    public  List<SoLuongDoiTraHang> locSoLuongSanPhamLoi(Date startDate, Date endDate);
    public List<SoLuongDoiTraHang> SoLuongDoiTraHang();

}
