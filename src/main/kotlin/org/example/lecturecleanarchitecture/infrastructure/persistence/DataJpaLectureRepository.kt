package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface DataJpaLectureRepository : JpaRepository<LectureJpaEntity, Long> {
    fun findAllByStartTimeBetween(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
    ): List<LectureJpaEntity>

    @Query("SELECT l FROM LectureJpaEntity l JOIN UserLectureJpaEntity ul on ul.lectureId = l.id WHERE ul.id = :id")
    fun findByUserId(id: Long): List<LectureJpaEntity>
}
