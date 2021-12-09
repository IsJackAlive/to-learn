package com.isjackalive.dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pacjenci")

public class Pacjenci {

    @Id
    @Column(name = "id_pacjent")
    private int id;
    @Column(name = "imie")
    private String imiePac;
    @Column(name = "nazwisko")
    private String nazwiskoPac;
    @Column(name = "telefon")
    private String telefonPac;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImiePac() {
        return imiePac;
    }

    public void setImiePac(String imiePac) {
        this.imiePac = imiePac;
    }

    public String getNazwiskoPac() {
        return nazwiskoPac;
    }

    public void setNazwiskoPac(String nazwiskoPac) {
        this.nazwiskoPac = nazwiskoPac;
    }

    public String getTelefonPac() {
        return telefonPac;
    }

    public void setTelefonPac(String telefonPac) {
        this.telefonPac = telefonPac;
    }

    public Pacjenci(int id, String imiePac, String nazwiskoPac, String telefonPac) {
        this.id = id;
        this.imiePac = imiePac;
        this.nazwiskoPac = nazwiskoPac;
        this.telefonPac = telefonPac;
    }

    public Pacjenci(){}

}
