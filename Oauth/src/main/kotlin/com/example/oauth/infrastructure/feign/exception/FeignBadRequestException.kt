package com.example.oauth.infrastructure.feign.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class FeignBadRequestException:OAuthException(ErrorCode.FEIGN_BAD_REQUEST) {
    companion object {
        @JvmField
        val EXCEPTION = FeignBadRequestException()
    }
}