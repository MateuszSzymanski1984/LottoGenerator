package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ILotek extends Remote {
   public  List<Lotek> dajOstatnie(int n) throws RemoteException;

   public int dajTemperature(int liczba, int ile) throws RemoteException;
   public List<Integer> dajTemperatury(int ile) throws RemoteException;

}