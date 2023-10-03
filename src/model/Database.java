package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {

    private static Database instance;
    private List<Regija> regije;
    private List<Vanzemaljac> vanzemaljci;


    private Database() {
        regije = new ArrayList<>();
        vanzemaljci = new ArrayList<>();
        ucitaj();
    }

    public static Database getInstance() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private void ucitaj(){
        String putanja = "src/vanzemaljci.txt";

        BufferedReader br = null;
        try {
            File file = new File(putanja);
            br = new BufferedReader(new FileReader(file));

            String linija;
            int redniBroj = 1;
            String kontinent = "";

            while(!(linija = br.readLine()).contains("IZGUBLJENI")){
                String split[];
                if(linija.contains("Kontinent")){
                    split = linija.split(": ");
                    kontinent = split[1];
                    continue;
                }

                Regija r = new Regija(String.valueOf(redniBroj), kontinent, linija);
                regije.add(r);
                redniBroj++;
            }

            while(!(linija = br.readLine()).contains("PRONADJENI")){            // ovde se citaju izgubljeni vanzemaljci
                String split[] = linija.split(";");

                Vanzemaljac v = new Vanzemaljac(split[0]);
                v.setIzgubljen(true);
                v.setKontinent(split[1]);
                vanzemaljci.add(v);
            }

            while((linija = br.readLine()) != null){
                String split[] = linija.split(";");         /// split[0] = 55 , split[1] = indija

                Vanzemaljac v = new Vanzemaljac(split[0]);
                v.setPronadjen(true);
                v.setDrzava(split[1]);
                vanzemaljci.add(v);
            }

            Collections.sort(vanzemaljci);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Regija> getRegije() {
        return regije;
    }

    public List<Vanzemaljac> getVanzemaljci() {
        return vanzemaljci;
    }
}


















