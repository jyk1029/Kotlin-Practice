package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "FacebookFeignClient", url = "https://graph.facebook.com/v16.0/oauth/access_token?")
interface FacebookTokenClient {
    @PostMapping
    fun getCode(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String
    ): TokenResponse
}