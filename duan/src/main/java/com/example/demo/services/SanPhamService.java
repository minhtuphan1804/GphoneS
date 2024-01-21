package com.example.demo.services;

import com.example.demo.models.Anh;
import com.example.demo.models.Camera;
import com.example.demo.models.HangSanPham;
import com.example.demo.models.ManHinh;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {
    public Page<SanPham> getAll(Pageable pageable);

    public List<SanPham> findAll();

    public List<SanPham> findAll0();

    public List<SanPham> findAll1();

    public SanPham findById(UUID id);

    public SanPham add(SanPham sanPham);

    public SanPham update(UUID id, SanPham sanPham);

    public SanPham update1(SanPham sanPham);

    public Boolean delete(UUID id);

    public List<SanPham> search(String dungluong);

    public List<SanPham> search2(String dungluong);

    public List<SanPham> getAll0();

    public List<SanPham> getall1();

    public void update0();

    public List<SanPham> loc(UUID idHang, UUID idMan, UUID idCamera);

    public boolean trungDuLieu(String ten, HangSanPham hangSanPham, Camera camera, ManHinh manHinh, Anh anh);
    boolean existSanPham(String ten);

}


