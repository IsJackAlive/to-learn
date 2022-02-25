package com.isjackalive.entity;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapowanie Hibernate, dla encji klasy Recepta
 *
 * @author damian
 */

@Entity
@Table(name = "recepta")
public class Recepta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recepta", nullable = false)
    private Integer id;

    @ManyToOne(targetEntity = Doktor.class)
    @JoinColumn(name = "doktor", nullable = false)
    private Doktor doktor;

    @ManyToOne(targetEntity = Pacjent.class)
    @JoinColumn(name = "pacjent", nullable = false)
    private Pacjent pacjent;

    @OneToOne(targetEntity = Produkt.class)
    @JoinColumn(name = "produkt", nullable = false)
    private Produkt produkt;

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Recepta(Doktor doktor, Pacjent pacjent, Produkt produkt) {
        this.doktor = doktor;
        this.pacjent = pacjent;
        this.produkt = produkt;
    }

    public Recepta(){}
    public Recepta(Doktor doktor, Pacjent pacjent) {
        this.doktor = doktor;
        this.pacjent = pacjent;
    }

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

    public Recepta getById(int id)
    {
        Recepta obj = null;
        Session session = MainHib.getSessionFactory().openSession();
        try {
            obj =  (Recepta) session.get(Recepta.class, id);
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

    //znajdź obiekt Doktor przez ID
    public List<Recepta> getByDoktor(Integer id)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Recepta> obj = session.createSQLQuery("select * from recepta where doktor= " + id.intValue() + ";")
                .addEntity(Recepta.class).list();
        session.close();
        return obj;
    }

    //znajdź obiekt Pacjent przez ID
    public List<Recepta> getByPacjent(Integer id)
    {
        Session session = MainHib.getSessionFactory().openSession();
        List<Recepta> obj = session.createQuery("from Recepta where pacjent=" + id, Recepta.class).list();
        session.close();
        return obj;
    }

    //znajdź obiekt Produtk przez Nazwę produktu
    public List<Recepta> getByName (String obj)
    {
        System.out.println("getByName:" + obj);
        List<Recepta> fin = new ArrayList<>();
        Session session = MainHib.getSessionFactory().openSession();    //createQuery
        List<Produkt> produkts = session.createQuery("from Produkt where nazwa like '%" + obj +"%'", Produkt.class).list();

        for(int i=0; i < produkts.size(); i++)
        {
            int a = produkts.get(i).getId();    //ID produktu
            fin.add(getById(a));
        }
        session.close();
        return fin;
    }

}