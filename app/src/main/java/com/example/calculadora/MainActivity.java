package com.example.calculadora;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textSalida;
    float operador1 = 0.0f, operador2 = 0.0f, acc = 0.0f;
    String operacion = "";
    Boolean bflag = false;
    TextToSpeech tts;
    ImageButton stt;
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextToSpeech
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

        //SpeechToText

        stt = findViewById(R.id.btVoice);

        stt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToTalk();
            }
        });

        //

        textSalida = findViewById(R.id.textSalida);
    }

    // Speech to Text

    private void pushToTalk() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        String language = "en-GB";
        String mathExpression = "([0-9]+[\\.\\,][0-9]*|[0-9]*[\\.\\,][0-9]+|[0-9]+)(\\s*(plus|minus|times|divided by|multiply by|divide by|add|subtract|multiply|divide)\\s*([0-9]+[\\.\\,][0-9]*|[0-9]*[\\.\\,][0-9]+|[0-9]+))*";
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Di tu operación...");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, true);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 4000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 4000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 4000);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, mathExpression);

        try {
            // no error
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null!= data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String operation[] = String.valueOf(result.get(0)).split(" ");
                    operador1 = Float.parseFloat(operation[1].toString());
                    operacion = operation[1];
                    operador2 = Float.parseFloat(operation[2].toString());

                    if(operacion.equals("+")) {
                        acc = operador1 + operador2;
                    }
                    else if(operacion.equals("-")) {
                        acc = operador1 - operador2;
                    }
                    else if(operacion.equals("*")) {
                        acc = operador1 * operador2;
                    }
                    else if(operacion.equals("/")) {
                        if(operador2 == 0) {
                            textSalida.setText("Error");
                        }
                        acc = operador1 / operador2;
                    }
                    //textSalida.setText(String.valueOf(acc));
                    tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
                    operador1 = 0.0f;
                    operador2 = 0.0f;
                    operacion = "";
                    bflag = true;
                }
                break;
            }
        }
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

    //Operadores & Operaciones
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
            }
            else if(operacion.equals("-")) {
                acc = operador1 - operador2;
            }
            else if(operacion.equals("*")) {
                acc = operador1 * operador2;
            }
            else if(operacion.equals("/")) {
                if(operador2 == 0) {
                    textSalida.setText("Error");
                }
                acc = operador1 / operador2;
            }
            textSalida.setText(String.valueOf(acc));
            tts.speak(String.valueOf(acc), TextToSpeech.QUEUE_FLUSH, null);
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