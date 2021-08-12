package co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters

import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse

interface OnCardClickListener {
    fun onClick(item: UserListResponse)
}