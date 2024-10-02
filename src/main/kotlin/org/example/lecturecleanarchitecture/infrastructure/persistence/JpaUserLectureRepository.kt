package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.domain.UserLecture
import org.example.lecturecleanarchitecture.domain.UserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toDomain
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toJpaEntity
import org.springframework.stereotype.Repository

@Repository
class JpaUserLectureRepository(
    private val dataJpaUserLectureRepository: DataJpaUserLectureRepository,
) : UserLectureRepository {
    override fun save(userLecture: UserLecture): UserLecture =
        dataJpaUserLectureRepository
            .save(userLecture.toJpaEntity())
            .toDomain()
}
