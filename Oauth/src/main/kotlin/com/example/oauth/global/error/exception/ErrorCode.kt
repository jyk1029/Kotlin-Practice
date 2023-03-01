package com.example.oauth.global.error.exception

import com.example.oauth.global.error.ErrorProperty

enum class ErrorCode(
    override val status: Int,
    override val message: String
) : ErrorProperty {
    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),

    EXPIRED_TOKEN(401, "Expired Token"),
    JWT_SIGNATURE(401, "Invalid Signature"),
    JWT_VALIDATE_FAIL(401, "Token Validate Failed"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),
    FEIGN_UNAUTHORIZED(401, "Feign Unauthorized"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    FEIGN_FORBIDDEN(403, "Feign Forbidden"),

    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),


    FEIGN_EXPIRED_TOKEN(419, "Feign Expired Token"),

    UNEXPECTED_TOKEN(500, "Unexpected Token Exception"),
    INTENAL_SERVER_ERROR(500, "Internal Server Error");
}