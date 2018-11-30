package com.dionrasmadi.lelangapp.mapPenjual;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.listDetailBarang.DetailPenjualActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapPetaniActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] nama, noTelepon, alamat,username;
    int numData;
    LatLng latLng[];
    Boolean markerD[];
    private Double[] latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_petani);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void getLokasi(){
        String USER_URL = "https://ondy13.000webhostapp.com/api.php";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, USER_URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        numData = response.length();
                        Log.d("DEBUG_", "Parse JSON");
                        latLng = new LatLng[numData];
                        markerD = new Boolean[numData];
                        nama = new String[numData];
                        username = new String[numData];
                        alamat = new String[numData];
                        noTelepon = new String[numData];
                        latitude = new Double[numData];
                        longitude = new Double[numData];

                        for (int i = 0; i < numData; i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                username[i] = data.getString("username");
                                nama[i] = data.getString("nama");
                                alamat[i] = data.getString("alamat");
                                noTelepon[i] = data.getString("tlf");
                                latLng[i] = new LatLng(data.getDouble("latitude"),
                                        data.getDouble("longitude"));
                                latitude[i] = data.getDouble("latitude");
                                longitude[i] = data.getDouble("longitude");


                                markerD[i] = false;

                                if (latLng[i]!=null){
                                    mMap.addMarker(new MarkerOptions()
                                            .position(latLng[i])
                                            .title(nama[i])
                                            .snippet(alamat[i])
                                            .icon(BitmapDescriptorFactory.defaultMarker()));
                                }

                            } catch (JSONException je) {
                                je.printStackTrace();
                            }

                            if (latLng[i]!=null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 15.5f));
                            }
                        }
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Log.d("DEBUG_", "Marker clicked");
                                for (int i = 0; i < numData; i++) {

                                    if (marker.getTitle().equals(nama[i])) {
                                        if (markerD[i]) {
                                            Log.d("DEBUG_", "panggil activity");
//
                                            Intent intent = new Intent(MapPetaniActivity.this, DetailPenjualActivity.class);
                                            Bundle extras = new Bundle();
                                            extras.putString("username",username[i]);
                                            extras.putString("nama", nama[i]);
                                            extras.putString("tlf", noTelepon[i]);
                                            extras.putString("alamat",alamat[i]);
                                            intent.putExtras(extras);
                                            startActivity(intent);
                                            markerD[i] = false;
                                        } else {
                                            Log.d("DEBUG_", "show info");
                                            // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.5f));
                                            markerD[i] = true;
                                            marker.showInfoWindow();
                                            Toast ts = Toast.makeText(MapPetaniActivity.this, "Tap once again on marker, for detail GeoCoffee field", Toast.LENGTH_SHORT);
                                            TextView v = (TextView) ts.getView().findViewById(android.R.id.message);
                                            if (v != null)
                                                v.setGravity(Gravity.CENTER);
                                            ts.show();
                                        }
                                    } else {
                                        markerD[i] = false;
                                    }
                                }
                                return false;
                            }

                        });
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapPetaniActivity.this);
                        builder.setTitle("Error!");
                        builder.setMessage("No Internet Connection");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getLokasi();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
        Volley.newRequestQueue(this).add(request);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLokasi();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
