package com.example.oauth.global.security.jwt

import com.example.oauth.domain.auth.domain.RefreshToken
import com.example.oauth.domain.auth.domain.repository.RefreshTokenRepository
import com.example.oauth.domain.auth.presentation.dto.response.TokenResponse
import com.example.oauth.global.exception.ExpiredTokenException
import com.example.oauth.global.exception.JwtValidateException
import com.example.oauth.global.exception.SignatureTokenException
import com.example.oauth.global.exception.UnexpectedTokenException
import com.example.oauth.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    companion object {
        private const val REFRESH_KEY = "refresh"
        private const val ACCESS_KEY = "access"
    }

    fun getToken(email: String): TokenResponse {
        val accessToken: String = generateToken(email, jwtProperties.accessExp, ACCESS_KEY)

        return TokenResponse(accessToken = accessToken)
    }

    fun generateRefreshToken(email: String): String {
        val newRefreshToken: String = generateToken(email, jwtProperties.refreshExp, REFRESH_KEY)
        refreshTokenRepository.save(
            RefreshToken(
                email = (email),
                token = newRefreshToken,
                ttl = jwtProperties.refreshExp
            )
        )
        return newRefreshToken
    }

    private fun generateToken(email: String, expiration: Long, type: String): String {
        return "Bearer " + Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey).setSubject(email)
            .setHeaderParam("type", type).setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000)).compact()
    }

    fun getAuthentication(token: String?): Authentication? {
        return token?.let {
            val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))

            return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        }
    }

    private fun getTokenSubject(subject: String): String {
        return parseTokenBody(subject).subject
    }

    private fun parseTokenBody(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw ExpiredTokenException.EXCEPTION
                is SignatureException -> throw SignatureTokenException.EXCEPTION
                is MalformedJwtException -> throw JwtValidateException.EXCEPTION
                else -> throw UnexpectedTokenException.EXCEPTION
            }
        }
    }
}