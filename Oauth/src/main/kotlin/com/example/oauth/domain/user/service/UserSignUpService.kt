package com.example.oauth.domain.user.service

import com.example.oauth.domain.user.domain.User
import com.example.oauth.domain.user.domain.repository.UserRepository
import com.example.oauth.domain.user.domain.type.ProviderType.LOCAL
import com.example.oauth.domain.user.domain.type.Role
import com.example.oauth.domain.user.exception.EmailAlreadyExistsException
import com.example.oauth.domain.user.presentation.dto.request.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserSignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun execute(request: UserSignUpRequest) {

        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException.EXCEPTION
        }

        val user = User(
            id = UUID(0, 0),
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            role = Role.USER,
            providerType = LOCAL
        )

        userRepository.save(user)
    }
}