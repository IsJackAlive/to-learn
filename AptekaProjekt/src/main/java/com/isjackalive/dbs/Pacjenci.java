package com.isjackalive.dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pacjenci")

public class Pacjenci {



    @Id
    @Column(name = "id_pacjent")
    private int id;

    @Column(name = "imie")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pacjenci(){}
}
