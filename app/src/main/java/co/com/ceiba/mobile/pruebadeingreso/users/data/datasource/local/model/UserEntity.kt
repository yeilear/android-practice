package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val phone: String
)