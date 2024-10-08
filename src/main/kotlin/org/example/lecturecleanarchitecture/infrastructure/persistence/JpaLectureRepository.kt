package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toDomain
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toJpaEntity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class JpaLectureRepository(
    private val dataJpaLectureRepository: DataJpaLectureRepository,
) : LectureRepository {
    override fun findByIdWithLock(id: Long): Lecture? =
        dataJpaLectureRepository
            .findByIdOrNullWithLock(id)
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

    override fun findByUserId(id: Long): List<Lecture> =
        dataJpaLectureRepository
            .findByUserId(id)
            .map { it.toDomain() }
}
