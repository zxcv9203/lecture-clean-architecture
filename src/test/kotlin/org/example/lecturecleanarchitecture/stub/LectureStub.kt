package org.example.lecturecleanarchitecture.stub

import org.example.lecturecleanarchitecture.application.mapper.toResponse
import org.example.lecturecleanarchitecture.domain.Lecture
import java.time.LocalDateTime

object LectureStub {
    fun create(
        enrollmentCount: Long = 0,
        id: Long = 1L,
    ) = Lecture(
        id = id,
        lecturer = "lecturer$id",
        name = "test$id",
        enrollmentCount = 0,
        startTime = LocalDateTime.of(2021, 1, 1, 0, 0),
        endTime = LocalDateTime.of(2021, 1, 1, 1, 0),
    )

    fun createList(size: Int) = (1..size).map { create(it.toLong()) }

    fun createResponseList(size: Int) = (1..size).map { create(it.toLong()).toResponse() }
}
