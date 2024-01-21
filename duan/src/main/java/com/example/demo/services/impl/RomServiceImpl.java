package com.example.demo.services.impl;

import com.example.demo.models.KhachHang;
import com.example.demo.models.Rom;
import com.example.demo.repositories.RomRepository;
import com.example.demo.services.RomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class RomServiceImpl implements RomService {

    @Autowired
    RomRepository romRepository;

//    @Override
//    public Page<Rom> getALL0(Pageable pageable) {
//        return romRepository.getall0(pageable);
//    }

//    @Override
//    public Page<Rom> getAll(Pageable pageable) {
//        return romRepository.findAll(pageable);
//    }


    @Override
    public List<Rom> findAll() {
        return romRepository.findAll();
    }

    @Override
    public List<Rom> findAll0() {
        return romRepository.findAll0();
    }

    @Override
    public Rom findById(UUID id) {
        return romRepository.findById(id).orElse(null);
    }

    @Override
    public Rom add(Rom rom) {
        return romRepository.save(rom);
    }

    @Override
    public List<Rom> search(String dungluong) {
        return romRepository.search(dungluong);
    }

    @Override
    public List<Rom> search2(String dungluong) {
        return romRepository.search2(dungluong);
    }

    @Override
    public Rom update(UUID id, Rom rom) {
        if (id != null) {
            Rom romUpdate = romRepository.findById(id).orElse(null);
            if (romUpdate != null) {
                BeanUtils.copyProperties(rom, romUpdate);
                romRepository.save(romUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Rom rom = romRepository.findById(id).orElse(null);
            if (rom != null) {
                romRepository.delete(rom);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Rom> getAll0() {
        return romRepository.getall0();
    }

    @Override
    public List<Rom> getall1() {
        return romRepository.getall1();
    }

    @Override
    public void update0() {
        romRepository.update0();
    }

    @Override
    public boolean existRom(String dungLuong) {
        return romRepository.existsByDungLuong(dungLuong);
    }
}
