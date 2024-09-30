package org.example.lecturecleanarchitecture.stub

import org.example.lecturecleanarchitecture.domain.Lecture
import java.time.LocalDateTime

object LectureStub {
    fun create(id: Long = 1L) =
        Lecture(
            id = id,
            name = "test$id",
            startTime = LocalDateTime.now(),
            endTime = LocalDateTime.now().plusHours(1),
        )
}
