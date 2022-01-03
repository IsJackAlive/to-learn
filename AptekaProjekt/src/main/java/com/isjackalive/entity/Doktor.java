package com.isjackalive.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doktorzy")
public class Doktor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doktor", nullable = false)
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "telefon")
    private String telefon;

    @OneToMany
    @JoinColumn(name = "id_doktor")

    private Set<Recepta> recepty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Recepta> getRecepty() {
        return recepty;
    }

    public void setRecepty(Set<Recepta> recepty) {
        this.recepty = recepty;
    }
}
