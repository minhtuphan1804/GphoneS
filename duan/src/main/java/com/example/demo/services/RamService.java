package com.example.demo.services;

import com.example.demo.models.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RamService {
    public List<Ram> getAll();

    public List<Ram> getAll1();

    public List<Ram> findAll();

    public List<Ram> findAll0();

    public Ram findById(UUID id);


    public Ram add(Ram ram);

    public Ram update(UUID id, Ram ram);

    public Boolean delete(UUID id);

    public void updateTT();

    public List<Ram> search0(String ten);

    public List<Ram> search1(String ten);
    boolean existRam(String dungLuong);
}



