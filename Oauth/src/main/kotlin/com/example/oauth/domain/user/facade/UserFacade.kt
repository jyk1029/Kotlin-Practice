package com.example.oauth.domain.user.facade

import com.example.oauth.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun checkUserExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}