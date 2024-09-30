package org.example.lecturecleanarchitecture.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.lecturecleanarchitecture.domain.UserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.example.lecturecleanarchitecture.stub.UserLectureStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserLectureCommandServiceTest {
    @InjectMockKs
    private lateinit var userLectureCommandService: UserLectureCommandService

    @MockK
    private lateinit var userLectureRepository: UserLectureRepository

    @Nested
    @DisplayName("사용자 강의 신청")
    inner class Register {
        @Test
        @DisplayName("사용자가 강의 신청을 할 수 있다.")
        fun userCanRegisterLecture() {
            val lectureId = 1L
            val request = RegisterLectureRequest(1L)
            val userLecture = UserLectureStub.create()

            every { userLectureRepository.save(any()) } returns userLecture

            userLectureCommandService.register(lectureId, request)

            verify { userLectureRepository.save(any()) }
        }
    }
}
