package com.example.oauth.infrastructure.feign.client

import com.example.oauth.infrastructure.feign.dto.response.UserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "FacebookUserInfoClient", url = "https://www.facebook.com/v16.0/dialog/oauth?")
interface FacebookUserInfoClient {
    @GetMapping
    fun getUserInfo(@RequestHeader("Authorization") authorization: String): UserInfoResponse
}