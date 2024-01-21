package com.example.demo.config;

import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.security.MySimpleUrlAuthenticationSuccessHandler;
import com.example.demo.security.UserInfoService;
import com.example.demo.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final NhanVienRepository repository;
    private final KhachHangRepository khachHangRepository;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(repository, khachHangRepository);
    }


    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    SecurityFilterChain securityFilterChainGmail(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/","/dang-ky-tai-khoan","/login/dang-ky-tai-khoan","/login/quen-mat-khau","/quen-mat-khau", "/WEB-INF/**", "/lgin", "/logout", "/ban-hang-online/**", "/css/**", "/cssbanhang/**"
                        , "/documentation/**", "/icons/**", "/images/**", "/inHoaDon/**", "/js/**", "/jsbanhang/**", "/maqr/**", "/scss/**"
                        , "uploads/**", "/vendor/**","/ghn").permitAll()
                .requestMatchers("/favicon.ico").permitAll()
                //ca admin ca nhan vien
                .requestMatchers("/chi-tiet-san-pham/hien-thi", "/ban-hang/**", "/anh/hien-thi-delete"
                        , "/anh/search-0", "/anh/search-1", "/camera/hien-thi", "/camera/hien-thi-delete", "/camera/search-0", "/camera/search-1",
                        "/chip/hien-thi", "/chip/hien-thi-delete", "/chip/search-0", "/chip/search-1",
                        "/chi-tiet-san-pham/hien-thi", "/chi-tiet-san-pham/hien-thi-da-xoa", "/chi-tiet-san-pham/loc", "/chi-tiet-san-pham/search-da-xoa","/chi-tiet-san-pham/export-excel",
                        "/chi-tiet-san-pham/export-excel-chi-tiet-san-pham-ngung-kinh-doanh","/imei/show-qr/**","/imei/export-excel","/imei/export-excel-imei-da-ban","/san-pham/export-excel",
                        "/san-pham/export-excel-san-pham-da-ngung-kinh-doanh","/chi-tiet-san-pham/export-excel-chi-theo-hang/**",
                        "/chi-tiet-san-pham/export-excel-chi-theo-cam/**","/chi-tiet-san-pham/export-excel-chi-theo-ram/**","/chi-tiet-san-pham/export-excel-chi-theo-rom/**",
                        "/chi-tiet-san-pham/export-excel-chi-theo-pin/**","/chi-tiet-san-pham/export-excel-chi-theo-chip/**","/chi-tiet-san-pham/export-excel-chi-theo-man/**",

                        "/dia-chi/hien-thi","/dia-chi/dia-chi-tung-xoa","/dia-chi/tim-kiem/**","/dia-chi/tim-kiem-tung-xoa","/dia-chi/view-add","/dia-chi/add",
                        "/don-hang/**",
                        "/dung-luong-pin/hien-thi","/dung-luong-pin/hien-thi-delete","/dung-luong-pin/search-0","/dung-luong-pin/search-1",
                        "/hang-dien-thoai/hien-thi","/hang-dien-thoai/hien-thi-tung-xoa","/hang-dien-thoai/search","/hang-dien-thoai/search2",
                        "/hang-khach-hang/hien-thi","/hang-khach-hang/hang-khach-hang-tung-xoa","/hang-khach-hang/tim-kiem/**","/hang-khach-hang/tim-kiem-tung-xoa",
                        "/hang-khach-hang/view-add","/hang-khach-hang/add",

                        "/hoa-don/**",
                        "/imei/hien-thi","/imei/search-on","/imei/search-off-1","/imei/search-off-2","/imei/hien-thi-da-ban","/imei/hien-thi-da-xoa","/imei/loc-da-ban","/imei/loc-loi","/imei/loc-da-xoa","/imei/loc-chua-ban",
                        "/khach-hang/hien-thi","/khach-hang/tim-kiem","/khach-hang/tim-kiem-tung-xoa","khach-hang/khach-hang-tung-xoa","/khach-hang/view-add",
                        "/khach-hang/add","/khach-hang/them-hang-khach-hang-add",
                        "/man-hinh/hien-thi","/man-hinh/hien-thi-delete","/man-hinh/search-0","/man-hinh/search-1",
                        "/mau-sac/hien-thi","/mau-sac/hien-thi-delete","/mau-sac/search-0","/mau-sac/search-1",
                        "/pin/hien-thi","/pin/hien-thi-delete","/pin/search-0","/pin/search-1",
                        "/ram/hien-thi","/ram/hien-thi-delete","/ram/search-0","/ram/search-1",
                        "/rom/hien-thi","/rom/hien-thi-tung-xoa","/rom/search","/rom/search2",
                        "/san-pham/hien-thi","/san-pham/hien-thi-tung-xoa","/san-pham/search","/san-pham/search2","/san-pham/loc","/san-pham/hien-thi-loc",
                        "/doi-tra/**"


                ).hasAnyAuthority("STAFF", "ADMIN")

                // chi admin
                .requestMatchers("/chi-tiet-san-pham/**", "/ban-hang/**", "/anh/**", "/camera/**", "/chip/**",
                        "/chuc-vu/**","/dia-chi/**","/don-hang/**","/dung-luong-pin/**","/hang-dien-thoai/**","/hang-khach-hang/**",
                        "/hoa-don/**","/imei/**","/khach-hang/**","/man-hinh/**","/mau-sac/**","/nhan-vien/**",
                        "/pin/**","/ram/**","/rom/**","/san-pham/**","/san-pham-giam-gia/**","/thong-ke/**").hasAuthority("ADMIN")

                //tat ca tai khoan
                .requestMatchers("/ban-hang-online/trang-chi-tiet-san-pham/thanh-toan/**", "/ban-hang-online/trang-gio-hang-chi-tiet/**",
                        "/ban-hang-online/san-pham-duoc-chon-thanh-toan/**").authenticated()


                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/lgin")
                .loginProcessingUrl("/security/login")

                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .oauth2Login()
                .loginPage("/lgin")
                .defaultSuccessUrl("/ban-hang-online/gmail", true)
                .userInfoEndpoint().userService(customOAuth2UserService);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
