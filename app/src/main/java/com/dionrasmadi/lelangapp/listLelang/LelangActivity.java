package com.dionrasmadi.lelangapp.listLelang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

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
    }
}
