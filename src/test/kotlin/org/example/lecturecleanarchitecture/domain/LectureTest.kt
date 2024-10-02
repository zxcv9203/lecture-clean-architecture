package org.example.lecturecleanarchitecture.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LectureTest {
    @Nested
    @DisplayName("강의 신청")
    inner class Enroll {
        @Test
        @DisplayName("강의를 신청하면 인원이 1명 증가한다.")
        fun success() {
            val want = 1L
            val lecture = LectureStub.create()

            lecture.enroll()
            val got = lecture.enrollmentCount

            assertThat(got).isEqualTo(want)
        }

        @Test
        @DisplayName("최대 인원을 초과하면 예외가 발생한다.")
        fun fail() {
            val lecture = LectureStub.create(enrollmentCount = LecturePolicy.MAXIMUM_ENROLLMENT)

            assertThatThrownBy { lecture.enroll() }
                .isInstanceOf(BusinessException::class.java)
                .hasMessageContaining(ErrorType.LECTURE_ENROLLMENT_FULL.message)
        }
    }
}
