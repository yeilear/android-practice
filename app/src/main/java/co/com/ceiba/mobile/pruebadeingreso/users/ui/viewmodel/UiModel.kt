package co.com.ceiba.mobile.pruebadeingreso.users.ui.viewmodel

import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

sealed class UiModel{
    data class Loading(val visible: Boolean) : UiModel()
    data class UserList(val list: List<UserListResponse>) : UiModel()
    data class UserPostList(val list: List<UserPostResponse>) : UiModel()
    data class Message(val message: String): UiModel()
}