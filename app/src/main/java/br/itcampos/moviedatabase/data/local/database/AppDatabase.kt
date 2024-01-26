package br.itcampos.moviedatabase.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.itcampos.moviedatabase.domain.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}