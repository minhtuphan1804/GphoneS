package com.example.demo.repositories;

import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {

    @Query("select nv from NhanVien nv  where nv.tinhTrang=0")
    List<NhanVien> findAll();

    @Query("select nv from NhanVien nv  where nv.tinhTrang=0")
    List<NhanVien> getAll();

    @Query("select nv from NhanVien nv  where nv.tinhTrang=1")
    List<NhanVien> getAll1();

    @Query("select nv from NhanVien nv INNER JOIN nv.chucVu cv  where nv.tinhTrang=0 and (cv.ten like %:ten% or nv.ma like %:ten% or nv.hoTen like %:ten% or nv.queQuan like %:ten% or nv.taiKhoan like %:ten% " +
            "or nv.sdt like %:ten% or nv.canCuoc like %:ten% or nv.email like %:ten%)")
    List<NhanVien> search0(String ten);

    @Query("select nv from NhanVien nv  where nv.tinhTrang=1 and (nv.ma like %:ten% or nv.hoTen like %:ten% or nv.queQuan like %:ten% or nv.taiKhoan like %:ten% " +
            "or nv.sdt like %:ten% or nv.canCuoc like %:ten% or nv.email like %:ten%)")
    List<NhanVien> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update  nhan_vien set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();


    @Query("SELECT nv FROM NhanVien nv " +
            "INNER JOIN nv.chucVu cv " +
            "WHERE  cv.ten = :tenChucVu " +
            "AND  nv.gioiTinh = :gioiTinh")
    List<NhanVien> searchByTenChucVuAndGioiTinh(String tenChucVu,
                                                Boolean gioiTinh);

    @Query("select kh from NhanVien kh left join HoaDon hd on kh.id=hd.nhanVien.id where hd.id=:id")
    List<NhanVien> nhanVienThanhToan(UUID id);

    @Query("select kh from NhanVien kh left join HoaDon hd on kh.id=hd.nhanVien.id where hd.id=:id")
    NhanVien nhanVienUpdateHoaDon(UUID id);

    //    @Query("SELECT nv.taiKhoan, nv.matKhau, kh.taiKhoan AS Expr1, kh.matKhau AS Expr2\n" +
//            "FROM     NhanVien nv INNER JOIN\n" +
//            "                  KhachHang  kh ON nv.id = kh.id")
//    List<NhanVien> getAllTaiKhoanMatKhau();
    @Query("select nv from NhanVien  nv where nv.tinhTrang=0 and (nv.taiKhoan like %:username% or nv.email like %:username%)")
    Optional<NhanVien> findByTaiKhoan(String username);


}
