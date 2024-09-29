package com.example.fetchtask.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class HiringItem(
    val id: Int,
    val listId: Int,
    val name: String?
) : Parcelable
