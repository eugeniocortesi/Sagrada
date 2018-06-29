package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveWithNoValueRestriction3 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public MoveWithNoValueRestriction3() {
    }

    public MoveWithNoValueRestriction3(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="MoveWithNoValueRestriction3";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="MoveWithNoValueRestriction3";
        this.typeToolCard = "ToolCard";

    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){toolcard.setOneToken(player);}

    public void setTwoToken(PlayerZone player){toolcard.setTwoToken(player);}

    @Override
    public boolean isInUse() {
        return toolcard.isInUse();
    }

    @Override
    public void setInUse(boolean inUse) { toolcard.setInUse(inUse); }

    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }


    public boolean play(int player){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play (Box fromBox,Box toBox, int pl)  {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        DieInt die = fromBox.getDie();
        if(!fromBox.isIsPresent()){
            System.out.println("no die found");
            return false;
        }
        if(toBox.isIsPresent()){
            System.out.println("a die is already present here ");
            return false;
        }
        PlaceDie placement = new PlaceDie(die, toBox, player);
        fromBox.free();
        if(player.getPlayerBoard().getNumDice()==1) {
            if (placement.checkColorRestriction() && placement.checkEdgeRestrictions()) {
                toBox.setDie(die);
                return true;
            }
        }
        else if (placement.checkColorRestriction() && placement.checkNearByRestrictions() ){
            toBox.setDie(die);
            return true;
        }

        System.out.println("error card 2");
        fromBox.setDie(die);
        return false;

    }

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
}
