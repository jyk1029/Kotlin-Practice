package com.example.oauth.domain.user.domain

import com.example.oauth.domain.user.domain.type.ProviderType
import com.example.oauth.domain.user.domain.type.Role
import com.example.oauth.global.entity.BaseUUIDEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.validator.constraints.Length
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotNull


@Entity
@DynamicInsert
class User(
    override val id: UUID,

    @field:NotNull
    @field:Length(max = 64)
    val email: String,

    @field:NotNull
    @field:Length(max = 60)
    val password: String,

    @field:NotNull
    @field:Length(max = 5)
    val name: String,

    role: Role,

    providerType: ProviderType

) : BaseUUIDEntity() {
    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    var role = role
        protected set

    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    var providerType = providerType
        protected set
}