package com.example.demo.repositories;

import com.example.demo.models.Chip;
import com.example.demo.models.DungLuongPin;
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
public interface DungLuongPinRepository extends JpaRepository<DungLuongPin, UUID> {

    @Query("select d from DungLuongPin d  where d.tinhTrang= 0")
    List<DungLuongPin> getAll();

    @Query("select d from DungLuongPin d  where d.tinhTrang= 1")
    List<DungLuongPin> getAll1();

    @Query("select d from DungLuongPin d  where  d.tinhTrang = 0 ")
    List<DungLuongPin> findAll0();

    @Query("select d from DungLuongPin d  where  d.tinhTrang = 0 and (d.ma like %:ten% or d.thongSo like %:ten% )")
    List<DungLuongPin> sreach0(String ten);

    @Query("select d from DungLuongPin d  where  d.tinhTrang = 1 and (d.ma like %:ten% or d.thongSo like %:ten% )")
    List<DungLuongPin> sreach1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update dung_luong_pin set tinh_trang = 0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
