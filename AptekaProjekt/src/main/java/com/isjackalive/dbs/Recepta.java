package com.isjackalive.dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recepta")

public class Recepta {

    @Id
    @Column(name = "id_recepta")
    private int id;
    @Column(name = "id_doktor")
    private int idDoktor;
    @Column(name = "id_pacjent")
    private int idPacjent;
    @Column(name = "id_produkt")
    private int idProdukt;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoktor() {
        return idDoktor;
    }
    public void setIdDoktor(int idDoktor) {
        this.idDoktor = idDoktor;
    }

    public int getIdPacjent() {
        return idPacjent;
    }
    public void setIdPacjent(int idPacjent) {
        this.idPacjent = idPacjent;
    }

    public int getIdProdukt() {
        return idProdukt;
    }
    public void setIdProdukt(int idProdukt) {
        this.idProdukt = idProdukt;
    }

    public Recepta(){}

    public Recepta(int id, int idDoktor, int idPacjent, int idProdukt) {
        this.id = id;
        this.idDoktor = idDoktor;
        this.idPacjent = idPacjent;
        this.idProdukt = idProdukt;
    }
}
