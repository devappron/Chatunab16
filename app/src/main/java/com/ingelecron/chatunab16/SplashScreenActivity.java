package com.ingelecron.chatunab16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ingelecron.chatunab16.vistas.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();

        if(usuario==null){
            iniciarNuevaActividad(LoginActivity.class);
        }else {
            iniciarNuevaActividad(MainActivity.class);
        }
    }

    private void iniciarNuevaActividad(Class clase) {
        startActivity(new Intent(this, clase));
        finish();
    }
}