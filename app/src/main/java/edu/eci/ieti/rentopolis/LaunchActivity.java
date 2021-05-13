package edu.eci.ieti.rentopolis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import edu.eci.ieti.rentopolis.ui.login.LoginActivity;

public class LaunchActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(LaunchActivity.this, NavigationDrawer.class));

        } else {
            startActivity( new Intent( this, LoginActivity.class ) );
        }
        finish();
    }
}