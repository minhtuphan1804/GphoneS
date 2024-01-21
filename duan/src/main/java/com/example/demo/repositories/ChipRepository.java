package com.example.demo.repositories;

import com.example.demo.models.Chip;
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
public interface ChipRepository extends JpaRepository<Chip, UUID> {
    @Query("select c from Chip c  where c.tinhTrang=0")
    List<Chip> getAll();

    @Query("select c from Chip c  where c.tinhTrang=1")
    List<Chip> getAll1();

    @Query("select c from Chip c  where  c.tinhTrang = 0")
    List<Chip> findAll0();

    @Query("select c from Chip c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.ten like %:ten% or c.loaiChip like %:ten%)")
    List<Chip> sreach0(String ten);

    @Query("select c from Chip c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.ten like %:ten% or c.loaiChip like %:ten% )")
    List<Chip> sreach1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update Chip set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
    boolean existsByTenAndLoaiChip(String ten, String loaiChip);

}
