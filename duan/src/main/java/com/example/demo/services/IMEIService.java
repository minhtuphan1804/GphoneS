package com.example.demo.services;

import com.example.demo.models.IMEI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface IMEIService {
    public Page<IMEI> getAll(Pageable pageable);

    public List<IMEI> getIMEI(UUID id);

    public List<IMEI> searchOn(String imei);

    public List<IMEI> searchOnBanHang(String imei, UUID id);

    public List<IMEI> searchOff(String imei);

    public List<IMEI> searchOff2(String imei);

    public List<IMEI> findAll();

    public Page<IMEI> fill1(Pageable pageable);

    public List<IMEI> findAll0();

    List<IMEI> showIMEI(UUID id);

    public IMEI searchSoImei(String imei);

    public String searchSoImei2(String imei);

    public IMEI findById(UUID id);

    public IMEI add(IMEI imei);

    public IMEI update(UUID id, IMEI imei);

    public IMEI updateI(IMEI imei);

    public Boolean delete(UUID id);

    public void updatImei(Date date, UUID id);

    public void updatImeiChoXuLy(Date date, UUID id);

    public void updatImei1(Date date, UUID id);

    public List<IMEI> getImeiOn();

    public List<IMEI> getImeiOfff();

    public List<IMEI> getImeiOff();

    public List<IMEI> getImeiOff3();

    public List<IMEI> findAll3();

    public void khoiPhuc(UUID uuid);

    public List<IMEI> statusSanPham(UUID id);

    public List<IMEI> statusCTSP(UUID id);

    public List<IMEI> findImeiLoi();

    public List<IMEI> searchImeiLoi(String imei);

    boolean existImei(String imei);

    List<IMEI> fct(UUID id);
}


