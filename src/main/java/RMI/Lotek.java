package RMI;

import java.util.List;

public class Lotek {
        int day;
        int month;

    public List<Integer> getTrafione() {
        return trafione;
    }

    public void setTrafione(List<Integer> trafione) {
        this.trafione = trafione;
    }

    List<Integer> trafione;

        public int getTrafienia() {
            return trafienia;
        }

        public void setTrafienia(int trafienia) {
            this.trafienia = trafienia;
        }

        int trafienia;

        public Lotek(int day, int month, int year, int[] result) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.result = result;
        }
        public Lotek(){

        }

        int year;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int[] getResult() {
            return result;
        }

        public void setResult(int[] result) {
            this.result = result;
        }

        int[] result;

    }


