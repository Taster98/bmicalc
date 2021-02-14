package com.example.bmicalctest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //I riferimenti a "codice" degli elementi del layout
    private TextView altezzaTextView, pesoTextView, resultTextView;
    private EditText altezzaEditText, pesoEditText;
    private Button calcoloButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Associamo la UI alla nostra activity
        setContentView(R.layout.activity_main);
        //inizializzare tutto il layout
        initView();
        //logica per il calcolo del BMI
        process();
    }
    @Override
    public void onClick(View v) {
        action();
    }

    private void initView(){
        altezzaTextView = (TextView)findViewById(R.id.altezzaTextView);
        pesoTextView = (TextView)findViewById(R.id.pesoTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        altezzaEditText = (EditText) findViewById(R.id.altezzaEditText);
        pesoEditText = (EditText) findViewById(R.id.pesoEditText);
        calcoloButton = (Button) findViewById(R.id.calcoloButton);
        resultTextView.setText("");
    }
    private void process() {
        //Vogliamo che all'evento bottone premuto, il bottone calcoli qualcosa e la inserisca nel result
        calcoloButton.setOnClickListener(this);
        pesoEditText.setOnKeyListener((v, keyCode, event) -> {
            //In particolare se ho premuto fuori dalla tastiera o ho premuto enter, svolgo l'azione di calcolare
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    action();
                    return true;
                default:
                    break;
            }
            return false;
        });
    }
    @SuppressLint("DefaultLocale")
    private void action(){
        if(altezzaEditText != null || pesoEditText != null || !altezzaEditText.getText().toString().equals("0")){
            try {
                //Prendiamo la stringa relativa all'altezza e la inseriamo in un double
                double alt = Double.parseDouble(altezzaEditText.getText().toString());
                //L'altezza va ora convertita in metri, quindi divisa per 100
                alt /= 100;
                //Stessa cosa con il peso
                double pes = Double.parseDouble(pesoEditText.getText().toString());
                //Calcoliamo ora il BMI (la formula è semplicissima, bmi = p/(a*a)
                double bmi = pes / (alt * alt);
                //Vogliamo poi impostare il risultato come testo della nostra textView
                resultTextView.setText(String.format("%2.1f", bmi));

            }catch(Exception e){
                resultTextView.setText("???");
                Toast.makeText(this, "Attenzione, dati non validi",Toast.LENGTH_LONG).show();
            }
        }
        closeKeyboard();
    }
    //Metodo per chiudere la tastiera
    private void closeKeyboard() {
        //La tastiera è un componente di sistema, quindi bisogna chiedere al sistema operativo di poterne avere un riferimento
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //Il metodo nativo per nascondere la tastiera
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}