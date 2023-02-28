package com.example.oauth.domain.auth.presentation

import com.example.oauth.domain.auth.presentation.dto.response.TokenResponse
import com.example.oauth.domain.auth.service.ReissueService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val reissueService: ReissueService
) {
    @PatchMapping
    fun reissueToken(@RequestHeader("refresh-token") refreshToken: String): TokenResponse {
        return reissueService.execute(refreshToken)
    }
}