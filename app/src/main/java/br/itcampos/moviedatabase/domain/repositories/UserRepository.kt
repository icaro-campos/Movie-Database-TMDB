package br.itcampos.moviedatabase.domain.repositories

import br.itcampos.moviedatabase.domain.entities.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}