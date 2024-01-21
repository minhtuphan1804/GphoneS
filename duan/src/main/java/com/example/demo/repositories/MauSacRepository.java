package com.example.demo.repositories;

import com.example.demo.models.MauSac;
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
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    @Query("select ms from MauSac ms  where ms.tinhTrang=0")
    List<MauSac> getAll();

    @Query("select ms from MauSac ms  where ms.tinhTrang=0")
    List<MauSac> findAll0();

    @Query("select ms from MauSac ms  where ms.tinhTrang=1")
    List<MauSac> getAll1();

    @Query("select ms from MauSac ms  where ms.tinhTrang=0 and (ms.ma like %:ten% or ms.ten like %:ten%)")
    List<MauSac> search0(String ten);

    @Query("select ms from MauSac ms  where ms.tinhTrang=1 and (ms.ma like %:ten% or ms.ten like %:ten%)")
    List<MauSac> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update  mau_sac set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
    boolean existsByTen(String ten);
}
