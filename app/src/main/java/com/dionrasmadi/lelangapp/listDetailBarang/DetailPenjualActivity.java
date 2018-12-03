package com.dionrasmadi.lelangapp.listDetailBarang;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dionrasmadi.lelangapp.BackgroundWorker;
import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataBarang;

import java.util.ArrayList;
import java.util.List;

public class DetailPenjualActivity extends AppCompatActivity {
    TextView tvNama, tvTlf, tvAlamat;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public static ProgressBar progressBar2;

    public static RecyclerView recyclerView;
    public static List<DataBarang> barangList;
    public static AdapterBarang adapter;
    public static LinearLayout layout_no_data;

    private static final String BARANG_URL = "https://ondy13.000webhostapp.com/barangApi.php";

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penjual);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvNama = findViewById(R.id.tv_nama);
        tvTlf = findViewById(R.id.tv_tlf);
        tvAlamat = findViewById(R.id.tv_alamat);
        layout_no_data = findViewById(R.id.layout_no_data);

        Intent i = getIntent();
        final Bundle extras = i.getExtras();
        if (extras != null) {
            username = extras.getString("username");
            tvNama.setText(extras.getString("nama"));
            tvTlf.setText(extras.getString("tlf"));
            tvAlamat.setText(extras.getString("alamat"));
        }

        progressBar2 = findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.rv_list_lelang);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        barangList = new ArrayList<>();
        loadDataBarang();

    }

    private void loadDataBarang() {
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("listBarang", username);
//        if(barangList.size()>=0) {
////            progressBar2.setVisibility(View.GONE);
//
//        }

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, BARANG_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i=0; i<array.length(); i++){
                                JSONObject dataObject = array.getJSONObject(i);

                                String id_barang = dataObject.getString("id_barang");
                                String username = dataObject.getString("username");
                                String nama_barang = dataObject.getString("nama_brg");
                                String jumlah = dataObject.getString("jumlah");
                                String nama_penjual = dataObject.getString("nama_penjual");
                                String alamat_penjual = dataObject.getString("alamat");
                                String harga_tawar = dataObject.getString("harga");
                                String status_tawar = dataObject.getString("status");
                                String gambar = dataObject.getString("gambar");

                                DataBarang dataBarang = new DataBarang(id_barang, username,
                                        nama_barang, nama_penjual, alamat_penjual, jumlah,
                                        harga_tawar, status_tawar, gambar);
                                barangList.add(dataBarang);

                            }

                            adapter = new AdapterBarang(DetailPenjualActivity.this, barangList);
                            recyclerView.setAdapter(adapter);

//                            progressBar2.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailPenjualActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(DetailPenjualActivity.this).add(stringRequest);*/
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
