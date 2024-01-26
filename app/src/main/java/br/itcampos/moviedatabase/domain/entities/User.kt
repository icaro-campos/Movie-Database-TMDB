package br.itcampos.moviedatabase.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userName: String,
    val email: String,
    val password: String
)
