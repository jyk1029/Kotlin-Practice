package com.example.oauth.infrastructure.feign.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class FeignUnAuthorizedException : OAuthException(ErrorCode.FEIGN_UNAUTHORIZED) {
    companion object {
        @JvmField
        val EXCEPTION = FeignUnAuthorizedException()
    }
}