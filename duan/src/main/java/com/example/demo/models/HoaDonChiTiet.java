package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_imei")
    private IMEI imei;


    public  Integer phantram(){
        Double giam=100-Double.valueOf(String.valueOf(donGia))/Double.valueOf(String.valueOf(imei.getChiTietSanPham().getGiaBan()))*100;
        Integer phanTramGiam = giam.intValue(); // Chuyển đổi giá trị Double thành Integer
        return phanTramGiam;
    }

    public String convertDongiaHDCT() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }
}
