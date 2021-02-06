package com.example.bundibusapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bundibusapp.components.Message;

public class RegisterActivity extends AppCompatActivity {
    private Message msg;

    private EditText edit_name;
    private EditText edit_lastname;
    private EditText edit_email;
    private EditText edit_password;
    private EditText edit_password_validation;
    private CheckBox chk_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        msg = new Message();

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_lastname = (EditText)findViewById(R.id.edit_lastname);
        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_password = (EditText)findViewById(R.id.edit_password);
        edit_password_validation = (EditText)findViewById(R.id.edit_password_validation);
        chk_terms = (CheckBox)findViewById(R.id.chk_terms);
    }

    public void redirectMainActivity(View v) {
        if(isEmpty(edit_name) || isEmpty(edit_lastname) || isEmpty(edit_email) || isEmpty(edit_password) || isEmpty(edit_password_validation)){
            msg.showMsg(this,"\nNo es posible completar el registro. Verifique si alguno de los campos esta vacio.", 2);
        }
        else if(!chk_terms.isChecked()) {
            msg.showMsg(this,"\nNo es posible completar el registro. Debe aceptar las condiciones y terminos del servicio.", 2);
        }
        else if(!edit_password.getText().toString().equals(edit_password_validation.getText().toString())){
            msg.showMsg(this,"\nLas contraseñas ingresadas no coinciden.", 2);
        }
        else if(!isValidEmail(edit_email.getText().toString())){
            msg.showMsg(this,"\nFormato de correo inválido.", 2);
        }
        else{
            Intent i = new Intent(this, MainActivity.class);
            boolean response = true;
            i.putExtra("signup", response);
            startActivity(i);
        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}