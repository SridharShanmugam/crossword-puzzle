package com.sridhar.crosswordpuzzle.util

import androidx.recyclerview.widget.DiffUtil
import com.sridhar.crosswordpuzzle.vo.Puzzle

class PuzzleDiffCallback(
    private val newPuzzles: List<Puzzle>,
    private val oldPuzzles: List<Puzzle>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldPuzzles.size
    }

    override fun getNewListSize(): Int {
        return newPuzzles.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPuzzles[oldItemPosition].documentId.equals(newPuzzles[newItemPosition].documentId)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPuzzles[oldItemPosition] == newPuzzles[newItemPosition]
    }
}