package org.example.lecturecleanarchitecture.domain

import java.time.LocalDateTime

class Lecture(
    val name: String,
    val lecturer: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val enrollmentCount: Int = 0,
    val id: Long = 0,
)
