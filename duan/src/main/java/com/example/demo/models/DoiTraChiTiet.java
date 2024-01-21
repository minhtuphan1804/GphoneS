package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "doi_tra_chi_tiet")
public class DoiTraChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doi_tra")
    private DoiTra doiTra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imei")
    private IMEI imei;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don_chi_tiet")
    private HoaDonChiTiet hoaDonChiTiet;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @Column(name = "hien_trang_san_pham")
    private int hienTrangSanPham;

    @Column(name = "hinh_thuc_doi_tra")
    private int hinhThucDoiTra;

    @Column(name = "ly_do_doi_tra")
    private String lyDo;

    @Column(name = "tien_doi_tra")
    private BigDecimal tienDoiTra;

    @Column(name = "don_gia")
    private BigDecimal donGia;






}


