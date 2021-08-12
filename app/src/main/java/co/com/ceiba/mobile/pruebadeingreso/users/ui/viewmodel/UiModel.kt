package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

sealed class UiModel{
    data class Loading(val visible: Boolean) : UiModel()
    data class UserList(val list: List<UserEntity>) : UiModel()
    data class UserPostList(val list: List<UserPostResponse>) : UiModel()
    data class EmptyList(val visible: Boolean) : UiModel()
    data class Message(val message: String): UiModel()
}