package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var mInstance: UserDataBase? = null

        fun getDatabase(context: Context): UserDataBase{
            val tempInstance = mInstance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                mInstance = instance
                return instance
            }
        }

    }
}