package com.azhar.crudrealm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.crudrealm.R;
import com.azhar.crudrealm.adapter.DataAdapter;
import com.azhar.crudrealm.helper.RealmHelper;
import com.azhar.crudrealm.model.ModelSiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Azhar Rivaldi on 12/02/2020.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<ModelSiswa> dataSiswa = new ArrayList<>();
    private RealmHelper helper;
    TextView tvToday, txtNoData;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahDataActivity.class));

            }
        });

        txtNoData = findViewById(R.id.txtNoData);
        recycler = findViewById(R.id.recycler_view);
        helper = new RealmHelper(MainActivity.this);
        recycler.setAdapter(new DataAdapter(MainActivity.this, dataSiswa));
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        tvToday = findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")) {
            hariIni = "Minggu";
        } else if (hariIni.equalsIgnoreCase("monday")) {
            hariIni = "Senin";
        } else if (hariIni.equalsIgnoreCase("tuesday")) {
            hariIni = "Selasa";
        } else if (hariIni.equalsIgnoreCase("wednesday")) {
            hariIni = "Rabu";
        } else if (hariIni.equalsIgnoreCase("thursday")) {
            hariIni = "Kamis";
        } else if (hariIni.equalsIgnoreCase("friday")) {
            hariIni = "Jumat";
        } else if (hariIni.equalsIgnoreCase("saturday")) {
            hariIni = "Sabtu";
        }

        setData();
        getToday();
    }

    private void setData() {
        dataSiswa = helper.tampilDataSiswa();
        if (dataSiswa.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
            recycler.setAdapter(new DataAdapter(MainActivity.this, dataSiswa));
        }
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + year;
        tvToday.setText(formatFix);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }
}
