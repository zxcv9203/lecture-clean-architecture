package org.example.lecturecleanarchitecture.application

import org.assertj.core.api.Assertions.assertThat
import org.example.lecturecleanarchitecture.common.BusinessException
import org.example.lecturecleanarchitecture.domain.LecturePolicy
import org.example.lecturecleanarchitecture.infrastructure.persistence.DataJpaLectureRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.example.lecturecleanarchitecture.stub.LectureStub
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CompletableFuture

@SpringBootTest
@DisplayName("LectureCommandService 통합 테스트")
class IntegrationLectureCommandServiceTest(
    @Autowired
    private val lectureCommandService: LectureCommandService,
    @Autowired
    private val dataJpaLectureRepository: DataJpaLectureRepository,
) {
    @Nested
    @DisplayName("강의 신청")
    inner class Enroll {
        private lateinit var entity: LectureJpaEntity

        @BeforeEach
        fun setUp() {
            entity = dataJpaLectureRepository.save(LectureStub.createJpaEntity())
        }

        @AfterEach
        fun tearDown() {
            dataJpaLectureRepository.delete(entity)
        }

        @Test
        @DisplayName("동시에 40명이 신청했을 때, 30명만 성공합니다.")
        fun enroll() {
            val lectureId = entity.id
            val threadCount = 40
            val futureList =
                (1..threadCount).map {
                    CompletableFuture.supplyAsync {
                        try {
                            lectureCommandService.enroll(lectureId)
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

            assertThat(successCount).isEqualTo(LecturePolicy.MAXIMUM_ENROLLMENT)
            assertThat(failCount).isEqualTo(threadCount - LecturePolicy.MAXIMUM_ENROLLMENT)
        }
    }
}
