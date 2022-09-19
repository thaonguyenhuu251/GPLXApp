package com.example.calculatorexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSum = (Button) findViewById(R.id.btnSummation);
        Button btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnMulti = (Button) findViewById(R.id.btnMultiplication);
        Button btnDevi = (Button) findViewById(R.id.btnDevision);

        TextView txtResult = (TextView) findViewById(R.id.txtResult);

        EditText edtFirst = (EditText) findViewById(R.id.edtFirst);
        EditText edtSecond = (EditText) findViewById(R.id.edtSecond);

        btnSum.setOnClickListener(v->{
            long a = Long.parseLong(
                    edtFirst.getText().toString()) + Long.parseLong(edtSecond.getText().toString()
            );
            txtResult.setText(
                    edtFirst.getText().toString() + " + " + edtSecond.getText().toString() + " = " + a
            );
        });

        btnMinus.setOnClickListener(v->{
            long a = Long.parseLong(
                    edtFirst.getText().toString()) - Long.parseLong(edtSecond.getText().toString()
            );
            txtResult.setText(
                    edtFirst.getText().toString() + " - " + edtSecond.getText().toString() + " = " + a
            );
        });

        btnMulti.setOnClickListener(v->{
            long a = Long.parseLong(
                    edtFirst.getText().toString()) * Long.parseLong(edtSecond.getText().toString()
            );
            txtResult.setText(
                    edtFirst.getText().toString() + " * " + edtSecond.getText().toString() + " = " + a
            );
        });

        btnDevi.setOnClickListener(v->{
            float a = Float.parseFloat(
                    edtFirst.getText().toString()) / Long.parseLong(edtSecond.getText().toString()
            );
            txtResult.setText(
                    edtFirst.getText().toString() + " / " + edtSecond.getText().toString() + " = " + a
            );
        });
    }
}