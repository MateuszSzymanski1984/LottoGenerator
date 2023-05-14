package com.example.lottofx;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

    public class Main {
        static List<Lotek> results;

        public static void main(String[] args) {
            results = new ArrayList<>();
            read();
            List<Lotek> filtered = results
                    .stream()

                    .filter(l -> (l.day >= 01) && (l.day <= 31) && (l.month >= 1) && (l.month <= 12) && (l.year > 1980))
                    .collect(Collectors.toList());
            System.out.println("Ostatnie wyniki");
            pokazostatnie(filtered, 200);
            System.out.println("--------------------------------");
            List<Lotek>ostatnie = dajOstatnie(results,200);
            int[] howmanytimes = new int[43];

            for (Lotek l : ostatnie) {
                int[] result = l.getResult();
//            System.out.format("%2d-%2d-%4d : %2d %2d %2d %2d %2d %n", l.day, l.month, l.year, result[0], result[1], result[2], result[3],
                //                 result[4]);
                howmanytimes[result[0]]++;
                howmanytimes[result[1]]++;
                howmanytimes[result[2]]++;
                howmanytimes[result[3]]++;
                howmanytimes[result[4]]++;
            }
            for (int i = 1; i < 43; i++) {
                if (howmanytimes[i] > 0)
                    System.out.println(i + " : " + howmanytimes[i] + " ");
            }
        }

        public static void read() {
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
                    results.add(lotek);
                }
                br.close();
                fl.close();

            } catch (IOException ix) {
                System.out.println(ix.getMessage());
            }
        }

        private static void pokazostatnie(List<Lotek> draws, int n) {
            int limit = draws.size() - n;
            for (int i = 0; i < draws.size(); i++)
                if (i >= limit) {
                    Lotek l = draws.get(i);
                    int[] result = l.getResult();
                    System.out.format("%2d-%2d-%4d : %2d %2d %2d %2d %2d %n", l.day, l.month, l.year, result[0], result[1], result[2], result[3],
                            result[4]);
                }
        }

        private static List<Lotek> dajOstatnie(List<Lotek> draws, int n) {
            List<Lotek> ostatnie = new ArrayList<>();
            int limit = draws.size() - n;
            for (int i = 0; i < draws.size(); i++)
                if (i >= limit) {
                    Lotek l = draws.get(i);
                    ostatnie.add(l);
                }
            return ostatnie;
        }

/*  private static void checkthetable(List<Lotek> draws, int[] tab) {

        int howmuch2 = 0;
        int howmuch3 = 0;
        int howmuch4 = 0;
        int howmuch5 = 0;
       int[] howmanytimes = new int[43];
        for (int i = 0; i < tab.length; i++) {
            int v = tab[i];

            for (Lotek draw : draws) {
                int[] los = draw.getResult();
                if (los[0] == v || los[1] == v || los[2] == v || los[3] == v || los[4] == v) {
                    int set = 1;
                    for (int j = 0; j < tab.length; j++) {
                        if (j == i) continue;
                        int x = tab[j];
                        if (los[0] == x || los[1] == x || los[2] == x || los[3] == x || los[4] == x) {
                            set++;
                        }
                    }
                    if (set == 2) howmuch2++;
                    if (set == 3) howmuch3++;
                    if (set == 4) howmuch4++;
                    if (set == 5) howmuch5++;
                }
            }
        }
        System.out.println("howmuch 2: " + howmuch2);
        System.out.println("howmuch 3: " + howmuch3);
        System.out.println("howmuch 4: " + howmuch4);
        System.out.println("howmuch 5: " + howmuch5);
        System.out.println("-----------------------");
    }

 */
    }


