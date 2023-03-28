package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    TextView textSalida;
    float operador1 = 0.0f, operador2 = 0.0f, acc = 0.0f;
    String operacion = "";
    Boolean bflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.ENGLISH);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        tts.setLanguage(Locale.ENGLISH);
                    }
                }
            }
        });
        textSalida = findViewById(R.id.textSalida);
    }

    // Botones
    public void bt0 (View view) {

        if( bflag == true ) {
            textSalida.setText("0");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "0");
    }
    public void bt1 (View view) {
        if( bflag == true ) {
            textSalida.setText("1");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "1");
    }
    public void bt2 (View view) {
        if( bflag == true ) {
            textSalida.setText("2");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "2");
    }
    public void bt3 (View view) {
        if( bflag == true ) {
            textSalida.setText("3");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "3");
    }
    public void bt4 (View view) {
        if( bflag == true ) {
            textSalida.setText("4");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "4");
    }
    public void bt5 (View view) {
        if( bflag == true ) {
            textSalida.setText("5");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "5");
    }
    public void bt6 (View view) {
        if( bflag == true ) {
            textSalida.setText("6");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "6");
    }
    public void bt7 (View view) {
        if( bflag == true ) {
            textSalida.setText("7");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "7");
    }
    public void bt8 (View view) {
        if( bflag == true ) {
            textSalida.setText("8");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "8");
    }
    public void bt9 (View view) {
        if( bflag == true ) {
            textSalida.setText("9");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "9");
    }

    public void btDot(View view) {
        if( bflag == true ) {
            textSalida.setText(".");
            bflag = false;
        }
        String texto = textSalida.getText().toString();
        if(texto.endsWith(".")) {
        }else{
            textSalida.setText(textSalida.getText() + ".");
        }

    }
    public void btMult(View view) {
        if(operacion == "" && textSalida.getText().toString().length() > 0) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "*";
            textSalida.setText("");
        }
    }
    public void btDiv(View view) {
        if(operacion == "" && textSalida.getText().toString().length() > 0) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "/";
            textSalida.setText("");
        }
    }
    public void btSuma(View view) {
        if(operacion == "" && textSalida.getText().toString().length() > 0) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "+";
            textSalida.setText("");
        }

    }
    public void btResta(View view) {
        if (textSalida.getText().toString().equals("")) {
            textSalida.setText("-");
        }
        else if(operacion == "" && textSalida.getText().toString().length() > 0) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "-";
            textSalida.setText("");
        }
    }
    public void btClear(View view) {
        textSalida.setText("");
        operador1 = 0.0f;
        operador2 = 0.0f;
        operacion = "";
        bflag = false;
    }
    public void btIgual(View view) {

        if(textSalida.getText().toString().length() > 0) {
            operador2 = Float.parseFloat(textSalida.getText().toString());

            if(operacion.equals("+")) {
                acc = operador1 + operador2;
                textSalida.setText(String.valueOf(acc));
                tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(operacion.equals("-")) {
                acc = operador1 - operador2;
                textSalida.setText(String.valueOf(acc));
                tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(operacion.equals("*")) {
                acc = operador1 * operador2;
                textSalida.setText(String.valueOf(acc));
                tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(operacion.equals("/")) {
                if(operador2 == 0) {
                    textSalida.setText("Error");
                }
                acc = operador1 / operador2;
                textSalida.setText(String.valueOf(acc));
                tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
            }
            operador1 = 0.0f;
            operador2 = 0.0f;
            operacion = "";
            bflag = true;
        }
        // text to speech
    }
    public void btDelete(View view) {
        if(textSalida.getText().toString().length() > 0) {
            String cadena = textSalida.getText().toString();
            cadena = cadena.substring(0, cadena.length() - 1);
            textSalida.setText(cadena);
        }
    }
}