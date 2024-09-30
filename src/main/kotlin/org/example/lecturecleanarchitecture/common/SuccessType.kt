package org.example.lecturecleanarchitecture.common

import org.springframework.http.HttpStatus

enum class SuccessType(
    val status: String,
    val message: String,
) {
    LECTURE_REGISTERED(HttpStatus.OK.value().toString(), "강의 신청이 완료되었습니다."),
}
