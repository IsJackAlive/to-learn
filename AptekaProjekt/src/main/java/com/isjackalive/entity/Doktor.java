package com.isjackalive.entity;

import org.hibernate.Cache;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;
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
    @JoinColumn(name = "doktor")
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

    public Doktor getDoktorById(int id)
    {
        Doktor doktor = null;
        Session session = MainHib.getSessionFactory().openSession();
        try {
            doktor =  (Doktor) session.get(Doktor.class, id);
            Hibernate.initialize(doktor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return doktor;
    }

    public Doktor getDoktorByName(String imie)
    {
        Session session = MainHib.getSessionFactory().openSession();
        Doktor dok = null;
        try {
            dok = (Doktor)
                    session.createSQLQuery("select * from doktorzy where imie = '" + imie + "';")
                            .addEntity(Doktor.class).uniqueResult();
            session.close();
        }
        finally {
            return dok;
        }
    }

    public Doktor getDoktorByLastName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        Doktor dok = (Doktor)
                session.createSQLQuery("select * from doktorzy where nazwisko = '" + obj + "';")
                        .addEntity(Doktor.class).uniqueResult();
        session.close();
        return dok;
    }
}

