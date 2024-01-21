package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "url_anh")
    private String urlAnh;

    @DecimalMin(value = "0.01", message = "Giá bán phải là số và lớn hơn 0")
    @DecimalMax(value = "100000000", message = "Giá bán tối đa mà sản phẩm cửa hàng bán là 100 triệu")
    @NotNull(message = "Không để trống thông tin")
    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @CreationTimestamp
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @DecimalMin(value = "1", message = "Năm bảo hành phải lớn hơn 0")
    @DecimalMax(value = "3", message = "Năm bảo hành tối đa là 3 năm")
    @NotNull(message = "Không để trống thông tin")
    @Column(name = "nam_bao_hanh")
    private int namBaoHanh;

//    @DecimalMin(value = "1", message = "Số lượng tồn phải lớn hơn 0")
//    @DecimalMax(value = "500", message = "Số lượng tồn tối đa cửa hàng tiếp nhận là 500 chiếc")
    @NotNull(message = "Không để trống thông tin")
    @Column(name = "so_luong_ton")
    private int soLuong;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chip")
    private Chip chip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ram")
    private Ram ram;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rom")
    private Rom rom;

//    @NotNull(message = "Khong de trong thong tin")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pin")
    private Pin pin;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_khuyen_mai")
    private KhuyenMai khuyenMai;


    public String basoOchammotlam() {
        // Input number
        long number = Long.valueOf(String.valueOf(giaBan));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String basoOchammotlam2() {
        // Input number
        long number = Long.valueOf(String.valueOf(giaBan));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

return  decimalFormat.format(number).replaceAll("[,]", ".");



    }
}
