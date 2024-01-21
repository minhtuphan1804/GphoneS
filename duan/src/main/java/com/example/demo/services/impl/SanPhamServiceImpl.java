package com.example.demo.services.impl;

import com.example.demo.models.Anh;
import com.example.demo.models.Camera;
import com.example.demo.models.HangSanPham;
import com.example.demo.models.ManHinh;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.SanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;


    @Override
    public Page<SanPham> getAll(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> findAll0() {
        return sanPhamRepository.findAll0();
    }

    @Override
    public List<SanPham> findAll1() {
        return sanPhamRepository.findAll1();
    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public SanPham add(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(UUID id, SanPham sanPham) {
        if (id != null) {
            SanPham sanPhamUpdate = sanPhamRepository.findById(id).orElse(null);
            if (sanPhamUpdate != null) {
                BeanUtils.copyProperties(sanPham, sanPhamUpdate);
                sanPhamRepository.save(sanPhamUpdate);
            }
        }
        return null;
    }

    @Override
    public SanPham update1(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            SanPham sanPham = sanPhamRepository.findById(id).orElse(null);
            if (sanPham != null) {
                sanPhamRepository.delete(sanPham);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SanPham> search(String dungluong) {
        return sanPhamRepository.search(dungluong);
    }

    @Override
    public List<SanPham> search2(String dungluong) {
        return sanPhamRepository.search2(dungluong);
    }

    @Override
    public List<SanPham> getAll0() {
        return sanPhamRepository.getall0();
    }

    @Override
    public List<SanPham> getall1() {
        return sanPhamRepository.getall1();
    }

    @Override
    public void update0() {
        sanPhamRepository.update0();
    }

    @Override
    public List<SanPham> loc(UUID idHang, UUID idMan, UUID idCamera) {
        return sanPhamRepository.loc(idHang, idMan, idCamera);
    }

    @Override
    public boolean trungDuLieu(String ten, HangSanPham hangSanPham, Camera camera,ManHinh manHinh,  Anh anh) {
        return sanPhamRepository.existsByTenAndHangSanPhamAndCameraAndManHinhAndAnh(ten, hangSanPham, camera,manHinh,  anh);
    }

    @Override
    public boolean existSanPham(String ten) {
        return sanPhamRepository.existsByTen(ten);
    }
}
