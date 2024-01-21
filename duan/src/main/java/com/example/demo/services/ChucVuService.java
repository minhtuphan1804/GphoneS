package com.example.demo.services;

import com.example.demo.models.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {


    public List<ChucVu> getAll();

    public List<ChucVu> getAll1();

    public List<ChucVu> findAll();

    public List<ChucVu> findAll0();

    public ChucVu findById(UUID id);

    public ChucVu add(ChucVu chucVu);

    public ChucVu update(UUID id, ChucVu chucVu);

    public Boolean delete(UUID id);

    public void updateTT();

    public List<ChucVu> search0(String ten);

    public List<ChucVu> search1(String ten);
}
