package com.example.demo.services;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {
    public Page<HoaDon> getAll(Pageable pageable);

    public List<HoaDon> donHang();

    public List<HoaDon> hoaDon();

    public List<HoaDon> hoaDonChoGiaoHang();

    public List<HoaDon> hoaDonDangGiaoHang();

    public List<HoaDon> hoaDonHoanTat();

    public List<HoaDon> hoaDonDaHuy();

    public List<HoaDon> findAll();

    public List<HoaDon> searchDoiTra(String search);

    public List<HoaDon> find();

    public HoaDon findById(UUID id);

    public List<HoaDon> hoaDonKH(UUID id);

    public HoaDon add(HoaDon hoaDon);

    public HoaDon update(UUID id, HoaDon hoaDon);

    public HoaDon thanhToan(HoaDon hoaDon);

    public Boolean delete(UUID id);

    public void update(UUID id, int tinhTrang, Date ngayCapNhat);

    public void autoUpdate();

    public List<HoaDon> search(String ten, BigDecimal soTienQuyDoi);

    public List<HoaDon> searchChoGiaoHang(String ten, BigDecimal soTienQuyDoi);

    public List<HoaDon> searchDangGiaoHang(String ten, BigDecimal soTienQuyDoi);

    public List<HoaDon> searchHoanTat(String ten, BigDecimal soTienQuyDoi);

    public List<HoaDon> searchDaHuy(String ten, BigDecimal soTienQuyDoi);

    public List<HoaDon> loc1(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                             Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> locChoGiaoHang(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                                       Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> locDangGiaoHang(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                                        Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> locHoanTat(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                                   Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> locDaHuy(UUID idKH, UUID idNV, UUID idDC, Integer loai,
                                   Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> locDonHang(UUID idKH, UUID idNV, UUID idDC, Integer trangThai,
                                   Date startDate, Date endDate, Date shipStartDate, Date shipEndDate, Date receiveStartDate, Date receiveEndDate
    );

    public List<HoaDon> searchDonHang(String ten);

    public List<HoaDon> findAllByCreatedAtAfter(java.util.Date startDate);

    public List<HoaDon> findAllByNgayNhan(java.util.Date startDate);

    public List<HoaDon> findAllByNgayShip(java.util.Date startDate);

    public List<HoaDon> findDonHangByCreatedAtAfter(@Param("startDate") java.util.Date startDate);

    public List<HoaDon> findDonHangByNgayNhan(@Param("startDate") java.util.Date startDate);

    public List<HoaDon> findDonHangByNgayShip(@Param("startDate") java.util.Date startDate);

    public ResponseEntity<byte[]> generatePdfDonTaiQuay(UUID hoaDonId);

    public ResponseEntity<byte[]> generatePdfPhieu(UUID hoaDonId);

    public ResponseEntity<byte[]> generatePdfDonOnline(UUID hoaDonId);
}


