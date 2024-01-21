package com.example.demo.services;

import com.example.demo.models.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {


    public List<NhanVien> getAll();

    public List<NhanVien> getAll1();


    public List<NhanVien> findAll();

    public NhanVien findById(UUID id);

    public NhanVien add(NhanVien nhanVien);

    public NhanVien update(UUID id, NhanVien nhanVien);

    public Boolean delete(UUID id);

    boolean existsByPhoneNumber(String sdt);

    public void updateTT();

    public List<NhanVien> search0(String ten);

    public List<NhanVien> search1(String ten);

    List<NhanVien> searchByChucVuAndGioiTinh(String tenChucVu, String gioiTinh);

    List<NhanVien> nhanVienThanhToan(UUID id);

    NhanVien nhanVienUpdateHoaDon(UUID id);

}


