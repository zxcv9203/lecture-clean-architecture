package org.example.lecturecleanarchitecture.domain

interface UserLectureRepository {
    fun save(userLecture: UserLecture): UserLecture
}
