package ClientSide;

import RMI.ILotek;
import RMI.Lotek;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class KlientKonsolowy {
    public static void main(String[] args) {

        try {
            ILotek serwis =
                    (ILotek) Naming.lookup("rmi://127.0.0.1:1900/lotek");
            // obliczenie temperatur dla liczba od 1 do 42
            List<Integer> temperatury = serwis.dajTemperatury(100);
            for(int i = 1; i < 42; i++) {
                System.out.println("Dla " + i + " temp = " + temperatury.get(i));
            }
            int N = 30;
            // Pobranie ostatnich N losowań
            List<Lotek> ostatnie = serwis.dajOstatnie(N);
            for(int i = 0; i < N; i++) {
                Lotek l = ostatnie.get(i);
                int[] result = l.getResult();
                System.out.format("%2d-%2d-%4d : %2d %2d %2d %2d %2d %n", l.getDay(), l.getMonth(), l.getYear(), result[0], result[1], result[2], result[3],
                        result[4]);
                System.out.println("Wynik");
            }

        } catch (NotBoundException e) {
            System.out.println("Not bound " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Malformed " + e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Remote " + e.getMessage());
        }
    }
}
