package com.htnguyen.gplxapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSum = findViewById<View>(R.id.btnSummation) as Button
        val btnMinus = findViewById<View>(R.id.btnMinus) as Button
        val btnMulti = findViewById<View>(R.id.btnMultiplication) as Button
        val btnDevi = findViewById<View>(R.id.btnDevision) as Button
        val txtResult = findViewById<View>(R.id.txtResult) as TextView
        val edtFirst = findViewById<View>(R.id.edtFirst) as EditText
        val edtSecond = findViewById<View>(R.id.edtSecond) as EditText
        btnSum.setOnClickListener { v: View? ->
            try {
                val a =
                    edtFirst.text.toString().toDouble() + edtSecond.text.toString()
                        .toDouble()
                txtResult.text =
                    edtFirst.text.toString() + " + " + edtSecond.text.toString() + " = " + a
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Không để trống", Toast.LENGTH_LONG).show()
            }
        }
        btnMinus.setOnClickListener { v: View? ->
            try {
                val a =
                    edtFirst.text.toString().toDouble() - edtSecond.text.toString()
                        .toDouble()
                txtResult.text =
                    edtFirst.text.toString() + " - " + edtSecond.text.toString() + " = " + a
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Không để trống", Toast.LENGTH_LONG).show()
            }
        }
        btnMulti.setOnClickListener { v: View? ->
            try {
                val a =
                    edtFirst.text.toString().toDouble() * edtSecond.text.toString()
                        .toDouble()
                txtResult.text =
                    edtFirst.text.toString() + " * " + edtSecond.text.toString() + " = " + a
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Không để trống", Toast.LENGTH_LONG).show()
            }
        }
        btnDevi.setOnClickListener { v: View? ->
            try {
                if (edtSecond.text.toString().toDouble() != 0) {
                    val a =
                        edtFirst.text.toString().toDouble() / edtSecond.text.toString()
                            .toDouble()
                    txtResult.text =
                        edtFirst.text.toString() + " / " + edtSecond.text.toString() + " = " + a
                } else {
                    Toast.makeText(this@MainActivity, "Mẫu khác 0", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Không để trống", Toast.LENGTH_LONG).show()
            }
        }
    }
}