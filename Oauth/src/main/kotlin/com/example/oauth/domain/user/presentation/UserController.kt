package com.example.oauth.domain.user.presentation

import com.example.oauth.domain.user.presentation.dto.request.UserSignUpRequest
import com.example.oauth.domain.user.service.UserSignUpService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/user")
@RestController
class UserController(
    private val userSignUpService: UserSignUpService
) {
    @ResponseStatus(CREATED)
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: UserSignUpRequest) {
        userSignUpService.execute(request)
    }
}