package com.sridhar.crosswordpuzzle.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cell(val chars: String = "", val attrFlags: Long = 0) : Parcelable