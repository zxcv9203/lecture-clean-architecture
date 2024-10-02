package org.example.lecturecleanarchitecture.common

class BusinessException(
    val errorType: ErrorType,
    cause: Throwable? = null,
) : RuntimeException(
        errorType.message,
        cause,
    )
