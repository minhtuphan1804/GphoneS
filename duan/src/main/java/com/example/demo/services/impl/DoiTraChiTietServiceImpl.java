package com.example.demo.services.impl;

import com.example.demo.models.DoiTra;
import com.example.demo.models.DoiTraChiTiet;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.DoiTraChiTietRepository;
import com.example.demo.services.DoiTraChiTietService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoiTraChiTietServiceImpl implements DoiTraChiTietService {
    @Autowired
    DoiTraChiTietRepository doiTraChiTietRepository;

    public Page<DoiTraChiTiet> getAll(Pageable pageable) {
        return doiTraChiTietRepository.fillAll0(pageable);
    }

    @Override
    public List<HoaDonChiTiet> getHoaDonChiTiet(UUID id) {
        return doiTraChiTietRepository.getHoaDonChiTiet(id);
    }

    @Override
    public DoiTraChiTiet add(DoiTraChiTiet doiTraChiTiet) {
        return doiTraChiTietRepository.save(doiTraChiTiet);
    }

   @Override
  public DoiTraChiTiet getDoiTraByHDCT(UUID id){
        return doiTraChiTietRepository.getDoiTraByHDCT(id);

   };

    @Override
    public List<DoiTraChiTiet> getAll() {
        return doiTraChiTietRepository.findAll();
    }

    public void saveAll(List<DoiTraChiTiet> doiTraChiTiets) {
        doiTraChiTietRepository.saveAll(doiTraChiTiets);
    }

    @Override
    public boolean existsByDoiTraAndHoaDonChiTiet(DoiTra doiTra, HoaDonChiTiet hoaDonChiTiet) {
        return doiTraChiTietRepository.existsByDoiTraAndHoaDonChiTiet(doiTra, hoaDonChiTiet);
    }

    @Override
    public List<DoiTraChiTiet> getDoiTraChiTietByDoiTraId(UUID id) {
        return doiTraChiTietRepository.getDoiTraChiTietByDoiTraId(id);
    }

    @Override
    public DoiTraChiTiet findById(UUID id) {
        return doiTraChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDonChiTiet> getHoaDonChiTietByIdList(@Param("idList") List<UUID> idList) {
        return doiTraChiTietRepository.getHoaDonChiTietByIdList(idList);
    }

    @Override
    public List<DoiTraChiTiet> getDoiTraChiTietByIdList(@Param("idList") List<UUID> idList) {
        return doiTraChiTietRepository.getDoiTraChiTietByIdList(idList);
    }

    @Override
    public DoiTraChiTiet update(UUID id, DoiTraChiTiet doiTraChiTiet) {
        if (id != null) {
            DoiTraChiTiet doiTraUpdate = doiTraChiTietRepository.findById(id).orElse(null);
            if (doiTraUpdate != null) {
                BeanUtils.copyProperties(doiTraChiTiet, doiTraUpdate);
                doiTraChiTietRepository.save(doiTraUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            DoiTraChiTiet doiTra = doiTraChiTietRepository.findById(id).orElse(null);
            if (doiTra != null) {
                doiTraChiTietRepository.delete(doiTra);
                return true;
            }
        }
        return false;
    }


    public DoiTraChiTiet findByHDCT(UUID id) {
        return doiTraChiTietRepository.findByHDCT(id);
    }

    @Override
    public DoiTraChiTiet findByDTCT(UUID id) {
        return doiTraChiTietRepository.findByDTCT(id);
    }

    @Override
    public List<DoiTraChiTiet> doiTraChiTiet(UUID id) {
        return doiTraChiTietRepository.doiTraChiTiet(id);
    }


}
