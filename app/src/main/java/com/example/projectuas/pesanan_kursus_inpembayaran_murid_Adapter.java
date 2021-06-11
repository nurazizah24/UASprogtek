package com.example.projectuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.list_guru;

public class pesanan_kursus_inpembayaran_murid_Adapter extends RecyclerView.Adapter<pesanan_kursus_inpembayaran_murid_Adapter.inpembayaran_pesanan_kursus_muridHolder>{


    private ArrayList<list_guru> arraylistinpembayaran_murid;
    public pesanan_kursus_inpembayaran_murid_Adapter(ArrayList<list_guru> listinpembayaran_murid){
        this.arraylistinpembayaran_murid=listinpembayaran_murid;
    }
    @Override
    public inpembayaran_pesanan_kursus_muridHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater=LayoutInflater.from(parent.getContext());

        View view=layoutinflater.inflate(R.layout.card_list_pesanan_kursus_inpembayaran_murid, parent, false);

        return new pesanan_kursus_inpembayaran_murid_Adapter.inpembayaran_pesanan_kursus_muridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  pesanan_kursus_inpembayaran_murid_Adapter.inpembayaran_pesanan_kursus_muridHolder holder, int position) {
        holder.cardView_nama_guru.setText(String.valueOf(arraylistinpembayaran_murid.get(position).getNama_guru()));
        holder.cardView_keahlian_guru.setText(String.valueOf(arraylistinpembayaran_murid.get(position).getKeahlian_guru()));
        holder.cardView_kota_guru.setText(String.valueOf(arraylistinpembayaran_murid.get(position).getKota_guru()));

        holder.button_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saldomurid--
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylistinpembayaran_murid.size();
    }

    public class inpembayaran_pesanan_kursus_muridHolder extends RecyclerView.ViewHolder {
        private TextView cardView_nama_guru, cardView_keahlian_guru, cardView_kota_guru;
        private Button button_bayar;
        public inpembayaran_pesanan_kursus_muridHolder(@NonNull  View itemView) {
            super(itemView);

button_bayar=itemView.findViewById(R.id.button_bayar);
            cardView_nama_guru=itemView.findViewById((R.id.cardView_nama_guru));
            cardView_keahlian_guru=itemView.findViewById((R.id.cardView_keahlian_guru));
            cardView_kota_guru=itemView.findViewById((R.id.cardView_kota_guru));
        }
    }
}


