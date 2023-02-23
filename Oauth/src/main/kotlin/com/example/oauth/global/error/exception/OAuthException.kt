package com.example.oauth.global.error.exception

import com.example.oauth.global.error.ErrorProperty

open class OAuthException(
    private val errorProperty: ErrorProperty
) : RuntimeException() {
    val status: Int
        get() = errorProperty.status

    override val message: String
        get() = errorProperty.message

    override fun fillInStackTrace(): Throwable {
        return this
    }
}