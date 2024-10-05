package org.example.lecturecleanarchitecture.application

import org.example.lecturecleanarchitecture.domain.Lecture
import org.example.lecturecleanarchitecture.domain.LectureRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureCommandService(
    private val lectureQueryService: LectureQueryService,
    private val lectureRepository: LectureRepository,
) {
    @Transactional
    fun enroll(id: Long): Lecture {
        val lecture = lectureQueryService.getByIdWithLock(id)
        lecture.enroll()

        return lectureRepository.update(lecture)
    }
}
