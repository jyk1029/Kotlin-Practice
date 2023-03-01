package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GoogleFeignClient", url = "https://oauth2.googleapis.com/token")
interface GoogleTokenClient {

    @PostMapping
    fun getCode(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String
    ): TokenResponse
}