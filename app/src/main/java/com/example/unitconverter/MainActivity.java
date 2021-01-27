package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Double temperature;

    int inputUnits;
    int outputUnits;

    EditText tempInput;

    TextView output;

    Button convertButton;

    Spinner input_Units;
    Spinner output_Units;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_Units = findViewById(R.id.input_Units);
        output_Units = findViewById(R.id.output_Units);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        input_Units.setAdapter(adapter);
        input_Units.setOnItemSelectedListener(this);
        output_Units.setAdapter(adapter);
        output_Units.setOnItemSelectedListener(this);

        tempInput = (EditText) findViewById(R.id.temp_Input);
        output = (TextView) findViewById(R.id.output);
        convertButton = (Button) findViewById(R.id.button);
        convertButton.setOnClickListener(v -> {
            temperature = Double.valueOf(tempInput.getText().toString());

            //call Convert magic method


            //output the answer
            output.setText(Convert(temperature).toString());

        });

    }

    private Double Convert(Double input) {
        return input;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
            case R.id.input_Units: inputUnits = position;
            case R.id.output_Units: outputUnits = position;
                break;
            default: break;
        }
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}