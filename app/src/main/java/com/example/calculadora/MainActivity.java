package com.example.calculadora;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String language = "es_ES";
    TextView textSalida;
    float operador1 = 0.0f, operador2 = 0.0f, acc = 0.0f;
    String operacion = "";
    Boolean bflag = false; // Flag para borrar el accumulador
    Boolean nflag = false; // Flag para negar un numero
    Boolean mflag = false; // Flag para el mute del tts
    TextToSpeech tts;
    ImageButton sttButton;
    ImageView muteButton;
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextToSpeech
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    Locale locSpanish = new Locale("es", "ES");
                    int result = tts.setLanguage(locSpanish);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        tts.setLanguage(Locale.ENGLISH);
                    }
                }
            }
        });

        //SpeechToText

        sttButton = findViewById(R.id.btVoice);
        muteButton =(ImageView)findViewById(R.id.btMute);
        sttButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToTalk();
            }
        });

        textSalida = findViewById(R.id.textSalida);
    }

    // Speech to Text

    private void pushToTalk() {

        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        String mathExpression = "([0-9]+[\\.\\,][0-9]*|[0-9]*[\\.\\,][0-9]+|[0-9]+)" +
                "(\\s*(más|menos|por|dividido|multiplicado por|dividido por)\\s*" +
                "([0-9]+[\\.\\,][0-9]*|[0-9]*[\\.\\,][0-9]+|[0-9]+))*";

        String lang = null;
        String country = null;
        if (language != null) {
            lang = language.substring(0, language.lastIndexOf("_"));
            country = language.substring(language.lastIndexOf("_") + 1, language.length()).toUpperCase();
        }

        Locale locale = new Locale(lang, country);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, locale);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, locale);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, locale);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_RESULTS, locale);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, mathExpression);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Di tu operación...");

        try {
            // no error
            startActivityForResult(recognizerIntent, REQ_CODE_SPEECH_INPUT);
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


                    String aux = String.valueOf(result.get(0));

                    /*aux.replaceAll("cero","0").replaceAll("uno","1").replaceAll("dos", "2")
                        .replaceAll("tres", "3").replaceAll("cuatro", "4").replaceAll("cinco","5")
                        .replaceAll("seis", "6").replaceAll("siete", "7").replaceAll("ocho","8")
                        .replaceAll("nueve", "9").replaceAll("diez", "10").replaceAll("once","11")
                        .replaceAll( "doce","12").replaceAll("trece","13").replaceAll("catorce","14")
                        .replaceAll("quince","15").replaceAll("dieciseis","16").replaceAll("diecisiete","17")
                        .replaceAll("dieciocho","18").replaceAll("diecinueve","19").replaceAll("veinte","20")
                        .replaceAll("treinta","30").replaceAll("cuarenta","40").replaceAll("cincuenta","50")
                        .replaceAll("sesenta","60").replaceAll("setenta","70").replaceAll("ochenta","80")
                        .replaceAll("noventa","90").replaceAll("cien", "100").replaceAll("más","+")
                        .replaceAll("menos","-").replaceAll("por","*").replaceAll("entre","/");*/

                    String operation[] = aux.split(" ");
                    operador1 = Float.parseFloat(operation[0].toString());
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
                    textSalida.setText(String.valueOf(acc));
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
        nflag = false;
    }
    public void bt1 (View view) {
        if( bflag == true ) {
            textSalida.setText("1");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "1");
        nflag = false;
    }
    public void bt2 (View view) {
        if( bflag == true ) {
            textSalida.setText("2");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "2");
        nflag = false;
    }
    public void bt3 (View view) {
        if( bflag == true ) {
            textSalida.setText("3");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "3");
        nflag = false;
    }
    public void bt4 (View view) {
        if( bflag == true ) {
            textSalida.setText("4");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "4");
        nflag = false;
    }
    public void bt5 (View view) {
        if( bflag == true ) {
            textSalida.setText("5");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "5");
        nflag = false;
    }
    public void bt6 (View view) {
        if( bflag == true ) {
            textSalida.setText("6");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "6");
        nflag = false;
    }
    public void bt7 (View view) {
        if( bflag == true ) {
            textSalida.setText("7");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "7");
        nflag = false;
    }
    public void bt8 (View view) {
        if( bflag == true ) {
            textSalida.setText("8");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "8");
        nflag = false;
    }
    public void bt9 (View view) {
        if( bflag == true ) {
            textSalida.setText("9");
            bflag = false;
        }
        textSalida.setText(textSalida.getText() + "9");
        nflag = false;
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
        if(textSalida.getText().toString().equals("-")) {

        }
        else if(operacion == "" && textSalida.getText().toString().length() > 0 && nflag == false) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "*";
            textSalida.setText("");
        }
    }
    public void btDiv(View view) {
        if(textSalida.getText().toString().equals("-")) {

        }
        else if(operacion == "" && textSalida.getText().toString().length() > 0 && nflag == false) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "/";
            textSalida.setText("");
        }
    }
    public void btSuma(View view) {
        if(textSalida.getText().toString().equals("-")) {
        }
        else if(operacion == "" && textSalida.getText().toString().length() > 0 && nflag == false) {
            operador1 = Float.parseFloat(textSalida.getText().toString());
            operacion = "+";
            textSalida.setText("");
        }

    }
    public void btResta(View view) {
        if(textSalida.getText().toString().equals("-")) {
        }
        else if (textSalida.getText().toString().equals("")) {
            textSalida.setText("-");
            nflag = true;
        }
        else if(operacion == "" && textSalida.getText().toString().length() > 0 && nflag == false) {
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

        if(textSalida.getText().toString().length() > 0 && nflag == false) {
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
            if(mflag == false) {
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
    public void btMute(View view) {
        if(mflag == false) {
            muteButton.setImageResource(R.drawable.mute);
            mflag = true;
        }
        else {
            muteButton.setImageResource(R.drawable.mute_off);
            mflag = false;
        }
    }

}