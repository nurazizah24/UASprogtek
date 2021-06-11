package com.example.projectuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.list_guru;

public class pesanan_kursus_inhistori_murid_Adapter extends RecyclerView.Adapter<pesanan_kursus_inhistori_murid_Adapter.inhistori_pesanan_kursus_muridHolder>{

    private ArrayList<list_guru> arraylistinhistori_murid;
    public pesanan_kursus_inhistori_murid_Adapter(ArrayList<list_guru> listinhistori_murid){
        this.arraylistinhistori_murid=listinhistori_murid;
    }
    @Override
    public inhistori_pesanan_kursus_muridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater=LayoutInflater.from(parent.getContext());

        View view=layoutinflater.inflate(R.layout.card_list_pesanan_kursus_inhistori_murid, parent, false);
        return new inhistori_pesanan_kursus_muridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  pesanan_kursus_inhistori_murid_Adapter.inhistori_pesanan_kursus_muridHolder holder, int position) {
        holder.cardView_nama_guru.setText(arraylistinhistori_murid.get(position).getNama_guru());
        holder.cardView_keahlian_guru.setText(arraylistinhistori_murid.get(position).getKeahlian_guru());
        holder.cardView_kota_guru.setText((arraylistinhistori_murid.get(position).getKota_guru()));
    }

    @Override
    public int getItemCount() {
        return arraylistinhistori_murid.size();
    }

    public class inhistori_pesanan_kursus_muridHolder extends RecyclerView.ViewHolder {
        private TextView cardView_nama_guru, cardView_keahlian_guru, cardView_kota_guru;

        public inhistori_pesanan_kursus_muridHolder(@NonNull View itemView) {
            super(itemView);

            cardView_nama_guru=itemView.findViewById((R.id.cardView_nama_guru));
            cardView_keahlian_guru=itemView.findViewById((R.id.cardView_keahlian_guru));
            cardView_kota_guru=itemView.findViewById((R.id.cardView_kota_guru));
        }
    }
}
