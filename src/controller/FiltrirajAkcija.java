package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Vanzemaljac;
import view.GlavniView;

import java.util.function.Predicate;

public class FiltrirajAkcija implements EventHandler<ActionEvent> {

    private GlavniView view;

    public FiltrirajAkcija(GlavniView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        String kontinent = view.getKontinentcmb().getValue();
        FilteredList<Vanzemaljac> vanzemaljskiLikovi = new FilteredList<>(view.getVanzemaljciOL());         // znaci napravim filter listu i stavim u nju observable listu

        boolean selektovanPronadjen = view.getPronadjenCB().isSelected();
        boolean selektovanIzgubljen = view.getIzgubljenCB().isSelected();

        vanzemaljskiLikovi.setPredicate(new Predicate<Vanzemaljac>() {

            @Override
            public boolean test(Vanzemaljac vanzemaljac) {

                if(selektovanIzgubljen && !vanzemaljac.isIzgubljen()){
                    return false;
                }
                else if(selektovanPronadjen && !vanzemaljac.isPronadjen()){
                    return false;
                }

                if(vanzemaljac.getKontinent() == null)
                    return false;

                if(kontinent.equalsIgnoreCase("svi kontinenti")){
                    return true;
                }

                if(!vanzemaljac.getKontinent().equalsIgnoreCase(kontinent)){
                    return false;
                }

                return true;
            }
        });

        ObservableList<Vanzemaljac> cicici = FXCollections.observableArrayList(vanzemaljskiLikovi);         // napravim observable listu u kojoj stavim filter listu
        view.getTabela().setItems(cicici);          /// vracam observable listu !!!
        view.getTabela().refresh();

    }



    public GlavniView getView() {
        return view;
    }

    public void setView(GlavniView view) {
        this.view = view;
    }
}
