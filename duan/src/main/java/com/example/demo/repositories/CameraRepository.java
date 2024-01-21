package com.example.demo.repositories;

import com.example.demo.models.Camera;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CameraRepository extends JpaRepository<Camera, UUID> {
    @Query("select c from Camera c  where c.tinhTrang=0")
    List<Camera> getAll();

    @Query("select c from Camera c  where c.tinhTrang=1")
    List<Camera> getAll1();

    @Query("select c from Camera c  where  c.tinhTrang = 0 ")
    List<Camera> findAll0();

    @Query("select c from Camera c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.thongSo like %:ten%)")
    List<Camera> sreach0(String ten);

    @Query("select c from Camera c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.thongSo like %:ten%)")
    List<Camera> sreach1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update Camera set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
    boolean existsByThongSo(String thongSo);
}
