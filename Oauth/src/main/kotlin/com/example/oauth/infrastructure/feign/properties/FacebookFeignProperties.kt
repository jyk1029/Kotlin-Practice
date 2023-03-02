package com.example.oauth.infrastructure.feign.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(value = "auth.facebook")
@ConstructorBinding
data class FacebookFeignProperties(
    val clientId: String,
    val clientSecret: String
)