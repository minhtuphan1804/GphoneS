package com.example.demo.repositories;

import com.example.demo.models.DonDatHang;
import com.example.demo.models.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DonDatHangRepository extends JpaRepository<DonDatHang, UUID> {
}
