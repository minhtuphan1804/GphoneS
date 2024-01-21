package com.example.demo.services.impl;

import com.example.demo.models.Chip;
import com.example.demo.repositories.ChipRepository;
import com.example.demo.services.ChipService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ChipServiceImpl implements ChipService {

    @Autowired
    ChipRepository chipRepository;


    @Override
    public List<Chip> getAll() {
        return chipRepository.getAll();
    }

    @Override
    public List<Chip> getAll1() {
        return chipRepository.getAll1();
    }

    @Override
    public List<Chip> findAll() {
        return chipRepository.findAll();
    }

    @Override
    public List<Chip> findAll0() {
        return chipRepository.findAll0();
    }

    @Override
    public Chip findById(UUID id) {
        return chipRepository.findById(id).orElse(null);
    }

    @Override
    public Chip add(Chip chip) {
        return chipRepository.save(chip);
    }

    @Override
    public Chip update(UUID id, Chip chip) {
        if (id != null) {
            Chip chipUpdate = chipRepository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                chipRepository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        chipRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Chip chip = chipRepository.findById(id).orElse(null);
            if (chip != null) {
                chipRepository.delete(chip);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Chip> search0(String ten) {
        return chipRepository.sreach0(ten);
    }

    @Override
    public List<Chip> search1(String ten) {
        return chipRepository.sreach1(ten);
    }

    @Override
    public boolean existChip(String ten, String loaiChip) {
        return chipRepository.existsByTenAndLoaiChip(ten, loaiChip);
    }

}
