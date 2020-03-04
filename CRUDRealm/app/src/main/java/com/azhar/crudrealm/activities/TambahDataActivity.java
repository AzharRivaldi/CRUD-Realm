package com.azhar.crudrealm.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.crudrealm.R;
import com.azhar.crudrealm.helper.RealmHelper;

/**
 * Created by Azhar Rivaldi on 12/02/2020.
 */

public class TambahDataActivity extends AppCompatActivity {

    private EditText tvNama;
    private EditText tvAlamat;
    private Button btnTambah;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        initView();

        Toolbar tbMW = findViewById(R.id.tbAdd);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");

        helper = new RealmHelper(this);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvNama.getText().toString().isEmpty() || tvAlamat.getText().toString().isEmpty()) {
                    Toast.makeText(TambahDataActivity.this, "Lengkapi Data!", Toast.LENGTH_LONG).show();
                } else {
                    String nama = tvNama.getText().toString();
                    String alamat = tvAlamat.getText().toString();
                    helper.tambahSiswa(nama, alamat);
                    Toast.makeText(TambahDataActivity.this, "Data Terkirim", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        btnTambah = findViewById(R.id.btn_tambah);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
