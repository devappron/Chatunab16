package com.ingelecron.chatunab16.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.ingelecron.chatunab16.R;
import com.ingelecron.chatunab16.utiles.ValidarCorreo;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText tie_correo, tie_contraseña;
    private TextView tv_recuperarContra;
    private Button b_login, b_registro;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        view= findViewById(R.id.cl_login);

        tie_correo=findViewById(R.id.tie_correo);
        tie_contraseña=findViewById(R.id.tie_contraseña);
        tv_recuperarContra=findViewById(R.id.tv_recuperarContra);
        b_login=findViewById(R.id.b_login);
        b_registro=findViewById(R.id.b_registro);

        tie_correo.addTextChangedListener(textWatcher);
        tie_contraseña.addTextChangedListener(textWatcher);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginControlador.login(LoginActivity.this, getCorreo(), getContraseña());
            }
        });

        tv_recuperarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecuperarContraActivity.class));
            }
        });

        b_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }

    private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            habilitar();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean habilitar(){

        String correo= getCorreo().trim();
        String contraseña= getContraseña().trim();

        if(ValidarCorreo.validar(correo) && contraseña.length()>5){
            b_login.setEnabled(true);
            return true;
        }else {
            b_login.setEnabled(false);
            return false;
        }
    }


    private String getCorreo() {
        return tie_correo.getText().toString();
    }

    private String getContraseña() {
        return tie_contraseña.getText().toString();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        ocultarTeclado(LoginActivity.this, view);

        tie_correo.clearFocus();
        tie_contraseña.clearFocus();

        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {

        InputMethodManager inputMethodManager= (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}