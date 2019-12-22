package com.sridhar.crosswordpuzzle.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(
    val number: Int = 0,
    val hint: String? = null,
    val startRow: Int = 0,
    val startColumn: Int = 0,
    val direction: Int = 0,
    val hintUrl: String? = null,
    val citation: String? = null,
    val cells: List<Cell>? = null
) : Parcelable