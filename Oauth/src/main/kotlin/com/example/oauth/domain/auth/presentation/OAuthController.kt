package com.example.oauth.domain.auth.presentation

import com.example.oauth.domain.auth.service.FacebookOauthService
import com.example.oauth.domain.auth.service.GoogleOauthService
import com.example.oauth.domain.auth.service.KakaoOauthService
import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/oauth")
@RestController
class OAuthController(
    private val googleOauthService: GoogleOauthService,
    private val kakaoOauthService: KakaoOauthService,
    private val facebookOauthService: FacebookOauthService
) {
    @GetMapping("/google")
    fun getGoogleCode(): TokenResponse {
        return googleOauthService.getCode()
    }

    @GetMapping("/kakao")
    fun getKakaoCode(): TokenResponse {
        return kakaoOauthService.getCode()
    }

    @GetMapping("/facebook")
    fun getFacebookCode(): TokenResponse {
        return facebookOauthService.getCode()
    }
}