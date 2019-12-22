package com.sridhar.crosswordpuzzle.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.sridhar.crosswordpuzzle.R
import com.sridhar.crosswordpuzzle.databinding.ActivityHomeBinding
import com.sridhar.crosswordpuzzle.util.AlertDialogUtil
import kotlinx.android.synthetic.main.activity_home.*
import org.akop.ararat.core.buildCrossword
import org.akop.ararat.io.PuzFormatter

class HomeActivity : AppCompatActivity(), AlertDialogUtil.OnClickListener, OnClick, ShowToast {

    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private var hasSavedInstanceState = false
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { hasSavedInstanceState = true }
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeBinding.lifecycleOwner = this

        init()
    }

    private fun init() {
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeViewModel.onClick = this
        homeViewModel.showToast = this
        homeBinding.mainViewModel = homeViewModel

        setSupportActionBar(toolbar)
        if (hasSavedInstanceState) {
            if (homeViewModel.fileUri != null)
                handleResult()
        } else {
            homeViewModel.fetchPuzzleData()
        }
    }

    private fun openFile() {
        val intent = Intent().apply {
            action = Intent.ACTION_OPEN_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(intent, RC_PICK_PUZ_FILE)
    }

    private fun handleResult() {
        val path = homeViewModel.fileUri?.path
        val fileExtension = path?.substring(path.lastIndexOf(".") + 1)
        if (fileExtension.equals("puz", true)
            or fileExtension.equals("puzzle", true)
        ) {
            alertDialog = AlertDialogUtil.showDialog(
                this,
                getString(R.string.title_upload),
                getString(R.string.message_upload),
                getString(R.string.action_upload),
                getString(R.string.action_cancel),
                this,
                11
            )
            alertDialog!!.show()
        } else {
            alertDialog = AlertDialogUtil.showDialog(
                this,
                getString(R.string.title_invalid),
                getString(R.string.message_cannot_upload),
                getString(R.string.action_okay),
                null,
                this,
                12
            )
            alertDialog!!.show()
        }
    }

    private fun readFile(uri: Uri) {
        val contentResolver = applicationContext.contentResolver
        val crossword = buildCrossword {
            contentResolver.openInputStream(uri)?.let {
                PuzFormatter().read(this, it)
            }
        }
        homeViewModel.addData(crossword)
    }

    override fun onClick(requestCode: Int) {
        when (requestCode) {
            11 -> homeViewModel.fileUri?.let { readFile(it) }
        }
    }

    override fun invoke(id: Int) {
        when (id) {
            R.id.uploadPuzzleFab -> openFile()
        }
    }

    override fun invoke() {
        Toast.makeText(
            applicationContext,
            getString(R.string.puzzle_available),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_PICK_PUZ_FILE && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                homeViewModel.fileUri = uri
                handleResult()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (alertDialog != null && alertDialog!!.isShowing)
            alertDialog!!.cancel()
        alertDialog = null
    }

    companion object {
        internal const val RC_PICK_PUZ_FILE = 1
    }
}
