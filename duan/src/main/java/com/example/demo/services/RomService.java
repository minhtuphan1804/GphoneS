package com.example.demo.services;

import com.example.demo.models.Rom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RomService {
    public List<Rom> findAll();

    public List<Rom> findAll0();

    public Rom findById(UUID id);

    public Rom add(Rom rom);

    public List<Rom> search(String dungluong);

    public List<Rom> search2(String dungluong);

    public Rom update(UUID id, Rom rom);

    public Boolean delete(UUID id);

    public List<Rom> getAll0();

    public List<Rom> getall1();

    public void update0();
    boolean existRom(String dungLuong);
}


