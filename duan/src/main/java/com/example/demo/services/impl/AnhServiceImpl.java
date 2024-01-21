package com.example.demo.services.impl;

import com.example.demo.models.Anh;
import com.example.demo.repositories.AnhRepository;
import com.example.demo.services.AnhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class AnhServiceImpl implements AnhService {

    @Autowired
    AnhRepository anhRepository;

    @Override
    public List<Anh> getAll() {
        return anhRepository.getAll();
    }

    @Override
    public List<Anh> getAll1() {
        return anhRepository.getAll1();
    }

    @Override
    public List<Anh> findAll() {
        return anhRepository.findAll();
    }

    @Override
    public List<Anh> findAll0() {
        return anhRepository.findAll0();
    }

    @Override
    public Anh findById(UUID id) {
        return anhRepository.findById(id).orElse(null);
    }

    @Override
    public Anh add(Anh chip) {
        return anhRepository.save(chip);
    }

    @Override
    public Anh update(UUID id, Anh chip) {
        if (id != null) {
            Anh chipUpdate = anhRepository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                anhRepository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        anhRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Anh chip = anhRepository.findById(id).orElse(null);
            if (chip != null) {
                anhRepository.delete(chip);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Anh> search0(String ten) {
        return anhRepository.sreach0(ten);
    }

    @Override
    public List<Anh> search1(String ten) {
        return anhRepository.sreach1(ten);
    }
}
