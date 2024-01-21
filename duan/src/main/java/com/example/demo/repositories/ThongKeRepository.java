package com.example.demo.repositories;

import com.example.demo.DTO.DoanhThuHang;
import com.example.demo.DTO.DoanhThuKhachHang;
import com.example.demo.DTO.DoanhThuNhanVien;
import com.example.demo.DTO.DoanhThuSanPham;
import com.example.demo.DTO.DoanhThuTheoThang;
import com.example.demo.DTO.SoLuongDoiTraHang;
import com.example.demo.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ThongKeRepository extends JpaRepository<HoaDon, UUID> {

    @Query(value = "select count(h) from HoaDon h where  h.tinhTrang = 2")
    int countHD();

    @Query(value = "select avg (h.tongTien) from HoaDon h  where h.tinhTrang = 2")
    int avgHD();

    @Query(value = "SELECT SUM(tong_tien) AS doanh_thu, MONTH(ngay_thanh_toan) AS thang FROM " +
            "hoa_don WHERE YEAR(ngay_thanh_toan) = ? GROUP BY thang", nativeQuery = true)
    List<DoanhThuTheoThang> getDoanhThusInYear(int year);

    @Query(value = "Select MONTH(hoa_don.ngay_thanh_toan) AS thang,\n" +
            "COUNT(hoa_don_chi_tiet.so_luong) as soLuongSP,\n" +
            "COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) AS TienDoiTra,\n" +
            "SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThuThucTe,  \n" +
            "SUM(chi_tiet_san_pham.gia_ban) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0) AS DoanhThuChuaKhuyenMai\n" +
            "FROM hoa_don left join hoa_don_chi_tiet on  hoa_don_chi_tiet.id_hoa_don = hoa_don.id \t\t\t\n" +
            "left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "where YEAR(ngay_thanh_toan) = 2023 and hoa_don.tinh_trang = 2\n" +
            "GROUP BY MONTH(ngay_thanh_toan)\n" +
            "                                    ORDER BY thang ASC", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThu();

    @Query(value = " SELECT DAY(hoa_don.ngay_thanh_toan) AS ngay,\n" +
            " MONTH(hoa_don.ngay_thanh_toan) AS thang,\n" +
            " YEAR(hoa_don.ngay_thanh_toan) AS nam,\n" +
            " COUNT(hoa_don_chi_tiet.so_luong) as soLuongSP,\n" +
            " COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) AS TienDoiTra,\n" +
            " SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0) AS DoanhThuThucTe, \n" +
            " SUM(chi_tiet_san_pham.gia_ban) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0) AS DoanhThuChuaKhuyenMai\n" +
            "FROM hoa_don left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id \n" +
            "left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "where YEAR(ngay_thanh_toan) = 2024 and hoa_don.tinh_trang = 2\n" +
            "and DAY(hoa_don.ngay_thanh_toan) = DAY(GETDATE())\n" +
            "GROUP BY DAY(ngay_thanh_toan),\n" +
            " MONTH(hoa_don.ngay_thanh_toan),\n" +
            " YEAR(hoa_don.ngay_thanh_toan) ", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThuNgay();

    @Query(value = "Select MONTH(hoa_don.ngay_thanh_toan) AS thang,\n" +
            "COUNT(hoa_don_chi_tiet.so_luong) as soLuongSP,\n" +
            "COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) AS TienDoiTra,\n" +
            "SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThuThucTe,  \n" +
            "SUM(chi_tiet_san_pham.gia_ban) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0) AS DoanhThuChuaKhuyenMai\n" +
            "FROM hoa_don left join hoa_don_chi_tiet on  hoa_don_chi_tiet.id_hoa_don = hoa_don.id \t\t\t\n" +
            "left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "where YEAR(ngay_thanh_toan) = ?1  and hoa_don.tinh_trang = 2\n" +
            "GROUP BY MONTH(ngay_thanh_toan)\n" +
            "ORDER BY thang ASC", nativeQuery = true)
    List<DoanhThuTheoThang> loctheonam(Integer Nam);

    @Query(value = "SELECT YEAR(ngay_thanh_toan) AS Nam FROM hoa_don GROUP BY YEAR(ngay_thanh_toan)", nativeQuery = true)
    List<DoanhThuTheoThang> selectedYear();


    @Query(value = "select san_pham.ten as tenSanPham,\n" +
            "            chi_tiet_san_pham.so_luong_ton as soLuongTon,\n" +
            "            COUNT(imei.id) AS soLuongSP,\n" +
            "            COALESCE(SUM(hoa_don_chi_tiet.don_gia) + COALESCE(SUM(doi_tra_chi_tiet.don_gia),0), SUM(doi_tra_chi_tiet.don_gia)) AS doanhThu\n" +
            "            from imei\n" +
            "            LEFT JOIN hoa_don_chi_tiet on hoa_don_chi_tiet.id_imei = imei.id\n" +
            "            LEFT JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "            LEFT JOIN chi_tiet_san_pham ON imei.id_chi_tiet_san_pham = chi_tiet_san_pham.id\n" +
            "            LEFT JOIN san_pham ON chi_tiet_san_pham.id_san_pham = san_pham.id\n" +
            "            LEFT JOIN doi_tra_chi_tiet on doi_tra_chi_tiet.id_imei = imei.id\n" +
            "            where imei.tinh_trang = 1 \n" +
            "            GROUP BY san_pham.ten, chi_tiet_san_pham.so_luong_ton", nativeQuery = true)
    List<DoanhThuSanPham> doanhThuSanPham();

    @Query(value = "select san_pham.ten as tenSanPham,\n" +
            "            chi_tiet_san_pham.so_luong_ton as soLuongTon,\n" +
            "            COUNT(imei.id) AS soLuongSP,\n" +
            "            COALESCE(SUM(hoa_don_chi_tiet.don_gia) + COALESCE(SUM(doi_tra_chi_tiet.don_gia),0), SUM(doi_tra_chi_tiet.don_gia)) AS doanhThu\n" +
            "            from imei\n" +
            "            LEFT JOIN hoa_don_chi_tiet on hoa_don_chi_tiet.id_imei = imei.id\n" +
            "            LEFT JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "            LEFT JOIN chi_tiet_san_pham ON imei.id_chi_tiet_san_pham = chi_tiet_san_pham.id\n" +
            "            LEFT JOIN san_pham ON chi_tiet_san_pham.id_san_pham = san_pham.id\n" +
            "            LEFT JOIN hang_dien_thoai ON  hang_dien_thoai.id = san_pham.id_hang\n" +
            "            LEFT JOIN doi_tra_chi_tiet on doi_tra_chi_tiet.id_imei = imei.id\n" +
            "            where imei.tinh_trang = 1 and hang_dien_thoai.ten like %:ten%\n" +
            "            GROUP BY san_pham.ten, chi_tiet_san_pham.so_luong_ton", nativeQuery = true)
    List<DoanhThuSanPham> locHang(String ten);

    @Query(value = "select hang_dien_thoai.ten as tenHang\n" +
            "from imei\n" +
            "LEFT JOIN hoa_don_chi_tiet on hoa_don_chi_tiet.id_imei = imei.id\n" +
            "LEFT JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "LEFT JOIN chi_tiet_san_pham ON imei.id_chi_tiet_san_pham = chi_tiet_san_pham.id \n" +
            "LEFT JOIN san_pham ON chi_tiet_san_pham.id_san_pham = san_pham.id\n" +
            "LEFT JOIN hang_dien_thoai ON  hang_dien_thoai.id = san_pham.id_hang\n" +
            "GROUP BY hang_dien_thoai.ten", nativeQuery = true)
    List<DoanhThuSanPham> selectedHang();

    @Query(value = " select hang_dien_thoai.ten AS tenHang,\n" +
            "            COALESCE(SUM(hoa_don_chi_tiet.don_gia),0) as doanhThuCu,\n" +
            "            COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra),0) as tienDoiTra,\n" +
            "            COALESCE(SUM(hoa_don_chi_tiet.don_gia) -  COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra) - COALESCE(SUM(phi_ship), 0),0),0) AS doanhThuThucTe\n" +
            "            from hoa_don \n" +
            "            left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "            left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "            left join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "            left join hang_dien_thoai on hang_dien_thoai.id = san_pham.id_hang\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            GROUP BY hang_dien_thoai.ten\n", nativeQuery = true)
    List<DoanhThuHang> doanhThuHang();

    @Query(value = " select hang_dien_thoai.ten AS tenHang,\n" +
            "            COALESCE(SUM(hoa_don_chi_tiet.don_gia),0) as doanhThuCu,\n" +
            "            COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra),0) as tienDoiTra,\n" +
            "            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThuThucTe  \n" +
            "            from hoa_don \n" +
            "            left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "            left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "            left join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "            left join hang_dien_thoai on hang_dien_thoai.id = san_pham.id_hang\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            WHERE hoa_don.ngay_thanh_toan BETWEEN :startDate AND :endDate\n" +
            "            GROUP BY hang_dien_thoai.ten", nativeQuery = true)
    List<DoanhThuHang> locdoanhThuHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT nhan_vien.ho_ten AS tenNhanVien,\n" +
            "            COUNT(hoa_don_chi_tiet.so_luong) as soLuongSP,\n" +
            "            COALESCE(SUM(phi_ship), 0) as PhiShip,\n" +
            "            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThu,\n" +
            "            SUM(doi_tra_chi_tiet.tien_doi_tra) AS TienDoiTra,\n" +
            "            SUM(tong_tien) AS DoanhThuCu\n" +
            "            FROM hoa_don left join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id " +
            "            left join hoa_don_chi_tiet on hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +

            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id" +
            "            where hoa_don.tinh_trang = 2 and hoa_don.tinh_trang_giao_hang = 3" +
            "            GROUP BY nhan_vien.ho_ten", nativeQuery = true)
    List<DoanhThuNhanVien> doanhThuNhanVien();

    @Query(value = "SELECT nhan_vien.ho_ten AS tenNhanVien,\n" +
            "            COUNT(hoa_don_chi_tiet.so_luong) as soLuongSP,\n" +
            "            COALESCE(SUM(phi_ship), 0) as PhiShip,\n" +
            "            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThu,\n" +
            "            SUM(doi_tra_chi_tiet.tien_doi_tra) AS TienDoiTra,\n" +
            "            SUM(tong_tien) AS DoanhThuCu\n" +
            "            FROM hoa_don left join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id " +
            "            left join hoa_don_chi_tiet on hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id" +
            "            where hoa_don.tinh_trang = 2 and hoa_don.tinh_trang_giao_hang = 3" +
            "            AND ngay_thanh_toan BETWEEN :startDate AND :endDate\n" +
            "            GROUP BY nhan_vien.ho_ten", nativeQuery = true)
    List<DoanhThuNhanVien> locDoanhThuNhanVien(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "         SELECT CASE WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 25 THEN N'Dưới 25'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 25 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 35 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 45 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                ELSE N'Trên 55 tuổi'\n" +
            "                END AS tuoi,\n" +
            "                COALESCE(SUM(hoa_don_chi_tiet.don_gia) -  COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra),0),0) AS DoanhThu\n" +
            "                FROM imei\n" +
            "                LEFT JOIN hoa_don_chi_tiet on hoa_don_chi_tiet.id_imei = imei.id\n" +
            "                LEFT JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "                LEFT JOIN chi_tiet_san_pham ON imei.id_chi_tiet_san_pham = chi_tiet_san_pham.id\n" +
            "                LEFT JOIN san_pham ON chi_tiet_san_pham.id_san_pham = san_pham.id\n" +
            "                LEFT JOIN khach_hang ON hoa_don.id_khach_hang = khach_hang.id\n" +
            "                left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "                GROUP BY CASE WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 25 THEN N'Dưới 25'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 25 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 35 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 45 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                ELSE N'Trên 55 tuổi'\n" +
            "                END", nativeQuery = true)
    List<DoanhThuKhachHang> doanhThuKhachHang();

    @Query(value = "SELECT khach_hang.gioi_tinh as gioiTinh,\n" +
            "            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThu\n" +
            "            FROM hoa_don\n" +
            "            left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            LEFT JOIN khach_hang ON hoa_don.id_khach_hang = khach_hang.id\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            WHERE hoa_don.tinh_trang = 2 and hoa_don.tinh_trang_giao_hang = 3\n" +
            "            Group by khach_hang.gioi_tinh", nativeQuery = true)
    List<DoanhThuKhachHang> doanhThuKhachHangGioiTinh();

    @Query(value = "       SELECT CASE WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 25 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 35 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 45 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END AS tuoi,\n" +
            "                            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThu\n" +
            "                            FROM imei\n" +
            "                            LEFT JOIN hoa_don_chi_tiet on hoa_don_chi_tiet.id_imei = imei.id\n" +
            "                            LEFT JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            "                            LEFT JOIN chi_tiet_san_pham ON imei.id_chi_tiet_san_pham = chi_tiet_san_pham.id\n" +
            "                            LEFT JOIN san_pham ON chi_tiet_san_pham.id_san_pham = san_pham.id\n" +
            "                            LEFT JOIN khach_hang ON hoa_don.id_khach_hang = khach_hang.id\n" +
            "                            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "                            where hoa_don.ngay_thanh_toan BETWEEN :startDate AND :endDate   " +
            "                            GROUP BY CASE WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 25 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 35 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) >= 45 AND YEAR(GETDATE()) - YEAR(khach_hang.ngay_sinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END", nativeQuery = true)
    List<DoanhThuKhachHang> locDoanhThuKhachHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT khach_hang.gioi_tinh as gioiTinh,\n" +
            "            SUM(hoa_don_chi_tiet.don_gia) - COALESCE(SUM(doi_tra_chi_tiet.tien_doi_tra), 0) - COALESCE(SUM(phi_ship), 0)   AS DoanhThu\n" +
            "            FROM hoa_don\n" +
            "            left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            LEFT JOIN khach_hang ON hoa_don.id_khach_hang = khach_hang.id\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            WHERE hoa_don.tinh_trang = 2 and hoa_don.tinh_trang_giao_hang = 3" +
            "            and hoa_don.ngay_thanh_toan BETWEEN :startDate AND :endDate\n" +
            "            Group by khach_hang.gioi_tinh", nativeQuery = true)
    List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select hang_dien_thoai.ten AS tenHang,\n" +
            "            COUNT(hoa_don_chi_tiet.id_imei) as soLuong\n" +
            "            from hoa_don \n" +
            "            left join hoa_don_chi_tiet on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            left join imei on imei.id = hoa_don_chi_tiet.id_imei\n" +
            "            left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "            left join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "            left join hang_dien_thoai on hang_dien_thoai.id = san_pham.id_hang\n" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            where doi_tra_chi_tiet.hien_trang_san_pham = 1" +
            "            GROUP BY hang_dien_thoai.ten", nativeQuery = true)
    List<SoLuongDoiTraHang> SoLuongDoiTraHang();

    @Query(value = "select hang_dien_thoai.ten AS tenHang,\n" +
            "            COUNT(imei.id) as soLuong\n" +
            "            from imei \n" +
            "            left join hoa_don_chi_tiet on imei.id = hoa_don_chi_tiet.id_imei \n" +
            "            left join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "            left join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "            left join hang_dien_thoai on hang_dien_thoai.id = san_pham.id_hang\n" +
            "            left join doi_tra on doi_tra.id_hoa_don = hoa_don.id" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            where imei.tinh_trang = 0" +
            "            and ngay_doi_tra BETWEEN :startDate AND :endDate\n" +
            "            GROUP BY hang_dien_thoai.ten", nativeQuery = true)
    List<SoLuongDoiTraHang> locSoLuongDoiTraHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select hang_dien_thoai.ten AS tenHang,\n" +
            "            COUNT(imei.id) as soLuong\n" +
            "            from imei \n" +
            "            left join hoa_don_chi_tiet on imei.id = hoa_don_chi_tiet.id_imei \n" +
            "            left join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id\n" +
            "            left join chi_tiet_san_pham on chi_tiet_san_pham.id = imei.id_chi_tiet_san_pham\n" +
            "            left join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "            left join hang_dien_thoai on hang_dien_thoai.id = san_pham.id_hang\n" +
            "            left join doi_tra on doi_tra.id_hoa_don = hoa_don.id" +
            "            left join doi_tra_chi_tiet on doi_tra_chi_tiet.id_hoa_don_chi_tiet = hoa_don_chi_tiet.id\n" +
            "            where imei.tinh_trang = 4" +
            "            and ngay_doi_tra BETWEEN :startDate AND :endDate\n" +
            "            GROUP BY hang_dien_thoai.ten", nativeQuery = true)
    List<SoLuongDoiTraHang> locSoLuongSanPhamLoi(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}