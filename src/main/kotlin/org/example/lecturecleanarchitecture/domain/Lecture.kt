package org.example.lecturecleanarchitecture.domain

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
        enrollmentCount++
    }
}
