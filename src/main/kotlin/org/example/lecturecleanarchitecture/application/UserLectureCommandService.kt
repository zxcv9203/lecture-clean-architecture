package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.domain.UserLecture
import org.example.lecturecleanarchitecture.domain.UserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserLectureCommandService(
    private val userLectureRepository: UserLectureRepository,
) {
    @Transactional
    fun register(
        id: Long,
        request: RegisterLectureRequest,
    ) {
        val userLecture =
            UserLecture(
                userId = request.userId,
                lectureId = id,
            )
        userLectureRepository.save(userLecture)
    }
}
