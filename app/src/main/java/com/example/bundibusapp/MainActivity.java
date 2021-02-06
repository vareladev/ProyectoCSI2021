package com.example.bundibusapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.example.bundibusapp.components.Message;


public class MainActivity extends AppCompatActivity {
    private Message msg;
    private EditText edit_email;
    private EditText edit_password;
    private boolean signup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_password = (EditText)findViewById(R.id.edit_password);

        msg = new Message();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            signup = extras.getBoolean("signup");
        }

        if(signup){
            msg.showMsg(this,"\n¡Registro completo! Ahora puedes iniciar sesión.", 1);
        }

    }

    public void redirectRegisterActivity(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void redirectMenuActivity(View v) {
        //verificando usuario
        String email = edit_email.getText().toString();
        String password = edit_password.getText().toString();

        if(isEmpty(edit_email) || isEmpty(edit_password)){
            msg.showMsg(this,"\nNo es posible iniciar sesión. Verifique si los campos de correo electrónico o contraseña están vacios.", 2);
        }
        else if(email.equals("admin") && password.equals("admin")){
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        }
        else{
            msg.showMsg(this,"\nNo es posible iniciar sesión. Usuario no encontrado.\nVerifique si su correo electrónico o contraseña estan bien escritos.", 2);
        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }
}