package com.example.oauth.domain.auth.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class RefreshTokenNotFoundException : OAuthException(ErrorCode.REFRESH_TOKEN_NOT_FOUND) {
    companion object {
        @JvmField
        val EXCEPTION = RefreshTokenNotFoundException()
    }
}