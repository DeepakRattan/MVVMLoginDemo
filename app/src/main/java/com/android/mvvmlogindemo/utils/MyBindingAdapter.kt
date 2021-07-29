package com.android.mvvmlogindemo.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setError")
fun TextInputLayout.setCustomError(error: String?) {
    this.error = error
}