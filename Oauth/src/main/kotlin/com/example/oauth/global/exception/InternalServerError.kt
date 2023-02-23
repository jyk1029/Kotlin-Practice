package com.example.oauth.global.exception

import com.example.oauth.global.error.exception.ErrorCode
import com.example.oauth.global.error.exception.OAuthException

object InternalServerError : OAuthException(ErrorCode.INTENAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerError
}