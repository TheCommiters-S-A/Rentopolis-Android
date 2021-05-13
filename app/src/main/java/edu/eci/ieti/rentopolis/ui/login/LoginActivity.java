package edu.eci.ieti.rentopolis.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.eci.ieti.rentopolis.NavigationDrawer;
import edu.eci.ieti.rentopolis.R;
import edu.eci.ieti.rentopolis.Register;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button loginButton;
    private EditText emailView;
    private EditText passwordView;
    private TextView signUpView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = (EditText) findViewById(R.id.username);
        passwordView= (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

    }

    public void onSignUp(View view){
        startActivity( new Intent(LoginActivity.this, Register.class));
        finish();
    }

    public void onLogin(View view){

        emailView.setError(null);
        passwordView.setError(null);
        String email = emailView.getText().toString();
        String password  = emailView.getText().toString();
        if(validCredentials(email, password)){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, NavigationDrawer.class));
                        finish();
                    } else {
                        showInvalidDataError();
                    }
                }
            });
        }else{
            Toast.makeText(LoginActivity.this, "Verifica los Campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void showInvalidDataError(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                emailView.setError("Verify Your email!");
                passwordView.setError("verify Your Password!");
            }
        });
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