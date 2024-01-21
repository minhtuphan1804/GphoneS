package com.example.demo.services;

import com.example.demo.models.Camera;
import com.example.demo.models.ManHinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ManHinhService {
    public List<ManHinh> getAll();

    public List<ManHinh> getAll1();

    public List<ManHinh> findAll();

    public List<ManHinh> findAll0();

    public ManHinh findById(UUID id);

    public ManHinh add(ManHinh manHinh);

    public ManHinh update(UUID id, ManHinh manHinh);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<ManHinh> search0(String ten);

    public List<ManHinh> search1(String ten);
    boolean existManHinh(String thongSo,String loaiCamUng,String tiLe,String doPhanGiai,String tanSoQuet,String congNghe);
}
