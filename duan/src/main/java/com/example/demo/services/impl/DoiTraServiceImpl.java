package com.example.demo.services.impl;

import com.example.demo.DTO.DTODoiTra;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DoiTra;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.DoiTraRepository;
import com.example.demo.services.DoiTraService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service

public class DoiTraServiceImpl implements DoiTraService {

    @Autowired
    DoiTraRepository doiTraRepository;


    @Override
    public Page<DoiTra> getAll(Pageable pageable) {
        return doiTraRepository.findAll(pageable);
    }

    @Override
    public List<DoiTra> getAll0() {
        return doiTraRepository.fillAll0();
    }

    @Override
    public List<DoiTra> getAll1() {
        return doiTraRepository.fillAll1();
    }



@Override
    public DoiTra getDoiTraByHoaDon(UUID id){
        return doiTraRepository.getDoiTraByHoaDon(id);
    };

    @Override
    public List<DoiTra> getAll2() {
        return doiTraRepository.fillAll2();
    }

    @Override
    public Page<HoaDon> getAllhoadon(Pageable pageable) {
        return doiTraRepository.fillAllHoaDon(pageable);
    }


    public List<DTODoiTra> getAllHD() {
        return doiTraRepository.getAllHD();
    }


    @Override
    public List<DoiTra> findAll() {
        return doiTraRepository.findAll();
    }

    @Override
    public List<DoiTra> fillAll0() {
        return doiTraRepository.getAll0();
    }

    @Override
    public List<DoiTra> fillAll1() {
        return doiTraRepository.getAll1();
    }

    @Override
    public List<DoiTra> fillAll2() {
        return doiTraRepository.getAll2();
    }

    @Override
    public List<DoiTra> locDoiTraTrangThai0(UUID idKH, UUID idNV, Date startDate, Date endDate, Date ngayTao0, Date ngayTao1) {
        return doiTraRepository.locDoiTraTrangThai0(idKH, idNV, startDate, endDate, ngayTao0, ngayTao1);
    }

    @Override
    public List<DoiTra> locDoiTraTrangThai1(UUID idKH, UUID idNV, Date startDate, Date endDate, Date ngayTao0, Date ngayTao1) {
        return doiTraRepository.locDoiTraTrangThai1(idKH, idNV, startDate, endDate, ngayTao0, ngayTao1);
    }

    @Override
    public List<DoiTra> locDoiTraTrangThai2(UUID idKH, UUID idNV, Date startDate, Date endDate, Date ngayTao0, Date ngayTao1) {
        return doiTraRepository.locDoiTraTrangThai2(idKH, idNV, startDate, endDate, ngayTao0, ngayTao1);
    }

    @Override
    public List<DoiTra> searchDoiTraTrangThai0(String search) {
        return doiTraRepository.searchDoiTraTrangThai0(search);
    }

    @Override
    public List<DoiTra> searchDoiTraTrangThai1(String search) {
        return doiTraRepository.searchDoiTraTrangThai1(search);
    }

    @Override
    public List<DoiTra> searchDoiTraTrangThai2(String search) {
        return doiTraRepository.searchDoiTraTrangThai2(search);
    }

    @Override
    public List<HoaDonChiTiet> getHoaDonChiTiet(UUID id) {
        return doiTraRepository.getHoaDonChiTiet(id);
    }

    ;


    @Override
    public DoiTra findById(UUID id) {
        return doiTraRepository.findById(id).orElse(null);
    }

    @Override
    public DoiTra add(DoiTra doiTra) {
        return doiTraRepository.save(doiTra);
    }

    @Override
    public DoiTra update(UUID id, DoiTra doiTra) {
        if (id != null) {
            DoiTra doiTraUpdate = doiTraRepository.findById(id).orElse(null);
            if (doiTraUpdate != null) {
                BeanUtils.copyProperties(doiTra, doiTraUpdate);
                doiTraRepository.save(doiTraUpdate);
            }
        }
        return null;
    }

    @Override
    public List<ChiTietSanPham> locbanhang(String idHang,
                                           String moTaCam,
                                           String moTaMan,
                                           String moTaMau,
                                           String idRam,
                                           String idRom,
                                           String idPin,
                                           String idDLPin,
                                           String idChip,
                                           String tenSP) {
        return doiTraRepository.locbanhang(idHang,
                moTaCam,
                moTaMan,
                moTaMau,
                idRam,
                idRom,
                idPin,
                idDLPin,
                idChip,
                tenSP);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            DoiTra doiTra = doiTraRepository.findById(id).orElse(null);
            if (doiTra != null) {
                doiTraRepository.delete(doiTra);
                return true;
            }
        }
        return false;
    }
}
