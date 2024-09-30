package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.springframework.stereotype.Service

@Service
class LectureQueryService(
    private val lectureRepository: LectureRepository,
) {
    fun getById(id: Long): Lecture =
        lectureRepository.findById(id)
            ?: throw BusinessException(ErrorType.LECTURE_NOT_FOUND)
}
