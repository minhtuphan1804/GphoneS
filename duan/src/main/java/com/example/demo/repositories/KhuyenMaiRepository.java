package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DiaChi;
import com.example.demo.models.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, UUID> {
    @PersistenceContext
    EntityManager entityManager = null;


    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (SELECT SUM(b.so_tien_giam) AS tonggiamgia FROM chi_tiet_san_pham a LEFT JOIN khuyen_mai b ON a.id_khuyen_mai = b.id  " +
            "             WHERE a.id = :idctsp AND a.tinh_trang = 0 AND b.tinh_trang = 0);" +

            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer tonggiamgia(UUID idctsp);

    @Query("select hkh from KhuyenMai hkh  where hkh.tinhTrang=0")
    List<KhuyenMai> getall0();

    @Query("select hkh from KhuyenMai hkh  where hkh.tinhTrang=0")
    Page<KhuyenMai> getall00(Pageable pageable);

    @Query("select hkh from KhuyenMai hkh  where hkh.tinhTrang=1")
    List<KhuyenMai> getall1();

    @Query("select hkh from KhuyenMai hkh  where hkh.tinhTrang=1")
    Page<KhuyenMai> getall11(Pageable pageable);

    @Query("select hkh from ChiTietSanPham hkh  where hkh.tinhTrang=0")
    List<ChiTietSanPham> getall0CTSP();

    @Query("select hkh from KhuyenMai hkh  ")
    Page<KhuyenMai> fillalllPageable(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update chi_tiet_san_pham set id_khuyen_mai=null where id_khuyen_mai in (select id from khuyen_mai where tinh_trang=1)", nativeQuery = true)
    void xoalienketKM1();

    @Query("select kh from KhuyenMai kh  where kh.ma like %:timkiem%  or kh.ten like %:timkiem% ")
    List<KhuyenMai> timkiemKM(String timkiem);


    @Transactional
    @Modifying
    @Query(value = " update chi_tiet_san_pham set id_khuyen_mai=null where id_khuyen_mai=:idkm\n" +
            "    delete from khuyen_mai where id=:idkm", nativeQuery = true)
    void XoaKhuyenMai(@Param("idkm") UUID idkm);


    @Transactional
    @Modifying
    @Query(value = "update chi_tiet_san_pham set id_khuyen_mai=null where id=:idctsp", nativeQuery = true)
    void HuyApDungKMvs1ctsp(@Param("idctsp") UUID idctsp);


    @Query("select kh from KhuyenMai kh  where kh.tinhTrang=:tinhtrang ")
    List<KhuyenMai> TimTrangThaiKM(@Param("tinhtrang") Integer tinhtrang );

    @Query("select hkh from KhuyenMai hkh order by hkh.ma desc ")
    List<KhuyenMai> danhsachgiamdan();

}
