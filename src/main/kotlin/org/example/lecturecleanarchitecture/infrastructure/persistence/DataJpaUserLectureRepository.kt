package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserLectureJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaUserLectureRepository : JpaRepository<UserLectureJpaEntity, Long>
