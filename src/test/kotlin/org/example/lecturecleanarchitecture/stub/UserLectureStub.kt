package org.example.lecturecleanarchitecture.stub

import org.example.lecturecleanarchitecture.domain.UserLecture

object UserLectureStub {
    fun create(
        id: Long = 1L,
        userId: Long = 1L,
        lectureId: Long = 1L,
    ) = UserLecture(
        id = id,
        userId = userId,
        lectureId = lectureId,
    )
}
