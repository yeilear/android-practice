package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(usersDao: List<UserEntity>)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllUsers(): List<UserEntity>

    @Query("DELETE FROM user")
    suspend fun deleteAllData()
}