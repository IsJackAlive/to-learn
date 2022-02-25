package com.isjackalive.entity;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import javax.persistence.*;
import java.security.ProtectionDomain;
import java.util.Date;
import java.util.List;

/**
 * Mapowanie Hibernate, dla encji klasy Produkt
 *
 * @author damian
 */

@Entity
@Table(name = "produkty")
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produkt", nullable = false)
    private Integer id;

    @Column(name = "cena")
    private Integer cena;

    @Column(name = "data_wydania")
    private Date data;

    @Column(name = "nazwa")
    private String nazwa;

    @OneToOne(targetEntity = Recepta.class)
    private Recepta recepta;

    public Produkt(){}
    public Produkt(Integer cena, Date data, String nazwa) {
        this.cena = cena;
        this.data = data;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recepta getRecepta() {
        return recepta;
    }

    public void setRecepta(Recepta recepta) {
        this.recepta = recepta;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Produkt(Integer id, Integer cena, Date data, String nazwa) {
        this.id = id;
        this.cena = cena;
        this.data = data;
        this.nazwa = nazwa;
    }

    //znajdź obiekt przez ID
    public Produkt getById(int id)
    {
        Produkt obj = null;
        Session session = MainHib.getSessionFactory().openSession();
        try {
            obj =  (Produkt) session.get(Produkt.class, id);
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

    //znajdź obiekt przez Cenę
    public List<Produkt> getByPrice(Integer cena)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Produkt> obj = session.createQuery("from Produkt p where p.cena='" + cena + "'", Produkt.class).list();
        session.close();
        return obj;
    }

    //znajdź obiekt przez Nazwę produktu
    public List<Produkt> getByName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Produkt> dok = session.createSQLQuery("select * from produkty where nazwa LIKE '%" + obj + "%';")
                .addEntity(Produkt.class).list();
        session.close();
        return dok;
    }

    //znajdź obiekt przez Nazwę produktu (zwraca tylko jeden)
    public Produkt getByExclName(String obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Produkt> prods = session.createQuery("from Produkt where nazwa = '" + obj + "'" , Produkt.class).list();
        session.close();
        return prods.get(0);
    }

    //znajdź obiekt przez Datę
    public List<Produkt> getByDate(Date obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        String date = obj.toString();
        System.out.println(date + " " + obj);
        List<Produkt> produkts = session.createQuery("from Produkt where data= " + date , Produkt.class).list();
        session.close();
        return produkts;
    }

}
