package com.isjackalive.dbs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Produkty")
public class Produkty {

    @Id
    @Column(name = "id_produkt")
    private int id;
    @Column(name = "id_apteka")
    private int idApteka;
    @Column(name = "nazwa")
    private String nazwaProd;
    @Column(name = "data_wydania")
    private Date dataWyd;
    @Column(name = "cena")
    private int cenaProd;

    public Produkty(){}

    public Produkty(int id, int idApteka, String nazwaProd, Date dataWyd, int cenaProd) {
        this.id = id;
        this.idApteka = idApteka;
        this.nazwaProd = nazwaProd;
        this.dataWyd = dataWyd;
        this.cenaProd = cenaProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdApteka() {
        return idApteka;
    }

    public void setIdApteka(int idApteka) {
        this.idApteka = idApteka;
    }

    public String getNazwaProd() {
        return nazwaProd;
    }

    public void setNazwaProd(String nazwaProd) {
        this.nazwaProd = nazwaProd;
    }

    public Date getDataWyd() {
        return dataWyd;
    }

    public void setDataWyd(Date dataWyd) {
        this.dataWyd = dataWyd;
    }

    public int getCenaProd() {
        return cenaProd;
    }

    public void setCenaProd(int cenaProd) {
        this.cenaProd = cenaProd;
    }
}
