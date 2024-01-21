package com.example.demo.repositories;

import com.example.demo.models.DoiTra;
import com.example.demo.models.DoiTraChiTiet;
import com.example.demo.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoiTraChiTietRepository extends JpaRepository<DoiTraChiTiet, UUID> {
    @Query("SELECT d from DoiTraChiTiet d where d.tinhTrang=0")
    Page<DoiTraChiTiet> fillAll0(Pageable pageable);

    @Query("select hdct from HoaDonChiTiet hdct left join DoiTraChiTiet hd on hdct.id=hd.hoaDonChiTiet.id where hd.id=:id")
    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);

    @Query("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.id IN :idList")
    List<HoaDonChiTiet> getHoaDonChiTietByIdList(@Param("idList") List<UUID> idList);

    @Query("select hdct from DoiTraChiTiet hdct left join HoaDonChiTiet hd on hdct.hoaDonChiTiet.id=hd.id where hd.id=:id")
    DoiTraChiTiet getDoiTraByHDCT(UUID id);

    @Query("SELECT hdct FROM DoiTraChiTiet hdct WHERE hdct.hoaDonChiTiet.id IN :idList")
    List<DoiTraChiTiet> getDoiTraChiTietByIdList(@Param("idList") List<UUID> idList);


    @Query("SELECT dtct FROM DoiTraChiTiet dtct WHERE dtct.doiTra.id = :id")
    List<DoiTraChiTiet> getDoiTraChiTietByDoiTraId(UUID id);

    @Query("SELECT dtct FROM DoiTraChiTiet dtct WHERE dtct.hoaDonChiTiet.id = :id")
    DoiTraChiTiet findByHDCT(UUID id);

    @Query("SELECT dtct FROM DoiTraChiTiet dtct WHERE dtct.id = :id")
    DoiTraChiTiet findByDTCT(UUID id);

    @Query("SELECT CASE WHEN COUNT(dtct) > 0 THEN true ELSE false END FROM DoiTraChiTiet dtct WHERE dtct.doiTra = :doiTra AND dtct.hoaDonChiTiet = :hoaDonChiTiet")
    public boolean existsByDoiTraAndHoaDonChiTiet(@Param("doiTra") DoiTra doiTra, @Param("hoaDonChiTiet") HoaDonChiTiet hoaDonChiTiet);

    @Query("select dtct from DoiTraChiTiet dtct left join DoiTra dt on dt.id=dtct.doiTra.id " +
            "left join HoaDon hd on dt.hoaDon.id=hd.id where hd.id=:id")
    List<DoiTraChiTiet> doiTraChiTiet(UUID id);

}
