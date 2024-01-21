package com.example.demo.repositories;

import com.example.demo.models.DiaChi;
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
public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
//    @Query("select dc from DiaChi dc  where dc.tinhTrang=0")
//    List<DiaChi> getall0();
    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=0 and hkh.khachHang.id=:khachHangId")
    List<DiaChi> findByKhachHangId(UUID khachHangId);

    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=0")
    List<DiaChi> getall0();

    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=0")
    Page<DiaChi> getall00(Pageable pageable);

    @Query("select hkh from DiaChi hkh  where hkh.ma=:timkiem")
    DiaChi timkiemma(String timkiem);

    @Query("select kh from DiaChi kh  where kh.ma like %:timkiem% and kh.tinhTrang=0 or kh.diaChi like %:timkiem%  and kh.tinhTrang=0")
    List<DiaChi> timkiemMT(String timkiem);

    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=0")
    List<DiaChi> findAll0();

    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=1")
    List<DiaChi> getall1();

    @Query("select hkh from DiaChi hkh  where hkh.tinhTrang=1")
    Page<DiaChi> getall11(Pageable pageable);

    @Query("select kh from DiaChi kh  where kh.ma like %:timkiem% and kh.tinhTrang=1 or kh.diaChi like %:timkiem%  and kh.tinhTrang=1")
    List<DiaChi> timkiemMT1(String timkiem);

    @Transactional
    @Modifying
    @Query(value = "update dia_chi set tinh_trang=0 , ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void update0();

    @Query("select kh from DiaChi kh  where kh.khachHang.id=:idKhachHang  and kh.tinhTrang=0")
    List<DiaChi> timkiem(UUID idKhachHang);

    @Query("select kh from DiaChi kh  where kh.khachHang.id=:id  and kh.tinhTrang=0")
    List<DiaChi> diaChiThanhToan(UUID id);
}
