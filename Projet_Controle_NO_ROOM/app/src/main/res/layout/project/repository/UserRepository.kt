package ma.ensa.project.repository

import ma.ensa.project.database.UserDAO
import ma.ensa.project.database.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDAO) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDAO): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
        }
    }

    suspend fun registerUser(email: String, username: String, password: String): Result<UserEntity> {
        return withContext(Dispatchers.IO) {
            try {
                if (userDao.isEmailExists(email)) {
                    return@withContext Result.failure(Exception("Email already exists"))
                }

                val user = UserEntity(
                    email = email,
                    username = username,
                    password = password
                )
                val userId = userDao.addUser(user)
                Result.success(user.copy(id = userId))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loginUser(email: String, password: String): Result<UserEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.login(email, password)
                user?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Invalid credentials"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    suspend fun getUserByEmail(email: String): Result<UserEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserByEmail(email)
                user?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("User not found"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    suspend fun updateUser(user: UserEntity): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                userDao.updateUser(user)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun deleteUser(user: UserEntity): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                userDao.deleteUser(user)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    suspend fun isEmailTaken(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.isEmailExists(email)
        }
    }
}