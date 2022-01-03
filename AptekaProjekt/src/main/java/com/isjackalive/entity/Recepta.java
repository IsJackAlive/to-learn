package com.isjackalive.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recepta")
public class Recepta {
    @Id
    @Column(name = "id_recepta", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doktor", nullable = false)
    private Doktor doktor;

    @ManyToOne
    @JoinColumn(name = "pacjent", nullable = false)
    private Pacjent pacjent;

    @OneToMany
    private Set<Produkt> zbiorProduktow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Set<Produkt> getZbiorProduktow() {
        return zbiorProduktow;
    }

    public void setZbiorProduktow(Set<Produkt> zbiorProduktow) {
        this.zbiorProduktow = zbiorProduktow;
    }
}
