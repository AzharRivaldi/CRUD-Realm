package com.azhar.crudrealm.model;

import io.realm.RealmObject;

/**
 * Created by Azhar Rivaldi on 12/02/2020.
 */

public class ModelSiswa extends RealmObject {
    private int id;
    private String nama;
    private String alamat;

    public ModelSiswa(int id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    public ModelSiswa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
