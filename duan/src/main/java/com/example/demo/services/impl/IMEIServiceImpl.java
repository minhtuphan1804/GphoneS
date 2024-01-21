package com.example.demo.services.impl;

import com.example.demo.models.IMEI;
import com.example.demo.repositories.IMEIRepository;
import com.example.demo.services.IMEIService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service

public class IMEIServiceImpl implements IMEIService {

    @Autowired
    IMEIRepository imeiRepository;

    @Override
    public Page<IMEI> getAll(Pageable pageable) {
        return imeiRepository.findAll(pageable);
    }

    @Override
    public List<IMEI> getIMEI(UUID id) {
        return imeiRepository.getIMEI(id);
    }

    @Override
    public List<IMEI> searchOn(String imei) {
        return imeiRepository.searchIMEIOn(imei);
    }
    @Override
    public   Page<IMEI> fill1(Pageable pageable){
        return imeiRepository.fill1(pageable);
    };

    @Override
    public List<IMEI> searchOnBanHang(String imei, UUID id) {
        return imeiRepository.searchIMEIBanHang(imei, id);
    }

    @Override
    public List<IMEI> searchOff(String imei) {
        return imeiRepository.searchIMEIOff(imei);
    }

    @Override
    public List<IMEI> searchOff2(String imei) {
        return imeiRepository.searchIMEIOff2(imei);
    }

    @Override
    public List<IMEI> findAll() {
        return imeiRepository.findAll();
    }

    @Override
    public List<IMEI> findAll0() {
        return imeiRepository.findAll0();
    }

    @Override
    public List<IMEI> showIMEI(UUID id) {
        return imeiRepository.showIMEI(id);
    }

    @Override
    public IMEI searchSoImei(String imei) {
        return imeiRepository.searchSoImei(imei);
    }


    @Override
    public String searchSoImei2(String imei) {
        return imeiRepository.searchSoImei2(imei);
    }

    @Override
    public IMEI findById(UUID id) {
        return imeiRepository.findById(id).orElse(null);
    }

    @Override
    public IMEI add(IMEI imei) {
        return imeiRepository.save(imei);
    }

    @Override
    public IMEI update(UUID id, IMEI imei) {
        if (id != null) {
            IMEI imeiUpdate = imeiRepository.findById(id).orElse(null);
            if (imeiUpdate != null) {
                BeanUtils.copyProperties(imei, imeiUpdate);
                imeiRepository.save(imeiUpdate);
            }
        }
        return null;
    }

    @Override
    public IMEI updateI(IMEI imei) {
        return imeiRepository.save(imei);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            IMEI imei = imeiRepository.findById(id).orElse(null);
            if (imei != null) {
                imei.setTinhTrang(2);
                imeiRepository.save(imei);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatImei(Date date, UUID id) {
        imeiRepository.updateImei(date, id);
    }

    @Override
    public void updatImeiChoXuLy(Date date, UUID id) {
        imeiRepository.updateImeiChoXuLy(date, id);
    }

    @Override
    public void updatImei1(Date date, UUID id) {
        imeiRepository.updateImei1(date, id);
    }

    @Override
    public List<IMEI> getImeiOn() {
        return imeiRepository.getImeiOn();
    }

    @Override
    public List<IMEI> getImeiOfff() {
        return imeiRepository.getImeiOfff();
    }

    @Override
    public List<IMEI> getImeiOff() {
        return imeiRepository.getImeiOff();
    }

    @Override
    public List<IMEI> getImeiOff3() {
        return imeiRepository.getImeiOff3();
    }

    @Override
    public List<IMEI> findAll3() {
        return imeiRepository.findAll3();
    }

    @Override
    public void khoiPhuc(UUID uuid) {
        IMEI imei = imeiRepository.findById(uuid).orElse(null);
        imei.setTinhTrang(0);


    }

    @Override
    public List<IMEI> statusSanPham(UUID id) {
        return imeiRepository.statusSanPham(id);
    }

    @Override
    public List<IMEI> statusCTSP(UUID id) {
        return imeiRepository.statusCTSP(id);
    }

    @Override
    public List<IMEI> findImeiLoi() {
        return imeiRepository.getImeiLoi();
    }

    @Override
    public List<IMEI> searchImeiLoi(String imei) {
        return imeiRepository.searchImeiLoi(imei);
    }

    @Override
    public boolean existImei(String imei) {
        return imeiRepository.existsBySoImei(imei);
    }

    @Override
    public List<IMEI> fct(UUID id) {
        return imeiRepository.fct(id);
    }
}
