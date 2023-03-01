package com.example.oauth.infrastructure.feign.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class FeignForbiddenException : OAuthException(ErrorCode.FEIGN_FORBIDDEN) {
    companion object {
        @JvmField
        val EXCEPTION = FeignForbiddenException()
    }
}