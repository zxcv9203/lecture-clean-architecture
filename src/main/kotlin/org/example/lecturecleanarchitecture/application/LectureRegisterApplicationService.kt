package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureRegisterApplicationService(
    private val userQueryService: UserQueryService,
    private val lectureQueryService: LectureQueryService,
    private val userLectureCommandService: UserLectureCommandService,
) {
    @Transactional
    fun register(
        id: Long,
        request: RegisterLectureRequest,
    ) {
        userQueryService.getById(request.userId)
        lectureQueryService.getById(id)

        userLectureCommandService.register(id, request)
    }
}
