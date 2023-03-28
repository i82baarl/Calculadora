package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textSalida;
    float operador1 = 0.0f, operador2 = 0.0f, acc = 0.0f;
    String operacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSalida = findViewById(R.id.textSalida);
    }

    public void bt0 (View view) {
        textSalida.setText(textSalida.getText() + "0");
    }

    public void bt1 (View view) {
        textSalida.setText(textSalida.getText() + "1");
    }
    public void bt2(View view) {
        textSalida.setText(textSalida.getText() + "2");
    }
    public void bt3(View view) {
        textSalida.setText(textSalida.getText() + "3");
    }
    public void bt4(View view) {
        textSalida.setText(textSalida.getText() + "4");
    }
    public void bt5(View view) {
        textSalida.setText(textSalida.getText() + "5");
    }
    public void bt6(View view) {
        textSalida.setText(textSalida.getText() + "6");
    }
    public void bt7(View view) {
        textSalida.setText(textSalida.getText() + "7");
    }
    public void bt8(View view) {
        textSalida.setText(textSalida.getText() + "8");
    }
    public void bt9(View view) {
        textSalida.setText(textSalida.getText() + "9");
    }
    public void btDot(View view) {
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
        if (textSalida.getText().toString().equals("")){
            textSalida.setText("-");
        }
        else if(operacion == ""      && textSalida.getText().toString().length() > 0) {
            operador2 = Float.parseFloat(textSalida.getText().toString());
            operacion = "-";
            textSalida.setText("");
        }
    }
    public void btClear(View view) {
        textSalida.setText("");
        operador1 = 0.0f;
        operador2 = 0.0f;
        operacion = "";
    }
    public void btIgual(View view) {

        if(textSalida.getText().toString().length() > 0) {
            operador2 = Float.parseFloat(textSalida.getText().toString());

            if(operacion.equals("+")) {
                acc = operador1 + operador2;
                textSalida.setText(String.valueOf(acc));
            }
            else if(operacion.equals("-")) {
                acc = operador1 - operador2;
                textSalida.setText(String.valueOf(acc));
            }
            else if(operacion.equals("*")) {
                acc = operador1 * operador2;
                textSalida.setText(String.valueOf(acc));
            }
            else if(operacion.equals("/")) {
                if(operador2 == 0) {
                    textSalida.setText("Error");
                }
                acc = operador1 / operador2;
                textSalida.setText(String.valueOf(acc));
            }
            operador1 = 0.0f;
            operador2 = 0.0f;
            operacion = "";
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