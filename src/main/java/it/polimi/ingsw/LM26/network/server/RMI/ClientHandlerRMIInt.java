package it.polimi.ingsw.LM26.network.server.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandlerRMIInt extends Remote {

    void login (String username) throws RemoteException;

    void disconnect(String username) throws RemoteException;
}