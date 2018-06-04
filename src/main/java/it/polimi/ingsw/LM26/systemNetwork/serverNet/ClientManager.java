package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ClientManager implements Runnable {

    public abstract void connect();

    public abstract int getAvailableId();

    public abstract void requestedLogin();

    public abstract void login(String name);

    public abstract void logged(Boolean l, String name);

    public abstract void disconnect();

    public abstract void run();

    public abstract void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    public abstract void chosenWindowPattern(String user, WindowPatternCard windowcard);
}
