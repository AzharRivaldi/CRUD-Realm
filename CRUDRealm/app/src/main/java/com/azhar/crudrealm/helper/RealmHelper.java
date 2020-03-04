package com.azhar.crudrealm.helper;

import android.content.Context;
import android.widget.Toast;

import com.azhar.crudrealm.model.ModelSiswa;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Azhar Rivaldi on 12/02/2020.
 */

public class RealmHelper {

    private Context context;
    private Realm realm;
    private RealmResults<ModelSiswa> realmResults;

    //logt
    private static final String TAG = "RealmHelper";

    public RealmHelper(Context context) {
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void inputDataAwal() {
        ModelSiswa siswa = new ModelSiswa();
        siswa.setId(1);
        siswa.setNama("Azhar");
        siswa.setAlamat("Pondok Indah");

        realm.beginTransaction();
        realm.copyToRealm(siswa);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil ditambah", Toast.LENGTH_LONG).show();
    }

    public ArrayList<ModelSiswa> tampilDataSiswa() {
        ArrayList<ModelSiswa> data = new ArrayList<>();
        realmResults = realm.where(ModelSiswa.class).findAll();
        realmResults.sort("id", Sort.ASCENDING);

        if (realmResults.size() > 0) {

            for (int i = 0; i < realmResults.size(); i++) {
                ModelSiswa siswa = new ModelSiswa();
                siswa.setId(realmResults.get(i).getId());
                siswa.setNama(realmResults.get(i).getNama());
                siswa.setAlamat(realmResults.get(i).getAlamat());
                data.add(siswa);
            }
        }
        return data;
    }

    public void tambahSiswa(String nama, String alamat) {
        ModelSiswa siswa = new ModelSiswa();
        siswa.setId((int) (System.currentTimeMillis() / 1000));
        siswa.setNama(nama);
        siswa.setAlamat(alamat);

        realm.beginTransaction();
        realm.copyToRealm(siswa);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil ditambah", Toast.LENGTH_LONG).show();
    }

    public void updateSiswa(int id, String nama, String alamat) {
        realm.beginTransaction();
        ModelSiswa siswa = realm.where(ModelSiswa.class).equalTo("id", id).findFirst();
        siswa.setNama(nama);
        siswa.setAlamat(alamat);
        realm.copyToRealm(siswa);
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil diupdate", Toast.LENGTH_LONG).show();
    }

    public void deleteSiswa(int id) {
        realm.beginTransaction();
        RealmResults<ModelSiswa> siswa = realm.where(ModelSiswa.class).equalTo("id", id).findAll();
        siswa.deleteAllFromRealm();
        realm.commitTransaction();

        Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_LONG).show();
    }

}
