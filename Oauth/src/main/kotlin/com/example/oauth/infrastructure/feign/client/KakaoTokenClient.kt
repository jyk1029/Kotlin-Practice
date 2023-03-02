package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "KakaoFeignClient", url = "https://kauth.kakao.com/oauth/token")
interface KakaoTokenClient {
    @PostMapping
    fun getCode(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("client_authentication_method") clientAuthenticationMethod: String,
        @RequestParam("authorization_grant_type") authorizationGrantType: String,
        @RequestParam("redircet_url") redirectUrl: String,
        @RequestParam("client_name") clientName: String
    ): TokenResponse
}