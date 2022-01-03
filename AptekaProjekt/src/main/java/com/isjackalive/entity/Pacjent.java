package com.isjackalive.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pacjenci")
public class Pacjent {
    @Id
    @Column(name = "id_pacjent", nullable = false)
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko", length = 11)
    private String nazwisko;

    @Column(name = "telefon")
    private String telefon;

    @OneToMany
    @JoinColumn(name = "id_pacjent")

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
