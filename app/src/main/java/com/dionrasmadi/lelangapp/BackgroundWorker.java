package com.dionrasmadi.lelangapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.dionrasmadi.lelangapp.dataClass.DataBarang;
import com.dionrasmadi.lelangapp.listDetailBarang.AdapterBarang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.dionrasmadi.lelangapp.LoginActivity.loading;
import static com.dionrasmadi.lelangapp.RegisterActivity.etEmail;
import static com.dionrasmadi.lelangapp.RegisterActivity.loadingRegist;
import static com.dionrasmadi.lelangapp.listDetailBarang.DetailPenjualActivity.adapter;
import static com.dionrasmadi.lelangapp.listDetailBarang.DetailPenjualActivity.barangList;
import static com.dionrasmadi.lelangapp.listDetailBarang.DetailPenjualActivity.recyclerView;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    private String USERNAME="", PASSWORD="";
    private String TYPE="";

    private AlertDialog alertDialog ;
    private Context context ;

    public BackgroundWorker(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        TYPE = params[0];
        String login_url = "https://ondy13.000webhostapp.com/login.php";
        String register_url = "https://ondy13.000webhostapp.com/registrasi.php";
        String listBarang_url = "https://ondy13.000webhostapp.com/barangApi.php";

        switch (TYPE){
            case "login":{

                try {
                    String nim = params[1];
                    String pass = params[2];
                    PASSWORD = pass;

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(nim,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();

                    String result ="";
                    int tmp;
                    while ((tmp = inputStream.read())!=-1){
                        result += (char)tmp;
                    }

                    inputStream.close();
                    httpURLConnection.disconnect();

                    Log.e("result", result);

                    return  result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            case "register":{

                try {
                    String email = params[1];
                    String nama = params[2];
                    String tlf = params[3];
                    String alamat = params[4];
                    String pass = params[5];

                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("nama","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"
                            +URLEncoder.encode("tlf","UTF-8")+"="+URLEncoder.encode(tlf,"UTF-8")+"&"
                            +URLEncoder.encode("alamat","UTF-8")+"="+URLEncoder.encode(alamat,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();

                    String result ="";
                    int tmp;
                    while ((tmp = inputStream.read())!=-1){
                        result += (char)tmp;
                    }

                    inputStream.close();
                    httpURLConnection.disconnect();

                    Log.e("result", result);

                    return  result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            case "listBarang":{
                try {
                    String username = params[1];

                    URL url = new URL(listBarang_url);
                    HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();

                    String result ="";
                    int tmp;
                    while ((tmp = inputStream.read())!=-1){
                        result += (char)tmp;
                    }

                    inputStream.close();
                    httpURLConnection.disconnect();

                    Log.e("result", result);

                    return  result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

        return "Anda Offline";
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setIcon(R.drawable.warning);
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String results) {

        String trr= null;
        switch (TYPE){
            case "login":{
                boolean berhasilLogin = false;
                try {
                    JSONObject root = new JSONObject(results);
                    JSONObject user_data = root.getJSONObject("user_data");
                    berhasilLogin = true;

                    USERNAME = user_data.getString("username");

                } catch (JSONException e) {
                    e.printStackTrace();
                    trr = "Exception: "+e.getMessage();
                }

                if (berhasilLogin){
                    Intent i  = new Intent(context, HomeActivity.class);
                    i.putExtra("username",USERNAME);
                    i.putExtra("password", PASSWORD);
                    context.startActivity(i);
                    ((LoginActivity)context).finish();
                }

                else {
                    alertDialog.setMessage(results);
                    loading.dismiss();
                    alertDialog.show();
                }

                break;
            }

            case "register":{
                loadingRegist.dismiss();
                if (results.equals("Registrasi berhasil!")){
                    new AlertDialog.Builder(context)
                            .setMessage(results)
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //finish activity
                                    ((Activity)context).finish();
                                }
                            })
                            .show();

                }
                else{
                    etEmail.setError("email sudah terdaftar");
                    new AlertDialog.Builder(context)
                            .setMessage(results)
                            .setIcon(R.drawable.warning)
                            .setCancelable(false)
                            .setPositiveButton("Ok", null)
                            .show();
                }

                break;
            }

            case "listBarang":{
                try {
                    JSONArray array = new JSONArray(results);

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
                        Log.e("coba i", String.valueOf(i));
                    }

                    adapter = new AdapterBarang(context, barangList);
                    recyclerView.setAdapter(adapter);

//                    USERNAME = data_barang.getString("username");

                } catch (JSONException e) {
                    e.printStackTrace();
                    trr = "Exception: "+e.getMessage();
                }
                break;
            }


        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
