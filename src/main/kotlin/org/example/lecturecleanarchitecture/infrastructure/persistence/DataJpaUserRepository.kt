package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.infrastructure.persistence.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DataJpaUserRepository : JpaRepository<UserJpaEntity, Long>
