package com.example.oauth.domain.auth.service

import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.domain.repository.UserRepository
import com.example.oauth.domain.user.domain.type.ProviderType.FACEBOOK
import com.example.oauth.domain.user.domain.type.Role.USER
import com.example.oauth.global.security.jwt.JwtTokenProvider
import com.example.oauth.infrastructure.feign.client.FacebookTokenClient
import com.example.oauth.infrastructure.feign.client.FacebookUserInfoClient
import com.example.oauth.infrastructure.feign.dto.response.FacebookUserInfoElement
import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import com.example.oauth.infrastructure.feign.properties.FacebookFeignProperties
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class FacebookOauthService(
    private val facebookTokenClient: FacebookTokenClient,
    private val facebookUserInfoClient: FacebookUserInfoClient,
    private val facebookFeignProperties: FacebookFeignProperties,
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    companion object {
        val GRANT_TYPE = "authorization_code"
    }

    fun getCode(): TokenResponse {
        val facebookToken: String = "Bearer " + facebookTokenClient.getCode(
            grantType = GRANT_TYPE,
            clientId = facebookFeignProperties.clientId,
            clientSecret = facebookFeignProperties.clientSecret
        ).accessToken

        val userInfo: FacebookUserInfoElement = facebookUserInfoClient.getUserInfo(facebookToken).facebookResponse

        var user: User? = userRepository.findByEmail(userInfo.email)

        if (user == null) {
            user = User(
                id = UUID(0, 0),
                email = userInfo.email,
                password = passwordEncoder.encode(userInfo.password),
                name = userInfo.name,
                role = USER,
                providerType = FACEBOOK
            )

            userRepository.save(user)
        }

        return jwtTokenProvider.getToken(user.email)
    }
}