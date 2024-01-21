package com.example.demo.security;

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by HachNV on 31/05/2023
 */
@Service
public final class UserService {
    private final NhanVienRepository repository;
    private final PasswordEncoder passwordEncoder;

    UserService(NhanVienRepository repository,
                PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public NhanVienRepository repository() {
        return repository;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserService) obj;
        return Objects.equals(this.repository, that.repository) &&
                Objects.equals(this.passwordEncoder, that.passwordEncoder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, passwordEncoder);
    }

    @Override
    public String toString() {
        return "UserService[" +
                "repository=" + repository + ", " +
                "passwordEncoder=" + passwordEncoder + ']';
    }

    public String addUser(NhanVien userInfo) {
        PasswordEncoder passwordEncoder=passwordEncoder();
        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
        repository.save(userInfo);
        return "Thêm user thành công!";
    }
}
