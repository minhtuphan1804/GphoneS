package com.example.demo.services;

import com.example.demo.models.Chip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChipService {
    public List<Chip> getAll();

    public List<Chip> getAll1();

    public List<Chip> findAll();

    public List<Chip> findAll0();

    public Chip findById(UUID id);

    public Chip add(Chip chip);

    public Chip update(UUID id, Chip chip);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<Chip> search0(String ten);

    public List<Chip> search1(String ten);
    boolean existChip(String ten,String loaiChip);
}

