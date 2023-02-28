package com.example.oauth.global.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class JwtValidateException : OAuthException(ErrorCode.JWT_VALIDATE_FAIL) {
    companion object {
        @JvmField
        val EXCEPTION = JwtValidateException()
    }
}