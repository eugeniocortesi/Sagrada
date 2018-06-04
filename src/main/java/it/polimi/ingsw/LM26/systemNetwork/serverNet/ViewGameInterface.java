package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ViewGameInterface extends Observable {

    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

}
