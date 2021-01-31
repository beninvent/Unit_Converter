package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Benjamin Thiessen
    //Jan. 31, 2020

    private EditText input;//where the input number comes from
    private TextView output;//where the output number goes to

    private Spinner input_Units;//where the input units comes from
    private Spinner output_Units;//where the output units comes from


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set where the input units comes from
        input_Units = findViewById(R.id.input_Units_Spinner);
        output_Units = findViewById(R.id.output_Units_Spinner);

        //set up a listener to know when the units update
        input_Units.setOnItemSelectedListener(this);
        output_Units.setOnItemSelectedListener(this);

        //set where the input number comes from
        //and where the output number goes to
        input = findViewById(R.id.temp_Input);
        output = findViewById(R.id.temp_Output);

        //Listen for when the input has changed
        TextWatcher input_Watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                //When the input changes perform the conversion
                performConvert();
            }
        };
        input.addTextChangedListener(input_Watcher);
    }


    public void performConvert() {

        //Set the input and output units
        int inputUnits = input_Units.getSelectedItemPosition();
        int outputUnits = output_Units.getSelectedItemPosition();

        try {
            //Get temperature to convert
            double temperature = Double.valueOf(input.getText().toString());

            //Call Convert method and output the answer
            @SuppressLint("DefaultLocale") String outputString;
            outputString = String.format("%.2f", convert(temperature, inputUnits, outputUnits));

            output.setText(outputString);
        }
        catch(Exception e) {
            //if nothing is entered set the output to nothing
            output.setText("");
        }
    }


    private Double convert(Double in, int inUnits, int outUnits) {
        //output variable
        double out = 0;
        //Conversion values
        final double fm = 1.8;
        final int fa = 32;
        final double ck = 273.15;
        //Conversion Logic
        switch(inUnits) {
            case 0: switch(outUnits) {
                case 0: out = in;
                    break;
                case 1: out = (in * fm) + fa;
                    break;
                case 2: out = in + ck;
                    break;
                default:
                    break;
            }
                break;
            case 1: switch(outUnits) {
                case 0: out = (in - fa) / 1.8;
                    break;
                case 1: out = in;
                    break;
                case 2: out = ((in - fa) / 1.8) + ck;
                    break;
                default:
                    break;
            }
                break;
            case 2: switch(outUnits) {
                case 0: out = in - ck;
                    break;
                case 1: out = ((in - ck) * fm) + 32;
                    break;
                case 2: out = in;
                    break;
                default:
                    break;
            }
                break;
            default:
                break;
        }
        //out put converted number
        return out;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //when input or output units change perform a conversion
        performConvert();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}