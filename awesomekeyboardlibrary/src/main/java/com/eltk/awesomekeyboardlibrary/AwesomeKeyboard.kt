package com.eltk.awesomekeyboardlibrary

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.keyboard.view.*
import java.util.*

class AwesomeKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val keyValues = SparseArray<String>()
    private var inputConnection: InputConnection? = null
    lateinit var timer: Timer
    private var run = false

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true)
        button_1.setOnClickListener { setValue(button_1) }
        button_2.setOnClickListener { setValue(button_2) }
        button_3.setOnClickListener { setValue(button_3) }
        button_4.setOnClickListener { setValue(button_4) }
        button_5.setOnClickListener { setValue(button_5) }
        button_6.setOnClickListener { setValue(button_6) }
        button_7.setOnClickListener { setValue(button_7) }
        button_8.setOnClickListener { setValue(button_8) }
        button_9.setOnClickListener { setValue(button_9) }
        button_0.setOnClickListener { setValue(button_0) }
        button_q.setOnClickListener { setValue(button_q) }
        button_w.setOnClickListener { setValue(button_w) }
        button_e.setOnClickListener { setValue(button_e) }
        button_r.setOnClickListener { setValue(button_r) }
        button_t.setOnClickListener { setValue(button_t) }
        button_y.setOnClickListener { setValue(button_y) }
        button_u.setOnClickListener { setValue(button_u) }
        button_i.setOnClickListener { setValue(button_i) }
        button_o.setOnClickListener { setValue(button_o) }
        button_p.setOnClickListener { setValue(button_p) }
        button_a.setOnClickListener { setValue(button_a) }
        button_s.setOnClickListener { setValue(button_s) }
        button_d.setOnClickListener { setValue(button_d) }
        button_f.setOnClickListener { setValue(button_f) }
        button_g.setOnClickListener { setValue(button_g) }
        button_h.setOnClickListener { setValue(button_h) }
        button_j.setOnClickListener { setValue(button_j) }
        button_k.setOnClickListener { setValue(button_k) }
        button_l.setOnClickListener { setValue(button_l) }
        button_z.setOnClickListener { setValue(button_z) }
        button_x.setOnClickListener { setValue(button_x) }
        button_c.setOnClickListener { setValue(button_c) }
        button_v.setOnClickListener { setValue(button_v) }
        button_b.setOnClickListener { setValue(button_b) }
        button_n.setOnClickListener { setValue(button_n) }
        button_m.setOnClickListener { setValue(button_m) }
        button_delete.setOnClickListener { setValue(button_delete) }
        button_space.setOnClickListener { setValue(button_space) }
        button_toggle_number_input.setOnClickListener { toggleNumberInput() }
        button_delete.setOnClickListener { deleteInputText() }
        button_delete.setOnLongClickListener { continuosDelete() }
        button_delete.setOnTouchListener { view, motionEvent ->
            deleteButtonTouchListener(motionEvent)
        }


        keyValues.put(R.id.button_1, "1")
        keyValues.put(R.id.button_2, "2")
        keyValues.put(R.id.button_3, "3")
        keyValues.put(R.id.button_4, "4")
        keyValues.put(R.id.button_5, "5")
        keyValues.put(R.id.button_6, "6")
        keyValues.put(R.id.button_7, "7")
        keyValues.put(R.id.button_8, "8")
        keyValues.put(R.id.button_9, "9")
        keyValues.put(R.id.button_0, "0")
        keyValues.put(R.id.button_q, "Q")
        keyValues.put(R.id.button_w, "W")
        keyValues.put(R.id.button_e, "E")
        keyValues.put(R.id.button_r, "R")
        keyValues.put(R.id.button_t, "T")
        keyValues.put(R.id.button_y, "Y")
        keyValues.put(R.id.button_u, "U")
        keyValues.put(R.id.button_i, "I")
        keyValues.put(R.id.button_o, "O")
        keyValues.put(R.id.button_p, "P")
        keyValues.put(R.id.button_a, "A")
        keyValues.put(R.id.button_s, "S")
        keyValues.put(R.id.button_d, "D")
        keyValues.put(R.id.button_f, "F")
        keyValues.put(R.id.button_g, "G")
        keyValues.put(R.id.button_h, "H")
        keyValues.put(R.id.button_j, "J")
        keyValues.put(R.id.button_k, "K")
        keyValues.put(R.id.button_l, "L")
        keyValues.put(R.id.button_z, "Z")
        keyValues.put(R.id.button_x, "X")
        keyValues.put(R.id.button_v, "V")
        keyValues.put(R.id.button_c, "C")
        keyValues.put(R.id.button_b, "B")
        keyValues.put(R.id.button_n, "N")
        keyValues.put(R.id.button_m, "M")
        keyValues.put(R.id.button_space, context.getString(R.string.chr_space))
    }

    fun setValue(view: View) {
        if (inputConnection == null)
            return

        if (view.id == R.id.button_delete) {
            val selectedText = inputConnection!!.getSelectedText(0)

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection!!.deleteSurroundingText(1, 0)
            } else {
                inputConnection!!.commitText("", 1)
            }
        } else {
            val value = keyValues.get(view.id)
            inputConnection!!.commitText(value, 1)
        }
    }

    fun setInputConnection(ic: InputConnection) {
        inputConnection = ic
    }

    private fun toggleNumberInput() {
        if (ll_number_input.isVisible) {
            ll_number_input.visibility = View.GONE
        } else {
            ll_number_input.visibility = View.VISIBLE

        }
    }

    private fun deleteInputText() {
        val selectedText = inputConnection!!.getSelectedText(0)

        if (TextUtils.isEmpty(selectedText)) {
            inputConnection!!.deleteSurroundingText(1, 0)
        } else {
            inputConnection!!.commitText("", 1)
        }
    }

    private fun continuosDelete(): Boolean {
        timer = Timer()
        run = true
        val deleteTask = object : TimerTask() {
            override fun run() {
                if (run) {
                    deleteInputText()
                } else {
                    timer.cancel()
                    timer.purge()
                }
            }

        }
        timer.scheduleAtFixedRate(deleteTask, 200, 200)
        return false
    }

    private fun deleteButtonTouchListener(motionEvent: MotionEvent): Boolean {
        when(motionEvent.action){
            MotionEvent.ACTION_UP -> run = false
        }
        return false
    }

}