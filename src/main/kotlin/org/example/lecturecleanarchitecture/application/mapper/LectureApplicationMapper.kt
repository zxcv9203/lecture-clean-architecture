package org.example.lecturecleanarchitecture.application.mapper

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.infrastructure.web.response.LectureResponse

fun Lecture.toResponse() =
    LectureResponse(
        id = id,
        name = name,
        startTime = startTime,
        endTime = endTime,
        lecturer = lecturer,
        enrollmentCount = enrollmentCount,
    )
