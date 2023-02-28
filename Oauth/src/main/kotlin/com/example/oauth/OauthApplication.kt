package com.example.oauth

import com.example.oauth.global.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class OauthApplication

fun main(args: Array<String>) {
    runApplication<OauthApplication>(*args)
}
