package com.example.demo.repositories;

import com.example.demo.models.Rom;
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
public interface RomRepository extends JpaRepository<Rom, UUID> {
    @Query("SELECT r FROM Rom r WHERE r.dungLuong LIKE %:dungluong% and r.tinhTrang=0")
    List<Rom> search(String dungluong);

    @Query("SELECT r FROM Rom r WHERE  r.tinhTrang=0")
    List<Rom> findAll0();

    @Query("SELECT r FROM Rom r WHERE r.dungLuong LIKE %:dungluong% and r.tinhTrang=1")
    List<Rom> search2(String dungluong);

    @Query("select r from Rom r  where r.tinhTrang=0")
    List<Rom> getall0();

    @Query("select r from Rom r  where r.tinhTrang=1")
    List<Rom> getall1();

    @Transactional
    @Modifying
    @Query(value = "update rom set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void update0();
    boolean existsByDungLuong(String dungLuong);
}
