/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserversidechat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import rmicommonchat.ServerInterface;
import rmicommonchat.clientInterface;

/**
 *
 * @author salma
 */
public class ServerImplementClass extends UnicastRemoteObject implements ServerInterface{
    
    ArrayList<clientInterface> clientsList=new ArrayList<clientInterface>();
    
    public ServerImplementClass() throws RemoteException {
    }
    

    @Override
    public void broadcastMessage(byte[] message) throws RemoteException {
        for(int i=0;i<clientsList.size();i++)
        {
            clientsList.get(i).recieve(message);
        }
    }

    @Override
    public void registerClient(clientInterface client) throws RemoteException {
        clientsList.add(client);
    }

    @Override
    public void unregisterClient(clientInterface existingClient) throws RemoteException {
        for(int i=0;i<clientsList.size();i++)
        {
           
                clientsList.remove(existingClient);
            
        }
    }
    
}
