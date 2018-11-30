package com.dionrasmadi.lelangapp.dataClass;

public class DataBarang {
    private String id_barang;
    private String username;
    private String nama_barang;
    private String nama_penjual;
    private String alamat_penjual;
    private String harga_tawar;
    private String jumlah;
    private String status_tawar;
    private String gambar;

    public DataBarang(String id_barang, String username, String nama_barang, String nama_penjual, String alamat_penjual, String jumlah, String harga_tawar, String status_tawar, String gambar) {
        this.id_barang = id_barang;
        this.username = username;
        this.nama_barang = nama_barang;
        this.nama_penjual = nama_penjual;
        this.alamat_penjual = alamat_penjual;
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

    public String getNama_penjual() {
        return nama_penjual;
    }

    public String getAlamat_penjual() {
        return alamat_penjual;
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
