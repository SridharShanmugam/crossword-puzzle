package com.sridhar.crosswordpuzzle.ui.puzzle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sridhar.crosswordpuzzle.vo.Puzzle
import com.sridhar.crosswordpuzzle.vo.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.akop.ararat.core.Crossword
import org.akop.ararat.core.CrosswordState

class PuzzleViewModel : ViewModel() {

    internal lateinit var puzzle: Puzzle
    internal var crosswordState: CrosswordState? = null

    private val _hint = MutableLiveData<String>()
    val hint: LiveData<String> = _hint

    internal val crossword: LiveData<Crossword> = liveData(Dispatchers.Default) {
        val c = Crossword(
            width = puzzle.width,
            height = puzzle.height,
            squareCount = puzzle.squareCount,
            flags = puzzle.flags,
            title = puzzle.title,
            description = puzzle.description,
            author = puzzle.author,
            copyright = puzzle.copyright,
            comment = puzzle.comment,
            date = puzzle.date,
            wordsAcross = readWord(puzzle.wordsAcross),
            wordsDown = readWord(puzzle.wordsDown),
            alphabet = puzzle.alphabet?.toCharArray()?.toSet()
        )
        emit(c)
    }

    private suspend fun readWord(crosswordWords: ArrayList<Word>?): List<Crossword.Word>? {
        val words = ArrayList<Crossword.Word>()
        withContext(Dispatchers.Default) {
            crosswordWords?.forEach { crossWord ->
                val cells = ArrayList<Crossword.Cell>().apply {
                    crossWord.cells?.forEach { cell ->
                        add(Crossword.Cell(cell.chars, cell.attrFlags))
                    }
                }
                words.add(
                    Crossword.Word(
                        number = crossWord.number,
                        hint = crossWord.hint,
                        startRow = crossWord.startRow,
                        startColumn = crossWord.startColumn,
                        direction = crossWord.direction,
                        hintUrl = crossWord.hintUrl,
                        citation = crossWord.citation,
                        cells = cells
                    )
                )
            }
        }
        return words
    }

    internal fun setHint(hint: String) {
        _hint.value = hint
    }
}