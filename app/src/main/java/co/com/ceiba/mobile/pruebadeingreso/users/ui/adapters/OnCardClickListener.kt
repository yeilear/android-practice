package co.com.ceiba.mobile.pruebadeingreso.users.ui.adapters

import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity

interface OnCardClickListener {
    fun onClick(item: UserEntity)
}