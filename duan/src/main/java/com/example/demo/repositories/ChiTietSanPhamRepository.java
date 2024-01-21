package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id" +
            " left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join Chip chip on ct.chip.id=chip.id where ct.tinhTrang=0 and ( sp.ten like %:ten% or hang.ten like %:ten% or ram.dungLuong like %:ten% or rom.dungLuong like %:ten% " +
            "or pin.loaiPin like %:ten% or ms.ten like %:ten% or chip.ten like %:ten%)")
    List<ChiTietSanPham> search(String ten);

    @Query("select ct from ChiTietSanPham  ct where ct.tinhTrang=0 and ct.giaBan = " +
            "(select hdct.donGia from HoaDonChiTiet hdct left join DoiTraChiTiet dtct on hdct.id=dtct.hoaDonChiTiet.id where dtct.id=:id)")
    List<ChiTietSanPham> searchGia(UUID id);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id" +
            " left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join Chip chip on ct.chip.id=chip.id where ct.tinhTrang=1 and" +
            " (sp.ten like %:ten% or hang.ten like %:ten% or ram.dungLuong like %:ten% or rom.dungLuong like %:ten% " +
            "or pin.loaiPin like %:ten% or ms.ten like %:ten% or chip.ten like %:ten%)")
    List<ChiTietSanPham> search1(String ten);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where ct.tinhTrang=0 and" +
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
    List<ChiTietSanPham> loc(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where ct.tinhTrang=0 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or dungLuong.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) " +
            "and (:giaBanMin is null and :giaBanMax is null " +
            "or ct.giaBan between :giaBanMin and ct.giaBan or ct.giaBan between 0 and :giaBanMax or ct.giaBan between :giaBanMin and :giaBanMax))"
    )
    List<ChiTietSanPham> locBanHang(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam, BigDecimal giaBanMin, BigDecimal giaBanMax);

    @Query("select ct from ChiTietSanPham ct left join IMEI  i on ct.id=i.chiTietSanPham.id where i.id=:id")
    ChiTietSanPham getChiTiet(UUID id);

    @Query("select ct from ChiTietSanPham ct left join IMEI  i on ct.id=i.chiTietSanPham.id left join HoaDonChiTiet hd on i.id=hd.imei.id where i.id=:id")
    ChiTietSanPham getChiTiet2(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 ")
    List<ChiTietSanPham> getChiTietOn();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=1 ")
    List<ChiTietSanPham> getChiTietOff();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=1 ")
    List<ChiTietSanPham> getChiTietSanPhamOff();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 ")
    List<ChiTietSanPham> findAll0();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.hangSanPham.id=:id")
    List<ChiTietSanPham> findAllHang(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.manHinh.id=:id")
    List<ChiTietSanPham> findAllMan(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.chip.id=:id")
    List<ChiTietSanPham> findAllChip(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.pin.dungLuongPin.id=:id")
    List<ChiTietSanPham> findAllPin(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.rom.id=:id")
    List<ChiTietSanPham> findAllRom(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.ram.id=:id")
    List<ChiTietSanPham> findAllRam(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.camera.id=:id")
    List<ChiTietSanPham> findAllCam(UUID id);


    @Query("SELECT SUM(ctsp.soLuong) FROM ChiTietSanPham ctsp")
    Integer tongSanPham();


    @Query("SELECT COUNT(imei) FROM IMEI imei where imei.tinhTrang=1")
    Integer tongSPDaBan();

    @Query("SELECT COUNT(hd) FROM HoaDon hd")
    Integer cacDonHang();

    @Query("SELECT COUNT(hd) FROM HoaDon hd where hd.tinhTrangGiaoHang=0")
    Integer cacDonHangChoXuLy();

    @Query("SELECT COUNT(kh) FROM KhachHang kh WHERE kh.taiKhoan IS NOT NULL and kh.tinhTrang=0")
    Integer soKhachHang();

    @Query("SELECT COUNT(nv) FROM NhanVien nv WHERE nv.taiKhoan IS NOT NULL and nv.tinhTrang=0")
    Integer soNV();

    boolean existsBySanPhamAndChipAndRomAndRamAndPinAndMauSac(SanPham sanPham, Chip chip, Rom rom, Ram ram, Pin pin, MauSac mauSac);
}
