package RMI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LotekServis extends UnicastRemoteObject implements ILotek {
    List<Lotek> draws;


    public LotekServis() throws RemoteException {
        draws = new ArrayList<>();
        System.out.println("Serwis Mini Lotek został uruchomiony");
        read();
        System.out.println("Dane archiwalne losowań zostały zaimportowane");

    }

    private void read() {
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

    @Override
    public List<Lotek> dajOstatnie(int n) throws RemoteException {
        List<Lotek> ostatnie = new ArrayList<>();
        int limit = draws.size() - n;
        for (int i = 0; i < draws.size(); i++)
            if (i >= limit) {
                Lotek l = draws.get(i);
                ostatnie.add(l);
            }
        return ostatnie;
    }

    @Override
    public int dajTemperature(int liczba, int ile) throws RemoteException{
        System.out.println("Zażądano podania temperatury dla liczby " + liczba);
        int temp = 0;
        List<Lotek> ostatnie = dajOstatnie(ile);
        for (Lotek los : ostatnie) {
            int[] w = los.getResult();
            if (w[0] == liczba || w[1] == liczba || w[2] == liczba || w[3] == liczba || w[4] == liczba)
                temp++;
        }
        return temp;
    }

    @Override
    public List<Integer> dajTemperatury(int ile) throws RemoteException {
        List<Integer> temperatury = new ArrayList<>();
        for (int i = 1; i < 43; i++) {
            int a = dajTemperature(i, ile);
            temperatury.add(a);
        }
        return temperatury;
    }

}
