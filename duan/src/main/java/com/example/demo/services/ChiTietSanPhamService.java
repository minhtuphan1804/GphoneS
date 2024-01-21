package com.example.demo.services;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {
    public Page<ChiTietSanPham> getAll(Pageable pageable);

    public List<ChiTietSanPham> findAll();

    public List<ChiTietSanPham> findAll0();

    public List<ChiTietSanPham> search(String ten);

    public List<ChiTietSanPham> search1(String ten);

    public List<ChiTietSanPham> loc(UUID idSanPham,UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    public List<ChiTietSanPham> locBanHang(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam, BigDecimal giaBanMin, BigDecimal giaBanMax);

    public ChiTietSanPham findById(UUID id);

    public ChiTietSanPham getChiTiet(UUID id);

    public ChiTietSanPham getChiTiet2(UUID id);

    public ChiTietSanPham add(ChiTietSanPham chiTietSanPham);

    public ChiTietSanPham update(UUID id, ChiTietSanPham chiTietSanPham);

    public ChiTietSanPham update1(ChiTietSanPham chiTietSanPham);

    public Boolean delete(UUID id);

    public List<ChiTietSanPham> getAllTTOn();

    public List<ChiTietSanPham> getAllTTOff();

    public List<ChiTietSanPham> finAllTTOff();

    public List<ChiTietSanPham> findAllHang(UUID id);

    public List<ChiTietSanPham> findAllMan(UUID id);

    public List<ChiTietSanPham> findAllChip(UUID id);

    public List<ChiTietSanPham> findAllRam(UUID id);

    public List<ChiTietSanPham> findAllRom(UUID id);

    public List<ChiTietSanPham> findAllPin(UUID id);

    public List<ChiTietSanPham> findAllCam(UUID id);

    Integer getCountTongSP();

    Integer cacDonHang();

    Integer tongSPDaBan();

    Integer cacDonHangChoXuLy();

    Integer soKhachHang();

    Integer soNV();

    public List<ChiTietSanPham> searchGia(UUID id);

    public boolean existsDuplicate(SanPham sanPham, Chip chip, Rom rom, Ram ram, Pin pin, MauSac mauSac);
}


