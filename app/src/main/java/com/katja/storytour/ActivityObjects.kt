package com.katja.storytour

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtils {
    fun showQuitConfirmationDialog(context: Context, onConfirmed: () -> Unit) {
        AlertDialog.Builder(context)
            .setMessage(context.getString(R.string.sure_end_adventure))
            .setPositiveButton(R.string.yes) { dialog, _ ->
                onConfirmed.invoke()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}