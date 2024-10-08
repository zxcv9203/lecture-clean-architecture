package org.example.lecturecleanarchitecture.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.example.lecturecleanarchitecture.stub.UserStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class LectureRegisterApplicationServiceTest {
    @InjectMockKs
    private lateinit var lectureRegisterApplicationService: LectureRegisterApplicationService

    @MockK
    private lateinit var userQueryService: UserQueryService

    @MockK
    private lateinit var userLectureCommandService: UserLectureCommandService

    @MockK
    private lateinit var lectureCommandService: LectureCommandService

    @Nested
    @DisplayName("강의 신청")
    inner class Register {
        @Test
        @DisplayName("사용자와 강의가 존재하는 경우 강의 신청에 성공합니다.")
        fun registerLectureSuccess() {
            val id = 1L
            val request = RegisterLectureRequest(1L)
            every { userQueryService.getById(request.userId) } returns UserStub.create()
            every { lectureCommandService.enroll(id) } returns LectureStub.create(enrollmentCount = 1)
            every { userLectureCommandService.enroll(id, request) } just runs

            lectureRegisterApplicationService.enroll(id, request)

            verify { lectureCommandService.enroll(id) }
            verify { userLectureCommandService.enroll(id, request) }
        }
    }
}
