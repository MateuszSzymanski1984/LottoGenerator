package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Server {
    public static void main(String[] args) {
        try{ILotek servis = new LotekServis();
            LocateRegistry.createRegistry(1900);
            Naming.rebind("rmi://127.0.0.1:1900/lotek", servis);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
