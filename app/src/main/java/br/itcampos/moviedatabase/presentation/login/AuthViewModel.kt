package br.itcampos.moviedatabase.presentation.login

import androidx.lifecycle.ViewModel
import br.itcampos.moviedatabase.domain.entities.User
import br.itcampos.moviedatabase.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    suspend fun registerUser(username: String, email: String, password: String) {
        val newUser = User(userName = username, email = email, password = password)
        userRepository.insertUser(newUser)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}