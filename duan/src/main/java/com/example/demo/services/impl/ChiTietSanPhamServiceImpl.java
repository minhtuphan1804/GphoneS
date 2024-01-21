package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.services.ChiTietSanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service

public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;


    @Override
    public Page<ChiTietSanPham> getAll(Pageable pageable) {
        return chiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public List<ChiTietSanPham> findAll0() {
        return chiTietSanPhamRepository.findAll0();
    }

    @Override
    public List<ChiTietSanPham> search(String ten) {
        return chiTietSanPhamRepository.search(ten);
    }

    @Override
    public List<ChiTietSanPham> search1(String ten) {
        return chiTietSanPhamRepository.search1(ten);
    }

    @Override
    public List<ChiTietSanPham> loc(UUID idSanPham,UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam) {
        return chiTietSanPhamRepository.loc(idSanPham,idHang, idRam, idRom, idDLPin, idChip, moTaMan, moTaCam);
    }

    @Override
    public List<ChiTietSanPham> locBanHang(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam, BigDecimal giaBanMin, BigDecimal giaBanMax) {
        return chiTietSanPhamRepository.locBanHang(idHang, idRam, idRom, idDLPin, idChip, moTaMan, moTaCam, giaBanMin, giaBanMax);
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public ChiTietSanPham getChiTiet(UUID id) {
        return chiTietSanPhamRepository.getChiTiet(id);
    }

    @Override
    public ChiTietSanPham getChiTiet2(UUID id) {
        return chiTietSanPhamRepository.getChiTiet2(id);
    }

    @Override
    public ChiTietSanPham add(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham update(UUID id, ChiTietSanPham chiTietSanPham) {
        if (id != null) {
            ChiTietSanPham chiTietSanPhamUpdate = chiTietSanPhamRepository.findById(id).orElse(null);
            if (chiTietSanPhamUpdate != null) {
                BeanUtils.copyProperties(chiTietSanPham, chiTietSanPhamUpdate);
                chiTietSanPhamRepository.save(chiTietSanPhamUpdate);
            }
        }
        return null;
    }

    @Override
    public ChiTietSanPham update1(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
            if (chiTietSanPham != null) {
                chiTietSanPhamRepository.delete(chiTietSanPham);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ChiTietSanPham> getAllTTOn() {
        return chiTietSanPhamRepository.getChiTietOn();
    }

    @Override
    public List<ChiTietSanPham> getAllTTOff() {
        return chiTietSanPhamRepository.getChiTietOff();
    }

    @Override
    public List<ChiTietSanPham> finAllTTOff() {
        return chiTietSanPhamRepository.getChiTietSanPhamOff();
    }

    @Override
    public List<ChiTietSanPham> findAllHang(UUID id) {
        return chiTietSanPhamRepository.findAllHang(id);
    }

    @Override
    public List<ChiTietSanPham> findAllMan(UUID id) {
        return chiTietSanPhamRepository.findAllMan(id);
    }

    @Override
    public List<ChiTietSanPham> findAllChip(UUID id) {
        return chiTietSanPhamRepository.findAllChip(id);
    }

    @Override
    public List<ChiTietSanPham> findAllRam(UUID id) {
        return chiTietSanPhamRepository.findAllRam(id);
    }

    @Override
    public List<ChiTietSanPham> findAllRom(UUID id) {
        return chiTietSanPhamRepository.findAllRom(id);
    }

    @Override
    public List<ChiTietSanPham> findAllPin(UUID id) {
        return chiTietSanPhamRepository.findAllPin(id);
    }

    @Override
    public List<ChiTietSanPham> findAllCam(UUID id) {
        return chiTietSanPhamRepository.findAllCam(id);
    }

    @Override
    public Integer getCountTongSP() {
        return chiTietSanPhamRepository.tongSanPham();
    }

    @Override
    public Integer cacDonHang() {
        return chiTietSanPhamRepository.cacDonHang();
    }

    @Override
    public Integer tongSPDaBan() {
        return chiTietSanPhamRepository.tongSPDaBan();
    }

    @Override
    public Integer cacDonHangChoXuLy() {
        return chiTietSanPhamRepository.cacDonHangChoXuLy();
    }

    @Override
    public Integer soKhachHang() {
        return chiTietSanPhamRepository.soKhachHang();

    }

    @Override
    public Integer soNV() {
        return chiTietSanPhamRepository.soNV();
    }

    @Override
    public List<ChiTietSanPham> searchGia(UUID id) {
        return chiTietSanPhamRepository.searchGia(id);
    }

    @Override
    public boolean existsDuplicate(SanPham sanPham, Chip chip, Rom rom, Ram ram, Pin pin, MauSac mauSac) {
        return chiTietSanPhamRepository.existsBySanPhamAndChipAndRomAndRamAndPinAndMauSac(sanPham, chip, rom, ram, pin,mauSac);
    }


}
