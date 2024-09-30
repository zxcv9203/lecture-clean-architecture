package org.example.lecturecleanarchitecture.infrastructure.persistence.converter

import org.example.lecturecleanarchitecture.domain.User
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserJpaEntity

fun UserJpaEntity.toDomain() =
    User(
        id = id,
        name = name,
    )

fun User.toJpaEntity() =
    UserJpaEntity(
        id = id,
        name = name,
    )
