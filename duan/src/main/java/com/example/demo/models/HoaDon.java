package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

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
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ma")
    private String ma;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "sdt")
    private String sdt;

    //    @NotBlank(message = "Không để trống thông tin"
    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @DecimalMin(value = "0", message = "Phí ship phải là số và lớn hơn hoặc bằng 0")
    @DecimalMax(value = "300000", message = "Phí ship tối đa là 300.000")
    @Column(name = "phi_ship")
    private BigDecimal phiShip;

    @Column(name = "nguoi_nhan")
    private String nguoiNhan;

    @CreationTimestamp
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "ngay_ship")
    private Date ngayShip;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @Column(name = "loai")
    private int loai;

    @Column(name = "hinh_thuc_thanh_toan")
    private int hinhThucThanhToan;

    @Column(name = "tinh_trang_giao_hang")
    private int tinhTrangGiaoHang;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dia_chi")
    private DiaChi diaChi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quy_doi")
    private QuyDoi quyDoi;

    @Column(name = "ma_giao_dich")
    private String maGiaoDich;

    public String convertTongtien() {
        // Input number
        long number = Long.valueOf(String.valueOf(tongTien));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String convertTongtien2() {
        // Input number
        long number = Long.valueOf(String.valueOf(tongTien));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }

    public String convertPhiShip() {
        // Input number
        if (phiShip==null){
            return "0";
        }else {
            long number = Long.valueOf(String.valueOf(phiShip));

            // Create a DecimalFormat instance with the desired pattern
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

            // Format the number
            return decimalFormat.format(number);        }

    }
}
