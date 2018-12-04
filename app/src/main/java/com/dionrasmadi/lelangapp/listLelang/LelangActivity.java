package com.dionrasmadi.lelangapp.listLelang;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataBarangLelang;

import java.util.List;

public class LelangActivity extends AppCompatActivity {

    Intent intent;

    static ProgressBar progressBar;
    static RecyclerView recyclerView;
    static List<DataBarangLelang> barang;
    static LelangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang);

        Toolbar toolbar = findViewById(R.id.toolbar_lelang);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton floatingActionButton=(FloatingActionButton) findViewById(R.id.add_lelang);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(view.getContext(),"tambah Lelang",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
