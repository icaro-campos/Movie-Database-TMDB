package br.itcampos.moviedatabase.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.itcampos.moviedatabase.domain.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}