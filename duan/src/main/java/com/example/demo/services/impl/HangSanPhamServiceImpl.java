package com.example.demo.services.impl;

import com.example.demo.models.HangSanPham;
import com.example.demo.models.Rom;
import com.example.demo.repositories.HangSanPhamRepository;
import com.example.demo.services.HangSanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class HangSanPhamServiceImpl implements HangSanPhamService {

    @Autowired
    HangSanPhamRepository hangSanPhamRepository;


    @Override
    public Page<HangSanPham> getAll(Pageable pageable) {
        return hangSanPhamRepository.findAll(pageable);
    }

    @Override
    public List<HangSanPham> findAll() {
        return hangSanPhamRepository.findAll();
    }

    @Override
    public List<HangSanPham> findAll0() {
        return hangSanPhamRepository.findAll0();
    }

    @Override

    public HangSanPham findById(UUID id) {
        return hangSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public HangSanPham add(HangSanPham hangSanPham) {
        return hangSanPhamRepository.save(hangSanPham);
    }

    @Override
    public HangSanPham update(UUID id, HangSanPham hangSanPham) {
        if (id != null) {
            HangSanPham hangSanPhamUpdate = hangSanPhamRepository.findById(id).orElse(null);
            if (hangSanPhamUpdate != null) {
                BeanUtils.copyProperties(hangSanPham, hangSanPhamUpdate);
                hangSanPhamRepository.save(hangSanPhamUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            HangSanPham hangSanPham = hangSanPhamRepository.findById(id).orElse(null);
            if (hangSanPham != null) {
                hangSanPhamRepository.delete(hangSanPham);
                return true;
            }
        }
        return false;
    }
    @Override
    public List<HangSanPham>search(String dungluong){
        return hangSanPhamRepository.search(dungluong);
    }
    @Override
    public List<HangSanPham>search2(String dungluong){
        return hangSanPhamRepository.search2(dungluong);
    }

    @Override
    public void updateTT() {
        hangSanPhamRepository.update0();
    }

    @Override
    public List<HangSanPham> getAll0() {
        return hangSanPhamRepository.getall0();
    }

    @Override
    public List<HangSanPham> getall1() {
        return hangSanPhamRepository.getall1();
    }

    @Override
    public boolean existHang(String ten) {
        return hangSanPhamRepository.existsByTen(ten);
    }
}
