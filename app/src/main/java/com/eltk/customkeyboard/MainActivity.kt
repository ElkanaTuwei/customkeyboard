package com.eltk.customkeyboard

import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_text_input.setRawInputType(InputType.TYPE_CLASS_TEXT)
        val etTextInputIc = et_text_input.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnection(etTextInputIc)
        et_text_input.setOnFocusChangeListener { view, b -> togggleView(keyboard) }
    }
}
