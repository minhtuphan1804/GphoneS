package com.example.demo.repositories;

import com.example.demo.DTO.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.viewmodels.DSfullSanPhamDatHangOnline;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface BanHangOnLinerepository extends JpaRepository<KhachHang, UUID> {


    @PersistenceContext
    EntityManager entityManager = null;

    //    @Transactional
//    @Query(value = "DECLARE @bienb int; " +
//            "SET @bienb = (SELECT SUM(b.so_tien_giam) AS tonggiamgia FROM san_pham_giam_gia a LEFT JOIN khuyen_mai b ON a.id_khuyen_mai = b.id " +
//            "             WHERE a.id_chi_tiet_san_pham = :idctsp AND a.tinh_trang = 0 AND b.tinh_trang = 0); " +
//            "IF @bienb > 80 " +
//            "BEGIN " +
//            "   SET @bienb = 80; " +
//            "END; " +
//            "SELECT CAST(@bienb AS int);", nativeQuery = true)
//    Integer tonggiamgia(UUID idctsp);
    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (SELECT SUM(b.so_tien_giam) AS tonggiamgia FROM chi_tiet_san_pham a LEFT JOIN khuyen_mai b ON a.id_khuyen_mai = b.id  " +
            "             WHERE a.id = :idctsp AND a.tinh_trang = 0 AND b.tinh_trang = 0);" +

            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer tonggiamgia(UUID idctsp);

    @Query("select ct from SanPham sp left join ChiTietSanPham ct on sp.id=ct.sanPham.id where sp.tinhTrang=0 and ct.tinhTrang=0 order by sp.ngayTao desc")
    List<ChiTietSanPham> ctspbanhang();

    @Query(value = "SELECT TOP 10\n" +
            "    san_pham.ten,\n" +
            "    COUNT(imei.id) AS so_luong_ban\n" +
            "FROM hoa_don\n" +
            "LEFT JOIN hoa_don_chi_tiet ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "LEFT JOIN imei ON imei.id = hoa_don_chi_tiet.id_imei\n" +
            "LEFT JOIN chi_tiet_san_pham ON chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "LEFT JOIN san_pham ON san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "WHERE hoa_don.tinh_trang = 2\n" +
            "GROUP BY san_pham.id, san_pham.ten\n" +
            "ORDER BY so_luong_ban DESC;", nativeQuery = true)
    List<Top10SanPham> top10SanPhamBanChay();


//    @Query("select ct from SanPham sp left join ChiTietSanPham ct on sp.id=ct.sanPham.id where sp.tinhTrang=0 and ct.tinhTrang=0 ")
//    List<ChiTietSanPham> ctspbanhangBanChay();

    @Query("select hdct from HoaDonChiTiet hdct left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id")
    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);

    @Transactional
    @Query(value = "DECLARE @bienb int;\n" +
            "DECLARE @bienc int;\n" +
            "DECLARE @biena int;\n" +

            " SET @bienb = (select COUNT(id_chi_tiet_san_pham) from \n" +
            " imei where tinh_trang=0 and id_chi_tiet_san_pham=:idctsp) ;\n" +

            "SET @bienc = (select sum(a.so_luong) \n" +
            "from don_dat_hang a group by a.id_chi_tiet_san_pham,a.tinh_trang\n" +
            "having tinh_trang=0 and id_chi_tiet_san_pham=:idctsp) ;\n" +

            "if @bienc is null\n" +
            "            begin\n" +
            "            set @bienc=0;\n" +
            "            end\n" +

            "SET @biena=@bienb-@bienc;\n" +

            "if @biena <0\n" +
            "            begin\n" +
            "            set @biena=0;\n" +
            "            end\n" +

            "            SELECT CAST(@biena AS int);", nativeQuery = true)
    Integer soluongcon(UUID idctsp);

    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (select COUNT(id_chi_tiet_san_pham) from " +
            "imei where tinh_trang=0 and id_chi_tiet_san_pham=:idctsp) ;" +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer tongimeiTT0cua1ctsp(UUID idctsp);

    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (select COUNT(id_chi_tiet_san_pham) from " +
            "imei where tinh_trang=1 and id_chi_tiet_san_pham=:idctsp) ;" +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer soluongdaban(UUID idctsp);

    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN HangSanPham hang ON sp.hangSanPham.id = hang.id " +
            "LEFT JOIN Ram ram ON ct.ram.id = ram.id " +
            "LEFT JOIN Rom rom ON ct.rom.id = rom.id " +
            "LEFT JOIN Pin pin ON ct.pin.id = pin.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
            "LEFT JOIN Chip chip ON ct.chip.id = chip.id " +
            "LEFT JOIN DungLuongPin dungLuong ON pin.dungLuongPin.id = dungLuong.id " +
            "LEFT JOIN ManHinh manHinh ON sp.manHinh.id = manHinh.id " +
            "LEFT JOIN Camera cam ON sp.camera.id = cam.id " +
            "WHERE " +
            "(:idHang = 'null' OR hang.ten = :idHang) " +
            "AND (:idRam = 'null' OR ram.dungLuong = :idRam) " +
            "AND (:idRom = 'null' OR rom.dungLuong = :idRom) " +
            "AND (:idPin = 'null' OR pin.loaiPin = :idPin) " +
            "AND (:idDLPin = 'null' OR dungLuong.thongSo = :idDLPin) " +
            "AND (:idChip = 'null' OR chip.ten = :idChip) " +
            "AND (:moTaMan = 'null' OR manHinh.thongSo = :moTaMan) " +
            "AND (:moTaCam = 'null' OR cam.thongSo = :moTaCam) " +
            "AND (:moTaMau = 'null' OR ms.ten = :moTaMau) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) " +
            "AND (ct.tinhTrang=0) "
    )
    List<ChiTietSanPham> locbanhang(@Param("idHang") String idHang,
                                    @Param("moTaCam") String moTaCam,
                                    @Param("moTaMan") String moTaMan,
                                    @Param("moTaMau") String moTaMau,
                                    @Param("idRam") String idRam,
                                    @Param("idRom") String idRom,
                                    @Param("idPin") String idPin,
                                    @Param("idDLPin") String idDLPin,
                                    @Param("idChip") String idChip,
                                    @Param("tenSP") String tenSP);


    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN HangSanPham hang ON sp.hangSanPham.id = hang.id " +
            "LEFT JOIN Ram ram ON ct.ram.id = ram.id " +
            "LEFT JOIN Rom rom ON ct.rom.id = rom.id " +
            "LEFT JOIN Pin pin ON ct.pin.id = pin.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
            "LEFT JOIN Chip chip ON ct.chip.id = chip.id " +
            "LEFT JOIN DungLuongPin dungLuong ON pin.dungLuongPin.id = dungLuong.id " +
            "LEFT JOIN ManHinh manHinh ON sp.manHinh.id = manHinh.id " +
            "LEFT JOIN Camera cam ON sp.camera.id = cam.id " +
            "WHERE " +
            "(:idHang = 'null' OR hang.ten = :idHang) " +
            "AND (:idRam = 'null' OR ram.dungLuong = :idRam) " +
            "AND (:idRom = 'null' OR rom.dungLuong = :idRom) " +
            "AND (:idPin = 'null' OR pin.loaiPin = :idPin) " +
            "AND (:idDLPin = 'null' OR dungLuong.thongSo = :idDLPin) " +
            "AND (:idChip = 'null' OR chip.ten = :idChip) " +
            "AND (:moTaMan = 'null' OR manHinh.thongSo = :moTaMan) " +
            "AND (:moTaCam = 'null' OR cam.thongSo = :moTaCam) " +
            "AND (:moTaMau = 'null' OR ms.ten = :moTaMau) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) " +
            "AND (ct.giaBan >= :tienMin AND ct.giaBan <= :tienMax)" +
            "AND (ct.tinhTrang=0) "
    )
    List<ChiTietSanPham> locbanhangcoGIATIEN(@Param("idHang") String idHang,
                                             @Param("moTaCam") String moTaCam,
                                             @Param("moTaMan") String moTaMan,
                                             @Param("moTaMau") String moTaMau,
                                             @Param("idRam") String idRam,
                                             @Param("idRom") String idRom,
                                             @Param("idPin") String idPin,
                                             @Param("idDLPin") String idDLPin,
                                             @Param("idChip") String idChip,
                                             @Param("tenSP") String tenSP,
                                             @Param("tienMin") BigDecimal tienMin,
                                             @Param("tienMax") BigDecimal tienMax);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.sanPham.id=:idsp ")
    List<ChiTietSanPham> ListctspTheoidsp(@Param("idsp") UUID idsp);

    @Query("select gh from  GioHang gh where gh.khachHang.id=:idkh ")
    List<GioHang> ListghTheoidkh(@Param("idkh") UUID idkh);


    @Transactional
    @Query(value = "DECLARE @bienb int;\n" +
            "set @bienb=(\n" +
            "select sum (so_luong) from gio_hang_chi_tiet \n" +
            "where id_gio_hang=:idgh and id_chi_tiet_san_pham=:idctsp \n" +
            ")\n" +
            "if @bienb is null\n" +
            "begin\n" +
            "set @bienb=0;\n" +
            "end\n" +
            "SELECT CAST(@bienb AS int); ", nativeQuery = true)
    Integer sl1ctsptronggh(@Param("idgh") UUID idgh, @Param("idctsp") UUID idctsp);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh ")
    List<GioHangChiTiet> ListghctTheoidgh(@Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.chiTietSanPham.id=:idctsp ")
    List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(@Param("idgh") UUID idgh, @Param("idctsp") UUID idctsp);

    @Query(value = "select sum(so_luong*don_gia_khi_giam) as tongtien,\n" +
            "COUNT(id) as tongsanphamchon  from\n" +
            "gio_hang_chi_tiet \n" +
            "where tinh_trang=0 and id_gio_hang=:idgh", nativeQuery = true)
    TongtienvsTongspchon TongtienvsTongspchon(@Param("idgh") UUID idgh);

    @Transactional
    @Modifying
    @Query(value = "update gio_hang_chi_tiet set tinh_trang=:trangthai where id_gio_hang=:idgh", nativeQuery = true)
    void trangthaighct(@Param("trangthai") Integer trangthai, @Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and  ghct.tinhTrang=0")
    List<GioHangChiTiet> ListghTheoidghvsTT1(@Param("idgh") UUID idgh);

    @Query("select dc from  DiaChi dc where dc.khachHang.id=:idkh and  dc.tinhTrang=0")
    List<DiaChi> Listdiachimotkhachang(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.ma=:mahd")
    HoaDon timhdtheomahd(@Param("mahd") String mahd);

    @Query("select im from  IMEI im where im.chiTietSanPham.id=:idctsp and im.tinhTrang=0")
    List<IMEI> timimeitheoidctspVSttO(@Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query("delete from GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.tinhTrang=0")
    void xoaghcttheoIDGHvsTTO(@Param("idgh") UUID idgh);

    @Transactional
    @Modifying
    @Query("delete from DonDatHang ghct where ghct.hoaDon.id=:idhd ")
    void xoaDDHtheoIDHD(@Param("idhd") UUID idhd);

    @Query("select hd from  HoaDon hd where hd.khachHang.id=:idkh")
    List<HoaDon> timhoadontheoidkh(@Param("idkh") UUID idkh);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.khachHang.id = :idkh")
    Page<HoaDon> cacDonHang(@Param("idkh") UUID idkh, Pageable pageable);

    @Query("select hd from  HoaDon hd where hd.tinhTrang=8 and hd.khachHang.id=:idkh")
    List<HoaDon> donHang8(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrang=1 and hd.khachHang.id=:idkh")
    List<HoaDon> donHang1(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrang=0 and hd.khachHang.id=:idkh")
    List<HoaDon> donHang0(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrang=2 and hd.khachHang.id=:idkh")
    List<HoaDon> donHang2(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrang=3 and hd.khachHang.id=:idkh")
    List<HoaDon> donHang3(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrangGiaoHang=0 and hd.khachHang.id=:idkh")
    List<HoaDon> donHangDangChoXuLy(@Param("idkh") UUID idkh);


    @Query("select hd from  HoaDon hd where hd.tinhTrangGiaoHang=2 and hd.khachHang.id=:idkh")
    List<HoaDon> donHangDangGiao(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.tinhTrangGiaoHang=3 and hd.khachHang.id=:idkh")
    List<HoaDon> donHangThanhCong(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=0 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH0(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=1 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH1(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=2 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) or hd.ngayThanhToan like %:ten% order by hd.ma DESC")
    List<HoaDon> searchDH2(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=8 and hd.khachHang.id=:idkh  and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH8(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=3 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH3(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten% or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten% or hd.ngayThanhToan like %:ten%) order by hd.ma DESC")
    List<HoaDon> search(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrangGiaoHang=0 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchChoXuLy(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrangGiaoHang=2 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDangGiao(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.hoaDon.id where hd.tinhTrang=3 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.sdt like %:ten%  or hdct.imei.chiTietSanPham.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDHThanhCong(@Param("idkh") UUID idkh, String ten);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd   ")
    List<HoaDonChiTiet> timhoadonchitiettheoidhd(@Param("idhd") UUID idhd);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp ")
    List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query(value = "update HoaDon  hd set  hd.tinhTrang=8 where  hd.id=:idhd")
    void huyhoadon(@Param("idhd") UUID idhd);

    @Transactional
    @Modifying
    @Query(value = "update HoaDonChiTiet  hd set  hd.tinhTrang=8 where  hd.hoaDon.id=:idhd")
    void huyhoadonchitiet(@Param("idhd") UUID idhd);

    @Transactional
    @Modifying
    @Query(value = "update IMEI  im set  im.tinhTrang=0 where  im.id in (select hdct.imei.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd)")
    void updateimeiTTveOtheoidhd(@Param("idhd") UUID idhd);


    @Transactional
    @Modifying
    @Query(value = "update IMEI  im set  im.tinhTrang=0 where  im.id in (select hdct.imei.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp)")
    void updateimeiTTveOtheoIDHDvsIDCTSP(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query(value = "delete from  HoaDonChiTiet hdct where hdct.id in (select hdct.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp)")
    void XoahdcttheoIDHDvsIDCTSP(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id" +
            " left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join Chip chip on ct.chip.id=chip.id where ct.tinhTrang=0  and" +
            " (sp.ten like %:ten% or hang.ten like %:ten% or ram.dungLuong like %:ten% or rom.dungLuong like %:ten% " +
            "or pin.loaiPin like %:ten% or ms.ten like %:ten% or chip.ten like %:ten%)")
    List<ChiTietSanPham> timkiemTrangChu(@Param("ten") String ten);


    @Query("select hd from  HoaDon hd where hd.loai=1 ")
    List<HoaDon> dsHDonlineloai1();


    @Query("select hd from  DonDatHang hd where hd.hoaDon.id=:idhd ")
    List<DonDatHang> dsDDHtheoIDHD(@Param("idhd") UUID idhd);


    @Transactional
    @Query(value = "SELECT SUM(sl1ctspTRONGhdctTHEOidhdVSisctsp) AS total_count\n" +
            "FROM (\n" +
            "    SELECT COUNT(a.id) AS sl1ctspTRONGhdctTHEOidhdVSisctsp\n" +
            "    FROM hoa_don_chi_tiet a\n" +
            "    JOIN imei b ON a.id_imei = b.id\n" +
            "    WHERE a.id_hoa_don = :idhd\n" +
            "      AND b.id_chi_tiet_san_pham = :idctsp\n" +
            "    GROUP BY a.id, a.id_hoa_don, b.id_chi_tiet_san_pham\n" +
            ") AS counts", nativeQuery = true)
    Integer sl1ctspTRONGhdctTHEOidhdVSisctsp(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Query("select hd from  IMEI hd where hd.soImei=:soimei ")
    IMEI timIMEItheosoImei(@Param("soimei") String soimei);


    @Query(value = "select a.id_chi_tiet_san_pham as idctsp,sum(a.so_luong) as slctsp" +
            " from don_dat_hang a group by a.id_chi_tiet_san_pham,a.tinh_trang" +
            " having tinh_trang=0", nativeQuery = true)
    List<DSfullSanPhamDatHangOnline> dsfullDDHcoTT0ladoixacnhan();


    @Transactional
    @Modifying
    @Query(value = "update  DonDatHang dh set  dh.tinhTrang=1 where dh.hoaDon.id=:idhd")
    void updateTTdonDatHangkhiDASULytheoIDHD(@Param("idhd") UUID idhd);


    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where ct.tinhTrang=1 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or dungLuong.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<ChiTietSanPham> locctspngungban(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);


    @Transactional
    @Modifying
    @Query(value = "update GioHangChiTiet ghct set ghct.tinhTrang=1 where ghct.id not in  (select ghct.id from GioHangChiTiet where ghct.id=:idghct)")
    void updatelaighctve1trumotIDGH(@Param("idghct") UUID idghct);


}
