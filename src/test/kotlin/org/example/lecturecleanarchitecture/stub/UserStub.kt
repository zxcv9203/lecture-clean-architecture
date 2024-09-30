package org.example.lecturecleanarchitecture.stub

import org.example.lecturecleanarchitecture.domain.User

object UserStub {
    fun create(id: Long = 1L) =
        User(
            id = id,
            name = "test$id",
        )
}
