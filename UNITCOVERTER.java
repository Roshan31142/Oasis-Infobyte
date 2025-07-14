package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertButton;
    TextView resultText;

    String[] units = {"Centimeter", "Meter", "Gram", "Kilogram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(input);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double result = 0.0;
        boolean valid = true;

        if (fromUnit.equals("Centimeter") && toUnit.equals("Meter")) {
            result = value / 100;
        } else if (fromUnit.equals("Meter") && toUnit.equals("Centimeter")) {
            result = value * 100;
        } else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) {
            result = value / 1000;
        } else if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) {
            result = value * 1000;
        } else if (fromUnit.equals(toUnit)) {
            result = value;
        } else {
            valid = false;
        }

        if (valid) {
            resultText.setText("Converted Value: " + result);
        } else {
            resultText.setText("Invalid unit conversion.");
        }
    }
}
