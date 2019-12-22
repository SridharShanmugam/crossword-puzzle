package com.sridhar.crosswordpuzzle.ui.home

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject
import com.sridhar.crosswordpuzzle.vo.Cell
import com.sridhar.crosswordpuzzle.vo.Puzzle
import com.sridhar.crosswordpuzzle.vo.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.akop.ararat.core.Crossword

typealias OnClick = (id: Int) -> Unit
typealias ShowToast = () -> Unit

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var listenerRegistration: ListenerRegistration
    val adapter = PuzzleListAdapter()
    internal var fileUri: Uri? = null
    internal lateinit var onClick: OnClick
    internal lateinit var showToast: ShowToast

    private val _isPuzzlesEmpty = MutableLiveData<Boolean>()
    val isPuzzlesEmpty: LiveData<Boolean> = _isPuzzlesEmpty

    private val _showLoading = MutableLiveData<Boolean>(true)
    val showLoading: LiveData<Boolean> = _showLoading

    private val _puzzles = MutableLiveData<List<Puzzle>>()
    private val puzzles: LiveData<List<Puzzle>> = _puzzles

    internal fun fetchPuzzleData() {
        listenerRegistration = db.collection("puzzles")
            .addSnapshotListener { snapshots, e ->
                _showLoading.postValue(false)
                if (e != null) {
                    Log.e(TAG, "SnapshotListener:Error", e)
                    return@addSnapshotListener
                }

                Log.d(TAG, "addSnapshotListener")

                val puzzles = arrayListOf<Puzzle>()
                for (dc in snapshots!!.documents) {
                    val puzzle = dc.toObject<Puzzle>()!!
                    puzzle.documentId = dc.id
                    puzzles.add(puzzle)
                }
                _isPuzzlesEmpty.value = puzzles.isNullOrEmpty()
                _puzzles.value = puzzles
                adapter.setPuzzles(puzzles)
            }
    }

    internal fun addData(crossword: Crossword) {
        viewModelScope.launch(Dispatchers.Default) {
            var isPuzzleFound = false
            puzzles.value?.let {
                for (puzzle in it) {
                    if (!puzzle.title.isNullOrEmpty()
                        && !puzzle.author.isNullOrEmpty()
                        && puzzle.title.equals(crossword.title, true)
                        && (puzzle.author.equals(crossword.author, true))
                    ) {
                        isPuzzleFound = true
                        break
                    }
                }
            }
            if (!isPuzzleFound) {
                val data = hashMapOf(
                    "width" to crossword.width,
                    "height" to crossword.height,
                    "squareCount" to crossword.squareCount,
                    "flags" to crossword.flags,
                    "title" to crossword.title,
                    "description" to crossword.description,
                    "author" to crossword.author,
                    "copyright" to crossword.copyright,
                    "comment" to crossword.comment,
                    "date" to crossword.date,
                    "wordsAcross" to readWord(crossword.wordsAcross),
                    "wordsDown" to readWord(crossword.wordsDown),
                    "alphabet" to crossword.alphabet.toCharArray().joinToString("")
                )
                db.collection("puzzles")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "Document Snapshot written with ID: ${documentReference.id}")
                        fileUri = null
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error adding document", e)
                        fileUri = null
                    }
            } else {
                withContext(Dispatchers.Main) {
                    showToast.invoke()
                }
            }
        }
    }

    private fun readWord(crosswordWords: List<Crossword.Word>): MutableList<Word> {
        val words: MutableList<Word> = mutableListOf()
        crosswordWords.forEachIndexed { index, word ->
            val cells: MutableList<Cell> = mutableListOf()
            for (cellIndex in 0 until word.length) {
                cells.add(
                    cellIndex, Cell(
                        word.cellAt(cellIndex).chars,
                        word.cellAt(cellIndex).attrFlags
                    )
                )
            }
            words.add(
                index, Word(
                    word.number,
                    word.hint,
                    word.startRow,
                    word.startColumn,
                    word.direction,
                    word.hintUrl,
                    word.citation,
                    cells
                )
            )
        }
        return words
    }

    fun onClick(view: View) {
        onClick.invoke(view.id)
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration.remove()
    }

    companion object {
        private var TAG = HomeViewModel::class.java.simpleName
    }
}