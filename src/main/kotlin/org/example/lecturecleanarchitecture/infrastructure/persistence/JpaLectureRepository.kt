package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.converter.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaLectureRepository(
    private val dataJpaLectureRepository: DataJpaLectureRepository,
) : LectureRepository {
    override fun findById(id: Long): Lecture? =
        dataJpaLectureRepository
            .findByIdOrNull(id)
            ?.toDomain()
}
