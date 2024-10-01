package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureRegisterApplicationService(
    private val userQueryService: UserQueryService,
    private val userLectureCommandService: UserLectureCommandService,
    private val lectureCommandService: LectureCommandService,
) {
    @Transactional
    fun enroll(
        id: Long,
        request: RegisterLectureRequest,
    ) {
        userQueryService.getById(request.userId)
        lectureCommandService.enroll(id)
        userLectureCommandService.enroll(id, request)
    }
}
