package com.example.demo.services;

import com.example.demo.models.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DiaChiService {
    public Page<DiaChi> getAll(Pageable pageable);

    public List<DiaChi> findAll();

    public List<DiaChi> findAll0();

    public List<DiaChi> getALL0();

    public DiaChi findById(UUID id);

    public DiaChi add(DiaChi hangKhachHang);

    public DiaChi update(UUID id, DiaChi hangKhachHang);

    public Boolean delete(UUID id);

    public Page<DiaChi> getAll00(Pageable pageable);

    public DiaChi TimMa(String id);

    public List<DiaChi> TimMT(String mt);

    List<DiaChi> getall1();

    Page<DiaChi> getall11(Pageable pageable);

    void update0();

    List<DiaChi> timkiemMT1(String timkiem);

    List<DiaChi> timkiem(UUID timkiem);

    List<DiaChi> diaChiThanhToan(UUID id);
}


