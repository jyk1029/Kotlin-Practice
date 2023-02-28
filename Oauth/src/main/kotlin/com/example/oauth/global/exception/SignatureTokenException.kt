package com.example.oauth.global.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class SignatureTokenException : OAuthException(ErrorCode.JWT_SIGNATURE) {
    companion object {
        @JvmField
        val EXCEPTION = SignatureTokenException()
    }
}