package com.isjackalive.dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doktorzy")

public class Doktorzy {

    @Id
    @Column(name = "id_doktor")
    private int idDoktor;
    @Column(name = "imie")
    private String imieDok;
    @Column(name = "nazwisko")
    private String nazwiskoDok;
    @Column(name = "telefon")
    private String telefonDok;

    public Doktorzy(){};

    public Doktorzy(int idDoktor, String imieDok, String nazwiskoDok, String telefonDok) {
        this.idDoktor = idDoktor;
        this.imieDok = imieDok;
        this.nazwiskoDok = nazwiskoDok;
        this.telefonDok = telefonDok;
    }

    public int getIdDoktor() {
        return idDoktor;
    }

    public void setIdDoktor(int idDoktor) {
        this.idDoktor = idDoktor;
    }

    public String getImieDok() {
        return imieDok;
    }

    public void setImieDok(String imieDok) {
        this.imieDok = imieDok;
    }

    public String getNazwiskoDok() {
        return nazwiskoDok;
    }

    public void setNazwiskoDok(String nazwiskoDok) {
        this.nazwiskoDok = nazwiskoDok;
    }

    public String getTelefonDok() {
        return telefonDok;
    }

    public void setTelefonDok(String telefonDok) {
        this.telefonDok = telefonDok;
    }
}
