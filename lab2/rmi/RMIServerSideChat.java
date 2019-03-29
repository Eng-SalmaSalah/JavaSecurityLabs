/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salma
 */
public class RMIServerSideChat {

    /**
     * @param args the command line arguments
     */
    
    public RMIServerSideChat() {
        try {
            ServerImplementClass chatService = new ServerImplementClass();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("chatService", chatService);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RMIServerSideChat();
    }
    
}
