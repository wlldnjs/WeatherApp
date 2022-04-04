package com.jiwon.weatherapp.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.jiwon.weatherapp.R

class SimpleDividerItemDecoration() : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null

    constructor(context: Context) : this() {
        mDivider = AppCompatResources.getDrawable(context, R.drawable.shape_rectangle_solid_darkgray_size_1dp)
    }

    constructor(context: Context, drawableId: Int) : this() {
        mDivider = AppCompatResources.getDrawable(context, drawableId)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }
}