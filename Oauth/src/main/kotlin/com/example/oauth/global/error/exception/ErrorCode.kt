package com.example.oauth.global.error.exception

import com.example.oauth.global.error.ErrorProperty

enum class ErrorCode(
    override val status: Int,
    override val message: String
) : ErrorProperty {
    EXPIRED_TOKEN(401, "Expired Token"),
    JWT_SIGNATURE(401, "Invalid Signature"),
    JWT_VALIDATE_FAIL(401, "Token Validate Failed"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(401, "Refresh Token Not Found"),

    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),

    UNEXPECTED_TOKEN(500, "Unexpected Token Exception"),
    INTENAL_SERVER_ERROR(500, "Internal Server Error");
}