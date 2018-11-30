package com.dionrasmadi.lelangapp.dataClass;

public class DataUser {
    private String username;
    private String nama;
    private String tlf;
    private String alamat;
    private String longitude;
    private String latitude;

    public DataUser(String username, String nama, String tlf, String alamat, String longitude, String latitude) {
        this.username = username;
        this.nama = nama;
        this.tlf = tlf;
        this.alamat = alamat;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public String getTlf() {
        return tlf;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

}
