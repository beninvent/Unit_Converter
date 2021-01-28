package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Double temperature;
    int inputUnits;
    int outputUnits;

    EditText input;
    TextView output;

    Button convert;

    Spinner input_Units;
    Spinner output_Units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_Units = findViewById(R.id.input_Units_Spinner);
        output_Units = findViewById(R.id.output_Units_Spinner);

        input = findViewById(R.id.temp_Input);
        output = findViewById(R.id.temp_Output);

        convert = findViewById(R.id.button);

        input.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    convert.performClick();
                    return true;
                }
                return false;
            }
        });

    }

    public void convertButtonClick(View view) {

        inputUnits = input_Units.getSelectedItemPosition();
        outputUnits = output_Units.getSelectedItemPosition();

        output.setText("");

        try {
            temperature = Double.valueOf(input.getText().toString());

            //Call Convert method and output the answer
            String outputString = String.format("%.2f", Convert(temperature));

            output.setText(outputString);
        }
        catch(Exception e) {
            Toast.makeText(this, "Enter a Number", Toast.LENGTH_SHORT).show();
        }
    }

    private Double Convert(Double input) {
        double output = 0;
        final double fm = 1.8;
        final int fa = 32;
        final double ck = 273.15;
        switch(inputUnits) {
            case 0: switch(outputUnits) {
                case 0: output = input;
                    break;
                case 1: output = (input * fm) + fa;
                    break;
                case 2: output = input + ck;
                    break;
                default:
                    break;
            }
                break;
            case 1: switch(outputUnits) {
                case 0: output = (input - fa) / 1.8;
                    break;
                case 1: output = input;
                    break;
                case 2: output = ((input - fa) / 1.8) + ck;
                    break;
                default:
                    break;
            }
                break;
            case 2: switch(outputUnits) {
                case 0: output = input - ck;
                    break;
                case 1: output = ((input - ck) * fm) + 32;
                    break;
                case 2: output = input;
                    break;
                default:
                    break;
            }
                break;
            default:
                break;
        }
        return output;
    }

}