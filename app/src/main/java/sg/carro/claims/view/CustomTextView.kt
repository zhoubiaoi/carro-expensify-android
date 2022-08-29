package sg.carro.claims.view

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import sg.carro.claims.R

open class CustomFont : AppCompatTextView {
    object FontWeight {
        var BOLD = "bold"
        var MEDIUM = "medium"
        var DEMIBOLD = "demibold"
    }

    constructor(context: Context) : super(context) {
        applyCustomFont(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        applyCustomFont(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs)
    }

    fun setHighLightText(
        content: String,
        highLightText: String, @ColorInt color: Int,
        clickableSpan: ClickableSpan?,
        isUnderLine: Boolean = false
    ) {
        if (TextUtils.isEmpty(content)) return
        val spannableString = SpannableString(content)
        val start = content.indexOf(highLightText)
        if (start != -1) {
            val end = start + highLightText.length
            if (clickableSpan != null) {
                spannableString.setSpan(
                    clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                movementMethod = LinkMovementMethod.getInstance()
            }
            spannableString.setSpan(object : UnderlineSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = color
                    ds.isUnderlineText = isUnderLine
                }
            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        text = spannableString
//        movementMethod = LinkMovementMethod.getInstance()
    }

    fun setHighLightTextIgnoreCase(
        content: String,
        highLightText: String, @ColorInt color: Int,
        clickableSpan: ClickableSpan?
    ) {

        if (TextUtils.isEmpty(content)) return
        val spannableString = SpannableString(content)
        val start = content.indexOf(highLightText, ignoreCase = true)
        if (start != -1) {
            val end = start + highLightText.length
            if (clickableSpan != null) spannableString.setSpan(
                clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(object : UnderlineSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = color
                    ds.isFakeBoldText = true
                    ds.isUnderlineText = false
                }
            }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        text = spannableString
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun applyCustomFont(
        context: Context,
        attrs: AttributeSet?
    ) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFont)
        var customFont = FontCache.getTypeface(
            context.resources.getString(R.string.custom_font_regular),
            context
        )
        if (attrs != null && typedArray.hasValue(R.styleable.CustomFont_textStyle)) {
            if (typedArray.getString(
                    R.styleable.CustomFont_textStyle
                ) == FontWeight.BOLD
            ) {
                customFont = FontCache.getTypeface(
                    context.resources.getString(R.string.custom_font_bold),
                    context
                )
            } else if (typedArray.getString(
                    R.styleable.CustomFont_textStyle
                ) == FontWeight.MEDIUM
            ) {
                customFont = FontCache.getTypeface(
                    context.resources.getString(R.string.custom_font_medium),
                    context
                )
            } else if (typedArray.getString(
                    R.styleable.CustomFont_textStyle
                ) == FontWeight.DEMIBOLD
            ) {
                customFont = FontCache.getTypeface(
                    context.resources.getString(R.string.custom_font_demibold),
                    context
                )
            }
        }
        typeface = customFont
        typedArray.recycle()
    }


    fun updateFont(
        context: Context,
        font: String
    ) {
        var customFont = FontCache.getTypeface(
            context.resources.getString(R.string.custom_font_regular),
            context
        )

        if (font == FontWeight.BOLD
        ) {
            customFont = FontCache.getTypeface(
                context.resources.getString(R.string.custom_font_bold),
                context
            )
        } else if (font == FontWeight.MEDIUM
        ) {
            customFont = FontCache.getTypeface(
                context.resources.getString(R.string.custom_font_medium),
                context
            )
        }
        typeface = customFont
    }

    fun addRequiredIcon(content: String) {
        if (content.isEmpty()) {
            text = content
        } else {
            val span = SpannableString("$text\t\t*");
            span.setSpan(
                ForegroundColorSpan(Color.parseColor("#F54165")),
                text.length + 2,
                text.length + 3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            text = span
        }
    }
}