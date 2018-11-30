package com.dionrasmadi.lelangapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etNIM, etPass;
    public static Dialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_login);

        etNIM = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_password);

        loading = new Dialog(LoginActivity.this);
        loading.setContentView(R.layout.loading);
        loading.setCanceledOnTouchOutside(false);
    }

    public void onLogin(View view){

        String username = etNIM.getText().toString();
        String password = etPass.getText().toString();

        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"Silahkan input username dan password Anda" , Toast.LENGTH_SHORT).show();
        }else {
            loading.show();
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("login", username, password);
        }

    }

    public void toRegister(View view){
        Intent i  = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
