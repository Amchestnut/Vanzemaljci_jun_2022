package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Regija;
import model.Vanzemaljac;
import view.GlavniView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SacuvajAkcija implements EventHandler<ActionEvent> {

    private GlavniView view;
    private List<Vanzemaljac> pronadjeniVanzemaljci;

    public SacuvajAkcija(GlavniView view) {
        pronadjeniVanzemaljci = new ArrayList<>();
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {

        String putanja = "src/regije.txt";
        BufferedWriter bw = null;

        try {
            File file = new File(putanja);
            bw = new BufferedWriter(new FileWriter(file));

            for(Vanzemaljac v : view.getVanzemaljciOL()){
                if(v.isPronadjen()){
                    pronadjeniVanzemaljci.add(v);
                }
            }

            /*
            1) Moram da gledam kontinent, recimo afrika, pa evropa
            2) Za vanzemaljca POJEDINACNO proveravam da li je u africi
            3) to proveravam tako sto vidim u kojoj je drzavi
            4) ako ta drzava pripada africi -> ++
             */

            for(String kontinent : view.getKontinentiSet()){
                int brojacKontinenti = 0;

                for(Vanzemaljac v : pronadjeniVanzemaljci){
                    String drzavaUKomeJeVanzemaljac = v.getDrzava();
                    for(Regija r : view.getRegijeOL()){
                        if(r.getDrzava().equalsIgnoreCase(drzavaUKomeJeVanzemaljac)){
                            if(r.getKontinent().equalsIgnoreCase(kontinent)){
                                brojacKontinenti++;
                            }
                        }
                    }
                }
                bw.append(kontinent);
                bw.append(": ima ");
                bw.append(String.valueOf(brojacKontinenti));
                bw.append(" Vanzemaljca\n");

                // za drzave pojedinacno
                // Prvo uzmem kontinent
                // Proveravam redom da li drzava iz padajujeg menija pripada tom kontinentu
                // Ako pripada, onda prolazim kroz sve regije i brojim vanz

                for(String odande : view.getDrzaveSet()){
                    int brojac = 0;
                    Regija x = null;
                    for(Regija r : view.getRegijeOL()){
                        if(r.getDrzava().equalsIgnoreCase(odande)){
                            x = r;
                            break;
                        }
                    }

                    if(x.getKontinent() != null){
                        if(x.getKontinent().equalsIgnoreCase(kontinent)){
                            bw.append("\t");
                            bw.append(odande);
                            bw.append(" drzava ima: ");

                            for(Vanzemaljac v : view.getVanzemaljciOL()){
                                if(v.getDrzava() != null){
                                    if(v.getDrzava().equalsIgnoreCase(x.getDrzava())){
                                        brojac++;
                                    }
                                }
                            }
                            bw.append(String.valueOf(brojac));
                            bw.append(" vanzemaljaca");
                            bw.append("\n");
                        }
                    }
                }


            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public GlavniView getView() {
        return view;
    }

    public void setView(GlavniView view) {
        this.view = view;
    }
}

/*
for (String kontinent : view.getKontinentiSet()){
                bw.append(kontinent);
                int broj = 0;
                for(Vanzemaljac v : view.getVanzemaljciOL()){
                    if(v.getKontinent() != null){
                        if(v.getKontinent().equalsIgnoreCase(kontinent)){
                            broj++;
                        }
                    }
                }
                bw.append(": ima ");
                bw.append(String.valueOf(broj));
                bw.append(" Vanzemaljca");
                bw.append("\n\t");


                // za drzave pojedinacno

                // Prvo uzmem kontinent
                // Proveravam redom da li drzava iz padajujeg menija pripada tom kontinentu
                // Ako pripada, onda prolazim kroz sve regije i brojim vanz

                for(String odande : view.getDrzaveSet()){
                    int brojac = 0;
                    Regija x = null;
                    for(Regija r : view.getRegijeOL()){
                        if(r.getDrzava().equalsIgnoreCase(odande)){
                            x = r;
                            break;
                        }
                    }

                    if(x.getKontinent() != null){
                        if(x.getKontinent().equalsIgnoreCase(kontinent)){
                            for(Vanzemaljac v : view.getVanzemaljciOL()){
                                if(v.getDrzava() != null){
                                    if(v.getDrzava().equalsIgnoreCase(x.getDrzava())){
                                        brojac++;
                                    }
                                }
                            }
                            bw.append(odande);
                            bw.append(" drzava ima: ");
                            bw.append(String.valueOf(brojac));
                            bw.append(" vanzemaljaca");
                            bw.append("\n\t");
                        }
                    }

                }


//                for(Regija r : view.getRegijeOL()){
//                    if(r.getKontinent().equalsIgnoreCase(kontinent)){
//                        String drzava = r.getDrzava();
//                        int drzavljani = 0;
//                        for(Regija region : view.getRegijeOL()){
//                            if(region.getDrzava().equalsIgnoreCase(drzava)){
//                                drzavljani++;
//                            }
//                        }
//                        bw.append(drzava);
//                        bw.append(String.valueOf(drzavljani));
//                        bw.append("\n");
//                    }
//                }
            }
 */
