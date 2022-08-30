package com.example.clockapp.controllers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 *
 */
// used to decorate recler view item
class Spaceitemdecoration(private val horzintal_space: Int, private val verical_space: Int) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = horzintal_space
        outRect.left = horzintal_space
        outRect.top = verical_space
        outRect.bottom = verical_space
    }
}