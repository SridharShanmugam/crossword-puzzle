package com.sridhar.crosswordpuzzle.ui.puzzle

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sridhar.crosswordpuzzle.R
import com.sridhar.crosswordpuzzle.databinding.ActivityPuzzleBinding
import kotlinx.android.synthetic.main.activity_puzzle.*
import org.akop.ararat.core.Crossword
import org.akop.ararat.view.CrosswordView

class PuzzleActivity : AppCompatActivity(), CrosswordView.OnLongPressListener,
    CrosswordView.OnStateChangeListener, CrosswordView.OnSelectionChangeListener {

    private lateinit var puzzleBinding: ActivityPuzzleBinding
    private lateinit var puzzleViewModel: PuzzleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        puzzleBinding = DataBindingUtil.setContentView(this, R.layout.activity_puzzle)
        puzzleBinding.lifecycleOwner = this

        init()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        puzzleViewModel.crosswordState?.let { puzzleGridCv.restoreState(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        puzzleViewModel.crosswordState = puzzleGridCv.state
    }

    private fun init() {
        puzzleViewModel = ViewModelProviders.of(this).get(PuzzleViewModel::class.java)
        puzzleViewModel.puzzle = intent?.getParcelableExtra("puzzle")!!
        puzzleViewModel.crossword.observe(this, Observer {
            initView()
        })
        puzzleBinding.puzzleViewModel = puzzleViewModel

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(
            R.string.title_by_author,
            puzzleViewModel.puzzle.title, puzzleViewModel.puzzle.author
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        puzzleGridCv.let {
            it.crossword = puzzleViewModel.crossword.value
            it.onLongPressListener = this
            it.onStateChangeListener = this
            it.onSelectionChangeListener = this
            it.inputValidator = { ch -> !ch.first().isISOControl() }
            it.undoMode = CrosswordView.UNDO_NONE
            it.markerDisplayMode = CrosswordView.MARKER_CHEAT
            onSelectionChanged(it, it.selectedWord, it.selectedCell)
        }
    }

    override fun onCellLongPressed(view: CrosswordView, word: Crossword.Word, cell: Int) {}

    override fun onCrosswordChanged(view: CrosswordView) {}

    override fun onCrosswordSolved(view: CrosswordView) {
        Toast.makeText(
            applicationContext,
            getString(R.string.you_solved_puzzle),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCrosswordUnsolved(view: CrosswordView) {}

    override fun onSelectionChanged(view: CrosswordView, word: Crossword.Word?, position: Int) {
        puzzleViewModel.setHint(
            when (word?.direction) {
                Crossword.Word.DIR_ACROSS -> getString(R.string.across, word.number, word.hint)
                Crossword.Word.DIR_DOWN -> getString(R.string.down, word.number, word.hint)
                else -> ""
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_puzzle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_solve_square -> {
                puzzleGridCv.solveChar(puzzleGridCv.selectedWord!!, puzzleGridCv.selectedCell)
                true
            }
            R.id.menu_solve_word -> {
                puzzleGridCv.solveWord(puzzleGridCv.selectedWord!!)
                true
            }
            R.id.menu_solve_puzzle -> {
                puzzleGridCv.solveCrossword()
                true
            }
            R.id.menu_restart -> {
                puzzleGridCv.reset()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
