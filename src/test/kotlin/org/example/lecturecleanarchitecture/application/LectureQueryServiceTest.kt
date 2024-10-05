package org.example.lecturecleanarchitecture.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class LectureQueryServiceTest {
    @InjectMockKs
    private lateinit var lectureQueryService: LectureQueryService

    @MockK
    private lateinit var lectureRepository: LectureRepository

    @Nested
    @DisplayName("id를 사용해서 강의 조회")
    inner class GetByIdWithLock {
        @Test
        @DisplayName("강의가 존재하는 경우 정상 조회된다.")
        fun lectureExists() {
            val lectureId = 1L
            val want = LectureStub.create(id = lectureId)
            every { lectureRepository.findByIdWithLock(lectureId) } returns want

            val got = lectureQueryService.getByIdWithLock(lectureId)

            assertThat(got).isEqualTo(want)
        }

        @Test
        @DisplayName("강의가 존재하지 않는 경우 예외가 발생한다.")
        fun lectureNotExists() {
            val lectureId = 2L
            every { lectureRepository.findByIdWithLock(lectureId) } returns null

            assertThatThrownBy { lectureQueryService.getByIdWithLock(lectureId) }
                .isInstanceOf(BusinessException::class.java)
                .hasMessage(ErrorType.LECTURE_NOT_FOUND.message)
        }
    }

    @Nested
    @DisplayName("해당하는 날짜에 존재하는 특강 목록을 조회합니다.")
    inner class FindByDate {
        @Test
        @DisplayName("해당하는 날짜에 특강이 존재하는 경우 정상 조회된다.")
        fun lectureExists() {
            val date = LocalDate.of(2021, 1, 1)
            val want = LectureStub.createResponseList(2)

            every {
                lectureRepository.findByDateBetween(
                    any(LocalDateTime::class),
                    any(LocalDateTime::class),
                )
            } returns LectureStub.createList(2)

            val got = lectureQueryService.findByDate(date)

            assertThat(got).isEqualTo(want)
        }
    }

    @Nested
    @DisplayName("사용자 ID를 사용해서 특강 목록을 조회합니다.")
    inner class FindByUserId {
        @Test
        @DisplayName("사용자 ID에 해당하는 특강이 존재하는 경우 정상 조회된다.")
        fun lectureExists() {
            val userId = 1L
            val want = LectureStub.createResponseList(2)

            every { lectureRepository.findByUserId(userId) } returns LectureStub.createList(2)

            val got = lectureQueryService.findByUserId(userId)

            assertThat(got).isEqualTo(want)
        }
    }
}
