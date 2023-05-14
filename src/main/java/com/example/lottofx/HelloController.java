package com.example.lottofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    List<Lotek> draws= new ArrayList<>();
    @FXML
    private Label welcomeText;
    @FXML
    Button przycisk = new Button();
    @FXML
    Label nazwa = new Label();
    @FXML
    TextField pokazLiczby;
    @FXML
    Slider suwak = new Slider();
    @FXML
    ComboBox combo1 = new ComboBox();
    @FXML
    ComboBox combo2 = new ComboBox();
    @FXML
    Button przycisk2 = new Button();
    @FXML
    TextField grupa = new TextField();
    @FXML
    Button oblicz = new Button();
    @FXML
    ComboBox combo3 = new ComboBox();
    CategoryAxis xaxis = new CategoryAxis();   //
    NumberAxis yaxis = new NumberAxis();
    @FXML
    BarChart wykres = new BarChart<String,Number>(xaxis,yaxis);

    XYChart.Series seria = new XYChart.Series();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        seria.getData().add(new XYChart.Data("", 0));
        seria.getData().add(new XYChart.Data("", 0));
        wykres.getData().add(seria);
        read();
      //  suwak.setMax(100);
    //    ileSLider.setMin(5);
     //   ileSLider.setValue(7);
    }

    public void read() {
        FileReader fl = null;
        try {
            fl = new FileReader("results.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fl);

        try {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fragments = line.split(";");
                int d = Integer.parseInt(fragments[1]);
                int m = Integer.parseInt(fragments[2]);
                int r = Integer.parseInt(fragments[3]);
                int l1 = Integer.parseInt(fragments[4]);
                int l2 = Integer.parseInt(fragments[5]);
                int l3 = Integer.parseInt(fragments[6]);
                int l4 = Integer.parseInt(fragments[7]);
                int l5 = Integer.parseInt(fragments[8]);
                int[] tab = new int[]{
                        l1, l2, l3, l4, l5
                };

                Lotek lotek = new Lotek(d, m, r, tab);
                draws.add(lotek);
            }
            br.close();
            fl.close();

        } catch (IOException ix) {
            System.out.println(ix.getMessage());
        }
    }

    private void pokazostatnie(List<Lotek> draws, int n) {
        int limit = draws.size() - n;
        for (int i = 0; i < draws.size(); i++)
            if (i >= limit) {
                Lotek l = draws.get(i);
                int[] result = l.getResult();
                System.out.format("%2d-%2d-%4d : %2d %2d %2d %2d %2d %n", l.day, l.month, l.year, result[0], result[1], result[2], result[3],
                        result[4]);
            }


        }


    private List<Lotek> dajOstatnie(List<Lotek> draws, int n) {
        List<Lotek> ostatnie = new ArrayList<>();
        int limit = draws.size() - n;
        for (int i = 0; i < draws.size(); i++)
            if (i >= limit) {
                Lotek l = draws.get(i);
                ostatnie.add(l);
            }
        return ostatnie;
    }

    private int pokaztemperature(List<Lotek> draws, int a) {
        int temp = 0;
        for (Lotek los : draws){
            int[]w= los.getResult();
            if(w[0]==a || w[1]==a || w[2]==a || w[3]==a || w[4]==a)
                temp++;
        }
        return temp;
    }

    @FXML
    private void Ile(ActionEvent event){
        double v = suwak.getValue();
        int ile = (int)v;
        combo1.getItems().clear();
        List<Lotek> ostatnie = dajOstatnie(draws,ile);
        for(Lotek los:ostatnie){
            int[]w= los.getResult();
            String s0 = ""+w[0];
            if(w[0]<10)
                s0 = " "+w[0];
            String s1 = ""+w[1];
            if(w[1]<10)
                s1 = " "+w[1];
            String s2 = ""+w[2];
            if(w[2]<10)
                s2 = " "+w[2];
           String day = "" +los.day;
           if(los.day<10){
               day=" "+los.day;
           }
            combo1.getItems().add(los.year+ "-"+ los.month+ "-"+ day+ ":  " +s0+" "+s1+" "+s2+" "+w[3]+
                    " "+w[4]);
        }
    }

    public void Oblicztemperature(ActionEvent event) {
        int ile = (int)suwak.getValue();
        List<Lotek>ostatnie = dajOstatnie(draws,ile);
        combo2.getItems().clear();
        for(int i =1; i<43;i++){
            int a = pokaztemperature(ostatnie,i);
            combo2.getItems().add(i+" wylosowana"+ " "+a+ "razy");
        }


    }

    public void Obliczjakgraja(ActionEvent event) {
        String napis = grupa.getText();
        String[] liczbyS = napis.split(",");
        List<Integer> liczby = new ArrayList<>();
        for(String s:liczbyS) {
            liczby.add(Integer.parseInt(s));
        }
        List<Lotek> lista = new ArrayList<>();
        double v = suwak.getValue();
        int ile = (int)v;
        combo3.getItems().clear();
        List<Lotek> ostatnie = dajOstatnie(draws,ile);
        for(Lotek los:ostatnie){                           //pobieramy losowanie z danego dnia
            int wynik = 0;                       //ile bylo trafien z grupy liczb
            int[]w = los.getResult();     //nasze liczby z losowania
            List<Integer> trafione = new ArrayList<>();
            for(int x:liczby){
                if (x== w[0] || x==w[1] || x==w[2] || x==w[3] || x==w[4]){
                    wynik++;
                    trafione.add(x);
                }
            }
            Lotek nowy = los;
            los.setTrafienia(wynik);
            los.setTrafione(trafione);      //przekazujemy liste trafionych
            nowy.setResult(w);
            lista.add(nowy);
        }
         for(Lotek los: lista )   {
             String day = "" +los.day;     //lista to kolekcja typu arraylist z obiektami typu Lotek
             if(los.day<10){               //znajdz wszystkie obiekty typu Lotek o nazwie los
                 day=" "+los.day;
                 }
             String listaTrafionych = "";
             for(int t: los.getTrafione()) listaTrafionych += t + ",";
             if(los.getTrafienia()>0)
                 combo3.getItems().add(los.year+ "-"+ los.month+ "-"+ day+ " : [" + los.getTrafienia()+" ]"+listaTrafionych+ " ");
         }
         pokazWykres(lista);
    }
        private void pokazWykres(List<Lotek> lista) {
        wykres.getData().clear();
        wykres.setTitle("wykres");
        xaxis.setLabel("daty");
        yaxis.setLabel("liczba trafień");
  //      yaxis.setMaxHeight(6);
            seria.getData().clear();
       XYChart.Series seria = new XYChart.Series();
         for (Lotek los: lista){
             String day = "" +los.day;     //lista to kolekcja typu arraylist z obiektami typu Lotek
             if(los.day<10){               //znajdz wszystkie obiekty typu Lotek o nazwie los
                 day=" "+los.day;
             }
             String data = los.getYear()+ "-" + los.getMonth()+"-"+ day;
             seria.getData().add(new XYChart.Data(data, los.getTrafienia()));
             }
         wykres.getData().add(seria);
         }
        }

