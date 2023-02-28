package com.example.oauth.global.error.exception

import com.example.oauth.global.error.ErrorProperty

enum class ErrorCode(
    override val status: Int,
    override val message: String
) : ErrorProperty {
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),
    INTENAL_SERVER_ERROR(500, "Internal Server Error");
}