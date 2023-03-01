package com.example.oauth.infrastructure.feign.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)