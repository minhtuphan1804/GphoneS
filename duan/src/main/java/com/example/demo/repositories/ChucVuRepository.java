package com.example.demo.repositories;

import com.example.demo.models.ChucVu;
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
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {

    @Query("select cv from ChucVu cv  where cv.tinhTrang=0")
    List<ChucVu> getAll();


    @Query("select cv from ChucVu cv  where cv.tinhTrang=0")
    List<ChucVu> findAll();

    @Query("select cv from ChucVu cv  where cv.tinhTrang=1")
    List<ChucVu> getAll1();

    @Query("select cv from ChucVu cv  where cv.tinhTrang=0 ")
    List<ChucVu> findAll0();

    @Query("select cv from ChucVu cv  where cv.tinhTrang=0 and (cv.ma like %:ten% or cv.ten like %:ten%)")
    List<ChucVu> search0(String ten);

    @Query("select cv from ChucVu cv  where cv.tinhTrang=1 and (cv.ma like %:ten% or cv.ten like %:ten%)")
    List<ChucVu> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update  chuc_vu set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
