package org.example.lecturecleanarchitecture.domain

import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import java.time.LocalDateTime

class Lecture(
    val name: String,
    val lecturer: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    var enrollmentCount: Int = 0,
    val id: Long = 0,
) {
    fun enroll() {
        require(enrollmentCount < LecturePolicy.MAXIMUM_ENROLLMENT) { throw BusinessException(ErrorType.LECTURE_ENROLLMENT_FULL) }
        enrollmentCount++
    }
}
