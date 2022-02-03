package com.isjackalive.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "produkty")
public class Produkt {
    @Id
    @Column(name = "id_produkt", nullable = false)
    private Integer id;

    @Column(name = "cena")
    private Integer cena;

    @Column(name = "data_wydania")
    private Instant data;

    @Column(name = "nazwa")
    private String nazwa;

    @ManyToOne
    @JoinColumn(name = "recepta")
    private Recepta recepta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Recepta getRecepta() {
        return recepta;
    }

    public void setRecepta(Recepta recepta) {
        this.recepta = recepta;
    }
}
