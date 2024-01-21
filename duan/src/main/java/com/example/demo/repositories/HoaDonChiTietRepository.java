package com.example.demo.repositories;

import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.IMEI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("select i from HoaDonChiTiet hdct left join HoaDon hd on hdct.hoaDon.id=hd.id left join IMEI i on hdct.imei.id=i.id " +
            "left join ChiTietSanPham ct on i.chiTietSanPham.id=ct.id where ct.id=: id or hd.id=: id")
    Page<HoaDonChiTiet> getHoaDonChiTiet(Pageable pageable, UUID id);

    @Query("select hdct from HoaDonChiTiet hdct left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=: id")
    Page<HoaDonChiTiet> getHoaDonChiTietPage(Pageable pageable, UUID id);

    @Query("select hdct from HoaDonChiTiet hdct left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id")
    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);

    @Query("select hdct from HoaDonChiTiet hdct left join IMEI imei on hdct.imei.id=imei.id left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id " +
            "and (imei.soImei like %:search% or imei.chiTietSanPham.sanPham.ten like %:search% or imei.chiTietSanPham.sanPham.camera.thongSo like %:search% or imei.chiTietSanPham.sanPham.manHinh.thongSo like %:search% or " +
            "imei.chiTietSanPham.sanPham.hangSanPham.ten like %:search% or imei.chiTietSanPham.mauSac.ten like %:search% or " +
            "imei.chiTietSanPham.chip.ten like %:search% or imei.chiTietSanPham.ram.dungLuong like %:search% or imei.chiTietSanPham.rom.dungLuong like %:search%)")
    List<HoaDonChiTiet> searchHDCTBanHangTaiQuay(UUID id, String search);


    @Query("select hdct from HoaDonChiTiet hdct left join IMEI imei on hdct.imei.id=imei.id left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id " +
            "and (imei.soImei like %:search% or imei.chiTietSanPham.sanPham.ten like %:search% or imei.chiTietSanPham.sanPham.camera.thongSo like %:search% or imei.chiTietSanPham.sanPham.manHinh.thongSo like %:search% or " +
            "imei.chiTietSanPham.sanPham.hangSanPham.ten like %:search% or imei.chiTietSanPham.mauSac.ten like %:search% or " +
            "imei.chiTietSanPham.chip.ten like %:search% or imei.chiTietSanPham.ram.dungLuong like %:search% or imei.chiTietSanPham.rom.dungLuong like %:search%)")
    List<HoaDonChiTiet> search(UUID id, String search);

    @Transactional
    @Modifying
    @Query("update HoaDonChiTiet hd set hd.tinhTrang=8 where hd.id=:id")
    void updateTinhTrangDelete(UUID id);

}
