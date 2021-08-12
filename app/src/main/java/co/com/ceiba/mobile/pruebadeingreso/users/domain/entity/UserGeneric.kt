package co.com.ceiba.mobile.pruebadeingreso.users.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserGeneric(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
) : Parcelable