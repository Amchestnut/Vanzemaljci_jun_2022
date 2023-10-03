package view;

import controller.FiltrirajAkcija;
import controller.SacuvajAkcija;
import controller.UpisiAkcija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Database;
import model.Regija;
import model.Vanzemaljac;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GlavniView extends BorderPane {

    private Label kontintentlb;
    private Label pronadjenlb;
    private Label izgubljenlb;
    private Label desniIzgubljenlb;
    private Label porukalb;

    private ComboBox<String> kontinentcmb;
    private ComboBox<String> drzavacmb;

    private CheckBox pronadjenCB;
    private CheckBox izgubljenCB;
    private ToggleGroup kupujemProdajemtg;

    private Button filtrirajBtn;
    private Button upisiBtn;
    private Button sacuvajBtn;

    private TableView<Vanzemaljac> tabela;

    private Set<String> kontinentiSet;
    private Set<String> drzaveSet;

    private ObservableList<Regija> regijeOL;
    private ObservableList<Vanzemaljac> vanzemaljciOL;

    public GlavniView() {
        inicijalizacija();
        pozicioniranje();
        akcija();
    }

    private void inicijalizacija() {
        kontintentlb = new Label("Kontinent");
        pronadjenlb = new Label("Pronadjen");
        izgubljenlb = new Label("Izgubljen");
        desniIzgubljenlb = new Label("Pronadjen");
        porukalb = new Label("Poruka");

        kontinentcmb = new ComboBox<>();
        drzavacmb = new ComboBox<>();

        pronadjenCB = new CheckBox();
        izgubljenCB = new CheckBox();

        // OVO JE NOOOOOOOOOOOOOOOOOOOOOOOOOOOVO, nisam znao !
        pronadjenCB.setOnAction(event -> {
            if (pronadjenCB.isSelected()) {
                izgubljenCB.setSelected(false);  // Deselect the other checkbox
            }
        });

        izgubljenCB.setOnAction(event -> {
            if (izgubljenCB.isSelected()) {
                pronadjenCB.setSelected(false);  // Deselect the other checkbox
            }
        });

        filtrirajBtn = new Button("Filtriraj");
        upisiBtn = new Button("Upisi");
        sacuvajBtn = new Button("Sacuvaj");

        tabela = new TableView<>();                                                                         // 1) a moze i ovde u () da se doda OL lista
        regijeOL = FXCollections.observableArrayList(Database.getInstance().getRegije());
        vanzemaljciOL = FXCollections.observableArrayList(Database.getInstance().getVanzemaljci());

        kontinentiSet = new TreeSet<>();
        drzaveSet = new TreeSet<>();

        for(Regija r : Database.getInstance().getRegije()){
            kontinentiSet.add(r.getKontinent());
            drzaveSet.add(r.getDrzava());
        }

        kontinentcmb.getItems().addAll(kontinentiSet);
        kontinentcmb.getItems().add("Svi kontinenti");
        kontinentcmb.setValue(kontinentcmb.getItems().get(0));
        drzavacmb.getItems().addAll(drzaveSet);
        drzavacmb.setValue(drzavacmb.getItems().get(0));


        TableColumn<Vanzemaljac, String> idKolona = new TableColumn<>("ID");
        TableColumn<Vanzemaljac, String> kontinentKolona = new TableColumn<>("Kontinent");
        TableColumn<Vanzemaljac, String> drzavaKolona = new TableColumn<>("Drzava");

        idKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        kontinentKolona.setCellValueFactory(new PropertyValueFactory<>("kontinent"));
        drzavaKolona.setCellValueFactory(new PropertyValueFactory<>("drzava"));

        tabela.setItems(vanzemaljciOL);                                                                     // 2) moze i ovako sa setItems
        tabela.getColumns().addAll(idKolona, kontinentKolona, drzavaKolona);

    }

    private void pozicioniranje() {
        HBox gore = new HBox();
        gore.getChildren().addAll(kontintentlb, kontinentcmb, pronadjenCB, pronadjenlb, izgubljenCB, izgubljenlb, filtrirajBtn);
        gore.setAlignment(Pos.CENTER);
        gore.setSpacing(5);
        gore.setPadding(new Insets(5));

        VBox desno = new VBox();
        desno.getChildren().addAll(desniIzgubljenlb, drzavacmb, upisiBtn, porukalb);
        desno.setAlignment(Pos.CENTER);
        desno.setSpacing(5);
        desno.setPadding(new Insets(20));

        tabela.setPrefWidth(600);

        VBox dole = new VBox();
        dole.getChildren().addAll(sacuvajBtn);
        dole.setAlignment(Pos.CENTER);
        dole.setPadding(new Insets(20));
        dole.setPadding(new Insets(20));

        this.setPadding(new Insets(40));

        this.setTop(gore);
        this.setRight(desno);
        this.setLeft(tabela);
        this.setBottom(dole);

    }

    private void akcija(){
        filtrirajBtn.setOnAction(new FiltrirajAkcija(this));
        upisiBtn.setOnAction(new UpisiAkcija(this));
        sacuvajBtn.setOnAction(new SacuvajAkcija(this));
    }

    public Label getKontintentlb() {
        return kontintentlb;
    }

    public void setKontintentlb(Label kontintentlb) {
        this.kontintentlb = kontintentlb;
    }

    public Label getPronadjenlb() {
        return pronadjenlb;
    }

    public void setPronadjenlb(Label pronadjenlb) {
        this.pronadjenlb = pronadjenlb;
    }

    public Label getIzgubljenlb() {
        return izgubljenlb;
    }

    public void setIzgubljenlb(Label izgubljenlb) {
        this.izgubljenlb = izgubljenlb;
    }

    public Label getDesniIzgubljenlb() {
        return desniIzgubljenlb;
    }

    public void setDesniIzgubljenlb(Label desniIzgubljenlb) {
        this.desniIzgubljenlb = desniIzgubljenlb;
    }

    public Label getPorukalb() {
        return porukalb;
    }

    public void setPorukalb(Label porukalb) {
        this.porukalb = porukalb;
    }

    public ComboBox<String> getKontinentcmb() {
        return kontinentcmb;
    }

    public void setKontinentcmb(ComboBox<String> kontinentcmb) {
        this.kontinentcmb = kontinentcmb;
    }

    public ComboBox<String> getDrzavacmb() {
        return drzavacmb;
    }

    public void setDrzavacmb(ComboBox<String> drzavacmb) {
        this.drzavacmb = drzavacmb;
    }

    public CheckBox getPronadjenCB() {
        return pronadjenCB;
    }

    public void setPronadjenCB(CheckBox pronadjenCB) {
        this.pronadjenCB = pronadjenCB;
    }

    public CheckBox getIzgubljenCB() {
        return izgubljenCB;
    }

    public void setIzgubljenCB(CheckBox izgubljenCB) {
        this.izgubljenCB = izgubljenCB;
    }

    public Button getFiltrirajBtn() {
        return filtrirajBtn;
    }

    public void setFiltrirajBtn(Button filtrirajBtn) {
        this.filtrirajBtn = filtrirajBtn;
    }

    public Button getUpisiBtn() {
        return upisiBtn;
    }

    public void setUpisiBtn(Button upisiBtn) {
        this.upisiBtn = upisiBtn;
    }

    public Button getSacuvajBtn() {
        return sacuvajBtn;
    }

    public void setSacuvajBtn(Button sacuvajBtn) {
        this.sacuvajBtn = sacuvajBtn;
    }

    public TableView<Vanzemaljac> getTabela() {
        return tabela;
    }

    public void setTabela(TableView<Vanzemaljac> tabela) {
        this.tabela = tabela;
    }

    public Set<String> getKontinentiSet() {
        return kontinentiSet;
    }

    public void setKontinentiSet(Set<String> kontinentiSet) {
        this.kontinentiSet = kontinentiSet;
    }

    public Set<String> getDrzaveSet() {
        return drzaveSet;
    }

    public void setDrzaveSet(Set<String> drzaveSet) {
        this.drzaveSet = drzaveSet;
    }

    public ObservableList<Regija> getRegijeOL() {
        return regijeOL;
    }

    public void setRegijeOL(ObservableList<Regija> regijeOL) {
        this.regijeOL = regijeOL;
    }

    public ObservableList<Vanzemaljac> getVanzemaljciOL() {
        return vanzemaljciOL;
    }

    public void setVanzemaljciOL(ObservableList<Vanzemaljac> vanzemaljciOL) {
        this.vanzemaljciOL = vanzemaljciOL;
    }
}
