package com.example.projectuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.list_guru;

public class temukan_guru_Adapter extends RecyclerView.Adapter<temukan_guru_Adapter.temukan_guruHolder>{

    private ArrayList<list_guru> arraylisttemukanguru_murid;


    public temukan_guru_Adapter(ArrayList<list_guru> listtemukan_guru){
        this.arraylisttemukanguru_murid=listtemukan_guru;
    }
    @Override
    public temukan_guruHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater=LayoutInflater.from(parent.getContext());

        View view=layoutinflater.inflate(R.layout.card_list_temukan_guru_murid, parent, false);
        return new temukan_guru_Adapter.temukan_guruHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull temukan_guru_Adapter.temukan_guruHolder holder, int position) {
        holder.cardTemukanGuru_keterangan_infoGuru_murid.setText(String.valueOf(arraylisttemukanguru_murid.get(position).getKeterangan_guru()));
        holder.cardTemukanGuru_nama_guru_infoGuru_murid.setText(String.valueOf(arraylisttemukanguru_murid.get(position).getNama_guru()));

        holder.cardTemukanGuru_jenis_kelamin_guru_infoGuru_murid.setText(String.valueOf(arraylisttemukanguru_murid.get(position).getJenis_kelamin_guru()));
        holder.cardTemukanGuru_keahlian_guru_infoGuru_murid.setText(String.valueOf(arraylisttemukanguru_murid.get(position).getKeahlian_guru()));
        holder.cardTemukanGuru_kota_guru_infoGuru_murid.setText(String.valueOf(arraylisttemukanguru_murid.get(position).getKota_guru()));

   }

    @Override
    public int getItemCount() {
        return arraylisttemukanguru_murid.size();
    }

    public class temukan_guruHolder extends RecyclerView.ViewHolder {
        private TextView cardTemukanGuru_keterangan_infoGuru_murid, cardTemukanGuru_nama_guru_infoGuru_murid,
                cardTemukanGuru_jenis_kelamin_guru_infoGuru_murid,cardTemukanGuru_keahlian_guru_infoGuru_murid,
                cardTemukanGuru_kota_guru_infoGuru_murid;
        public temukan_guruHolder(@NonNull View itemView) {
            super(itemView);



            cardTemukanGuru_keterangan_infoGuru_murid=itemView.findViewById((R.id.cardTemukanGuru_keterangan_infoGuru_murid));
            cardTemukanGuru_nama_guru_infoGuru_murid=itemView.findViewById((R.id.cardTemukanGuru_nama_guru_infoGuru_murid));
            cardTemukanGuru_jenis_kelamin_guru_infoGuru_murid=itemView.findViewById((R.id. cardTemukanGuru_jenis_kelamin_guru_infoGuru_murid));
            cardTemukanGuru_keahlian_guru_infoGuru_murid=itemView.findViewById((R.id.cardTemukanGuru_keahlian_guru_infoGuru_murid));
            cardTemukanGuru_kota_guru_infoGuru_murid=itemView.findViewById((R.id.cardTemukanGuru_kota_guru_infoGuru_murid));
        }
    }
}
