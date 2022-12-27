package com.lasteinsa.noteappmu.core.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class CardHelper() {

    fun setCardUnSelect(context: Context, card: MaterialCardView) {
        card.scaleX = 1F
        card.scaleY = 1F
        card.setCardBackgroundColor(
            ContextCompat.getColorStateList(context, com.google.android.material.R.color.material_dynamic_primary80)
        )
    }

    fun setCardOnSelect(context: Context, card: MaterialCardView) {
        card.scaleX = 0.98F
        card.scaleY = 0.98F
        card.setCardBackgroundColor(
            ContextCompat.getColorStateList(context, com.google.android.material.R.color.material_dynamic_primary40)
        )
    }
}