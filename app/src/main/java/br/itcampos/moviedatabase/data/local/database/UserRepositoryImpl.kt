package br.itcampos.moviedatabase.data.local.database

import br.itcampos.moviedatabase.domain.entities.User
import br.itcampos.moviedatabase.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return userDao.getUserByEmailAndPassword(email, password)
    }
}