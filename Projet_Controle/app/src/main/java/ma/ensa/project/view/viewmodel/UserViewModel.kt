package ma.ensa.project.view.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ma.ensa.project.database.UserEntity
import ma.ensa.project.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _registrationResult = MutableStateFlow<Result<UserEntity>?>(null)
    val registrationResult: StateFlow<Result<UserEntity>?> get() = _registrationResult

    private val _loginResult = MutableStateFlow<Result<UserEntity>?>(null)
    val loginResult: StateFlow<Result<UserEntity>?> get() = _loginResult

    fun registerUser(email: String, username: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.registerUser(email, username, password)
            _registrationResult.value = result
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.loginUser(email, password)
            _loginResult.value = result
        }
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userRepository.getAllUsers()

    fun isEmailTaken(email: String): Flow<Boolean> = flow {
        emit(userRepository.isEmailTaken(email))
    }
}
