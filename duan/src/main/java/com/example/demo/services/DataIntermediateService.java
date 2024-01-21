package com.example.demo.services;

import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import org.springframework.stereotype.Service;

@Service
public class DataIntermediateService {
    private KhachHang sharedData;

    private NhanVien dataNhanVienLogin;

    public void setSharedData(KhachHang data) {
        this.sharedData = data;
    }

    public void setDataNhanVienLogin(NhanVien data) {
        this.dataNhanVienLogin = data;
    }

    public KhachHang getSharedData() {
        return sharedData;
    }

    public NhanVien getSharedDataNhanVien() {
        return dataNhanVienLogin;
    }

}
