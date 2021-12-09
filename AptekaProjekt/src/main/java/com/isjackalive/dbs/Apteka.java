package com.isjackalive.dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Apteka")

public class Apteka {
    @Id
    @Column(name = "id_apteka")
    private int id;
    @Column(name = "adres")
    private String adresApt;
    @Column(name = "telefon")
    private String telefonApt;

    public Apteka(){}

    public Apteka(int id, String adresApt, String telefonApt) {
        this.id = id;
        this.adresApt = adresApt;
        this.telefonApt = telefonApt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresApt() {
        return adresApt;
    }

    public void setAdresApt(String adresApt) {
        this.adresApt = adresApt;
    }

    public String getTelefonApt() {
        return telefonApt;
    }

    public void setTelefonApt(String telefonApt) {
        this.telefonApt = telefonApt;
    }
}
