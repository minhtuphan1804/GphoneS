package com.example.demo.models;

import com.google.api.client.util.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Size(min = 6, message = "Tên phải lớn hơn hoặc bằng 6 kí tự")
    @Column(name = "ten")
    private String ten;


    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_cap_nhat")
    private String ngayCapNhat;


    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ngay_bat_dau")
    private String  ngayBatDau;


    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ngay_ket_thuc")
    private String   ngayKetThuc;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "loai_giam_gia")
    private String loaiGiamGia;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "hinh_thuc_giam_gia")
    private String hinhThucGiamGia;


    @NotNull(message = "Không để trống thông tin")
    @Min(value = 1, message = "chỉ chấp nhận 1-50")
    @Max(value = 50, message = "chỉ chấp nhận 1-50")
    @Column(name = "so_tien_giam")
    private Integer soTienGiam;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "mo_ta")
    private String moTa;
    public String trangThai(){
        if(tinhTrang== 0 ){
            return "Còn dùng";
        }return "Không còn dùng";

    }






}
