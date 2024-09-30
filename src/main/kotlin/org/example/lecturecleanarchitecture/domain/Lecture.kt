package org.example.lecturecleanarchitecture.domain

import java.time.LocalDateTime

class Lecture(
    val name: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val id: Long = 0,
)
