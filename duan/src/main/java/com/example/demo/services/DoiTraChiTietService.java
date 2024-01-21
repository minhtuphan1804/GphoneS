package com.example.demo.services;

import com.example.demo.models.DoiTra;
import com.example.demo.models.DoiTraChiTiet;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DoiTraChiTietService {
    public Page<DoiTraChiTiet> getAll(Pageable pageable);

    public DoiTraChiTiet add(DoiTraChiTiet doiTraChiTiet);

    public DoiTraChiTiet update(UUID id, DoiTraChiTiet doiTraChiTiet);

    public Boolean delete(UUID id);

    public List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);


    public   DoiTraChiTiet getDoiTraByHDCT(UUID id);

    public List<HoaDonChiTiet> getHoaDonChiTietByIdList(@Param("idList") List<UUID> idList);

    public List<DoiTraChiTiet> getDoiTraChiTietByIdList(@Param("idList") List<UUID> idList);

    public List<DoiTraChiTiet> getAll();

    public boolean existsByDoiTraAndHoaDonChiTiet(DoiTra doiTra, HoaDonChiTiet hoaDonChiTiet);

    public void saveAll(List<DoiTraChiTiet> doiTraChiTiets);

    public List<DoiTraChiTiet> getDoiTraChiTietByDoiTraId(UUID id);

    public DoiTraChiTiet findById(UUID id);

    public DoiTraChiTiet findByHDCT(UUID id);

    public DoiTraChiTiet findByDTCT(UUID id);

    public List<DoiTraChiTiet> doiTraChiTiet(UUID id);
}
