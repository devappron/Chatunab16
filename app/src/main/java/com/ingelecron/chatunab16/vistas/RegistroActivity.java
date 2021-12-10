package com.ingelecron.chatunab16.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.ingelecron.chatunab16.R;
import com.ingelecron.chatunab16.utiles.ValidarCorreo;

public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText tie_nombre, tie_correo, tie_contraseña, tie_confirmarContra;
    private Button b_registrar;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        view= findViewById(R.id.cl_registro);

        tie_nombre=findViewById(R.id.tie_nombre);
        tie_correo=findViewById(R.id.tie_correo);
        tie_contraseña=findViewById(R.id.tie_contraseña);
        tie_confirmarContra=findViewById(R.id.tie_confirmarContra);
        b_registrar=findViewById(R.id.b_registrar);

        tie_nombre.addTextChangedListener(textWatcher);
        tie_correo.addTextChangedListener(textWatcher);
        tie_contraseña.addTextChangedListener(textWatcher);
        tie_confirmarContra.addTextChangedListener(textWatcher);

        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroControlador.registro(RegistroActivity.this, getNombre(), getCorreo(), getContraseña());
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

        String nombre= getNombre().trim();
        String correo= getCorreo().trim();
        String contraseña= getContraseña().trim();
        String confirmarContra= getConfirmarContra().trim();

        if((nombre.length()>2) && ValidarCorreo.validar(correo) && contraseña.length()>5 && confirmarContra.equals(contraseña)){
            b_registrar.setEnabled(true);
            return true;
        }else {
            b_registrar.setEnabled(false);
            return false;
        }
    }

    private String getNombre() {
        return tie_nombre.getText().toString();
    }

    private String getCorreo() {
        return tie_correo.getText().toString();
    }

    private String getContraseña() {
        return tie_contraseña.getText().toString();
    }

    private String getConfirmarContra() {
        return tie_confirmarContra.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        ocultarTeclado(RegistroActivity.this, view);

        tie_nombre.clearFocus();
        tie_correo.clearFocus();
        tie_contraseña.clearFocus();
        tie_confirmarContra.clearFocus();

        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {

        InputMethodManager inputMethodManager= (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}