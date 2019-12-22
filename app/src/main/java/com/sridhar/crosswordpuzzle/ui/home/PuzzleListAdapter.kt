package com.sridhar.crosswordpuzzle.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sridhar.crosswordpuzzle.BR
import com.sridhar.crosswordpuzzle.R
import com.sridhar.crosswordpuzzle.databinding.LayoutPuzzleListItemBinding
import com.sridhar.crosswordpuzzle.ui.puzzle.PuzzleActivity
import com.sridhar.crosswordpuzzle.util.PuzzleDiffCallback
import com.sridhar.crosswordpuzzle.vo.Puzzle


class PuzzleListAdapter : RecyclerView.Adapter<PuzzleListAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var puzzles: MutableList<Puzzle> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            DataBindingUtil.inflate<LayoutPuzzleListItemBinding>(
                LayoutInflater.from(context),
                R.layout.layout_puzzle_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return puzzles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    internal fun setPuzzles(newPuzzles: List<Puzzle>) {
        val diffResult = DiffUtil.calculateDiff(
            PuzzleDiffCallback(
                newPuzzles,
                puzzles
            )
        )
        puzzles.clear()
        puzzles.addAll(newPuzzles)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getPuzzle(position: Int): Puzzle? {
        return puzzles[position]
    }

    fun onClick(view: View, position: Int) {
        val intent = Intent(context.applicationContext, PuzzleActivity::class.java).apply {
            putExtra("puzzle", puzzles.get(position))
        }
        context.startActivity(intent)
    }

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.setVariable(BR.puzzleListAdapter, this@PuzzleListAdapter)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}