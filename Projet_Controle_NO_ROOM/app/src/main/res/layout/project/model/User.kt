package ma.ensa.project.model

data class User(
    val id: Long = 0,
    val email: String,
    val username: String,
    val password: String,
    val createdAt: Long = System.currentTimeMillis()
)