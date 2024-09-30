package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.LectureJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaLectureRepository : JpaRepository<LectureJpaEntity, Long>
