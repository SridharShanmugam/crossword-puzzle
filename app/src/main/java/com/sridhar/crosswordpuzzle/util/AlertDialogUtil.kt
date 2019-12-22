package com.sridhar.crosswordpuzzle.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object AlertDialogUtil {

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String?,
        listener: OnClickListener,
        requestCode: Int
    ): AlertDialog {
        val builder = AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                positiveButtonText
            ) { dialog, _ ->
                listener.onClick(requestCode)
                dialog.dismiss()
            }
            negativeButtonText?.let {
                setNegativeButton(
                    negativeButtonText
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            }
        }
        return builder.create().apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    interface OnClickListener {

        fun onClick(requestCode: Int)
    }

}

