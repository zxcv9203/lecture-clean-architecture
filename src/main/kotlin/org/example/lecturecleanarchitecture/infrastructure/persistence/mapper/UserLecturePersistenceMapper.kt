package org.example.lecturecleanarchitecture.infrastructure.persistence.mapper

import org.example.lecturecleanarchitecture.domain.UserLecture
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserLectureJpaEntity

fun UserLecture.toJpaEntity() =
    UserLectureJpaEntity(
        id = id,
        userId = userId,
        lectureId = lectureId,
    )

fun UserLectureJpaEntity.toDomain() =
    UserLecture(
        id = id,
        userId = userId,
        lectureId = lectureId,
    )
