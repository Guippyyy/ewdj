package classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int ISBNnr;
    private double price;
    private int sterren;

    protected Boek() {
    }

    public Boek(String name, int ISBNnr, double price, int sterren) {
        this.name = name;
        this.ISBNnr = ISBNnr;
        this.price = price;
        this.sterren = sterren;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getISBNnr() {
        return ISBNnr;
    }

    public void setISBNnr(int iSBNnr) {
        ISBNnr = iSBNnr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSterren() {
        return sterren;
    }

    public void setSterren(int sterren) {
        this.sterren = sterren;
    }

}
