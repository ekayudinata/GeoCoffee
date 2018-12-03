package com.dionrasmadi.lelangapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dionrasmadi.lelangapp.listJual.ListPenjualActivity;
import com.dionrasmadi.lelangapp.listLelang.LelangActivity;
import com.dionrasmadi.lelangapp.mapPenjual.MapPetaniActivity;

public class HomeActivity extends AppCompatActivity {

    Intent intent ;
    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = getIntent();

        dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.layout_loading);
        dialog.setCanceledOnTouchOutside(false);
    }

    public void toListPenjual(View view){
        Intent i = new Intent(this, ListPenjualActivity.class);
        startActivity(i);
    }

    public void toMapPenjual(View view) {
        Intent i = new Intent(this, MapPetaniActivity.class);
        startActivity(i);
    }

    public void toLelangSaya(View view) {
//        dialog.show();

        Log.d("latitude",intent.getStringExtra("latitude"));

//        String username = intent.getStringExtra("username");
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute("cekLelang", username);

//        Intent i = new Intent(this, LelangActivity.class);
//        i.putExtra("username",intent.getStringExtra("username"));
//        startActivity(i);
    }

    @Override
    public Intent getIntent() {
        return intent;
    }
}
