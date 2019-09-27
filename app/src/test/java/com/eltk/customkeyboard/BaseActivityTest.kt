package com.eltk.customkeyboard

import org.junit.Test
import com.eltk.awesomekeyboardlibrary.AwesomeKeyboard
import com.eltk.customkeyboard.activity.BaseActivity
import com.eltk.customkeyboard.activity.MainActivity
import com.eltk.customkeyboard.application.CustomKeyboardApplication
import junit.framework.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class BaseActivityTest {
    @Before
    fun setUp() {
        val app = RuntimeEnvironment.application as CustomKeyboardApplication
    }

    @Test
    fun shouldToggleKeyboardView() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val keyBoardView: AwesomeKeyboard = activity.findViewById(R.id.keyboard)
        val initialState = keyBoardView.visibility
        activity.togggleView(keyBoardView)
        val finalState = keyBoardView.visibility
        assertNotSame(initialState, finalState)
    }
}