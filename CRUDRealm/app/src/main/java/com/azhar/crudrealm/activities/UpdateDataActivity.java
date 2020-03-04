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

public class UpdateDataActivity extends AppCompatActivity {

    private EditText tvNama;
    private EditText tvAlamat;
    private Button btnUpdate, btnHapus;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        Toolbar tbMW = findViewById(R.id.tbUpdate);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Data");

        final int id = getIntent().getIntExtra("DATA_ID", 0);
        String nama = getIntent().getStringExtra("DATA_NAMA");
        String alamat = getIntent().getStringExtra("DATA_ALAMAT");

        initView();

        tvNama.setText(nama);
        tvAlamat.setText(alamat);

        helper = new RealmHelper(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tvNama.getText().toString();
                String alamat = tvAlamat.getText().toString();
                helper.updateSiswa(id, nama, alamat);
                Toast.makeText(UpdateDataActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.deleteSiswa(id);
                Toast.makeText(UpdateDataActivity.this, "Data Dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initView() {
        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        btnUpdate = findViewById(R.id.btn_update);
        btnHapus = findViewById(R.id.btn_hapus);
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
