package com.example.oauth.domain.user.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class PasswordMisMatchException : OAuthException(ErrorCode.PASSWORD_MISMATCH) {
    companion object {
        @JvmField
        val EXCEPTION = PasswordMisMatchException()
    }
}