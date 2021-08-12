package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local

import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity

class RoomUserLocal(private val _database: UserDataBase): UserDao {
    override suspend fun addUser(usersDao: List<UserEntity>) {
        deleteAllData()
        _database.userDao().addUser(usersDao)
    }

    override fun readAllUsers(): List<UserEntity> = _database.userDao().readAllUsers()

    override suspend fun deleteAllData() = _database.userDao().deleteAllData()

}