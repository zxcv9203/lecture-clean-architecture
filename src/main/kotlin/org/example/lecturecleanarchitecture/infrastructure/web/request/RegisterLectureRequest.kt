package org.example.lecturecleanarchitecture.infrastructure.web.request

import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType

data class RegisterLectureRequest(
    val userId: Long,
) {
    init {
        require(userId > 0) { throw BusinessException(ErrorType.USER_NOT_FOUND) }
    }
}
