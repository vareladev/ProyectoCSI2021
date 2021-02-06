package com.example.bundibusapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bundibusapp.components.Message;


public class MenuActivity extends AppCompatActivity {
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        msg = new Message();
        msg.showMsg(this,"\n¡Bienvenido Admin! has iniciado sesión correctamente.\nPor razones de seguridad, se registrará la hora y el usuario utilizado para realizar el inicio de sesión.", 1);
    }

    public void redirectRouteManagementActivity(View v) {
        Intent i = new Intent(this, RouteManagementActivity.class);
        startActivity(i);
    }
}