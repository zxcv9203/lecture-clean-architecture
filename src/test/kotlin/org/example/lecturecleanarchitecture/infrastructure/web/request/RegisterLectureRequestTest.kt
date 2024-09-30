package org.example.lecturecleanarchitecture.infrastructure.web.request

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.common.ErrorType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RegisterLectureRequestTest {
    @Nested
    @DisplayName("객체 생성")
    inner class Init {
        @ParameterizedTest
        @ValueSource(longs = [0, -1])
        @DisplayName("userId가 0 이하인 경우 BusinessException 발생")
        fun userIdLessThanZeroThrow(userId: Long) {
            assertThatThrownBy { RegisterLectureRequest(userId) }
                .isInstanceOf(BusinessException::class.java)
                .hasMessage(ErrorType.USER_NOT_FOUND.message)
        }
    }
}
