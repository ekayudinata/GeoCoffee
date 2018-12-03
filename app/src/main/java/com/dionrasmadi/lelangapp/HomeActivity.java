package com.dionrasmadi.lelangapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dionrasmadi.lelangapp.listJual.ListPenjualActivity;
import com.dionrasmadi.lelangapp.listLelang.LelangActivity;
import com.dionrasmadi.lelangapp.mapPenjual.MapPetaniActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

public class HomeActivity extends AppCompatActivity {

    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;


    public static Dialog dialog;
    private  String latitude="", longitude="",username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        username  = intent.getStringExtra("username");

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
        if ((latitude.equals("")) && (longitude.equals(""))){

            new AlertDialog.Builder(this)
                    .setTitle("Informasi")
                    .setMessage("Lokasi Anda Masih Kosong,Masukkan Lokasi anda Sekarang ?")
                    .setCancelable(false)
                    .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HomeActivity.this, "masuk ke maps", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("tidak",null).show();

        } else{
            Intent i = new Intent(HomeActivity.this, LelangActivity.class );
            i.putExtra("username",username);
            startActivity(i);

        }
    }

}
