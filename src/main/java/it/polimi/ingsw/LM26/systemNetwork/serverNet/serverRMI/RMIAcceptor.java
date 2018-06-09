package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIAcceptor {

    private ServerBase myserver;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private ClientManagerRemote stub;

    private static final Logger LOGGER = Logger.getLogger(RMIAcceptor.class.getName());

    public RMIAcceptor(ServerBase serverBase, DataServerConfiguration data){
        myserver = serverBase;
        this.RMIPORTClient = data.getClientRMIPORT();
        this.RMIPORTServer = data.getServerRMIPORT();
        this.address = data.getIp();

        bind();
    }

    private void bind(){


        //Creates stub

        try{
            ClientManager clientManagerRMI = new ClientManagerRMI(myserver, this.RMIPORTServer, this.RMIPORTClient, address);
            Thread t = new Thread(clientManagerRMI);
            t.start();
            ClientManagerRemote clientManagerRemote = new ClientManagerRMIRemote(clientManagerRMI);
            stub = (ClientManagerRemote) UnicastRemoteObject.exportObject(clientManagerRemote, RMIPORTServer);
            Registry registry = LocateRegistry.createRegistry(RMIPORTServer);
            registry.bind("ClientManagerRemote",  stub);
            LOGGER.log(Level.WARNING,"Server ready, stub created");

        }catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

       /* try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(address, RMIPORTClient);
            //Looking up the registry for the remote object
            ClientViewRemote skeleton = (ClientViewRemote) registry.lookup("ClientViewRemote");
            myserver.addView(skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }*/
    }
}