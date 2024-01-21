package com.example.demo.services.impl;

import com.example.demo.models.DiaChi;
import com.example.demo.repositories.DiaChiRepository;
import com.example.demo.services.DiaChiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;


    @Override
    public Page<DiaChi> getAll(Pageable pageable) {
        return diaChiRepository.findAll(pageable);
    }

    @Override
    public List<DiaChi> findAll() {
        return diaChiRepository.findAll();
    }

    @Override
    public List<DiaChi> findAll0() {
        return diaChiRepository.findAll0();
    }

    @Override
    public List<DiaChi> getALL0() {
        return diaChiRepository.getall0();
    }

    @Override
    public DiaChi findById(UUID id) {
        return diaChiRepository.findById(id).orElse(null);
    }

    @Override
    public DiaChi add(DiaChi hangKhachHang) {
        return diaChiRepository.save(hangKhachHang);
    }

    @Override
    public DiaChi update(UUID id, DiaChi hangKhachHang) {
        if (id != null) {
            DiaChi hangKhachHangUpdate = diaChiRepository.findById(id).orElse(null);
            if (hangKhachHangUpdate != null) {
                BeanUtils.copyProperties(hangKhachHang, hangKhachHangUpdate);
                diaChiRepository.save(hangKhachHangUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            DiaChi hangKhachHang = diaChiRepository.findById(id).orElse(null);
            if (hangKhachHang != null) {
                diaChiRepository.delete(hangKhachHang);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<DiaChi> getAll00(Pageable pageable) {
        return diaChiRepository.getall00(pageable);
    }

    @Override
    public DiaChi TimMa(String id) {
        System.out.println("---" + id);
        return diaChiRepository.timkiemma(id);
    }

    @Override
    public List<DiaChi> TimMT(String mt) {
        return diaChiRepository.timkiemMT(mt);
    }

    @Override
    public List<DiaChi> getall1() {
        return diaChiRepository.getall1();
    }

    @Override
    public Page<DiaChi> getall11(Pageable pageable) {
        return diaChiRepository.getall11(pageable);
    }

    @Override
    public void update0() {
        diaChiRepository.update0();
    }

    @Override
    public List<DiaChi> timkiemMT1(String timkiem) {
        return diaChiRepository.timkiemMT1(timkiem);
    }

    @Override
    public List<DiaChi> timkiem(UUID timkiem) {
        return diaChiRepository.timkiem(timkiem);
    }

    @Override
    public List<DiaChi> diaChiThanhToan(UUID id) {
        return diaChiRepository.diaChiThanhToan(id);
    }
}
