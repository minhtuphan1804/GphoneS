package com.example.demo.repositories;

import com.example.demo.models.HangKhachHang;
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
public interface HangKhachHangRepository extends JpaRepository<HangKhachHang, UUID> {
    @Query("select hkh from HangKhachHang hkh  where hkh.tinhTrang=0")
    List<HangKhachHang> getall0();

    @Query("select hkh from HangKhachHang hkh  where hkh.tinhTrang=0")
    Page<HangKhachHang> getall00(Pageable pageable);

    @Query("select hkh from HangKhachHang hkh  where hkh.ma=:timkiem")
    HangKhachHang timkiemma(String timkiem);

    @Query("select kh from HangKhachHang kh  where kh.ma like %:timkiem% and kh.tinhTrang=0 or kh.ten like %:timkiem%  and kh.tinhTrang=0")
    List<HangKhachHang> timkiemMT(String timkiem);

    @Query("select hkh from HangKhachHang hkh  where hkh.tinhTrang=1")
    List<HangKhachHang> getall1();

    @Query("select hkh from HangKhachHang hkh  where hkh.tinhTrang=1")
    Page<HangKhachHang> getall11(Pageable pageable);

    @Query("select kh from HangKhachHang kh  where kh.ma like %:timkiem% and kh.tinhTrang=1 or kh.ten like %:timkiem%  and kh.tinhTrang=1")
    List<HangKhachHang> timkiemMT1(String timkiem);

    @Transactional
    @Modifying
    @Query(value = "update hang_khach_hang set tinh_trang=0 , ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void update0();
}
