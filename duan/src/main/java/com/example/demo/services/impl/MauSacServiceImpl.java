package com.example.demo.services.impl;

import com.example.demo.models.MauSac;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.services.MauSacService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class MauSacServiceImpl implements MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;


    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAll();
    }

    @Override
    public List<MauSac> getAll1() {
        return mauSacRepository.getAll1();
    }

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public List<MauSac> findAll0() {
        return mauSacRepository.findAll0();
    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepository.findById(id).orElse(null);
    }

    @Override
    public MauSac add(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(UUID id, MauSac mauSac) {
        if (id != null) {
            MauSac mauSacUpdate = mauSacRepository.findById(id).orElse(null);
            if (mauSacUpdate != null) {
                BeanUtils.copyProperties(mauSac, mauSacUpdate);
                mauSacRepository.save(mauSacUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            MauSac mauSac = mauSacRepository.findById(id).orElse(null);
            if (mauSac != null) {
                mauSacRepository.delete(mauSac);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTT() {
        mauSacRepository.updateTT();

    }

    @Override
    public List<MauSac> search0(String ten) {
        return mauSacRepository.search0(ten);
    }

    @Override
    public List<MauSac> search1(String ten) {
        return mauSacRepository.search1(ten);
    }

    @Override
    public boolean exitstByTen(String ten) {
        return mauSacRepository.existsByTen(ten);
    }
}
