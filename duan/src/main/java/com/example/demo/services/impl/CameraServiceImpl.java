package com.example.demo.services.impl;

import com.example.demo.models.Camera;
import com.example.demo.models.Chip;
import com.example.demo.repositories.CameraRepository;
import com.example.demo.services.CameraService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public List<Camera> getAll() {
        return cameraRepository.getAll();
    }

    @Override
    public List<Camera> getAll1() {
        return cameraRepository.getAll1();
    }

    @Override
    public List<Camera> findAll() {
        return cameraRepository.findAll();
    }

    @Override
    public List<Camera> findAll0() {
        return cameraRepository.findAll0();
    }

    @Override
    public Camera findById(UUID id) {
        return cameraRepository.findById(id).orElse(null);
    }

    @Override
    public Camera add(Camera chip) {
        return cameraRepository.save(chip);
    }

    @Override
    public Camera update(UUID id, Camera chip) {
        if (id != null) {
            Camera chipUpdate = cameraRepository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                cameraRepository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        cameraRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Camera chip = cameraRepository.findById(id).orElse(null);
            if (chip != null) {
                cameraRepository.delete(chip);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Camera> search0(String ten) {
        return cameraRepository.sreach0(ten);
    }

    @Override
    public List<Camera> search1(String ten) {
        return cameraRepository.sreach1(ten);
    }

    @Override
    public boolean existCamera(String thongSo) {
        return cameraRepository.existsByThongSo(thongSo);
    }
}
