package com.example.demo.services.impl;

import com.example.demo.models.DungLuongPin;
import com.example.demo.models.Pin;
import com.example.demo.repositories.PinRepository;
import com.example.demo.services.PinService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class PinServiceImpl implements PinService {

    @Autowired
    PinRepository pinRepository;


    @Override
    public List<Pin> getAll() {
        return pinRepository.getAll();
    }

    @Override
    public List<Pin> getAll1() {
        return pinRepository.getAll1();
    }

    @Override
    public List<Pin> findAll() {
        return pinRepository.findAll();
    }

    @Override
    public List<Pin> findAll0() {
        return pinRepository.findAll0();
    }

    @Override
    public Pin findById(UUID id) {
        return pinRepository.findById(id).orElse(null);
    }

    @Override
    public Pin add(Pin pin) {
        return pinRepository.save(pin);
    }

    @Override
    public Pin update(UUID id, Pin pin) {
        if (id != null) {
            Pin pinUpdate = pinRepository.findById(id).orElse(null);
            if (pinUpdate != null) {
                BeanUtils.copyProperties(pin, pinUpdate);
                pinRepository.save(pinUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
          pinRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Pin pin = pinRepository.findById(id).orElse(null);
            if (pin != null) {
                pinRepository.delete(pin);
                return true;
            }
        }
        return false;
    }



    @Override
    public List<Pin> search1(String ten) {
        return pinRepository.sreach1(ten);
    }

    @Override
    public boolean existPin(String loaiPin, String congNghePin, DungLuongPin dungLuongPin) {
        return pinRepository.existsByLoaiPinAndCongNghePinAndDungLuongPin(loaiPin, congNghePin, dungLuongPin);
    }

    @Override
    public List<Pin> search0(String ten) {
        return pinRepository.sreach0(ten);
    }
}
