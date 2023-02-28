package com.example.oauth.domain.user.domain.repository

import com.example.oauth.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun existsByEmail(email: String): Boolean
}