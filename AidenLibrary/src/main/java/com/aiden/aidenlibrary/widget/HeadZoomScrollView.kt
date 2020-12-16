package com.aiden.aidenlibrary.widget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView

class HeadZoomScrollView : ScrollView {
    private var mZoomView: View? = null
    private var mZoomViewWidth = 0
    private var mZoomViewHeight = 0
    private var firstPosition = 0f
    private var isScrolling = false
    private var mScrollRate = 0.3f
    private var mReplyRate = 0.5f

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setZoomView(mZoomView: View?) {
        this.mZoomView = mZoomView
    }

    fun setScrollRate(mScrollRate: Float) {
        this.mScrollRate = mScrollRate
    }

    fun setReplyRate(mReplyRate: Float) {
        this.mReplyRate = mReplyRate
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        overScrollMode = View.OVER_SCROLL_NEVER
        if (getChildAt(0) != null) {
            val vg = getChildAt(0) as ViewGroup
            if (vg.getChildAt(0) != null) {
                mZoomView = vg.getChildAt(0)
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (mZoomViewWidth <= 0 || mZoomViewHeight <= 0) {
            mZoomViewWidth = mZoomView!!.measuredWidth
            mZoomViewHeight = mZoomView!!.measuredHeight
        }
        when (ev.action) {
            MotionEvent.ACTION_UP -> {
                //手指离开后恢复图片
                isScrolling = false
                replyImage()
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isScrolling) {
                    firstPosition = if (scrollY == 0) {
                        ev.y
                    } else {
                        0f
                    }
                }
                val distance = ((ev.y - firstPosition) * mScrollRate).toInt() // 滚动距离乘以一个系数

                // 处理放大
                isScrolling = true
                setZoom(distance.toFloat())
                return true // 返回true表示已经完成触摸事件，不再处理
            }
        }
        return true
    }

    //回弹动画
    private fun replyImage() {
        val distance = mZoomView!!.measuredWidth - mZoomViewWidth.toFloat()
        val valueAnimator =
            ValueAnimator.ofFloat(distance, 0f).setDuration((distance * mReplyRate).toLong())
        valueAnimator.addUpdateListener { animation -> setZoom(animation.animatedValue as Float) }
        valueAnimator.start()
    }

    private fun setZoom(zoom: Float) {
        if (mZoomViewWidth <= 0 || mZoomViewHeight <= 0) {
            return
        }
        val lp = mZoomView!!.layoutParams
        lp.width = (mZoomViewWidth + zoom).toInt()
        lp.height = (mZoomViewHeight * ((mZoomViewWidth + zoom) / mZoomViewWidth)).toInt()
        (lp as MarginLayoutParams).setMargins(-(lp.width - mZoomViewWidth) / 2, 0, 0, 0)
        mZoomView!!.layoutParams = lp
    }
}