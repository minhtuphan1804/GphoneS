package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.IMEI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IMEIRepository extends JpaRepository<IMEI, UUID> {
    @Query("select i from IMEI i left join ChiTietSanPham ct on i.chiTietSanPham.id=ct.id where ct.id=:id and i.tinhTrang=0")
    List<IMEI> getIMEI(UUID id);

    @Query("select i from IMEI i where  i.soImei=:soImei")
    IMEI getIMEISo(@Param("soImei") String soImei);

    @Query("select i from IMEI i where i.id=:id")
    List<IMEI> showIMEI(UUID id);

    @Query("select i from IMEI i where i.tinhTrang=1 or i.tinhTrang=3 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOff(String imei);

    @Query("select i from IMEI i where i.tinhTrang=2 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOff2(String imei);

    @Query("select i from IMEI i where i.tinhTrang=1")
    Page<IMEI> fill1(Pageable pageable);

    @Query("select i from IMEI i where i.tinhTrang=0 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOn(String imei);

    @Query("select i from IMEI i where i.tinhTrang=0 and i.chiTietSanPham.id=:idS and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIBanHang(String imei, UUID idS);

    @Query(value = "SELECT id FROM IMEI WHERE so_imei =:imei", nativeQuery = true)
    String searchSoImei2(@Param("imei") String imei);

    @Query(value = "SELECT * FROM IMEI WHERE so_imei =:imei and tinh_trang=0", nativeQuery = true)
    IMEI searchSoImei(@Param("imei") String imei);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 1,i.ngayCapNhat=:date where i.id=" +
            "(select hdct.imei.id from HoaDonChiTiet  hdct where hdct.id=:id)")
    void updateImei(Date date, UUID id);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 3,i.ngayCapNhat=:date where i.id=:id")
    void updateImeiChoXuLy(Date date, UUID id);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 0,i.ngayCapNhat=:date where i.id= " +
            "(select hdct.imei.id from HoaDonChiTiet  hdct where hdct.id=:id)")
    void updateImei1(Date date, UUID id);

    @Query("select imei from  IMEI imei where imei.tinhTrang=1 or imei.tinhTrang=3")
    List<IMEI> getImeiOfff();

    @Query("select imei from  IMEI imei where imei.tinhTrang=1 and imei.tinhTrang=3")
    List<IMEI> getImeiOff();


    @Query("select imei from  IMEI imei where imei.tinhTrang=0 ")
    List<IMEI> getImeiOn();

    @Query("select imei from  IMEI imei where imei.tinhTrang=4 ")
    List<IMEI> getImeiLoi();

    @Query("select imei from  IMEI imei where imei.tinhTrang=0 and imei.chiTietSanPham.id=:id ")
    List<IMEI> fct(UUID id);

    @Query("select i from IMEI i where i.tinhTrang=4 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchImeiLoi(String imei);

    @Query("select imei from  IMEI imei where imei.tinhTrang=0 ")
    List<IMEI> findAll0();


    @Query("select imei from  IMEI imei where imei.tinhTrang=2 ")
    List<IMEI> getImeiOff3();

    @Query("select imei from  IMEI imei where imei.tinhTrang=2 ")
    List<IMEI> findAll3();

    @Query("select imei from  IMEI imei where imei.chiTietSanPham.sanPham.id=:id and imei.tinhTrang=0")
    List<IMEI> statusSanPham(UUID id);

    @Query("select imei from  IMEI imei where imei.tinhTrang=0 and imei.chiTietSanPham.id=:id ")
    List<IMEI> statusCTSP(UUID id);

    boolean existsBySoImei(String imei);

    @Query("select imei from IMEI imei left join ChiTietSanPham ct on imei.chiTietSanPham.id=ct.id left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where imei.tinhTrang=0 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or ct.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<IMEI> locChuaBan(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    @Query("select imei from IMEI imei left join ChiTietSanPham ct on imei.chiTietSanPham.id=ct.id left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where (imei.tinhTrang=1 or imei.tinhTrang=3) and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or ct.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<IMEI> locDaBan(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    @Query("select imei from IMEI imei left join ChiTietSanPham ct on imei.chiTietSanPham.id=ct.id left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where imei.tinhTrang=2 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or ct.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<IMEI> locDaXoa(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    @Query("select imei from IMEI imei left join ChiTietSanPham ct on imei.chiTietSanPham.id=ct.id left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where imei.tinhTrang=4 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or ct.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<IMEI> locLoi(UUID idSanPham, UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

}
