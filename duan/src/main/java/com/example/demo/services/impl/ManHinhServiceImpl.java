package com.example.demo.services.impl;

import com.example.demo.models.ManHinh;
import com.example.demo.models.ManHinh;
import com.example.demo.repositories.ManHinhRepository;
import com.example.demo.services.ManHinhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ManHinhServiceImpl implements ManHinhService {

    @Autowired
    private ManHinhRepository manHinhRepository;

    @Override
    public List<ManHinh> getAll() {
        return manHinhRepository.getAll();
    }

    @Override
    public List<ManHinh> getAll1() {
        return manHinhRepository.getAll1();
    }

    @Override
    public List<ManHinh> findAll() {
        return manHinhRepository.findAll();
    }

    @Override
    public List<ManHinh> findAll0() {
        return manHinhRepository.findAll0();
    }

    @Override
    public ManHinh findById(UUID id) {
        return manHinhRepository.findById(id).orElse(null);
    }

    @Override
    public ManHinh add(ManHinh chip) {
        return manHinhRepository.save(chip);
    }

    @Override
    public ManHinh update(UUID id, ManHinh chip) {
        if (id != null) {
            ManHinh chipUpdate = manHinhRepository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                manHinhRepository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        manHinhRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            ManHinh chip = manHinhRepository.findById(id).orElse(null);
            if (chip != null) {
                manHinhRepository.delete(chip);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ManHinh> search0(String ten) {
        return manHinhRepository.sreach0(ten);
    }

    @Override
    public List<ManHinh> search1(String ten) {
        return manHinhRepository.sreach1(ten);
    }

    @Override
    public boolean existManHinh(String thongSo, String loaiCamUng, String tiLe, String doPhanGiai, String tanSoQuet, String congNghe) {
        return manHinhRepository.existsByThongSoAndLoaiCamUngAndTiLeKhungHinhAndDoPhanGiaiAndTanSoQuetAndCongNghe(thongSo, loaiCamUng, tiLe, doPhanGiai, tanSoQuet, congNghe);
    }

}
