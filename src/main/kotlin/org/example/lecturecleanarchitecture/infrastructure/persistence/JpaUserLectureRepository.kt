package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.UserLecture
import org.example.lecturecleanarchitecture.domain.UserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toDomain
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toJpaEntity
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Repository

@Repository
class JpaUserLectureRepository(
    private val dataJpaUserLectureRepository: DataJpaUserLectureRepository,
) : UserLectureRepository {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun save(userLecture: UserLecture): UserLecture =
        try {
            dataJpaUserLectureRepository
                .save(userLecture.toJpaEntity())
                .toDomain()
        } catch (e: DataIntegrityViolationException) {
            log.warn("데이터 무결성 에러가 발생했습니다.", e)
            throw BusinessException(ErrorType.LECTURE_ALREADY_ENROLLED)
        }
}
