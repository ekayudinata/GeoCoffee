package com.dionrasmadi.lelangapp.dataClass;

public class DataLelangSaya {

    private String id_barang;
    private String username;
    private String nama_barang;
    private String harga_tawar;
    private String jumlah;
    private String status_tawar;
    private String gambar;

    public DataLelangSaya(String id_barang, String username, String nama_barang,  String jumlah, String harga_tawar, String status_tawar, String gambar) {
        this.id_barang = id_barang;
        this.username = username;
        this.nama_barang = nama_barang;
        this.harga_tawar = harga_tawar;
        this.status_tawar = status_tawar;
        this.gambar = gambar;
        this.jumlah = jumlah;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getGambar() {
        return gambar;
    }


    public String getId_barang() {
        return id_barang;
    }

    public String getUsername() {
        return username;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getHarga_tawar() {
        return harga_tawar;
    }

    public String getStatus_tawar() {
        return status_tawar;
    }
}
