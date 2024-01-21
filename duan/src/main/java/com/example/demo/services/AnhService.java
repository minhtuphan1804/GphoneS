package com.example.demo.services;

import com.example.demo.models.Anh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AnhService {
    public List<Anh> getAll();

    public List<Anh> getAll1();

    public List<Anh> findAll();

    public List<Anh> findAll0();

    public Anh findById(UUID id);

    public Anh add(Anh anh);

    public Anh update(UUID id, Anh anh);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<Anh> search0(String ten);

    public List<Anh> search1(String ten);
}


