package com.example.oauth.domain.user.service

import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.exception.PasswordMisMatchException
import com.example.oauth.domain.user.facade.UserFacade
import com.example.oauth.domain.user.presentation.dto.request.UserSignInRequest
import com.example.oauth.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun execute(request: UserSignInRequest): TokenResponse {
        val user: User = userFacade.getByEmail(request.email)
        val accessToken = jwtTokenProvider.getToken(request.email)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw PasswordMisMatchException.EXCEPTION
        }

        return accessToken
    }
}