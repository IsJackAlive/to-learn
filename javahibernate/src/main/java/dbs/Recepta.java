package dbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbs.Recepta")

public class Recepta {

    public Recepta(){}

    public Recepta(int id, int idDoktor, int idPacjent, int idProdukt) {
        this.id = id;
        this.idDoktor = idDoktor;
        this.idPacjent = idPacjent;
        this.idProdukt = idProdukt;
    }

    @Id
    @Column(name = "id_recepta")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_doktor")
    private int idDoktor;

    public int getIdDoktor() {
        return idDoktor;
    }

    public void setIdDoktor(int idDoktor) {
        this.idDoktor = idDoktor;
    }

    @Column(name = "id_pacjent")
    private int idPacjent;

    public int getIdPacjent() {
        return idPacjent;
    }

    public void setIdPacjent(int idPacjent) {
        this.idPacjent = idPacjent;
    }

    @Column(name = "id_produkt")
    private int idProdukt;

    public int getIdProdukt() {
        return idProdukt;
    }

    public void setIdProdukt(int idProdukt) {
        this.idProdukt = idProdukt;
    }
}
