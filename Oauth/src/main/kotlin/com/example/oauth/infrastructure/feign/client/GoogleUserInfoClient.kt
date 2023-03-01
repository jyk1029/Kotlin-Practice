package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.UserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "GoogleUserInfoClient", url = "https://accounts.google.com/o/oauth2/auth")
interface GoogleUserInfoClient {
    @GetMapping
    fun getUserInfo(@RequestHeader("Authorization") authorization: String): UserInfoResponse
}