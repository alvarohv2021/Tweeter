package com.example.tweeter;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);


        mAuth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        Button signUpButton = findViewById(R.id.signUpButton);
        Button logInButton = findViewById(R.id.logInButton);

        //Inicio de sesión
        logInButton.setOnClickListener(view -> {
            mAuth.signInWithEmailAndPassword(
                    email.getText().toString(),
                    password.getText().toString()).addOnCompleteListener(
                    this, task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Si ha iniciado con exito, hace un intent a la página principal y envia el usuario
                            Intent intent = new Intent(MainActivity.this, homePage.class);
                            intent.putExtra("usuario", 0);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        //Registro
        signUpButton.setOnClickListener(view -> {
            mAuth.createUserWithEmailAndPassword(
                    email.getText().toString(),
                    password.getText().toString()).addOnCompleteListener(
                    this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Todo bien", Toast.LENGTH_SHORT).show();

                            //Si se ha registrado con exito, hace un intent a la página principal y envia el usuario
                            Intent intent = new Intent(MainActivity.this, homePage.class);
                            intent.putExtra("usuario", 0+"");
                            startActivity(intent);
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }
}