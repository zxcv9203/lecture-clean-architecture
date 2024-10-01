package org.example.lecturecleanarchitecture.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class LectureCommandServiceTest {
    @InjectMockKs
    private lateinit var lectureCommandService: LectureCommandService

    @MockK
    private lateinit var lectureQueryService: LectureQueryService

    @MockK
    private lateinit var lectureRepository: LectureRepository

    @Nested
    @DisplayName("강의 신청")
    inner class Enroll {
        @Test
        @DisplayName("강의 ID를 전달하면 강의 신청에 성공한다.")
        fun enrollLectureSuccess() {
            val id = 1L
            val lecture = LectureStub.create()
            val want = LectureStub.create(enrollmentCount = 1)
            every { lectureQueryService.getById(id) } returns lecture
            every { lectureRepository.update(lecture) } returns want

            val got = lectureCommandService.enroll(id)

            assertThat(got).isEqualTo(want)
        }
    }
}
