package com.example.demo.repositories;

import com.example.demo.models.Chip;
import com.example.demo.models.DungLuongPin;
import com.example.demo.models.Pin;
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
public interface PinRepository extends JpaRepository<Pin, UUID> {

    @Query("select p from Pin p  where p.tinhTrang= 0")
    List<Pin> getAll();

    @Query("select p from Pin p  where p.tinhTrang= 0")
    List<Pin> findAll0();

    @Query("select p from Pin p  where p.tinhTrang= 1")
    List<Pin> getAll1();

    @Query("select p from Pin p  where  p.tinhTrang = 0 and (p.ma like %:ten% or p.congNghePin like %:ten% or p.loaiPin like %:ten%)")
    List<Pin> sreach0(String ten);

    @Query("select p from Pin p  where  p.tinhTrang = 1 and (p.ma like %:ten% or p.congNghePin like %:ten% or p.loaiPin like %:ten%)")
    List<Pin> sreach1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update Pin set tinh_trang = 0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
    boolean existsByLoaiPinAndCongNghePinAndDungLuongPin(String loaiPin,String congNghePin,DungLuongPin dungLuongPin);
}
