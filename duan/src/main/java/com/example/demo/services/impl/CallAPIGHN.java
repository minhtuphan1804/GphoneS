package com.example.demo.services.impl;

import com.example.demo.models.GiaoHangNhanh;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallAPIGHN {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";

    public String getAPIGHN(GiaoHangNhanh giaoHangNhanh) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ShopId", "4782975 - Bán đớ"); // Điền ShopId của bạn vào đây
        headers.set("Token", "908a0de5-9f55-11ee-b394-8ac29577e80e");    // Điền Token của bạn vào đây
        HttpEntity<GiaoHangNhanh> entity = new HttpEntity<>(giaoHangNhanh,headers);
        System.out.println(giaoHangNhanh);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("data").get("total_fee").asText();
    }
}
