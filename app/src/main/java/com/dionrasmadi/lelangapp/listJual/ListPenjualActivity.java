package com.dionrasmadi.lelangapp.listJual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListPenjualActivity extends AppCompatActivity {
    ProgressBar progressBar;

    RecyclerView recyclerView;
    List<DataUser> users;
    AdapterPenjual adapter;
    private static final String USER_URL = "http://lelang.freeoda.com/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_penjual);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.rv_list_penjual);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        users = new ArrayList<>();

        loadDataPenjual();
    }

    private void loadDataPenjual() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i=0; i<array.length(); i++){

                                JSONObject dataObject = array.getJSONObject(i);

                                String username = dataObject.getString("username");
                                String nama = dataObject.getString("nama");
                                String tlf = dataObject.getString("tlf");
                                String alamat = dataObject.getString("alamat");
                                String longitude = dataObject.getString("longitude");
                                String latitude = dataObject.getString("latitude");

                                Log.e("api", nama);

                                DataUser dataUser = new DataUser (username, nama, tlf, alamat, longitude, latitude);
                                users.add(dataUser);
                            }

                            adapter = new AdapterPenjual(ListPenjualActivity.this, users);
                            recyclerView.setAdapter(adapter);

                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListPenjualActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(ListPenjualActivity.this).add(stringRequest);
    }
}
