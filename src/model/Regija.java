package model;

public class Regija {

    private String id;
    private String kontinent;
    private String drzava;

    public Regija(String id, String kontinent, String drzava) {
        this.id = id;
        this.kontinent = kontinent;
        this.drzava = drzava;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKontinent() {
        return kontinent;
    }

    public void setKontinent(String kontinent) {
        this.kontinent = kontinent;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return "Regija{" +
                "id='" + id + '\'' +
                ", kontinent='" + kontinent + '\'' +
                ", drzava='" + drzava + '\'' +
                '}';
    }
}
