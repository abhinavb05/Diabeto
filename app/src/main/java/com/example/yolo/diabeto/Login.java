package com.example.yolo.diabeto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    FirebaseAuth auth;
    ProgressBar progressBar;
    Button btn;
    EditText un;
    EditText pas;
    TextView sup;
    String ps,usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar)findViewById(R.id.progressBar4);
        btn = (Button) findViewById(R.id.btn_login);
        un = (EditText)findViewById(R.id.input_email);
        pas = (EditText)findViewById(R.id.input_password);
        sup = (TextView)findViewById(R.id.link_signup);
        auth = FirebaseAuth.getInstance();
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usn = un.getText().toString();
                ps = pas.getText().toString();
                if (TextUtils.isEmpty(usn)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(ps)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ps.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(usn,ps)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (ps.length() < 6) {
                                        pas.setError("Short Password");
                                    } else {
                                        Toast.makeText(Login.this,"Authentication failed!", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this,"Welcome!", Toast.LENGTH_LONG).show();
                                    //Intent intent = new Intent(Login.this, MainActivity.class);
                                    //startActivity(intent);
                                    //finish();
                                }
                            }
                        });
            }
        });
    }
}
