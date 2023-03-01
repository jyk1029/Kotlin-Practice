package com.example.oauth.domain.auth.service

import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.domain.repository.UserRepository
import com.example.oauth.domain.user.domain.type.ProviderType.GOOGLE
import com.example.oauth.domain.user.domain.type.Role.USER
import com.example.oauth.domain.user.facade.UserFacade
import com.example.oauth.global.security.jwt.JwtTokenProvider
import com.example.oauth.infrastructure.feign.client.GoogleTokenClient
import com.example.oauth.infrastructure.feign.client.GoogleUserInfoClient
import com.example.oauth.infrastructure.feign.dto.response.GoogleUserInfoElement
import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import com.example.oauth.infrastructure.feign.properties.GoogleFeignProperties
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class GoogleOauthService(
    private val googleTokenClient: GoogleTokenClient,
    private val googleUserInfoClient: GoogleUserInfoClient,
    private val googleFeignProperties: GoogleFeignProperties,
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val userFacade: UserFacade
) {
    companion object {
        const val GRANT_TYPE = "authorization_code"
    }

    fun getCode(): TokenResponse {
        val googleToken: String = "Bearer " + googleTokenClient.getCode(
            grantType = GRANT_TYPE,
            clientId = googleFeignProperties.clientId,
            clientSecret = googleFeignProperties.clientSecret
        ).accessToken

        val userInfo: GoogleUserInfoElement = googleUserInfoClient.getUserInfo(googleToken).response

        var user: User? = userFacade.getByEmail(userInfo.email)

        if (user == null) {
            user = User(
                id = UUID(0, 0),
                email = userInfo.email,
                password = passwordEncoder.encode(userInfo.password),
                name = userInfo.name,
                role = USER,
                providerType = GOOGLE,
            )
            userRepository.save(user)
        }

        return jwtTokenProvider.getToken(user.email)
    }
}