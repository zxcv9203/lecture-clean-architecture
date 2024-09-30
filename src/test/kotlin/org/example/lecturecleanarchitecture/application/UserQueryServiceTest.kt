package org.example.lecturecleanarchitecture.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lecturecleanarchitecture.common.ErrorType
import org.example.lecturecleanarchitecture.domain.UserRepository
import org.example.lecturecleanarchitecture.stub.UserStub
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserQueryServiceTest {
    @InjectMockKs
    private lateinit var userQueryService: UserQueryService

    @MockK
    private lateinit var userRepository: UserRepository

    @Nested
    @DisplayName("id를 사용해서 사용자 조회")
    inner class GetById {
        @Test
        @DisplayName("사용자가 존재하는 경우 정상 조회된다.")
        fun userExists() {
            val userId = 1L
            val want = UserStub.create(userId)
            every { userRepository.findById(userId) } returns want

            val got = userQueryService.getById(userId)

            assertThat(got).isEqualTo(want)
        }

        @Test
        @DisplayName("사용자가 존재하지 않는 경우 예외가 발생한다.")
        fun userNotExists() {
            val userId = 2L
            every { userRepository.findById(userId) } returns null

            assertThatThrownBy { userQueryService.getById(userId) }
                .isInstanceOf(RuntimeException::class.java)
                .hasMessage(ErrorType.USER_NOT_FOUND.message)
        }
    }
}
