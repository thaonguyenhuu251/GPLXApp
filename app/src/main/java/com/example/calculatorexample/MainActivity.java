package com.example.calculatorexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            try {
                double a = Double.parseDouble(
                        edtFirst.getText().toString()) + Double.parseDouble(edtSecond.getText().toString()
                );
                txtResult.setText(
                        edtFirst.getText().toString() + " + " + edtSecond.getText().toString() + " = " + a
                );
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Không để trống", Toast.LENGTH_LONG).show();
            }
        });

        btnMinus.setOnClickListener(v->{
            try {
                double a = Double.parseDouble(
                        edtFirst.getText().toString()) - Double.parseDouble(edtSecond.getText().toString()
                );
                txtResult.setText(
                        edtFirst.getText().toString() + " - " + edtSecond.getText().toString() + " = " + a
                );
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Không để trống", Toast.LENGTH_LONG).show();
            }
        });

        btnMulti.setOnClickListener(v->{

            try {
                double a = Double.parseDouble(
                        edtFirst.getText().toString()) * Double.parseDouble(edtSecond.getText().toString()
                );
                txtResult.setText(
                        edtFirst.getText().toString() + " * " + edtSecond.getText().toString() + " = " + a
                );
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Không để trống", Toast.LENGTH_LONG).show();
            }
        });

        btnDevi.setOnClickListener(v->{
            try {
                if(Double.parseDouble(edtSecond.getText().toString())!=0) {
                    double a = Double.parseDouble(
                            edtFirst.getText().toString()) / Double.parseDouble(edtSecond.getText().toString()
                    );
                    txtResult.setText(
                            edtFirst.getText().toString() + " / " + edtSecond.getText().toString() + " = " + a
                    );
                } else {
                    Toast.makeText(MainActivity.this, "Mẫu khác 0", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Không để trống", Toast.LENGTH_LONG).show();
            }
        });
    }
}