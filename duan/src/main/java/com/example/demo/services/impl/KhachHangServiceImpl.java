package com.example.demo.services.impl;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.repositories.DiaChiRepository;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.KhachHangService;
import com.example.demo.viewmodels.KhachHangHoaDon;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    DiaChiRepository diaChiRepository;
    @Override
    public Page<KhachHang> getAll(Pageable pageable) {
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public List<KhachHang> findAll0() {
        return khachHangRepository.getall00();
    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public KhachHang add(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang update(UUID id, KhachHang khachHang) {
        if (id != null) {
            KhachHang khachHangUpdate = khachHangRepository.findById(id).orElse(null);
            if (khachHangUpdate != null) {
                BeanUtils.copyProperties(khachHang, khachHangUpdate);
                khachHangRepository.save(khachHangUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            KhachHang khachHang = khachHangRepository.findById(id).orElse(null);
            if (khachHang != null) {
                khachHangRepository.delete(khachHang);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<KhachHang> getALL0(Pageable pageable) {
        return khachHangRepository.getall0(pageable);
    }

    @Override
    public Page<KhachHang> getALL1(Pageable pageable) {
        return khachHangRepository.getall1(pageable);
    }

    @Override
    public List<KhachHang> findAll00() {
        return khachHangRepository.getall00();
    }

    @Override
    public List<KhachHang> findAll11() {
        return khachHangRepository.getall11();
    }

    @Override
    public List<KhachHangLSMuaHang> fillLSMuaHang(UUID uuid) {
        return khachHangRepository.getLSMuaHang(uuid);
    }

    @Override
    public List<KhachHangHoaDon> getHD() {
        return khachHangRepository.getHD();
    }

    @Override
    public List<KhachHang> timkiem(String timkiem) {
        return khachHangRepository.timkiem(timkiem);
    }

    @Override
    public List<KhachHang> timkiem1(String timkiem) {
        return khachHangRepository.timkiem1(timkiem);
    }

    @Override
    public Page<KhachHangLSMuaHang> getLSMuaHangPage(Pageable pageable, UUID idkh) {
        return khachHangRepository.getLSMuaHangPage(pageable, idkh);
    }

    @Override
    public List<KhachHang> khachHangThanhToan(UUID id) {
        return khachHangRepository.khachHangThanhToan(id);
    }

    @Override
    public KhachHang newKhachHang(UUID id) {
        return khachHangRepository.newKhachHang(id);
    }

    @Override
    public KhachHang quenMatKhau(String username, String email) {
        return khachHangRepository.quenMatKhau(username, email);
    }

    @Override
    public List<DiaChi> getAllDiaChiByKhachHangId(UUID khachHangId) {
       return diaChiRepository.findByKhachHangId(khachHangId);
    }


}
