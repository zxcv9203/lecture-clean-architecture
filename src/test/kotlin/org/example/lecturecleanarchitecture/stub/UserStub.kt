package org.example.lecturecleanarchitecture.stub

import org.example.lecturecleanarchitecture.domain.User
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserJpaEntity

object UserStub {
    fun create(id: Long = 1L) =
        User(
            id = id,
            name = "test$id",
        )

    fun createJpaEntity(): UserJpaEntity =
        UserJpaEntity(
            name = "test",
        )
}
