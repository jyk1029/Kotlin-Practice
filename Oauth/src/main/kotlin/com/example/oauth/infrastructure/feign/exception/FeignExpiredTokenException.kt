package com.example.oauth.infrastructure.feign.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class FeignExpiredTokenException : OAuthException(ErrorCode.FEIGN_EXPIRED_TOKEN) {
    companion object {
        @JvmField
        val EXCEPTION = FeignExpiredTokenException()
    }
}