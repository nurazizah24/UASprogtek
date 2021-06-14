package model;

public class list_guru {
    private String nama_guru;
    private String kota_guru;
    private String keahlian_guru;
public list_guru(){

}
    public list_guru(String nama_guru, String kota_guru, String keahlian_guru, String keterangan_guru, String jenis_kelamin_guru){
        this.nama_guru=nama_guru;
        this.kota_guru=kota_guru;
        this.keahlian_guru=keahlian_guru;
        this.keterangan_guru=keterangan_guru;
        this.jenis_kelamin_guru=jenis_kelamin_guru;
    }
    public String getKeterangan_guru() {
        return keterangan_guru;
    }

    public void setKeterangan_guru(String keterangan_guru) {
        this.keterangan_guru = keterangan_guru;
    }

    public String getJenis_kelamin_guru() {
        return jenis_kelamin_guru;
    }

    public void setJenis_kelamin_guru(String jenis_kelamin_guru) {
        this.jenis_kelamin_guru = jenis_kelamin_guru;
    }

    private String keterangan_guru;
    private String jenis_kelamin_guru;
private int saldo_guru;

    public int getSaldo_guru() {
        return saldo_guru;
    }

    public void setSaldo_guru(int saldo_guru) {
        this.saldo_guru = saldo_guru;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }




    public String getKota_guru() {
        return kota_guru;
    }

    public void setKota_guru(String kota_guru) {
        this.kota_guru = kota_guru;
    }

    public String getKeahlian_guru() {
        return keahlian_guru;
    }

    public void setKeahlian_guru(String keahlian_guru) {
        this.keahlian_guru = keahlian_guru;
    }


}
