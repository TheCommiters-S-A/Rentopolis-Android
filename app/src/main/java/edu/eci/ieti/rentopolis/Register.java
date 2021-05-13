package edu.eci.ieti.rentopolis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import edu.eci.ieti.rentopolis.ui.login.LoginActivity;

public class Register extends AppCompatActivity {

    private EditText emailView;
    private EditText nameView;
    private EditText lastNameView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailView = (EditText) findViewById(R.id.username);
        nameView = (EditText) findViewById(R.id.name);
        lastNameView = (EditText) findViewById(R.id.lastName);
        passwordView = (EditText) findViewById(R.id.password);
    }

    public void onSignUp(View view){

    }

    public void onSignIn(View view){
        startActivity(new Intent(Register.this, LoginActivity.class));
        finish();
    }

    private boolean validCredentials(String email, String password){

        View focusView = null;
        if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
        }
        if (!isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
        }
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
        }
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = emailView;
        }

        if (focusView != null) {
            focusView.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        /**Si la cadena contiene el caracter @ es un email valido*/
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        /**Si la cadena supera los 4 caracteres es una contraseña valida*/
        return password.length() > 5;
    }
}