package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView input;
    double val1 = 0, val2 = 0;
    char operation;
    boolean isOpPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        input = findViewById(R.id.input);

        int[] numberIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener numberClick = v -> {
            Button b = (Button) v;
            input.append(b.getText());
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberClick);
        }

        findViewById(R.id.btnAdd).setOnClickListener(v -> operatorClicked('+'));
        findViewById(R.id.btnSub).setOnClickListener(v -> operatorClicked('-'));
        findViewById(R.id.btnMul).setOnClickListener(v -> operatorClicked('*'));
        findViewById(R.id.btnDiv).setOnClickListener(v -> operatorClicked('/'));

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            if (isOpPressed) {
                val2 = Double.parseDouble(input.getText().toString());
                double res = 0;
                switch (operation) {
                    case '+': res = val1 + val2; break;
                    case '-': res = val1 - val2; break;
                    case '*': res = val1 * val2; break;
                    case '/': res = val2 != 0 ? val1 / val2 : 0; break;
                }
                input.setText(String.valueOf(res));
                isOpPressed = false;
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> input.setText(""));
    }

    private void operatorClicked(char op) {
        val1 = Double.parseDouble(input.getText().toString());
        operation = op;
        isOpPressed = true;
        input.setText("");
    }
}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" 
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="20dp"
    android:gravity="center">

    <TextView
        android:id="@+id/input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="32sp"
        android:gravity="end"
        android:background="#EEEEEE"
        android:padding="20dp"
        android:text=""/>

    <GridLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:columnCount="4"
        android:rowCount="5"
        android:layout_marginTop="20dp">

        <!-- Number Buttons -->
        <Button android:id="@+id/btn7" android:text="7" style="@style/CalcButton"/>
        <Button android:id="@+id/btn8" android:text="8" style="@style/CalcButton"/>
        <Button android:id="@+id/btn9" android:text="9" style="@style/CalcButton"/>
        <Button android:id="@+id/btnDiv" android:text="/" style="@style/CalcButton"/>

        <Button android:id="@+id/btn4" android:text="4" style="@style/CalcButton"/>
        <Button android:id="@+id/btn5" android:text="5" style="@style/CalcButton"/>
        <Button android:id="@+id/btn6" android:text="6" style="@style/CalcButton"/>
        <Button android:id="@+id/btnMul" android:text="*" style="@style/CalcButton"/>

        <Button android:id="@+id/btn1" android:text="1" style="@style/CalcButton"/>
        <Button android:id="@+id/btn2" android:text="2" style="@style/CalcButton"/>
        <Button android:id="@+id/btn3" android:text="3" style="@style/CalcButton"/>
        <Button android:id="@+id/btnSub" android:text="-" style="@style/CalcButton"/>

        <Button android:id="@+id/btn0" android:text="0" style="@style/CalcButton"/>
        <Button android:id="@+id/btnClear" android:text="C" style="@style/CalcButton"/>
        <Button android:id="@+id/btnEqual" android:text="=" style="@style/CalcButton"/>
        <Button android:id="@+id/btnAdd" android:text="+" style="@style/CalcButton"/>

    </GridLayout>
</LinearLayout>
<style name="CalcButton">
    <item name="android:layout_width">80dp</item>
    <item name="android:layout_height">80dp</item>
    <item name="android:textSize">24sp</item>
    <item name="android:layout_margin">5dp</item>
</style>
