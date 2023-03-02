package com.example.oauth

import com.example.oauth.global.security.jwt.JwtProperties
import com.example.oauth.infrastructure.feign.properties.FacebookFeignProperties
import com.example.oauth.infrastructure.feign.properties.GoogleFeignProperties
import com.example.oauth.infrastructure.feign.properties.KakaoFeignProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableConfigurationProperties(
    JwtProperties::class,
    GoogleFeignProperties::class,
    KakaoFeignProperties::class,
    FacebookFeignProperties::class
)
@EnableFeignClients
class OauthApplication

fun main(args: Array<String>) {
    runApplication<OauthApplication>(*args)
}
