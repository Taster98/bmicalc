package it.luiggi.projects.BMICalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button calcola;
    private EditText altezza,peso;
    private TextView risposta,risposta2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcola = (Button)findViewById(R.id.calcola);
        altezza = (EditText)findViewById(R.id.altezza);
        peso = (EditText)findViewById(R.id.peso);
        risposta = (TextView)findViewById(R.id.risposta);
        risposta2 = (TextView)findViewById(R.id.risposta2);
        calcola.setOnClickListener(this);
        calcola.setEnabled(true);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View view) {
        if(view==calcola){
            try{
                closeKeyboard();
                altezza.setText(altezza.getText().toString().replace(",","."));
                peso.setText(peso.getText().toString().replace(",","."));
                double a = Double.parseDouble(altezza.getText().toString());
                double p = Double.parseDouble(peso.getText().toString());
                double bmi = p/(a*a);
                risposta.setText(String.format("%2.1f",bmi));
                
            }catch (Exception e){
                risposta.setText("???");
            }
        }
    }

    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}