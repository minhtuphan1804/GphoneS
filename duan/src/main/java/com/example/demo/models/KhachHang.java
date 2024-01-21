package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "anh")
    private String anh;

    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ho_ten")
    private String hoTen;


    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;


    //    @Pattern(regexp = "^.{8,}@gmail\\.com$", message = "8 ký tự +@gmail.com")
    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "email")
    private String email;


    @Pattern(regexp = "^0[0-9]{9}$", message = "Sdt phải 10 số và bắt đầu bằng 0")
//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "tai_khoan")
    private String taiKhoan;

    //    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "mat_khau")
    private String matKhau;

    @CreationTimestamp
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;


    @Column(name = "tinh_trang")
    private int tinhTrang;

    @Positive(message = "Điểm tối thiểu phải là số nguyên dương")
    @NotNull(message = "Không để trống thông tin")
    @Column(name = "diem")
    private Integer diem;


    @ManyToOne
    @JoinColumn(name = "id_hang_khach_hang")
    private HangKhachHang hangKhachHang;


    public String goitinh() {
        if (gioiTinh == true) {
            return "Nam";
        }
        return "Nữ";
    }

    public String tt() {
        if (tinhTrang == 0) {
            return "Còn hoạt động";
        }
        return "Không còn hoạt động";
    }

    public String getKHMT() {

        return ma + "-" + hoTen;

    }

    public KhachHang(String ma,
                     String hoTen,
                     Boolean gioiTinh,
                     String sdt,
                     Date ngayTao,
                     Date ngayCapNhat,
                     int tinhTrang) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.tinhTrang = tinhTrang;

    }


    public String dateok()  {
        // Chuỗi ngày theo định dạng 'yyyy-MM-dd'
//        return String.valueOf(this.ngaySinh);

        if(ngaySinh==null){
            return String.valueOf(ngaySinh);
        }else {


            // Đối tượng java.sql.Date
            Date sqlDate = ngaySinh;

            // Định dạng chuỗi ngày
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Chuyển đổi java.sql.Date thành chuỗi
            return outputFormat.format(sqlDate);


        }



    }
}
