package org.example.lecturecleanarchitecture.domain

interface LectureRepository {
    fun findById(id: Long): Lecture?
}
