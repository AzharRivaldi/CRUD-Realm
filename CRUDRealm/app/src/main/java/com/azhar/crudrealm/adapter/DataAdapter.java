package com.azhar.crudrealm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.azhar.crudrealm.R;
import com.azhar.crudrealm.activities.UpdateDataActivity;
import com.azhar.crudrealm.model.ModelSiswa;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 12/02/2020.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ModelSiswa> dataSiswa;

    public DataAdapter(Context context, ArrayList<ModelSiswa> dataSiswa) {
        this.context = context;
        this.dataSiswa = dataSiswa;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvAlamat;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_list_nama);
            tvAlamat = itemView.findViewById(R.id.tv_list_alamat);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //set data
        holder.tvNama.setText(dataSiswa.get(position).getNama());
        holder.tvAlamat.setText(dataSiswa.get(position).getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, UpdateDataActivity.class);
                pindah.putExtra("DATA_ID", dataSiswa.get(position).getId());
                pindah.putExtra("DATA_NAMA", dataSiswa.get(position).getNama());
                pindah.putExtra("DATA_ALAMAT", dataSiswa.get(position).getAlamat());
                context.startActivity(pindah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSiswa.size();
    }

}
