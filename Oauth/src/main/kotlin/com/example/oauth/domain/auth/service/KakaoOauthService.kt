package com.example.oauth.domain.auth.service

import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.domain.repository.UserRepository
import com.example.oauth.domain.user.domain.type.ProviderType.KAKAO
import com.example.oauth.domain.user.domain.type.Role.USER
import com.example.oauth.domain.user.facade.UserFacade
import com.example.oauth.global.security.jwt.JwtTokenProvider
import com.example.oauth.infrastructure.feign.client.KakaoTokenClient
import com.example.oauth.infrastructure.feign.client.KakaoUserInfoClient
import com.example.oauth.infrastructure.feign.dto.response.KakaoUserInfoElement
import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import com.example.oauth.infrastructure.feign.properties.KakaoFeignProperties
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class KakaoOauthService(
    private val kakaoTokenClient: KakaoTokenClient,
    private val kakaoUserInfoClient: KakaoUserInfoClient,
    private val kakaoFeignProperties: KakaoFeignProperties,
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userFacade: UserFacade
) {
    companion object {
        val GRANT_TYPE = "authorization_code"
    }

    fun getCode(): TokenResponse {
        val kakaoToken: String = "Bearer " + kakaoTokenClient.getCode(
            clientId = kakaoFeignProperties.clientId,
            clientSecret = kakaoFeignProperties.clientSecret,
            clientName = kakaoFeignProperties.clientName,
            clientAuthenticationMethod = kakaoFeignProperties.clientAuthenticationMethod,
            authorizationGrantType = GRANT_TYPE,
            redirectUrl = kakaoFeignProperties.redirectUri
        ).accessToken

        val userInfo: KakaoUserInfoElement = kakaoUserInfoClient.getUserInfo(kakaoToken).kakaoResponse

        var user: User? = userFacade.getByEmail(userInfo.accountEmail)

        if (user == null) {
            user = User(
                id = UUID(0, 0),
                email = userInfo.accountEmail,
                password = passwordEncoder.encode(userInfo.password),
                name = userInfo.profileNickname,
                role = USER,
                providerType = KAKAO
            )

            userRepository.save(user)
        }

        return jwtTokenProvider.getToken(user.email)
    }
}