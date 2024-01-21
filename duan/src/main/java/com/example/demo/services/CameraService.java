package com.example.demo.services;

import com.example.demo.models.Camera;
import com.example.demo.models.Chip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CameraService {
    public List<Camera> getAll();

    public List<Camera> getAll1();

    public List<Camera> findAll();

    public List<Camera> findAll0();

    public Camera findById(UUID id);

    public Camera add(Camera camera);

    public Camera update(UUID id, Camera camera);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<Camera> search0(String ten);

    public List<Camera> search1(String ten);
    boolean existCamera(String thongSo);

}
