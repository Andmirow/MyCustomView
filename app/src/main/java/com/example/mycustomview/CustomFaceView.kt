package com.example.mycustomview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {


    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    // Краска объекта для окрашивания и стилизации
    private val paint = Paint()
    private val mouthPath = Path()
    private var size = 0


    var happinessState = HAPPY
        set(state) {
            field = state
            // 4
            invalidate()
        }


    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }


    private fun setupAttributes(attrs: AttributeSet?) {
        // 6
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomFaceView, 0, 0)

        // 7
        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.CustomFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.CustomFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.CustomFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.CustomFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(R.styleable.CustomFaceView_borderColor,
            DEFAULT_BORDER_COLOR)
        borderWidth = typedArray.getDimension(R.styleable.CustomFaceView_borderWidth,
            DEFAULT_BORDER_WIDTH)

        // 8
        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }




    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = Math.min(measuredWidth, measuredHeight)

        setMeasuredDimension(size, size)


    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
        
    }

    private fun drawMouth(canvas: Canvas?) {
        mouthPath.reset()
        mouthPath.moveTo(size * 0.22f , size * 0.7f )

        if (happinessState == HAPPY) {
            // 1
            mouthPath.quadTo(size * 0.5f , size * 0.80f , size * 0.78f , size * 0.7f )
            mouthPath.quadTo(size * 0.5f , size * 0.90f , size * 0.22f , size * 0.7f )
        } else {
            //
            mouthPath.quadTo(size * 0.5f , size * 0.50f , size * 0.78f , size * 0.7f )
            mouthPath.quadTo(size * 0.5f , size * 0.60f , size * 0.22f, size * 0.7f )
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL // 5
        canvas?.drawPath(mouthPath, paint)

    }

    private fun drawEyes(canvas: Canvas?) {

        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas?.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas?.drawOval(rightEyeRect, paint)

    }

    private fun drawFaceBackground(canvas: Canvas?) {

        paint.color = faceColor
        paint.style = Paint.Style.FILL
        val radius = size / 2f
        canvas?.drawCircle(size / 2f, size / 2f, radius, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE

        paint.strokeWidth = borderWidth
        canvas?.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)

    }
}