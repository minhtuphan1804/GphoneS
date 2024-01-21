package com.example.demo.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public interface DTODoiTra {
    UUID getId();
    String getMa();
    Date getNgayThanhToan();
    Date getNgayTao();
    Date getNgayNhan();
    String getHoTenKhachHang();
    String getHoTenNhanVien();
    String getDiaChi();
    String getQuan();
    String getHuyen();
    String getThanhPho();
    String getSDT();
    BigDecimal getTongTien();
    Integer getHinhThuc();
}
