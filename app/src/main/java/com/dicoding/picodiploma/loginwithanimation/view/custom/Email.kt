package com.dicoding.picodiploma.loginwithanimation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.loginwithanimation.R

class Email @JvmOverloads constructor(
    context: Context, attrs: AttributeSet
): AppCompatEditText(context, attrs), View.OnTouchListener {

    init {
        setOnTouchListener(this)
        addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    error = if (Patterns.EMAIL_ADDRESS.matcher(it.toString()).matches()) {
                        null
                    } else {
                        context.getString(R.string.invalid_email_error)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }
}