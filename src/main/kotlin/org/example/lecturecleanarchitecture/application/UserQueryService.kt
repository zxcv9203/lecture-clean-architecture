package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.User
import org.example.lecturecleanarchitecture.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userRepository: UserRepository,
) {
    fun getById(userId: Long): User =
        userRepository.findById(userId)
            ?: throw BusinessException(ErrorType.USER_NOT_FOUND)
}
