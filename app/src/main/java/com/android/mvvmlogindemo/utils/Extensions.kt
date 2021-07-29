package com.android.mvvmlogindemo.utils

import android.content.Context
import android.widget.Toast

fun Context.makeToast(msg :String){
    Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
}