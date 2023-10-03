package model;

public class Vanzemaljac implements Comparable<Vanzemaljac>{

    private String id;
    private String kontinent;
    private String drzava;
    private boolean pronadjen = false;
    private boolean izgubljen = false;


    /// objekat vanzemaljac
    /// id 1
    /// izgubljen true
    /// pronadjen false
    /// kontinent = evropa
    /// drzava = null


    public Vanzemaljac(String id) {
        this.id = id;
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

    public boolean isPronadjen() {
        return pronadjen;
    }

    public void setPronadjen(boolean pronadjen) {
        this.pronadjen = pronadjen;
    }

    public boolean isIzgubljen() {
        return izgubljen;
    }

    public void setIzgubljen(boolean izgubljen) {
        this.izgubljen = izgubljen;
    }

    @Override
    public int compareTo(Vanzemaljac o) {
        int id1 = Integer.parseInt(this.id);
        int id2 = Integer.parseInt((o).id);
        return id1-id2;
    }

    @Override
    public String toString() {
        return "Vanzemaljac{" +
                "id='" + id + '\'' +
                ", kontinent='" + kontinent + '\'' +
                ", drzava='" + drzava + '\'' +
                '}' +"\n";
    }


}
