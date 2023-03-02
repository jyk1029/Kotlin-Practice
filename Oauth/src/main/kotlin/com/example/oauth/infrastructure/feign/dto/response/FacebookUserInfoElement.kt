package com.example.oauth.infrastructure.feign.dto.response

data class FacebookUserInfoElement(
    val email: String,
    val password: String,
    val name: String
)