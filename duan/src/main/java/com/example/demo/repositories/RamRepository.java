package com.example.demo.repositories;

import com.example.demo.models.Ram;
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
public interface RamRepository extends JpaRepository<Ram, UUID> {
    @Query("select r from Ram r where r.tinhTrang= 0")
    List<Ram> getAll();

    @Query("select r from Ram r where r.tinhTrang= 0")
    List<Ram> findAll0();

    @Query("select r from Ram r where r.tinhTrang= 1")
    List<Ram> getAll1();

    @Query("select r from Ram r where r.tinhTrang= 0 and (r.ma like %:ten% or r.dungLuong like %:ten%)")
    List<Ram> search0(String ten);

    @Query("select r from Ram r where r.tinhTrang= 1 and (r.ma like %:ten% or r.dungLuong like %:ten%)")
    List<Ram> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update Ram set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
    boolean existsByDungLuong(String dungLuong);
}
