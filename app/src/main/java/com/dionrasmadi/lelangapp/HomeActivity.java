package com.dionrasmadi.lelangapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
    import com.google.android.gms.location.places.Place;
    import com.google.android.gms.location.places.ui.PlacePicker;
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

        requestPermission();

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

                            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                            try {
                                Intent intent = builder.build(HomeActivity.this);
                                startActivityForResult(intent,PLACE_PICKER_REQUEST);
                            } catch (GooglePlayServicesRepairableException e) {
                                e.printStackTrace();
                            } catch (GooglePlayServicesNotAvailableException e) {
                                e.printStackTrace();
                            }

                        }
                    })
                    .setNegativeButton("tidak",null).show();

        } else{
            Intent i = new Intent(HomeActivity.this, LelangActivity.class );
            i.putExtra("username",username);
            startActivity(i);

        }
    }

    private void requestPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(HomeActivity.this,data);
                Double lat = place.getLatLng().latitude;
                Double longt = place.getLatLng().longitude;
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longt);

                Log.d("longt : ", longitude);
                new AlertDialog.Builder(this)
                        .setTitle("Lokasi anda :" )
                        .setMessage("Latitude :"+latitude+
                                "Longitude :"+longitude+
                                "Simpan Lokasi ? ")
                        .setCancelable(false)
                        .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "panggil background worker", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("tidak",null).show();

            }
        }

    }
}
