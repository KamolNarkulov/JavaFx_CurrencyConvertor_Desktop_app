package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
    @FXML
    private TextField uzs_field;
    @FXML
    private TextField usd_field;
    @FXML
    private TextField eur_field;
    @FXML
    private TextField rub_field;

    @FXML
    private TextField diffdateUZS;
    @FXML
    private TextField diffdateUSD;
    @FXML
    private TextField diffdateEUR;
    @FXML
    private TextField diffdateRUB;

    public String diffUZS;
    public String diffUSD;
    public String diffEUR;
    public String diffRUB;

    public String dateUZS;
    public String dateUSD;
    public String dateEUR;
    public String dateRUB;

    public double rateUZS;
    public double rateUSD;
    public double rateEUR;
    public double rateRUB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void CurrencyConvertAction(ActionEvent event) {
        Gson gson = new Gson();
        URL url;

        {
            try {
                /*URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                Type type = new TypeToken<ArrayList<Currency>>() {
                }.getType();
                currencies = gson.fromJson(reader, type);*/
                url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                Type type = new TypeToken<ArrayList<Currency>>() {
                }.getType();
//                List<CurrencyJson> currencyList = gson.fromJson(reader, type);
                List<CurrencyJson> currencyList = Arrays.asList(gson.fromJson(reader,
                        CurrencyJson[].class));
                for (CurrencyJson currencyJson : currencyList) {
                    if (currencyJson.getCcy().equals("UZS")){
                        rateUZS = Double.parseDouble(currencyJson.getRate());
                        diffUZS = currencyJson.getDiff();
                        dateUZS = currencyJson.getDate();
                    }else if (currencyJson.getCcy().equals("USD")){
                        rateUSD = Double.parseDouble(currencyJson.getRate());
                        diffUSD = currencyJson.getDiff();
                        dateUSD = currencyJson.getDate();
                    }else if (currencyJson.getCcy().equals("EUR")){
                        rateEUR = Double.parseDouble(currencyJson.getRate());
                        diffEUR = currencyJson.getDiff();
                        dateEUR = currencyJson.getDate();
                    }else if (currencyJson.getCcy().equals("RUB")){
                        rateRUB = Double.parseDouble(currencyJson.getRate());
                        diffRUB = currencyJson.getDiff();
                        dateRUB = currencyJson.getDate();
                    }
                }
                if (!uzs_field.getText().equals("")) {
                    uzs_field.setStyle("-fx-text-inner-color: blue;");
                    usd_field.setStyle("-fx-text-inner-color: green;");
                    eur_field.setStyle("-fx-text-inner-color: green;");
                    rub_field.setStyle("-fx-text-inner-color: green;");
                    double UZS = Double.parseDouble(uzs_field.getText());
                    double USD = UZS / rateUSD;
                    usd_field.setText(USD + "");

                    double EUR = UZS / rateEUR;
                    eur_field.setText(EUR + "");

                    double RUB = UZS / rateRUB;
                    rub_field.setText(RUB + "");
                    diffdateUSD.setText(checkPosNeg(diffUSD,diffdateUSD) + " date: " + dateUSD);
                    diffdateEUR.setText(checkPosNeg(diffEUR,diffdateEUR) + " date: " + dateEUR);
                    diffdateRUB.setText(checkPosNeg(diffRUB,diffdateRUB) + " date: " + dateRUB);
                } else if (!usd_field.getText().equals("")) {
                    usd_field.setStyle("-fx-text-inner-color: blue;");
                    uzs_field.setStyle("-fx-text-inner-color: green;");
                    eur_field.setStyle("-fx-text-inner-color: green;");
                    rub_field.setStyle("-fx-text-inner-color: green;");
                    double USD = Double.parseDouble(usd_field.getText());
                    double UZS = USD * rateUSD;
                    uzs_field.setText(UZS + "");

                    double EUR = (USD * rateUSD) / rateEUR;
                    eur_field.setText(EUR + "");

                    double RUB = (USD * rateUSD) / rateRUB;
                    rub_field.setText(RUB + "");

                    diffdateUZS.setText(checkPosNeg(diffUZS,diffdateUZS) + " date: " + dateUZS);
                    diffdateEUR.setText(checkPosNeg(diffEUR,diffdateEUR) + " date: " + dateEUR);
                    diffdateRUB.setText(checkPosNeg(diffRUB,diffdateRUB) + " date: " + dateRUB);
                } else if (!eur_field.getText().equals("")) {
                    eur_field.setStyle("-fx-text-inner-color: blue;");
                    usd_field.setStyle("-fx-text-inner-color: green;");
                    uzs_field.setStyle("-fx-text-inner-color: green;");
                    rub_field.setStyle("-fx-text-inner-color: green;");
                    double EUR = Double.parseDouble(eur_field.getText());
                    double UZS = EUR * rateEUR;
                    uzs_field.setText(UZS + "");

                    double USD = (EUR * rateEUR) / rateUSD;
                    usd_field.setText(USD + "");

                    double RUB = (EUR * rateEUR) / rateRUB;
                    rub_field.setText(RUB + "");

                    diffdateUSD.setText(checkPosNeg(diffUSD,diffdateUSD) + " date: " + dateUSD);
                    diffdateUZS.setText(checkPosNeg(diffUZS,diffdateUZS) + " date: " + dateUZS);
                    diffdateRUB.setText(checkPosNeg(diffRUB,diffdateRUB) + " date: " + dateRUB);
                } else if (!rub_field.getText().equals("")) {
                    rub_field.setStyle("-fx-text-inner-color: blue;");
                    usd_field.setStyle("-fx-text-inner-color: green;");
                    eur_field.setStyle("-fx-text-inner-color: green;");
                    uzs_field.setStyle("-fx-text-inner-color: green;");
                    double RUB = Double.parseDouble(rub_field.getText());
                    double UZS = RUB * rateRUB;
                    uzs_field.setText(UZS + "");

                    double EUR = (RUB * rateRUB) / rateEUR;
                    eur_field.setText(EUR + "");

                    double USD = (RUB * rateRUB) / rateUSD;
                    usd_field.setText(USD + "");

                    diffdateUSD.setText(checkPosNeg(diffUSD,diffdateUSD) + " date: " + dateUSD);
                    diffdateEUR.setText(checkPosNeg(diffEUR,diffdateEUR) + " date: " + dateEUR);
                    diffdateUZS.setText(checkPosNeg(diffUZS,diffdateUZS) + " date: " + dateUZS);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void clearAction(ActionEvent event) {
        usd_field.setText("");
        uzs_field.setText("");
        eur_field.setText("");
        rub_field.setText("");

        diffdateUZS.setText("");
        diffdateUSD.setText("");
        diffdateEUR.setText("");
        diffdateRUB.setText("");

        rub_field.setStyle("-fx-text-inner-color: black;");
        usd_field.setStyle("-fx-text-inner-color: black;");
        eur_field.setStyle("-fx-text-inner-color: black;");
        uzs_field.setStyle("-fx-text-inner-color: black;");
    }

    public String checkPosNeg(String diff, TextField diffColor){
        Double diffDouble = Double.parseDouble(diff);
        if (diffDouble>0){
            diff = "+" + diff;
            diffColor.setStyle("-fx-text-inner-color: blue;");
        }else {
            diff = "-"+diff;
            diffColor.setStyle("-fx-text-inner-color: red;");
        }
        return diff;
    }
}

