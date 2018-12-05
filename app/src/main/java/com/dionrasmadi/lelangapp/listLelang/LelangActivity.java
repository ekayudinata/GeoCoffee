package com.dionrasmadi.lelangapp.listLelang;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dionrasmadi.lelangapp.BackgroundWorker;
import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataBarangLelang;
import com.dionrasmadi.lelangapp.dataClass.DataLelangSaya;

import java.util.ArrayList;
import java.util.List;

public class LelangActivity extends AppCompatActivity {

    TextView namaUser, noTeleponUser,alamatUser;

    public static ProgressBar progressBarlealang;
    public static RecyclerView recyclerViewlelang;
    public static List<DataLelangSaya> barang;
    public static LelangAdapter adapterlelang;

    private  String latitude="", longitude="",username="",nama="",alamat="",noTlf="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang);
        namaUser = (TextView) findViewById(R.id.nama_user);
        noTeleponUser = (TextView) findViewById(R.id.no_telepon_user);
        alamatUser = (TextView) findViewById(R.id.alamat_user);

        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        username  = intent.getStringExtra("username");
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        noTlf = intent.getStringExtra("tfl");

        namaUser.setText(nama);
        noTeleponUser.setText(noTlf);
        alamatUser.setText(alamat);


        progressBarlealang = findViewById(R.id.progressBar_lelang);
        recyclerViewlelang = findViewById(R.id.rv_list_lelang_saya);
        recyclerViewlelang.setHasFixedSize(true);
        recyclerViewlelang.setLayoutManager(new LinearLayoutManager(this));
        barang = new ArrayList<>();


        Toolbar toolbar = findViewById(R.id.toolbar_lelang);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        FloatingActionButton floatingActionButton=(FloatingActionButton) findViewById(R.id.add_lelang);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent( LelangActivity.this,TambahLelangActivity.class);
                startActivity(intent);
            }
        });

      loadData();

    }


    public void loadData(){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("listLelangSaya", username);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
