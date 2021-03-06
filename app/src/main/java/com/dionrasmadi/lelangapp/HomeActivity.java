package com.dionrasmadi.lelangapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dionrasmadi.lelangapp.listJual.ListPenjualActivity;
import com.dionrasmadi.lelangapp.listLelang.LelangActivity;
import com.dionrasmadi.lelangapp.mapPenjual.MapPetaniActivity;

public class HomeActivity extends AppCompatActivity {

    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = getIntent();
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
        Intent i = new Intent(this, LelangActivity.class);
        i.putExtra("username",intent.getStringExtra("username"));
        startActivity(i);
    }
}
