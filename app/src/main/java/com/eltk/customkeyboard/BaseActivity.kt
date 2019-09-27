package com.eltk.customkeyboard

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

public abstract class BaseActivity : AppCompatActivity() {
    public fun togggleView(view: View) {
        if (view.isVisible) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}