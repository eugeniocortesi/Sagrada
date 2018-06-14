package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class ChangeDieValue1 extends ToolCardDecorator {

    private ToolCard toolcard = null;


    public ChangeDieValue1(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="ChangeDieValue1";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="ChangeDieValue1";
        this.typeToolCard = "ToolCard";

    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){}

    public void setTwoToken(PlayerZone player){}

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public void setInUse(boolean inUse) {

    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }

    public boolean play( int player){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play (DieInt die, String inDeCrement) {


        if (inDeCrement == "increment") {
            if (die.getValue() == 6) {
                System.out.println("error, choose a lower value");
                return false;
            }
            die.increment();
            die.dump();
            return true;
        }

        if (inDeCrement == "decrement"){
            if (die.getValue() == 1) {
                System.out.println("error, choose a higher value");
                return false;
            }
            die.decrement();
            die.dump();
            return true;
        }

        return false;


    }
}
