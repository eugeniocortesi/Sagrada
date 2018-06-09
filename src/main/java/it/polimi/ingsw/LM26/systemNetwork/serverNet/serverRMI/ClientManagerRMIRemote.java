package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.ClientManagerRemote;

import java.rmi.RemoteException;

public class ClientManagerRMIRemote implements ClientManagerRemote {

    private ClientManager clientManagerRMI;

    public ClientManagerRMIRemote(ClientManager clientManager){
        clientManagerRMI = clientManager;
    }

    @Override
    public void connect() throws RemoteException {
        clientManagerRMI.connect();
    }

    @Override
    public int getAvailableId() throws RemoteException {
        return clientManagerRMI.getAvailableId();
    }

    @Override
    public void login(String name) throws RemoteException {
        clientManagerRMI.login(name);
    }

    @Override
    public void disconnect() throws RemoteException {
        clientManagerRMI.disconnect();
    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) throws RemoteException {
        clientManagerRMI.chosenWindowPattern(actionEventWindow);
    }

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) throws RemoteException {
        clientManagerRMI.sendActionEventFromView(actionEvent);
    }


}
