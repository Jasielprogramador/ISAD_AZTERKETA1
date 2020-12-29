package ehu.isad.controller;

import com.google.gson.Gson;
import ehu.isad.Main;
import ehu.isad.model.Txanpona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.converter.DateTimeStringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable {

    @FXML
    private Button btnSartu;

    @FXML
    private Button btnGorde;

    @FXML
    private ComboBox<String> cmbCombo;

    @FXML
    private TableView<Txanpona> tbvTaula;

    @FXML
    private TableColumn<Txanpona, Integer> tvID;

    @FXML
    private TableColumn<Txanpona, String> tvTxanpon;

    @FXML
    private TableColumn<Txanpona, DateTimeStringConverter> tvNoiz;

    @FXML
    private TableColumn<Txanpona, Float> tvZenbat;

    @FXML
    private TableColumn<Txanpona, Float> tvBolumena;

    @FXML
    private TableColumn<Txanpona, Image> tvPortaera;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void onClick(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        if (btn.equals(this.btnSartu)) {
            List<Txanpona> emaitza = DBHasiera.getInstance().datuBaseaLortu();
            Txanpona txanpona = this.readFromUrl(this.cmbCombo.getValue());
            emaitza.add(txanpona);
            ObservableList<Txanpona> a = FXCollections.observableArrayList(emaitza);
            tbvTaula.setItems(a);

            tvID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tvNoiz.setCellValueFactory(new PropertyValueFactory<>("data"));
            tvTxanpon.setCellValueFactory(new PropertyValueFactory<>("mota"));
            tvBolumena.setCellValueFactory(new PropertyValueFactory<>("bolumena"));
            tvZenbat.setCellValueFactory(new PropertyValueFactory<>("balioa"));
        }

        else if (btn.equals(this.btnGorde)) {
            List<Txanpona> datuak = this.tvTaulakoDatuakLortu();
            DBHasiera.getInstance().datuBaseaEzabatu();
            DBHasiera.getInstance().datuBaseaGorde(datuak);
        }

    }

    private Txanpona readFromUrl(String txanpona) throws IOException {
        URL coin;
        String inputLine = "";
        Txanpona txanpon = null;

        try {
            coin = new URL("https://api.gdax.com/products/" + txanpona + "-eur/ticker");
            URLConnection yc = coin.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            inputLine = in.readLine();

            Gson gson = new Gson();
            txanpon = gson.fromJson(inputLine, Txanpona.class);

            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return txanpon;
    }

    private List<Txanpona> tvTaulakoDatuakLortu(){

        List<Txanpona> e = new ArrayList<>();

        for(int i = 0;i<this.tbvTaula.getItems().size();i++){
            e.add(this.tbvTaula.getItems().get(i));
        }

        return e;
    }

    public void hasieraketa() throws IOException {
        List<Txanpona> emaitza = DBHasiera.getInstance().datuBaseaLortu();
        ObservableList<Txanpona> a = FXCollections.observableArrayList(emaitza);
        tbvTaula.setItems(a);

        tvID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvTxanpon.setCellValueFactory(new PropertyValueFactory<>("mota"));
        tvBolumena.setCellValueFactory(new PropertyValueFactory<>("bolumena"));
        tvZenbat.setCellValueFactory(new PropertyValueFactory<>("balioa"));
        tvNoiz.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCombo.getItems().addAll("ETH","LTC","BTC");
        cmbCombo.getSelectionModel().selectFirst();
        try {
            hasieraketa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
