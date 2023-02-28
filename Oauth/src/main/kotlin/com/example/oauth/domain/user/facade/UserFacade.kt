package com.example.oauth.domain.user.facade

import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.domain.repository.UserRepository
import com.example.oauth.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun checkUserExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun getByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }
}