
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
@ToString
@Entity
@Table(name = "don_dat_hang")
public class DonDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "don_gia")
    private BigDecimal donGia;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "don_gia_khi_giam")
    private BigDecimal donGiaKhiGiam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chi_tiet_san_pham")
    private ChiTietSanPham chiTietSanPham;



    public  Integer phantram(){
        Double giam=100-Double.valueOf(String.valueOf(donGiaKhiGiam))/Double.valueOf(String.valueOf(donGia))*100;
        Integer phanTramGiam = giam.intValue(); // Chuyển đổi giá trị Double thành Integer
        return phanTramGiam;
    }
    public String basoOchammotlamGHDG() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }
    public String basoOchammotlamGHDG2() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }

    public String basoOchammotlamGHDGKG() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGiaKhiGiam));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }
    public String basoOchammotlamGHDGKG2() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGiaKhiGiam));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }
    public String tichDONGIAvsSL() {
        // Input number

        long number = Long.valueOf(String.valueOf(donGiaKhiGiam))*Long.valueOf(String.valueOf(soLuong));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String tichDONGIAvsSL2() {
        // Input number

        long number = Long.valueOf(String.valueOf(donGiaKhiGiam))*Long.valueOf(String.valueOf(soLuong));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }
}
