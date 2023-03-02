package com.example.oauth.infrastructure.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "auth.kakao")
@ConstructorBinding
data class KakaoFeignProperties(
    val clientId: String,
    val clientSecret: String,
    val clientAuthenticationMethod: String,
    val authorizationGrantType: String,
    val redirectUri: String,
    val clientName: String
)