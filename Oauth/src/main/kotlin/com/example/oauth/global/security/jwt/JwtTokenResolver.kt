package com.example.oauth.global.security.jwt

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenResolver(
    private val jwtProperties: JwtProperties
) {
    fun resolveToken(httpServletRequest: HttpServletRequest): String? {
        val bearerToken: String? = httpServletRequest.getHeader(jwtProperties.header)
        return parseToken(bearerToken)
    }

    fun parseToken(token: String?): String? {
        return if (token != null && token.startsWith(jwtProperties.prefix)) {
            return token.replace(jwtProperties.prefix, "")
        } else null
    }
}