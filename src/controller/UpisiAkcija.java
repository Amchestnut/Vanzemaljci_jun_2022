package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Regija;
import model.Vanzemaljac;
import view.GlavniView;

public class UpisiAkcija implements EventHandler<ActionEvent> {

    private GlavniView view;

    public UpisiAkcija(GlavniView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        Vanzemaljac izabraniMarsovac = view.getTabela().getSelectionModel().getSelectedItem();
        String drzavaIzabrana = view.getDrzavacmb().getValue();

        String mojKontinent = null;
        for(Regija r : view.getRegijeOL()){
            if(drzavaIzabrana.equalsIgnoreCase(r.getDrzava())){
                mojKontinent = r.getKontinent();
                break;
            }
        }

        if(!view.getTabela().getSelectionModel().getSelectedItem().isIzgubljen()){
            view.getPorukalb().setText("Vanzemaljac sa ID-jem " + izabraniMarsovac.getId() + " je vec pronadjen");
            return;
        }

        if(!izabraniMarsovac.getKontinent().equalsIgnoreCase(mojKontinent)){
            view.getPorukalb().setText("Drzava " + drzavaIzabrana + " ne pripada kontinentu " + izabraniMarsovac.getKontinent() + " vec kontinentu " + mojKontinent);
            return;
        }

        /// pazi kad proveravas "" i null, jer "" i null ---> NISU ISTI

        view.getTabela().getSelectionModel().getSelectedItem().setDrzava(drzavaIzabrana);
        view.getPorukalb().setText("Uspesno izvrsavanje");
        view.getTabela().refresh();
        return;


    }
}
