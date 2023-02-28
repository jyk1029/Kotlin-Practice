package com.example.oauth.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank

data class UserSignUpRequest(
    @field:NotBlank
    var email: String,

    @field:NotBlank
    var password: String,

    @field:NotBlank
    var name: String
)