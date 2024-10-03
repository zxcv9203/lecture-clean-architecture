package org.example.lecturecleanarchitecture.application.integration

import org.assertj.core.api.Assertions.assertThat
import org.example.lecturecleanarchitecture.application.LectureRegisterApplicationService
import org.example.lecturecleanarchitecture.domain.LecturePolicy
import org.example.lecturecleanarchitecture.helper.ConcurrentTestHelper
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaUserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaUserRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserJpaEntity
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.example.lecturecleanarchitecture.stub.UserStub
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class IntegrationLectureRegisterApplicationServiceTest(
    @Autowired
    private val lectureRegisterApplicationService: LectureRegisterApplicationService,
    @Autowired
    private val dataJpaUserRepository: DataJpaUserRepository,
    @Autowired
    private val dataJpaLectureRepository: DataJpaLectureRepository,
    @Autowired
    private val dataJpaUserLectureRepository: DataJpaUserLectureRepository,
) {
    private lateinit var user: UserJpaEntity
    private lateinit var lecture: LectureJpaEntity

    @BeforeEach
    fun setUp() {
        user = dataJpaUserRepository.save(UserStub.createJpaEntity())
        lecture = dataJpaLectureRepository.save(LectureStub.createJpaEntity())
    }

    @AfterEach
    fun tearDown() {
        dataJpaUserLectureRepository.deleteAll()
        dataJpaLectureRepository.deleteAll()
    }

    @Nested
    @DisplayName("강의 신청")
    inner class Register {
        @Test
        @DisplayName("aa")
        fun a() {}

        @Test
        @DisplayName("동일한 사용자가 5번 강의 신청을 하는 경우 1번 성공 후 4번 강의 신청에 실패한다.")
        fun sameUserEnrollFail() {
            val lectureId = lecture.id
            val request = RegisterLectureRequest(userId = user.id)
            val threadCount = 5

            val results =
                ConcurrentTestHelper.executeAsyncTasks(
                    taskCount = threadCount,
                    task = { lectureRegisterApplicationService.enroll(lectureId, request) },
                )
            val successCount = results.count { it }
            val failCount = results.count { !it }

            assertThat(successCount).isEqualTo(1)
            assertThat(failCount).isEqualTo(4)
        }

        @Test
        @DisplayName("동시에 40명이 신청했을 때, 30명만 성공합니다.")
        fun enroll() {
            val lectureId = lecture.id
            val userCount = 40
            val users =
                dataJpaUserRepository.saveAll(
                    (1..userCount)
                        .map { UserStub.createJpaEntity() },
                )

            val results =
                ConcurrentTestHelper.executeAsyncTasksWithId(
                    taskCount = userCount,
                    ids = users.map { it.id },
                    task = { userId -> lectureRegisterApplicationService.enroll(lectureId, RegisterLectureRequest(userId)) },
                )

            val successCount = results.count { it }
            val failCount = results.count { !it }

            assertThat(successCount).isEqualTo(LecturePolicy.MAXIMUM_ENROLLMENT)
            assertThat(failCount).isEqualTo(userCount - LecturePolicy.MAXIMUM_ENROLLMENT)
        }
    }
}
