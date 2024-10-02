package org.example.lecturecleanarchitecture.infrastructure.persistence

import org.example.lecturecleanarchitecture.domain.User
import org.example.lecturecleanarchitecture.domain.UserRepository
import org.example.lecturecleanarchitecture.infrastructure.persistence.mapper.toDomain
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(
    private val dataJpaUserRepository: DataJpaUserRepository,
) : UserRepository {
    override fun findById(id: Long): User? =
        dataJpaUserRepository
            .findByIdOrNull(id)
            ?.toDomain()
}
