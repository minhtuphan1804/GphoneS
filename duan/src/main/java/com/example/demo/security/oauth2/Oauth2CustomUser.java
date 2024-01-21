package com.example.demo.security.oauth2;

import com.example.demo.models.KhachHang;
import com.example.demo.repositories.KhachHangRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Oauth2CustomUser implements OAuth2User {
    private OAuth2User oauth2;
    private List<GrantedAuthority> authorities;
    @Autowired
    private KhachHangRepository khachHangRepository;

    public Oauth2CustomUser(OAuth2User oauth2) {
        this.oauth2 = oauth2;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        authorities= new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("USER")));
        return authorities;
    }

    @Override
    public String getName() {
        return oauth2.getAttribute("email");
    }
    public String getFullName(){
        return oauth2.getAttribute("name");
    }
    public String getEmail() {
        return oauth2.getAttribute("email");
    }
    public String getTen(){
        KhachHang khachHang =khachHangRepository.getKhachHangByTaiKhoan(oauth2.getAttribute("email")).get();
        return khachHang.getTaiKhoan();
    }

}
