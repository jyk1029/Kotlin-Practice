package com.example.oauth.global.security.auth

import com.example.oauth.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userFacade.getByEmail(username)

        return AuthDetails(user = user)
    }
}