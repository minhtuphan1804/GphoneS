package com.example.demo.services;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.viewmodels.KhachHangHoaDon;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    public Page<KhachHang> getAll(Pageable pageable);

    public List<KhachHang> findAll();

    public List<KhachHang> findAll0();

    public KhachHang findById(UUID id);

    public KhachHang add(KhachHang khachHang);

    public KhachHang update(UUID id, KhachHang khachHang);

    public Boolean delete(UUID id);

    public Page<KhachHang> getALL0(Pageable pageable);

    public Page<KhachHang> getALL1(Pageable pageable);

    public List<KhachHang> findAll00();

    public List<KhachHang> findAll11();

    public List<KhachHangLSMuaHang> fillLSMuaHang(UUID uuid);

    public List<KhachHangHoaDon> getHD();

    public List<KhachHang> timkiem(String timkiem);

    public List<KhachHang> timkiem1(String timkiem);

    public Page<KhachHangLSMuaHang> getLSMuaHangPage(Pageable pageable, UUID idkh);

    public List<KhachHang> khachHangThanhToan(UUID id);

    public KhachHang newKhachHang(UUID id);

    public KhachHang quenMatKhau(String username, String email);

    public List<DiaChi> getAllDiaChiByKhachHangId(UUID khachHangId);

}


