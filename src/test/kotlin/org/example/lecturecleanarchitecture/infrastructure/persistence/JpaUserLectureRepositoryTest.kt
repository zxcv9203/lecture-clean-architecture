package org.example.lecturecleanarchitecture.infrastructure.persistence

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserLectureJpaEntity
import org.example.lecturecleanarchitecture.stub.UserLectureStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.dao.DataIntegrityViolationException

@ExtendWith(MockKExtension::class)
class JpaUserLectureRepositoryTest {
    @InjectMockKs
    private lateinit var jpaUserLectureRepository: JpaUserLectureRepository

    @MockK
    private lateinit var dataJpaUserLectureRepository: DataJpaUserLectureRepository

    @Nested
    @DisplayName("사용자의 강의 신청 기록 저장")
    inner class Save {
        @Test
        @DisplayName("데이터 무결성 에러가 발생한 경우 이미 신청한 강의로 판단하여 예외를 발생시킵니다.")
        fun dataIntegrationViolationException() {
            val userLecture = UserLectureStub.create()
            every { dataJpaUserLectureRepository.save(any(UserLectureJpaEntity::class)) } throws DataIntegrityViolationException("")

            assertThatThrownBy { jpaUserLectureRepository.save(userLecture) }
                .isInstanceOf(BusinessException::class.java)
                .hasMessage(ErrorType.LECTURE_ALREADY_ENROLLED.message)
        }
    }
}
