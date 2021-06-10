package com.example.projectuas;

import java.util.ArrayList;

public class array_muridLive {



    public String nama_murid, username_murid, no_hp_murid, kota_murid;
    public int id_murid;

    public array_muridLive(int id_murid, String username_murid, String nama_murid, String no_hp_murid, String kota_murid){
        this.nama_murid=nama_murid;
        this.username_murid=username_murid;
        this.no_hp_murid=no_hp_murid;
        this.kota_murid=kota_murid;
        this.id_murid=id_murid;
    }
    public String getUsername_murid() {
        return this.username_murid;
    }

    public String getNo_hp_murid() {
        return this.no_hp_murid;
    }

    public String getKota_murid() {
        return this.kota_murid;
    }

    public int getId_murid() {
        return this.id_murid;
    }



}
