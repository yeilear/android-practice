package co.com.ceiba.mobile.pruebadeingreso.users.ui.di

import co.com.ceiba.mobile.pruebadeingreso.users.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.users.PostActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UsersModule::class])
interface UsersComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(postActivity: PostActivity)
}