package com.example.oauth.infrastructure.feign.dto.response

data class GoogleUserInfoElement(
    val email: String,
    val password: String,
    val name: String
)