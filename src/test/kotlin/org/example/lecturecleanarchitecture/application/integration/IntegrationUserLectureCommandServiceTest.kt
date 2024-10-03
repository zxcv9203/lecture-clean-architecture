package org.example.lecturecleanarchitecture.application.integration

import org.assertj.core.api.Assertions.assertThat
import org.example.lecturecleanarchitecture.application.UserLectureCommandService
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaUserLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaUserRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserJpaEntity
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.example.lecturecleanarchitecture.stub.UserStub
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CompletableFuture

@SpringBootTest
class IntegrationUserLectureCommandServiceTest(
    @Autowired
    private val userLectureCommandService: UserLectureCommandService,
    @Autowired
    private val dataJpaUserLectureRepository: DataJpaUserLectureRepository,
    @Autowired
    private val dataJpaLectureRepository: DataJpaLectureRepository,
    @Autowired
    private val dataJpaUserRepository: DataJpaUserRepository,
) {
    @Nested
    @DisplayName("강의 신청 기록")
    inner class Save {
        private lateinit var user: UserJpaEntity
        private lateinit var lecture: LectureJpaEntity

        @BeforeEach
        fun setUp() {
            user = dataJpaUserRepository.save(UserStub.createJpaEntity())
            lecture = dataJpaLectureRepository.save(LectureStub.createJpaEntity())
        }

        @BeforeEach
        fun tearDown() {
            dataJpaUserLectureRepository.deleteAll()
            dataJpaLectureRepository.deleteAll()
        }

        @Test
        @DisplayName("동일한 사용자가 5번 강의 신청을 하는 경우 1번 성공 후 4번 강의 신청에 실패한다.")
        fun sameUserEnrollFail() {
            val lectureId = lecture.id
            val request = RegisterLectureRequest(userId = user.id)
            val threadCount = 5
            val futureList =
                (1..threadCount).map {
                    CompletableFuture.supplyAsync {
                        try {
                            userLectureCommandService.enroll(lectureId, request)
                            true
                        } catch (e: BusinessException) {
                            false
                        }
                    }
                }

            CompletableFuture.allOf(*futureList.toTypedArray()).join()
            val results = futureList.map { it.get() }
            val successCount = results.count { it }
            val failCount = results.count { !it }

            assertThat(successCount).isEqualTo(1)
            assertThat(failCount).isEqualTo(4)
        }
    }
}
