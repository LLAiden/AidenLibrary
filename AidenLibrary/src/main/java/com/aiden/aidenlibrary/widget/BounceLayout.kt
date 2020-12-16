package com.aiden.aidenlibrary.widget

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.OverScroller
import com.aiden.aidenlibrary.utils.DensityUtil.dp2px

class BounceLayout @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {

    private var mOverScrollDistance = 50
    private var mOverScroller: OverScroller = OverScroller(context)
    private val mInvalidPointId = -1
    private var mTranslatePointerId = mInvalidPointId
    private val mTranslateLastTouch = PointF()
    private var firstX = 0f
    private var firstY = 0f


    init {
        mOverScrollDistance = dp2px(100F)
        overScrollMode = View.OVER_SCROLL_ALWAYS
    }


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action
        return when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_MOVE -> {
                val translateX = ev.x
                val translateY = ev.y
                //距离小于5认为是单击事件，传递给子控件
                firstX - translateX < -5 || firstX - translateX > 5 || firstY - translateY < -5 || firstY - translateY > 5
            }
            MotionEvent.ACTION_DOWN -> {
                if (!mOverScroller.isFinished) {
                    mOverScroller.abortAnimation()
                }
                val x = ev.x
                val y = ev.y
                firstX = x
                firstY = y
                mTranslateLastTouch[x] = y
                //记录第一个手指按下时的ID
                mTranslatePointerId = ev.getPointerId(0)
                false
            }
            else -> {
                false
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                if (!mOverScroller.isFinished) {
                    mOverScroller.abortAnimation()
                }
                val x = event.x
                val y = event.y
                mTranslateLastTouch[x] = y
                //记录第一个手指按下时的ID
                mTranslatePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                /**
                 * 取第一个触摸点的位置
                 */
                val pointerIndexTranslate = event.findPointerIndex(mTranslatePointerId)
                if (pointerIndexTranslate >= 0) {
                    val translateX = event.getX(pointerIndexTranslate)
                    val translateY = event.getY(pointerIndexTranslate)
                    /**
                     * deltaX 将要在X轴方向上移动距离
                     * scrollX 滚动deltaX之前，x轴方向上的偏移
                     * scrollRangeX 在X轴方向上最多能滚动的距离
                     * maxOverScrollX 在x轴方向上，滚动到边界时，还能超出的滚动距离
                     */
                    overScrollBy((mTranslateLastTouch.x - translateX).toInt(), (mTranslateLastTouch.y - translateY).toInt() / 4, this.scrollX, this.scrollY, 0, 0, 0, mOverScrollDistance, true)
                    mTranslateLastTouch[translateX] = translateY
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                /**
                 * startX 回滚开始时x轴上的偏移
                 * minX 和maxX 当前位置startX在minX和manX之 间时就不再回滚
                 * 此配置表示X和Y上的偏移都必须复位到0
                 */
                if (mOverScroller.springBack(this.scrollX, this.scrollY, 0, 0, 0, 0)) {
                    invalidate()
                }
                mTranslatePointerId = mInvalidPointId
            }
        }
        return true
    }

    override fun computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            val oldX = scrollX
            val oldY = scrollY

            /**
             * 根据动画开始及持续时间计算出当前时间下，view的X.Y方向上的偏移量
             * 参见OverScroller computeScrollOffset 的SCROLL_MODE
             */
            val x = mOverScroller.currX
            val y = mOverScroller.currY
            if (oldX != x || oldY != y) {
                overScrollBy(x - oldX, y - oldY, oldX, oldY, 0, 0, 0, mOverScrollDistance, false)
            }
            postInvalidate()
        }
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        if (!mOverScroller.isFinished) {
            super.scrollTo(scrollX, scrollY)
            if (clampedX || clampedY) {
                mOverScroller.springBack(scrollX, scrollY, 0, 0, 0, 0)
            }
        } else {
            super.scrollTo(scrollX, scrollY)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (index in 0..childCount) {
            getChildAt(index)?.apply {
                overScrollMode = View.OVER_SCROLL_NEVER
            }
        }
    }
}