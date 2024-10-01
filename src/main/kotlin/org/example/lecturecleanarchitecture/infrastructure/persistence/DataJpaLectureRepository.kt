package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface DataJpaLectureRepository : JpaRepository<LectureJpaEntity, Long> {
    fun findAllByStartTimeBetween(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
    ): List<LectureJpaEntity>
}
