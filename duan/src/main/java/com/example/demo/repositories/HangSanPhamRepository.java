package com.example.demo.repositories;

import com.example.demo.models.HangSanPham;
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
public interface HangSanPhamRepository extends JpaRepository<HangSanPham, UUID> {

    @Query("SELECT r FROM HangSanPham r WHERE  r.tinhTrang=0")
    List<HangSanPham> findAll0();

    @Query("SELECT r FROM HangSanPham r WHERE r.ten LIKE %:dungluong% and r.tinhTrang=0")
    List<HangSanPham> search(String dungluong);

    @Query("SELECT r FROM HangSanPham r WHERE r.ten LIKE %:dungluong% and r.tinhTrang=1")
    List<HangSanPham> search2(String dungluong);

    @Query("select r from HangSanPham r  where r.tinhTrang=0")
    List<HangSanPham> getall0();

    @Query("select r from HangSanPham r  where r.tinhTrang=1")
    List<HangSanPham> getall1();

    @Transactional
    @Modifying
    @Query(value = "update hang_dien_thoai set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void update0();
    boolean existsByTen(String ten);
}
