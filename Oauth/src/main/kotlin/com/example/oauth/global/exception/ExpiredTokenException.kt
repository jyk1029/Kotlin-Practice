package com.example.oauth.global.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class ExpiredTokenException : OAuthException(ErrorCode.EXPIRED_TOKEN) {
    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}