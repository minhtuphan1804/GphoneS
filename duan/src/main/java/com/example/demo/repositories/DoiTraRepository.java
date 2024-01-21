package com.example.demo.repositories;

import com.example.demo.DTO.DTODoiTra;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DoiTra;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoiTraRepository extends JpaRepository<DoiTra, UUID> {
    @Query("SELECT d from DoiTra d where d.tinhTrang=0")
    List<DoiTra> fillAll0();

    @Query("SELECT d from DoiTra d where d.tinhTrang=1")
    List<DoiTra> fillAll1();

    @Query("SELECT d from DoiTra d where d.id=:id")
    List<DoiTra> findId(UUID id);

    @Query("SELECT d from DoiTra d where d.tinhTrang=2")
    List<DoiTra> fillAll2();

    @Query("SELECT d from HoaDon d where d.tinhTrang=2")
    Page<HoaDon> fillAllHoaDon(Pageable pageable);

    @Query(value = "SELECT d.id as id,d.ma as ma,d.ngay_tao as ngayTao,d.ngay_thanh_toan as ngayThanhToan,d.ngay_nhan as ngayNhan" +
            ",kh.ho_ten as hoTenKhachHang,nv.ho_ten as hotenNhanVien,dc.dia_chi as diaChi,dc.quan as quan,dc.huyen as huyen,dc.thanh_pho as thanhPho," +
            "d.sdt as SDT,d.tong_tien as tongTien,d.hinh_thuc_thanh_toan as hinhThuc FROM hoa_don d " +
            "left join khach_hang kh on d.id_khach_hang = kh.id left join nhan_vien nv on d.id_nhan_vien= nv.id " +
            "left join dia_chi dc on dc.id=d.id_dia_chi WHERE d.tinh_trang = 2 AND d.tinh_trang_giao_hang = 3 " +
            "AND NOT EXISTS (SELECT 1 FROM doi_tra dt WHERE dt.id_hoa_don = d.id) " +
            "AND DATEDIFF(DAYOFYEAR,ngay_nhan ,GETDATE() ) <= 7", nativeQuery = true)
    List<DTODoiTra> getAllHD();

    @Query("SELECT d from DoiTra d where d.tinhTrang=0")
    List<DoiTra> getAll0();

    @Query("SELECT d from DoiTra d where d.tinhTrang=1")
    List<DoiTra> getAll1();

    @Query("SELECT d from DoiTra d where d.tinhTrang=2")
    List<DoiTra> getAll2();

    @Query("select hdct from HoaDonChiTiet hdct left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id")
    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);

    @Query("select hdct from DoiTra hdct left join HoaDon hd on hdct.hoaDon.id=hd.id where hd.id=:id")
    DoiTra getDoiTraByHoaDon(UUID id);

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
            "AND (ct.tinhTrang = 0 AND sp.tinhTrang = 0)")
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

    @Query("select hd from DoiTra hd " +
            " where hd.tinhTrang=0 and (:idKH IS NULL OR hd.khachHang.id=:idKH)and (:idNV IS NULL OR hd.nhanVien.id=:idNV) " +
            "AND ((:startDate IS NULL OR :endDate IS NULL) OR hd.ngayDoiTra >= COALESCE(:startDate, hd.ngayDoiTra) " +
            "and hd.ngayDoiTra <= COALESCE(:endDate, hd.ngayDoiTra))" +
            "AND ((:ngayTao0 IS NULL OR :ngayTao1 IS NULL) OR hd.ngayTao >= COALESCE(:ngayTao0, hd.ngayTao))")
    List<DoiTra> locDoiTraTrangThai0(
            UUID idKH, UUID idNV,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "ngayTao0", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao0,
            @RequestParam(value = "ngayTao1", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao1);

    @Query("select hd from DoiTra hd " +
            " where hd.tinhTrang=1 and (:idKH IS NULL OR hd.khachHang.id=:idKH)and (:idNV IS NULL OR hd.nhanVien.id=:idNV) " +
            "AND ((:startDate IS NULL OR :endDate IS NULL) OR hd.ngayDoiTra >= COALESCE(:startDate, hd.ngayDoiTra) " +
            "and hd.ngayDoiTra <= COALESCE(:endDate, hd.ngayDoiTra))" +
            "AND ((:ngayTao0 IS NULL OR :ngayTao1 IS NULL) OR hd.ngayTao >= COALESCE(:ngayTao0, hd.ngayTao))")
    List<DoiTra> locDoiTraTrangThai1(
            UUID idKH, UUID idNV,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "ngayTao0", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao0,
            @RequestParam(value = "ngayTao1", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao1);

    @Query("select hd from DoiTra hd " +
            " where hd.tinhTrang=2 and (:idKH IS NULL OR hd.khachHang.id=:idKH)and (:idNV IS NULL OR hd.nhanVien.id=:idNV) " +
            "AND ((:startDate IS NULL OR :endDate IS NULL) OR hd.ngayDoiTra >= COALESCE(:startDate, hd.ngayDoiTra) " +
            "and hd.ngayDoiTra <= COALESCE(:endDate, hd.ngayDoiTra))" +
            "AND ((:ngayTao0 IS NULL OR :ngayTao1 IS NULL) OR hd.ngayTao >= COALESCE(:ngayTao0, hd.ngayTao))")
    List<DoiTra> locDoiTraTrangThai2(
            UUID idKH, UUID idNV,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "ngayTao0", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao0,
            @RequestParam(value = "ngayTao1", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao1);


    @Query("select dt from DoiTra dt where dt.tinhTrang=0 and dt.ma like %:search% " +
            "or dt.hoaDon.ma like %:search% " +
            "or dt.khachHang.hoTen like %:search% " +
            "or dt.nhanVien.hoTen like %:search% " +
            "or dt.hoaDon.sdt like %:search%")
    List<DoiTra> searchDoiTraTrangThai0(String search);

    @Query("select dt from DoiTra dt where dt.tinhTrang=1 and dt.ma like %:search% " +
            "or dt.hoaDon.ma like %:search% " +
            "or dt.khachHang.hoTen like %:search% " +
            "or dt.nhanVien.hoTen like %:search% " +
            "or dt.hoaDon.sdt like %:search%")
    List<DoiTra> searchDoiTraTrangThai1(String search);

    @Query("select dt from DoiTra dt where dt.tinhTrang=2 and dt.ma like %:search% " +
            "or dt.hoaDon.ma like %:search% " +
            "or dt.khachHang.hoTen like %:search% " +
            "or dt.nhanVien.hoTen like %:search% " +
            "or dt.hoaDon.sdt like %:search%")
    List<DoiTra> searchDoiTraTrangThai2(String search);
}
