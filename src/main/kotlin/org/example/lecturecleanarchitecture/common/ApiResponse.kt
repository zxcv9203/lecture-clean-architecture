package org.example.lecturecleanarchitecture.common

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T,
) {
    companion object {
        fun success(type: SuccessType): ApiResponse<Unit> = ApiResponse(type.status, type.message, Unit)

        fun error(errorType: ErrorType): ApiResponse<Unit> = ApiResponse(errorType.status, errorType.message, Unit)
    }
}
