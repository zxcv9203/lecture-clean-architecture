package org.example.lecturecleanarchitecture.infrastructure.web.response

import java.time.LocalDateTime

data class LectureResponse(
    val id: Long,
    val name: String,
    val lecturer: String,
    val enrollmentCount: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)
