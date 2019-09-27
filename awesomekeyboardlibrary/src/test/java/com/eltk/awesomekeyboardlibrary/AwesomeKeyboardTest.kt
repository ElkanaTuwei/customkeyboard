package com.eltk.awesomekeyboardlibrary

import android.app.Instrumentation
import android.content.Context
import android.os.SystemClock
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.util.contains
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
public class AwesomeKeyboardTest {

    @Mock
    var context: Context? = null
    val downTime = SystemClock.uptimeMillis()
    val eventTime = SystemClock.uptimeMillis()
    val inst: Instrumentation = getInstrumentation();
    var x10 = 160f
    var y10 = 400f
    @Before
    fun setUp() {
        context = RuntimeEnvironment.application.getApplicationContext()
    }

    @Test
    public fun deleteButton_LongClick_startsTimerTask() {
        val keyboardView = AwesomeKeyboard(context!!)
        val deleteButton = keyboardView.findViewById<ImageButton>(R.id.button_delete)
        deleteButton.performLongClick()
        assertNotNull(keyboardView.timer)
        assertTrue(keyboardView.run)
    }

    @Test
    public fun deleteButton_touchUpAfterLongClick_stopsTimerTask() {
        val keyboardView = AwesomeKeyboard(context!!)
        val deleteButton = keyboardView.findViewById<ImageButton>(R.id.button_delete)
        val event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x10, y10, 0)
        deleteButton.onTouchEvent(event)
        assertFalse(keyboardView.run)
    }

    @Test
    fun buttonClick_shouldToggleNumberrow() {
        val keyboardView = AwesomeKeyboard(context!!)
        val numberToggleButton = keyboardView.findViewById<Button>(R.id.button_toggle_number_input)
        val numberRow = keyboardView.findViewById<LinearLayout>(R.id.ll_number_input)
        val initialState = numberRow.visibility
        numberToggleButton.performClick()
        val finalState = numberRow.visibility
        assertNotSame(initialState, finalState)
    }

    @Test
    fun buttonValue_isAdded_toKeyValuesArray_forButtonValue(){
        val keyboardView = AwesomeKeyboard(context!!)
        keyboardView.keyValues.append(R.id.button_0, "0")
        assertTrue(keyboardView.keyValues.contains(R.id.button_0))
    }
}