package com.pru.ktorandyserver.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pru.ktorandyserver.databinding.LayoutMyTextViewBinding

class MyTextView(context: Context, attributes: AttributeSet? = null) :
    LinearLayout(context, attributes) {
    constructor(value: String?, context: Context, attributes: AttributeSet? = null) : this(
        context,
        attributes
    ) {
        updateText(value)
    }

    private val binding: LayoutMyTextViewBinding = LayoutMyTextViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private fun updateText(value: String?) {
        binding.tvChildItem.text = value
    }

}