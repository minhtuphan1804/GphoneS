package com.example.demo.security;

import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final NhanVienRepository repository;

    private final KhachHangRepository khachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<NhanVien> userInfo = repository.findByTaiKhoan(username);
//        if (userInfo!=null){
//            return userInfo.map(UserInfoUserDetails::new)
//                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//
//        }
//        Optional<KhachHang> khachHang =khachHangRepository.getKhachHangByTaiKhoan(username);
//
//        if (khachHang!=null){
//            return khachHang.map(UserInfoUserDetails::new)
//                    .orElseThrow(()->new UsernameNotFoundException("user not found"+username));
//
//        }
//        throw  new UsernameNotFoundException("Not found");


        Optional<NhanVien> userInfo = repository.findByTaiKhoan(username);
        if (userInfo.isPresent()) {
            return userInfo.map(UserInfoUserDetails::new).get();
        }

        Optional<KhachHang> khachHang = khachHangRepository.getKhachHangByTaiKhoan(username);
        if (khachHang.isPresent()) {
            return khachHang.map(UserInfoUserDetails::new).get();
        }

        throw new UsernameNotFoundException("User not found: " + username);

    }
}

