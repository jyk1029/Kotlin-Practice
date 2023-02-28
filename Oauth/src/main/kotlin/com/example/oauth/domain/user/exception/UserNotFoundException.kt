package com.example.oauth.domain.user.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

class UserNotFoundException : OAuthException(ErrorCode.USER_NOT_FOUND) {
    companion object {
        @JvmField
        val EXCEPTION = UserNotFoundException()
    }
}