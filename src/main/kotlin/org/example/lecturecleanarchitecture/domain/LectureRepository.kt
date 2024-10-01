package org.example.lecturecleanarchitecture.domain

import java.time.LocalDateTime

interface LectureRepository {
    fun findById(id: Long): Lecture?

    fun findByDateBetween(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
    ): List<Lecture>

    fun update(lecture: Lecture): Lecture
}
