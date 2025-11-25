package com.cibermodelo.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deceased(
    val id: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val address: String,
    val eps: String,
    val birthday: String,
    val deceasedDate: String
) : Parcelable
