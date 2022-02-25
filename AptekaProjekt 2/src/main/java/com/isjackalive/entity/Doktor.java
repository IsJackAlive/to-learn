package com.isjackalive.entity;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.List;

/**
 * Mapowanie Hibernate, dla encji klasy Doktor
 *
 * @author damian
 */

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

    @OneToMany(targetEntity = Recepta.class)
    @JoinColumn(name = "doktor")
    private List<Recepta> receptaList;

    public Doktor(){}
    public Doktor(String imie, String nazwisko, String telefon)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
    }

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

    //znajdź obiekt przez ID
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

    //znajdź obiekt przez Imię
    public List<Doktor> getDoktorByName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Doktor> dok = session.createSQLQuery("select * from doktorzy where imie = '" + obj + "';")
                .addEntity(Doktor.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Nazwisko
    public List<Doktor> getDoktorByLastName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Doktor> dok = session.createSQLQuery("select * from doktorzy where nazwisko = '" + obj + "';")
                        .addEntity(Doktor.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Telefon
    public List<Doktor> getDoktorByTelephone(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Doktor> dok = session.createSQLQuery("SELECT * FROM doktorzy WHERE telefon LIKE '%" + obj + "%';")
                        .addEntity(Doktor.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Nazwę produktu (zwraca tylko jeden)
    public Doktor getByExclTel(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Doktor> prods = session.createQuery("from Doktor where telefon = '" + obj + "'" , Doktor.class).list();
        session.close();
        return prods.get(0);
    }

}

