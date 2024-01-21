package com.example.demo.services;

import com.example.demo.DTO.DTODoiTra;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DoiTra;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface DoiTraService {
    public Page<DoiTra> getAll(Pageable pageable);

    public List<DoiTra> findAll();

    public DoiTra findById(UUID id);

    public DoiTra add(DoiTra doiTra);

    public DoiTra update(UUID id, DoiTra doiTra);

    public Boolean delete(UUID id);

    public List<DoiTra> getAll0();

    public List<DoiTra> getAll1();

    public List<DoiTra> getAll2();

    public Page<HoaDon> getAllhoadon(Pageable pageable);

    public List<DTODoiTra> getAllHD();

    public DoiTra getDoiTraByHoaDon(UUID id);

    public List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);


    List<ChiTietSanPham> locbanhang(String idHang,
                                    String moTaCam,
                                    String moTaMan,
                                    String moTaMau,
                                    String idRam,
                                    String idRom,
                                    String idPin,
                                    String idDLPin,
                                    String idChip,
                                    String tenSP);

    public List<DoiTra> fillAll0();

    public List<DoiTra> fillAll1();

    public List<DoiTra> fillAll2();

    public List<DoiTra> locDoiTraTrangThai0(UUID idKH, UUID idNV,
                                            Date startDate, Date endDate, Date ngayTao0, Date ngayTao1);

    public List<DoiTra> locDoiTraTrangThai1(UUID idKH, UUID idNV,
                                            Date startDate, Date endDate, Date ngayTao0, Date ngayTao1);

    public List<DoiTra> locDoiTraTrangThai2(UUID idKH, UUID idNV,
                                            Date startDate, Date endDate, Date ngayTao0, Date ngayTao1);

    List<DoiTra> searchDoiTraTrangThai0(String search);

    List<DoiTra> searchDoiTraTrangThai1(String search);

    List<DoiTra> searchDoiTraTrangThai2(String search);
}


