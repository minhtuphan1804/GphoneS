package com.example.demo.repositories;

import com.example.demo.models.KhachHang;
import com.example.demo.viewmodels.KhachHangHoaDon;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {


    @Query("select kh from KhachHang kh  where kh.tinhTrang=0")
    Page<KhachHang> getall0(Pageable pageable);

    @Query("select kh from KhachHang kh  where kh.tinhTrang=1")
    Page<KhachHang> getall1(Pageable pageable);


    @Query("select kh from KhachHang kh  where kh.tinhTrang=0")
    List<KhachHang> getall00();

    @Query("select kh from KhachHang kh  where kh.tinhTrang=1")
    List<KhachHang> getall11();

    @Query(value = "select a.ho_ten as 'tenkh',b.ma as 'mahd'," +
            "c.ho_ten as 'tennv',b.ngay_nhan as 'ngaynhan',b.tong_tien as 'tongtien'," +
            "count(d.id) as 'tongsp',b.tinh_trang as'tinhtrang' " +
            "from\n" +
            "khach_hang a left join hoa_don b\n" +
            "on a.id=b.id_khach_hang\n" +
            "left join nhan_vien c\n" +
            "on c.id=b.id_nhan_vien\n" +
            "left join hoa_don_chi_tiet d\n" +
            "on d.id_hoa_don=b.id\n" +
            "group by \n" +
            "a.id,a.ma,a.ho_ten,b.ma,c.ho_ten,b.ngay_nhan,b.tong_tien,b.tinh_trang\n" +
            "having a.id=:idkh", nativeQuery = true)
    List<KhachHangLSMuaHang> getLSMuaHang(@Param("idkh") UUID idkh);


    @Query(value = "select a.ho_ten as 'tenkh',b.ma as 'mahd'," +
            "c.ho_ten as 'tennv',b.ngay_nhan as 'ngaynhan',b.tong_tien as 'tongtien'," +
            "count(d.id) as 'tongsp',b.tinh_trang as'tinhtrang' " +
            "from\n" +
            "khach_hang a left join hoa_don b\n" +
            "on a.id=b.id_khach_hang\n" +
            "left join nhan_vien c\n" +
            "on c.id=b.id_nhan_vien\n" +
            "left join hoa_don_chi_tiet d\n" +
            "on d.id_hoa_don=b.id\n" +
            "group by \n" +
            "a.id,a.ma,a.ho_ten,b.ma,c.ho_ten,b.ngay_nhan,b.tong_tien,b.tinh_trang\n" +
            "having a.id=:idkh", nativeQuery = true)
    Page<KhachHangLSMuaHang> getLSMuaHangPage(Pageable pageable, @Param("idkh") UUID idkh);


    @Query(value = "select a.ma as 'mahd',\n" +
            "c.so_imei as 'soimei',\n" +
            "n.ten as 'tensp',\n" +
            "z.ten+','+o.thong_so+','+x.do_phan_giai+','+e.ten+','+f.dung_luong+','+g.dung_luong+','+h.loai_pin+' '+i.thong_so as 'thongtin' ,\n" +
            "b.so_luong as 'soluong',\n" +
            "b.don_gia as 'dongia',\n" +
            "y.so_tien_quy_doi as 'tienquydoi'\n" +
            "from\n" +
            "hoa_don a left join hoa_don_chi_tiet b\n" +
            "on a.id=b.id_hoa_don\n" +
            "left join imei c\n" +
            "on c.id=b.id_imei\n" +
            "left join chi_tiet_san_pham d\n" +
            "on c.id_chi_tiet_san_pham=d.id\n" +
            "left join chip e\n" +
            "on e.id=d.id_chip\n" +
            "left join rom f\n" +
            "on f.id=d.id_rom\n" +
            "left join ram g\n" +
            "on g.id=d.id_ram\n" +
            "left join pin h\n" +
            "on h.id=d.id_pin\n" +
            "left join dung_luong_pin i\n" +
            "on i.id=h.id_dung_luong\n" +
            "left join mau_sac k\n" +
            "on k.id=d.id_mau_sac\n" +
            "left join san_pham n\n" +
            "on k.id=d.id_san_pham\n" +
            "left join anh m\n" +
            "on m.id=n.id_anh\n" +
            "left join camera o\n" +
            "on o.id=n.id_camera\n" +
            "left join hang_dien_thoai z\n" +
            "on z.id=n.id_hang\n" +
            "left join man_hinh x\n" +
            "on x.id=n.id_man_hinh\n" +
            "left join quy_doi y\n" +
            "on y.id=a.id_quy_doi\n"
            , nativeQuery = true)
    List<KhachHangHoaDon> getHD();


    @Query("select kh from KhachHang kh  where kh.tinhTrang=0 and (kh.ma like %:timkiem% or kh.hoTen like %:timkiem% or kh.hangKhachHang.ten like %:timkiem% or kh.email like %:timkiem%)")
    List<KhachHang> timkiem(String timkiem);

    @Query("select kh from KhachHang kh  where  kh.tinhTrang=1 and (kh.ma like %:timkiem% or kh.hoTen like %:timkiem% or kh.hangKhachHang.ten like %:timkiem% or kh.email like %:timkiem%)")
    List<KhachHang> timkiem1(String timkiem);

    @Query("select kh from KhachHang kh left join HoaDon hd on kh.id=hd.khachHang.id where hd.id=:id")
    List<KhachHang> khachHangThanhToan(UUID id);

    @Query("select kh from KhachHang kh left join HoaDon hd on kh.id=hd.khachHang.id where hd.id=:id")
    KhachHang newKhachHang(UUID id);



    @Query("select kh from KhachHang  kh where kh.tinhTrang=0 and (kh.taiKhoan like %:username% and kh.email like %:email%)")
    KhachHang quenMatKhau(String username, String email);
    @Query("select kh from KhachHang  kh where kh.tinhTrang=0 and (kh.taiKhoan like %:username% or kh.email like %:username%)")
    Optional<KhachHang> getKhachHangByTaiKhoan(String username);


}
