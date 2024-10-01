package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.application.mapper.toResponse
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.infrastructure.web.response.LectureResponse
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class LectureQueryService(
    private val lectureRepository: LectureRepository,
) {
    fun getById(id: Long): Lecture =
        lectureRepository.findById(id)
            ?: throw BusinessException(ErrorType.LECTURE_NOT_FOUND)

    fun findByDate(date: LocalDate): List<LectureResponse> {
        val startOfDay = date.atStartOfDay()
        val endOfDay = date.atTime(LocalTime.MAX)

        return lectureRepository
            .findByDateBetween(startOfDay, endOfDay)
            .map { it.toResponse() }
    }
}
