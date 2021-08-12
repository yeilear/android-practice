package co.com.ceiba.mobile.pruebadeingreso.users.ui.di

import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.common.URL_BASE
import co.com.ceiba.mobile.pruebadeingreso.common.network.NetworkManagerStateImpl
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.RoomUserLocal
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.UserDataBase
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.RetrofitUserFactory
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.UserApiImpl
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.RepositoryUserImpl
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserPostsListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsersModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideUserViewModelFactory() = UserViewModelFactory(
        GetUserListUseCase(provideRepositoryUserImpl()),
        GetUserPostsListUseCase(provideRepositoryUserImpl())
    )

    @Provides
    @Singleton
    fun provideRepositoryUserImpl() = RepositoryUserImpl(RoomUserLocal(UserDataBase.getDatabase(context)),
        UserApiImpl(RetrofitUserFactory.userApiRetrofit(URL_BASE)),NetworkManagerStateImpl(context))
}