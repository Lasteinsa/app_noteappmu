package com.lasteinsa.noteappmu.core.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getColor
import com.google.android.material.snackbar.Snackbar
import com.lasteinsa.noteappmu.core.R

class SnackbarHelper {
    fun infoSnackbar(context: Context, view: View, anchorView: View, text: String) {
        Snackbar.make(view, text, 3000).setAnchorView(anchorView).setBackgroundTint(
            getColor(context, R.color.info)
        ).setTextColor(getColor(context, R.color.white)).show()
    }

    fun dangerSnackbar(context: Context, view: View, anchorView: View, text: String) {
        Snackbar.make(view, text, 3000).setAnchorView(anchorView).setBackgroundTint(
            getColor(context, R.color.danger)
        ).setTextColor(getColor(context, R.color.white)).show()
    }
}