package com.dionrasmadi.lelangapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    public static EditText etEmail, etNama, etTlf, etAlamat, etPass;
    Button btRegister;
    public static Dialog loadingRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email);
        etNama = findViewById(R.id.et_nama);
        etTlf = findViewById(R.id.et_tlf);
        etAlamat = findViewById(R.id.et_alamat);
        etPass = findViewById(R.id.et_password_regist);
        btRegister = findViewById(R.id.bt_regist);

        loadingRegist = new Dialog(RegisterActivity.this);
        loadingRegist.setContentView(R.layout.loading);
        loadingRegist.setCanceledOnTouchOutside(false);
    }

    public void onRegister(View view){
        String email = etEmail.getText().toString();
        String nama = etNama.getText().toString();
        String tlf = etTlf.getText().toString();
        String alamat = etAlamat.getText().toString();
        String pass = etPass.getText().toString();

        if(email.equals("") || nama.equals("") || tlf.equals("") || alamat.equals("") || pass.equals("")){
            Toast.makeText(this,"Silahkan isikan semua form!" , Toast.LENGTH_SHORT).show();
        }else {
            loadingRegist.show();
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("register", email, nama, tlf, alamat, pass);
        }

    }
}
