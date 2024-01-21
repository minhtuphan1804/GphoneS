package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "dia_chi")
    private String diaChi;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "quan")
    private String quan;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "huyen")
    private String huyen;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "thanh_pho")
    private String thanhPho;

    @CreationTimestamp
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    public String tt(){
        if(tinhTrang== 0 ){
            return "Còn hoạt động";
        }return "Không còn hoạt động";
    }

}
