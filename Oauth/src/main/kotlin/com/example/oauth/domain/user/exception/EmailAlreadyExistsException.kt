package com.example.oauth.domain.user.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class EmailAlreadyExistsException : OAuthException(ErrorCode.EMAIL_ALREADY_EXISTS) {
    companion object {
        @JvmField
        val EXCEPTION = EmailAlreadyExistsException()
    }
}