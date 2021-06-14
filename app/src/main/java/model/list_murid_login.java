package model;

import java.util.ArrayList;

public class list_murid_login {

    public int id_murid, saldo_murid;
    public String username_murid;

public list_murid_login(){

}
    public list_murid_login(int id_murid, String username_murid, int saldo_murid, String nama_murid, String jenis_kelamin_murid,
                            String nomor_hp_murid, String kota_murid){
        this.id_murid=id_murid;
        this.username_murid=username_murid;
        this.saldo_murid=saldo_murid;
        this.nama_murid=nama_murid;
        this.jenis_kelamin_murid=jenis_kelamin_murid;
        this.nomor_hp_murid=nomor_hp_murid;
        this.kota_murid=kota_murid;
    }
    public int getId_murid() {
        return this.id_murid;
    }

    public void setId_murid(int id_murid) {
        this.id_murid = id_murid;
    }

    public int getSaldo_murid() {
        return this.saldo_murid;
    }

    public void setSaldo_murid(int saldo_murid) {
        this.saldo_murid = saldo_murid;
    }

    public String getUsername_murid() {
        return this.username_murid;
    }

    public void setUsername_murid(String username_murid) {
        this.username_murid = username_murid;
    }

    public String getNama_murid() {
        return this.nama_murid;
    }

    public void setNama_murid(String nama_murid) {
        this.nama_murid = nama_murid;
    }

    public String getJenis_kelamin_murid() {
        return this.jenis_kelamin_murid;
    }

    public void setJenis_kelamin_murid(String jenis_kelamin_murid) {
        this.jenis_kelamin_murid = jenis_kelamin_murid;
    }

    public String getNomor_hp_murid() {
        return this.nomor_hp_murid;
    }

    public void setNomor_hp_murid(String nomor_hp_murid) {
        this.nomor_hp_murid = nomor_hp_murid;
    }

    public String getKota_murid() {
        return this.kota_murid;
    }

    public void setKota_murid(String kota_murid) {
        this.kota_murid = kota_murid;
    }

    private String nama_murid;
    private String jenis_kelamin_murid;
    private String nomor_hp_murid;
    private String kota_murid;
}
