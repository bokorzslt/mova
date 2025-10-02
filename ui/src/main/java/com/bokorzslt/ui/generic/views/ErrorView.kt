package com.bokorzslt.ui.generic.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bokorzslt.ui.R
import com.bokorzslt.ui.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding: ErrorViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = ErrorViewBinding.inflate(inflater, this, true)
    }

    fun show() {
        binding.errorView.text = resources.getString(R.string.error_view_text)
        binding.errorView.visibility = VISIBLE
    }

    fun hide() {
        binding.errorView.visibility = GONE
    }
}