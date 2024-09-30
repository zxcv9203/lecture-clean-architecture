package org.example.lecturecleanarchitecture.infrastructure.persistence.converter

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity

fun LectureJpaEntity.toDomain() =
    Lecture(
        id = id,
        name = name,
        startTime = startTime,
        endTime = endTime,
    )

fun Lecture.toJpaEntity() =
    LectureJpaEntity(
        id = id,
        name = name,
        startTime = startTime,
        endTime = endTime,
    )
