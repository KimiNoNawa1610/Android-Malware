package com.example.tipcalculatorv1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator( 0.17f, 100.0f );
        setContentView(R.layout.activity_main);
    }
    public void calculate(View v) {
        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText =  (EditText) findViewById(R.id.amount_tip_percent);
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        TextView tipTextView =
                (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView =
                (TextView) findViewById(R.id.amount_total);
        try {
            // convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            // update the Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            // ask Model to calculate tip and total amounts
            float tip = .01f * tipPercent;
            float total = tipCalc.totalAmount();
            // update the View with formatted tip and total amounts
            tipTextView.setText(money.format(tipCalc.tipAmount()));
            totalTextView.setText(money.format(tipCalc.totalAmount()));
        } catch (NumberFormatException nfe) {
            // pop up an alert view here (optional)
        }
    }
}