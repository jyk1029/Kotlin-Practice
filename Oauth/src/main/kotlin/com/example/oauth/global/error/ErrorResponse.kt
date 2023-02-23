package com.example.oauth.global.error

import com.example.oauth.global.error.exception.OAuthException

class ErrorResponse<T>(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(e: OAuthException): ErrorResponse<Unit> {
            return ErrorResponse(
                status = e.status,
                message = e.message
            )
        }
    }
}