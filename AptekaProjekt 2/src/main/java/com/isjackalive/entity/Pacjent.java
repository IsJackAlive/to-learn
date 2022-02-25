package com.isjackalive.entity;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Mapowanie Hibernate, dla encji klasy Pacjetn
 *
 * @author damian
 */

@Entity
@Table(name = "pacjenci")
public class Pacjent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pacjent", nullable = false)
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko", length = 11)
    private String nazwisko;

    @Column(name = "telefon")
    private String telefon;

    @OneToMany(targetEntity = Recepta.class)
    @JoinColumn(name = "pacjent")
    private Set<Recepta> recepty;

    public Pacjent(){}
    public Pacjent(String imie, String nazwisko, String telefon)
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
    public Pacjent getById(int id)
    {
        Pacjent obj = null;
        Session session = MainHib.getSessionFactory().openSession();
        try {
            obj = session.get(Pacjent.class, id);
            Hibernate.initialize(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obj;
    }

    //znajdź obiekt przez Imię
    public List<Pacjent> getByName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Pacjent> dok = session.createSQLQuery("select * from pacjenci where imie = '" + obj + "';")
                .addEntity(Pacjent.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Nazwisko
    public List<Pacjent> getByLastName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Pacjent> dok = session.createSQLQuery("select * from pacjenci where nazwisko = '" + obj + "';")
                .addEntity(Pacjent.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Telefon
    public List<Pacjent> getByTelephone(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Pacjent> dok = session.createSQLQuery("SELECT * FROM pacjenci WHERE telefon LIKE '%" + obj + "%';")
                .addEntity(Pacjent.class).list();
        session.close();
        return dok;
    }

    public Pacjent getByExclTel(String obj) {
        Session session = MainHib.getSessionFactory().openSession();
        List<Pacjent> prods = session.createQuery("from Pacjent where telefon = '" + obj + "'" , Pacjent.class).list();
        session.close();
        return prods.get(0);
    }
}
