package com.sridhar.crosswordpuzzle.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Puzzle(
    var documentId: String? = null,
    val title: String? = null,
    val author: String? = null,
    val description: String? = null,
    val copyright: String? = null,
    val comment: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val squareCount: Int = 0,
    val flags: Int = 0,
    val date: Long = 0,
    val wordsAcross: ArrayList<Word>? = null,
    val wordsDown: ArrayList<Word>? = null,
    val alphabet: String? = null
) : Parcelable