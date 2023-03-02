package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.UserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "KakaoUserInfoClient", url = "https://kauth.kakao.com/oauth/authorize")
interface KakaoUserInfoClient {
    @GetMapping
    fun getUserInfo(@RequestHeader("Authorization") authorization: String): UserInfoResponse
}
