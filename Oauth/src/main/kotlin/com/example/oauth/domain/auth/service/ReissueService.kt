package com.example.oauth.domain.auth.service

import com.example.oauth.domain.auth.domain.RefreshToken
import com.example.oauth.domain.auth.domain.repository.RefreshTokenRepository
import com.example.oauth.domain.auth.exception.RefreshTokenNotFoundException
import com.example.oauth.infrastructure.feign.dto.response.TokenResponse
import com.example.oauth.global.security.jwt.JwtProperties
import com.example.oauth.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReissueService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties
) {
    @Transactional
    fun execute(refreshToken: String): TokenResponse {
        val redisRefreshToken: RefreshToken = refreshTokenRepository.findByToken(refreshToken) ?: throw RefreshTokenNotFoundException.EXCEPTION
        val newRefreshToken: String = jwtTokenProvider.generateRefreshToken(redisRefreshToken.email)
        val newToken = jwtTokenProvider.getToken(redisRefreshToken.email)

        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.refreshExp)
        refreshTokenRepository.save(redisRefreshToken)

        return newToken
    }
}