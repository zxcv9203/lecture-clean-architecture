package org.example.lecturecleanarchitecture.domain

interface UserRepository {
    fun findById(id: Long): User?
}
