package it.polimi.ingsw.LM26.network.client;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.network.server.ClientHandlerInt;
import it.polimi.ingsw.LM26.network.server.RMI.ClientHandlerRMIRemoteInt;
import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.networkServer.Server.VirtualControllerInt;

import java.rmi.RemoteException;
import java.util.Observable;

public class ConnectionToServer extends ClientHandlerInt implements ControllerInt {

    ClientHandlerRMIRemoteInt clientHandlerRMIRemote;

    public ConnectionToServer(ClientHandlerRMIRemoteInt clientHandlerRMIRemote){
        this.clientHandlerRMIRemote = clientHandlerRMIRemote;
    }

    public void login(String username){

    }

    public void disconnect(String username){

    }


    public boolean check(Die dieFromDraft, Box toBox, int player) {
        return false;
    }

    public boolean check(ToolCard twoThree, Box fromBox, Box toBox, int player) {
        return false;
    }

    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player) {
        return false;
    }

    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player) {
        return false;
    }

    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player) {
        return false;
    }

    public boolean check(ToolCard one, Die dieFromDraft, String inDecrement, int player) {
        return false;
    }

    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player) {
        return false;
    }

    public boolean check(ToolCard seven, int player) {
        return false;
    }

    public void update(Observable o, Object arg) {

    }

    public boolean check(String noAction, int player) {
        return false;
    }
}