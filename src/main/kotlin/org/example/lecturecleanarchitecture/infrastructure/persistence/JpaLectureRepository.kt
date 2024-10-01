package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.converter.toDomain
import org.example.lecturecleanarchitecture.infrastructure.persistence.converter.toJpaEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class JpaLectureRepository(
    private val dataJpaLectureRepository: DataJpaLectureRepository,
) : LectureRepository {
    override fun findById(id: Long): Lecture? =
        dataJpaLectureRepository
            .findByIdOrNull(id)
            ?.toDomain()

    override fun findByDateBetween(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
    ): List<Lecture> =
        dataJpaLectureRepository
            .findAllByStartTimeBetween(startTime, endTime)
            .map { it.toDomain() }

    override fun update(lecture: Lecture): Lecture =
        dataJpaLectureRepository
            .save(lecture.toJpaEntity())
            .toDomain()
}
