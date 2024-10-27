package ma.ensa.project.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import ma.ensa.project.utils.DateConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@TypeConverters(DateConverter::class)
@Entity(tableName = "users")
data class UserEntity @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date()
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formattedCreatedAt(): String {
        val localDateTime = LocalDateTime.ofInstant(
            createdAt.toInstant(),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }
}