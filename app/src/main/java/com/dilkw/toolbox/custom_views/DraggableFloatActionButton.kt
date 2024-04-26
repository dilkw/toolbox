package com.dilkw.toolbox.custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * @ClassName DraggableFloatActionButton
 * @Author name
 * @Date 2024/3/19
 * @Description
 */
@SuppressLint("ViewConstructor")
class DraggableFloatActionButton: FloatingActionButton, View.OnTouchListener {

    private var lastActionDownX = 0
    private var lastActionDownY = 0

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val layoutParams = v.layoutParams
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastActionDownX = event.rawX.toInt() - layoutParams.width / 2
                lastActionDownY = event.rawY.toInt() - layoutParams.height / 2
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX.toInt() - lastActionDownX
                val dy = event.rawY.toInt() - lastActionDownY
                layoutParams.width = v.width
                layoutParams.height = v.height
                layoutParams.width += dx
                layoutParams.height += dy
                v.layoutParams = layoutParams
            }
        }
        return true
    }

}