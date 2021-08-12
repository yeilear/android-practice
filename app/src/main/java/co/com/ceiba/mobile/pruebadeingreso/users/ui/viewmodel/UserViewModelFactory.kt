package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserListUseCase
import co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase.GetUserPostsListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserViewModelFactory(private val getUserListUseCase: GetUserListUseCase,
                           private val getUserPostsListUseCase: GetUserPostsListUseCase,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(getUserListUseCase::class.java, getUserPostsListUseCase::class.java, CoroutineDispatcher::class.java)
            .newInstance(getUserListUseCase,getUserPostsListUseCase,ioDispatcher)
    }
}