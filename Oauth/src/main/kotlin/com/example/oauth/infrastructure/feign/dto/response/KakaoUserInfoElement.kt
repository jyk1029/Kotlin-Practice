package com.example.oauth.infrastructure.feign.dto.response

data class KakaoUserInfoElement(
    val profileNickname: String,
    val accountEmail: String,
    val password: String
)